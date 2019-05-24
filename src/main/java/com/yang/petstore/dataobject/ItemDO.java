package com.yang.petstore.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;

public class ItemDO  implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item.id
     *
     * @mbg.generated Mon May 06 10:44:44 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item.title
     *
     * @mbg.generated Mon May 06 10:44:44 CST 2019
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item.price
     *
     * @mbg.generated Mon May 06 10:44:44 CST 2019
     */
    private BigDecimal price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item.decription
     *
     * @mbg.generated Mon May 06 10:44:44 CST 2019
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item.sales
     *
     * @mbg.generated Mon May 06 10:44:44 CST 2019
     */
    private Integer sales;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item.img_url
     *
     * @mbg.generated Mon May 06 10:44:44 CST 2019
     */
    private String imgUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item.brand
     *
     * @mbg.generated Mon May 06 10:44:44 CST 2019
     */
    private String brand;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item.category
     *
     * @mbg.generated Mon May 06 10:44:44 CST 2019
     */
    private Integer category;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item.id
     *
     * @return the value of item.id
     *
     * @mbg.generated Mon May 06 10:44:44 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item.id
     *
     * @param id the value for item.id
     *
     * @mbg.generated Mon May 06 10:44:44 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item.title
     *
     * @return the value of item.title
     *
     * @mbg.generated Mon May 06 10:44:44 CST 2019
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item.title
     *
     * @param title the value for item.title
     *
     * @mbg.generated Mon May 06 10:44:44 CST 2019
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item.price
     *
     * @return the value of item.price
     *
     * @mbg.generated Mon May 06 10:44:44 CST 2019
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item.price
     *
     * @param price the value for item.price
     *
     * @mbg.generated Mon May 06 10:44:44 CST 2019
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item.decription
     *
     * @return the value of item.decription
     *
     * @mbg.generated Mon May 06 10:44:44 CST 2019
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item.decription
     *
     * @param description the value for item.decription
     *
     * @mbg.generated Mon May 06 10:44:44 CST 2019
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item.sales
     *
     * @return the value of item.sales
     *
     * @mbg.generated Mon May 06 10:44:44 CST 2019
     */
    public Integer getSales() {
        return sales;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item.sales
     *
     * @param sales the value for item.sales
     *
     * @mbg.generated Mon May 06 10:44:44 CST 2019
     */
    public void setSales(Integer sales) {
        this.sales = sales;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item.img_url
     *
     * @return the value of item.img_url
     *
     * @mbg.generated Mon May 06 10:44:44 CST 2019
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item.img_url
     *
     * @param imgUrl the value for item.img_url
     *
     * @mbg.generated Mon May 06 10:44:44 CST 2019
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item.brand
     *
     * @return the value of item.brand
     *
     * @mbg.generated Mon May 06 10:44:44 CST 2019
     */
    public String getBrand() {
        return brand;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item.brand
     *
     * @param brand the value for item.brand
     *
     * @mbg.generated Mon May 06 10:44:44 CST 2019
     */
    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item.category
     *
     * @return the value of item.category
     *
     * @mbg.generated Mon May 06 10:44:44 CST 2019
     */
    public Integer getCategory() {
        return category;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item.category
     *
     * @param category the value for item.category
     *
     * @mbg.generated Mon May 06 10:44:44 CST 2019
     */
    public void setCategory(Integer category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ItemDO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", sales=" + sales +
                ", imgUrl='" + imgUrl + '\'' +
                ", brand='" + brand + '\'' +
                ", category=" + category +
                '}';
    }
}