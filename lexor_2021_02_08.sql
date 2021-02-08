--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

-- Started on 2021-02-08 07:06:38

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
-- TOC entry 201 (class 1259 OID 25295)
-- Name: case; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."case" (
    caseid integer NOT NULL,
    customerid integer NOT NULL,
    salonid integer NOT NULL,
    casename text,
    casepriority integer,
    casetype integer NOT NULL,
    status integer NOT NULL,
    customerservicerep integer NOT NULL,
    createddate timestamp without time zone,
    updateddate timestamp without time zone
);


ALTER TABLE public."case" OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 25293)
-- Name: case_caseid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.case_caseid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.case_caseid_seq OWNER TO postgres;

--
-- TOC entry 3182 (class 0 OID 0)
-- Dependencies: 200
-- Name: case_caseid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.case_caseid_seq OWNED BY public."case".caseid;


--
-- TOC entry 205 (class 1259 OID 25322)
-- Name: caseinformation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.caseinformation (
    transactionid integer NOT NULL,
    caseid integer NOT NULL,
    doccode text,
    status integer,
    address text,
    createddate timestamp without time zone
);


ALTER TABLE public.caseinformation OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 25320)
-- Name: caseinformation_transactionid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.caseinformation_transactionid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.caseinformation_transactionid_seq OWNER TO postgres;

--
-- TOC entry 3183 (class 0 OID 0)
-- Dependencies: 204
-- Name: caseinformation_transactionid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.caseinformation_transactionid_seq OWNED BY public.caseinformation.transactionid;


--
-- TOC entry 207 (class 1259 OID 25338)
-- Name: caselog; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.caselog (
    logid integer NOT NULL,
    caseid integer NOT NULL,
    logmessage text,
    createddate timestamp without time zone NOT NULL
);


ALTER TABLE public.caselog OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 25336)
-- Name: caselog_logid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.caselog_logid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.caselog_logid_seq OWNER TO postgres;

--
-- TOC entry 3184 (class 0 OID 0)
-- Dependencies: 206
-- Name: caselog_logid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.caselog_logid_seq OWNED BY public.caselog.logid;


--
-- TOC entry 203 (class 1259 OID 25306)
-- Name: casemessage; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.casemessage (
    messageid integer NOT NULL,
    caseid integer NOT NULL,
    sendto text,
    subject text,
    messagebody text,
    createddate timestamp without time zone,
    updateddate timestamp without time zone
);


ALTER TABLE public.casemessage OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 25304)
-- Name: casemessage_messageid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.casemessage_messageid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.casemessage_messageid_seq OWNER TO postgres;

--
-- TOC entry 3185 (class 0 OID 0)
-- Dependencies: 202
-- Name: casemessage_messageid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.casemessage_messageid_seq OWNED BY public.casemessage.messageid;


--
-- TOC entry 213 (class 1259 OID 25383)
-- Name: casereturn; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.casereturn (
    casereturnid integer NOT NULL,
    caseid integer NOT NULL,
    customersoid integer NOT NULL,
    createddate timestamp without time zone,
    updateddate timestamp without time zone
);


ALTER TABLE public.casereturn OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 25381)
-- Name: casereturn_casereturnid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.casereturn_casereturnid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.casereturn_casereturnid_seq OWNER TO postgres;

--
-- TOC entry 3186 (class 0 OID 0)
-- Dependencies: 212
-- Name: casereturn_casereturnid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.casereturn_casereturnid_seq OWNED BY public.casereturn.casereturnid;


--
-- TOC entry 209 (class 1259 OID 25354)
-- Name: caseservice; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.caseservice (
    caseserviceid integer NOT NULL,
    caseid integer NOT NULL,
    customersoid integer NOT NULL,
    logmessage text,
    createddate timestamp without time zone,
    updateddate timestamp without time zone
);


ALTER TABLE public.caseservice OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 25352)
-- Name: caseservice_caseserviceid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.caseservice_caseserviceid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.caseservice_caseserviceid_seq OWNER TO postgres;

--
-- TOC entry 3187 (class 0 OID 0)
-- Dependencies: 208
-- Name: caseservice_caseserviceid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.caseservice_caseserviceid_seq OWNED BY public.caseservice.caseserviceid;


--
-- TOC entry 211 (class 1259 OID 25370)
-- Name: caseserviceso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.caseserviceso (
    caseservicedetailid integer NOT NULL,
    caseserviceid integer NOT NULL,
    customersoid integer NOT NULL,
    createddate timestamp without time zone,
    updateddate timestamp without time zone
);


ALTER TABLE public.caseserviceso OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 25368)
-- Name: caseserviceso_caseservicedetailid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.caseserviceso_caseservicedetailid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.caseserviceso_caseservicedetailid_seq OWNER TO postgres;

--
-- TOC entry 3188 (class 0 OID 0)
-- Dependencies: 210
-- Name: caseserviceso_caseservicedetailid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.caseserviceso_caseservicedetailid_seq OWNED BY public.caseserviceso.caseservicedetailid;


--
-- TOC entry 227 (class 1259 OID 25726)
-- Name: casetype; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.casetype (
    casetypeid integer NOT NULL,
    caseid integer NOT NULL,
    casetypevalue text
);


ALTER TABLE public.casetype OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 25724)
-- Name: casetype_casetypeid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.casetype_casetypeid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.casetype_casetypeid_seq OWNER TO postgres;

--
-- TOC entry 3189 (class 0 OID 0)
-- Dependencies: 226
-- Name: casetype_casetypeid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.casetype_casetypeid_seq OWNED BY public.casetype.casetypeid;


--
-- TOC entry 215 (class 1259 OID 25396)
-- Name: rma; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rma (
    rmaid integer NOT NULL,
    caseid integer NOT NULL,
    customersoid integer NOT NULL,
    status text,
    createddate timestamp without time zone,
    updateddate timestamp without time zone
);


ALTER TABLE public.rma OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 25394)
-- Name: rma_rmaid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rma_rmaid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rma_rmaid_seq OWNER TO postgres;

--
-- TOC entry 3190 (class 0 OID 0)
-- Dependencies: 214
-- Name: rma_rmaid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.rma_rmaid_seq OWNED BY public.rma.rmaid;


--
-- TOC entry 221 (class 1259 OID 25681)
-- Name: rmalog; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rmalog (
    rmaid integer NOT NULL,
    logid integer NOT NULL,
    logmessage text,
    createddate timestamp without time zone
);


