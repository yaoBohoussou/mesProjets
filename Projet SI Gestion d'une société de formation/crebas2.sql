/*==============================================================*/
/* Nom de SGBD :  ORACLE Version 10g                            */
/* Date de création :  20/12/2015 21:50:22                      */
/*==============================================================*/


alter table CATALOGUER
   drop constraint FK_CATALOGU_CATALOGUE_FORMATIO;

alter table CATALOGUER
   drop constraint FK_CATALOGU_CATALOGUE_CATALOGU;

alter table CONTENIR
   drop constraint FK_CONTENIR_CONTENIR_SSSESSIO;

alter table CONTENIR
   drop constraint FK_CONTENIR_CONTENIR2_PROGRAMM;

alter table FACTURE
   drop constraint FK_FACTURE_G_REGLER_GROUPE;

alter table FACTURE
   drop constraint FK_FACTURE_REGLER_CLIENT;

alter table GROUPE
   drop constraint FK_GROUPE_BAISSER_REMISE;

alter table GROUPE
   drop constraint FK_GROUPE_ETRE_ENTREPRI;

alter table G_MEMBRE
   drop constraint FK_G_MEMBRE_G_MEMBRE_CLIENT;

alter table G_MEMBRE
   drop constraint FK_G_MEMBRE_G_MEMBRE2_GROUPE;

alter table G_P_PARTICIPER
   drop constraint FK_G_P_PART_G_P_PARTI_GROUPE;

alter table G_P_PARTICIPER
   drop constraint FK_G_P_PART_G_P_PARTI_PROGRAMM;

alter table G_S_PARTICIPER
   drop constraint FK_G_S_PART_G_S_PARTI_GROUPE;

alter table G_S_PARTICIPER
   drop constraint FK_G_S_PART_G_S_PARTI_SSSESSIO;

alter table PROPOSER
   drop constraint FK_PROPOSER_PROPOSER_FORMATIO;

alter table PROPOSER
   drop constraint FK_PROPOSER_PROPOSER2_PARTENAI;

alter table P_FACTURER
   drop constraint FK_P_FACTUR_P_FACTURE_FACTURE;

alter table P_FACTURER
   drop constraint FK_P_FACTUR_P_FACTURE_PROGRAMM;

alter table P_PARTICIPER
   drop constraint FK_P_PARTIC_P_PARTICI_CLIENT;

alter table P_PARTICIPER
   drop constraint FK_P_PARTIC_P_PARTICI_PROGRAMM;

alter table SSSESSION
   drop constraint FK_SSSESSIO_ANIMER_FORMATEU;

alter table SSSESSION
   drop constraint FK_SSSESSIO_FORMER_FORMATIO;

alter table S_FACTURER
   drop constraint FK_S_FACTUR_S_FACTURE_FACTURE;

alter table S_FACTURER
   drop constraint FK_S_FACTUR_S_FACTURE_SSSESSIO;

alter table S_PARTICIPER
   drop constraint FK_S_PARTIC_S_PARTICI_CLIENT;

alter table S_PARTICIPER
   drop constraint FK_S_PARTIC_S_PARTICI_SSSESSIO;

drop table CATALOGUE cascade constraints;

drop index CATALOGUER2_FK;

drop index CATALOGUER_FK;

drop table CATALOGUER cascade constraints;

drop table CLIENT cascade constraints;

drop index CONTENIR2_FK;

drop index CONTENIR_FK;

drop table CONTENIR cascade constraints;

drop table ENTREPRISE cascade constraints;

drop index G_REGLER_FK;

drop index REGLER_FK;

drop table FACTURE cascade constraints;

drop table FORMATEUR cascade constraints;

drop table FORMATION cascade constraints;

drop index BAISSER_FK;

drop index ETRE_FK;

drop table GROUPE cascade constraints;

drop index G_MEMBRE2_FK;

drop index G_MEMBRE_FK;

drop table G_MEMBRE cascade constraints;

drop index G_P_PARTICIPER2_FK;

drop index G_P_PARTICIPER_FK;

drop table G_P_PARTICIPER cascade constraints;

drop index G_S_PARTICIPER2_FK;

drop index G_S_PARTICIPER_FK;

drop table G_S_PARTICIPER cascade constraints;

