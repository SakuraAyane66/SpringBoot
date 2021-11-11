package com.example.demo.common.utils.DataStructure.list;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//java的栈实现，动态数组实现 ，//下面是链表实现
public class Stack<T> {
        //实现栈的数组
    private Object[] stack;
    private int index; //指向栈顶
    //指定栈大小的初始化
    public Stack(int size){
        this.stack = new Object[size];//index 默认就是0
    }
    //默认初始化，大小为16
    public Stack(){
        this.stack = new Object[16]; //index 默认就是0
    }
    //pop
    public T pop(){
        if(isEmpty()){
            return null;
        }
        T t = (T)stack[index-1]; //当前栈顶元素
        index--;
        return t;
    }
    //push,因为是动态数组，所以不用判断栈是否溢出，动态扩容
    public void push(T t){
        isFull();
        stack[index] = t;
        index++;
    }
    //peek,返回栈顶元素，如果有
    public T peek(){
        if(isEmpty()){
            return null;
        }
        T t = (T)stack[index-1];
        return t;
    }
    //isFull,扩容
    public void isFull(){
          //判断是否溢出
          if(index<stack.length){
              return;
          }
          int size = stack.length*2; //扩容2倍
        stack = Arrays.copyOf(stack, size);//拷贝扩容的数组
    }
    //isEmpty
    public boolean isEmpty(){
        return index== 0 ? true:false;
    }

    public static void main(String[] args) {
//        Stack<String> stringStack = new Stack<>(2); //初始化为2个size大小
//        stringStack.push("Sakura");
//        System.out.println(stringStack.peek());
//        System.out.println(stringStack.isEmpty());
//        stringStack.push("ayane");
//        System.out.println(stringStack.peek());
//        stringStack.push("sikida"); //自动扩容
//        System.out.println(stringStack.peek());

        Stack1<String> stringStack1 = new Stack1<>();
        stringStack1.push("sakura");
        stringStack1.push("ayane");
        System.out.println(stringStack1.isEmpty());
        System.out.println(stringStack1.pop());
        System.out.println(stringStack1.pop());
        System.out.println(stringStack1.isEmpty());
        HashMap<String,String> map = new HashMap<>();
        map.put("sakura","ayane");
        map.put("sakura","siki");
        System.out.println(map.get("sakura"));
        for(Map.Entry<String,String> ll:map.entrySet()){
            System.out.println("遍历map"+map.get(ll.getKey()));
        }

    }
}
//链表实现,自动扩容，可以判断是否为空
class Stack1<T>{
    //内部类Node
    private class Node{
        private T t;
        private Node next;
        public Node(){}
        public Node(T t){
            this.t = t;
        }
        public T getT(){
            return this.t;
        }
    }
    private Node head; //头节点
    public Stack1(){
        this.head = new Node();
    }

    //插入栈
    public void push(T t){
        Node node = new Node(t);
        Node tmp = head;
        while (tmp.next!=null){
            tmp = tmp.next;
        }
        tmp.next = node;
    }
    //出栈,链表最后一个节点释放，指向空
    public T pop(){
        Node node =new Node();
        if(isEmpty()){
            //空栈直接返回
            return null;
        }
        Node tmp = this.head;
        while (tmp.next.next!=null){
            tmp = tmp.next;
        }
        node = tmp.next;
        tmp.next =null;
        return node.getT();
    }

    //判断是否为空栈
    public boolean isEmpty(){
        return this.head.next ==null ? true:false;
    }
}
