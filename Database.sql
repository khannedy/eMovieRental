/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     09/04/2008 17:12:25                          */
/*==============================================================*/


drop table if exists AKTIVITAS_OPERATOR;

drop table if exists ANGGOTA;

drop table if exists DIREKTOR;

drop table if exists FILM;

drop table if exists JENIS_FILM;

drop table if exists OPERATOR;

drop table if exists PEMERAN_UTAMA;

drop table if exists PEMINJAMAN;

drop table if exists PENGEMBALIAN;

drop table if exists PESAN;

drop table if exists RUMAH_PRODUKSI;

/*==============================================================*/
/* Table: AKTIVITAS_OPERATOR                                    */
/*==============================================================*/
create table AKTIVITAS_OPERATOR
(
   ID_OPERATOR          varchar(10) not null,
   AKTIVITAS_OPERATOR   text not null,
   WAKTU                timestamp not null
)
type = InnoDB;

/*==============================================================*/
/* Table: ANGGOTA                                               */
/*==============================================================*/
create table ANGGOTA
(
   ID_ANGGOTA           varchar(25) not null,
   NAMA_ANGGOTA         varchar(64) not null,
   TANGGAL_LAHIR_ANGGOTA date not null,
   KONTAK_ANGGOTA       varchar(64) not null,
   ALAMAT_ANGGOTA       text not null,
   TOTAL_MEMINJAM       integer not null,
   primary key (ID_ANGGOTA)
)
type = InnoDB;

/*==============================================================*/
/* Table: DIREKTOR                                              */
/*==============================================================*/
create table DIREKTOR
(
   ID_DIREKTOR          varchar(10) not null,
   NAMA_DIREKTOR        varchar(64) not null,
   primary key (ID_DIREKTOR)
)
type = InnoDB;

/*==============================================================*/
/* Table: FILM                                                  */
/*==============================================================*/
create table FILM
(
   ID_FILM              varchar(10) not null,
   ID_RUMAH_PRODUKSI    varchar(10) not null,
   ID_DIREKTOR          varchar(10) not null,
   ID_PEMERAN_UTAMA     varchar(10) not null,
   ID_JENIS_FILM        varchar(10) not null,
   JUDUL_FILM           varchar(64) not null,
   DIPINJAM             boolean not null,
   TOTAL_DIPINJAM       integer not null,
   primary key (ID_FILM)
)
type = InnoDB;

/*==============================================================*/
/* Table: JENIS_FILM                                            */
/*==============================================================*/
create table JENIS_FILM
(
   ID_JENIS_FILM        varchar(10) not null,
   NAMA_JENIS_FILM      varchar(64) not null,
   primary key (ID_JENIS_FILM)
)
type = InnoDB;

/*==============================================================*/
/* Table: OPERATOR                                              */
/*==============================================================*/
create table OPERATOR
(
   ID_OPERATOR          varchar(10) not null,
   PASSWORD_OPERATOR    varchar(64) not null,
   NAMA_OPERATOR        varchar(64) not null,
   TANGGAL_LAHIR_OPERATOR date not null,
   KONTAK_OPERATOR      varchar(64) not null,
   ALAMAT_OPERATOR      text not null,
   primary key (ID_OPERATOR)
)
type = InnoDB;

/*==============================================================*/
/* Table: PEMERAN_UTAMA                                         */
/*==============================================================*/
create table PEMERAN_UTAMA
(
   ID_PEMERAN_UTAMA     varchar(10) not null,
   NAMA_PEMERAN_UTAMA   varchar(64) not null,
   primary key (ID_PEMERAN_UTAMA)
)
type = InnoDB;

/*==============================================================*/
/* Table: PEMINJAMAN                                            */
/*==============================================================*/
create table PEMINJAMAN
(
   ID_TRANSAKSI         integer not null auto_increment,
   ID_OPERATOR          varchar(10) not null,
   ID_FILM              varchar(10) not null,
   ID_ANGGOTA           varchar(25) not null,
   TANGGAL_PEMINJAMAN   date not null,
   TANGGAL_PENGEMBALIAN date not null,
   TELAH_DIKEMBALIKAN   boolean not null,
   primary key (ID_TRANSAKSI)
)
type = InnoDB;

