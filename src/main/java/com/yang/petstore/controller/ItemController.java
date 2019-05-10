package com.yang.petstore.controller;

import com.github.pagehelper.PageInfo;
import com.yang.petstore.controller.ViewObject.ItemVO;
import com.yang.petstore.dataobject.ItemDO;
import com.yang.petstore.response.CommonReturnType;
import com.yang.petstore.service.ItemService;
import com.yang.petstore.service.Model.ItemModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    private static final Logger lg = LoggerFactory.getLogger(ItemController.class);
    //商品页表浏览
    @RequestMapping(value = "/list")//Content type 'null' not supported
    public String listItem(@RequestParam(value="pageNo",defaultValue="1")int pageNo, @RequestParam(value="pageSize",defaultValue="3")int pageSize, Model model){
        PageInfo<ItemDO> page = itemService.listItem(pageNo,pageSize);
        httpServletRequest.getSession().setAttribute("pageInfo",page);
        lg.info("xxoooooooooooooooooooooooooooooooooo",page.getTotal());
        return "listitem";
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
