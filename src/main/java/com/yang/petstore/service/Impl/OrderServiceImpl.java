package com.yang.petstore.service.Impl;
import com.yang.petstore.controller.ViewObject.CartItemVO;
import com.yang.petstore.controller.ViewObject.CartVO;
import com.yang.petstore.controller.ViewObject.OrderVO;
import com.yang.petstore.dao.OrderDOMapper;
import com.yang.petstore.dao.OrderInfoDOMapper;
import com.yang.petstore.dao.SequenceDOMapper;
import com.yang.petstore.dao.UserAddressDOMapper;
import com.yang.petstore.dataobject.*;
import com.yang.petstore.redis.RedisConfiguration;
import com.yang.petstore.service.CartService;
import com.yang.petstore.service.OrderService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    private static final Logger lg = LoggerFactory.getLogger(RedisConfiguration.class);

    @Autowired
    private CartService cartService;

    @Autowired
    private SequenceDOMapper sequenceDOMapper;

    @Autowired
    private OrderInfoDOMapper orderInfoDOMapper;

    @Autowired
    private UserAddressDOMapper userAddressDOMapper;

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Override
    @Transactional
    public OrderVO createOrder(Integer userId, Integer shippingId) {

        //1.获取用户购物车中已经勾选的商品信息，并计算总价格
        CartVO cartVO = cartService.myCart(userId);
        //获取订单总价格
        BigDecimal payment = cartVO.getCartTotal();
        //2.生成Order
        String orderNo = generateOrderNo();
        OrderVO orderVO = this.assembleOrder(userId,shippingId,payment);
        orderVO.setOrderNo(orderNo);
        //3.将数据插入至订单明细表
        List<CartItemVO> cartItemVOList = cartVO.getCartItemVOList();
        for (CartItemVO cartItemVO:cartItemVOList) {
            OrderInfoDO orderInfoDO = new OrderInfoDO();
            orderInfoDO.setOrderNo(orderNo);
            orderInfoDO.setUserId(userId);
            orderInfoDO.setItemId(cartItemVO.getItemId());
            orderInfoDO.setItemName(cartItemVO.getName());
            orderInfoDO.setItemUrl(cartItemVO.getImg_url());
            orderInfoDO.setCurrentUnitPrice(cartItemVO.getPrice());
            orderInfoDO.setQuantity(cartItemVO.getQuantity());
            orderInfoDO.setTotalPrice(cartItemVO.getTotal());
            orderInfoDO.setCreateTime(DateTime.now().toDate());
            orderInfoDO.setUpdateTime(DateTime.now().toDate());
            orderInfoDOMapper.insert(orderInfoDO);
            //将商品移出购物车
            cartService.deleteItem(cartItemVO.getItemId());
        }
        //4.根据Userid查找地址信息
        UserAddressDO userAddressDO  = userAddressDOMapper.selectByUserId(userId).get(0);
        orderVO.setUserAddressDO(userAddressDO);

        //5.将商品放入OrderVO
        orderVO.setCartItemVO(cartItemVOList);

        //6.将数据插入订单表
        OrderDO orderDO = new OrderDO();
        orderDO.setOrderNo(orderNo);
        orderDO.setUserId(userId);
        orderDO.setPayment(payment);
        orderDO.setPostage(0);
        orderDO.setPostage(10);
        orderDO.setCreateTime(DateTime.now().toDate());
        orderDO.setUpdateTime(DateTime.now().toDate());

        lg.info("xxxxxxxxxxxxxxxxx",orderDO.toString());
        orderDOMapper.insert(orderDO);
        return orderVO;
    }

    /**
     * create by: yyc
     * description: 组装Order
     * create time: 2019/5/8 17:34
     *
     * @Param: null  * @Param: null
     */
    private OrderVO assembleOrder(Integer userId,Integer shippingId,BigDecimal payment){
        OrderVO orderVO = new OrderVO();
        //设置订单号，用户id，支付金额

        orderVO.setUserId(userId);
        orderVO.setPayment(payment);

        //运费,状态设置为未支付,订单创建时间
        orderVO.setPostage(0);
        orderVO.setPostage(10);
        orderVO.setCreateTime(DateTime.now().toDate());
        //时间暂未设置,支付时设置
        return orderVO;
    }




    @Transactional(propagation = Propagation.REQUIRES_NEW)//嵌套事务
     String generateOrderNo(){
        //订单号16位
        StringBuilder stringBuilder = new StringBuilder();
        //前八位为时间信息，年月日
        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.BASIC_ISO_DATE).replace("-","");
        stringBuilder.append(nowDate);
        //中间六位为自增序列(可能会超出六位)
        //获取当前sequence
        int sequence = 0;
        SequenceDO sequenceDO = sequenceDOMapper.getSequenceByName("order_info");
        sequence = sequenceDO.getCurrentValue();
        sequenceDO.setCurrentValue(sequenceDO.getCurrentValue() + sequenceDO.getStep());
        sequenceDOMapper.updateByPrimaryKey(sequenceDO);
        String sequenceStr = String.valueOf(sequence);
        for(int i = 0; i < 6-sequenceStr.length(); i++){
            stringBuilder.append(0);
        }
        stringBuilder.append(sequenceStr);
        //最后2位为分库分表位(不实现)
        //Integer userid = 1000122;
        //userid % 100;
        stringBuilder.append("11");
        return stringBuilder.toString();
    }
}