/*==============================================================*/
/* Table: PENGEMBALIAN                                          */
/*==============================================================*/
create table PENGEMBALIAN
(
   ID_OPERATOR          varchar(10) not null,
   ID_TRANSAKSI         integer not null,
   TANGGAL_PENGEMBALIAN date not null,
   DENDA_KETERLAMBATAN  integer not null,
   TOTAL_KETERLAMBATAN  integer not null,
   primary key (ID_TRANSAKSI)
)
type = InnoDB;

/*==============================================================*/
/* Table: PESAN                                                 */
/*==============================================================*/
create table PESAN
(
   ID_PESAN             integer not null auto_increment,
   ID_OPERATOR_PENGIRIM varchar(10) not null,
   ID_OPERATOR_TUJUAN   varchar(10) not null,
   JUDUL_PESAN          varchar(64) not null,
   ISI_PESAN            text not null,
   WAKTU                timestamp not null,
   primary key (ID_PESAN)
)
type = InnoDB;

/*==============================================================*/
/* Table: RUMAH_PRODUKSI                                        */
/*==============================================================*/
create table RUMAH_PRODUKSI
(
   ID_RUMAH_PRODUKSI    varchar(10) not null,
   NAMA_RUMAH_PRODUKSI  varchar(64) not null,
   primary key (ID_RUMAH_PRODUKSI)
)
type = InnoDB;

alter table AKTIVITAS_OPERATOR add constraint FK_REFERENCE_14 foreign key (ID_OPERATOR)
      references OPERATOR (ID_OPERATOR) on delete restrict on update restrict;

alter table FILM add constraint FK_REFERENCE_1 foreign key (ID_RUMAH_PRODUKSI)
      references RUMAH_PRODUKSI (ID_RUMAH_PRODUKSI) on delete restrict on update restrict;

alter table FILM add constraint FK_REFERENCE_2 foreign key (ID_DIREKTOR)
      references DIREKTOR (ID_DIREKTOR) on delete restrict on update restrict;

alter table FILM add constraint FK_REFERENCE_3 foreign key (ID_PEMERAN_UTAMA)
      references PEMERAN_UTAMA (ID_PEMERAN_UTAMA) on delete restrict on update restrict;

alter table FILM add constraint FK_REFERENCE_4 foreign key (ID_JENIS_FILM)
      references JENIS_FILM (ID_JENIS_FILM) on delete restrict on update restrict;

alter table PEMINJAMAN add constraint FK_REFERENCE_7 foreign key (ID_OPERATOR)
      references OPERATOR (ID_OPERATOR) on delete restrict on update restrict;

alter table PEMINJAMAN add constraint FK_REFERENCE_8 foreign key (ID_FILM)
      references FILM (ID_FILM) on delete restrict on update restrict;

alter table PEMINJAMAN add constraint FK_REFERENCE_9 foreign key (ID_ANGGOTA)
      references ANGGOTA (ID_ANGGOTA) on delete restrict on update restrict;

alter table PENGEMBALIAN add constraint FK_REFERENCE_10 foreign key (ID_OPERATOR)
      references OPERATOR (ID_OPERATOR) on delete restrict on update restrict;

alter table PENGEMBALIAN add constraint FK_REFERENCE_11 foreign key (ID_TRANSAKSI)
      references PEMINJAMAN (ID_TRANSAKSI) on delete restrict on update restrict;

alter table PESAN add constraint FK_REFERENCE_12 foreign key (ID_OPERATOR_PENGIRIM)
      references OPERATOR (ID_OPERATOR) on delete restrict on update restrict;

alter table PESAN add constraint FK_REFERENCE_13 foreign key (ID_OPERATOR_TUJUAN)
      references OPERATOR (ID_OPERATOR) on delete restrict on update restrict;