ALTER TABLE public.rmalog OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 25679)
-- Name: rmalog_logid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rmalog_logid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rmalog_logid_seq OWNER TO postgres;

--
-- TOC entry 3191 (class 0 OID 0)
-- Dependencies: 220
-- Name: rmalog_logid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.rmalog_logid_seq OWNED BY public.rmalog.logid;


--
-- TOC entry 219 (class 1259 OID 25665)
-- Name: rmamessage; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rmamessage (
    rmaid integer NOT NULL,
    messageid integer NOT NULL,
    sendto text,
    subject text,
    messagebody text,
    createddate timestamp without time zone
);


ALTER TABLE public.rmamessage OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 25663)
-- Name: rmamessage_messageid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rmamessage_messageid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rmamessage_messageid_seq OWNER TO postgres;

--
-- TOC entry 3192 (class 0 OID 0)
-- Dependencies: 218
-- Name: rmamessage_messageid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.rmamessage_messageid_seq OWNED BY public.rmamessage.messageid;


--
-- TOC entry 217 (class 1259 OID 25649)
-- Name: rmapayment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rmapayment (
    rmaid integer NOT NULL,
    paymentid integer NOT NULL,
    paymenttype text NOT NULL,
    paymentamount double precision NOT NULL,
    paymentstatus integer NOT NULL
);


ALTER TABLE public.rmapayment OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 25647)
-- Name: rmapayment_paymentid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rmapayment_paymentid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rmapayment_paymentid_seq OWNER TO postgres;

--
-- TOC entry 3193 (class 0 OID 0)
-- Dependencies: 216
-- Name: rmapayment_paymentid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.rmapayment_paymentid_seq OWNED BY public.rmapayment.paymentid;


--
-- TOC entry 228 (class 1259 OID 42165)
-- Name: rmaso_soid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rmaso_soid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rmaso_soid_seq OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 42167)
-- Name: rmaso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rmaso (
    rmasoid integer DEFAULT nextval('public.rmaso_soid_seq'::regclass) NOT NULL,
    soid integer NOT NULL,
    rmaid integer NOT NULL,
    fee double precision NOT NULL,
    total double precision NOT NULL,
    createddate timestamp without time zone,
    updateddate timestamp without time zone
);


ALTER TABLE public.rmaso OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 42181)
-- Name: rmaso_detail_sodetail_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rmaso_detail_sodetail_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rmaso_detail_sodetail_id_seq OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 42183)
-- Name: rmaso_detail; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rmaso_detail (
    rmaid integer NOT NULL,
    rmasoid integer NOT NULL,
    sodetail_id integer DEFAULT nextval('public.rmaso_detail_sodetail_id_seq'::regclass) NOT NULL,
    productid integer NOT NULL,
    quantity integer NOT NULL,
    price double precision NOT NULL,
    createddate timestamp without time zone,
    updateddate timestamp without time zone
);


ALTER TABLE public.rmaso_detail OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 25710)
-- Name: servicedetail; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.servicedetail (
    servicedetailid integer NOT NULL,
    servicemasterid integer NOT NULL,
    productid integer,
    quantity integer,
    soldprice double precision,
    amount double precision,
    totalweight double precision,
    serialnumber text,
    iswarrantly integer,
    warrantystartdate timestamp without time zone,
    warrantyenddate timestamp without time zone,
    paymenttype integer,
    logmessage text,
    createddate timestamp without time zone,
    warehouse integer
);


ALTER TABLE public.servicedetail OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 25708)
-- Name: servicedetail_servicedetailid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.servicedetail_servicedetailid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.servicedetail_servicedetailid_seq OWNER TO postgres;

--
-- TOC entry 3194 (class 0 OID 0)
-- Dependencies: 224
-- Name: servicedetail_servicedetailid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.servicedetail_servicedetailid_seq OWNED BY public.servicedetail.servicedetailid;


--
-- TOC entry 223 (class 1259 OID 25697)
-- Name: servicemaster; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.servicemaster (
    servicemasterid integer NOT NULL,
    caseserviceid integer,
    subtotal double precision,
    shippingfee double precision,
    managerdiscount double precision,
    total double precision,
    createddate timestamp without time zone,
    issubmittedproduction integer,
    status integer DEFAULT 0 NOT NULL,
    warehouse integer
);


ALTER TABLE public.servicemaster OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 25695)
-- Name: servicemaster_servicemasterid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.servicemaster_servicemasterid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.servicemaster_servicemasterid_seq OWNER TO postgres;

--
-- TOC entry 3195 (class 0 OID 0)
-- Dependencies: 222
-- Name: servicemaster_servicemasterid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.servicemaster_servicemasterid_seq OWNED BY public.servicemaster.servicemasterid;


--
-- TOC entry 2951 (class 2604 OID 25298)
-- Name: case caseid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."case" ALTER COLUMN caseid SET DEFAULT nextval('public.case_caseid_seq'::regclass);


--
-- TOC entry 2953 (class 2604 OID 25325)
-- Name: caseinformation transactionid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.caseinformation ALTER COLUMN transactionid SET DEFAULT nextval('public.caseinformation_transactionid_seq'::regclass);


--
-- TOC entry 2954 (class 2604 OID 25341)
-- Name: caselog logid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.caselog ALTER COLUMN logid SET DEFAULT nextval('public.caselog_logid_seq'::regclass);


--
-- TOC entry 2952 (class 2604 OID 25309)
-- Name: casemessage messageid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.casemessage ALTER COLUMN messageid SET DEFAULT nextval('public.casemessage_messageid_seq'::regclass);


--
-- TOC entry 2957 (class 2604 OID 25386)
-- Name: casereturn casereturnid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.casereturn ALTER COLUMN casereturnid SET DEFAULT nextval('public.casereturn_casereturnid_seq'::regclass);


--
-- TOC entry 2955 (class 2604 OID 25357)
-- Name: caseservice caseserviceid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.caseservice ALTER COLUMN caseserviceid SET DEFAULT nextval('public.caseservice_caseserviceid_seq'::regclass);


--
-- TOC entry 2956 (class 2604 OID 25373)
-- Name: caseserviceso caseservicedetailid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.caseserviceso ALTER COLUMN caseservicedetailid SET DEFAULT nextval('public.caseserviceso_caseservicedetailid_seq'::regclass);


