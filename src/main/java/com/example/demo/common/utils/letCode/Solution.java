package com.example.demo.common.utils.letCode;

//不用* / % 符合，求2个整数的商 比如 15/7 = 2 ,慢慢来吧，培养思考能力
class Solution {
    public int divide(int a, int b){
        int count = 0;
        if(b==0){
            throw new RuntimeException("被除数等于0,不合法");
        }
        //排除b=0，a=0的时候，直接返回0
        if(a==0){
            return 0;
        }
        boolean isNegative = false; //是否是负数，默认为false，默认正数
        if((a<0&&b>0)||(a>0&&b<0)){
                isNegative =true;
        }
        a = Math.abs(a);//取绝对值
        b = Math.abs(b);
        while (a-b>=0){
            count++;
            a = a-b;
        }
        if (isNegative){
            count = 0-count;
        }
        return count;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.divide(15,2));
        System.out.println(s.divide(4,-22));
        System.out.println(s.divide(-8,33));
        System.out.println(s.divide(0,0));
    }
}