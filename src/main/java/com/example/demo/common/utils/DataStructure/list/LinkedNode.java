package com.example.demo.common.utils.DataStructure.list;

//CTL 学习复习结构，单向链表
//单向链表的头节点是没有数据的，只有指针指向第一个有数据的节点,头节点是自己定义的
public class LinkedNode <T> {
    //单链表的内部节点类，包含data，指针
    private class Node{
        public T t;  //node节点的数据
        public Node next; //指针，指向下一个节点
        public Node(){}
        public Node(T t){
            this.t = t;
            //为初始化的next自动为null
        }
        public Node(T t,Node node){
            this.t = t;
            this.next = node;
        }
    }
    private Node head; //链表头节点
    private int size; //链表大小
    //单向链表构造函数
    public LinkedNode(){
        this.head =new Node(); //头节点初始化为null，无数据，只有指针
        this.size = 0; //初始大小等于0
    }
    //CTL尝试自己在别人基础上书写,新增节点，新增数据
    public boolean addNode(T t){
        Node node = new Node(t);//初始化node
        System.out.println("新增的node值？="+node.t);
        //第一次插入的时候还是需要判断一下
        if(this.size==0){ //头节点无数据
            this.head.next = node; //头节点下一个节点是第一次插入的节点
            this.size++;
            return true;
        }
        //新增的时候是头节点之后的next，头节点不带数据
        Node temp = this.head;

        while(temp.next!=null){
            temp = temp.next;
        }
        temp.next = node; //添加到到最后一个node的next指针
        this.size++;
        return true;
    }
    //重载,向链表指定位置插入值（从链表头开始后的x位,我这里是向前插入，不是向后插入node）
    public int addNode(Integer index,T t){
        Node node = new Node(t);//新增node
        //先判断index是否合法
        if(index<=0||index>this.size){
            return -1; //-1表示插入失败
        }
        Node tmp = this.head; //过渡节点,插入节点前前一个节点
        //循环，找到index位置
        for(int i=0;i<index-1;i++){
                tmp = tmp.next;
        }
        //进行插入逻辑
        Node tmpNext = tmp.next; //插入前的原来下一位
        tmp.next = node;//原来的tmp更改指针，指向新的node
        node.next = tmpNext; //插入node的指针指向原来tmp的next
        return 1;
    }
    //删除,通过指定位置删除,java自己的回收机制，会自动回收删除掉的对象
    public int deleteNodeByIndex(Integer index){
        if(isEmpty()){
            return -1;
        }
        //先判断index是否合理.从1开始
        if(index<=0||index>this.size){
            return -1;
        }
        Node tmp = this.head;
        for(int i=0;i<index-1;i++){
            tmp = tmp.next; //最后的tmp是需要删除数据的前一个node
        }
        tmp.next = tmp.next.next;
        this.size--;
        return 1;
    }
    //删除，通过值删除，只删除第一次出现的值
    public int deleteNodeByValue(T value){
        if(isEmpty()){
            return -1;
        }
        Node tmp = this.head;//头节点
        //循环nodes,size-1是因为访问的是tmp.next，最后一位的前一位才有next，此时访问的是最后一个node节点的值
        for(int i=0;i<this.size-1;i++){
            if(tmp.next.t==value){
                if(tmp.next.next!=null){ //下一个的下一个不为null，相同值不是最后一个节点
                    tmp.next = tmp.next.next;//指针指向下一个
                }
                if(tmp.next.next==null){ //删除的值是最后一个node节点，前一个node节点指针指向空
                    tmp.next = null;
                }
                this.size--;
                return 1; //直接return
            }
            tmp = tmp.next; //最终指向null
        }
        return 0; //没有找到相同值
    }
    //删除出现的所有值
//    public int deleteAllNodeByValue(T value){
//        if(isEmpty()){
//            return -1;
//        }
//        int count = 0;
//        Node tmp = this.head; //遍历节点
//        for(int i=0;i<this.size;i++){
//            //this.size不能在循环中更改,要找preNode值
//            Node pre = tmp; //当前节点
//            if(pre.next.t ==value){ //pre下一个node的值相同
//                pre.next=pre.next.next; //将pre的next移动到下两个
//            }
//            tmp = tmp.next;
//        }
//    }
    //清空链表
    public boolean deleteAllNode(){
            this.head.next = null;
            this.size=0;
            return true;
    }

    //查找,返回指定位置的值
    public T searchIndex(Integer index){
        if(isEmpty()){
            return null;
        }
        if(index<=0||index>this.size){
            return null;
        }
        Node node = this.head; //头节点
        for(int i=0;i<index;i++){
            node = node.next;
        }
        return node.t;
    }
    //查找，链表是否包含该值
    public boolean searchIsCon(T value){
        if(isEmpty()){return false;}
        Node node = this.head;
        for(int i=0;i<this.size;i++){
            if(node.t==value){
                return true;
            }
            node= node.next;
        }
        return false;
    }
    //更改
    //遍历链表，遍历所有节点
    public void traversalLinkedNode(){
        //因为单向链表不带数据
//        if(this.head==null){
//            return;
//        }
        Node node = this.head.next;
        int index = 1;
        while(node!=null){
            System.out.println("遍历中的"+index+"位node===="+node.t);//输出node的数据
            node = node.next; //node 切换，直到最后一个node
            index++;
        }
    }
    //判断链表是否是空链表
    public boolean isEmpty(){
        return this.size==0 ? true:false;
    }

    public static void main(String[] args) {
        LinkedNode<String> linkedNode = new LinkedNode<>();
        linkedNode.addNode("Sakura");
        linkedNode.addNode("ayane");
        System.out.println("是否为空"+linkedNode.isEmpty());
        linkedNode.traversalLinkedNode();
        System.out.println(linkedNode.size);
        linkedNode.addNode("sikida!");
        linkedNode.addNode("CTL");
        linkedNode.traversalLinkedNode();
        System.out.println(linkedNode.size);
        linkedNode.searchIndex(0);
        System.out.println("第3位是"+linkedNode.searchIndex(5));
        System.out.println(linkedNode.searchIsCon("Sakura"));
        System.out.println(linkedNode.deleteNodeByValue("Sakura"));
        System.out.println(linkedNode.searchIsCon("Sakura"));
        System.out.println(linkedNode.size);
        linkedNode.deleteNodeByIndex(2);
        linkedNode.addNode(2,"Siki");
        linkedNode.traversalLinkedNode();
        linkedNode.deleteAllNode();
        System.out.println(linkedNode.isEmpty());
    }
}