--
-- TOC entry 2965 (class 2604 OID 25729)
-- Name: casetype casetypeid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.casetype ALTER COLUMN casetypeid SET DEFAULT nextval('public.casetype_casetypeid_seq'::regclass);


--
-- TOC entry 2958 (class 2604 OID 25399)
-- Name: rma rmaid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rma ALTER COLUMN rmaid SET DEFAULT nextval('public.rma_rmaid_seq'::regclass);


--
-- TOC entry 2961 (class 2604 OID 25684)
-- Name: rmalog logid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rmalog ALTER COLUMN logid SET DEFAULT nextval('public.rmalog_logid_seq'::regclass);


--
-- TOC entry 2960 (class 2604 OID 25668)
-- Name: rmamessage messageid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rmamessage ALTER COLUMN messageid SET DEFAULT nextval('public.rmamessage_messageid_seq'::regclass);


--
-- TOC entry 2959 (class 2604 OID 25652)
-- Name: rmapayment paymentid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rmapayment ALTER COLUMN paymentid SET DEFAULT nextval('public.rmapayment_paymentid_seq'::regclass);


--
-- TOC entry 2964 (class 2604 OID 25713)
-- Name: servicedetail servicedetailid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.servicedetail ALTER COLUMN servicedetailid SET DEFAULT nextval('public.servicedetail_servicedetailid_seq'::regclass);


--
-- TOC entry 2962 (class 2604 OID 25700)
-- Name: servicemaster servicemasterid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.servicemaster ALTER COLUMN servicemasterid SET DEFAULT nextval('public.servicemaster_servicemasterid_seq'::regclass);


--
-- TOC entry 3146 (class 0 OID 25295)
-- Dependencies: 201
-- Data for Name: case; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."case" (caseid, customerid, salonid, casename, casepriority, casetype, status, customerservicerep, createddate, updateddate) FROM stdin;
10	91059	0	\N	1	1	2	2	\N	\N
8	55972	0	\N	1	1	2	1	\N	\N
7	80117	0	\N	1	1	2	1	\N	\N
6	25910	0	\N	1	1	2	1	\N	\N
5	18852	0	\N	1	1	2	1	\N	\N
4	8776	0	\N	1	1	2	1	\N	\N
3	61351	0	\N	1	1	2	1	\N	\N
9	77372	0	\N	2	1	2	5	\N	\N
11	1	0	\N	1	1	1	0	\N	\N
12	1	0	\N	1	1	1	0	\N	\N
13	1	0	\N	1	1	1	0	\N	\N
16	77978	0	\N	2	1	1	3	\N	\N
14	1	0	\N	1	1	1	0	\N	\N
15	1	0	\N	1	1	1	0	\N	\N
\.


--
-- TOC entry 3150 (class 0 OID 25322)
-- Dependencies: 205
-- Data for Name: caseinformation; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.caseinformation (transactionid, caseid, doccode, status, address, createddate) FROM stdin;
1	10	Service	1	Address	2021-01-30 00:00:00
2	10	Service	1	Address	2021-01-30 00:00:00
3	10	Service	1	Address	2021-01-30 00:00:00
4	9	Service	1	Address	2021-01-30 00:00:00
5	9	Service	1	Address	2021-01-30 00:00:00
6	9	Service	1	Address	2021-01-30 00:00:00
7	9	Service	1	Address	2021-01-30 00:00:00
8	9	Service	1	Address	2021-01-30 00:00:00
9	10	Service	1	Address	2021-01-30 00:00:00
10	9	Service	1	Address	2021-01-31 00:00:00
11	9	Return	1	Address	2021-01-31 00:00:00
12	9	Return	1	Address	2021-01-31 00:00:00
13	9	Return	1	Address	2021-01-31 00:00:00
14	9	Return	1	Address	2021-01-31 00:00:00
15	9	Return	1	Address	2021-01-31 00:00:00
16	9	Return	1	Address	2021-01-31 00:00:00
17	9	Return	1	Address	2021-01-31 00:00:00
18	9	Return	1	Address	2021-01-31 00:00:00
19	9	Return	1	Address	2021-01-31 00:00:00
20	9	Return	1	Address	2021-01-31 00:00:00
21	9	Return	1	Address	2021-01-31 00:00:00
22	9	Return	1	Address	2021-01-31 00:00:00
23	9	Return	1	Address	2021-01-31 00:00:00
24	9	Return	1	Address	2021-01-31 00:00:00
25	9	Return	1	Address	2021-01-31 00:00:00
26	9	Return	1	Address	2021-01-31 00:00:00
27	9	Return	1	Address	2021-01-31 00:00:00
28	9	Return	1	Address	2021-01-31 00:00:00
29	9	Return	1	Address	2021-01-31 00:00:00
30	9	Return	1	Address	2021-01-31 00:00:00
31	9	Return	1	Address	2021-01-31 00:00:00
32	9	Return	1	Address	2021-01-31 00:00:00
33	9	Return	1	Address	2021-01-31 00:00:00
34	9	Return	1	Address	2021-01-31 00:00:00
35	9	Return	1	Address	2021-01-31 00:00:00
36	9	Return	1	Address	2021-01-31 00:00:00
37	9	Return	1	Address	2021-01-31 00:00:00
38	9	Return	1	Address	2021-01-31 00:00:00
39	9	Return	1	Address	2021-01-31 00:00:00
40	9	Return	1	Address	2021-01-31 00:00:00
41	9	Return	1	Address	2021-01-31 00:00:00
42	9	Return	1	Address	2021-01-31 00:00:00
43	9	Return	1	Address	2021-01-31 00:00:00
44	9	Return	1	Address	2021-01-31 00:00:00
45	9	Return	1	Address	2021-01-31 00:00:00
46	9	Return	1	Address	2021-01-31 00:00:00
47	9	Return	1	Address	2021-01-31 00:00:00
48	9	Return	1	Address	2021-01-31 00:00:00
49	9	Return	1	Address	2021-01-31 00:00:00
50	11	Service	1	Address	2021-01-31 00:00:00
51	11	Service	1	Address	2021-01-31 00:00:00
52	11	Service	1	Address	2021-01-31 00:00:00
53	11	Service	1	Address	2021-01-31 00:00:00
54	13	Service	1	Address	2021-02-02 00:00:00
55	13	Service	1	Address	2021-02-02 00:00:00
56	13	Service	1	Address	2021-02-02 00:00:00
57	13	Service	1	Address	2021-02-02 00:00:00
58	13	Return	1	Address	2021-02-02 00:00:00
59	13	Return	1	Address	2021-02-02 00:00:00
60	13	Service	1	Address	2021-02-02 00:00:00
61	13	Return	1	Address	2021-02-02 00:00:00
62	13	Service	1	Address	2021-02-02 00:00:00
63	13	Service	1	Address	2021-02-02 00:00:00
64	13	Service	1	Address	2021-02-02 00:00:00
65	13	Service	1	Address	2021-02-02 00:00:00
66	13	Service	1	Address	2021-02-02 00:00:00
67	13	Service	1	Address	2021-02-02 00:00:00
68	13	Service	1	Address	2021-02-02 00:00:00
69	13	Service	1	Address	2021-02-02 00:00:00
70	13	Service	1	Address	2021-02-02 00:00:00
71	13	Service	1	Address	2021-02-02 00:00:00
72	13	Service	1	Address	2021-02-02 00:00:00
73	13	Service	1	Address	2021-02-02 00:00:00
74	13	Service	1	Address	2021-02-02 00:00:00
75	13	Service	1	Address	2021-02-02 00:00:00
76	13	Service	1	Address	2021-02-02 00:00:00
77	13	Service	1	Address	2021-02-02 00:00:00
78	13	Service	1	Address	2021-02-02 00:00:00
79	13	Service	1	Address	2021-02-02 00:00:00
80	13	Service	1	Address	2021-02-02 00:00:00
81	13	Service	1	Address	2021-02-02 00:00:00
82	13	Service	1	Address	2021-02-02 00:00:00
83	13	Service	1	Address	2021-02-02 00:00:00
84	13	Service	1	Address	2021-02-02 00:00:00
85	13	Service	1	Address	2021-02-02 00:00:00
86	14	Return	1	Address	2021-02-07 00:00:00
87	14	Return	1	Address	2021-02-07 00:00:00
88	14	Return	1	Address	2021-02-07 00:00:00
89	14	Return	1	Address	2021-02-07 00:00:00
90	14	Return	1	Address	2021-02-07 00:00:00
91	14	Return	1	Address	2021-02-07 00:00:00
92	14	Return	1	Address	2021-02-07 00:00:00
93	14	Return	1	Address	2021-02-07 00:00:00
94	14	Return	1	Address	2021-02-07 00:00:00
95	14	Return	1	Address	2021-02-07 00:00:00
96	16	Service	1	Address	2021-02-08 00:00:00
97	16	Return	1	Address	2021-02-08 00:00:00
\.


