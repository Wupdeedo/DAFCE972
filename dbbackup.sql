--
-- PostgreSQL database dump
--

SET client_encoding = 'LATIN1';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

--
-- Name: drift; Type: TYPE; Schema: public; Owner: joagusta
--

CREATE TYPE drift AS ENUM (
    '4',
    '2'
);


ALTER TYPE public.drift OWNER TO joagusta;

--
-- Name: klass; Type: TYPE; Schema: public; Owner: joagusta
--

CREATE TYPE klass AS ENUM (
    'B',
    'F',
    'E'
);


ALTER TYPE public.klass OWNER TO joagusta;

--
-- Name: vaxel; Type: TYPE; Schema: public; Owner: joagusta
--

CREATE TYPE vaxel AS ENUM (
    'A',
    'M'
);


ALTER TYPE public.vaxel OWNER TO joagusta;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: bil; Type: TABLE; Schema: public; Owner: joagusta; Tablespace: 
--

CREATE TABLE bil (
    bilid integer NOT NULL,
    modellid integer NOT NULL,
    kostnad integer
);


ALTER TABLE public.bil OWNER TO joagusta;

--
-- Name: bilmodell; Type: TABLE; Schema: public; Owner: joagusta; Tablespace: 
--

CREATE TABLE bilmodell (
    modellid integer NOT NULL,
    drift drift,
    vaxel vaxel,
    tillverkare character varying(25),
    modell character varying(25)
);


ALTER TABLE public.bilmodell OWNER TO joagusta;

--
-- Name: bilschema; Type: TABLE; Schema: public; Owner: joagusta; Tablespace: 
--

CREATE TABLE bilschema (
    bilid integer NOT NULL,
    bokningsid bigint NOT NULL,
    hamtdatum date NOT NULL,
    lamningsdatum date NOT NULL,
    hamtplats integer NOT NULL,
    lamningsplats integer NOT NULL
);


ALTER TABLE public.bilschema OWNER TO joagusta;

--
-- Name: bokning; Type: TABLE; Schema: public; Owner: joagusta; Tablespace: 
--

CREATE TABLE bokning (
    bokningsid integer NOT NULL,
    kontoid bigint NOT NULL
);


ALTER TABLE public.bokning OWNER TO joagusta;

--
-- Name: bokning_bokningsid_seq; Type: SEQUENCE; Schema: public; Owner: joagusta
--

CREATE SEQUENCE bokning_bokningsid_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.bokning_bokningsid_seq OWNER TO joagusta;

--
-- Name: bokning_bokningsid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: joagusta
--

ALTER SEQUENCE bokning_bokningsid_seq OWNED BY bokning.bokningsid;


--
-- Name: bokning_bokningsid_seq; Type: SEQUENCE SET; Schema: public; Owner: joagusta
--

SELECT pg_catalog.setval('bokning_bokningsid_seq', 1, false);


--
-- Name: depot; Type: TABLE; Schema: public; Owner: joagusta; Tablespace: 
--

CREATE TABLE depot (
    depotid integer NOT NULL,
    ortid integer NOT NULL,
    adress character varying(60)
);


ALTER TABLE public.depot OWNER TO joagusta;

--
-- Name: evenemang; Type: TABLE; Schema: public; Owner: joagusta; Tablespace: 
--

CREATE TABLE evenemang (
    evenemangsid integer NOT NULL,
    namn character varying(25),
    startdatum date,
    slutdatum date,
    starttid time without time zone,
    sluttid time without time zone,
    beskrivning text,
    antalplatser smallint,
    platsid integer NOT NULL,
    kostnad integer
);


ALTER TABLE public.evenemang OWNER TO joagusta;

--
-- Name: evenemangsschema; Type: TABLE; Schema: public; Owner: joagusta; Tablespace: 
--

CREATE TABLE evenemangsschema (
    evenemangsid integer NOT NULL,
    bokningsid bigint NOT NULL,
    antal smallint
);


ALTER TABLE public.evenemangsschema OWNER TO joagusta;

--
-- Name: flight; Type: TABLE; Schema: public; Owner: joagusta; Tablespace: 
--

CREATE TABLE flight (
    flightid integer NOT NULL,
    avgangstid time without time zone,
    ankomsttid time without time zone,
    avgangsdatum date,
    ankomstdatum date,
    avgangsort integer NOT NULL,
    ankomstort integer NOT NULL,
    flygplansid integer NOT NULL
);


ALTER TABLE public.flight OWNER TO joagusta;

--
-- Name: flightbokning; Type: TABLE; Schema: public; Owner: joagusta; Tablespace: 
--

CREATE TABLE flightbokning (
    flightid integer NOT NULL,
    bokningsid bigint NOT NULL,
    nummer smallint NOT NULL
);


ALTER TABLE public.flightbokning OWNER TO joagusta;

--
-- Name: flygplan; Type: TABLE; Schema: public; Owner: joagusta; Tablespace: 
--

