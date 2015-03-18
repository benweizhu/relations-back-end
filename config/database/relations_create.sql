create table relt_kit (
  id   int         not null auto_increment,
  name varchar(20) not null,
  constraint relt_kit_pk primary key (id)
);

create table relt_location (
  id   int         not null auto_increment,
  name varchar(20) not null,
  constraint relt_location_pk primary key (id)
);

create table relt_allele (
  id   int         not null auto_increment,
  name varchar(20) not null,
  constraint relt_allele_pk primary key (id)
);

create table relt_locus (
  id    int            not null auto_increment,
  value decimal(10, 5) not null,
  constraint relt_locus_pk primary key (id)
);

create table relr_kit_allele (
  id        int not null auto_increment,
  kit_id    int not null,
  allele_id int not null,
  constraint relr_kit_allele_pk primary key (id)
);

create table relr_loca_alle_locus (
  id          int            not null auto_increment,
  location_id int            not null,
  allele_id   int            not null,
  locus_id    int            not null,
  value       decimal(10, 7) not null,
  constraint relr_loca_alle_locus_pk primary key (id)
);

alter table relr_kit_allele add constraint ka_fk_allele foreign key ka_fk_allele (allele_id)
references relt_allele (id);

alter table relr_kit_allele add constraint ka_fk_kit foreign key ka_fk_kit (kit_id)
references relt_kit (id);

alter table relr_loca_alle_locus add constraint lal_fk_allele foreign key lal_fk_allele (allele_id)
references relt_allele (id);

alter table relr_loca_alle_locus add constraint lal_fk_location foreign key lal_fk_location (location_id)
references relt_location (id);

alter table relr_loca_alle_locus add constraint lal_fk_locus foreign key lal_fk_locus (locus_id)
references relt_locus (id);