--
-- TOC entry 3152 (class 0 OID 25338)
-- Dependencies: 207
-- Data for Name: caselog; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.caselog (logid, caseid, logmessage, createddate) FROM stdin;
1	10	Create Service	2021-01-30 00:00:00
2	10	Create Service	2021-01-30 00:00:00
3	10	Create Service	2021-01-30 00:00:00
4	9	Create Service	2021-01-30 00:00:00
5	9	Create Service	2021-01-30 00:00:00
6	9	Create Service	2021-01-30 00:00:00
7	9	Create Service	2021-01-30 00:00:00
8	9	Create Service	2021-01-30 00:00:00
9	10	Create Service	2021-01-30 00:00:00
10	9	Create Service	2021-01-31 00:00:00
11	9	Create Return	2021-01-31 00:00:00
12	9	Create Return	2021-01-31 00:00:00
13	9	Create Return	2021-01-31 00:00:00
14	9	Create Return	2021-01-31 00:00:00
15	9	Create Return	2021-01-31 00:00:00
16	9	Create Return	2021-01-31 00:00:00
17	9	Create Return	2021-01-31 00:00:00
18	9	Create Return	2021-01-31 00:00:00
19	9	Create Return	2021-01-31 00:00:00
20	9	Create Return	2021-01-31 00:00:00
21	9	Create Return	2021-01-31 00:00:00
22	9	Create Return	2021-01-31 00:00:00
23	9	Create Return	2021-01-31 00:00:00
24	9	Create Return	2021-01-31 00:00:00
25	9	Create Return	2021-01-31 00:00:00
26	9	Create Return	2021-01-31 00:00:00
27	9	Create Return	2021-01-31 00:00:00
28	9	Create Return	2021-01-31 00:00:00
29	9	Create Return	2021-01-31 00:00:00
30	9	Create Return	2021-01-31 00:00:00
31	9	Create Return	2021-01-31 00:00:00
32	9	Create Return	2021-01-31 00:00:00
33	9	Create Return	2021-01-31 00:00:00
34	9	Create Return	2021-01-31 00:00:00
35	9	Create Return	2021-01-31 00:00:00
36	9	Create Return	2021-01-31 00:00:00
37	9	Create Return	2021-01-31 00:00:00
38	9	Create Return	2021-01-31 00:00:00
39	9	Create Return	2021-01-31 00:00:00
40	9	Create Return	2021-01-31 00:00:00
41	9	Create Return	2021-01-31 00:00:00
42	9	Create Return	2021-01-31 00:00:00
43	9	Create Return	2021-01-31 00:00:00
44	9	Create Return	2021-01-31 00:00:00
45	9	Create Return	2021-01-31 00:00:00
46	9	Create Return	2021-01-31 00:00:00
47	9	Create Return	2021-01-31 00:00:00
48	9	Create Return	2021-01-31 00:00:00
49	9	Create Return	2021-01-31 00:00:00
50	11	Create Service	2021-01-31 00:00:00
51	11	Create Service	2021-01-31 00:00:00
52	11	Create Service	2021-01-31 00:00:00
53	11	Create Service	2021-01-31 00:00:00
54	13	Create Service	2021-02-02 00:00:00
55	13	Create Service	2021-02-02 00:00:00
56	13	Create Service	2021-02-02 00:00:00
57	13	Create Service	2021-02-02 00:00:00
58	13	Create Return	2021-02-02 00:00:00
59	13	Create Return	2021-02-02 00:00:00
60	13	Create Service	2021-02-02 00:00:00
61	13	Create Return	2021-02-02 00:00:00
62	13	Create Service	2021-02-02 00:00:00
63	13	Create Service	2021-02-02 00:00:00
64	13	Create Service	2021-02-02 00:00:00
65	13	Create Service	2021-02-02 00:00:00
66	13	Create Service	2021-02-02 00:00:00
67	13	Create Service	2021-02-02 00:00:00
68	13	Create Service	2021-02-02 00:00:00
69	13	Create Service	2021-02-02 00:00:00
70	13	Create Service	2021-02-02 00:00:00
71	13	Create Service	2021-02-02 00:00:00
72	13	Create Service	2021-02-02 00:00:00
73	13	Create Service	2021-02-02 00:00:00
74	13	Create Service	2021-02-02 00:00:00
75	13	Create Service	2021-02-02 00:00:00
76	13	Create Service	2021-02-02 00:00:00
77	13	Create Service	2021-02-02 00:00:00
78	13	Create Service	2021-02-02 00:00:00
79	13	Create Service	2021-02-02 00:00:00
80	13	Create Service	2021-02-02 00:00:00
81	13	Create Service	2021-02-02 00:00:00
82	13	Create Service	2021-02-02 00:00:00
83	13	Create Service	2021-02-02 00:00:00
84	13	Create Service	2021-02-02 00:00:00
85	13	Create Service	2021-02-02 00:00:00
86	14	Create Return	2021-02-07 00:00:00
87	14	Create Return	2021-02-07 00:00:00
88	14	Create Return	2021-02-07 00:00:00
89	14	Create Return	2021-02-07 00:00:00
90	14	Create Return	2021-02-07 00:00:00
91	14	Create Return	2021-02-07 00:00:00
92	14	Create Return	2021-02-07 00:00:00
93	14	Create Return	2021-02-07 00:00:00
94	14	Create Return	2021-02-07 00:00:00
95	14	Create Return	2021-02-07 00:00:00
96	16	Create Service	2021-02-08 00:00:00
97	16	Create Return	2021-02-08 00:00:00
\.


