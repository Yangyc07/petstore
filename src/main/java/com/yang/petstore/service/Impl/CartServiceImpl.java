package com.yang.petstore.service.Impl;

import com.yang.petstore.controller.ViewObject.CartItemVO;
import com.yang.petstore.controller.ViewObject.CartVO;
import com.yang.petstore.dao.CartDOMapper;
import com.yang.petstore.dao.ItemDOMapper;
import com.yang.petstore.dataobject.CartDO;
import com.yang.petstore.dataobject.ItemDO;
import com.yang.petstore.error.BusinessException;
import com.yang.petstore.error.EmBusinessError;
import com.yang.petstore.service.CartService;
import com.yang.petstore.service.ItemService;
import com.yang.petstore.service.Model.ItemModel;
import com.yang.petstore.service.Model.UserModel;
import com.yang.petstore.service.UserService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDOMapper cartDOMapper;

    @Autowired
    private ItemDOMapper itemDOMapper;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    //将商品添加至购物车

    @Override
    @Transactional
    public boolean addItemToCart(Integer userId, Integer itemId, Integer amount) throws BusinessException {
        //1.校验状态，下单的商品是否存在，用户是否存在，购买数量是否正确
        ItemModel itemModel = itemService.getItemById(itemId);
        if(itemModel == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"商品信息不存在");
        }
        UserModel userModel = userService.getUserById(userId);
        if(userModel == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"用户不存在");
        }
        if(amount <= 0||amount > 99){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"数量不正确");
        }
        //2.判断购物车是否已经添加该商品，如果已经添加过，则更新数据库中的数量和总价
        //如果没有添加，则插入一条数据。
        if(cartDOMapper.selectByUserIdAndItemId(userId,itemId) != null){
            //已经添加过该商品,则更新数量和总价
            cartDOMapper.updateMyCart(userId,amount);
        }
        else {//第一次添加该商品
            CartDO cartDO = new CartDO();
            cartDO.setUserId(userId);
            cartDO.setItemId(itemId);
            cartDO.setQuantity(amount);
            cartDO.setChecked(1);
            cartDO.setCreateTime(DateTime.now().toDate());
            cartDO.setUpdateTime(DateTime.now().toDate());
            cartDOMapper.insert(cartDO);
        }
        return true;
    }

    //查询购物车详细信息
    @Override
    @Transactional
    public CartVO myCart(Integer userId) {
        //1.根据userId关联查询Cart表中的购物车信息
        List<CartDO> cartDOList = cartDOMapper.listCart(userId);
        //2.计算购物车内商品的价格，并将其封装成VO
        List<CartItemVO> cartItemVOList = new ArrayList<CartItemVO>();
        BigDecimal cartTotal = new BigDecimal("0");
        CartVO cartVO = new CartVO();
        for (CartDO cartDO:cartDOList) {
            //设置购物车的详细商品信息
            BigDecimal total = new BigDecimal("0");
            CartItemVO cartItemVO = new CartItemVO();
            cartItemVO.setId(cartDO.getId());
            cartItemVO.setUserId(cartDO.getUserId());
            cartItemVO.setItemId(cartDO.getItemId());
            cartItemVO.setQuantity(cartDO.getQuantity());
            cartItemVO.setChecked(cartDO.getChecked());
            //根据itemId查询商品的信息
            ItemDO itemDO = itemDOMapper.selectByPrimaryKey(cartDO.getItemId());
            cartItemVO.setPrice(itemDO.getPrice());
            cartItemVO.setImg_url(itemDO.getImgUrl());
            cartItemVO.setName(itemDO.getTitle());

            //根据商品是否选中设置购物车的价格信息
            total = itemDO.getPrice().
                    multiply(new BigDecimal(cartDO.getQuantity()).
                    multiply(new BigDecimal(cartDO.getChecked())));
            cartItemVO.setTotal(total);
            //购物车总价格
            cartTotal = cartTotal.add(total);
            //加入购物车
            cartItemVOList.add(cartItemVO);
        }
        //设置CartVO
        cartVO.setCartItemVOList(cartItemVOList);
        cartVO.setCartTotal(cartTotal);
        return cartVO;
    }

    @Override
    public boolean deleteItem(Integer itemId) {
        return cartDOMapper.deleteByItemId(itemId) > 0;
    }
}
