ALTER TABLE relr_kit_allele DROP FOREIGN KEY ka_fk_allele;
ALTER TABLE relr_kit_allele DROP FOREIGN KEY ka_fk_kit;
ALTER TABLE relr_loca_alle_locus DROP FOREIGN KEY lal_fk_allele;
ALTER TABLE relr_loca_alle_locus DROP FOREIGN KEY lal_fk_location;

DROP TABLE relr_kit_allele;
DROP TABLE relr_loca_alle_locus;
DROP TABLE relt_allele;
DROP TABLE relt_kit;
DROP TABLE relt_location;

