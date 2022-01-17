package com.example.demo.alice.imgs.controller;

import com.example.demo.alice.imgs.domain.Image;
import com.example.demo.alice.imgs.service.ImageService;
import com.example.demo.common.base.AjaxResult;
import com.example.demo.common.base.BaseController;
import com.example.demo.common.base.BaseSearchMiddle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Images 控制器，放回图片的路径相关信息
 */
@RestController
public class ImageController extends BaseController {
    private final String ctx = "alice"; //系统的，后续可以更改为常量（更方便统一管理和修改,目前体量小就无所谓）
    @Autowired
    private ImageService imageService;

    @PostMapping(ctx+"/getImages")
    public AjaxResult getImages(@RequestParam("number") Integer number) throws Exception{
        try {
            System.out.println(number);
            List<Image> list = imageService.getUrl(number);
            if(list.size()!=0){
                return success("成功了",list);
            }
            return error("获取图片地址失败");
        }catch (Exception exception){
            System.out.println("捕获错误"+exception);
        }
        return null;
    }
    //因为懒得改搜索实体类，将就用实体类的id替换搜索用number了
//    @PostMapping(ctx+"/getImagess")
//    public AjaxResult getImagess(@RequestBody BaseSearchMiddle baseSearchMiddle) throws Exception{
//        try {
//            Integer number = baseSearchMiddle.getId();
//            List<Image> list = imageService.getUrl(number);
//            if(list.size()!=0){
//                return success("成功了",list);
//            }
//            return error("获取图片地址失败");
//        }catch (Exception exception){
//            System.out.println("捕获错误"+exception);
//        }
//        return null;
//    }
}
