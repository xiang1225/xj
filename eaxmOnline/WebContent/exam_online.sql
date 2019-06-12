prompt PL/SQL Developer import file
prompt Created on 2017��1��12�� by Administrator
set feedback off
set define off
prompt Creating QUESTION...
create table QUESTION
(
  qid    NUMBER not null,
  qtype  NUMBER not null,
  course VARCHAR2(20) not null,
  keya   VARCHAR2(300),
  keyb   VARCHAR2(300),
  keyc   VARCHAR2(300),
  keyd   VARCHAR2(300),
  ques   VARCHAR2(3000) not null,
  answer VARCHAR2(3000) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table QUESTION
  is '���';
comment on column QUESTION.qid
  is '������';
comment on column QUESTION.qtype
  is '�������� 1:��ѡ 2:��ѡ 3:�ж� 4:��� 5:���';
comment on column QUESTION.course
  is '�γ�����';
comment on column QUESTION.keya
  is 'ѡ��A';
comment on column QUESTION.keyb
  is 'ѡ��B';
comment on column QUESTION.keyc
  is 'ѡ��C';
comment on column QUESTION.keyd
  is 'ѡ��D';
comment on column QUESTION.ques
  is '������Ŀ';
comment on column QUESTION.answer
  is '�����';

prompt Creating RECORDTEST...
create table RECORDTEST
(
  userid    VARCHAR2(11) not null,
  course    VARCHAR2(50) not null,
  score     NUMBER not null,
  singlec   NUMBER not null,
  multiplyc NUMBER not null,
  judge     NUMBER not null,
  jd        NUMBER not null,
  program   NUMBER not null,
  test_time VARCHAR2(15)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table RECORDTEST
  is '���Գɼ�';
comment on column RECORDTEST.userid
  is '�û����';
comment on column RECORDTEST.course
  is '���Կγ�';
comment on column RECORDTEST.score
  is '���Է���';
comment on column RECORDTEST.singlec
  is '��ѡ��÷�';
comment on column RECORDTEST.multiplyc
  is '��ѡ��÷�';
comment on column RECORDTEST.judge
  is '�ж���÷�';
comment on column RECORDTEST.jd
  is '�����÷�';
comment on column RECORDTEST.program
  is '�����÷�';

prompt Creating TEST...
create table TEST
(
  userid    VARCHAR2(11),
  qid       NUMBER,
  answer    VARCHAR2(3000),
  course    VARCHAR2(20),
  score     NUMBER,
  qtype     NUMBER,
  tnumber   NUMBER,
  test_time VARCHAR2(15)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table TEST
  is '�Ծ�';
comment on column TEST.userid
  is '�û���ţ�������';
comment on column TEST.qid
  is '��Ŀ���';
comment on column TEST.answer
  is '������';
comment on column TEST.course
  is '���Կγ�';
comment on column TEST.score
  is '����÷�';
comment on column TEST.qtype
  is '��Ŀ����';
comment on column TEST.tnumber
  is '�Ծ�ڼ���';

prompt Creating TEST_SET...
create table TEST_SET
(
  course    VARCHAR2(20) not null,
  sinc      NUMBER not null,
  persin    NUMBER not null,
  mulc      NUMBER not null,
  permul    NUMBER not null,
  judge     NUMBER not null,
  perju     NUMBER not null,
  jd        NUMBER not null,
  perjd     NUMBER not null,
  program   NUMBER not null,
  perpro    NUMBER not null,
  totaltime NUMBER not null,
  test_time VARCHAR2(15)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table TEST_SET
  is '�������ñ�';
comment on column TEST_SET.course
  is '�γ�';
comment on column TEST_SET.sinc
  is '��ѡ����';
comment on column TEST_SET.persin
  is 'ÿ����ѡ���ֵ';
comment on column TEST_SET.mulc
  is '��ѡ����';
comment on column TEST_SET.permul
  is 'ÿ����ѡ���ֵ';
comment on column TEST_SET.judge
  is '�ж�����';
comment on column TEST_SET.perju
  is 'ÿ���ж����ֵ';
comment on column TEST_SET.jd
  is '������';
comment on column TEST_SET.perjd
  is 'ÿ��������ֵ';
comment on column TEST_SET.program
  is '�������';
comment on column TEST_SET.perpro
  is 'ÿ��������ֵ';
comment on column TEST_SET.totaltime
  is '����ʱ��';

prompt Creating USERFLAG...
create table USERFLAG
(
  userid    VARCHAR2(11),
  course    VARCHAR2(20),
  flag      VARCHAR2(1),
  test_time VARCHAR2(15)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table USERFLAG
  is '������Ǳ�';
comment on column USERFLAG.userid
  is '�û����';
comment on column USERFLAG.course
  is '���Կγ�';
comment on column USERFLAG.flag
  is '����״̬ 0:�Ծ�δ�ύ 1:�Ծ����ύ';

prompt Creating USERINFO...
create table USERINFO
(
  userid   VARCHAR2(11),
  utype    NUMBER,
  name     VARCHAR2(50),
  password VARCHAR2(40),
  sex      VARCHAR2(2) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column USERINFO.userid
  is '�û����';
comment on column USERINFO.utype
  is '�û����� 1:ѧ�� 2:��ʦ 3:����Ա';
comment on column USERINFO.name
  is '�û�����';
comment on column USERINFO.password
  is '�û�����';
comment on column USERINFO.sex
  is '�û��Ա�';

prompt Disabling triggers for QUESTION...
alter table QUESTION disable all triggers;
prompt Disabling triggers for RECORDTEST...
alter table RECORDTEST disable all triggers;
prompt Disabling triggers for TEST...
alter table TEST disable all triggers;
prompt Disabling triggers for TEST_SET...
alter table TEST_SET disable all triggers;
prompt Disabling triggers for USERFLAG...
alter table USERFLAG disable all triggers;
prompt Disabling triggers for USERINFO...
alter table USERINFO disable all triggers;
prompt Deleting USERINFO...
delete from USERINFO;
commit;
prompt Deleting USERFLAG...
delete from USERFLAG;
commit;
prompt Deleting TEST_SET...
delete from TEST_SET;
commit;
prompt Deleting TEST...
delete from TEST;
commit;
prompt Deleting RECORDTEST...
delete from RECORDTEST;
commit;
prompt Deleting QUESTION...
delete from QUESTION;
commit;
prompt Loading QUESTION...
prompt Table is empty
prompt Loading RECORDTEST...
prompt Table is empty
prompt Loading TEST...
prompt Table is empty
prompt Loading TEST_SET...
prompt Table is empty
prompt Loading USERFLAG...
prompt Table is empty
prompt Loading USERINFO...
insert into USERINFO (userid, utype, name, password, sex)
values ('admin', 3, '��������Ա', '21232f297a57a5a743894a0e4a801fc3', 'Ů');
commit;
prompt 1 records loaded
prompt Enabling triggers for QUESTION...
alter table QUESTION enable all triggers;
prompt Enabling triggers for RECORDTEST...
alter table RECORDTEST enable all triggers;
prompt Enabling triggers for TEST...
alter table TEST enable all triggers;
prompt Enabling triggers for TEST_SET...
alter table TEST_SET enable all triggers;
prompt Enabling triggers for USERFLAG...
alter table USERFLAG enable all triggers;
prompt Enabling triggers for USERINFO...
alter table USERINFO enable all triggers;
set feedback on
set define on
prompt Done.
