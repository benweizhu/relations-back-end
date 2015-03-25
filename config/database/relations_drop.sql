ALTER TABLE relr_kit_allele DROP FOREIGN KEY ka_fk_allele;
ALTER TABLE relr_kit_allele DROP FOREIGN KEY ka_fk_kit;
ALTER TABLE relt_locus_probability DROP FOREIGN KEY rlp_fk_allele;
ALTER TABLE relt_parents DROP FOREIGN KEY rp_fk_case_id;
ALTER TABLE relt_single_parent DROP FOREIGN KEY rsp_fk_case_id;

DROP TABLE relr_kit_allele;
DROP TABLE relt_locus_probability;
DROP TABLE relt_allele;
DROP TABLE relt_kit;
DROP TABLE relt_location;
DROP TABLE relt_single_parent;
DROP TABLE relt_parents;
DROP TABLE relr_user_case;