--
-- TOC entry 3148 (class 0 OID 25306)
-- Dependencies: 203
-- Data for Name: casemessage; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.casemessage (messageid, caseid, sendto, subject, messagebody, createddate, updateddate) FROM stdin;
\.


--
-- TOC entry 3158 (class 0 OID 25383)
-- Dependencies: 213
-- Data for Name: casereturn; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.casereturn (casereturnid, caseid, customersoid, createddate, updateddate) FROM stdin;
1	9	1	\N	\N
2	9	1	\N	\N
\.


--
-- TOC entry 3154 (class 0 OID 25354)
-- Dependencies: 209
-- Data for Name: caseservice; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.caseservice (caseserviceid, caseid, customersoid, logmessage, createddate, updateddate) FROM stdin;
1	10	1	Service	\N	\N
2	10	1	Service	\N	\N
3	10	1	Service	\N	\N
4	9	1	Service	\N	\N
5	9	1	Service	\N	\N
6	9	1	Service	\N	\N
7	9	1	Service	\N	\N
8	9	1	Service	\N	\N
11	10	1	Service	\N	\N
12	9	1	Service	\N	\N
13	11	1	Service	\N	\N
14	11	1	Service	\N	\N
15	11	1	Service	\N	\N
16	11	1	Service	\N	\N
17	13	1	Service	\N	\N
18	13	1	Service	\N	\N
19	13	1	Service	\N	\N
20	13	1	Service	\N	\N
21	13	1	Service	\N	\N
22	13	1	Service	\N	\N
23	13	1	Service	\N	\N
24	13	1	Service	\N	\N
25	13	1	Service	\N	\N
26	13	1	Service	\N	\N
27	13	1	Service	\N	\N
28	13	1	Service	\N	\N
29	13	1	Service	\N	\N
30	13	1	Service	\N	\N
31	13	1	Service	\N	\N
32	13	1	Service	\N	\N
33	13	1	Service	\N	\N
34	13	1	Service	\N	\N
35	13	1	Service	\N	\N
36	13	1	Service	\N	\N
37	13	1	Service	\N	\N
38	13	1	Service	\N	\N
39	13	1	Service	\N	\N
40	13	1	Service	\N	\N
41	13	1	Service	\N	\N
42	13	1	Service	\N	\N
43	13	1	Service	\N	\N
44	13	1	Service	\N	\N
45	13	1	Service	\N	\N
46	16	1	Service	\N	\N
\.


--
-- TOC entry 3156 (class 0 OID 25370)
-- Dependencies: 211
-- Data for Name: caseserviceso; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.caseserviceso (caseservicedetailid, caseserviceid, customersoid, createddate, updateddate) FROM stdin;
1	1	1	\N	\N
2	1	1	\N	\N
3	1	1	\N	\N
4	1	4	\N	\N
5	1	5	\N	\N
6	1	6	\N	\N
7	1	7	\N	\N
8	1	8	\N	\N
9	1	11	\N	\N
10	1	12	\N	\N
11	1	16	\N	\N
12	1	21	\N	\N
13	1	22	\N	\N
14	1	37	\N	\N
15	1	39	\N	\N
16	1	41	\N	\N
17	1	43	\N	\N
18	1	46	\N	\N
\.


--
-- TOC entry 3172 (class 0 OID 25726)
-- Dependencies: 227
-- Data for Name: casetype; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.casetype (casetypeid, caseid, casetypevalue) FROM stdin;
1	3	1
5	9	1
6	9	2
7	9	3
8	10	1
30	13	2
31	13	3
36	16	1
37	16	2
38	16	3
\.


--
-- TOC entry 3160 (class 0 OID 25396)
-- Dependencies: 215
-- Data for Name: rma; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rma (rmaid, caseid, customersoid, status, createddate, updateddate) FROM stdin;
43	14	1	\N	\N	\N
44	14	1	\N	\N	\N
45	14	1	\N	\N	\N
46	14	1	\N	\N	\N
47	14	1	\N	\N	\N
48	14	1	\N	\N	\N
49	14	1	\N	\N	\N
50	14	1	\N	\N	\N
51	14	1	\N	\N	\N
52	14	1	3	\N	\N
53	16	1	\N	\N	\N
\.


--
-- TOC entry 3166 (class 0 OID 25681)
-- Dependencies: 221
-- Data for Name: rmalog; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rmalog (rmaid, logid, logmessage, createddate) FROM stdin;
52	6	Edit product	2021-02-07 00:00:00
52	7	Edit product	2021-02-07 00:00:00
52	8	Edit product	2021-02-07 00:00:00
52	9	Edit product	2021-02-08 00:00:00
52	10	Edit product	2021-02-08 00:00:00
52	11	Edit product	2021-02-08 00:00:00
52	12	Edit product	2021-02-08 00:00:00
52	13	Edit product	2021-02-08 00:00:00
52	14	Edit product	2021-02-08 00:00:00
52	15	Edit product	2021-02-08 00:00:00
52	16	Edit product	2021-02-08 00:00:00
52	17	Edit product	2021-02-08 00:00:00
53	18	Edit product	2021-02-08 00:00:00
\.


