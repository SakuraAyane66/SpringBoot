package com.example.demo.common.utils.letCode;

//实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
public class Solution1 {
    public boolean isUnique(String astr) {
        if(astr.length()<0||astr.length()>100){
            throw new RuntimeException("长度错误");
        }
        String[] s = astr.split(""); //拆分字符串为单个字符数组
        //判断字符数组中是否存在相同元素
        for(int i=0;i<s.length-1;i++){
            for(int j=i+1;j<s.length;j++){
                if(s[i].equals(s[j])){
                    return true;
                }
            }
        }
        //拆分字符串到数组中，每个字符存放到数组中，判断数组是否重复
        return false;
    }
    //比较官方的答案
    public boolean isUnique(String astr,boolean s){
        for (char ch: astr.toCharArray()){
            if (astr.indexOf(ch) != astr.lastIndexOf(ch)) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        String a = "yyyymmmmdddd";
        char[] b = a.toCharArray();
        char c ='c';
        char d = 'd';
        if(c>d){
            System.out.println("大于");
        }else {
            System.out.println("小于");
        }
        for(char s:b){
            System.out.println(s);
        }
        Solution1 s = new Solution1();
        System.out.println(s.isUnique(a));
    }
}
