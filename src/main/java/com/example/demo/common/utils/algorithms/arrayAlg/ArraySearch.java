package com.example.demo.common.utils.algorithms.arrayAlg;

//各种对数组的查找方法
public class ArraySearch {
    public static void main(String[] args) {
//        binarySearch binarySearch = new binarySearch();
//        System.out.println(binarySearch.search(9));
        int random = (int)(Math.random()*10+1);
        System.out.println(random);
        bubbleSort bubblesort = new bubbleSort();
        bubblesort.sout();
        bubblesort.sort();
        System.out.println();
        bubblesort.sout();
        System.out.println();
        bubblesort.sortToSmall();
        bubblesort.sout();
    }
}
//二分查找
//查找的序列必须是有序的，如果要查找的元素在數組中是重複的，則不能保證最後找到是哪一個
class binarySearch{
    //整数数组
    private int[] arrayList;
    public binarySearch(){
        this.arrayList = new int[10];//10位
        for(int i=0;i<10;i++){
             arrayList[i] = i;
        }
    }
    public boolean search(int t){
        int left = 0;
        int right = this.arrayList.length-1;
        while (left<=right){
            int middle = left+(right-left)/2;
            if(arrayList[middle]<t){
                left = middle+1;
            }
            else if(arrayList[middle]>t){
                right = middle-1;
            }
            else {
                return true;
            }
        }
        return false;
    }

}
//冒泡排序
class bubbleSort{
    private int[] arrayList;
    public bubbleSort(int[] array){
        this.arrayList = array;//
    }
    public bubbleSort(){
        this.arrayList = new int[10];
        for(int i=0;i<this.arrayList.length;i++){
            int random = (int)(Math.random()*10+1);
            this.arrayList[i] = random;
        }
    }
    //从小到大排序
    public void sort(){
        for(int i=0;i<arrayList.length;i++){
            for(int j=i+1;j<arrayList.length;j++){
                if(arrayList[i]>arrayList[j]){
                    int tmp = arrayList[i];
                    arrayList[i] = arrayList[j];
                    arrayList[j] = tmp;
                }
            }
        }
    }
    //从大到小排序
    public void sortToSmall(){
        for(int i=0;i<arrayList.length;i++){
            for(int j=i+1;j<arrayList.length;j++){
                if(arrayList[i]<arrayList[j]){
                    int tmp = arrayList[i];
                    arrayList[i] = arrayList[j];
                    arrayList[j] = tmp;
                }
            }
        }
    }
    public void sout(){
        for(int i=0;i<this.arrayList.length;i++){
            System.out.print(this.arrayList[i]+" ");
        }
    }
}
//
