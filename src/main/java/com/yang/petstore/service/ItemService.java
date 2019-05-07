package com.yang.petstore.service;

import com.yang.petstore.error.BusinessException;
import com.yang.petstore.service.Model.ItemModel;

import java.util.List;

public interface ItemService {

    //创建商品
    ItemModel creatItem(ItemModel itemModel) throws BusinessException;
    //商品列表浏览
    List<ItemModel> listItem();

    //商品详情浏览
    ItemModel getItemById(Integer id);

    //库存扣减
    boolean decreaseStock(Integer itemId,Integer amount)throws BusinessException;

    //商品销量增加
     void increaseSales(Integer itemId,Integer amount)throws BusinessException;

}