drop table PARTENAIRE cascade constraints;

drop table PROGRAMME cascade constraints;

drop index PROPOSER2_FK;

drop index PROPOSER_FK;

drop table PROPOSER cascade constraints;

drop index P_FACTURER2_FK;

drop index P_FACTURER_FK;

drop table P_FACTURER cascade constraints;

drop index P_PARTICIPER2_FK;

drop index P_PARTICIPER_FK;

drop table P_PARTICIPER cascade constraints;

drop table REMISE cascade constraints;

drop table RESPONSABLE cascade constraints;

drop index FORMER_FK;

drop index ANIMER_FK;

drop table SSSESSION cascade constraints;

drop index S_FACTURER2_FK;

drop index S_FACTURER_FK;

drop table S_FACTURER cascade constraints;

drop index S_PARTICIPER2_FK;

drop index S_PARTICIPER_FK;

drop table S_PARTICIPER cascade constraints;

/*==============================================================*/
/* Table : CATALOGUE                                            */
/*==============================================================*/
create table CATALOGUE  (
   ID_CATALOGUE         INTEGER                         not null,
   ANNEE_CATALOGUE      INTEGER                         not null,
   constraint PK_CATALOGUE primary key (ID_CATALOGUE)
);

/*==============================================================*/
/* Table : CATALOGUER                                           */
/*==============================================================*/
create table CATALOGUER  (
   ID_FORMATION         INTEGER                         not null,
   ID_CATALOGUE         INTEGER                         not null,
   constraint PK_CATALOGUER primary key (ID_FORMATION, ID_CATALOGUE)
);

/*==============================================================*/
/* Index : CATALOGUER_FK                                        */
/*==============================================================*/
create index CATALOGUER_FK on CATALOGUER (
   ID_FORMATION ASC
);

/*==============================================================*/
/* Index : CATALOGUER2_FK                                       */
/*==============================================================*/
create index CATALOGUER2_FK on CATALOGUER (
   ID_CATALOGUE ASC
);

/*==============================================================*/
/* Table : CLIENT                                               */
/*==============================================================*/
create table CLIENT  (
   ID_CLIENT            INTEGER                         not null,
   NOM_CLIENT           VARCHAR2(70)                    not null,
   PRENOM_CLIENT        VARCHAR2(70)                    not null,
   LOGIN_CLIENT         VARCHAR2(10),
   GENRE_CLIENT         CHAR(1)                         not null,
   CIN_CLIENT           VARCHAR2(20)                    not null,
   DN_CLIENT            DATE                            not null,
   TEL_CLIENT           NUMBER,
   MAIL_CLIENT          VARCHAR2(70),
   PRO_CLIENT           VARCHAR2(20)                    not null,
   MDP_CLIENT           VARCHAR2(10)                    not null,
   constraint PK_CLIENT primary key (ID_CLIENT)
);

/*==============================================================*/
/* Table : CONTENIR                                             */
/*==============================================================*/
create table CONTENIR  (
   ID_SESSION           INTEGER                         not null,
   ID_PROGRAMME         INTEGER                         not null,
   constraint PK_CONTENIR primary key (ID_SESSION, ID_PROGRAMME)
);

/*==============================================================*/
/* Index : CONTENIR_FK                                          */
/*==============================================================*/
create index CONTENIR_FK on CONTENIR (
   ID_SESSION ASC
);

/*==============================================================*/
/* Index : CONTENIR2_FK                                         */
/*==============================================================*/
create index CONTENIR2_FK on CONTENIR (
   ID_PROGRAMME ASC
);

/*==============================================================*/
/* Table : ENTREPRISE                                           */
/*==============================================================*/
create table ENTREPRISE  (
   ID_ENTREPRISE        INTEGER                         not null,
   LOGIN_ENTREPRISE     VARCHAR2(10)                    not null,
   MDP_ENTREPRISE       VARCHAR2(10)                    not null,
   SEC_ACT_ENTREPRISE   VARCHAR2(70)                    not null,
   TEL_ENTREPRISE       NUMBER                          not null,
   MAIL_ENTREPRISE      VARCHAR2(70)                    not null,
   ADR_ENTREPRISE       VARCHAR2(70)                    not null,
   constraint PK_ENTREPRISE primary key (ID_ENTREPRISE)
);

