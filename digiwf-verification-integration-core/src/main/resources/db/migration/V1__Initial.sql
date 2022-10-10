create table VERIFICATION (
       ID varchar(36) not null,
        CORRELATION_KEY varchar(255) not null,
        EXPIRY_TIME timestamp,
        PROCESS_INSTANCE_ID varchar(255) not null,
        SUBJECT varchar(255),
        TOKEN varchar(255) not null,
        primary key (ID)
    )

create index IDX_PROCINSTID_CORRKEY on VERIFICATION (PROCESS_INSTANCE_ID, CORRELATION_KEY)

alter table VERIFICATION add constraint UK8alwtjycuu3asfrlsu2nnwqq8 unique (PROCESS_INSTANCE_ID, CORRELATION_KEY)

alter table VERIFICATION add constraint UK_tiglkupondvw9m2u6xe6u9x63 unique (TOKEN)