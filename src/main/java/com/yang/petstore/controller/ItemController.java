package com.yang.petstore.controller;

import com.github.pagehelper.PageInfo;
import com.yang.petstore.controller.ViewObject.ItemVO;
import com.yang.petstore.dataobject.ItemDO;
import com.yang.petstore.response.CommonReturnType;
import com.yang.petstore.service.CacheService;
import com.yang.petstore.service.ItemService;
import com.yang.petstore.service.Model.ItemModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Controller("/item ")
@RequestMapping("/item")
@CrossOrigin(origins = {"*"},allowCredentials = "true")
public class ItemController extends BaseController{

    @Autowired
    private ItemService itemService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CacheService cacheService;


    private static final Logger lg = LoggerFactory.getLogger(ItemController.class);
    //商品页表浏览
    @RequestMapping(value = "/list")//Content type 'null' not supported
    public String listItem(@RequestParam(value="pageNo",defaultValue="1")int pageNo, @RequestParam(value="pageSize",defaultValue="5")int pageSize,Model model){
        PageInfo<ItemDO> catPage = itemService.selectByPetCategory(pageNo,pageSize,1);
        PageInfo<ItemDO> dogPage = itemService.selectByPetCategory(pageNo,pageSize,0);
        List<ItemDO> itemsBySales = itemService.selectBySales();
        model.addAttribute("itemBySales",itemsBySales);
        model.addAttribute("catPage",catPage);
        model.addAttribute("dogPage",dogPage);
        return "index";
    }

    //根据分类查询商品
    @RequestMapping(value = "/listByCategory")//Content type 'null' not supported
    public String listItem(@RequestParam(value="pageNo",defaultValue="1")int pageNo, @RequestParam(value="pageSize",defaultValue="10")int pageSize, int category,Model model) {
        PageInfo<ItemDO> itemPage = itemService.selectByCategory(pageNo, pageSize, category);
        model.addAttribute("itemPage", itemPage);
        return "itemdivid";
    }


    //根据关键字搜索
    @RequestMapping(value = "/selectByKey")//Content type 'null' not supported
    public String selectByKey(@RequestParam(value="pageNo",defaultValue="1")int pageNo, @RequestParam(value="pageSize",defaultValue="5")int pageSize,Model model) {


        String key = (String)httpServletRequest.getParameter("key");
//        if(key == null || key.trim().length()== 0){
//            key = httpServletRequest.getParameter("key");
//            httpServletRequest.getSession().setAttribute("key",key);
//        }
        lg.info("key-----------------------------------------",key);
        PageInfo<ItemDO> itemPage = itemService.selectByKey(pageNo, pageSize, key);
        model.addAttribute("itemPage",itemPage);
        return "itemdivid";
    }

    //商品详情页浏览
    @RequestMapping(value = "/get{id}",method = {RequestMethod.GET})//Content type 'null' not supported
    public String getItem(@RequestParam(name = "id") Integer id,Model model){

       ItemModel itemModel = null;

        //先取本地缓存
       itemModel = (ItemModel)cacheService.getFromCommonCache("item_"+id);

       if(itemModel == null){
           //根据商品的id到redis获取
           itemModel =(ItemModel) redisTemplate.opsForValue().get("item_"+id);

           //若redis内不存在对应的itemMode,则访问下游service
           if(itemModel == null) {
               itemModel = itemService.getItemById(id);
               //设置itemMode到redis
               redisTemplate.opsForValue().set("item_"+id,itemModel);
               redisTemplate.expire("item_"+id,10, TimeUnit.MINUTES);
           }
           //填充本地缓存
           cacheService.setCommonCache("item_"+id,itemModel);
       }
        ItemVO itemVO = convertVOFromModel(itemModel);
        model.addAttribute("itemVO",itemVO);
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

    //价格降序排列
    @RequestMapping(value = "/selectByDesc",method = {RequestMethod.GET})//Content type 'null' not supported
    public String selectByDesc(){
        PageInfo<ItemDO> page  = (PageInfo<ItemDO>) httpServletRequest.getSession().getAttribute("pageInfo");
        //获取商品信息
        List<ItemDO>  itemDOList = page.getList();
        Collections.sort(itemDOList, new Comparator<ItemDO>() {
            public int compare(ItemDO itemDO1, ItemDO itemDO2) {
                BigDecimal price1 = itemDO1.getPrice();
                BigDecimal price2 = itemDO2.getPrice();
                if (price1.compareTo(price2)==1) {
                    return -1;
                } else if (price2 == price1) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        for (ItemDO itemDO: itemDOList) {
            System.out.println(itemDO.getPrice());
        }
        page.setList(itemDOList);
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