/*==============================================================*/
/* Table : FACTURE                                              */
/*==============================================================*/
create table FACTURE  (
   ID_FACTURE           INTEGER                         not null,
   ID_CLIENT            INTEGER,
   ID_GROUPE            INTEGER,
   DATE_FACTURE         DATE                            not null,
   SAT_FACTURE          VARCHAR2(10)                    not null,
   constraint PK_FACTURE primary key (ID_FACTURE)
);

/*==============================================================*/
/* Index : REGLER_FK                                            */
/*==============================================================*/
create index REGLER_FK on FACTURE (
   ID_CLIENT ASC
);

/*==============================================================*/
/* Index : G_REGLER_FK                                          */
/*==============================================================*/
create index G_REGLER_FK on FACTURE (
   ID_GROUPE ASC
);

/*==============================================================*/
/* Table : FORMATEUR                                            */
/*==============================================================*/
create table FORMATEUR  (
   ID_FORMATEUR         INTEGER                         not null,
   NOM_FORMATEUR        VARCHAR2(70)                    not null,
   PREN_FORMATEUR       VARCHAR2(70),
   LOGIN_FORMATEUR      VARCHAR2(10),
   GENRE_FORMATEUR      CHAR(1)                         not null,
   CIN_FORMATEUR        VARCHAR2(20)                    not null,
   DN_FORMATEUR         DATE                            not null,
   TEL_FORMATEUR        NUMBER                          not null,
   MAIL_FORMATEUR       VARCHAR2(70)                    not null,
   PRO_FORMATEUR        VARCHAR2(20)                    not null,
   MDP_FORMATEUR        VARCHAR2(10)                    not null,
   constraint PK_FORMATEUR primary key (ID_FORMATEUR)
);

/*==============================================================*/
/* Table : FORMATION                                            */
/*==============================================================*/
create table FORMATION  (
   ID_FORMATION         INTEGER                         not null,
   TH_FORMATION         VARCHAR2(70)                    not null,
   DESC_FORMATION       VARCHAR2(70)                    not null,
   constraint PK_FORMATION primary key (ID_FORMATION)
);

/*==============================================================*/
/* Table : GROUPE                                               */
/*==============================================================*/
create table GROUPE  (
   ID_GROUPE            INTEGER                         not null,
   ID_ENTREPRISE        INTEGER,
   ID_REMISE            INTEGER,
   LOGIN_GROUPE         VARCHAR2(10)                    not null,
   MDP_GROUPE           VARCHAR2(10)                    not null,
   constraint PK_GROUPE primary key (ID_GROUPE)
);

/*==============================================================*/
/* Index : ETRE_FK                                              */
/*==============================================================*/
create index ETRE_FK on GROUPE (
   ID_ENTREPRISE ASC
);

/*==============================================================*/
/* Index : BAISSER_FK                                           */
/*==============================================================*/
create index BAISSER_FK on GROUPE (
   ID_REMISE ASC
);

/*==============================================================*/
/* Table : G_MEMBRE                                             */
/*==============================================================*/
create table G_MEMBRE  (
   ID_CLIENT            INTEGER                         not null,
   ID_GROUPE            INTEGER                         not null,
   TYPE_MEMBRE          VARCHAR2(30),
   constraint PK_G_MEMBRE primary key (ID_CLIENT, ID_GROUPE)
);

/*==============================================================*/
/* Index : G_MEMBRE_FK                                          */
/*==============================================================*/
create index G_MEMBRE_FK on G_MEMBRE (
   ID_CLIENT ASC
);

/*==============================================================*/
/* Index : G_MEMBRE2_FK                                         */
/*==============================================================*/
create index G_MEMBRE2_FK on G_MEMBRE (
   ID_GROUPE ASC
);

/*==============================================================*/
/* Table : G_P_PARTICIPER                                       */
/*==============================================================*/
create table G_P_PARTICIPER  (
   ID_GROUPE            INTEGER                         not null,
   ID_PROGRAMME         INTEGER                         not null,
   constraint PK_G_P_PARTICIPER primary key (ID_GROUPE, ID_PROGRAMME)
);

