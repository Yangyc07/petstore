package com.yang.petstore.dao;

import com.yang.petstore.dataobject.UserAddressDO;

import java.util.List;

public interface UserAddressDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbg.generated Wed May 08 15:09:38 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbg.generated Wed May 08 15:09:38 CST 2019
     */
    int insert(UserAddressDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbg.generated Wed May 08 15:09:38 CST 2019
     */
    int insertSelective(UserAddressDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbg.generated Wed May 08 15:09:38 CST 2019
     */
    UserAddressDO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbg.generated Wed May 08 15:09:38 CST 2019
     */
    int updateByPrimaryKeySelective(UserAddressDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbg.generated Wed May 08 15:09:38 CST 2019
     */
    int updateByPrimaryKey(UserAddressDO record);

    List<UserAddressDO> selectByUserId(Integer UserId);

    void  deleteByUserId(Integer userID);
}