package com.yang.petstore.service.Impl;

import com.yang.petstore.dao.CartDOMapper;
import com.yang.petstore.dataobject.CartDO;
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

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDOMapper cartDOMapper;

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
            cartDO.setChecked(0);
            cartDO.setCreateTime(DateTime.now().toDate());
            cartDO.setUpdateTime(DateTime.now().toDate());
            cartDOMapper.insert(cartDO);
        }
        return true;
    }

    @Override
    @Transactional
    public List<ItemModel> myCart(Integer userId) {
        //1.根据userId关联查询Cart表中的购物车信息
        List<CartDO> cartDOList = cartDOMapper.ListCart(userId);

        //2.计算购物车内商品的价格，并将其封装成VO
        return null;
    }
}
