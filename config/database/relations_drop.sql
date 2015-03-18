alter table relr_kit_allele drop foreign key ka_fk_allele;
alter table relr_kit_allele drop foreign key ka_fk_kit;
alter table relr_loca_alle_locus drop foreign key lal_fk_allele;
alter table relr_loca_alle_locus drop foreign key lal_fk_location;
alter table relr_loca_alle_locus drop foreign key lal_fk_locus;

drop table relr_kit_allele;
drop table relr_loca_alle_locus;
drop table relt_allele;
drop table relt_kit;
drop table relt_location;
drop table relt_locus;

