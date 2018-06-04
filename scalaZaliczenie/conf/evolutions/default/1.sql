# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table lucky_number (
  id                            bigserial not null,
  number                        integer,
  user_id                       bigint,
  constraint pk_lucky_number primary key (id)
);

create table user_db (
  id                            bigserial not null,
  user_name                     varchar(255),
  password                      varchar(255),
  email                         varchar(255),
  constraint pk_user_db primary key (id)
);

alter table lucky_number add constraint fk_lucky_number_user_id foreign key (user_id) references user_db (id) on delete restrict on update restrict;
create index ix_lucky_number_user_id on lucky_number (user_id);


# --- !Downs

alter table if exists lucky_number drop constraint if exists fk_lucky_number_user_id;
drop index if exists ix_lucky_number_user_id;

drop table if exists lucky_number cascade;

drop table if exists user_db cascade;

