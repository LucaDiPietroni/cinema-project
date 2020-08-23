SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

COMMENT ON DATABASE "Cinema" IS 'Baza danych dla kina';

CREATE SCHEMA "CinemaMng";


ALTER SCHEMA "CinemaMng" OWNER TO postgres;

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;


CREATE TABLE "CinemaMng"."CinemaHall" (
    id integer NOT NULL,
    number integer
);


ALTER TABLE "CinemaMng"."CinemaHall" OWNER TO postgres;


COMMENT ON TABLE "CinemaMng"."CinemaHall" IS 'Sale kinowe';


CREATE SEQUENCE "CinemaMng"."CinemaHall_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "CinemaMng"."CinemaHall_id_seq" OWNER TO postgres;


ALTER SEQUENCE "CinemaMng"."CinemaHall_id_seq" OWNED BY "CinemaMng"."CinemaHall".id;


CREATE TABLE "CinemaMng"."Film" (
    id integer NOT NULL,
    title text,
    "yearOfPremiere" integer,
    director text,
    "mainRole" text,
    "filmGenre" text,
    scenarist text,
    production text,
    image text
);


ALTER TABLE "CinemaMng"."Film" OWNER TO postgres;

COMMENT ON TABLE "CinemaMng"."Film" IS 'Filmy';


CREATE SEQUENCE "CinemaMng"."Film_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "CinemaMng"."Film_id_seq" OWNER TO postgres;


ALTER SEQUENCE "CinemaMng"."Film_id_seq" OWNED BY "CinemaMng"."Film".id;


CREATE TABLE "CinemaMng"."Reservation" (
    id integer NOT NULL,
    clientname text,
    clientmail text,
    showingid integer,
    token text NOT NULL,
    clientsecondname text
);


ALTER TABLE "CinemaMng"."Reservation" OWNER TO postgres;


COMMENT ON TABLE "CinemaMng"."Reservation" IS 'Rezerwacje w kinie';


CREATE SEQUENCE "CinemaMng"."Reservation_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "CinemaMng"."Reservation_id_seq" OWNER TO postgres;


ALTER SEQUENCE "CinemaMng"."Reservation_id_seq" OWNED BY "CinemaMng"."Reservation".id;

CREATE TABLE "CinemaMng"."ReservedSeat" (
    id integer NOT NULL,
    seatid integer,
    isreduced boolean,
    token text NOT NULL,
    showingid integer NOT NULL
);


ALTER TABLE "CinemaMng"."ReservedSeat" OWNER TO postgres;


COMMENT ON TABLE "CinemaMng"."ReservedSeat" IS 'Zarezerwowane miejsca';


CREATE SEQUENCE "CinemaMng"."ReservedSeat_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "CinemaMng"."ReservedSeat_id_seq" OWNER TO postgres;


ALTER SEQUENCE "CinemaMng"."ReservedSeat_id_seq" OWNED BY "CinemaMng"."ReservedSeat".id;


CREATE TABLE "CinemaMng"."Seat" (
    id integer NOT NULL,
    number integer,
    cinemahallid integer,
    normalprice double precision,
    reducedprice double precision,
    line integer
);


ALTER TABLE "CinemaMng"."Seat" OWNER TO postgres;


COMMENT ON TABLE "CinemaMng"."Seat" IS 'Miejsca w sali kinowej';

CREATE SEQUENCE "CinemaMng"."Seat_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "CinemaMng"."Seat_id_seq" OWNER TO postgres;


ALTER SEQUENCE "CinemaMng"."Seat_id_seq" OWNED BY "CinemaMng"."Seat".id;


CREATE TABLE "CinemaMng"."Showing" (
    id integer NOT NULL,
    filmid integer,
    cinemahallid integer,
    dateofshowing date,
    timeofstart time(6) without time zone
);


ALTER TABLE "CinemaMng"."Showing" OWNER TO postgres;


COMMENT ON TABLE "CinemaMng"."Showing" IS 'Pokazy w kinie';


CREATE SEQUENCE "CinemaMng"."Showing_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "CinemaMng"."Showing_id_seq" OWNER TO postgres;


ALTER SEQUENCE "CinemaMng"."Showing_id_seq" OWNED BY "CinemaMng"."Showing".id;


ALTER TABLE ONLY "CinemaMng"."CinemaHall" ALTER COLUMN id SET DEFAULT nextval('"CinemaMng"."CinemaHall_id_seq"'::regclass);


ALTER TABLE ONLY "CinemaMng"."Film" ALTER COLUMN id SET DEFAULT nextval('"CinemaMng"."Film_id_seq"'::regclass);


ALTER TABLE ONLY "CinemaMng"."Reservation" ALTER COLUMN id SET DEFAULT nextval('"CinemaMng"."Reservation_id_seq"'::regclass);


