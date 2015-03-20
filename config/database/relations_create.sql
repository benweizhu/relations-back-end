CREATE TABLE relr_allele_locus (
  id        INT NOT NULL AUTO_INCREMENT,
  allele_id INT NOT NULL,
  locus_id  INT NOT NULL,
  CONSTRAINT relr_allele_locus_pk PRIMARY KEY (id)
);

CREATE TABLE relr_kit_allele (
  id        INT NOT NULL AUTO_INCREMENT,
  kit_id    INT NOT NULL,
  allele_id INT NOT NULL,
  CONSTRAINT relr_kit_allele_pk PRIMARY KEY (id)
);

CREATE TABLE relr_loca_alle_locus (
  id          INT            NOT NULL,
  location_id INT            NOT NULL,
  allele_id   INT            NOT NULL,
  locus_id    INT            NOT NULL,
  value       DECIMAL(10, 7) NOT NULL,
  CONSTRAINT relr_loca_alle_locus_pk PRIMARY KEY (id)
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

CREATE TABLE relt_locus (
  id    INT            NOT NULL AUTO_INCREMENT,
  value DECIMAL(10, 5) NOT NULL,
  CONSTRAINT relt_locus_pk PRIMARY KEY (id)
);

ALTER TABLE relr_allele_locus ADD CONSTRAINT al_fk_alele FOREIGN KEY al_fk_alele (allele_id)
REFERENCES relt_allele (id);

ALTER TABLE relr_allele_locus ADD CONSTRAINT al_fk_locus FOREIGN KEY al_fk_locus (locus_id)
REFERENCES relt_locus (id);

ALTER TABLE relr_kit_allele ADD CONSTRAINT ka_fk_allele FOREIGN KEY ka_fk_allele (allele_id)
REFERENCES relt_allele (id);

ALTER TABLE relr_kit_allele ADD CONSTRAINT ka_fk_kit FOREIGN KEY ka_fk_kit (kit_id)
REFERENCES relt_kit (id);

ALTER TABLE relr_loca_alle_locus ADD CONSTRAINT lal_fk_allele FOREIGN KEY lal_fk_allele (allele_id)
REFERENCES relt_allele (id);

ALTER TABLE relr_loca_alle_locus ADD CONSTRAINT lal_fk_location FOREIGN KEY lal_fk_location (location_id)
REFERENCES relt_location (id);

ALTER TABLE relr_loca_alle_locus ADD CONSTRAINT lal_fk_locus FOREIGN KEY lal_fk_locus (locus_id)
REFERENCES relt_locus (id);
