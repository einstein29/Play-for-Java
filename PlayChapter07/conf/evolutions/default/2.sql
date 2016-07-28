# --- Initialize tags
# --- !Ups

# --- This ensures that the 3 checkboxes are available.
insert into tag(id,name) values (1, 'lightweight');
insert into tag(id,name) values (2, 'metal');
insert into tag(id,name) values (3, 'plastic');

# --- This populates the product table so that you can see some data at the beginning.
insert into product (id, ean, name, description, date) values (10, '0000000000010', 'Paperclip 10', 'paperclip10', '2016-01-10');
insert into product (id, ean, name, description, date) values (11, '0000000000011', 'Paperclip 11', 'paperclip11', '2016-01-11');
insert into product (id, ean, name, description, date) values (12, '0000000000012', 'Paperclip 12', 'paperclip12', '2016-01-12');
insert into product (id, ean, name, description, date) values (13, '0000000000013', 'Paperclip 13', 'paperclip13', '2016-01-13');
insert into product (id, ean, name, description, date) values (14, '0000000000014', 'Paperclip 14', 'paperclip14', '2016-01-14');
insert into product (id, ean, name, description, date) values (15, '0000000000015', 'Paperclip 15', 'paperclip15', '2016-01-15');
insert into product (id, ean, name, description, date) values (16, '0000000000016', 'Paperclip 16', 'paperclip16', '2016-01-16');
insert into product (id, ean, name, description, date) values (17, '0000000000017', 'Paperclip 17', 'paperclip17', '2016-01-17');
insert into product (id, ean, name, description, date) values (18, '0000000000018', 'Paperclip 18', 'paperclip18', '2016-01-18');
insert into product (id, ean, name, description, date) values (19, '0000000000019', 'Paperclip 19', 'paperclip19', '2016-01-19');
insert into product (id, ean, name, description, date) values (20, '0000000000020', 'Paperclip 20', 'paperclip20', '2016-01-20');
insert into product (id, ean, name, description, date) values (21, '0000000000021', 'Paperclip 21', 'paperclip21', '2016-01-21');
insert into product (id, ean, name, description, date) values (22, '0000000000022', 'Paperclip 22', 'paperclip22', '2016-01-22');
insert into product (id, ean, name, description, date) values (23, '0000000000023', 'Paperclip 23', 'paperclip23', '2016-01-23');
insert into product (id, ean, name, description, date) values (24, '0000000000024', 'Paperclip 24', 'paperclip24', '2016-01-24');
insert into product (id, ean, name, description, date) values (25, '0000000000025', 'Paperclip 25', 'paperclip25', '2016-01-25');
insert into product (id, ean, name, description, date) values (26, '0000000000026', 'Paperclip 26', 'paperclip26', '2016-01-26');
insert into product (id, ean, name, description, date) values (27, '0000000000027', 'Paperclip 27', 'paperclip27', '2016-01-27');
insert into product (id, ean, name, description, date) values (28, '0000000000028', 'Paperclip 28', 'paperclip28', '2016-01-28');
insert into product (id, ean, name, description, date) values (29, '0000000000029', 'Paperclip 29', 'paperclip29', '2016-01-29');
insert into product (id, ean, name, description, date) values (30, '0000000000030', 'Paperclip 30', 'paperclip30', '2016-01-30');
insert into product (id, ean, name, description, date) values (31, '0000000000031', 'Paperclip 31', 'paperclip31', '2016-01-31');
# --- !Downs
SET REFERENTIAL_INTEGRITY FALSE;
delete from tag;