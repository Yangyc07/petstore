package com.yang.petstore.service.Impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yang.petstore.controller.ViewObject.CartItemVO;
import com.yang.petstore.controller.ViewObject.CartVO;
import com.yang.petstore.controller.ViewObject.OrderInfoVO;
import com.yang.petstore.controller.ViewObject.OrderVO;
import com.yang.petstore.dao.OrderDOMapper;
import com.yang.petstore.dao.OrderInfoDOMapper;
import com.yang.petstore.dao.SequenceDOMapper;
import com.yang.petstore.dao.UserAddressDOMapper;
import com.yang.petstore.dataobject.*;
import com.yang.petstore.error.BusinessException;
import com.yang.petstore.redis.RedisConfiguration;
import com.yang.petstore.service.CacheService;
import com.yang.petstore.service.CartService;
import com.yang.petstore.service.ItemService;
import com.yang.petstore.service.Model.ItemModel;
import com.yang.petstore.service.OrderService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private ItemService itemService;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private RedisTemplate redisTemplate;

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
        orderDO.setShippingId(shippingId);
        orderDO.setPostage(0);
        orderDO.setStatus(10);
        orderDO.setCreateTime(DateTime.now().toDate());
        orderDO.setUpdateTime(DateTime.now().toDate());

        lg.info("xxxxxxxxxxxxxxxxx",orderDO.toString());
        orderDOMapper.insert(orderDO);
        return orderVO;
    }


    //完成支付
    @Override
    public boolean completePay(String orderNo) throws BusinessException {
        //1.更新支付状态，首先根据订单号查询订单，然后更新
        OrderDO orderDO = orderDOMapper.selectByOrderNo(orderNo);
        //支付宝支付
        orderDO.setPaymentType(1);
        //已支付
        orderDO.setStatus(20);
        //设置时间
        orderDO.setUpdateTime(DateTime.now().toDate());
        orderDO.setPaymentTime(DateTime.now().toDate());
        //更新
        orderDOMapper.updateByPrimaryKey(orderDO);
        //2.落单减库存,增加销量
        //根据订单信息查询出所购买商品的数量
        List<OrderInfoDO> orderInfoDOList = orderInfoDOMapper.selectByOrderNo(orderNo);
        //减库存
        for (OrderInfoDO orderInfoDo: orderInfoDOList) {
            //先更新数据库，然后更新缓存
            itemService.decreaseStock(orderInfoDo.getItemId(),orderInfoDo.getQuantity());
            itemService.increaseSales(orderInfoDo.getItemId(),orderInfoDo.getQuantity());
            //首先更新本地缓存，再更新redis缓存
            ItemModel itemModel = (ItemModel) redisTemplate.opsForValue().get("item_"+orderInfoDo.getItemId());
            itemModel.setStock(itemModel.getStock()-orderInfoDo.getQuantity());
            itemModel.setSales(itemModel.getSales()+orderInfoDo.getQuantity());
            cacheService.setCommonCache("item_"+orderInfoDo.getItemId(),itemModel);
            //更新redis缓存
            redisTemplate.opsForValue().set("item_"+orderInfoDo.getItemId(),itemModel);
            redisTemplate.expire("item_"+orderInfoDo.getItemId(),10, TimeUnit.MINUTES);

        }
        return true;
    }


    @Override
    public PageInfo<OrderVO> selectByUserId(int pageNo, int pageSize,Integer userId) {
       PageHelper.startPage(pageNo,pageSize);//页数 和 行数
        //需要订单信息，详细信息和地址信息 购物车信息
       List<OrderDO> orderDOList =  orderDOMapper.selectByUserId(userId);
       //转化为pageInfo
       PageInfo<OrderDO> orderDOS = new PageInfo<>(orderDOList);
       //将pageInfo进行处理
       List<OrderVO> orderVOList = this.ConvertOrderVO(orderDOS.getList());
       orderDOS.setList(orderDOList);
       PageInfo<OrderVO> orderVOS = new PageInfo<>();
       BeanUtils.copyProperties(orderDOS,orderVOS);
       orderVOS.setList(orderVOList);
       return orderVOS;
    }

    private List<OrderVO>  ConvertOrderVO(List<OrderDO> orderDOList) {
        List<OrderVO> orderVOS = new LinkedList<>();
        Integer amount = 0;
        //循环组装VO
        for (OrderDO orderDO : orderDOList) {
            amount = 0;
            OrderVO orderVO = new OrderVO();
            List<CartItemVO> cartItemVOList = new LinkedList<>();
            BeanUtils.copyProperties(orderDO, orderVO);
            List<OrderInfoDO> orderInfoDOList = orderInfoDOMapper.selectByOrderNo(orderDO.getOrderNo());
            for (OrderInfoDO orderInfoDO : orderInfoDOList) {
                CartItemVO cartItemVO = new CartItemVO();
                amount += orderInfoDO.getQuantity();
                BeanUtils.copyProperties(orderInfoDO, cartItemVO);
                cartItemVO.setPrice(orderInfoDO.getCurrentUnitPrice());
                cartItemVO.setName(orderInfoDO.getItemName());
                cartItemVO.setImg_url(orderInfoDO.getItemUrl());
                cartItemVO.setTotal(orderInfoDO.getTotalPrice());
                cartItemVOList.add(cartItemVO);
                cartItemVO.setItemId(orderInfoDO.getItemId());
            }
            orderVO.setAmount(amount);
            orderVO.setUserAddressDO(userAddressDOMapper.selectByPrimaryKey(orderDO.getShippingId()));
            orderVO.setCartItemVO(cartItemVOList);
            orderVOS.add(orderVO);
        }
        return orderVOS;
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
        orderVO.setStatus(10);
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
        stringBuilder.append("11");
        return stringBuilder.toString();
    }


}
