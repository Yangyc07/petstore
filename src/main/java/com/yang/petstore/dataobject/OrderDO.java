package com.yang.petstore.dataobject;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order.id
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order.order_no
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    private String orderNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order.user_id
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order.shipping_id
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    private Integer shippingId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order.payment
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    private BigDecimal payment;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order.payment_type
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    private Integer paymentType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order.postage
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    private Integer postage;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order.status
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order.payment_time
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    private Date paymentTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order.send_time
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    private Date sendTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order.end_time
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    private Date endTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order.close_time
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    private Date closeTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order.create_time
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order.update_time
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order.id
     *
     * @return the value of order.id
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order.id
     *
     * @param id the value for order.id
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order.order_no
     *
     * @return the value of order.order_no
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order.order_no
     *
     * @param orderNo the value for order.order_no
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order.user_id
     *
     * @return the value of order.user_id
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order.user_id
     *
     * @param userId the value for order.user_id
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order.shipping_id
     *
     * @return the value of order.shipping_id
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public Integer getShippingId() {
        return shippingId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order.shipping_id
     *
     * @param shippingId the value for order.shipping_id
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order.payment
     *
     * @return the value of order.payment
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public BigDecimal getPayment() {
        return payment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order.payment
     *
     * @param payment the value for order.payment
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order.payment_type
     *
     * @return the value of order.payment_type
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public Integer getPaymentType() {
        return paymentType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order.payment_type
     *
     * @param paymentType the value for order.payment_type
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order.postage
     *
     * @return the value of order.postage
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public Integer getPostage() {
        return postage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order.postage
     *
     * @param postage the value for order.postage
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public void setPostage(Integer postage) {
        this.postage = postage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order.status
     *
     * @return the value of order.status
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order.status
     *
     * @param status the value for order.status
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order.payment_time
     *
     * @return the value of order.payment_time
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public Date getPaymentTime() {
        return paymentTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order.payment_time
     *
     * @param paymentTime the value for order.payment_time
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order.send_time
     *
     * @return the value of order.send_time
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order.send_time
     *
     * @param sendTime the value for order.send_time
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order.end_time
     *
     * @return the value of order.end_time
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order.end_time
     *
     * @param endTime the value for order.end_time
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order.close_time
     *
     * @return the value of order.close_time
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public Date getCloseTime() {
        return closeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order.close_time
     *
     * @param closeTime the value for order.close_time
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order.create_time
     *
     * @return the value of order.create_time
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order.create_time
     *
     * @param createTime the value for order.create_time
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order.update_time
     *
     * @return the value of order.update_time
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order.update_time
     *
     * @param updateTime the value for order.update_time
     *
     * @mbg.generated Wed May 08 22:13:08 CST 2019
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}