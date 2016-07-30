
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


ALTER TABLE t_employee_2 ADD (del_flg VARCHAR(1));
UPDATE t_employee_2 SET del_flg = '0';


CREATE TABLE
    t_store_detail_one_day
    (
        uuid INT NOT NULL AUTO_INCREMENT,
        create_date DATETIME NOT NULL,
        create_user_id VARCHAR(32) NOT NULL,
        update_date DATETIME NOT NULL,
        update_user_id VARCHAR(32) NOT NULL,
        version INT NOT NULL,
        bw_branch_no VARCHAR(8),
        _index INT NOT NULL,
        item_barcode VARCHAR(16),
        item_name VARCHAR(128),
        item_sale_amt DECIMAL(19,2),
        item_subno VARCHAR(16),
        opt_date VARCHAR(8) NOT NULL,
        opt_date_m VARCHAR(2),
        opt_date_y VARCHAR(4),
        org_id VARCHAR(32) NOT NULL,
        stock_amt DECIMAL(19,2),
        stock_qty DECIMAL(19,2),
        store_flg VARCHAR(1),
        PRIMARY KEY (uuid),
        CONSTRAINT _index UNIQUE (org_id,opt_date,_index ),
        INDEX index_t_store_detail_one_day_item_barcode (org_id,item_barcode ),
        INDEX index_store_detail_org_id_item_subno ( org_id,item_subno),
        INDEX index_store_detail_opt_date (opt_date)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
    
    
    
    
    
    
    
    
    
    INSERT
INTO
    t_store_detail_one_day
    (
        uuid ,
        create_date ,
        create_user_id ,
        update_date ,
        update_user_id ,
        version ,
        bw_branch_no ,
        _index ,
        item_barcode ,
        item_name ,
        item_sale_amt ,
        item_subno ,
        opt_date ,
        opt_date_m ,
        opt_date_y ,
        org_id ,
        stock_amt ,
        stock_qty ,
        store_flg
    )
SELECT
    uuid ,
    create_date ,
    create_user_id ,
    update_date ,
    update_user_id ,
    version ,
    bw_branch_no ,
    _index ,
    item_barcode ,
    item_name ,
    item_sale_amt ,
    item_subno ,
    opt_date ,
    opt_date_m ,
    opt_date_y ,
    org_id ,
    stock_amt ,
    stock_qty ,
    store_flg
FROM
    t_store_detail
WHERE
    opt_date='20160422'