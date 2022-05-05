create table if not exists shop
(
    id                   bigint        not null auto_increment,
    superior_id          bigint        null,
    tenant_id            bigint        not null,
    created_by           bigint        not null,
    created_at           timestamp(3)  not null,
    last_modified_by     bigint        null,
    last_modified_at     timestamp(3)  null,
    business_no          nvarchar(50)  not null,
    name                 nvarchar(50)  not null,
    business_type_code   tinyint(4)    not null,
    contact_telephone    nvarchar(20),
    contact_cellphone    nvarchar(20),
    contact_name         nvarchar(50),
    contact_address      nvarchar(200),
    management_type_code tinyint(4)    not null,
    opening_hours_start  nvarchar(50)  not null,
    opening_hours_end    nvarchar(50)  not null,
    business_area        nvarchar(20)  not null,
    comment              nvarchar(200) not null,
    enabled              tinyint(1)    not null,
    version              tinyint(10)   not null,
    unique key (`business_no`),
    primary key (`id`)
) default charset = utf8mb4
  collate = utf8mb4_unicode_ci;