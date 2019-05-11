package com.yang.petstore.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yang.petstore.dao.ItemDOMapper;
import com.yang.petstore.dao.ItemStockDOMapper;
import com.yang.petstore.dataobject.ItemDO;
import com.yang.petstore.dataobject.ItemStockDO;
import com.yang.petstore.error.BusinessException;
import com.yang.petstore.error.EmBusinessError;
import com.yang.petstore.service.ItemService;
import com.yang.petstore.service.Model.ItemModel;
import com.yang.petstore.validator.ValidationResult;
import com.yang.petstore.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private ItemDOMapper itemDOMapper;

    @Autowired
    private ItemStockDOMapper itemStockDOMapper;



    public ItemDO convertItemDOFromItemModel(ItemModel itemModel){
        if(itemModel == null){
            return null;
        }
        ItemDO itemDO = new ItemDO();
        BeanUtils.copyProperties(itemModel,itemDO);
        //设置价格的类型
        itemDO.setPrice(itemModel.getPrice());
        return itemDO;
    }

    public ItemStockDO convertStockDOFromItemModel(ItemModel itemModel){
        if(itemModel == null){
            return null;
        }
        ItemStockDO itemStockDO= new ItemStockDO();
        itemStockDO.setItemId(itemModel.getId());
        itemStockDO.setStock(itemModel.getStock());
        return itemStockDO;
    }

    @Override
    public ItemModel creatItem(ItemModel itemModel) throws BusinessException{
        //校验入参
       ValidationResult result = validator.validate(itemModel);
        if(result.isHasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrMsg());
        }

        //转化itemmodel->dataobject
        ItemDO itemDO = this.convertItemDOFromItemModel(itemModel);

        //写入数据库
        itemDOMapper.insertSelective(itemDO);
        itemModel.setId(itemDO.getId());

        ItemStockDO itemStockDO = this.convertStockDOFromItemModel(itemModel);
        itemStockDOMapper.insertSelective(itemStockDO);

        //返回对象
        return this.getItemById(itemModel.getId());
    }


    @Override
    public PageInfo<ItemDO> listItem(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);//页数 和 行数
        List<ItemDO> itemDOList = itemDOMapper.listItem();
        PageInfo<ItemDO> pageInfo =new PageInfo<ItemDO>(itemDOList);
        return pageInfo;
    }

    @Override
    public ItemModel getItemById(Integer id) {
        ItemDO itemDO = itemDOMapper.selectByPrimaryKey(id);
        if(itemDO == null){
            return null;
        }
        //操作获得库存数量
        ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());

        //将dataobjetc->model
        ItemModel itemModel = convertModelFromDataObject(itemDO,itemStockDO);

        return itemModel;
    }

    @Override
    public PageInfo<ItemDO> selectByCategory(int pageNo, int pageSize, int category) {
        PageHelper.startPage(pageNo,pageSize);//页数 和 行数
        List<ItemDO> itemDOList = itemDOMapper.selectByCategory(category);
        PageInfo<ItemDO> pageInfo =new PageInfo<ItemDO>(itemDOList);
        return pageInfo;
    }

    @Override
    @Transactional
    public boolean decreaseStock(Integer itemId, Integer amount) throws BusinessException {
        int affectedRow = itemStockDOMapper.decreaseStock(itemId, amount);
        if(affectedRow > 0){
            //更新库存成功
            return true;
        }else{
            //更新库存失败
            return false;
        }
    }

    @Override
    @Transactional
    public void increaseSales(Integer itemId, Integer amount) throws BusinessException {
        itemDOMapper.increaseSales(itemId,amount);
    }

    private ItemModel convertModelFromDataObject(ItemDO itemDO, ItemStockDO itemStockDO){
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDO,itemModel);
        itemModel.setPrice(itemDO.getPrice());
        itemModel.setStock(itemStockDO.getStock());
        return  itemModel;
    }
}
