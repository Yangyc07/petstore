package com.yang.petstore.service.Model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class ItemModel implements Serializable{

    private  Integer id;
    //商品名称
    @NotBlank(message = "商品名称不能为空")
    private  String title;
    //商品价格
    @NotNull(message = "商品价格不能为空")
    @Min(value=0,message ="商品价格必须大于0" )
    private BigDecimal price;
    //商品库存
    @NotNull(message = "库存不能不填")
    private  Integer stock;
    //商品描述
    @NotNull(message = "商品描述信息不能不填")
    private  String description;
    //商品销量
    private  Integer sales;
    //商品图片url
    @NotNull(message = "商品图片信息不能不填")
    private String imgUrl;

    //品牌
    @NotNull(message = "商品牌不能不填")
    private String brand;

    //商品分类
    @NotNull(message = "商品分类不能不填")
    private Integer category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "ItemModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", description='" + description + '\'' +
                ", sales=" + sales +
                ", imgUrl='" + imgUrl + '\'' +
                ", brand='" + brand + '\'' +
                ", category=" + category +
                '}';
    }
}
