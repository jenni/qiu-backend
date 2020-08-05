CREATE TABLE public.ping_pong_tables (
                                         id serial primary key not null,
                                         external_id integer not null,
                                         address text not null,
                                         city character varying(255),
                                         coordinates jsonb not null,
                                         description text,
                                         url character varying(255),
                                         image_url text,
                                         has_light boolean not null default false,
                                         is_indoor boolean not null default false,
                                         is_sports_club boolean not null default false,
                                         is_bar boolean not null default false,
                                         updated_at timestamp with time zone NOT NULL,
                                         created_at timestamp with time zone NOT NULL
);

create unique index ping_pong_tables_external_uid on ping_pong_tables(external_id);
