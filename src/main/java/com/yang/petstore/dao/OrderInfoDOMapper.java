package com.yang.petstore.dao;

import com.yang.petstore.dataobject.OrderInfoDO;

import java.util.List;

public interface OrderInfoDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_info
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_info
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    int insert(OrderInfoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_info
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    int insertSelective(OrderInfoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_info
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    OrderInfoDO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_info
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    int updateByPrimaryKeySelective(OrderInfoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_info
     *
     * @mbg.generated Wed May 08 19:48:22 CST 2019
     */
    int updateByPrimaryKey(OrderInfoDO record);

    List <OrderInfoDO> selectByOrderNo(String orderNo);
}