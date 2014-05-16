
CREATE INDEX index_goods_barcode ON t_goods(barcode(16))
CREATE INDEX index_goods_item_no ON t_goods(item_no(8))
CREATE INDEX index_goods_sup_id ON t_goods(supplier_bw_id(16))


CREATE INDEX index_sales_day_total_goods_item_subno ON t_sales_day_total_goods(item_subno(16))
CREATE INDEX index_sales_day_total_goods_org_id ON t_sales_day_total_goods(org_id(32))
CREATE INDEX index_sales_day_total_goods_opt_date ON t_sales_day_total_goods(opt_date(8))

CREATE INDEX index_store_detail_org_id_item_subno ON t_store_detail(item_subno(16),org_id(32))

CREATE INDEX index_sales_day_total_item_opt_date ON t_sales_day_total_item(opt_date(8))


ALTER TABLE
    t_cash_run ADD (coupon_no VARCHAR(8))
ALTER TABLE
    t_cash_run ADD (coupon_value DECIMAL(19,2))
    ALTER TABLE
    t_cash_daily ADD (coupon_value DECIMAL(19,2))