/*==============================================================*/
/* Index : G_P_PARTICIPER_FK                                    */
/*==============================================================*/
create index G_P_PARTICIPER_FK on G_P_PARTICIPER (
   ID_GROUPE ASC
);

/*==============================================================*/
/* Index : G_P_PARTICIPER2_FK                                   */
/*==============================================================*/
create index G_P_PARTICIPER2_FK on G_P_PARTICIPER (
   ID_PROGRAMME ASC
);

/*==============================================================*/
/* Table : G_S_PARTICIPER                                       */
/*==============================================================*/
create table G_S_PARTICIPER  (
   ID_GROUPE            INTEGER                         not null,
   ID_SESSION           INTEGER                         not null,
   constraint PK_G_S_PARTICIPER primary key (ID_GROUPE, ID_SESSION)
);

/*==============================================================*/
/* Index : G_S_PARTICIPER_FK                                    */
/*==============================================================*/
create index G_S_PARTICIPER_FK on G_S_PARTICIPER (
   ID_GROUPE ASC
);

/*==============================================================*/
/* Index : G_S_PARTICIPER2_FK                                   */
/*==============================================================*/
create index G_S_PARTICIPER2_FK on G_S_PARTICIPER (
   ID_SESSION ASC
);

/*==============================================================*/
/* Table : PARTENAIRE                                           */
/*==============================================================*/
create table PARTENAIRE  (
   ID_PARTENAIRE        INTEGER                         not null,
   NOM_PARTENAIRE       VARCHAR2(70)                    not null,
   MAIL_ARTENAIRE       VARCHAR2(70)                    not null,
   TEL_PARTENAIRE       NUMBER                          not null,
   constraint PK_PARTENAIRE primary key (ID_PARTENAIRE)
);

/*==============================================================*/
/* Table : PROGRAMME                                            */
/*==============================================================*/
create table PROGRAMME  (
   ID_PROGRAMME         INTEGER                         not null,
   DATE_PROGRAMME       DATE                            not null,
   DUREE_PROG           INTEGER                         not null,
   constraint PK_PROGRAMME primary key (ID_PROGRAMME)
);

/*==============================================================*/
/* Table : PROPOSER                                             */
/*==============================================================*/
create table PROPOSER  (
   ID_FORMATION         INTEGER                         not null,
   ID_PARTENAIRE        INTEGER                         not null,
   constraint PK_PROPOSER primary key (ID_FORMATION, ID_PARTENAIRE)
);

/*==============================================================*/
/* Index : PROPOSER_FK                                          */
/*==============================================================*/
create index PROPOSER_FK on PROPOSER (
   ID_FORMATION ASC
);

/*==============================================================*/
/* Index : PROPOSER2_FK                                         */
/*==============================================================*/
create index PROPOSER2_FK on PROPOSER (
   ID_PARTENAIRE ASC
);

/*==============================================================*/
/* Table : P_FACTURER                                           */
/*==============================================================*/
create table P_FACTURER  (
   ID_FACTURE           INTEGER                         not null,
   ID_PROGRAMME         INTEGER                         not null,
   constraint PK_P_FACTURER primary key (ID_FACTURE, ID_PROGRAMME)
);

/*==============================================================*/
/* Index : P_FACTURER_FK                                        */
/*==============================================================*/
create index P_FACTURER_FK on P_FACTURER (
   ID_FACTURE ASC
);

/*==============================================================*/
/* Index : P_FACTURER2_FK                                       */
/*==============================================================*/
create index P_FACTURER2_FK on P_FACTURER (
   ID_PROGRAMME ASC
);

/*==============================================================*/
/* Table : P_PARTICIPER                                         */
/*==============================================================*/
create table P_PARTICIPER  (
   ID_CLIENT            INTEGER                         not null,
   ID_PROGRAMME         INTEGER                         not null,
   constraint PK_P_PARTICIPER primary key (ID_CLIENT, ID_PROGRAMME)
);

/*==============================================================*/
/* Index : P_PARTICIPER_FK                                      */
/*==============================================================*/
create index P_PARTICIPER_FK on P_PARTICIPER (
   ID_CLIENT ASC
);

/*==============================================================*/
/* Index : P_PARTICIPER2_FK                                     */
/*==============================================================*/
create index P_PARTICIPER2_FK on P_PARTICIPER (
   ID_PROGRAMME ASC
);

