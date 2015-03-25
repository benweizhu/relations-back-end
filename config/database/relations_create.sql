CREATE TABLE relr_kit_allele (
  id        INT NOT NULL AUTO_INCREMENT,
  kit_id    INT NOT NULL,
  allele_id INT NOT NULL,
  CONSTRAINT relr_kit_allele_pk PRIMARY KEY (id)
);

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

CREATE TABLE relt_allele (
  id   INT         NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  CONSTRAINT relt_allele_pk PRIMARY KEY (id)
);

CREATE TABLE relt_case (
  id      INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  CONSTRAINT relt_case_pk PRIMARY KEY (id)
);

CREATE TABLE relt_kit (
  id   INT         NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  CONSTRAINT relt_kit_pk PRIMARY KEY (id)
);

CREATE TABLE relt_location (
  id   INT         NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  CONSTRAINT relt_location_pk PRIMARY KEY (id)
);

CREATE TABLE relt_locus_probability (
  id          INT            NOT NULL AUTO_INCREMENT,
  allele_id   INT            NOT NULL,
  locus       DECIMAL(10, 5) NOT NULL,
  probability DECIMAL(10, 7) NOT NULL,
  CONSTRAINT relt_locus_probability_pk PRIMARY KEY (id)
);

CREATE TABLE relt_user (
  id   INT         NOT NULL AUTO_INCREMENT,
  name VARCHAR(10) NOT NULL,
  CONSTRAINT relt_user_pk PRIMARY KEY (id)
);

ALTER TABLE relt_case ADD CONSTRAINT case_fk_user FOREIGN KEY case_fk_user (user_id)
REFERENCES relt_user (id);

ALTER TABLE relr_kit_allele ADD CONSTRAINT ka_fk_allele FOREIGN KEY ka_fk_allele (allele_id)
REFERENCES relt_allele (id);

ALTER TABLE relr_kit_allele ADD CONSTRAINT ka_fk_kit FOREIGN KEY ka_fk_kit (kit_id)
REFERENCES relt_kit (id);

ALTER TABLE relt_locus_probability ADD CONSTRAINT rlp_fk_allele FOREIGN KEY rlp_fk_allele (allele_id)
REFERENCES relt_allele (id);

ALTER TABLE relr_parents ADD CONSTRAINT rp_fk_case FOREIGN KEY rp_fk_case (case_id)
REFERENCES relt_case (id);

ALTER TABLE relr_single_parent ADD CONSTRAINT rsp_fk_case FOREIGN KEY rsp_fk_case (case_id)
REFERENCES relt_case (id);

