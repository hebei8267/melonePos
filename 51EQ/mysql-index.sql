
CREATE INDEX index_goods_barcode ON t_goods(barcode(16))
CREATE INDEX index_goods_item_no ON t_goods(item_no(8))
CREATE INDEX index_goods_sup_id ON t_goods(supplier_bw_id(16))


CREATE INDEX index_sales_day_total_goods_item_subno ON t_sales_day_total_goods(item_subno(16))
CREATE INDEX index_sales_day_total_goods_org_id ON t_sales_day_total_goods(org_id(32))
CREATE INDEX index_sales_day_total_goods_opt_date ON t_sales_day_total_goods(opt_date(8))

CREATE INDEX index_store_detail_org_id_item_subno ON t_store_detail(item_subno(16),org_id(32))

CREATE INDEX index_store_detail_opt_date ON t_store_detail(opt_date(8))
CREATE INDEX index_store_day_total_opt_date ON t_store_day_total(opt_date(8))

CREATE INDEX index_sales_day_total_item_opt_date ON t_sales_day_total_item(opt_date(8))


CREATE TABLE
    t_employee_2
    (
        uuid INT NOT NULL AUTO_INCREMENT,
        create_date DATETIME NOT NULL,
        create_user_id VARCHAR(32) NOT NULL,
        update_date DATETIME NOT NULL,
        update_user_id VARCHAR(32) NOT NULL,
        version INT NOT NULL,
        account_local VARCHAR(64),
        address VARCHAR(64),
        basic_info_desc_txt VARCHAR(1024),
        contract_exp_time VARCHAR(8),
        education VARCHAR(1),
        emergency_contact VARCHAR(32),
        emergency_phone VARCHAR(16),
        employ_form VARCHAR(1),
        entry_time VARCHAR(8),
        id_card_no VARCHAR(18),
        marital_status VARCHAR(1),
        name VARCHAR(32),
        org_id VARCHAR(32),
        org_trans_record VARCHAR(1024),
        phone VARCHAR(16),
        pos VARCHAR(16),
        pos_adjust_record VARCHAR(1024),
        pos_time VARCHAR(8),
        professional VARCHAR(32),
        renew_time VARCHAR(8),
        sex VARCHAR(1),
        start_work_time VARCHAR(6),
        PRIMARY KEY (uuid)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
    