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
        httpServletRequest.getSession().setAttribute("itemBySales",itemsBySales);
        model.addAttribute("catPage",catPage);
        model.addAttribute("dogPage",dogPage);
        return "index";
    }

    //根据具体分类查询商品
    @RequestMapping(value = "/listByCategory")//Content type 'null' not supported
    public String listItem(@RequestParam(value="pageNo",defaultValue="1")int pageNo, @RequestParam(value="pageSize",defaultValue="10")int pageSize, int category,Model model) {
        PageInfo<ItemDO> itemPage = itemService.selectByCategory(pageNo, pageSize, category);
        httpServletRequest.getSession().setAttribute("category",category);
        model.addAttribute("itemPage", itemPage);
        return "item_list";
    }

    //根据猫狗分类查询商品
    @RequestMapping(value = "/selectByPetCategory")//Content type 'null' not supported
    public String selectByPetCategory(@RequestParam(value="pageNo",defaultValue="1")int pageNo, @RequestParam(value="pageSize",defaultValue="10")int pageSize, Integer category,Model model) {
        if(category==null){
            category = (Integer) httpServletRequest.getSession().getAttribute("category");
        }
        PageInfo<ItemDO> itemPage = itemService.selectByPetCategory(pageNo, pageSize, category);
        httpServletRequest.getSession().setAttribute("category", category);
        model.addAttribute("itemPage", itemPage);
        return "item_list";
    }

    //根据关键字搜索
    @RequestMapping(value = "/selectByKey")//Content type 'null' not supported
    public String selectByKey(@RequestParam(value="pageNo",defaultValue="1")int pageNo,
                              @RequestParam(value="pageSize",defaultValue="5")int pageSize,
                              Model model) {
        String key = (String)httpServletRequest.getParameter("key");
        PageInfo<ItemDO> itemPage = itemService.selectByKey(pageNo, pageSize, key);
        model.addAttribute("itemPage",itemPage);
        return "item_list";
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

    //将model转化为vo
    private ItemVO convertVOFromModel(ItemModel itemModel){
        if(itemModel == null){
            return null;
        }
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel,itemVO);
        return itemVO;
    }
}