--
-- TOC entry 3164 (class 0 OID 25665)
-- Dependencies: 219
-- Data for Name: rmamessage; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rmamessage (rmaid, messageid, sendto, subject, messagebody, createddate) FROM stdin;
\.


--
-- TOC entry 3162 (class 0 OID 25649)
-- Dependencies: 217
-- Data for Name: rmapayment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rmapayment (rmaid, paymentid, paymenttype, paymentamount, paymentstatus) FROM stdin;
\.


--
-- TOC entry 3174 (class 0 OID 42167)
-- Dependencies: 229
-- Data for Name: rmaso; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rmaso (rmasoid, soid, rmaid, fee, total, createddate, updateddate) FROM stdin;
1	1	51	0	0	\N	\N
2	1	52	0	0	\N	\N
3	1	53	0	0	\N	\N
\.


--
-- TOC entry 3176 (class 0 OID 42183)
-- Dependencies: 231
-- Data for Name: rmaso_detail; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rmaso_detail (rmaid, rmasoid, sodetail_id, productid, quantity, price, createddate, updateddate) FROM stdin;
51	1	1	1	1	2095.2	\N	\N
51	1	2	2	2	2095.2	\N	\N
52	2	4	2	2	2095.2	\N	\N
52	2	6	592	1	2	\N	\N
53	3	8	1	1	2095.2	\N	\N
53	3	10	12	1	2	\N	\N
\.


--
-- TOC entry 3170 (class 0 OID 25710)
-- Dependencies: 225
-- Data for Name: servicedetail; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.servicedetail (servicedetailid, servicemasterid, productid, quantity, soldprice, amount, totalweight, serialnumber, iswarrantly, warrantystartdate, warrantyenddate, paymenttype, logmessage, createddate, warehouse) FROM stdin;
66	27	1	1	2095.02	2095.2	900	Product 02	\N	\N	\N	\N	\N	\N	\N
67	27	2	1	2095.02	2095.2	900	Product 01	\N	\N	\N	\N	\N	\N	\N
68	28	1	1	2095.02	2095.2	900	Product 02	\N	\N	\N	\N	\N	\N	\N
69	28	2	1	2095.02	2095.2	900	Product 01	\N	\N	\N	\N	\N	\N	\N
70	30	273	1	1000	2000	1000	Product 003 </br> Serial Number: 123-456-789	\N	\N	\N	\N	\N	\N	\N
71	30	839	1	1000	2000	1000	Product 003 </br> Serial Number: 123-456-789	\N	\N	\N	\N	\N	\N	\N
72	30	773	1	1000	2000	1000	Product 003 </br> Serial Number: 123-456-789	\N	\N	\N	\N	\N	\N	\N
73	31	2	1	2095.02	2095.2	900	Product 01	\N	\N	\N	\N	\N	\N	\N
30	2	800	1	1000	2000	1000	Product 003 </br> Serial Number: 123-456-789	\N	\N	\N	\N	\N	\N	\N
33	2	731	1	1000	2000	1000	Product 003 </br> Serial Number: 123-456-789	\N	\N	\N	\N	\N	\N	\N
34	2	501	1	1000	2000	1000	Product 003 </br> Serial Number: 123-456-789	\N	\N	\N	\N	\N	\N	\N
35	2	915	1	1000	2000	1000	Product 003 </br> Serial Number: 123-456-789	\N	\N	\N	\N	\N	\N	\N
36	2	723	1	1000	2000	1000	Product 003 </br> Serial Number: 123-456-789	\N	\N	\N	\N	\N	\N	\N
37	2	281	1	1000	2000	1000	Product 003 </br> Serial Number: 123-456-789	\N	\N	\N	\N	\N	\N	\N
38	2	318	1	1000	2000	1000	Product 003 </br> Serial Number: 123-456-789	\N	\N	\N	\N	\N	\N	\N
39	2	255	1	1000	2000	1000	Product 003 </br> Serial Number: 123-456-789	\N	\N	\N	\N	\N	\N	\N
40	2	373	1	1000	2000	1000	Product 003 </br> Serial Number: 123-456-789	\N	\N	\N	\N	\N	\N	\N
41	3	1	1	2095.02	2095.2	900	Product 02	\N	\N	\N	\N	\N	\N	\N
42	3	2	1	2095.02	2095.2	900	Product 01	\N	\N	\N	\N	\N	\N	\N
43	4	2	1	2095.02	2095.2	900	Product 01	\N	\N	\N	\N	\N	\N	\N
44	4	1	1	2095.02	2095.2	900	Product 02	\N	\N	\N	\N	\N	\N	\N
45	5	1	1	2095.02	2095.2	900	Product 02	\N	\N	\N	\N	\N	\N	\N
46	5	2	1	2095.02	2095.2	900	Product 01	\N	\N	\N	\N	\N	\N	\N
47	13	1	1	2095.02	2095.2	900	Product 02	\N	\N	\N	\N	\N	\N	\N
48	13	2	1	2095.02	2095.2	900	Product 01	\N	\N	\N	\N	\N	\N	\N
49	13	758	1	1000	2000	1000	Product 003 </br> Serial Number: 123-456-789	\N	\N	\N	\N	\N	\N	\N
50	14	1	1	2095.02	2095.2	900	Product 02	\N	\N	\N	\N	\N	\N	\N
51	14	2	1	2095.02	2095.2	900	Product 01	\N	\N	\N	\N	\N	\N	\N
52	15	2	1	2095.02	2095.2	900	Product 01	\N	\N	\N	\N	\N	\N	\N
53	15	1	1	2095.02	2095.2	900	Product 02	\N	\N	\N	\N	\N	\N	\N
54	16	50	1	1000	2000	1000	Product 003 </br> Serial Number: 123-456-789	\N	\N	\N	\N	\N	\N	\N
55	16	211	1	1000	2000	1000	Product 003 </br> Serial Number: 123-456-789	\N	\N	\N	\N	\N	\N	\N
56	25	2	1	2095.02	2095.2	900	Product 01	\N	\N	\N	\N	\N	\N	\N
57	25	1	1	2095.02	2095.2	900	Product 02	\N	\N	\N	\N	\N	\N	\N
58	25	994	1	1000	2000	1000	Product 003 </br> Serial Number: 123-456-789	\N	\N	\N	\N	\N	\N	\N
61	26	1	1	2095.02	2095.2	900	Product 02	\N	\N	\N	\N	\N	\N	\N
62	26	2	1	2095.02	2095.2	900	Product 01	\N	\N	\N	\N	\N	\N	\N
75	31	297	1	1000	2000	1000	Product 003 </br> Serial Number: 123-456-789	\N	\N	\N	\N	\N	\N	\N
\.