ALTER TABLE ONLY "CinemaMng"."ReservedSeat" ALTER COLUMN id SET DEFAULT nextval('"CinemaMng"."ReservedSeat_id_seq"'::regclass);


ALTER TABLE ONLY "CinemaMng"."Seat" ALTER COLUMN id SET DEFAULT nextval('"CinemaMng"."Seat_id_seq"'::regclass);


ALTER TABLE ONLY "CinemaMng"."Showing" ALTER COLUMN id SET DEFAULT nextval('"CinemaMng"."Showing_id_seq"'::regclass);


INSERT INTO "CinemaMng"."CinemaHall" VALUES (1, 1);
INSERT INTO "CinemaMng"."CinemaHall" VALUES (2, 2);
INSERT INTO "CinemaMng"."CinemaHall" VALUES (3, 3);
INSERT INTO "CinemaMng"."CinemaHall" VALUES (4, 4);


INSERT INTO "CinemaMng"."Film" VALUES (1, 'Shrek', 2001, 'Andrew Adamson', 'Mike Myers', 'komedia', 'Joe Stillman', 'USA', 'image/shrek.jpg');
INSERT INTO "CinemaMng"."Film" VALUES (2, 'Pulp Fiction', 1994, 'Quentin Tarantino', 'John Travolta', 'gangsterski', 'Quentin Tarantino', 'USA', 'image/PF.png');
INSERT INTO "CinemaMng"."Film" VALUES (3, 'Terminator', 1984, 'James Cameron', 'Arnold Schwarzenegger', 'sci-fi', 'James Cameron', 'USA', 'image/terminator.jpg');
INSERT INTO "CinemaMng"."Film" VALUES (4, 'Asterix i Obelix: Misja Kleopatra', 2002, 'Alain Chabat', 'Monica Belucci', 'komedia', 'Alain Chabat', 'Francja/Niemcy', 'image/asterix.jpg');
INSERT INTO "CinemaMng"."Film" VALUES (5, 'Psy', 1992, 'Władysław Pasikowski', 'Bogusław Linda', 'sensacyjny', 'Władysław Pasikowski', 'Polska', 'image/psy.jpg');
INSERT INTO "CinemaMng"."Film" VALUES (6, 'Dzień Świra', 2002, 'Marek Koterski', 'Marek Kondrat', 'dramat', 'Marek Koterski', 'Polska', 'image/swir.jpg');
INSERT INTO "CinemaMng"."Film" VALUES (7, 'Władca Pierścieni: Drużyna Pierścienia', 2001, 'Peter Jackson', 'Elijah Wood', 'fantasy', 'Peter Jackson', 'Nowa Zelandia/USA', 'image/wladca.jpg');
INSERT INTO "CinemaMng"."Film" VALUES (8, 'Harry Potter i Kamień Filozoficzny', 2001, 'Chris Columbus', 'Daniel Radcliffe', 'fantasy', 'Steve Kloves', 'USA', 'image/harry.jpg');
INSERT INTO "CinemaMng"."Film" VALUES (9, 'Wojna światów', 2005, 'Steven Spielberg', 'Tom Cruise', 'dramat', 'Josh Friedman', 'USA', 'image/wojna.jpg');
INSERT INTO "CinemaMng"."Film" VALUES (10, 'Zaplątani', 2010, 'Nathan Greno', 'Mandy Moore', 'animacja', 'Dan Fogelman', 'USA', 'image/zaplatani.jpg');
INSERT INTO "CinemaMng"."Film" VALUES (11, 'Big Short', 2015, 'Adam McKay', 'Christian Bale', 'dramat', 'Adam McKay', 'USA', 'image/big.jpg');


INSERT INTO "CinemaMng"."Reservation" VALUES (84, 'Marcin', 'mar5011@o2.pl', 1, 'HVPL48IH9Q', 'Pietroń');
INSERT INTO "CinemaMng"."Reservation" VALUES (85, 'Marcin', 'mar5011@o2.pl', 1, 'ABFCT69LJ2', 'Pietroń');
INSERT INTO "CinemaMng"."Reservation" VALUES (86, 'cxgb', 'mar5011@dfg', 1, 'M1JCO5MQA5', 'fdgnnd');
INSERT INTO "CinemaMng"."Reservation" VALUES (87, 'Wojtek', 'woj@woj', 1, '0L59TSQK0G', 'Wojtek');
INSERT INTO "CinemaMng"."Reservation" VALUES (88, 'mar', 'mar@mar', 1, '90UL2JIR1Z', 'mar');
INSERT INTO "CinemaMng"."Reservation" VALUES (89, 'Marcin1111111', 'mar5011@o2.pl', 1, 'QAC1BZ9FG2', 'Pietroń111111');
INSERT INTO "CinemaMng"."Reservation" VALUES (90, '123', '12@3', 1, 'U60HQBO5MO', '123');

