package com.example.demo.common.utils.DataStructure.list;

//双向循环链表
public class DoubleLinkedNode<T> {
    //头节点
    private Node head;


    //节点类
    private class Node{
        private T t; //数据本身
        private Node pre; //前节点
        private Node next; //后节点
        public Node(){} //无参构造器
        public Node(T t){
            this.t = t; //初始化数据的构造器
        }

    }
    //无参构造器
    public DoubleLinkedNode(){
        //初始化为空双向循环链表
        this.head = null;
    }
    //链表头后面插入,插入数据
    public void add(T t){
        Node node = new Node(t);
        //第一次插入，第一次的时候和链表尾插入其实结果一样
        if(head==null){
            this.head = node;
            this.head.next = node;
            this.head.pre = node;
        }else{
            node.pre = this.head; //node节点前节点变为头节点
            node.next = this.head.next; //node节点下一个节点变为head的原始下一个
            this.head.next.pre = node; //头节点的下一个节点前节点变为node
            this.head.next = node; //头节点的下一个节点变为node
        }
    }
    //链表头前面插入
    public void addLast(T t){
        Node node = new Node(t);
        if(this.head==null){
            this.head = node;
            this.head.next = node;
            this.head.pre = node;
        }else {
            node.next = this.head;
            node.pre = this.head.pre;
            this.head.pre = node;
            this.head.pre.next  = node;
        }
    }
    //指定位置插入
    public void addByIndex(int index,T t){
        Node node = new Node(t);//新建node
        //位置小于0，或者大于总长度 , 位置1 []  位置2 []  位置3 [] ,size 大小为3 ，输入的数字是按照这个标准
        if(index<=0 || index>getSize()){
            return;
        }
        Node tmp  = this.head;//头节点
        for(int i=1;i<index;i++){
            tmp = tmp.next;
        }
        //node节点下一个为tmp原节点下一个
        node.next = tmp.next;
        node.pre = tmp; //node前节点为tmp节点
        tmp.next.pre = node; //tmp原下一节点的前节点为node
        tmp.next = node; //tmp下节点变更为node

    }
    //链表大小
    public int getSize(){
        int size = 0; //初始size大小为0
        if(this.head==null){
            return size;
        }
        Node node = this.head;
        while (node.next !=this.head){
            node = node.next;
            size++;
        }
        return size+1;
    }
    //是否空链表
    public boolean isEmpty(){
        return this.head ==null ? true:false;
    }
    //从头开始删除（删除头节点的下一个数据）
    public T deleteHead(){
        Node node = this.head; //指向头节点的node
        head.pre.next = head.next; //原head头节点变为head下一个节点
        head.next.pre = head.pre; //原head后节点变为原head前节点
        this.head = head.next ;//头节点后移
        //但此时node原来的pre和next没有清除啊..
        //node.pre = null;
        //node.next = null;
        return node.t;
    }
    //从尾节点开始删除(删除尾节点)

    //清空双向循环链表
    public void clear(){
        this.head = new Node();//令头节点重新指向null
    }
    //显示数据内容
    public void display(){
        if(isEmpty()){
            System.out.println("空链表");
            return;
        }
        Node tmpsize = this.head;
        Node tmp =this.head;
        int i = 0;
        while (tmpsize.next!=this.head){
            tmpsize = tmpsize.next;
            i++;
        }
        System.out.println("i的大小？"+i);
        for(int j=0;j<i+1;j++){
            System.out.print("["+tmp.t+"]----->");
            tmp=tmp.next;
        }
    }

    public void displayByMap(){
        if(isEmpty()){
            System.out.println("空表");
            return;
        }
        Node tmp = this.head;
        System.out.println("t?"+tmp.t);
        System.out.println("next?"+tmp.next.t);
        System.out.println("pre?"+tmp.pre.t);
        System.out.println("nextnext？"+tmp.next.next.t);
        System.out.println(tmp.next.next.next==this.head);
        //无法遍历双向循环链表，要不不能打印head，要不不能打印head前一个节点
//        do{
//            System.out.print("["+tmp.t+"]----->");
//            tmp = tmp.next;
//        }
//        while (tmp.next!=head);
    }
    public static void main(String[] args) {
        DoubleLinkedNode<String> linkedNode = new DoubleLinkedNode<>();
        linkedNode.add("sakura");
        linkedNode.add("ayane");
        linkedNode.add("siki");
        System.out.println(linkedNode.getSize());
        System.out.println(linkedNode.isEmpty());
        linkedNode.display();
//        linkedNode.displayByMap();

//        linkedNode.deleteHead();
//        linkedNode.display();
    }
}