CREATE TABLE flygplan (
    flygplansid integer NOT NULL,
    flygplanstypsid integer NOT NULL
);


ALTER TABLE public.flygplan OWNER TO joagusta;

--
-- Name: flygplanstyp; Type: TABLE; Schema: public; Owner: joagusta; Tablespace: 
--

CREATE TABLE flygplanstyp (
    flygplanstypsid integer NOT NULL,
    platser smallint,
    rokfri boolean
);


ALTER TABLE public.flygplanstyp OWNER TO joagusta;

--
-- Name: hotell; Type: TABLE; Schema: public; Owner: joagusta; Tablespace: 
--

CREATE TABLE hotell (
    hotellid integer NOT NULL,
    namn character varying(25),
    adress character varying(60),
    ortid integer NOT NULL,
    stjarnor smallint,
    beskrivning text
);


ALTER TABLE public.hotell OWNER TO joagusta;

--
-- Name: konto; Type: TABLE; Schema: public; Owner: joagusta; Tablespace: 
--

CREATE TABLE konto (
    kontoid integer NOT NULL,
    login character varying(40),
    password bytea,
    fornamn character varying(16),
    efternamn character varying(32),
    postort character varying(32),
    postnr integer,
    email character varying(40),
    adress character varying(60),
    telefon bigint,
    tabort date
);


ALTER TABLE public.konto OWNER TO joagusta;

--
-- Name: konto_kontoid_seq; Type: SEQUENCE; Schema: public; Owner: joagusta
--

CREATE SEQUENCE konto_kontoid_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.konto_kontoid_seq OWNER TO joagusta;

--
-- Name: konto_kontoid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: joagusta
--

ALTER SEQUENCE konto_kontoid_seq OWNED BY konto.kontoid;


--
-- Name: konto_kontoid_seq; Type: SEQUENCE SET; Schema: public; Owner: joagusta
--

SELECT pg_catalog.setval('konto_kontoid_seq', 1, false);


--
-- Name: landsinfo; Type: TABLE; Schema: public; Owner: joagusta; Tablespace: 
--

CREATE TABLE landsinfo (
    landsid smallint NOT NULL,
    land character varying(25),
    visum text,
    information text
);


ALTER TABLE public.landsinfo OWNER TO joagusta;

--
-- Name: ort; Type: TABLE; Schema: public; Owner: joagusta; Tablespace: 
--

CREATE TABLE ort (
    ortid integer NOT NULL,
    ort character varying(30) NOT NULL,
    landsid smallint NOT NULL
);


ALTER TABLE public.ort OWNER TO joagusta;

--
-- Name: platser; Type: TABLE; Schema: public; Owner: joagusta; Tablespace: 
--

CREATE TABLE platser (
    platsid integer NOT NULL,
    platsnamn character varying(25),
    ortid integer NOT NULL
);


ALTER TABLE public.platser OWNER TO joagusta;

--
-- Name: rum; Type: TABLE; Schema: public; Owner: joagusta; Tablespace: 
--

CREATE TABLE rum (
    rumsid integer NOT NULL,
    hotellid integer NOT NULL,
    rumsnummer smallint,
    rokfri boolean,
    kostnad integer
);


ALTER TABLE public.rum OWNER TO joagusta;

--
-- Name: rumsschema; Type: TABLE; Schema: public; Owner: joagusta; Tablespace: 
--

CREATE TABLE rumsschema (
    bokningsid bigint NOT NULL,
    rumsid integer NOT NULL,
    frandatum date NOT NULL,
    tilldatum date NOT NULL
);


ALTER TABLE public.rumsschema OWNER TO joagusta;

--
-- Name: stol; Type: TABLE; Schema: public; Owner: joagusta; Tablespace: 
--

CREATE TABLE stol (
    nummer smallint NOT NULL,
    flygplansid integer NOT NULL,
    typ klass,
    kostnad integer
);


ALTER TABLE public.stol OWNER TO joagusta;

--
-- Name: vacciland; Type: TABLE; Schema: public; Owner: joagusta; Tablespace: 
--

CREATE TABLE vacciland (
    landsid smallint NOT NULL,
    vaccinationsid integer NOT NULL
);


ALTER TABLE public.vacciland OWNER TO joagusta;

--
-- Name: vaccination; Type: TABLE; Schema: public; Owner: joagusta; Tablespace: 
--

CREATE TABLE vaccination (
    vaccinationsid integer NOT NULL,
    vaccbeskrivning text
);


ALTER TABLE public.vaccination OWNER TO joagusta;

--
-- Name: bokningsid; Type: DEFAULT; Schema: public; Owner: joagusta
--

ALTER TABLE bokning ALTER COLUMN bokningsid SET DEFAULT nextval('bokning_bokningsid_seq'::regclass);


--
-- Name: kontoid; Type: DEFAULT; Schema: public; Owner: joagusta
--

