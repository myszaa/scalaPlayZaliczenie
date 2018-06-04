# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user_db (
  id                            bigserial not null,
  user_name                     varchar(255),
  password                      varchar(255),
  constraint pk_user_db primary key (id)
);


# --- !Downs

drop table if exists user_db cascade;