/*==============================================================*/
/* Table : REMISE                                               */
/*==============================================================*/
create table REMISE  (
   ID_REMISE            INTEGER                         not null,
   TAUX_REMISE          FLOAT                           not null,
   DSC_REMISE           VARCHAR2(100)                   not null,
   constraint PK_REMISE primary key (ID_REMISE)
);

/*==============================================================*/
/* Table : RESPONSABLE                                          */
/*==============================================================*/
create table RESPONSABLE  (
   ID_RESPONSABLE       INTEGER                         not null,
   NOM_RESPONSABLE      VARCHAR2(70)                    not null,
   PRENOM_RESONSABLE    VARCHAR2(70)                    not null,
   LOGIN_RESPONSABLE    VARCHAR2(10),
   MDP_REPONSABLE       VARCHAR2(10)                    not null,
   constraint PK_RESPONSABLE primary key (ID_RESPONSABLE)
);

/*==============================================================*/
/* Table : SSSESSION                                            */
/*==============================================================*/
create table SSSESSION  (
   ID_SESSION           INTEGER                         not null,
   ID_FORMATEUR         INTEGER                         not null,
   ID_FORMATION         INTEGER                         not null,
   DATE_DEB_SESSION     DATE                            not null,
   PRIX_SESSION         INTEGER                         not null,
   PLA_SESSION          INTEGER                         not null,
   DUREE_SESSION        INTEGER                         not null,
   PROG_SESSION         VARCHAR2(70)                    not null,
   constraint PK_SSSESSION primary key (ID_SESSION)
);

/*==============================================================*/
/* Index : ANIMER_FK                                            */
/*==============================================================*/
create index ANIMER_FK on SSSESSION (
   ID_FORMATEUR ASC
);

/*==============================================================*/
/* Index : FORMER_FK                                            */
/*==============================================================*/
create index FORMER_FK on SSSESSION (
   ID_FORMATION ASC
);

/*==============================================================*/
/* Table : S_FACTURER                                           */
/*==============================================================*/
create table S_FACTURER  (
   ID_FACTURE           INTEGER                         not null,
   ID_SESSION           INTEGER                         not null,
   constraint PK_S_FACTURER primary key (ID_FACTURE, ID_SESSION)
);

/*==============================================================*/
/* Index : S_FACTURER_FK                                        */
/*==============================================================*/
create index S_FACTURER_FK on S_FACTURER (
   ID_FACTURE ASC
);

/*==============================================================*/
/* Index : S_FACTURER2_FK                                       */
/*==============================================================*/
create index S_FACTURER2_FK on S_FACTURER (
   ID_SESSION ASC
);

/*==============================================================*/
/* Table : S_PARTICIPER                                         */
/*==============================================================*/
create table S_PARTICIPER  (
   ID_CLIENT            INTEGER                         not null,
   ID_SESSION           INTEGER                         not null,
   NOTE_EXAM            FLOAT,
   constraint PK_S_PARTICIPER primary key (ID_CLIENT, ID_SESSION)
);

/*==============================================================*/
/* Index : S_PARTICIPER_FK                                      */
/*==============================================================*/
create index S_PARTICIPER_FK on S_PARTICIPER (
   ID_CLIENT ASC
);

/*==============================================================*/
/* Index : S_PARTICIPER2_FK                                     */
/*==============================================================*/
create index S_PARTICIPER2_FK on S_PARTICIPER (
   ID_SESSION ASC
);

alter table CATALOGUER
   add constraint FK_CATALOGU_CATALOGUE_FORMATIO foreign key (ID_FORMATION)
      references FORMATION (ID_FORMATION);

alter table CATALOGUER
   add constraint FK_CATALOGU_CATALOGUE_CATALOGU foreign key (ID_CATALOGUE)
      references CATALOGUE (ID_CATALOGUE);

alter table CONTENIR
   add constraint FK_CONTENIR_CONTENIR_SSSESSIO foreign key (ID_SESSION)
      references SSSESSION (ID_SESSION);

alter table CONTENIR
   add constraint FK_CONTENIR_CONTENIR2_PROGRAMM foreign key (ID_PROGRAMME)
      references PROGRAMME (ID_PROGRAMME);

