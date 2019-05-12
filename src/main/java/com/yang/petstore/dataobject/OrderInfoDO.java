package com.yang.petstore.dataobject;

import java.math.BigDecimal;
import java.util.Date;

public class OrderInfoDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.id
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.user_id
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.order_no
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    private String orderNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.item_id
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    private Integer itemId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.item_name
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    private String itemName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.item_url
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    private String itemUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.current_unit_price
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    private BigDecimal currentUnitPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.quantity
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    private Integer quantity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.total_price
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    private BigDecimal totalPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.create_time
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.update_time
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.id
     *
     * @return the value of order_info.id
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.id
     *
     * @param id the value for order_info.id
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.user_id
     *
     * @return the value of order_info.user_id
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.user_id
     *
     * @param userId the value for order_info.user_id
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.order_no
     *
     * @return the value of order_info.order_no
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.order_no
     *
     * @param orderNo the value for order_info.order_no
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.item_id
     *
     * @return the value of order_info.item_id
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.item_id
     *
     * @param itemId the value for order_info.item_id
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.item_name
     *
     * @return the value of order_info.item_name
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.item_name
     *
     * @param itemName the value for order_info.item_name
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.item_url
     *
     * @return the value of order_info.item_url
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    public String getItemUrl() {
        return itemUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.item_url
     *
     * @param itemUrl the value for order_info.item_url
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl == null ? null : itemUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.current_unit_price
     *
     * @return the value of order_info.current_unit_price
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    public BigDecimal getCurrentUnitPrice() {
        return currentUnitPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.current_unit_price
     *
     * @param currentUnitPrice the value for order_info.current_unit_price
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    public void setCurrentUnitPrice(BigDecimal currentUnitPrice) {
        this.currentUnitPrice = currentUnitPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.quantity
     *
     * @return the value of order_info.quantity
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.quantity
     *
     * @param quantity the value for order_info.quantity
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.total_price
     *
     * @return the value of order_info.total_price
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.total_price
     *
     * @param totalPrice the value for order_info.total_price
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.create_time
     *
     * @return the value of order_info.create_time
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.create_time
     *
     * @param createTime the value for order_info.create_time
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.update_time
     *
     * @return the value of order_info.update_time
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.update_time
     *
     * @param updateTime the value for order_info.update_time
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}