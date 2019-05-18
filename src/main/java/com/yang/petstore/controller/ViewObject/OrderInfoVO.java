package com.yang.petstore.controller.ViewObject;

import com.yang.petstore.dataobject.ItemDO;
import com.yang.petstore.dataobject.UserAddressDO;
import com.yang.petstore.service.Model.ItemModel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderInfoVO {

    //订单号
    private String orderNo;

    //用户账号
    private Integer userId;

    //支付金额
    private BigDecimal payment;

    //支付方式
    private Integer paymentType;

    //运费
    private Integer postage;

    //订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭
    private Integer status;

    //支付时间
    private Date paymentTime;

    //发货时间
    private Date sendTime;

    //交易完成时间
    private Date endTime;

    //交易关闭时间
    private Date closeTime;

    //订单创建时间
    private Date createTime;

    //订单更新时间
    private Date updateTime;

    //商品信息
    private List<ItemModel> itemModelList;


    //地址信息
    private UserAddressDO userAddressDO;

    private Integer amount;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public UserAddressDO getUserAddressDO() {
        return userAddressDO;
    }

    public void setUserAddressDO(UserAddressDO userAddressDO) {
        this.userAddressDO = userAddressDO;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getPostage() {
        return postage;
    }

    public void setPostage(Integer postage) {
        this.postage = postage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<ItemModel> getItemModelList() {
        return itemModelList;
    }

    public void setItemModelList(List<ItemModel> itemModelList) {
        this.itemModelList = itemModelList;
    }
}
