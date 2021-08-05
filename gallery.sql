create table gallery(
no number,
user_no number,
content varchar2(1000),
filePath varchar2(500),
orgName varchar2(200),
saveName varchar2(500),
fileSize number,
primary key(no),
constraint userNo_fk foreign key (user_no)
references users(no)
);

create sequence seq_gallery_no
increment by 1
start with 1
nocache;

insert into gallery
values (seq_gallery_no.nextval,1,'content','filePath','orgName','saveName',1);




commit;