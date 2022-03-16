alter table if exists Client add column clientNumber serial;
alter table if exists Partner add column deleted boolean not null default false;
create table PartnerIntermediaryNumber (id int8 not null, createdAt timestamp, description varchar(255), number varchar(255), partner_id int8, primary key (id));
alter table if exists PartnerIntermediaryNumber add constraint FK8257r64dlf1ekot457uheh0yo foreign key (partner_id) references Partner;
alter sequence client_clientnumber_seq restart with 10000;
update client set clientNumber = default;