ALTER TABLE konto ALTER COLUMN kontoid SET DEFAULT nextval('konto_kontoid_seq'::regclass);


--
-- Data for Name: bil; Type: TABLE DATA; Schema: public; Owner: joagusta
--

COPY bil (bilid, modellid, kostnad) FROM stdin;
1	1	200
2	3	400
3	2	250
4	4	800
\.


--
-- Data for Name: bilmodell; Type: TABLE DATA; Schema: public; Owner: joagusta
--

COPY bilmodell (modellid, drift, vaxel, tillverkare, modell) FROM stdin;
1	4	A	Audi	A3
2	2	M	Saab	S95
3	4	M	Volvo	V70
4	2	A	Dodge	Viper
\.


--
-- Data for Name: bilschema; Type: TABLE DATA; Schema: public; Owner: joagusta
--

COPY bilschema (bilid, bokningsid, hamtdatum, lamningsdatum, hamtplats, lamningsplats) FROM stdin;
\.


--
-- Data for Name: bokning; Type: TABLE DATA; Schema: public; Owner: joagusta
--

COPY bokning (bokningsid, kontoid) FROM stdin;
\.


--
-- Data for Name: depot; Type: TABLE DATA; Schema: public; Owner: joagusta
--

COPY depot (depotid, ortid, adress) FROM stdin;
1	4	Sveavägen 214
2	1	Baker Street 214
3	5	Avenyn 356
\.


--
-- Data for Name: evenemang; Type: TABLE DATA; Schema: public; Owner: joagusta
--

COPY evenemang (evenemangsid, namn, startdatum, slutdatum, starttid, sluttid, beskrivning, antalplatser, platsid, kostnad) FROM stdin;
2	Pavarotti	2011-05-17	2011-05-17	16:00:00	18:45:00	Pavarotti visar prov på sin kraftfulla och unika röst i Operahuset i Stockholm	180	4	850
1	Justin Bieber	2011-04-21	2011-04-21	20:00:00	22:30:00	Justin Bieber besöker London under Nickelodeon turnén	250	2	600
\.


--
-- Data for Name: evenemangsschema; Type: TABLE DATA; Schema: public; Owner: joagusta
--

COPY evenemangsschema (evenemangsid, bokningsid, antal) FROM stdin;
\.


--
-- Data for Name: flight; Type: TABLE DATA; Schema: public; Owner: joagusta
--

COPY flight (flightid, avgangstid, ankomsttid, avgangsdatum, ankomstdatum, avgangsort, ankomstort, flygplansid) FROM stdin;
1	06:00:00	09:00:00	2011-04-21	2011-04-21	4	1	2
2	11:00:00	12:00:00	2011-04-19	2011-04-19	4	6	1
3	19:00:00	22:00:00	2011-05-17	2011-05-17	3	2	3
\.


--
-- Data for Name: flightbokning; Type: TABLE DATA; Schema: public; Owner: joagusta
--

COPY flightbokning (flightid, bokningsid, nummer) FROM stdin;
\.


--
-- Data for Name: flygplan; Type: TABLE DATA; Schema: public; Owner: joagusta
--

COPY flygplan (flygplansid, flygplanstypsid) FROM stdin;
1	2
2	3
3	2
4	1
\.


--
-- Data for Name: flygplanstyp; Type: TABLE DATA; Schema: public; Owner: joagusta
--

COPY flygplanstyp (flygplanstypsid, platser, rokfri) FROM stdin;
1	156	t
2	120	t
3	60	f
\.


--
-- Data for Name: hotell; Type: TABLE DATA; Schema: public; Owner: joagusta
--

COPY hotell (hotellid, namn, adress, ortid, stjarnor, beskrivning) FROM stdin;
2	Grand Hotel	Pickadilly Circus 546	1	5	The Grand Hotel of London
1	Rio	Olof Palmes Gata 345	4	3	Hotell med brasilianst tema
3	The Wild Goose	Hops Lane 23	3	1	Litet rustikt B&B
\.


--
-- Data for Name: konto; Type: TABLE DATA; Schema: public; Owner: joagusta
--

COPY konto (kontoid, login, password, fornamn, efternamn, postort, postnr, email, adress, telefon, tabort) FROM stdin;
\.


--
-- Data for Name: landsinfo; Type: TABLE DATA; Schema: public; Owner: joagusta
--

COPY landsinfo (landsid, land, visum, information) FROM stdin;
1	Sverige	I Sverige behöver man inget visum	Sverige är kallt och det finns inga isbjörnar där. Man använder sig av högertrafik
2	Storbrittanien	I Storbrittanien behöver man inget visum	I Storbrittanien kallar man varandra för mate. Man kör även på vänster sida av vägen
\.


--
-- Data for Name: ort; Type: TABLE DATA; Schema: public; Owner: joagusta
--