--
-- TOC entry 3168 (class 0 OID 25697)
-- Dependencies: 223
-- Data for Name: servicemaster; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.servicemaster (servicemasterid, caseserviceid, subtotal, shippingfee, managerdiscount, total, createddate, issubmittedproduction, status, warehouse) FROM stdin;
1	8	\N	\N	\N	\N	\N	\N	1	\N
2	11	\N	\N	\N	\N	\N	\N	1	\N
3	12	\N	\N	\N	\N	\N	\N	1	\N
4	3	\N	\N	\N	\N	\N	\N	1	\N
6	5	\N	\N	\N	\N	\N	\N	1	\N
7	6	\N	\N	\N	\N	\N	\N	1	\N
5	4	\N	\N	\N	\N	\N	\N	1	\N
8	7	\N	\N	\N	\N	\N	\N	1	\N
9	8	\N	\N	\N	\N	\N	\N	1	\N
12	11	\N	\N	\N	\N	\N	\N	0	\N
13	16	\N	\N	\N	\N	\N	1	2	\N
14	21	\N	\N	\N	\N	\N	\N	0	\N
15	22	\N	\N	\N	\N	\N	\N	0	\N
16	28	\N	\N	\N	\N	\N	\N	0	\N
17	29	\N	\N	\N	\N	\N	\N	0	\N
18	30	\N	\N	\N	\N	\N	\N	0	\N
19	31	\N	\N	\N	\N	\N	\N	0	\N
20	32	\N	\N	\N	\N	\N	\N	0	\N
21	33	\N	\N	\N	\N	\N	\N	0	\N
22	34	\N	\N	\N	\N	\N	\N	0	\N
23	35	\N	\N	\N	\N	\N	\N	0	\N
24	36	\N	\N	\N	\N	\N	\N	0	\N
25	37	\N	\N	\N	\N	\N	\N	0	\N
26	39	\N	\N	\N	\N	\N	\N	0	\N
27	41	\N	\N	\N	\N	\N	\N	0	\N
28	43	\N	\N	\N	\N	\N	\N	0	\N
29	44	\N	\N	\N	\N	\N	\N	0	\N
30	45	\N	\N	\N	\N	\N	\N	0	\N
31	46	\N	\N	\N	\N	\N	\N	0	\N
\.


--
-- TOC entry 3196 (class 0 OID 0)
-- Dependencies: 200
-- Name: case_caseid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.case_caseid_seq', 16, true);


--
-- TOC entry 3197 (class 0 OID 0)
-- Dependencies: 204
-- Name: caseinformation_transactionid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.caseinformation_transactionid_seq', 97, true);


--
-- TOC entry 3198 (class 0 OID 0)
-- Dependencies: 206
-- Name: caselog_logid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.caselog_logid_seq', 97, true);


--
-- TOC entry 3199 (class 0 OID 0)
-- Dependencies: 202
-- Name: casemessage_messageid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.casemessage_messageid_seq', 1, false);


--
-- TOC entry 3200 (class 0 OID 0)
-- Dependencies: 212
-- Name: casereturn_casereturnid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.casereturn_casereturnid_seq', 2, true);


--
-- TOC entry 3201 (class 0 OID 0)
-- Dependencies: 208
-- Name: caseservice_caseserviceid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.caseservice_caseserviceid_seq', 46, true);


--
-- TOC entry 3202 (class 0 OID 0)
-- Dependencies: 210
-- Name: caseserviceso_caseservicedetailid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.caseserviceso_caseservicedetailid_seq', 18, true);


--
-- TOC entry 3203 (class 0 OID 0)
-- Dependencies: 226
-- Name: casetype_casetypeid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.casetype_casetypeid_seq', 38, true);


--
-- TOC entry 3204 (class 0 OID 0)
-- Dependencies: 214
-- Name: rma_rmaid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rma_rmaid_seq', 53, true);


--
-- TOC entry 3205 (class 0 OID 0)
-- Dependencies: 220
-- Name: rmalog_logid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rmalog_logid_seq', 18, true);


--
-- TOC entry 3206 (class 0 OID 0)
-- Dependencies: 218
-- Name: rmamessage_messageid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rmamessage_messageid_seq', 1, false);


--
-- TOC entry 3207 (class 0 OID 0)
-- Dependencies: 216
-- Name: rmapayment_paymentid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rmapayment_paymentid_seq', 1, false);


--
-- TOC entry 3208 (class 0 OID 0)
-- Dependencies: 230
-- Name: rmaso_detail_sodetail_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rmaso_detail_sodetail_id_seq', 10, true);


--
-- TOC entry 3209 (class 0 OID 0)
-- Dependencies: 228
-- Name: rmaso_soid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rmaso_soid_seq', 3, true);


--
-- TOC entry 3210 (class 0 OID 0)
-- Dependencies: 224
-- Name: servicedetail_servicedetailid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.servicedetail_servicedetailid_seq', 75, true);


--
-- TOC entry 3211 (class 0 OID 0)
-- Dependencies: 222
-- Name: servicemaster_servicemasterid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.servicemaster_servicemasterid_seq', 31, true);


--
-- TOC entry 2969 (class 2606 OID 25303)
-- Name: case case_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."case"
    ADD CONSTRAINT case_pkey PRIMARY KEY (caseid);


--
-- TOC entry 2973 (class 2606 OID 25330)
-- Name: caseinformation caseinformation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.caseinformation
    ADD CONSTRAINT caseinformation_pkey PRIMARY KEY (transactionid);


--
-- TOC entry 2975 (class 2606 OID 25346)
-- Name: caselog caselog_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.caselog
    ADD CONSTRAINT caselog_pkey PRIMARY KEY (logid);


