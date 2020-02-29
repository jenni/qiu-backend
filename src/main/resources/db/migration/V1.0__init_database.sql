--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: ping_pong_tables; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.ping_pong_tables (
                                         id integer NOT NULL,
                                         address text,
                                         city character varying(255),
                                         coordinates jsonb,
                                         description text,
                                         image_url text,
                                         has_light boolean,
                                         is_indoor boolean,
                                         is_sports_club boolean,
                                         is_bar boolean,
                                         created_by integer NOT NULL,
                                         updated_at timestamp with time zone NOT NULL,
                                         created_at timestamp with time zone NOT NULL
);


--
-- Name: ping_pong_tables_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.ping_pong_tables_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: ping_pong_tables_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.ping_pong_tables_id_seq OWNED BY public.ping_pong_tables.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users (
                              id integer NOT NULL,
                              email text,
                              password text,
                              username text,
                              profile_image text,
                              starred jsonb DEFAULT '[]'::jsonb,
                              is_admin boolean DEFAULT false,
                              updated_at timestamp with time zone NOT NULL,
                              created_at timestamp with time zone NOT NULL
);


--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: ping_pong_tables id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.ping_pong_tables ALTER COLUMN id SET DEFAULT nextval('public.ping_pong_tables_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Name: ping_pong_tables ping_pong_tables_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.ping_pong_tables
    ADD CONSTRAINT ping_pong_tables_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