COPY ort (ortid, ort, landsid) FROM stdin;
1	London	2
2	Glasgow	2
3	Brighton	2
4	Stockholm	1
5	Göteborg	1
6	Malmö	1
\.


--
-- Data for Name: platser; Type: TABLE DATA; Schema: public; Owner: joagusta
--

COPY platser (platsid, platsnamn, ortid) FROM stdin;
1	Globen	4
2	O2 Arena	1
3	Ullevi	5
4	Operahuset	4
\.


--
-- Data for Name: rum; Type: TABLE DATA; Schema: public; Owner: joagusta
--

COPY rum (rumsid, hotellid, rumsnummer, rokfri, kostnad) FROM stdin;
1	1	1	t	234
2	1	2	t	234
3	1	3	t	234
4	2	1	t	564
5	2	2	t	564
6	2	3	t	564
7	3	1	f	117
8	3	2	f	117
9	3	3	f	117
\.


--
-- Data for Name: rumsschema; Type: TABLE DATA; Schema: public; Owner: joagusta
--

COPY rumsschema (bokningsid, rumsid, frandatum, tilldatum) FROM stdin;
\.


--
-- Data for Name: stol; Type: TABLE DATA; Schema: public; Owner: joagusta
--

COPY stol (nummer, flygplansid, typ, kostnad) FROM stdin;
1	1	E	350
2	1	E	350
3	1	E	350
4	1	E	350
5	1	E	350
6	1	E	350
7	1	E	350
8	1	E	350
9	1	E	350
10	1	E	350
11	1	E	350
12	1	E	350
13	1	E	350
14	1	E	350
15	1	E	350
16	1	E	350
17	1	E	350
18	1	E	350
19	1	E	350
20	1	E	350
21	1	E	350
22	1	E	350
23	1	E	350
24	1	E	350
25	1	E	350
26	1	E	350
27	1	E	350
28	1	E	350
29	1	E	350
30	1	E	350
31	1	E	350
32	1	E	350
33	1	E	350
34	1	E	350
35	1	E	350
36	1	E	350
37	1	E	350
38	1	E	350
39	1	E	350
40	1	E	350
41	1	E	350
42	1	E	350
43	1	E	350
44	1	E	350
45	1	E	350
46	1	E	350
47	1	E	350
48	1	E	350
49	1	E	350
50	1	E	350
51	1	E	350
52	1	E	350
53	1	E	350
54	1	E	350
55	1	E	350
56	1	E	350
57	1	E	350
58	1	E	350
59	1	E	350
60	1	E	350
61	1	E	350
62	1	E	350
63	1	E	350
64	1	E	350
65	1	E	350
66	1	E	350
67	1	E	350
68	1	E	350
69	1	E	350
70	1	E	350
71	1	E	350
72	1	E	350
73	1	E	350
74	1	E	350
75	1	E	350
76	1	E	350
77	1	E	350
78	1	E	350
79	1	E	350
80	1	E	350
81	1	E	350
82	1	E	350
83	1	E	350
84	1	E	350
85	1	E	350
86	1	E	350
87	1	E	350
88	1	E	350
89	1	E	350
90	1	E	350
91	1	E	350
92	1	E	350
93	1	E	350
94	1	E	350
95	1	E	350
96	1	E	350
97	1	E	350
98	1	E	350
99	1	E	350
100	1	E	350
101	1	E	350
102	1	E	350
103	1	E	350
104	1	E	350
105	1	E	350
106	1	E	350
107	1	E	350
108	1	E	350
109	1	E	350
110	1	E	350
111	1	E	350
112	1	E	350
113	1	E	350
114	1	E	350
115	1	E	350
116	1	E	350
117	1	E	350
118	1	E	350
119	1	E	350
120	1	E	350
1	2	E	350
2	2	E	350
3	2	E	350
4	2	E	350
5	2	E	350
6	2	E	350
7	2	E	350
8	2	E	350
9	2	E	350
10	2	E	350
11	2	E	350
12	2	E	350
13	2	E	350
14	2	E	350
15	2	E	350
16	2	E	350
17	2	E	350
18	2	E	350
19	2	E	350
20	2	E	350
21	2	E	350
22	2	E	350
23	2	E	350
24	2	E	350
25	2	E	350
26	2	E	350
27	2	E	350
28	2	E	350
29	2	E	350
30	2	B	900
31	2	B	900
32	2	B	900
33	2	B	900
34	2	B	900
35	2	B	900
36	2	B	900
37	2	B	900
38	2	B	900
39	2	B	900
40	2	B	900
41	2	B	900
42	2	B	900
43	2	B	900
44	2	B	900
45	2	B	900
46	2	B	900
47	2	B	900
48	2	B	900
49	2	B	900
50	2	B	900
51	2	B	900
52	2	B	900
53	2	B	900
54	2	B	900
55	2	B	900
56	2	B	900
57	2	B	900
58	2	B	900
59	2	B	900
60	2	B	900
1	3	F	1500
2	3	F	1500
3	3	F	1500
4	3	F	1500
5	3	F	1500
6	3	F	1500
7	3	F	1500
8	3	F	1500
9	3	F	1500
10	3	F	1500
11	3	F	1500
12	3	F	1500
13	3	F	1500
14	3	F	1500
15	3	F	1500
16	3	F	1500
17	3	F	1500
18	3	F	1500
19	3	F	1500
20	3	F	1500
21	3	F	1500
22	3	F	1500
23	3	F	1500
24	3	F	1500
25	3	F	1500
26	3	F	1500
27	3	F	1500
28	3	F	1500
29	3	F	1500
30	3	B	900
31	3	B	900
32	3	B	900
33	3	B	900
34	3	B	900
35	3	B	900
36	3	B	900
37	3	B	900
38	3	B	900
39	3	B	900
40	3	B	900
41	3	B	900
42	3	B	900
43	3	B	900
44	3	B	900
45	3	B	900
46	3	B	900
47	3	B	900
48	3	B	900
49	3	B	900
50	3	B	900
51	3	B	900
52	3	B	900
53	3	B	900
54	3	B	900
55	3	B	900
56	3	B	900
57	3	B	900
58	3	B	900
59	3	B	900
60	3	B	900
61	3	E	350
62	3	E	350
63	3	E	350
64	3	E	350
65	3	E	350
66	3	E	350
67	3	E	350
68	3	E	350
69	3	E	350
70	3	E	350
71	3	E	350
72	3	E	350
73	3	E	350
74	3	E	350
75	3	E	350
76	3	E	350
77	3	E	350
78	3	E	350
79	3	E	350
80	3	E	350
81	3	E	350
82	3	E	350
83	3	E	350
84	3	E	350
85	3	E	350
86	3	E	350
87	3	E	350
88	3	E	350
89	3	E	350
90	3	E	350
91	3	E	350
92	3	E	350
93	3	E	350
94	3	E	350
95	3	E	350
96	3	E	350
97	3	E	350
98	3	E	350
99	3	E	350
100	3	E	350
101	3	E	350
102	3	E	350
103	3	E	350
104	3	E	350
105	3	E	350
106	3	E	350
107	3	E	350
108	3	E	350
109	3	E	350
110	3	E	350
111	3	E	350
112	3	E	350
113	3	E	350
114	3	E	350
115	3	E	350
116	3	E	350
117	3	E	350
118	3	E	350
119	3	E	350
120	3	E	350
1	4	F	1500
2	4	F	1500
3	4	F	1500
4	4	F	1500
5	4	F	1500
6	4	F	1500
7	4	F	1500
8	4	F	1500
9	4	F	1500
10	4	F	1500
11	4	F	1500
12	4	F	1500
13	4	F	1500
14	4	F	1500
15	4	F	1500
16	4	F	1500
17	4	F	1500
18	4	F	1500
19	4	F	1500
20	4	F	1500
21	4	F	1500
22	4	F	1500
23	4	F	1500
24	4	F	1500
25	4	F	1500
26	4	F	1500
27	4	F	1500
28	4	F	1500
29	4	F	1500
30	4	B	900
31	4	B	900
32	4	B	900
33	4	B	900
34	4	B	900
35	4	B	900
36	4	B	900
37	4	B	900
38	4	B	900
39	4	B	900
40	4	B	900
41	4	B	900
42	4	B	900
43	4	B	900
44	4	B	900
45	4	B	900
46	4	B	900
47	4	B	900
48	4	B	900
49	4	B	900
50	4	B	900
51	4	B	900
52	4	B	900
53	4	B	900
54	4	B	900
55	4	B	900
56	4	B	900
57	4	B	900
58	4	B	900
59	4	B	900
60	4	B	900
61	4	B	900
62	4	B	900
63	4	B	900
64	4	B	900
65	4	B	900
66	4	B	900
67	4	B	900
68	4	B	900
69	4	B	900
70	4	B	900
71	4	B	900
72	4	B	900
73	4	B	900
74	4	B	900
75	4	B	900
76	4	B	900
77	4	B	900
78	4	B	900
79	4	B	900
80	4	B	900
81	4	B	900
82	4	B	900
83	4	B	900
84	4	B	900
85	4	B	900
86	4	B	900
87	4	B	900
88	4	B	900
89	4	B	900
90	4	B	900
91	4	B	900
92	4	B	900
93	4	B	900
94	4	B	900
95	4	B	900
96	4	B	900
97	4	B	900
98	4	B	900
99	4	B	900
100	4	E	350
101	4	E	350
102	4	E	350
103	4	E	350
104	4	E	350
105	4	E	350
106	4	E	350
107	4	E	350
108	4	E	350
109	4	E	350
110	4	E	350
111	4	E	350
112	4	E	350
113	4	E	350
114	4	E	350
115	4	E	350
116	4	E	350
117	4	E	350
118	4	E	350
119	4	E	350
120	4	E	350
121	4	E	350
122	4	E	350
123	4	E	350
124	4	E	350
125	4	E	350
126	4	E	350
127	4	E	350
128	4	E	350
129	4	E	350
130	4	E	350
131	4	E	350
132	4	E	350
133	4	E	350
134	4	E	350
135	4	E	350
136	4	E	350
137	4	E	350
138	4	E	350
139	4	E	350
140	4	E	350
141	4	E	350
142	4	E	350
143	4	E	350
144	4	E	350
145	4	E	350
146	4	E	350
147	4	E	350
148	4	E	350
149	4	E	350
150	4	E	350
151	4	E	350
152	4	E	350
153	4	E	350
154	4	E	350
155	4	E	350
156	4	E	350
157	4	E	350
158	4	E	350
159	4	E	350
160	4	E	350
\.


