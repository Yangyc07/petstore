package com.yang.petstore.controller;

import com.yang.petstore.controller.ViewObject.ItemVO;
import com.yang.petstore.service.ItemService;
import com.yang.petstore.service.Model.ItemModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller("/item ")
@RequestMapping("/item")
@CrossOrigin(origins = {"*"},allowCredentials = "true")
public class ItemController extends BaseController{

    @Autowired
    private ItemService itemService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    //商品页表浏览
    @RequestMapping(value = "/list",method = {RequestMethod.GET})//Content type 'null' not supported
    public String listItem(){
        List<ItemModel> itemModelList = itemService.listItem();
        List<ItemVO> itemVOList = itemModelList.stream().map(itemModel -> {
            ItemVO itemVO = this.convertVOFromModel(itemModel);
            return itemVO;
        }).collect(Collectors.toList());
        httpServletRequest.getSession().setAttribute("itemVOList",itemVOList);
        return "listItem";
    }

    //商品详情页浏览
    @RequestMapping(value = "/get{id}",method = {RequestMethod.GET})//Content type 'null' not supported
    public String getItem(@RequestParam(name = "id") Integer id){
        ItemModel itemModel = itemService.getItemById(id);
        ItemVO itemVO = convertVOFromModel(itemModel);
        httpServletRequest.getSession().setAttribute("itemVO",itemVO);
        return "getitem";
    }

    private ItemVO convertVOFromModel(ItemModel itemModel){
        if(itemModel == null){
            return null;
        }
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel,itemVO);
        return itemVO;
    }
}
