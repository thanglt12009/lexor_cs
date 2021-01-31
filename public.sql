/*
 Navicat PostgreSQL Data Transfer

 Source Server         : Local
 Source Server Type    : PostgreSQL
 Source Server Version : 130001
 Source Host           : localhost:5432
 Source Catalog        : lexor_cs
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 130001
 File Encoding         : 65001

 Date: 31/01/2021 22:12:23
*/


-- ----------------------------
-- Sequence structure for case_caseid_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."case_caseid_seq";
CREATE SEQUENCE "public"."case_caseid_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for caseinformation_transactionid_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."caseinformation_transactionid_seq";
CREATE SEQUENCE "public"."caseinformation_transactionid_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for caselog_logid_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."caselog_logid_seq";
CREATE SEQUENCE "public"."caselog_logid_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for casemessage_messageid_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."casemessage_messageid_seq";
CREATE SEQUENCE "public"."casemessage_messageid_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for casereturn_casereturnid_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."casereturn_casereturnid_seq";
CREATE SEQUENCE "public"."casereturn_casereturnid_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for caseservice_caseserviceid_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."caseservice_caseserviceid_seq";
CREATE SEQUENCE "public"."caseservice_caseserviceid_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for caseserviceso_caseservicedetailid_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."caseserviceso_caseservicedetailid_seq";
CREATE SEQUENCE "public"."caseserviceso_caseservicedetailid_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for casetype_casetypeid_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."casetype_casetypeid_seq";
CREATE SEQUENCE "public"."casetype_casetypeid_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for rma_rmaid_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."rma_rmaid_seq";
CREATE SEQUENCE "public"."rma_rmaid_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for rmalog_logid_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."rmalog_logid_seq";
CREATE SEQUENCE "public"."rmalog_logid_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for rmamessage_messageid_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."rmamessage_messageid_seq";
CREATE SEQUENCE "public"."rmamessage_messageid_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for rmapayment_paymentid_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."rmapayment_paymentid_seq";
CREATE SEQUENCE "public"."rmapayment_paymentid_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for rmaso_detail_sodetail_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."rmaso_detail_sodetail_id_seq";
CREATE SEQUENCE "public"."rmaso_detail_sodetail_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for rmaso_soid_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."rmaso_soid_seq";
CREATE SEQUENCE "public"."rmaso_soid_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for servicedetail_servicedetailid_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."servicedetail_servicedetailid_seq";
CREATE SEQUENCE "public"."servicedetail_servicedetailid_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for servicemaster_servicemasterid_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."servicemaster_servicemasterid_seq";
CREATE SEQUENCE "public"."servicemaster_servicemasterid_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Table structure for case
-- ----------------------------
DROP TABLE IF EXISTS "public"."case";
CREATE TABLE "public"."case" (
  "caseid" int4 NOT NULL DEFAULT nextval('case_caseid_seq'::regclass),
  "customerid" int4 NOT NULL,
  "salonid" int4 NOT NULL,
  "casename" text COLLATE "pg_catalog"."default",
  "casepriority" int4,
  "casetype" int4 NOT NULL,
  "status" int4 NOT NULL,
  "customerservicerep" int4 NOT NULL,
  "createddate" timestamp(6),
  "updateddate" timestamp(6)
)
;

-- ----------------------------
-- Records of case
-- ----------------------------
INSERT INTO "public"."case" VALUES (10, 91059, 0, NULL, 1, 1, 2, 2, NULL, NULL);
INSERT INTO "public"."case" VALUES (8, 55972, 0, NULL, 1, 1, 2, 1, NULL, NULL);
INSERT INTO "public"."case" VALUES (7, 80117, 0, NULL, 1, 1, 2, 1, NULL, NULL);
INSERT INTO "public"."case" VALUES (6, 25910, 0, NULL, 1, 1, 2, 1, NULL, NULL);
INSERT INTO "public"."case" VALUES (5, 18852, 0, NULL, 1, 1, 2, 1, NULL, NULL);
INSERT INTO "public"."case" VALUES (4, 8776, 0, NULL, 1, 1, 2, 1, NULL, NULL);
INSERT INTO "public"."case" VALUES (3, 61351, 0, NULL, 1, 1, 2, 1, NULL, NULL);
INSERT INTO "public"."case" VALUES (9, 77372, 0, NULL, 2, 1, 2, 5, NULL, NULL);
INSERT INTO "public"."case" VALUES (11, 1, 0, NULL, 1, 1, 1, 0, NULL, NULL);