--
-- Data for Name: vacciland; Type: TABLE DATA; Schema: public; Owner: joagusta
--

COPY vacciland (landsid, vaccinationsid) FROM stdin;
1	1
1	3
2	3
2	2
\.


--
-- Data for Name: vaccination; Type: TABLE DATA; Schema: public; Owner: joagusta
--

COPY vaccination (vaccinationsid, vaccbeskrivning) FROM stdin;
1	Röda Hund
2	TBC
3	Stelkramp
\.


--
-- Name: bil_pkey; Type: CONSTRAINT; Schema: public; Owner: joagusta; Tablespace: 
--

ALTER TABLE ONLY bil
    ADD CONSTRAINT bil_pkey PRIMARY KEY (bilid);


--
-- Name: bilmodell_pkey; Type: CONSTRAINT; Schema: public; Owner: joagusta; Tablespace: 
--

ALTER TABLE ONLY bilmodell
    ADD CONSTRAINT bilmodell_pkey PRIMARY KEY (modellid);


--
-- Name: bilschema_pkey; Type: CONSTRAINT; Schema: public; Owner: joagusta; Tablespace: 
--

ALTER TABLE ONLY bilschema
    ADD CONSTRAINT bilschema_pkey PRIMARY KEY (bilid, hamtdatum, lamningsdatum);


