-- created by vertabelo (http://vertabelo.com)
-- script type: create
-- scope: [tables, references, sequences, views, procedures]
-- generated at thu mar 26 03:56:42 utc 2015




-- tables
-- table relr_kit_locus
CREATE TABLE relr_kit_locus (
  id       INT NOT NULL AUTO_INCREMENT,
  kit_id   INT NOT NULL,
  locus_id INT NOT NULL,
  CONSTRAINT relr_kit_locus_pk PRIMARY KEY (id)
);

-- table relr_parents
CREATE TABLE relr_parents (
  id        INT            NOT NULL AUTO_INCREMENT,
  case_id   INT            NOT NULL,
  allele_id INT            NOT NULL,
  af_1      DECIMAL(10, 5) NOT NULL,
  af_2      DECIMAL(10, 5) NOT NULL,
  m_1       DECIMAL(10, 5) NOT NULL,
  m_2       DECIMAL(10, 5) NOT NULL,
  c_1       DECIMAL(10, 5) NOT NULL,
  c_2       DECIMAL(10, 5) NOT NULL,
  CONSTRAINT relr_parents_pk PRIMARY KEY (id)
);

-- table relr_single_parent
CREATE TABLE relr_single_parent (
  id        INT            NOT NULL AUTO_INCREMENT,
  case_id   INT            NOT NULL,
  allele_id INT            NOT NULL,
  af_1      DECIMAL(10, 5) NOT NULL,
  af_2      DECIMAL(10, 5) NOT NULL,
  c_1       DECIMAL(10, 5) NOT NULL,
  c_2       DECIMAL(10, 5) NOT NULL,
  CONSTRAINT relr_single_parent_pk PRIMARY KEY (id)
);

-- table relt_allele_probability
CREATE TABLE relt_allele_probability (
  id          INT            NOT NULL AUTO_INCREMENT,
  locus_id    INT            NOT NULL,
  allele      DECIMAL(10, 5) NOT NULL,
  probability DECIMAL(10, 7) NOT NULL,
  CONSTRAINT relt_allele_probability_pk PRIMARY KEY (id)
);

-- table relt_case
CREATE TABLE relt_case (
  id      INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  CONSTRAINT relt_case_pk PRIMARY KEY (id)
);

-- table relt_kit
CREATE TABLE relt_kit (
  id   INT         NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  CONSTRAINT relt_kit_pk PRIMARY KEY (id)
);

-- table relt_location
CREATE TABLE relt_location (
  id   INT         NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  CONSTRAINT relt_location_pk PRIMARY KEY (id)
);

-- table relt_locus
CREATE TABLE relt_locus (
  id   INT         NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  CONSTRAINT relt_locus_pk PRIMARY KEY (id)
);

-- table relt_user
CREATE TABLE relt_user (
  id   INT         NOT NULL AUTO_INCREMENT,
  name VARCHAR(10) NOT NULL,
  CONSTRAINT relt_user_pk PRIMARY KEY (id)
);


-- foreign keys
-- reference:  case_fk_user (table: relt_case)


ALTER TABLE relt_case ADD CONSTRAINT case_fk_user FOREIGN KEY case_fk_user (user_id)
REFERENCES relt_user (id);
-- reference:  ka_fk_kit (table: relr_kit_locus)


ALTER TABLE relr_kit_locus ADD CONSTRAINT ka_fk_kit FOREIGN KEY ka_fk_kit (kit_id)
REFERENCES relt_kit (id);
-- reference:  ka_fk_locus (table: relr_kit_locus)


ALTER TABLE relr_kit_locus ADD CONSTRAINT ka_fk_locus FOREIGN KEY ka_fk_locus (locus_id)
REFERENCES relt_locus (id);
-- reference:  rlp_fk_locus (table: relt_allele_probability)


ALTER TABLE relt_allele_probability ADD CONSTRAINT rlp_fk_locus FOREIGN KEY rlp_fk_locus (locus_id)
REFERENCES relt_locus (id);
-- reference:  rp_fk_case (table: relr_parents)


ALTER TABLE relr_parents ADD CONSTRAINT rp_fk_case FOREIGN KEY rp_fk_case (case_id)
REFERENCES relt_case (id);
-- reference:  rsp_fk_case (table: relr_single_parent)


ALTER TABLE relr_single_parent ADD CONSTRAINT rsp_fk_case FOREIGN KEY rsp_fk_case (case_id)
REFERENCES relt_case (id);


-- end of file.

