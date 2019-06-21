package com.yang.petstore.controller.back;


import com.github.pagehelper.PageInfo;
import com.yang.petstore.controller.ViewObject.ItemVO;
import com.yang.petstore.dataobject.ItemDO;
import com.yang.petstore.error.BusinessException;
import com.yang.petstore.response.CommonReturnType;
import com.yang.petstore.service.ItemService;
import com.yang.petstore.service.Model.ItemModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.yang.petstore.controller.BaseController.CONTENT_TYPE_FORMED;


@Controller("manage/item")
@RequestMapping("manage/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    //获取所有商品的信息
    @RequestMapping(value = "/list")//Content type 'null' not supported
    public String listItem(@RequestParam(value="pageNo",defaultValue="1")int pageNo, @RequestParam(value="pageSize",defaultValue="5")int pageSize, Model model){

     //获取所有商品的信息
     PageInfo<ItemDO> itemDOPage = itemService.listItem(pageNo,pageSize);
     //将vo转化为model
     PageInfo<ItemModel> itemModelPage = new PageInfo<ItemModel>();
     BeanUtils.copyProperties(itemDOPage,itemModelPage);
     List<ItemModel> itemModels = new LinkedList<>();
     for (ItemDO itemDO: itemDOPage.getList()) {
       itemModels.add(itemService.getItemById(itemDO.getId()));
     }
     itemModelPage.setList(itemModels);
     model.addAttribute("itemModelPage",itemModelPage);
     return "seller_item_list";
    }

    //跳转添加商品界面
    @RequestMapping(value = "/creatItem")//Content type 'null' not supported
    public String creatItem(){
        return "seller_item_detail";
    }

    @RequestMapping(value = "/doCreateItem",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createItem(@RequestParam(name = "title")String title,
                                       @RequestParam(name = "category")Integer category,
                                       @RequestParam(name = "description")String description,
                                       @RequestParam(name = "brand")String brand,
                                       @RequestParam(name = "price")BigDecimal price,
                                       @RequestParam(name = "stock")Integer stock,
                                       @RequestParam(name = "imgUrl")String imgUrl) throws BusinessException {
        //封装service请求用来创建商品
        ItemModel itemModel = new ItemModel();
        itemModel.setCategory(category);
        itemModel.setBrand(brand);
        itemModel.setTitle(title);
        itemModel.setDescription(description);
        itemModel.setPrice(price);
        itemModel.setStock(stock);
        itemModel.setImgUrl(imgUrl);

        itemService.creatItem(itemModel);
        return  CommonReturnType.create(null);
    }

    //商品详情页浏览
    @RequestMapping(value = "/get{id}",method = {RequestMethod.GET})//Content type 'null' not supported
    public String getItem(@RequestParam(name = "id") Integer id,Model model){
        ItemModel itemModel = itemService.getItemById(id);
        model.addAttribute("itemModel",itemModel);
        return "getitem";
    }
}
