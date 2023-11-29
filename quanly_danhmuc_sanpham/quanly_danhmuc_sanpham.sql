USE quanly_danhmuc_sanpham;

ALTER TABLE category
    ADD COLUMN status boolean DEFAULT true;

DELIMITER //
CREATE PROCEDURE PROC_CREATE_CATEGORY(
    IN catName VARCHAR(255)
)
BEGIN
    INSERT INTO category(name) values (catName);
end
//

DELIMITER //
CREATE PROCEDURE PROC_UPDATE_CATEGORY(
    IN idUpdate INT,
    IN catName VARCHAR(255),
    IN statusUpdate BOOLEAN
)
BEGIN
    UPDATE category SET name = catName, status = statusUpdate WHERE id = idUpdate;
end
//

DELIMITER //
CREATE PROCEDURE PROC_DELETE_CATEGORY(
    IN idDelete INT
)
BEGIN
    DELETE FROM category WHERE id = idDelete;
end
//

DELIMITER //
CREATE PROCEDURE PROC_CREATE_PRODUCT(
    IN proNameAdd VARCHAR(255),
    IN priceAdd INT,
    IN category_idAdd INT
)
BEGIN
    INSERT INTO PRODUCT (name, price, category_id) values (proNameAdd, priceAdd, category_idAdd);
end
//

DELIMITER //
CREATE PROCEDURE PROC_UPDATE_PRODUCT(
    IN idUpdate INT,
    IN proName VARCHAR(255),
    IN priceUpdate INT,
    IN categoryUpdate INT
)
BEGIN
    UPDATE product SET name = proName, price = priceUpdate, category_id=categoryUpdate WHERE id = idUpdate;
end
//

DELIMITER //
CREATE PROCEDURE PROC_DELETE_PRODUCT(
    IN idDelete INT
)
BEGIN
    DELETE FROM PRODUCT WHERE id = idDelete;
end
//

DELIMITER //
CREATE PROCEDURE PROC_UPDATE_QUANTITY_WHEN_ADD_PRODUCT(
    IN category_id INT
)
BEGIN
    UPDATE category SET quantity_product = quantity_product + 1 WHERE id = category_id;
end
//