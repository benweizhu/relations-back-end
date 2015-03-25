ALTER TABLE relt_case DROP FOREIGN KEY case_fk_user;
ALTER TABLE relr_kit_allele DROP FOREIGN KEY ka_fk_allele;
ALTER TABLE relr_kit_allele DROP FOREIGN KEY ka_fk_kit;
ALTER TABLE relt_locus_probability DROP FOREIGN KEY rlp_fk_allele;
ALTER TABLE relr_parents DROP FOREIGN KEY rp_fk_case;
ALTER TABLE relr_single_parent DROP FOREIGN KEY rsp_fk_case;

DROP TABLE relr_kit_allele;
DROP TABLE relr_parents;
DROP TABLE relr_single_parent;
DROP TABLE relt_allele;
DROP TABLE relt_case;
DROP TABLE relt_kit;
DROP TABLE relt_location;
DROP TABLE relt_locus_probability;
DROP TABLE relt_user;