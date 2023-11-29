package com.quanly_danhmuc_sanpham.model.entity;

public class Category {
    private int categoryId;
    private String categoryName;
    private int quantity_product;
    private boolean status;

    public Category() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getQuantity_product() {
        return quantity_product;
    }

    public void setQuantity_product(int quantity_product) {
        this.quantity_product = quantity_product;
    }

    public Category(int categoryId, String categoryName, int quantity_product, boolean status) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.quantity_product = quantity_product;
        this.status = status;
    }
}
