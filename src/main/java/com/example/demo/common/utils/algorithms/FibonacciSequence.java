package com.example.demo.common.utils.algorithms;

public class FibonacciSequence {
    private Integer n; //第n位数
    private Integer number; //第n位数的值为number
    private Integer total; //前n位的和
    public FibonacciSequence(Integer n){
        this.n = n;
    }

    public Integer fibSequence(Integer n){
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        return fibSequence(n-1)+fibSequence(n-2);
    }

    //得到第n位大小
    public Integer getNumber(){
        this.number = fibSequence(this.n);
        return this.number;
    }
    //得到前n位的和
    public Integer getTotal(){
        return this.total;
    }

    public static void main(String[] args) {
        for(int i=1;i<10;i++){
            FibonacciSequence f1 = new FibonacciSequence(i);
            System.out.println(f1.getNumber());
        }
        FibonacciSequence f1 = new FibonacciSequence(4);
        System.out.println("第4位是？="+f1.getNumber());
    }
}


