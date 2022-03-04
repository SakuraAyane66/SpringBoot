package com.example.demo.common.utils.Test;

import org.thymeleaf.context.IContext;

import java.util.*;

public class TestMain {
    public static int m = 3;
    public static void main(String[] args) {
        // 模板全的路径
        String templatePath = "D:\\日志测试\\test.docx";
        // 输出位置
        String outPath = "D:\\日志测试\\TTT.docx";
        String[] stringList = new String[4];//长度为5的string数组
        System.out.println(stringList.length);
        for (int i = 0; i < stringList.length - 1; i++) {
            stringList[i+1] = "${image:image" + i + "}";
            System.out.println(" stringList" + i + " I is ?" + stringList[i]);
        }
        stringList[0]=" 因为本身直接被解析了？";
        stringList[3] = " ";
        Map<String, Object> paramMap = new HashMap<>(16);
        // 段落中的动态段示例 [str], 支持动态行中添加图片
//        List<Object> list1 = new ArrayList<>(Arrays.asList("图片？为什么啊","${image:image0}","${image:image1}","???"));
        List<ImageEntity> list = new ArrayList<>(); //用于给map.put用的图片list
        ImageEntity imgEntity = new ImageEntity();
        imgEntity.setHeight(200);
        imgEntity.setWidth(300);
        imgEntity.setUrl("D:\\散图\\IMG_0006.JPG");
        imgEntity.setTypeId(ImageUtils.ImageType.JPG);
        list.add(imgEntity);

        ImageEntity imgEntity1 = new ImageEntity();
        imgEntity1.setHeight(200);
        imgEntity1.setWidth(300);
        imgEntity1.setUrl("D:\\散图\\IMG_0007.JPG");
        imgEntity1.setTypeId(ImageUtils.ImageType.JPG);
        list.add(imgEntity1);
        int n = 0;
        for (ImageEntity img : list) {
            paramMap.put("image:image" + n, img);
            n++;
        }
//        paramMap.put("image:image0", imgEntity);
//        paramMap.put("image:image1", imgEntity);
//        paramMap.put("image:image2",imgEntity1);
//        paramMap.put("image:image2", imgEntity);
//        paramMap.put("image:image3", imgEntity);
        List<Object> list1 = new ArrayList<>(Arrays.asList(stringList));
        paramMap.put("list1", list1);
        paramMap.put("ttt", "Test msg");
        DynWordUtils.process(paramMap, templatePath, outPath);
//        int end =6;
//        for(int i=0;i<end;i++){
//            if(i ==3){
//                end=10;
//            }
//            System.out.println(i);
//        }
    }
}
