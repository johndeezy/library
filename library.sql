--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: addresses; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE addresses (
    street character varying,
    zipcode character varying,
    state character varying,
    owner integer,
    id integer NOT NULL
);


ALTER TABLE addresses OWNER TO "Guest";

--
-- Name: addresses_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE addresses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE addresses_id_seq OWNER TO "Guest";

--
-- Name: addresses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE addresses_id_seq OWNED BY addresses.id;


--
-- Name: authors; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE authors (
    id integer NOT NULL,
    first_name character varying,
    last_name character varying
);


ALTER TABLE authors OWNER TO "Guest";

--
-- Name: authors_books; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE authors_books (
    id integer NOT NULL,
    author_id integer,
    book_id integer
);


ALTER TABLE authors_books OWNER TO "Guest";

--
-- Name: authors_books_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE authors_books_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE authors_books_id_seq OWNER TO "Guest";

--
-- Name: authors_books_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE authors_books_id_seq OWNED BY authors_books.id;


--
-- Name: authors_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE authors_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE authors_id_seq OWNER TO "Guest";

--
-- Name: authors_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE authors_id_seq OWNED BY authors.id;


--
-- Name: books; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE books (
    id integer NOT NULL,
    title character varying
);


ALTER TABLE books OWNER TO "Guest";

--
-- Name: books_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE books_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE books_id_seq OWNER TO "Guest";

--
-- Name: books_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE books_id_seq OWNED BY books.id;


--
-- Name: checkouts; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE checkouts (
    id integer NOT NULL,
    patron_id integer,
    copy_id integer
);


ALTER TABLE checkouts OWNER TO "Guest";

--
-- Name: checkouts_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE checkouts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE checkouts_id_seq OWNER TO "Guest";

--
-- Name: checkouts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE checkouts_id_seq OWNED BY checkouts.id;


--
-- Name: connections; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE connections (
    connection character varying,
    owner integer
);


ALTER TABLE connections OWNER TO "Guest";

--
-- Name: contacts; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE contacts (
    name character varying,
    email character varying,
    address character varying,
    id integer NOT NULL,
    phone character varying
);


ALTER TABLE contacts OWNER TO "Guest";

--
-- Name: contacts_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE contacts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE contacts_id_seq OWNER TO "Guest";

--
-- Name: contacts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE contacts_id_seq OWNED BY contacts.id;


--
-- Name: copies; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE copies (
    id integer NOT NULL,
    book_id integer,
    checked boolean,
    due_date character varying
);


ALTER TABLE copies OWNER TO "Guest";

--
-- Name: copies_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE copies_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE copies_id_seq OWNER TO "Guest";

--
-- Name: copies_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE copies_id_seq OWNED BY copies.id;


--
-- Name: emails; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE emails (
    email character varying,
    owner integer,
    id integer NOT NULL
);


ALTER TABLE emails OWNER TO "Guest";

--
-- Name: emails_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE emails_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE emails_id_seq OWNER TO "Guest";

--
-- Name: emails_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE emails_id_seq OWNED BY emails.id;


--
-- Name: patrons; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE patrons (
    id integer NOT NULL,
    first_name character varying,
    last_name character varying
);


ALTER TABLE patrons OWNER TO "Guest";

--
-- Name: patrons_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE patrons_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE patrons_id_seq OWNER TO "Guest";

--
-- Name: patrons_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE patrons_id_seq OWNED BY patrons.id;


--
-- Name: phones; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE phones (
    owner integer,
    id integer NOT NULL,
    number character varying
);


ALTER TABLE phones OWNER TO "Guest";

--
-- Name: phones_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE phones_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE phones_id_seq OWNER TO "Guest";

