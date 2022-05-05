-- Row 1
insert into shop ( id
                 , superior_id
                 , tenant_id
                 , created_by
                 , created_at
                 , last_modified_by
                 , last_modified_at
                 , business_no
                 , name
                 , business_type_code
                 , contact_telephone
                 , contact_cellphone
                 , contact_name
                 , contact_address
                 , management_type_code
                 , opening_hours_start
                 , opening_hours_end
                 , business_area
                 , comment
                 , enabled
                 , version)
values ( 100
       , null
       , 500
       , 11000
       , '2021-08-01 11:32'
       , 11000
       , '2021-08-01 12:12'
       , '1234567890'
       , '海棠川菜馆'
       , 10
       , '028-02938102'
       , '15827617283'
       , '刘先生'
       , '汉东市昌平路192号'
       , 10
       , '10:00'
       , '22:00'
       , '80平米'
       , '主营经典川菜系列'
       , 1
       , 2)
on duplicate key update tenant_id = 500;

-- Row 2
insert into shop ( id
                 , superior_id
                 , tenant_id
                 , created_by
                 , created_at
                 , last_modified_by
                 , last_modified_at
                 , business_no
                 , name
                 , business_type_code
                 , contact_telephone
                 , contact_cellphone
                 , contact_name
                 , contact_address
                 , management_type_code
                 , opening_hours_start
                 , opening_hours_end
                 , business_area
                 , comment
                 , enabled
                 , version)
values ( 101
       , 100
       , 500
       , 11000
       , '2021-08-01 11:41'
       , null
       , null
       , '1234567891'
       , '聚源美食'
       , 10
       , '028-87376281'
       , '18627361923'
       , '王女士'
       , '古溪市河沿路287号'
       , 10
       , '11:30'
       , '21:30'
       , '60平米'
       , '家常菜、特色菜'
       , 1
       , 1)
on duplicate key update tenant_id = 500;

-- Row 3
insert into shop ( id
                 , superior_id
                 , tenant_id
                 , created_by
                 , created_at
                 , last_modified_by
                 , last_modified_at
                 , business_no
                 , name
                 , business_type_code
                 , contact_telephone
                 , contact_cellphone
                 , contact_name
                 , contact_address
                 , management_type_code
                 , opening_hours_start
                 , opening_hours_end
                 , business_area
                 , comment
                 , enabled
                 , version)
values ( 102
       , 100
       , 500
       , 11000
       , '2021-08-01 13:18'
       , 11000
       , '2021-08-08 14:21'
       , '1234567892'
       , '皮特西餐馆'
       , 50
       , '028-29128371'
       , '18627361923'
       , '张先生'
       , '古丽市德佳路287号'
       , 20
       , '11:10'
       , '23:30'
       , '110平米'
       , '主营意式西餐，包含下午茶'
       , 1
       , 9)
on duplicate key update tenant_id = 500;

-- Row 4
insert into shop ( id
                 , superior_id
                 , tenant_id
                 , created_by
                 , created_at
                 , last_modified_by
                 , last_modified_at
                 , business_no
                 , name
                 , business_type_code
                 , contact_telephone
                 , contact_cellphone
                 , contact_name
                 , contact_address
                 , management_type_code
                 , opening_hours_start
                 , opening_hours_end
                 , business_area
                 , comment
                 , enabled
                 , version)
values ( 103
       , 100
       , 500
       , 11000
       , '2021-08-02 19:56'
       , 11000
       , '2021-08-05 20:39'
       , '1234567893'
       , '瑞溪火锅'
       , 30
       , '035-37912883'
       , '17382012938'
       , '龙女士'
       , '程饶市官家路89号'
       , 10
       , '11:00'
       , '01:00'
       , '300平米'
       , '主营市井火锅，开放式大场，适合大规模集体聚会'
       , 1
       , 5)
on duplicate key update tenant_id = 500;

-- Row 5
insert into shop ( id
                 , superior_id
                 , tenant_id
                 , created_by
                 , created_at
                 , last_modified_by
                 , last_modified_at
                 , business_no
                 , name
                 , business_type_code
                 , contact_telephone
                 , contact_cellphone
                 , contact_name
                 , contact_address
                 , management_type_code
                 , opening_hours_start
                 , opening_hours_end
                 , business_area
                 , comment
                 , enabled
                 , version)