INSERT INTO "CinemaMng"."ReservedSeat" VALUES (168, 1, true, 'ABFCT69LJ2', 1);
INSERT INTO "CinemaMng"."ReservedSeat" VALUES (169, 2, false, 'ABFCT69LJ2', 1);
INSERT INTO "CinemaMng"."ReservedSeat" VALUES (170, 3, false, 'ABFCT69LJ2', 1);
INSERT INTO "CinemaMng"."ReservedSeat" VALUES (171, 23, false, 'M1JCO5MQA5', 1);
INSERT INTO "CinemaMng"."ReservedSeat" VALUES (172, 24, false, 'M1JCO5MQA5', 1);
INSERT INTO "CinemaMng"."ReservedSeat" VALUES (173, 25, false, 'M1JCO5MQA5', 1);
INSERT INTO "CinemaMng"."ReservedSeat" VALUES (174, 45, false, '0L59TSQK0G', 1);
INSERT INTO "CinemaMng"."ReservedSeat" VALUES (175, 46, false, '0L59TSQK0G', 1);
INSERT INTO "CinemaMng"."ReservedSeat" VALUES (176, 47, false, '0L59TSQK0G', 1);
INSERT INTO "CinemaMng"."ReservedSeat" VALUES (178, 67, false, 'QAC1BZ9FG2', 1);
INSERT INTO "CinemaMng"."ReservedSeat" VALUES (179, 68, false, 'QAC1BZ9FG2', 1);
INSERT INTO "CinemaMng"."ReservedSeat" VALUES (180, 69, false, 'QAC1BZ9FG2', 1);
INSERT INTO "CinemaMng"."ReservedSeat" VALUES (181, 89, false, 'U60HQBO5MO', 1);
INSERT INTO "CinemaMng"."ReservedSeat" VALUES (182, 90, false, 'U60HQBO5MO', 1);
INSERT INTO "CinemaMng"."ReservedSeat" VALUES (183, 91, false, 'U60HQBO5MO', 1);


