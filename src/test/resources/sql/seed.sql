use ecommercetst;

TRUNCATE TABLE products;

INSERT into products(id,name,stock_number,rating,no_of_reviews,list_price,per_off,actual_price,quantity,isrestricted)VALUES
  (1,"MotoG","MOTO123",0,19,10000,0,0,1,1);