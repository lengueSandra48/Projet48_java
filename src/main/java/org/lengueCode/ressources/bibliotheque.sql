--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2 (Ubuntu 17.2-1.pgdg22.04+1)
-- Dumped by pg_dump version 17.2 (Ubuntu 17.2-1.pgdg22.04+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
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
-- Name: emprunt; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.emprunt (
    id_emprunt bigint NOT NULL,
    membre_id bigint NOT NULL,
    livre_id bigint NOT NULL,
    date_emprunt date NOT NULL,
    date_retour_prev date NOT NULL,
    date_retour_eff date,
    status character varying(8)
);


ALTER TABLE public.emprunt OWNER TO postgres;

--
-- Name: livres_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.livres_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.livres_id_seq OWNER TO postgres;

--
-- Name: livre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.livre (
    id bigint DEFAULT nextval('public.livres_id_seq'::regclass) NOT NULL,
    titre character varying(50) NOT NULL,
    auteur character varying(50) NOT NULL,
    categorie character varying(50) NOT NULL,
    nombre_exemplaires integer NOT NULL
);


ALTER TABLE public.livre OWNER TO postgres;

--
-- Name: membre_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.membre_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.membre_id_seq OWNER TO postgres;

--
-- Name: membre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.membre (
    id bigint DEFAULT nextval('public.membre_id_seq'::regclass) NOT NULL,
    nom character varying(50) NOT NULL,
    prenom character varying(50) NOT NULL,
    email character varying(50) NOT NULL,
    date_adhesion date NOT NULL
);


ALTER TABLE public.membre OWNER TO postgres;

--
-- Data for Name: emprunt; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.emprunt (id_emprunt, membre_id, livre_id, date_emprunt, date_retour_prev, date_retour_eff, status) FROM stdin;
1234	109876	12347	2025-01-13	2025-01-25	2025-02-06	EN_COURS
1235	109876	12346	2025-01-13	2025-01-25	\N	EN_COURS
\.


--
-- Data for Name: livre; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.livre (id, titre, auteur, categorie, nombre_exemplaires) FROM stdin;
12345	Java programming	Bande de codeurs	Learning	15
12346	python programming	Zone code	Learning	55
12347	JavaScript programming	Pierre Simo	Learning	20
1	java	programming	programming	15
123	Mythe	Armang	roman	5
\.


--
-- Data for Name: membre; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.membre (id, nom, prenom, email, date_adhesion) FROM stdin;
123456	Lengue	Sandra	lenguesandra@gmail.com	2025-01-12
109876	Hassan	Salif	hassansalif@gmail.com	2025-01-12
201	Mabah	Mabahk48	Mabah@gmail.com	2025-01-13
53	Bell	Bellk48	Bell@gmail.com	2025-01-13
75	Salif	Salifk48	Salif@gmail.com	2025-01-13
420	Anaba	Anabak48	Anaba@gmail.com	2025-01-13
931	Bell	Bellk48	Bell@gmail.com	2025-01-13
136	Djillo	Djillok48	Djillo@gmail.com	2025-01-13
599	Salif	Salifk48	Salif@gmail.com	2025-01-13
413	Anaba	Anabak48	Anaba@gmail.com	2025-01-13
505	Mabah	Mabahk48	Mabah@gmail.com	2025-01-13
279	Bell	Bellk48	Bell@gmail.com	2025-01-13
184	Djillo	Djillok48	Djillo@gmail.com	2025-01-13
966	Salif	Salifk48	Salif@gmail.com	2025-01-13
218	Anaba	Anabak48	Anaba@gmail.com	2025-01-13
12345	Abou	Salam	abou@gmail.com	2025-01-14
\.


--
-- Name: livres_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.livres_id_seq', 1, false);


--
-- Name: membre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.membre_id_seq', 1, false);


--
-- Name: emprunt emprunt_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emprunt
    ADD CONSTRAINT emprunt_pkey PRIMARY KEY (id_emprunt);


--
-- Name: livre livre_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livre
    ADD CONSTRAINT livre_pkey PRIMARY KEY (id);


--
-- Name: membre membre_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.membre
    ADD CONSTRAINT membre_pkey PRIMARY KEY (id);


--
-- Name: emprunt fk_livre; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emprunt
    ADD CONSTRAINT fk_livre FOREIGN KEY (livre_id) REFERENCES public.livre(id) ON DELETE CASCADE;


--
-- Name: emprunt fk_membre; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emprunt
    ADD CONSTRAINT fk_membre FOREIGN KEY (membre_id) REFERENCES public.membre(id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

