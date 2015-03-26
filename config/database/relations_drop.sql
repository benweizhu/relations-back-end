-- created by vertabelo (http://vertabelo.com)
-- script type: drop
-- scope: [tables, references, sequences, views, procedures]
-- generated at thu mar 26 03:57:55 utc 2015



-- foreign keys
ALTER TABLE relt_case DROP FOREIGN KEY case_fk_user;
ALTER TABLE relr_kit_locus DROP FOREIGN KEY ka_fk_kit;
ALTER TABLE relr_kit_locus DROP FOREIGN KEY ka_fk_locus;
ALTER TABLE relt_allele_probability DROP FOREIGN KEY rlp_fk_locus;
ALTER TABLE relr_parents DROP FOREIGN KEY rp_fk_case;
ALTER TABLE relr_single_parent DROP FOREIGN KEY rsp_fk_case;

-- tables
-- table relr_kit_locus
DROP TABLE relr_kit_locus;
-- table relr_parents
DROP TABLE relr_parents;
-- table relr_single_parent
DROP TABLE relr_single_parent;
-- table relt_allele_probability
DROP TABLE relt_allele_probability;
-- table relt_case
DROP TABLE relt_case;
-- table relt_kit
DROP TABLE relt_kit;
-- table relt_location
DROP TABLE relt_location;
-- table relt_locus
DROP TABLE relt_locus;
-- table relt_user
DROP TABLE relt_user;


-- end of file.

