
CREATE INDEX index_goods_barcode ON t_goods(barcode(16))
CREATE INDEX index_goods_item_no ON t_goods(item_no(8))


CREATE INDEX index_sales_day_total_goods_item_subno ON t_sales_day_total_goods(item_subno(16))
CREATE INDEX index_sales_day_total_goods_org_id ON t_sales_day_total_goods(org_id(32))
CREATE INDEX index_sales_day_total_goods_opt_date ON t_sales_day_total_goods(opt_date(8))