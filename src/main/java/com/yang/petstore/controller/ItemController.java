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
import javax.servlet.http.HttpSession;
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
    public String listItem(@RequestParam(value="pageNo",defaultValue="1")int pageNo, @RequestParam(value="pageSize",defaultValue="3")int pageSize, HttpSession httpSession){
        PageInfo<ItemDO> page = itemService.listItem(pageNo,pageSize);
        httpServletRequest.getSession().setAttribute("pageInfo",page);
       // model.addAttribute("pageInfo",page);
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

    @RequestMapping(value = "/mylist",method = {RequestMethod.GET})//Content type 'null' not supported
    public String list(){
        return "listitem";
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

    //主界面筛选
    @RequestMapping(value = "/selectByFactor",method = {RequestMethod.POST})//Content type 'null' not supported
    @ResponseBody
    public CommonReturnType selectByFactor(@RequestParam(value="pet")String pet,
                                 @RequestParam(value="use")String use,
                                 @RequestParam(value="price")String price,
                                 @RequestParam(value="pageNo",defaultValue="1")int pageNo,
                                 @RequestParam(value="pageSize",defaultValue="3")int pageSize){


        PageInfo<ItemDO> page = new PageInfo<ItemDO>();
        //单种查询
        //为猫
        if(pet.equals("狗狗")&&use.equals("")){
            //只根据狗查询(1,2,3)
            page = itemService.selectByPetCategory(pageNo,pageSize,0);
        }
        //为狗
        else if(pet.equals("猫猫")&&use.equals("")){
            //只根据狗查询(4,5,6)
            page = itemService.selectByPetCategory(pageNo,pageSize,1);
        }
        //两种查询
        else if(price.equals("")){
            //组装类别
            int category = convertCategory(pet, use);
            page = itemService.selectByCategory(pageNo,pageSize,category);
        }else{
            //三种查询
            int category = convertCategory(pet, use);
            String strings [] = price.split("-");
            int lowPrice = Integer.parseInt(strings[0]);
            int highPrice = Integer.parseInt(strings[1]);
            page = itemService.selectByPrice(pageNo,pageSize,category,lowPrice,highPrice);
        }
        httpServletRequest.getSession().setAttribute("pageInfo", page);
        return CommonReturnType.create(null);
    }



    private ItemVO convertVOFromModel(ItemModel itemModel){
        if(itemModel == null){
            return null;
        }
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel,itemVO);
        return itemVO;
    }


    private int convertCategory(String pet,String use){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(pet);
        stringBuilder.append(use);
        String str = stringBuilder.toString();
        switch (str){
            case "狗狗主粮零食":
                return 1;
            case "狗狗宠物玩具":
                return 2;
            case "狗狗出行装备":
                return 3;
            case "猫猫主粮零食":
                return 4;
            case "猫猫宠物玩具":
                return 5;
            case "猫猫出行装备":
                return 6;
        }
        return 0;
    }
}
