
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

CREATE INDEX index_supplier_supplier_bw_id ON t_supplier(supplier_bw_id)

ALTER TABLE t_cash_run ADD (wx_sale_amt DECIMAL(19,2));
ALTER TABLE t_cash_daily ADD (wx_sale_amt DECIMAL(19,2));
ALTER TABLE t_cash_daily ADD (bw_wx_sale_amt DECIMAL(19,2));


UPDATE t_cash_run SET wx_sale_amt = 0;
UPDATE t_cash_daily SET wx_sale_amt = 0;
UPDATE t_cash_daily SET bw_wx_sale_amt = 0;


ALTER TABLE kafei_new.t_bank_card ADD (account_name VARCHAR(32))







ALTER TABLE
    t_goods ADD (cost_price DECIMAL(19,2));
ALTER TABLE
    t_goods ADD (sale_price DECIMAL(19,2));
    
    
    
ALTER TABLE
    t_account_flow ADD (biz_code VARCHAR(64))