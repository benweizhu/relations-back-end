ALTER TABLE relr_allele_locus DROP FOREIGN KEY al_fk_alele;
ALTER TABLE relr_allele_locus DROP FOREIGN KEY al_fk_locus;
ALTER TABLE relr_kit_allele DROP FOREIGN KEY ka_fk_allele;
ALTER TABLE relr_kit_allele DROP FOREIGN KEY ka_fk_kit;
ALTER TABLE relr_loca_alle_locus DROP FOREIGN KEY lal_fk_allele;
ALTER TABLE relr_loca_alle_locus DROP FOREIGN KEY lal_fk_location;
ALTER TABLE relr_loca_alle_locus DROP FOREIGN KEY lal_fk_locus;

DROP TABLE relr_allele_locus;
DROP TABLE relr_kit_allele;
DROP TABLE relr_loca_alle_locus;
DROP TABLE relt_allele;
DROP TABLE relt_kit;
DROP TABLE relt_location;
DROP TABLE relt_locus;