--
-- Name: phones_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE phones_id_seq OWNED BY phones.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY addresses ALTER COLUMN id SET DEFAULT nextval('addresses_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY authors ALTER COLUMN id SET DEFAULT nextval('authors_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY authors_books ALTER COLUMN id SET DEFAULT nextval('authors_books_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY books ALTER COLUMN id SET DEFAULT nextval('books_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY checkouts ALTER COLUMN id SET DEFAULT nextval('checkouts_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY contacts ALTER COLUMN id SET DEFAULT nextval('contacts_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY copies ALTER COLUMN id SET DEFAULT nextval('copies_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY emails ALTER COLUMN id SET DEFAULT nextval('emails_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY patrons ALTER COLUMN id SET DEFAULT nextval('patrons_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY phones ALTER COLUMN id SET DEFAULT nextval('phones_id_seq'::regclass);


--
-- Data for Name: addresses; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY addresses (street, zipcode, state, owner, id) FROM stdin;
123 sw something	97223	or	1	1
456 sw something	97223	or	2	2
456 sw somethingelse	97223	or	3	3
\.


--
-- Name: addresses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('addresses_id_seq', 3, true);


--
-- Data for Name: authors; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY authors (id, first_name, last_name) FROM stdin;
1	Anony	Mous
2	Tommy	Gunn
3	Fydroe	Dostoyevky
4	lol	no
5	lol	no
6	Fydroe	Dostoyevky
\.


--
-- Data for Name: authors_books; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY authors_books (id, author_id, book_id) FROM stdin;
7	6	4
\.


--
-- Name: authors_books_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('authors_books_id_seq', 7, true);


--
-- Name: authors_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('authors_id_seq', 6, true);


--
-- Data for Name: books; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY books (id, title) FROM stdin;
4	The Brothers Karamzmoozgh
\.


--
-- Name: books_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('books_id_seq', 4, true);


--
-- Data for Name: checkouts; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY checkouts (id, patron_id, copy_id) FROM stdin;
\.


--
-- Name: checkouts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('checkouts_id_seq', 1, false);


--
-- Data for Name: connections; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY connections (connection, owner) FROM stdin;
family	1
school	3
business	2
work	3
\.


--
-- Data for Name: contacts; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY contacts (name, email, address, id, phone) FROM stdin;
Sadio	sadiali@pdx.edu	123 sw something beaverton or 97223	1	9715551222
Jane	sadiali@somethingelse.edu	456 sw something beaverton or 97223	2	5035551222
John	sadiali@ohsu.edu	456 sw somethingelse beaverton or 97223	3	5035559999
\.


--
-- Name: contacts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('contacts_id_seq', 3, true);


--
-- Data for Name: copies; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY copies (id, book_id, checked, due_date) FROM stdin;
\.


--
-- Name: copies_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('copies_id_seq', 1, false);


--
-- Data for Name: emails; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY emails (email, owner, id) FROM stdin;
sadiali@pdx.edu	1	1
sadiali@somethingelse.edu	2	2
sadiali@ohsu.edu	3	3
\.


--
-- Name: emails_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('emails_id_seq', 3, true);


--
-- Data for Name: patrons; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY patrons (id, first_name, last_name) FROM stdin;
\.


--
-- Name: patrons_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('patrons_id_seq', 1, false);


--
-- Data for Name: phones; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY phones (owner, id, number) FROM stdin;
1	1	9715551222
2	2	5035551222
3	3	5035559999
\.


--
-- Name: phones_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('phones_id_seq', 3, true);


--
-- Name: addresses_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY addresses
    ADD CONSTRAINT addresses_pkey PRIMARY KEY (id);


--
-- Name: authors_books_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY authors_books
    ADD CONSTRAINT authors_books_pkey PRIMARY KEY (id);


--
-- Name: authors_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY authors
    ADD CONSTRAINT authors_pkey PRIMARY KEY (id);


--
-- Name: books_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY books
    ADD CONSTRAINT books_pkey PRIMARY KEY (id);


--
-- Name: checkouts_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY checkouts
    ADD CONSTRAINT checkouts_pkey PRIMARY KEY (id);


--
-- Name: contacts_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY contacts
    ADD CONSTRAINT contacts_pkey PRIMARY KEY (id);


--
-- Name: copies_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY copies
    ADD CONSTRAINT copies_pkey PRIMARY KEY (id);


--
-- Name: emails_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY emails
    ADD CONSTRAINT emails_pkey PRIMARY KEY (id);


--
-- Name: patrons_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY patrons
    ADD CONSTRAINT patrons_pkey PRIMARY KEY (id);


--
-- Name: phones_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY phones
    ADD CONSTRAINT phones_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