--
-- Name: bokning_bokningsid_key; Type: CONSTRAINT; Schema: public; Owner: joagusta; Tablespace: 
--

ALTER TABLE ONLY bokning
    ADD CONSTRAINT bokning_bokningsid_key UNIQUE (bokningsid);


--
-- Name: bokning_kontoid_key; Type: CONSTRAINT; Schema: public; Owner: joagusta; Tablespace: 
--

ALTER TABLE ONLY bokning
    ADD CONSTRAINT bokning_kontoid_key UNIQUE (kontoid);


--
-- Name: bokning_pkey; Type: CONSTRAINT; Schema: public; Owner: joagusta; Tablespace: 
--

ALTER TABLE ONLY bokning
    ADD CONSTRAINT bokning_pkey PRIMARY KEY (bokningsid, kontoid);


--
-- Name: depot_pkey; Type: CONSTRAINT; Schema: public; Owner: joagusta; Tablespace: 
--

ALTER TABLE ONLY depot
    ADD CONSTRAINT depot_pkey PRIMARY KEY (depotid);


--
-- Name: evenemang_pkey; Type: CONSTRAINT; Schema: public; Owner: joagusta; Tablespace: 
--

ALTER TABLE ONLY evenemang
    ADD CONSTRAINT evenemang_pkey PRIMARY KEY (evenemangsid);


--
-- Name: evenemangsschema_pkey; Type: CONSTRAINT; Schema: public; Owner: joagusta; Tablespace: 
--

ALTER TABLE ONLY evenemangsschema
    ADD CONSTRAINT evenemangsschema_pkey PRIMARY KEY (evenemangsid, bokningsid);


--
-- Name: flight_pkey; Type: CONSTRAINT; Schema: public; Owner: joagusta; Tablespace: 
--

ALTER TABLE ONLY flight
    ADD CONSTRAINT flight_pkey PRIMARY KEY (flightid);


--
-- Name: flightbokning_pkey; Type: CONSTRAINT; Schema: public; Owner: joagusta; Tablespace: 
--

ALTER TABLE ONLY flightbokning
    ADD CONSTRAINT flightbokning_pkey PRIMARY KEY (flightid, bokningsid, nummer);


--
-- Name: flygplan_pkey; Type: CONSTRAINT; Schema: public; Owner: joagusta; Tablespace: 
--

ALTER TABLE ONLY flygplan
    ADD CONSTRAINT flygplan_pkey PRIMARY KEY (flygplansid);


--
-- Name: flygplanstyp_pkey; Type: CONSTRAINT; Schema: public; Owner: joagusta; Tablespace: 
--

