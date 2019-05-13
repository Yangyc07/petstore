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
    public String listItem(@RequestParam(value="pageNo",defaultValue="1")int pageNo, @RequestParam(value="pageSize",defaultValue="3")int pageSize){
        PageInfo<ItemDO> page = itemService.listItem(pageNo,pageSize);
        httpServletRequest.getSession().setAttribute("pageInfo",page);
        return "listitem";
    }

    //根据分类查询商品
    @RequestMapping(value = "/listByCategory")//Content type 'null' not supported
    public String listItem(@RequestParam(value="pageNo",defaultValue="1")int pageNo, @RequestParam(value="pageSize",defaultValue="3")int pageSize, int category) {
        PageInfo<ItemDO> page = itemService.selectByCategory(pageNo, pageSize, category);
        httpServletRequest.getSession().setAttribute("pageInfo", page);
        return "listitem";
    }


    //根据关键字搜索
    @RequestMapping(value = "/selectByKey")//Content type 'null' not supported
    public String selectByKey(@RequestParam(value="pageNo",defaultValue="1")int pageNo, @RequestParam(value="pageSize",defaultValue="3")int pageSize) {
        String key = httpServletRequest.getParameter("key");
        lg.info("key-----------------------------------------",key);
        PageInfo<ItemDO> page = itemService.selectByKey(pageNo, pageSize, key);
        httpServletRequest.getSession().setAttribute("pageInfo", page);
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

    //根据价格查询
    @RequestMapping(value = "/selectByPrice",method = {RequestMethod.GET})//Content type 'null' not supported
    public String selectByPrice(@RequestParam(value="pageNo",defaultValue="1")int pageNo,
                          @RequestParam(value="pageSize",defaultValue="3")int pageSize){
        PageInfo<ItemDO> pageInfo = (PageInfo<ItemDO>)httpServletRequest.getSession().getAttribute("pageInfo");
        int category = pageInfo.getList().get(0).getCategory();
        int lowPrice = Integer.parseInt(httpServletRequest.getParameter("lowPirce"));
        int highPrice = Integer.parseInt(httpServletRequest.getParameter("highPrice"));
        PageInfo<ItemDO> page = itemService.selectByPrice(pageNo, pageSize, category,lowPrice,highPrice);
        httpServletRequest.getSession().setAttribute("pageInfo", page);
        return "listitem";
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
