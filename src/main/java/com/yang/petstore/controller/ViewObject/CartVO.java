package com.yang.petstore.controller.ViewObject;

import java.math.BigDecimal;
import java.util.List;

//购物车信息
public class CartVO {

    private List<CartItemVO> cartItemVOList;

    private BigDecimal cartTotal;

    public List<CartItemVO> getCartItemVOList() {
        return cartItemVOList;
    }

    public void setCartItemVOList(List<CartItemVO> cartItemVOList) {
        this.cartItemVOList = cartItemVOList;
    }

    public BigDecimal getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(BigDecimal cartTotal) {
        this.cartTotal = cartTotal;
    }

    @Override
    public String toString() {
        return "CartVO{" +
                "cartItemVOList=" + cartItemVOList +
                ", cartTotal=" + cartTotal +
                '}';
    }
}