ALTER TABLE ONLY flygplanstyp
    ADD CONSTRAINT flygplanstyp_pkey PRIMARY KEY (flygplanstypsid);


--
-- Name: hotell_pkey; Type: CONSTRAINT; Schema: public; Owner: joagusta; Tablespace: 
--

ALTER TABLE ONLY hotell
    ADD CONSTRAINT hotell_pkey PRIMARY KEY (hotellid);


--
-- Name: konto_login_key; Type: CONSTRAINT; Schema: public; Owner: joagusta; Tablespace: 
--

ALTER TABLE ONLY konto
    ADD CONSTRAINT konto_login_key UNIQUE (login);


--
-- Name: konto_pkey; Type: CONSTRAINT; Schema: public; Owner: joagusta; Tablespace: 
--

ALTER TABLE ONLY konto
    ADD CONSTRAINT konto_pkey PRIMARY KEY (kontoid);


--
-- Name: landsinfo_pkey; Type: CONSTRAINT; Schema: public; Owner: joagusta; Tablespace: 
--

ALTER TABLE ONLY landsinfo
    ADD CONSTRAINT landsinfo_pkey PRIMARY KEY (landsid);


--
-- Name: ort_pkey; Type: CONSTRAINT; Schema: public; Owner: joagusta; Tablespace: 
--

ALTER TABLE ONLY ort
    ADD CONSTRAINT ort_pkey PRIMARY KEY (ortid);


--
-- Name: platser_pkey; Type: CONSTRAINT; Schema: public; Owner: joagusta; Tablespace: 
--

ALTER TABLE ONLY platser
    ADD CONSTRAINT platser_pkey PRIMARY KEY (platsid);


--
-- Name: rum_pkey; Type: CONSTRAINT; Schema: public; Owner: joagusta; Tablespace: 
--

ALTER TABLE ONLY rum
    ADD CONSTRAINT rum_pkey PRIMARY KEY (rumsid);


--
-- Name: rumsschema_bokningsid_key; Type: CONSTRAINT; Schema: public; Owner: joagusta; Tablespace: 
--

ALTER TABLE ONLY rumsschema
    ADD CONSTRAINT rumsschema_bokningsid_key UNIQUE (bokningsid);


--
-- Name: rumsschema_pkey; Type: CONSTRAINT; Schema: public; Owner: joagusta; Tablespace: 
--

ALTER TABLE ONLY rumsschema
    ADD CONSTRAINT rumsschema_pkey PRIMARY KEY (rumsid, frandatum, tilldatum);


--
-- Name: stol_pkey; Type: CONSTRAINT; Schema: public; Owner: joagusta; Tablespace: 
--

ALTER TABLE ONLY stol
    ADD CONSTRAINT stol_pkey PRIMARY KEY (nummer, flygplansid);


--
-- Name: vacciland_pkey; Type: CONSTRAINT; Schema: public; Owner: joagusta; Tablespace: 
--

ALTER TABLE ONLY vacciland
    ADD CONSTRAINT vacciland_pkey PRIMARY KEY (landsid, vaccinationsid);


--
-- Name: vaccination_pkey; Type: CONSTRAINT; Schema: public; Owner: joagusta; Tablespace: 
--

ALTER TABLE ONLY vaccination
    ADD CONSTRAINT vaccination_pkey PRIMARY KEY (vaccinationsid);


--
-- Name: bil_modellid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: joagusta
--

ALTER TABLE ONLY bil
    ADD CONSTRAINT bil_modellid_fkey FOREIGN KEY (modellid) REFERENCES bilmodell(modellid);


--
-- Name: bilschema_bilid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: joagusta
--

ALTER TABLE ONLY bilschema
    ADD CONSTRAINT bilschema_bilid_fkey FOREIGN KEY (bilid) REFERENCES bil(bilid);


--
-- Name: bilschema_bokningsid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: joagusta
--

ALTER TABLE ONLY bilschema
    ADD CONSTRAINT bilschema_bokningsid_fkey FOREIGN KEY (bokningsid) REFERENCES bokning(bokningsid);


--
-- Name: bilschema_hamtplats_fkey; Type: FK CONSTRAINT; Schema: public; Owner: joagusta
--

ALTER TABLE ONLY bilschema
    ADD CONSTRAINT bilschema_hamtplats_fkey FOREIGN KEY (hamtplats) REFERENCES depot(depotid);


--
-- Name: bilschema_lamningsplats_fkey; Type: FK CONSTRAINT; Schema: public; Owner: joagusta
--

ALTER TABLE ONLY bilschema
    ADD CONSTRAINT bilschema_lamningsplats_fkey FOREIGN KEY (lamningsplats) REFERENCES depot(depotid);


--
-- Name: bokning_kontoid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: joagusta
--

ALTER TABLE ONLY bokning
    ADD CONSTRAINT bokning_kontoid_fkey FOREIGN KEY (kontoid) REFERENCES konto(kontoid);


