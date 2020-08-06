package com.pingpong.app.services;

import com.pingpong.app.entities.PingPongTable;
import com.pingpong.app.entities.models.Coordinates;
import com.pingpong.app.repositories.PingPongTableRepository;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Service
@EnableScheduling
@AllArgsConstructor
public class CrawlerService {

    private static final String URL = "https://www.pingpongmap.net/mapcode/xml_places.php?typ=pingpong";
    private static final List<String> SUPPORTED_CITIES = List.of("berlin");

    private static final String MAIN_TAG = "marker";
    private static final String ADDRESS_TAG_KEY = "ort";
    private static final String ID_TAG_KEY = "id";
    private static final String LAT_TAG_KEY = "lat";
    private static final String LNG_TAG_KEY = "lng";
    private static final String URL_TAG_KEY = "url";
    private static final String IMAGE_URL_TAG_KEY = "fotourl";
    private static final String INFO_TAG_KEY = "info";
    private static final String LIGTH_TAG_KEY = "light";
    private static final String INDOOR_TAG_KEY = "indoor";
    private static final String BAR_TAG_KEY = "indoor";
    private static final String SPORTSCLUB_TAG_KEY = "sportsclub";

    private final PingPongTableRepository tableRepository;
    private final RestTemplate restTemplate;

    @Scheduled(cron = "0 0 0 * * *")
    public void crawl() {
        triggerCrawling();
    }

    public List<PingPongTable> triggerCrawling() {
        List<Integer> storedExternalIds = tableRepository.findAllExternalIds();
        String response = restTemplate.getForEntity(URL, String.class).getBody();
        List<PingPongTable> pingPongTables = mapNewPingPongTables(response, storedExternalIds);

        return tableRepository.saveAll(pingPongTables);
    }

    private List<PingPongTable> mapNewPingPongTables(String response,
                                                     List<Integer> storedExternalIds) {
        return Jsoup.parse(response)
                .getElementsByTag(MAIN_TAG)
                .stream()
                .filter(tag -> {
                    var externalId = parseInt(tag.attr(ID_TAG_KEY));
                    var address = tag.attr(ADDRESS_TAG_KEY);
                    return isNewTable(externalId, storedExternalIds) && isInSupportedCity(address);
                })
                .map(CrawlerService::toPingPongTable)
                .collect(toList());
    }

    private static boolean isNewTable(Integer externalId,
                                      List<Integer> storedExternalIds) {
        return !storedExternalIds.contains(externalId);
    }

    private static boolean isInSupportedCity(String text) {
        return isNotEmpty(findSupportedCity(text));
    }

    private static PingPongTable toPingPongTable(Element tag) {
        return PingPongTable.builder()
                .externalId(parseInt(tag.attr(ID_TAG_KEY)))
                .address(tag.attr(ADDRESS_TAG_KEY))
                .city(capitalize(findSupportedCity(tag.attr(ADDRESS_TAG_KEY))))
                .coordinates(Coordinates.builder()
                        .lat(parseDouble(tag.attr(LAT_TAG_KEY)))
                        .lng(parseDouble(tag.attr(LNG_TAG_KEY)))
                        .build())
                .description(tag.attr(INFO_TAG_KEY))
                .url(tag.attr(URL_TAG_KEY).equals("") ? null : tag.attr(URL_TAG_KEY))
                .imageUrl(tag.attr(IMAGE_URL_TAG_KEY).equals("") || tag.attr(IMAGE_URL_TAG_KEY).equals("#")
                        ? null
                        : tag.attr(IMAGE_URL_TAG_KEY))
                .hasLight(getBoolFromNum(parseInt(tag.attr(LIGTH_TAG_KEY))))
                .isBar(getBoolFromNum(parseInt(tag.attr(BAR_TAG_KEY))))
                .isIndoor(getBoolFromNum(parseInt(tag.attr(INDOOR_TAG_KEY))))
                .isSportsClub(getBoolFromNum(parseInt(tag.attr(SPORTSCLUB_TAG_KEY))))
                .build();
    }

    private static String findSupportedCity(String text) {
        return SUPPORTED_CITIES.stream()
                .filter(city -> text != null && text.toLowerCase().contains(city.toLowerCase()))
                .findFirst()
                .orElseThrow();
    }

    private static Boolean getBoolFromNum(Integer num) {
        return 1 == num;
    }
}