alter table FACTURE
   add constraint FK_FACTURE_G_REGLER_GROUPE foreign key (ID_GROUPE)
      references GROUPE (ID_GROUPE);

alter table FACTURE
   add constraint FK_FACTURE_REGLER_CLIENT foreign key (ID_CLIENT)
      references CLIENT (ID_CLIENT);

alter table GROUPE
   add constraint FK_GROUPE_BAISSER_REMISE foreign key (ID_REMISE)
      references REMISE (ID_REMISE);

alter table GROUPE
   add constraint FK_GROUPE_ETRE_ENTREPRI foreign key (ID_ENTREPRISE)
      references ENTREPRISE (ID_ENTREPRISE);

alter table G_MEMBRE
   add constraint FK_G_MEMBRE_G_MEMBRE_CLIENT foreign key (ID_CLIENT)
      references CLIENT (ID_CLIENT);

alter table G_MEMBRE
   add constraint FK_G_MEMBRE_G_MEMBRE2_GROUPE foreign key (ID_GROUPE)
      references GROUPE (ID_GROUPE);

alter table G_P_PARTICIPER
   add constraint FK_G_P_PART_G_P_PARTI_GROUPE foreign key (ID_GROUPE)
      references GROUPE (ID_GROUPE);

alter table G_P_PARTICIPER
   add constraint FK_G_P_PART_G_P_PARTI_PROGRAMM foreign key (ID_PROGRAMME)
      references PROGRAMME (ID_PROGRAMME);

alter table G_S_PARTICIPER
   add constraint FK_G_S_PART_G_S_PARTI_GROUPE foreign key (ID_GROUPE)
      references GROUPE (ID_GROUPE);

alter table G_S_PARTICIPER
   add constraint FK_G_S_PART_G_S_PARTI_SSSESSIO foreign key (ID_SESSION)
      references SSSESSION (ID_SESSION);

alter table PROPOSER
   add constraint FK_PROPOSER_PROPOSER_FORMATIO foreign key (ID_FORMATION)
      references FORMATION (ID_FORMATION);

alter table PROPOSER
   add constraint FK_PROPOSER_PROPOSER2_PARTENAI foreign key (ID_PARTENAIRE)
      references PARTENAIRE (ID_PARTENAIRE);

alter table P_FACTURER
   add constraint FK_P_FACTUR_P_FACTURE_FACTURE foreign key (ID_FACTURE)
      references FACTURE (ID_FACTURE);

alter table P_FACTURER
   add constraint FK_P_FACTUR_P_FACTURE_PROGRAMM foreign key (ID_PROGRAMME)
      references PROGRAMME (ID_PROGRAMME);

alter table P_PARTICIPER
   add constraint FK_P_PARTIC_P_PARTICI_CLIENT foreign key (ID_CLIENT)
      references CLIENT (ID_CLIENT);

alter table P_PARTICIPER
   add constraint FK_P_PARTIC_P_PARTICI_PROGRAMM foreign key (ID_PROGRAMME)
      references PROGRAMME (ID_PROGRAMME);

alter table SSSESSION
   add constraint FK_SSSESSIO_ANIMER_FORMATEU foreign key (ID_FORMATEUR)
      references FORMATEUR (ID_FORMATEUR);

alter table SSSESSION
   add constraint FK_SSSESSIO_FORMER_FORMATIO foreign key (ID_FORMATION)
      references FORMATION (ID_FORMATION);

alter table S_FACTURER
   add constraint FK_S_FACTUR_S_FACTURE_FACTURE foreign key (ID_FACTURE)
      references FACTURE (ID_FACTURE);

alter table S_FACTURER
   add constraint FK_S_FACTUR_S_FACTURE_SSSESSIO foreign key (ID_SESSION)
      references SSSESSION (ID_SESSION);

alter table S_PARTICIPER
   add constraint FK_S_PARTIC_S_PARTICI_CLIENT foreign key (ID_CLIENT)
      references CLIENT (ID_CLIENT);

alter table S_PARTICIPER
   add constraint FK_S_PARTIC_S_PARTICI_SSSESSIO foreign key (ID_SESSION)
      references SSSESSION (ID_SESSION);