--
-- Name: depot_ortid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: joagusta
--

ALTER TABLE ONLY depot
    ADD CONSTRAINT depot_ortid_fkey FOREIGN KEY (ortid) REFERENCES ort(ortid);


--
-- Name: evenemang_platsid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: joagusta
--

ALTER TABLE ONLY evenemang
    ADD CONSTRAINT evenemang_platsid_fkey FOREIGN KEY (platsid) REFERENCES platser(platsid);


--
-- Name: evenemangsschema_bokningsid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: joagusta
--

ALTER TABLE ONLY evenemangsschema
    ADD CONSTRAINT evenemangsschema_bokningsid_fkey FOREIGN KEY (bokningsid) REFERENCES bokning(bokningsid);


--
-- Name: evenemangsschema_evenemangsid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: joagusta
--

ALTER TABLE ONLY evenemangsschema
    ADD CONSTRAINT evenemangsschema_evenemangsid_fkey FOREIGN KEY (evenemangsid) REFERENCES evenemang(evenemangsid);


--
-- Name: flight_ankomstort_fkey; Type: FK CONSTRAINT; Schema: public; Owner: joagusta
--

ALTER TABLE ONLY flight
    ADD CONSTRAINT flight_ankomstort_fkey FOREIGN KEY (ankomstort) REFERENCES ort(ortid);


--
-- Name: flight_avgangsort_fkey; Type: FK CONSTRAINT; Schema: public; Owner: joagusta
--

ALTER TABLE ONLY flight
    ADD CONSTRAINT flight_avgangsort_fkey FOREIGN KEY (avgangsort) REFERENCES ort(ortid);


--
-- Name: flight_flygplansid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: joagusta
--

ALTER TABLE ONLY flight
    ADD CONSTRAINT flight_flygplansid_fkey FOREIGN KEY (flygplansid) REFERENCES flygplan(flygplansid);


--
-- Name: flightbokning_bokningsid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: joagusta
--

ALTER TABLE ONLY flightbokning
    ADD CONSTRAINT flightbokning_bokningsid_fkey FOREIGN KEY (bokningsid) REFERENCES bokning(bokningsid);


--
-- Name: flightbokning_flightid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: joagusta
--

ALTER TABLE ONLY flightbokning
    ADD CONSTRAINT flightbokning_flightid_fkey FOREIGN KEY (flightid) REFERENCES flight(flightid);


--
-- Name: flygplan_flygplanstypsid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: joagusta
--

ALTER TABLE ONLY flygplan
    ADD CONSTRAINT flygplan_flygplanstypsid_fkey FOREIGN KEY (flygplanstypsid) REFERENCES flygplanstyp(flygplanstypsid);


--
-- Name: hotell_ortid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: joagusta
--

ALTER TABLE ONLY hotell
    ADD CONSTRAINT hotell_ortid_fkey FOREIGN KEY (ortid) REFERENCES ort(ortid);


--
-- Name: ort_landsid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: joagusta
--

ALTER TABLE ONLY ort
    ADD CONSTRAINT ort_landsid_fkey FOREIGN KEY (landsid) REFERENCES landsinfo(landsid);


--
-- Name: platser_ortid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: joagusta
--

ALTER TABLE ONLY platser
    ADD CONSTRAINT platser_ortid_fkey FOREIGN KEY (ortid) REFERENCES ort(ortid);


--
-- Name: rum_hotellid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: joagusta
--

ALTER TABLE ONLY rum
    ADD CONSTRAINT rum_hotellid_fkey FOREIGN KEY (hotellid) REFERENCES hotell(hotellid);


--
-- Name: rumsschema_bokningsid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: joagusta
--

ALTER TABLE ONLY rumsschema
    ADD CONSTRAINT rumsschema_bokningsid_fkey FOREIGN KEY (bokningsid) REFERENCES bokning(bokningsid);


--
-- Name: rumsschema_rumsid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: joagusta
--

ALTER TABLE ONLY rumsschema
    ADD CONSTRAINT rumsschema_rumsid_fkey FOREIGN KEY (rumsid) REFERENCES rum(rumsid);


--
-- Name: stol_flygplansid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: joagusta
--

ALTER TABLE ONLY stol
    ADD CONSTRAINT stol_flygplansid_fkey FOREIGN KEY (flygplansid) REFERENCES flygplan(flygplansid);


--
-- Name: vacciland_landsid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: joagusta
--

ALTER TABLE ONLY vacciland
    ADD CONSTRAINT vacciland_landsid_fkey FOREIGN KEY (landsid) REFERENCES landsinfo(landsid);


--
-- Name: vacciland_vaccinationsid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: joagusta
--

ALTER TABLE ONLY vacciland
    ADD CONSTRAINT vacciland_vaccinationsid_fkey FOREIGN KEY (vaccinationsid) REFERENCES vaccination(vaccinationsid);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

