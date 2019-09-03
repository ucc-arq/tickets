
    alter table TICKET 
       drop 
       foreign key FK1wyi0a9690wvwhs7y8d7wwaa7;

    alter table TICKET 
       drop 
       foreign key FK7h2oigtx0vhwceqv72dtwks67;

    alter table TRANSICION 
       drop 
       foreign key FKtdqptfktbptgadtgt1uf6esx8;

    drop table if exists TICKET;

    drop table if exists TRANSICION;

    drop table if exists USUARIO;

    create table TICKET (
       ID bigint not null auto_increment,
        ESTADO_TICKET integer not null,
        FECHA_CREACION datetime not null,
        TITULO varchar(600) not null,
        ASIGNADO_ID bigint,
        CREADOR_ID bigint not null,
        primary key (ID)
    ) engine=InnoDB;

    create table TRANSICION (
       ID bigint not null auto_increment,
        COMENTARIO varchar(1000),
        ESTADO_ANTERIOR integer not null,
        ESTADO_NUEVO integer not null,
        FECHA_CREACION datetime not null,
        TICKET_ID bigint not null,
        primary key (ID)
    ) engine=InnoDB;

    create table USUARIO (
       ID bigint not null auto_increment,
        APELLIDO varchar(200) not null,
        NOMBRE varchar(300) not null,
        primary key (ID)
    ) engine=InnoDB;

    alter table TICKET 
       add constraint FK1wyi0a9690wvwhs7y8d7wwaa7 
       foreign key (ASIGNADO_ID) 
       references USUARIO (ID);

    alter table TICKET 
       add constraint FK7h2oigtx0vhwceqv72dtwks67 
       foreign key (CREADOR_ID) 
       references USUARIO (ID);

    alter table TRANSICION 
       add constraint FKtdqptfktbptgadtgt1uf6esx8 
       foreign key (TICKET_ID) 
       references TICKET (ID);
