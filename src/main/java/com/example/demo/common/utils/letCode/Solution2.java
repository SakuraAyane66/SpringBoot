package com.example.demo.common.utils.letCode;

import java.util.HashMap;

//给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。
//
//        你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
//
//        你可以按任意顺序返回答案。
public class Solution2 {
    //时间复杂度O(n平方)
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2]; //建立长度为2的数组作为result
        //先来通用处理，双循环
        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j< nums.length;j++){
                //符合条件直接返回
                if((nums[i]+nums[j])==target){
                    result[0]=i;
                    result[1]=j;
                    return  result;
                }
            }
        }
        return result; //找不到符合条件的目前，返回空数组（[0,0]）
    }
    //官方推荐答案之一，时间复杂度小于O(n平方)
    public int[] twoSum(int[] nums,int target,String Method_2_false){
        int[] result = new int[2];
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        //将数组下标和对应的值存进hashMap中
        for(int i=0;i<nums.length;i++){
            hashMap.put(i,nums[i]); //网上代码此处参考的是put(v,k)，但是v可能相同，所有有问题，自己的put(k,v)后续不能直接查找
        }
        //因为自己的put(k,v)所以在此处的函数出现了问题，需要在手写一个函数通过v找寻k的过程，并且判断k是否等于i
        for(int i=0;i< nums.length;i++){
            if(hashMap.containsValue(target-nums[i])){
                return result;
            }
        }
        return  result;
    }
    //这种方式应该最厉害
    public int[] twoSum(int[] nums,int target,int Method_3){
        int[] result = new int[2];
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        //一次循环，边插入边找寻
        for(int i=0;i<nums.length;i++){
            //从hashmap中找到目标值，并且目标值的v不等于i
            if(hashMap.containsKey(target-nums[i])&&hashMap.get(target-nums[i])!=i){
                result[0] = hashMap.get(target-nums[i]);
                result[1] = i;
                return result;
            }
            hashMap.put(nums[i],i);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution2 s = new Solution2();
        int[] numbs = {1,2,3,4,5,6,7,8,9,10,66};
        int[] result =s.twoSum(numbs,68,1);
        for(int i=0;i<result.length;i++){
            System.out.println(result[i]);
        }


    }
}