-- ----------------------------
-- Table structure for caseinformation
-- ----------------------------
DROP TABLE IF EXISTS "public"."caseinformation";
CREATE TABLE "public"."caseinformation" (
  "transactionid" int4 NOT NULL DEFAULT nextval('caseinformation_transactionid_seq'::regclass),
  "caseid" int4 NOT NULL,
  "doccode" text COLLATE "pg_catalog"."default",
  "status" int4,
  "address" text COLLATE "pg_catalog"."default",
  "createddate" timestamp(6)
)
;

-- ----------------------------
-- Records of caseinformation
-- ----------------------------
INSERT INTO "public"."caseinformation" VALUES (1, 10, 'Service', 1, 'Address', '2021-01-30 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (2, 10, 'Service', 1, 'Address', '2021-01-30 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (3, 10, 'Service', 1, 'Address', '2021-01-30 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (4, 9, 'Service', 1, 'Address', '2021-01-30 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (5, 9, 'Service', 1, 'Address', '2021-01-30 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (6, 9, 'Service', 1, 'Address', '2021-01-30 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (7, 9, 'Service', 1, 'Address', '2021-01-30 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (8, 9, 'Service', 1, 'Address', '2021-01-30 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (9, 10, 'Service', 1, 'Address', '2021-01-30 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (10, 9, 'Service', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (11, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (12, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (13, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (14, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (15, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (16, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (17, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (18, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (19, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (20, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (21, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (22, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (23, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (24, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (25, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (26, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (27, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (28, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (29, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (30, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (31, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (32, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (33, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (34, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (35, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (36, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (37, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (38, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (39, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (40, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (41, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (42, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (43, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (44, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (45, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (46, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (47, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (48, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (49, 9, 'Return', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (50, 11, 'Service', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (51, 11, 'Service', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (52, 11, 'Service', 1, 'Address', '2021-01-31 00:00:00');
INSERT INTO "public"."caseinformation" VALUES (53, 11, 'Service', 1, 'Address', '2021-01-31 00:00:00');

-- ----------------------------
-- Table structure for caselog
-- ----------------------------
DROP TABLE IF EXISTS "public"."caselog";
CREATE TABLE "public"."caselog" (
  "logid" int4 NOT NULL DEFAULT nextval('caselog_logid_seq'::regclass),
  "caseid" int4 NOT NULL,
  "logmessage" text COLLATE "pg_catalog"."default",
  "createddate" timestamp(6) NOT NULL
)
;

-- ----------------------------
-- Records of caselog
-- ----------------------------
INSERT INTO "public"."caselog" VALUES (1, 10, 'Create Service', '2021-01-30 00:00:00');
INSERT INTO "public"."caselog" VALUES (2, 10, 'Create Service', '2021-01-30 00:00:00');
INSERT INTO "public"."caselog" VALUES (3, 10, 'Create Service', '2021-01-30 00:00:00');
INSERT INTO "public"."caselog" VALUES (4, 9, 'Create Service', '2021-01-30 00:00:00');
INSERT INTO "public"."caselog" VALUES (5, 9, 'Create Service', '2021-01-30 00:00:00');
INSERT INTO "public"."caselog" VALUES (6, 9, 'Create Service', '2021-01-30 00:00:00');
INSERT INTO "public"."caselog" VALUES (7, 9, 'Create Service', '2021-01-30 00:00:00');
INSERT INTO "public"."caselog" VALUES (8, 9, 'Create Service', '2021-01-30 00:00:00');
INSERT INTO "public"."caselog" VALUES (9, 10, 'Create Service', '2021-01-30 00:00:00');
INSERT INTO "public"."caselog" VALUES (10, 9, 'Create Service', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (11, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (12, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (13, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (14, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (15, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (16, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (17, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (18, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (19, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (20, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (21, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (22, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (23, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (24, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (25, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (26, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (27, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (28, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (29, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (30, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (31, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (32, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (33, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (34, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (35, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (36, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (37, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (38, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (39, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (40, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (41, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (42, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (43, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (44, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (45, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (46, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (47, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (48, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (49, 9, 'Create Return', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (50, 11, 'Create Service', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (51, 11, 'Create Service', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (52, 11, 'Create Service', '2021-01-31 00:00:00');
INSERT INTO "public"."caselog" VALUES (53, 11, 'Create Service', '2021-01-31 00:00:00');

-- ----------------------------
-- Table structure for casemessage
-- ----------------------------
DROP TABLE IF EXISTS "public"."casemessage";
CREATE TABLE "public"."casemessage" (
  "messageid" int4 NOT NULL DEFAULT nextval('casemessage_messageid_seq'::regclass),
  "caseid" int4 NOT NULL,
  "sendto" text COLLATE "pg_catalog"."default",
  "subject" text COLLATE "pg_catalog"."default",
  "messagebody" text COLLATE "pg_catalog"."default",
  "createddate" timestamp(6),
  "updateddate" timestamp(6)
)
;

-- ----------------------------
-- Records of casemessage
-- ----------------------------

-- ----------------------------
-- Table structure for casereturn
-- ----------------------------
DROP TABLE IF EXISTS "public"."casereturn";
CREATE TABLE "public"."casereturn" (
  "casereturnid" int4 NOT NULL DEFAULT nextval('casereturn_casereturnid_seq'::regclass),
  "caseid" int4 NOT NULL,
  "customersoid" int4 NOT NULL,
  "createddate" timestamp(6),
  "updateddate" timestamp(6)
)
;

-- ----------------------------
-- Records of casereturn
-- ----------------------------
INSERT INTO "public"."casereturn" VALUES (1, 9, 1, NULL, NULL);
INSERT INTO "public"."casereturn" VALUES (2, 9, 1, NULL, NULL);

-- ----------------------------
-- Table structure for caseservice
-- ----------------------------
DROP TABLE IF EXISTS "public"."caseservice";
CREATE TABLE "public"."caseservice" (
  "caseserviceid" int4 NOT NULL DEFAULT nextval('caseservice_caseserviceid_seq'::regclass),
  "caseid" int4 NOT NULL,
  "customersoid" int4 NOT NULL,
  "logmessage" text COLLATE "pg_catalog"."default",
  "createddate" timestamp(6),
  "updateddate" timestamp(6)
)
;

-- ----------------------------
-- Records of caseservice
-- ----------------------------
INSERT INTO "public"."caseservice" VALUES (1, 10, 1, 'Service', NULL, NULL);
INSERT INTO "public"."caseservice" VALUES (2, 10, 1, 'Service', NULL, NULL);
INSERT INTO "public"."caseservice" VALUES (3, 10, 1, 'Service', NULL, NULL);
INSERT INTO "public"."caseservice" VALUES (4, 9, 1, 'Service', NULL, NULL);
INSERT INTO "public"."caseservice" VALUES (5, 9, 1, 'Service', NULL, NULL);
INSERT INTO "public"."caseservice" VALUES (6, 9, 1, 'Service', NULL, NULL);
INSERT INTO "public"."caseservice" VALUES (7, 9, 1, 'Service', NULL, NULL);
INSERT INTO "public"."caseservice" VALUES (8, 9, 1, 'Service', NULL, NULL);
INSERT INTO "public"."caseservice" VALUES (11, 10, 1, 'Service', NULL, NULL);
INSERT INTO "public"."caseservice" VALUES (12, 9, 1, 'Service', NULL, NULL);
INSERT INTO "public"."caseservice" VALUES (13, 11, 1, 'Service', NULL, NULL);
INSERT INTO "public"."caseservice" VALUES (14, 11, 1, 'Service', NULL, NULL);
INSERT INTO "public"."caseservice" VALUES (15, 11, 1, 'Service', NULL, NULL);
INSERT INTO "public"."caseservice" VALUES (16, 11, 1, 'Service', NULL, NULL);

-- ----------------------------
-- Table structure for caseserviceso
-- ----------------------------
DROP TABLE IF EXISTS "public"."caseserviceso";
CREATE TABLE "public"."caseserviceso" (
  "caseservicedetailid" int4 NOT NULL DEFAULT nextval('caseserviceso_caseservicedetailid_seq'::regclass),
  "caseserviceid" int4 NOT NULL,
  "customersoid" int4 NOT NULL,
  "createddate" timestamp(6),
  "updateddate" timestamp(6)
)
;

-- ----------------------------
-- Records of caseserviceso
-- ----------------------------
INSERT INTO "public"."caseserviceso" VALUES (1, 1, 1, NULL, NULL);
INSERT INTO "public"."caseserviceso" VALUES (2, 1, 1, NULL, NULL);
INSERT INTO "public"."caseserviceso" VALUES (3, 1, 1, NULL, NULL);
INSERT INTO "public"."caseserviceso" VALUES (4, 1, 4, NULL, NULL);
INSERT INTO "public"."caseserviceso" VALUES (5, 1, 5, NULL, NULL);
INSERT INTO "public"."caseserviceso" VALUES (6, 1, 6, NULL, NULL);
INSERT INTO "public"."caseserviceso" VALUES (7, 1, 7, NULL, NULL);
INSERT INTO "public"."caseserviceso" VALUES (8, 1, 8, NULL, NULL);
INSERT INTO "public"."caseserviceso" VALUES (9, 1, 11, NULL, NULL);
INSERT INTO "public"."caseserviceso" VALUES (10, 1, 12, NULL, NULL);
INSERT INTO "public"."caseserviceso" VALUES (11, 1, 16, NULL, NULL);

-- ----------------------------
-- Table structure for casetype
-- ----------------------------
DROP TABLE IF EXISTS "public"."casetype";
CREATE TABLE "public"."casetype" (
  "casetypeid" int4 NOT NULL DEFAULT nextval('casetype_casetypeid_seq'::regclass),
  "caseid" int4 NOT NULL,
  "casetypevalue" text COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of casetype
-- ----------------------------
INSERT INTO "public"."casetype" VALUES (1, 3, '1');
INSERT INTO "public"."casetype" VALUES (5, 9, '1');
INSERT INTO "public"."casetype" VALUES (6, 9, '2');
INSERT INTO "public"."casetype" VALUES (7, 9, '3');
INSERT INTO "public"."casetype" VALUES (8, 10, '1');
INSERT INTO "public"."casetype" VALUES (9, 3, '1');

-- ----------------------------
-- Table structure for rma
-- ----------------------------
DROP TABLE IF EXISTS "public"."rma";
CREATE TABLE "public"."rma" (
  "rmaid" int4 NOT NULL DEFAULT nextval('rma_rmaid_seq'::regclass),
  "caseid" int4 NOT NULL,
  "customersoid" int4 NOT NULL,
  "status" text COLLATE "pg_catalog"."default",
  "createddate" timestamp(6),
  "updateddate" timestamp(6)
)
;

-- ----------------------------
-- Records of rma
-- ----------------------------
INSERT INTO "public"."rma" VALUES (39, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (38, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (37, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (36, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (35, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (34, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (33, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (32, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (31, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (29, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (28, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (27, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (26, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (25, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (24, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (23, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (22, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (21, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (20, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (19, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (18, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (17, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (16, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (15, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (14, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (11, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (10, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (9, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (8, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (7, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (6, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (5, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (4, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (3, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (2, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (1, 9, 1, '1', NULL, NULL);
INSERT INTO "public"."rma" VALUES (30, 9, 1, '1', NULL, NULL);

-- ----------------------------
-- Table structure for rmalog
-- ----------------------------
DROP TABLE IF EXISTS "public"."rmalog";
CREATE TABLE "public"."rmalog" (
  "rmaid" int4 NOT NULL,
  "logid" int4 NOT NULL DEFAULT nextval('rmalog_logid_seq'::regclass),
  "logmessage" text COLLATE "pg_catalog"."default",
  "createddate" timestamp(6)
)
;

-- ----------------------------
-- Records of rmalog
-- ----------------------------
INSERT INTO "public"."rmalog" VALUES (30, 1, 'Edit product', '2021-01-31 00:00:00');
INSERT INTO "public"."rmalog" VALUES (30, 2, 'Edit product', '2021-01-31 00:00:00');
INSERT INTO "public"."rmalog" VALUES (30, 3, 'Edit product', '2021-01-31 00:00:00');
INSERT INTO "public"."rmalog" VALUES (30, 4, 'Edit product', '2021-01-31 00:00:00');
INSERT INTO "public"."rmalog" VALUES (30, 5, 'Edit product', '2021-01-31 00:00:00');

-- ----------------------------
-- Table structure for rmamessage
-- ----------------------------
DROP TABLE IF EXISTS "public"."rmamessage";
CREATE TABLE "public"."rmamessage" (
  "rmaid" int4 NOT NULL,
  "messageid" int4 NOT NULL DEFAULT nextval('rmamessage_messageid_seq'::regclass),
  "sendto" text COLLATE "pg_catalog"."default",
  "subject" text COLLATE "pg_catalog"."default",
  "messagebody" text COLLATE "pg_catalog"."default",
  "createddate" timestamp(6)
)
;

-- ----------------------------
-- Records of rmamessage
-- ----------------------------

-- ----------------------------
-- Table structure for rmapayment
-- ----------------------------
DROP TABLE IF EXISTS "public"."rmapayment";
CREATE TABLE "public"."rmapayment" (
  "rmaid" int4 NOT NULL,
  "paymentid" int4 NOT NULL DEFAULT nextval('rmapayment_paymentid_seq'::regclass),
  "paymenttype" text COLLATE "pg_catalog"."default" NOT NULL,
  "paymentamount" float8 NOT NULL,
  "paymentstatus" int4 NOT NULL
)
;

-- ----------------------------
-- Records of rmapayment
-- ----------------------------

-- ----------------------------
-- Table structure for rmaso
-- ----------------------------
DROP TABLE IF EXISTS "public"."rmaso";
CREATE TABLE "public"."rmaso" (
  "soid" int4 NOT NULL DEFAULT nextval('rmaso_soid_seq'::regclass),
  "rmaid" int4 NOT NULL,
  "fee" float8 NOT NULL,
  "total" float8 NOT NULL,
  "createddate" timestamp(6),
  "updateddate" timestamp(6)
)
;

-- ----------------------------
-- Records of rmaso
-- ----------------------------
INSERT INTO "public"."rmaso" VALUES (1, 17, 0, 0, NULL, NULL);
INSERT INTO "public"."rmaso" VALUES (3, 24, 0, 0, NULL, NULL);
INSERT INTO "public"."rmaso" VALUES (4, 25, 0, 0, NULL, NULL);
INSERT INTO "public"."rmaso" VALUES (5, 26, 0, 0, NULL, NULL);
INSERT INTO "public"."rmaso" VALUES (6, 27, 0, 0, NULL, NULL);
INSERT INTO "public"."rmaso" VALUES (7, 28, 0, 0, NULL, NULL);
INSERT INTO "public"."rmaso" VALUES (8, 29, 0, 0, NULL, NULL);
INSERT INTO "public"."rmaso" VALUES (9, 30, 0, 0, NULL, NULL);
INSERT INTO "public"."rmaso" VALUES (10, 31, 0, 0, NULL, NULL);
INSERT INTO "public"."rmaso" VALUES (11, 31, 0, 0, NULL, NULL);
INSERT INTO "public"."rmaso" VALUES (12, 32, 0, 0, NULL, NULL);
INSERT INTO "public"."rmaso" VALUES (13, 33, 0, 0, NULL, NULL);
INSERT INTO "public"."rmaso" VALUES (14, 34, 0, 0, NULL, NULL);
INSERT INTO "public"."rmaso" VALUES (15, 35, 0, 0, NULL, NULL);
INSERT INTO "public"."rmaso" VALUES (16, 36, 0, 0, NULL, NULL);
INSERT INTO "public"."rmaso" VALUES (17, 38, 0, 0, NULL, NULL);
INSERT INTO "public"."rmaso" VALUES (18, 39, 0, 0, NULL, NULL);

-- ----------------------------
-- Table structure for rmaso_detail
-- ----------------------------
DROP TABLE IF EXISTS "public"."rmaso_detail";
CREATE TABLE "public"."rmaso_detail" (
  "rmaid" int4 NOT NULL,
  "soid" int4 NOT NULL,
  "sodetail_id" int4 NOT NULL DEFAULT nextval('rmaso_detail_sodetail_id_seq'::regclass),
  "productid" int4 NOT NULL,
  "quantity" int4 NOT NULL,
  "price" float8 NOT NULL,
  "createddate" timestamp(6),
  "updateddate" timestamp(6)
)
;

-- ----------------------------
-- Records of rmaso_detail
-- ----------------------------
INSERT INTO "public"."rmaso_detail" VALUES (29, 8, 1, 1, 1, 0, NULL, NULL);
INSERT INTO "public"."rmaso_detail" VALUES (29, 8, 2, 1, 1, 0, NULL, NULL);
INSERT INTO "public"."rmaso_detail" VALUES (30, 9, 3, 1, 1, 2095.2, NULL, NULL);
INSERT INTO "public"."rmaso_detail" VALUES (30, 9, 4, 1, 1, 2095.2, NULL, NULL);
INSERT INTO "public"."rmaso_detail" VALUES (31, 10, 5, 1, 1, 2095.2, NULL, NULL);
INSERT INTO "public"."rmaso_detail" VALUES (31, 10, 6, 1, 1, 2095.2, NULL, NULL);
INSERT INTO "public"."rmaso_detail" VALUES (32, 12, 7, 1, 1, 2095.2, NULL, NULL);
INSERT INTO "public"."rmaso_detail" VALUES (32, 12, 8, 1, 1, 2095.2, NULL, NULL);
INSERT INTO "public"."rmaso_detail" VALUES (33, 13, 9, 1, 1, 2095.2, NULL, NULL);
INSERT INTO "public"."rmaso_detail" VALUES (33, 13, 10, 1, 1, 2095.2, NULL, NULL);

-- ----------------------------
-- Table structure for servicedetail
-- ----------------------------
DROP TABLE IF EXISTS "public"."servicedetail";
CREATE TABLE "public"."servicedetail" (
  "servicedetailid" int4 NOT NULL DEFAULT nextval('servicedetail_servicedetailid_seq'::regclass),
  "servicemasterid" int4 NOT NULL,
  "productid" int4,
  "quantity" int4,
  "soldprice" float8,
  "amount" float8,
  "totalweight" float8,
  "serialnumber" text COLLATE "pg_catalog"."default",
  "iswarrantly" int4,
  "warrantystartdate" timestamp(6),
  "warrantyenddate" timestamp(6),
  "paymenttype" int4,
  "logmessage" text COLLATE "pg_catalog"."default",
  "createddate" timestamp(6),
  "warehouse" int4
)
;

-- ----------------------------
-- Records of servicedetail
-- ----------------------------
INSERT INTO "public"."servicedetail" VALUES (30, 2, 800, 1, 1000, 2000, 1000, 'Product 003 </br> Serial Number: 123-456-789', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."servicedetail" VALUES (33, 2, 731, 1, 1000, 2000, 1000, 'Product 003 </br> Serial Number: 123-456-789', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."servicedetail" VALUES (34, 2, 501, 1, 1000, 2000, 1000, 'Product 003 </br> Serial Number: 123-456-789', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."servicedetail" VALUES (35, 2, 915, 1, 1000, 2000, 1000, 'Product 003 </br> Serial Number: 123-456-789', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."servicedetail" VALUES (36, 2, 723, 1, 1000, 2000, 1000, 'Product 003 </br> Serial Number: 123-456-789', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."servicedetail" VALUES (37, 2, 281, 1, 1000, 2000, 1000, 'Product 003 </br> Serial Number: 123-456-789', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."servicedetail" VALUES (38, 2, 318, 1, 1000, 2000, 1000, 'Product 003 </br> Serial Number: 123-456-789', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."servicedetail" VALUES (39, 2, 255, 1, 1000, 2000, 1000, 'Product 003 </br> Serial Number: 123-456-789', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."servicedetail" VALUES (40, 2, 373, 1, 1000, 2000, 1000, 'Product 003 </br> Serial Number: 123-456-789', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."servicedetail" VALUES (41, 3, 1, 1, 2095.02, 2095.2, 900, 'Product 02', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."servicedetail" VALUES (42, 3, 2, 1, 2095.02, 2095.2, 900, 'Product 01', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."servicedetail" VALUES (43, 4, 2, 1, 2095.02, 2095.2, 900, 'Product 01', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."servicedetail" VALUES (44, 4, 1, 1, 2095.02, 2095.2, 900, 'Product 02', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."servicedetail" VALUES (45, 5, 1, 1, 2095.02, 2095.2, 900, 'Product 02', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."servicedetail" VALUES (46, 5, 2, 1, 2095.02, 2095.2, 900, 'Product 01', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."servicedetail" VALUES (47, 13, 1, 1, 2095.02, 2095.2, 900, 'Product 02', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."servicedetail" VALUES (48, 13, 2, 1, 2095.02, 2095.2, 900, 'Product 01', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."servicedetail" VALUES (49, 13, 758, 1, 1000, 2000, 1000, 'Product 003 </br> Serial Number: 123-456-789', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for servicemaster
-- ----------------------------
DROP TABLE IF EXISTS "public"."servicemaster";
CREATE TABLE "public"."servicemaster" (
  "servicemasterid" int4 NOT NULL DEFAULT nextval('servicemaster_servicemasterid_seq'::regclass),
  "caseserviceid" int4,
  "subtotal" float8,
  "shippingfee" float8,
  "managerdiscount" float8,
  "total" float8,
  "createddate" timestamp(6),
  "issubmittedproduction" int4,
  "status" int4 NOT NULL DEFAULT 0,
  "warehouse" int4
)
;

-- ----------------------------
-- Records of servicemaster
-- ----------------------------
INSERT INTO "public"."servicemaster" VALUES (1, 8, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO "public"."servicemaster" VALUES (2, 11, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO "public"."servicemaster" VALUES (3, 12, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO "public"."servicemaster" VALUES (4, 3, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO "public"."servicemaster" VALUES (6, 5, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO "public"."servicemaster" VALUES (7, 6, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO "public"."servicemaster" VALUES (5, 4, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO "public"."servicemaster" VALUES (8, 7, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO "public"."servicemaster" VALUES (9, 8, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO "public"."servicemaster" VALUES (12, 11, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO "public"."servicemaster" VALUES (13, 16, NULL, NULL, NULL, NULL, NULL, 1, 2, NULL);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."case_caseid_seq"
OWNED BY "public"."case"."caseid";
SELECT setval('"public"."case_caseid_seq"', 12, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."caseinformation_transactionid_seq"
OWNED BY "public"."caseinformation"."transactionid";
SELECT setval('"public"."caseinformation_transactionid_seq"', 54, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."caselog_logid_seq"
OWNED BY "public"."caselog"."logid";
SELECT setval('"public"."caselog_logid_seq"', 54, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."casemessage_messageid_seq"
OWNED BY "public"."casemessage"."messageid";
SELECT setval('"public"."casemessage_messageid_seq"', 2, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."casereturn_casereturnid_seq"
OWNED BY "public"."casereturn"."casereturnid";
SELECT setval('"public"."casereturn_casereturnid_seq"', 3, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."caseservice_caseserviceid_seq"
OWNED BY "public"."caseservice"."caseserviceid";
SELECT setval('"public"."caseservice_caseserviceid_seq"', 17, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."caseserviceso_caseservicedetailid_seq"
OWNED BY "public"."caseserviceso"."caseservicedetailid";
SELECT setval('"public"."caseserviceso_caseservicedetailid_seq"', 12, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."casetype_casetypeid_seq"
OWNED BY "public"."casetype"."casetypeid";
SELECT setval('"public"."casetype_casetypeid_seq"', 10, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."rma_rmaid_seq"
OWNED BY "public"."rma"."rmaid";
SELECT setval('"public"."rma_rmaid_seq"', 40, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."rmalog_logid_seq"
OWNED BY "public"."rmalog"."logid";
SELECT setval('"public"."rmalog_logid_seq"', 6, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."rmamessage_messageid_seq"
OWNED BY "public"."rmamessage"."messageid";
SELECT setval('"public"."rmamessage_messageid_seq"', 2, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."rmapayment_paymentid_seq"
OWNED BY "public"."rmapayment"."paymentid";
SELECT setval('"public"."rmapayment_paymentid_seq"', 2, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."rmaso_detail_sodetail_id_seq"
OWNED BY "public"."rmaso_detail"."sodetail_id";
SELECT setval('"public"."rmaso_detail_sodetail_id_seq"', 11, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."rmaso_soid_seq"
OWNED BY "public"."rmaso"."soid";
SELECT setval('"public"."rmaso_soid_seq"', 19, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."servicedetail_servicedetailid_seq"
OWNED BY "public"."servicedetail"."servicedetailid";
SELECT setval('"public"."servicedetail_servicedetailid_seq"', 50, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."servicemaster_servicemasterid_seq"
OWNED BY "public"."servicemaster"."servicemasterid";
SELECT setval('"public"."servicemaster_servicemasterid_seq"', 14, true);

-- ----------------------------
-- Primary Key structure for table case
-- ----------------------------
ALTER TABLE "public"."case" ADD CONSTRAINT "case_pkey" PRIMARY KEY ("caseid");

-- ----------------------------
-- Primary Key structure for table caseinformation
-- ----------------------------
ALTER TABLE "public"."caseinformation" ADD CONSTRAINT "caseinformation_pkey" PRIMARY KEY ("transactionid");

-- ----------------------------
-- Primary Key structure for table caselog
-- ----------------------------
ALTER TABLE "public"."caselog" ADD CONSTRAINT "caselog_pkey" PRIMARY KEY ("logid");

-- ----------------------------
-- Primary Key structure for table casemessage
-- ----------------------------
ALTER TABLE "public"."casemessage" ADD CONSTRAINT "casemessage_pkey" PRIMARY KEY ("messageid");

-- ----------------------------
-- Primary Key structure for table casereturn
-- ----------------------------
ALTER TABLE "public"."casereturn" ADD CONSTRAINT "casereturn_pkey" PRIMARY KEY ("casereturnid");

-- ----------------------------
-- Primary Key structure for table caseservice
-- ----------------------------
ALTER TABLE "public"."caseservice" ADD CONSTRAINT "caseservice_pkey" PRIMARY KEY ("caseserviceid");

-- ----------------------------
-- Primary Key structure for table caseserviceso
-- ----------------------------
ALTER TABLE "public"."caseserviceso" ADD CONSTRAINT "caseserviceso_pkey" PRIMARY KEY ("caseservicedetailid");

-- ----------------------------
-- Primary Key structure for table casetype
-- ----------------------------
ALTER TABLE "public"."casetype" ADD CONSTRAINT "casetype_pkey" PRIMARY KEY ("casetypeid");

-- ----------------------------
-- Primary Key structure for table rma
-- ----------------------------
ALTER TABLE "public"."rma" ADD CONSTRAINT "rma_pkey" PRIMARY KEY ("rmaid");

-- ----------------------------
-- Primary Key structure for table rmalog
-- ----------------------------
ALTER TABLE "public"."rmalog" ADD CONSTRAINT "rmalog_pkey" PRIMARY KEY ("logid");

-- ----------------------------
-- Primary Key structure for table rmamessage
-- ----------------------------
ALTER TABLE "public"."rmamessage" ADD CONSTRAINT "rmamessage_pkey" PRIMARY KEY ("messageid");

-- ----------------------------
-- Primary Key structure for table rmapayment
-- ----------------------------
ALTER TABLE "public"."rmapayment" ADD CONSTRAINT "rmapayment_pkey" PRIMARY KEY ("paymentid");

-- ----------------------------
-- Primary Key structure for table rmaso
-- ----------------------------
ALTER TABLE "public"."rmaso" ADD CONSTRAINT "rmaso_pkey" PRIMARY KEY ("soid");

-- ----------------------------
-- Primary Key structure for table rmaso_detail
-- ----------------------------
ALTER TABLE "public"."rmaso_detail" ADD CONSTRAINT "rmaso_detail_pkey" PRIMARY KEY ("sodetail_id");

-- ----------------------------
-- Primary Key structure for table servicedetail
-- ----------------------------
ALTER TABLE "public"."servicedetail" ADD CONSTRAINT "servicedetail_pkey" PRIMARY KEY ("servicedetailid");

-- ----------------------------
-- Primary Key structure for table servicemaster
-- ----------------------------
ALTER TABLE "public"."servicemaster" ADD CONSTRAINT "servicemaster_pkey" PRIMARY KEY ("servicemasterid");

-- ----------------------------
-- Foreign Keys structure for table caseinformation
-- ----------------------------
ALTER TABLE "public"."caseinformation" ADD CONSTRAINT "fk_group_caseinformation_id" FOREIGN KEY ("caseid") REFERENCES "public"."case" ("caseid") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Keys structure for table caselog
-- ----------------------------
ALTER TABLE "public"."caselog" ADD CONSTRAINT "fk_group_caselog_id" FOREIGN KEY ("caseid") REFERENCES "public"."case" ("caseid") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Keys structure for table casemessage
-- ----------------------------
ALTER TABLE "public"."casemessage" ADD CONSTRAINT "fk_group_casemessage_id" FOREIGN KEY ("caseid") REFERENCES "public"."case" ("caseid") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Keys structure for table casereturn
-- ----------------------------
ALTER TABLE "public"."casereturn" ADD CONSTRAINT "fk_casereturn_id" FOREIGN KEY ("caseid") REFERENCES "public"."case" ("caseid") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Keys structure for table caseservice
-- ----------------------------
ALTER TABLE "public"."caseservice" ADD CONSTRAINT "fk_caseservice_id" FOREIGN KEY ("caseid") REFERENCES "public"."case" ("caseid") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Keys structure for table caseserviceso
-- ----------------------------
ALTER TABLE "public"."caseserviceso" ADD CONSTRAINT "fk_caseservice_id2" FOREIGN KEY ("caseserviceid") REFERENCES "public"."caseservice" ("caseserviceid") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Keys structure for table casetype
-- ----------------------------
ALTER TABLE "public"."casetype" ADD CONSTRAINT "fk_case_case_id" FOREIGN KEY ("caseid") REFERENCES "public"."case" ("caseid") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Keys structure for table rma
-- ----------------------------
ALTER TABLE "public"."rma" ADD CONSTRAINT "fk_rma_id" FOREIGN KEY ("caseid") REFERENCES "public"."case" ("caseid") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Keys structure for table rmalog
-- ----------------------------
ALTER TABLE "public"."rmalog" ADD CONSTRAINT "fk_rmalog_id" FOREIGN KEY ("rmaid") REFERENCES "public"."rma" ("rmaid") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Keys structure for table rmamessage
-- ----------------------------
ALTER TABLE "public"."rmamessage" ADD CONSTRAINT "fk_rmamessage_id" FOREIGN KEY ("rmaid") REFERENCES "public"."rma" ("rmaid") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Keys structure for table rmapayment
-- ----------------------------
ALTER TABLE "public"."rmapayment" ADD CONSTRAINT "fk_rmapayment_id" FOREIGN KEY ("rmaid") REFERENCES "public"."rma" ("rmaid") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Keys structure for table rmaso
-- ----------------------------
ALTER TABLE "public"."rmaso" ADD CONSTRAINT "fk_rmaso_id" FOREIGN KEY ("rmaid") REFERENCES "public"."rma" ("rmaid") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Keys structure for table servicedetail
-- ----------------------------
ALTER TABLE "public"."servicedetail" ADD CONSTRAINT "fk_servicedetail_id" FOREIGN KEY ("servicemasterid") REFERENCES "public"."servicemaster" ("servicemasterid") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Keys structure for table servicemaster
-- ----------------------------
ALTER TABLE "public"."servicemaster" ADD CONSTRAINT "fk_servicemaster_case_id" FOREIGN KEY ("caseserviceid") REFERENCES "public"."caseservice" ("caseserviceid") ON DELETE CASCADE ON UPDATE CASCADE;