--
-- TOC entry 2971 (class 2606 OID 25314)
-- Name: casemessage casemessage_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.casemessage
    ADD CONSTRAINT casemessage_pkey PRIMARY KEY (messageid);


--
-- TOC entry 2981 (class 2606 OID 25388)
-- Name: casereturn casereturn_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.casereturn
    ADD CONSTRAINT casereturn_pkey PRIMARY KEY (casereturnid);


--
-- TOC entry 2977 (class 2606 OID 25362)
-- Name: caseservice caseservice_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.caseservice
    ADD CONSTRAINT caseservice_pkey PRIMARY KEY (caseserviceid);


--
-- TOC entry 2979 (class 2606 OID 25375)
-- Name: caseserviceso caseserviceso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.caseserviceso
    ADD CONSTRAINT caseserviceso_pkey PRIMARY KEY (caseservicedetailid);


--
-- TOC entry 2995 (class 2606 OID 25734)
-- Name: casetype casetype_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.casetype
    ADD CONSTRAINT casetype_pkey PRIMARY KEY (casetypeid);


--
-- TOC entry 2983 (class 2606 OID 25404)
-- Name: rma rma_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rma
    ADD CONSTRAINT rma_pkey PRIMARY KEY (rmaid);


--
-- TOC entry 2989 (class 2606 OID 25689)
-- Name: rmalog rmalog_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rmalog
    ADD CONSTRAINT rmalog_pkey PRIMARY KEY (logid);


--
-- TOC entry 2987 (class 2606 OID 25673)
-- Name: rmamessage rmamessage_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rmamessage
    ADD CONSTRAINT rmamessage_pkey PRIMARY KEY (messageid);


--
-- TOC entry 2985 (class 2606 OID 25657)
-- Name: rmapayment rmapayment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rmapayment
    ADD CONSTRAINT rmapayment_pkey PRIMARY KEY (paymentid);


--
-- TOC entry 2999 (class 2606 OID 42188)
-- Name: rmaso_detail rmaso_detail_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rmaso_detail
    ADD CONSTRAINT rmaso_detail_pkey PRIMARY KEY (sodetail_id);


--
-- TOC entry 2997 (class 2606 OID 42172)
-- Name: rmaso rmaso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rmaso
    ADD CONSTRAINT rmaso_pkey PRIMARY KEY (rmasoid);


--
-- TOC entry 2993 (class 2606 OID 25718)
-- Name: servicedetail servicedetail_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.servicedetail
    ADD CONSTRAINT servicedetail_pkey PRIMARY KEY (servicedetailid);


--
-- TOC entry 2991 (class 2606 OID 25702)
-- Name: servicemaster servicemaster_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.servicemaster
    ADD CONSTRAINT servicemaster_pkey PRIMARY KEY (servicemasterid);


--
-- TOC entry 3012 (class 2606 OID 25735)
-- Name: casetype fk_case_case_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.casetype
    ADD CONSTRAINT fk_case_case_id FOREIGN KEY (caseid) REFERENCES public."case"(caseid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3005 (class 2606 OID 25389)
-- Name: casereturn fk_casereturn_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.casereturn
    ADD CONSTRAINT fk_casereturn_id FOREIGN KEY (caseid) REFERENCES public."case"(caseid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3003 (class 2606 OID 25363)
-- Name: caseservice fk_caseservice_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.caseservice
    ADD CONSTRAINT fk_caseservice_id FOREIGN KEY (caseid) REFERENCES public."case"(caseid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3004 (class 2606 OID 25376)
-- Name: caseserviceso fk_caseservice_id2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.caseserviceso
    ADD CONSTRAINT fk_caseservice_id2 FOREIGN KEY (caseserviceid) REFERENCES public.caseservice(caseserviceid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3001 (class 2606 OID 25331)
-- Name: caseinformation fk_group_caseinformation_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.caseinformation
    ADD CONSTRAINT fk_group_caseinformation_id FOREIGN KEY (caseid) REFERENCES public."case"(caseid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3002 (class 2606 OID 25347)
-- Name: caselog fk_group_caselog_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.caselog
    ADD CONSTRAINT fk_group_caselog_id FOREIGN KEY (caseid) REFERENCES public."case"(caseid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3000 (class 2606 OID 25315)
-- Name: casemessage fk_group_casemessage_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.casemessage
    ADD CONSTRAINT fk_group_casemessage_id FOREIGN KEY (caseid) REFERENCES public."case"(caseid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3006 (class 2606 OID 25405)
-- Name: rma fk_rma_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rma
    ADD CONSTRAINT fk_rma_id FOREIGN KEY (caseid) REFERENCES public."case"(caseid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3009 (class 2606 OID 25690)
-- Name: rmalog fk_rmalog_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rmalog
    ADD CONSTRAINT fk_rmalog_id FOREIGN KEY (rmaid) REFERENCES public.rma(rmaid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3008 (class 2606 OID 25674)
-- Name: rmamessage fk_rmamessage_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rmamessage
    ADD CONSTRAINT fk_rmamessage_id FOREIGN KEY (rmaid) REFERENCES public.rma(rmaid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3007 (class 2606 OID 25658)
-- Name: rmapayment fk_rmapayment_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rmapayment
    ADD CONSTRAINT fk_rmapayment_id FOREIGN KEY (rmaid) REFERENCES public.rma(rmaid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3013 (class 2606 OID 42173)
-- Name: rmaso fk_rmaso_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rmaso
    ADD CONSTRAINT fk_rmaso_id FOREIGN KEY (rmaid) REFERENCES public.rma(rmaid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3011 (class 2606 OID 25719)
-- Name: servicedetail fk_servicedetail_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.servicedetail
    ADD CONSTRAINT fk_servicedetail_id FOREIGN KEY (servicemasterid) REFERENCES public.servicemaster(servicemasterid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3010 (class 2606 OID 25703)
-- Name: servicemaster fk_servicemaster_case_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.servicemaster
    ADD CONSTRAINT fk_servicemaster_case_id FOREIGN KEY (caseserviceid) REFERENCES public.caseservice(caseserviceid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3014 (class 2606 OID 42189)
-- Name: rmaso_detail rmasoid; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rmaso_detail
    ADD CONSTRAINT rmasoid FOREIGN KEY (rmasoid) REFERENCES public.rmaso(rmasoid);


-- Completed on 2021-02-08 07:06:38

--
-- PostgreSQL database dump complete
--

