CREATE TABLE relr_kit_allele (
  id        INT NOT NULL AUTO_INCREMENT,
  kit_id    INT NOT NULL,
  allele_id INT NOT NULL,
  CONSTRAINT relr_kit_allele_pk PRIMARY KEY (id)
);

CREATE TABLE relt_locus_probability (
  id          INT            NOT NULL AUTO_INCREMENT,
  allele_id   INT            NOT NULL,
  locus       DECIMAL(10, 5) NOT NULL,
  probability DECIMAL(10, 7) NOT NULL,
  CONSTRAINT relt_locus_probability_pk PRIMARY KEY (id)
);

CREATE TABLE relt_allele (
  id   INT         NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  CONSTRAINT relt_allele_pk PRIMARY KEY (id)
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

CREATE TABLE relr_user_case (
  case_id INT         NOT NULL,
  user    VARCHAR(10) NOT NULL,
  CONSTRAINT relr_user_case_pk PRIMARY KEY (case_id)
);

CREATE TABLE relt_parents (
  id      INT            NOT NULL AUTO_INCREMENT,
  case_id INT            NOT NULL,
  af_1    DECIMAL(10, 5) NOT NULL,
  af_2    DECIMAL(10, 5) NOT NULL,
  m_1     DECIMAL(10, 5) NOT NULL,
  m_2     DECIMAL(10, 5) NOT NULL,
  c_1     DECIMAL(10, 5) NOT NULL,
  c_2     DECIMAL(10, 5) NOT NULL,
  CONSTRAINT relt_parents_pk PRIMARY KEY (id)
);

CREATE TABLE relt_single_parent (
  id      INT            NOT NULL AUTO_INCREMENT,
  case_id INT            NOT NULL,
  af_1    DECIMAL(10, 5) NOT NULL,
  af_2    DECIMAL(10, 5) NOT NULL,
  c_1     DECIMAL(10, 5) NOT NULL,
  c_2     DECIMAL(10, 5) NOT NULL,
  CONSTRAINT relt_single_parent_pk PRIMARY KEY (id)
);

ALTER TABLE relr_kit_allele ADD CONSTRAINT ka_fk_allele FOREIGN KEY ka_fk_allele (allele_id)
REFERENCES relt_allele (id);

ALTER TABLE relr_kit_allele ADD CONSTRAINT ka_fk_kit FOREIGN KEY ka_fk_kit (kit_id)
REFERENCES relt_kit (id);

ALTER TABLE relt_locus_probability ADD CONSTRAINT rlp_fk_allele FOREIGN KEY rlp_fk_allele (allele_id)
REFERENCES relt_allele (id);

ALTER TABLE relt_parents ADD CONSTRAINT rp_fk_case_id FOREIGN KEY rp_fk_case_id (case_id)
REFERENCES relr_user_case (case_id);

ALTER TABLE relt_single_parent ADD CONSTRAINT rsp_fk_case_id FOREIGN KEY rsp_fk_case_id (case_id)
REFERENCES relr_user_case (case_id);