values ( 104
       , 103
       , 500
       , 11000
       , '2021-08-06 16:23'
       , 11000
       , '2021-08-06 23:12'
       , '1234567894'
       , '巴适西昌烧烤'
       , 40
       , '016-39382736'
       , '13538192837'
       , '赵先生'
       , '府河市熙园路267号'
       , 10
       , '17:00'
       , '03:00'
       , '158平米'
       , '主营市井火锅，开放式大场，适合大规模集体聚会'
       , 1,
         3)
on duplicate key update tenant_id = 500;

-- Row 6
insert into shop ( id
                 , superior_id
                 , tenant_id
                 , created_by
                 , created_at
                 , last_modified_by
                 , last_modified_at
                 , business_no
                 , name
                 , business_type_code
                 , contact_telephone
                 , contact_cellphone
                 , contact_name
                 , contact_address
                 , management_type_code
                 , opening_hours_start
                 , opening_hours_end
                 , business_area
                 , comment
                 , enabled
                 , version)
values ( 105
       , 103
       , 500
       , 11000
       , '2021-08-07 10:23'
       , null
       , null
       , '1234567895'
       , '城市鸭快餐'
       , 20
       , '022-29837128'
       , '15572198273'
       , '陈先生'
       , '三江市吉和路8号'
       , 10
       , '11:30'
       , '19:00'
       , '50平米'
       , '提供健康可口的工作餐'
       , 1
       , 1)
on duplicate key update tenant_id = 500;

-- Row 7
insert into shop ( id
                 , superior_id
                 , tenant_id
                 , created_by
                 , created_at
                 , last_modified_by
                 , last_modified_at
                 , business_no
                 , name
                 , business_type_code
                 , contact_telephone
                 , contact_cellphone
                 , contact_name
                 , contact_address
                 , management_type_code
                 , opening_hours_start
                 , opening_hours_end
                 , business_area
                 , comment
                 , enabled
                 , version)
values ( 106
       , 102
       , 500
       , 11000
       , '2021-08-09 16:21'
       , 11000
       , '2021-08-10 12:21'
       , '1234567896'
       , '莱茵牛排'
       , 50
       , '030-09287179'
       , '13601293801'
       , '许先生'
       , '兆龙市新照路16号'
       , 10
       , '11:00'
       , '22:00'
       , '68平米'
       , '主营欧式精品牛排和沙拉等西式菜肴'
       , 1
       , 4)
on duplicate key update tenant_id = 500;

-- Row 8
insert into shop ( id
                 , superior_id
                 , tenant_id
                 , created_by
                 , created_at
                 , last_modified_by
                 , last_modified_at
                 , business_no
                 , name
                 , business_type_code
                 , contact_telephone
                 , contact_cellphone
                 , contact_name
                 , contact_address
                 , management_type_code
                 , opening_hours_start
                 , opening_hours_end
                 , business_area
                 , comment
                 , enabled
                 , version)
values ( 107
       , 102
       , 500
       , 11000
       , '2021-08-07 18:09'
       , null
       , null
       , '1234567897'
       , '小米女士'
       , 20
       , '019-12093801'
       , '13910928292'
       , '胡女士'
       , '西科市财富路78号'
       , 20
       , '11:00'
       , '20:00'
       , '40平米'
       , '提供小众的精品工作餐'
       , 1
       , 1)
on duplicate key update tenant_id = 500;

-- Row 9
insert into shop ( id
                 , superior_id
                 , tenant_id
                 , created_by
                 , created_at
                 , last_modified_by
                 , last_modified_at
                 , business_no
                 , name
                 , business_type_code
                 , contact_telephone
                 , contact_cellphone
                 , contact_name
                 , contact_address
                 , management_type_code
                 , opening_hours_start
                 , opening_hours_end
                 , business_area
                 , comment
                 , enabled
                 , version)
values ( 108,
         103
       , 500
       , 11000
       , '2021-08-12 19:26'
       , null
       , null
       , '1234567898'
       , '通肠阁'
       , 20
       , '028-38179102'
       , '17329271992'
       , '江女士'
       , '南岳市东升路197号'
       , 20
       , '11:15'
       , '19:45'
       , '112平米'
       , '健康素食的典型代表'
       , 2
       , 1)
on duplicate key update tenant_id = 500;