INSERT INTO "CinemaMng"."Seat" VALUES (565, 165, 3, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (566, 166, 3, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (567, 167, 3, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (568, 168, 3, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (569, 169, 3, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (570, 170, 3, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (571, 171, 3, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (572, 172, 3, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (573, 173, 3, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (574, 174, 3, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (575, 175, 3, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (576, 176, 3, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (577, 177, 3, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (578, 178, 3, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (579, 179, 3, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (580, 180, 3, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (581, 181, 3, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (582, 182, 3, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (583, 183, 3, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (584, 184, 3, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (585, 185, 3, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (586, 186, 3, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (587, 187, 3, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (588, 188, 3, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (589, 189, 3, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (590, 190, 3, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (591, 191, 3, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (592, 192, 3, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (593, 193, 3, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (594, 194, 3, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (595, 195, 3, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (596, 196, 3, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (597, 197, 3, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (598, 198, 3, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (599, 199, 3, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (600, 200, 3, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (601, 1, 4, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (602, 2, 4, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (603, 3, 4, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (604, 4, 4, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (605, 5, 4, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (606, 6, 4, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (607, 7, 4, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (608, 8, 4, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (609, 9, 4, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (610, 10, 4, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (611, 11, 4, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (612, 12, 4, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (613, 13, 4, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (614, 14, 4, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (615, 15, 4, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (616, 16, 4, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (617, 17, 4, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (618, 18, 4, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (619, 19, 4, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (620, 20, 4, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (621, 21, 4, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (622, 22, 4, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (623, 23, 4, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (624, 24, 4, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (625, 25, 4, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (626, 26, 4, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (627, 27, 4, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (628, 28, 4, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (629, 29, 4, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (630, 30, 4, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (1, 1, 1, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (2, 2, 1, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (3, 3, 1, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (4, 4, 1, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (5, 5, 1, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (6, 6, 1, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (7, 7, 1, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (8, 8, 1, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (9, 9, 1, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (10, 10, 1, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (11, 11, 1, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (12, 12, 1, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (13, 13, 1, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (14, 14, 1, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (15, 15, 1, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (16, 16, 1, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (17, 17, 1, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (18, 18, 1, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (19, 19, 1, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (20, 20, 1, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (21, 21, 1, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (22, 22, 1, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (23, 23, 1, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (24, 24, 1, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (25, 25, 1, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (26, 26, 1, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (27, 27, 1, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (28, 28, 1, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (29, 29, 1, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (30, 30, 1, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (31, 31, 1, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (32, 32, 1, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (33, 33, 1, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (34, 34, 1, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (35, 35, 1, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (36, 36, 1, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (37, 37, 1, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (38, 38, 1, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (39, 39, 1, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (40, 40, 1, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (41, 41, 1, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (42, 42, 1, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (43, 43, 1, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (44, 44, 1, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (45, 45, 1, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (46, 46, 1, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (47, 47, 1, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (48, 48, 1, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (49, 49, 1, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (50, 50, 1, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (51, 51, 1, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (52, 52, 1, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (53, 53, 1, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (54, 54, 1, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (55, 55, 1, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (56, 56, 1, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (57, 57, 1, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (58, 58, 1, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (59, 59, 1, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (60, 60, 1, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (61, 61, 1, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (62, 62, 1, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (63, 63, 1, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (64, 64, 1, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (65, 65, 1, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (66, 66, 1, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (67, 67, 1, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (68, 68, 1, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (69, 69, 1, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (70, 70, 1, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (71, 71, 1, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (72, 72, 1, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (73, 73, 1, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (74, 74, 1, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (75, 75, 1, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (76, 76, 1, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (77, 77, 1, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (78, 78, 1, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (79, 79, 1, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (80, 80, 1, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (81, 81, 1, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (82, 82, 1, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (83, 83, 1, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (84, 84, 1, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (85, 85, 1, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (86, 86, 1, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (87, 87, 1, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (88, 88, 1, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (89, 89, 1, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (90, 90, 1, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (91, 91, 1, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (92, 92, 1, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (93, 93, 1, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (94, 94, 1, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (95, 95, 1, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (96, 96, 1, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (97, 97, 1, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (98, 98, 1, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (99, 99, 1, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (100, 100, 1, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (101, 101, 1, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (102, 102, 1, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (103, 103, 1, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (104, 104, 1, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (105, 105, 1, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (106, 106, 1, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (107, 107, 1, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (108, 108, 1, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (109, 109, 1, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (110, 110, 1, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (111, 111, 1, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (112, 112, 1, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (113, 113, 1, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (114, 114, 1, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (115, 115, 1, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (116, 116, 1, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (117, 117, 1, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (118, 118, 1, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (119, 119, 1, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (120, 120, 1, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (121, 121, 1, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (122, 122, 1, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (123, 123, 1, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (124, 124, 1, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (125, 125, 1, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (126, 126, 1, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (127, 127, 1, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (128, 128, 1, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (129, 129, 1, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (130, 130, 1, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (131, 131, 1, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (132, 132, 1, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (133, 133, 1, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (134, 134, 1, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (135, 135, 1, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (136, 136, 1, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (137, 137, 1, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (138, 138, 1, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (139, 139, 1, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (140, 140, 1, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (141, 141, 1, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (142, 142, 1, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (143, 143, 1, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (144, 144, 1, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (145, 145, 1, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (146, 146, 1, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (147, 147, 1, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (148, 148, 1, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (149, 149, 1, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (150, 150, 1, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (151, 151, 1, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (152, 152, 1, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (153, 153, 1, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (154, 154, 1, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (155, 155, 1, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (156, 156, 1, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (157, 157, 1, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (158, 158, 1, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (159, 159, 1, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (160, 160, 1, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (161, 161, 1, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (162, 162, 1, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (163, 163, 1, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (164, 164, 1, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (165, 165, 1, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (166, 166, 1, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (167, 167, 1, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (168, 168, 1, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (169, 169, 1, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (170, 170, 1, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (171, 171, 1, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (172, 172, 1, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (173, 173, 1, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (174, 174, 1, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (175, 175, 1, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (176, 176, 1, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (177, 177, 1, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (178, 178, 1, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (179, 179, 1, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (180, 180, 1, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (181, 181, 1, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (182, 182, 1, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (183, 183, 1, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (184, 184, 1, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (185, 185, 1, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (186, 186, 1, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (187, 187, 1, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (188, 188, 1, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (189, 189, 1, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (190, 190, 1, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (191, 191, 1, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (192, 192, 1, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (193, 193, 1, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (194, 194, 1, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (195, 195, 1, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (196, 196, 1, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (197, 197, 1, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (198, 198, 1, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (199, 199, 1, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (200, 200, 1, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (201, 1, 2, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (202, 2, 2, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (203, 3, 2, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (204, 4, 2, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (205, 5, 2, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (206, 6, 2, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (207, 7, 2, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (208, 8, 2, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (209, 9, 2, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (210, 10, 2, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (211, 11, 2, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (212, 12, 2, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (213, 13, 2, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (214, 14, 2, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (215, 15, 2, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (216, 16, 2, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (217, 17, 2, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (218, 18, 2, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (219, 19, 2, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (220, 20, 2, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (221, 21, 2, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (222, 22, 2, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (223, 23, 2, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (224, 24, 2, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (225, 25, 2, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (226, 26, 2, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (227, 27, 2, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (228, 28, 2, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (229, 29, 2, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (230, 30, 2, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (231, 31, 2, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (232, 32, 2, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (233, 33, 2, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (234, 34, 2, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (235, 35, 2, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (236, 36, 2, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (237, 37, 2, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (238, 38, 2, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (239, 39, 2, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (240, 40, 2, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (241, 41, 2, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (242, 42, 2, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (243, 43, 2, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (244, 44, 2, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (245, 45, 2, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (246, 46, 2, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (247, 47, 2, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (248, 48, 2, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (249, 49, 2, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (250, 50, 2, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (251, 51, 2, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (252, 52, 2, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (253, 53, 2, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (254, 54, 2, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (255, 55, 2, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (256, 56, 2, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (257, 57, 2, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (258, 58, 2, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (259, 59, 2, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (260, 60, 2, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (261, 61, 2, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (262, 62, 2, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (263, 63, 2, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (264, 64, 2, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (265, 65, 2, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (266, 66, 2, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (267, 67, 2, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (268, 68, 2, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (269, 69, 2, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (270, 70, 2, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (271, 71, 2, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (272, 72, 2, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (273, 73, 2, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (274, 74, 2, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (275, 75, 2, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (276, 76, 2, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (277, 77, 2, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (278, 78, 2, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (279, 79, 2, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (280, 80, 2, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (281, 81, 2, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (282, 82, 2, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (283, 83, 2, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (284, 84, 2, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (285, 85, 2, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (286, 86, 2, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (287, 87, 2, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (288, 88, 2, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (289, 89, 2, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (290, 90, 2, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (291, 91, 2, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (292, 92, 2, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (293, 93, 2, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (294, 94, 2, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (295, 95, 2, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (296, 96, 2, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (297, 97, 2, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (298, 98, 2, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (299, 99, 2, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (300, 100, 2, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (301, 101, 2, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (302, 102, 2, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (303, 103, 2, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (304, 104, 2, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (305, 105, 2, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (306, 106, 2, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (307, 107, 2, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (308, 108, 2, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (309, 109, 2, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (310, 110, 2, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (311, 111, 2, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (312, 112, 2, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (313, 113, 2, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (314, 114, 2, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (315, 115, 2, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (316, 116, 2, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (317, 117, 2, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (318, 118, 2, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (319, 119, 2, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (320, 120, 2, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (321, 121, 2, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (322, 122, 2, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (323, 123, 2, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (324, 124, 2, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (325, 125, 2, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (326, 126, 2, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (327, 127, 2, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (328, 128, 2, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (329, 129, 2, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (330, 130, 2, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (331, 131, 2, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (332, 132, 2, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (333, 133, 2, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (334, 134, 2, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (335, 135, 2, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (336, 136, 2, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (337, 137, 2, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (338, 138, 2, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (339, 139, 2, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (340, 140, 2, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (341, 141, 2, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (342, 142, 2, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (343, 143, 2, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (344, 144, 2, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (345, 145, 2, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (346, 146, 2, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (347, 147, 2, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (348, 148, 2, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (349, 149, 2, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (350, 150, 2, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (351, 151, 2, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (352, 152, 2, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (353, 153, 2, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (354, 154, 2, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (355, 155, 2, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (356, 156, 2, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (357, 157, 2, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (358, 158, 2, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (359, 159, 2, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (360, 160, 2, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (361, 161, 2, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (362, 162, 2, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (363, 163, 2, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (364, 164, 2, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (365, 165, 2, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (366, 166, 2, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (367, 167, 2, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (368, 168, 2, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (369, 169, 2, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (370, 170, 2, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (371, 171, 2, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (372, 172, 2, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (373, 173, 2, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (374, 174, 2, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (375, 175, 2, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (376, 176, 2, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (377, 177, 2, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (378, 178, 2, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (379, 179, 2, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (380, 180, 2, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (381, 181, 2, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (382, 182, 2, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (383, 183, 2, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (384, 184, 2, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (385, 185, 2, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (386, 186, 2, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (387, 187, 2, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (388, 188, 2, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (389, 189, 2, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (390, 190, 2, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (391, 191, 2, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (392, 192, 2, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (393, 193, 2, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (394, 194, 2, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (395, 195, 2, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (396, 196, 2, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (397, 197, 2, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (398, 198, 2, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (399, 199, 2, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (400, 200, 2, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (401, 1, 3, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (402, 2, 3, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (403, 3, 3, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (404, 4, 3, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (405, 5, 3, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (406, 6, 3, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (407, 7, 3, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (408, 8, 3, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (409, 9, 3, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (410, 10, 3, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (411, 11, 3, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (412, 12, 3, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (413, 13, 3, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (414, 14, 3, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (415, 15, 3, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (416, 16, 3, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (417, 17, 3, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (418, 18, 3, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (419, 19, 3, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (420, 20, 3, 25, 12.5, 1);
INSERT INTO "CinemaMng"."Seat" VALUES (421, 21, 3, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (422, 22, 3, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (423, 23, 3, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (424, 24, 3, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (425, 25, 3, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (426, 26, 3, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (427, 27, 3, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (428, 28, 3, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (429, 29, 3, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (430, 30, 3, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (431, 31, 3, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (432, 32, 3, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (433, 33, 3, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (434, 34, 3, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (435, 35, 3, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (436, 36, 3, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (437, 37, 3, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (438, 38, 3, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (439, 39, 3, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (440, 40, 3, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (441, 41, 3, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (442, 42, 3, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (443, 43, 3, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (444, 44, 3, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (445, 45, 3, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (446, 46, 3, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (447, 47, 3, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (448, 48, 3, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (449, 49, 3, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (450, 50, 3, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (451, 51, 3, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (452, 52, 3, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (453, 53, 3, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (454, 54, 3, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (455, 55, 3, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (456, 56, 3, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (457, 57, 3, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (458, 58, 3, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (459, 59, 3, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (460, 60, 3, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (461, 61, 3, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (462, 62, 3, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (463, 63, 3, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (464, 64, 3, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (465, 65, 3, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (466, 66, 3, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (467, 67, 3, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (468, 68, 3, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (469, 69, 3, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (470, 70, 3, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (471, 71, 3, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (472, 72, 3, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (473, 73, 3, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (474, 74, 3, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (475, 75, 3, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (476, 76, 3, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (477, 77, 3, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (478, 78, 3, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (479, 79, 3, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (480, 80, 3, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (481, 81, 3, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (482, 82, 3, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (483, 83, 3, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (484, 84, 3, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (485, 85, 3, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (486, 86, 3, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (487, 87, 3, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (488, 88, 3, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (489, 89, 3, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (490, 90, 3, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (491, 91, 3, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (492, 92, 3, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (493, 93, 3, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (494, 94, 3, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (495, 95, 3, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (496, 96, 3, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (497, 97, 3, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (498, 98, 3, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (499, 99, 3, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (500, 100, 3, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (501, 101, 3, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (502, 102, 3, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (503, 103, 3, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (504, 104, 3, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (505, 105, 3, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (506, 106, 3, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (507, 107, 3, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (508, 108, 3, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (509, 109, 3, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (510, 110, 3, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (511, 111, 3, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (512, 112, 3, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (513, 113, 3, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (514, 114, 3, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (515, 115, 3, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (516, 116, 3, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (517, 117, 3, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (518, 118, 3, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (519, 119, 3, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (520, 120, 3, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (521, 121, 3, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (522, 122, 3, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (523, 123, 3, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (524, 124, 3, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (525, 125, 3, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (526, 126, 3, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (527, 127, 3, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (528, 128, 3, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (529, 129, 3, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (530, 130, 3, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (531, 131, 3, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (532, 132, 3, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (533, 133, 3, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (534, 134, 3, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (535, 135, 3, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (536, 136, 3, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (537, 137, 3, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (538, 138, 3, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (539, 139, 3, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (540, 140, 3, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (541, 141, 3, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (542, 142, 3, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (543, 143, 3, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (544, 144, 3, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (545, 145, 3, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (546, 146, 3, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (547, 147, 3, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (548, 148, 3, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (549, 149, 3, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (550, 150, 3, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (551, 151, 3, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (552, 152, 3, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (553, 153, 3, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (554, 154, 3, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (555, 155, 3, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (556, 156, 3, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (557, 157, 3, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (558, 158, 3, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (559, 159, 3, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (560, 160, 3, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (561, 161, 3, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (562, 162, 3, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (563, 163, 3, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (564, 164, 3, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (631, 31, 4, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (632, 32, 4, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (633, 33, 4, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (634, 34, 4, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (635, 35, 4, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (636, 36, 4, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (637, 37, 4, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (638, 38, 4, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (639, 39, 4, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (640, 40, 4, 25, 12.5, 2);
INSERT INTO "CinemaMng"."Seat" VALUES (641, 41, 4, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (642, 42, 4, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (643, 43, 4, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (644, 44, 4, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (645, 45, 4, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (646, 46, 4, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (647, 47, 4, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (648, 48, 4, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (649, 49, 4, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (650, 50, 4, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (651, 51, 4, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (652, 52, 4, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (653, 53, 4, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (654, 54, 4, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (655, 55, 4, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (656, 56, 4, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (657, 57, 4, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (658, 58, 4, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (659, 59, 4, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (660, 60, 4, 25, 12.5, 3);
INSERT INTO "CinemaMng"."Seat" VALUES (661, 61, 4, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (662, 62, 4, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (663, 63, 4, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (664, 64, 4, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (665, 65, 4, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (666, 66, 4, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (667, 67, 4, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (668, 68, 4, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (669, 69, 4, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (670, 70, 4, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (671, 71, 4, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (672, 72, 4, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (673, 73, 4, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (674, 74, 4, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (675, 75, 4, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (676, 76, 4, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (677, 77, 4, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (678, 78, 4, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (679, 79, 4, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (680, 80, 4, 25, 12.5, 4);
INSERT INTO "CinemaMng"."Seat" VALUES (681, 81, 4, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (682, 82, 4, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (683, 83, 4, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (684, 84, 4, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (685, 85, 4, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (686, 86, 4, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (687, 87, 4, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (688, 88, 4, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (689, 89, 4, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (690, 90, 4, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (691, 91, 4, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (692, 92, 4, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (693, 93, 4, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (694, 94, 4, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (695, 95, 4, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (696, 96, 4, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (697, 97, 4, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (698, 98, 4, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (699, 99, 4, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (700, 100, 4, 25, 12.5, 5);
INSERT INTO "CinemaMng"."Seat" VALUES (701, 101, 4, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (702, 102, 4, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (703, 103, 4, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (704, 104, 4, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (705, 105, 4, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (706, 106, 4, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (707, 107, 4, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (708, 108, 4, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (709, 109, 4, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (710, 110, 4, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (711, 111, 4, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (712, 112, 4, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (713, 113, 4, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (714, 114, 4, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (715, 115, 4, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (716, 116, 4, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (717, 117, 4, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (718, 118, 4, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (719, 119, 4, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (720, 120, 4, 25, 12.5, 6);
INSERT INTO "CinemaMng"."Seat" VALUES (721, 121, 4, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (722, 122, 4, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (723, 123, 4, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (724, 124, 4, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (725, 125, 4, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (726, 126, 4, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (727, 127, 4, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (728, 128, 4, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (729, 129, 4, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (730, 130, 4, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (731, 131, 4, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (732, 132, 4, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (733, 133, 4, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (734, 134, 4, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (735, 135, 4, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (736, 136, 4, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (737, 137, 4, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (738, 138, 4, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (739, 139, 4, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (740, 140, 4, 25, 12.5, 7);
INSERT INTO "CinemaMng"."Seat" VALUES (741, 141, 4, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (742, 142, 4, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (743, 143, 4, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (744, 144, 4, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (745, 145, 4, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (746, 146, 4, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (747, 147, 4, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (748, 148, 4, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (749, 149, 4, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (750, 150, 4, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (751, 151, 4, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (752, 152, 4, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (753, 153, 4, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (754, 154, 4, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (755, 155, 4, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (756, 156, 4, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (757, 157, 4, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (758, 158, 4, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (759, 159, 4, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (760, 160, 4, 25, 12.5, 8);
INSERT INTO "CinemaMng"."Seat" VALUES (761, 161, 4, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (762, 162, 4, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (763, 163, 4, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (764, 164, 4, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (765, 165, 4, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (766, 166, 4, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (767, 167, 4, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (768, 168, 4, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (769, 169, 4, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (770, 170, 4, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (771, 171, 4, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (772, 172, 4, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (773, 173, 4, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (774, 174, 4, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (775, 175, 4, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (776, 176, 4, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (777, 177, 4, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (778, 178, 4, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (779, 179, 4, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (780, 180, 4, 25, 12.5, 9);
INSERT INTO "CinemaMng"."Seat" VALUES (781, 181, 4, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (782, 182, 4, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (783, 183, 4, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (784, 184, 4, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (785, 185, 4, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (786, 186, 4, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (787, 187, 4, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (788, 188, 4, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (789, 189, 4, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (790, 190, 4, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (791, 191, 4, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (792, 192, 4, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (793, 193, 4, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (794, 194, 4, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (795, 195, 4, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (796, 196, 4, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (797, 197, 4, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (798, 198, 4, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (799, 199, 4, 25, 12.5, 10);
INSERT INTO "CinemaMng"."Seat" VALUES (800, 200, 4, 25, 12.5, 10);


INSERT INTO "CinemaMng"."Showing" VALUES (1, 1, 1, '2020-09-07', '10:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (2, 2, 1, '2020-09-07', '14:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (4, 4, 2, '2020-09-07', '10:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (5, 5, 2, '2020-09-07', '14:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (6, 6, 2, '2020-09-07', '18:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (8, 3, 3, '2020-09-07', '14:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (9, 2, 3, '2020-09-07', '18:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (10, 10, 4, '2020-09-07', '10:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (11, 6, 4, '2020-09-07', '14:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (3, 3, 1, '2020-09-07', '18:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (12, 5, 4, '2020-09-07', '18:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (7, 8, 3, '2020-09-07', '10:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (13, 10, 1, '2020-09-08', '10:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (14, 7, 1, '2020-09-08', '14:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (15, 11, 1, '2020-09-08', '18:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (16, 1, 2, '2020-09-08', '10:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (17, 9, 2, '2020-09-08', '14:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (18, 8, 2, '2020-09-08', '18:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (19, 10, 3, '2020-09-08', '10:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (20, 11, 3, '2020-09-08', '14:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (21, 7, 3, '2020-09-08', '18:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (22, 4, 4, '2020-09-08', '10:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (23, 8, 4, '2020-09-08', '14:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (24, 9, 4, '2020-09-08', '18:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (25, 1, 1, '2020-09-09', '10:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (26, 3, 1, '2020-09-09', '14:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (27, 7, 1, '2020-09-09', '18:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (28, 8, 2, '2020-09-09', '10:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (29, 10, 2, '2020-09-09', '14:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (30, 5, 2, '2020-09-09', '18:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (31, 4, 3, '2020-09-09', '10:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (33, 1, 3, '2020-09-09', '14:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (34, 3, 3, '2020-09-09', '18:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (35, 10, 4, '2020-09-09', '10:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (36, 5, 4, '2020-09-09', '14:00:00');
INSERT INTO "CinemaMng"."Showing" VALUES (37, 3, 4, '2020-09-09', '18:00:00');


SELECT pg_catalog.setval('"CinemaMng"."CinemaHall_id_seq"', 1, false);

SELECT pg_catalog.setval('"CinemaMng"."Film_id_seq"', 1, false);

SELECT pg_catalog.setval('"CinemaMng"."Reservation_id_seq"', 91, true);

SELECT pg_catalog.setval('"CinemaMng"."ReservedSeat_id_seq"', 184, true);

SELECT pg_catalog.setval('"CinemaMng"."Seat_id_seq"', 1, false);

SELECT pg_catalog.setval('"CinemaMng"."Showing_id_seq"', 37, true);

ALTER TABLE ONLY "CinemaMng"."CinemaHall"
    ADD CONSTRAINT "CinemaHall_pkey" PRIMARY KEY (id);

ALTER TABLE ONLY "CinemaMng"."Film"
    ADD CONSTRAINT "Film_pkey" PRIMARY KEY (id);

ALTER TABLE ONLY "CinemaMng"."Reservation"
    ADD CONSTRAINT "Reservation_pkey" PRIMARY KEY (id);

ALTER TABLE ONLY "CinemaMng"."ReservedSeat"
    ADD CONSTRAINT "ReservedSeat_pkey" PRIMARY KEY (id);

ALTER TABLE ONLY "CinemaMng"."Seat"
    ADD CONSTRAINT "Seat_pkey" PRIMARY KEY (id);

ALTER TABLE ONLY "CinemaMng"."Showing"
    ADD CONSTRAINT "Showing_pkey" PRIMARY KEY (id);

ALTER TABLE ONLY "CinemaMng"."ReservedSeat"
    ADD CONSTRAINT seatid_showingid_unq UNIQUE (seatid, showingid);

ALTER TABLE ONLY "CinemaMng"."Reservation"
    ADD CONSTRAINT token_unique UNIQUE (token);

CREATE INDEX fki_reservedseat_reservation_fk ON "CinemaMng"."ReservedSeat" USING btree (token);

ALTER TABLE ONLY "CinemaMng"."Reservation"
    ADD CONSTRAINT reservation_showing_fk FOREIGN KEY (showingid) REFERENCES "CinemaMng"."Showing"(id) ON UPDATE SET NULL ON DELETE CASCADE;

ALTER TABLE ONLY "CinemaMng"."ReservedSeat"
    ADD CONSTRAINT reservedseat_reservation_fk FOREIGN KEY (token) REFERENCES "CinemaMng"."Reservation"(token) ON UPDATE SET NULL ON DELETE CASCADE;

ALTER TABLE ONLY "CinemaMng"."ReservedSeat"
    ADD CONSTRAINT reservedseat_seat_fk FOREIGN KEY (id) REFERENCES "CinemaMng"."Seat"(id);

ALTER TABLE ONLY "CinemaMng"."Seat"
    ADD CONSTRAINT seat_hall_fk FOREIGN KEY (cinemahallid) REFERENCES "CinemaMng"."CinemaHall"(id) ON UPDATE SET NULL ON DELETE CASCADE;

ALTER TABLE ONLY "CinemaMng"."Showing"
    ADD CONSTRAINT showing_film_fk FOREIGN KEY (filmid) REFERENCES "CinemaMng"."Film"(id) ON UPDATE SET NULL ON DELETE CASCADE;

ALTER TABLE ONLY "CinemaMng"."Showing"
    ADD CONSTRAINT showing_hall_fk FOREIGN KEY (cinemahallid) REFERENCES "CinemaMng"."CinemaHall"(id) ON UPDATE SET NULL ON DELETE CASCADE;

