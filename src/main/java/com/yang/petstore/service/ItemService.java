package com.yang.petstore.service;

import com.github.pagehelper.PageInfo;
import com.yang.petstore.dataobject.ItemDO;
import com.yang.petstore.error.BusinessException;
import com.yang.petstore.service.Model.ItemModel;

import java.util.List;

public interface ItemService {

    //创建商品
    ItemModel creatItem(ItemModel itemModel) throws BusinessException;
    //商品列表浏览
    PageInfo<ItemDO> listItem(int pageNo, int pageSize);

    //商品详情浏览
    ItemModel getItemById(Integer id);

    //根据分类查询
    PageInfo<ItemDO> selectByCategory(int pageNo, int pageSize,int category);

    //库存扣减
    boolean decreaseStock(Integer itemId,Integer amount)throws BusinessException;

    //商品销量增加
     void increaseSales(Integer itemId,Integer amount)throws BusinessException;

     //根据关键字查询
     PageInfo<ItemDO> selectByKey(int pageNo, int pageSize,String key);

     //根据分类和价格区间查询
     PageInfo<ItemDO> selectByPrice(int pageNo, int pageSize,int category, int lowPirce, int highPrice);

     //根据猫狗分类查询
     PageInfo<ItemDO> selectByPetCategory(int pageNo, int pageSize,int category);

}
