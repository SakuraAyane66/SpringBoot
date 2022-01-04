package com.example.demo.common.utils.DataStructure.tree.数组实现;

import java.lang.reflect.Array;

/**
 * 以孩子节点为node的表示树，底层实现依旧是数组
 */
public class ChildTree<T> {
    //数组目前树的个数
    private int count;
    //树的容量，因为是通过数组实现的
    private int capacity;
    //数组实现
    private ChildTreeNode<T>[] nodes;
    public ChildTree(){ //无参构造器
        this.capacity = 10;
        nodes = new ChildTreeNode[10];
    }
    //手动更新大小
    public ChildTree(int x){
        this.capacity = x;
        nodes = new ChildTreeNode[capacity];
    }
    //判断是否是空树
    public boolean isEmpty(){
        return this.count==0 ? true :false;
    }
    //递归算法,返回树的深度,传入一个结点,重点理解还没理解透，12.23
    public int getDeep(ChildTreeNode<T> node){
        //空树返回0
        if (node == null){
            return 0;
        }
        int max = 0; //返回以该结点的深度 作为局部变量了..
        // 递归退出条件
        // 当我递归到叶子结点，说明我是最底层，则返回该层层级，既1
        if(node.childList.size()<=0){
           return 1; //递推返回0，而不是do nothing
        }
           for(Integer index:node.childList){
                int deep = getDeep(nodes[index]);
                max = max >deep ? max :deep;
           }
        return max+1;
    }

    //返回树的度（最大节点数）,遍历一次数组，求其的max
    public int getDu(){
        int max = 0;
        for(int i=0;i<count;i++){
             int size = nodes[i].childList.size();
             max = max >size ? max :size;
        }
        return max;
    }
    //返回树的根结点（如果有）
    public ChildTreeNode<T> getRoot(){
        return nodes[0];
    }

    //设置根结点,只有第一次设置生效
    public void addRoot(T data){
//        System.out.println("???什么意思？NUllpoint？= "+nodes[0]);
        if(nodes[0]!=null){
            System.out.println("执行异常");
            throw new RuntimeException("已经设置了根结点");
        }
        ChildTreeNode<T> node = new ChildTreeNode<>(data);
        nodes[0] = node;
        count++;
    }
    //为一个结点添加子节点,
    public void addChildNode(T data ,ChildTreeNode<T> parent){
        if(count>=capacity){
            throw new RuntimeException("容量不足!请扩容");
        }
            ChildTreeNode<T> node = new ChildTreeNode<>(data);
            nodes[count] = node;
            //父节点添加孩子的index坐标
            parent.childList.add(count);
            count++;
     }
    //删除一个结点及其所有的子节点，这里是树结构，而不是用树结构存储数据（平衡二叉树），所以删除结点之后其子树也一起删除，而不是重新构造树
    //还需要删除该结点父节点中的list坐标（如果有父节点）,也有可能该节点为根结点
    public void deleteNode(ChildTreeNode<T> node,int max){
        int index = getIndex(node,max);//得到删除结点的index
        //先保证存在需要删除的index，不是不合理的index
        if(index==-1){ //不能添加 ||index>=count ,因为count一直在减少，但是list中的index还未变化，index记录的值可能大于减少后的count值
            return;
        }
        nodes[index] =null;//将index置为null
        count--;
        for(Integer i:node.childList){
            deleteNode(nodes[i],max);//递归，对每一个list结点进行执行删除方法
        }
    }
    //返回一个结点的度(子节点数量)
    public int getDuByNode(ChildTreeNode<T> node){
        return node.childList.size();
    }

    //在数组中找到该结点的索引
    public int getIndex(ChildTreeNode<T> node){
        int index = -1; //默认无索引
        //循环数组（循环所有结点）
        for(int i=0;i<count;i++){
            if(nodes[i]==null){
                continue;  //加上了null判断(因为在delete中可能被删除了中间的值..)
            }
            if(nodes[i].equals(node)){
                index = i;
            }
        }
        return index;
    }
    //在数组中找到该结点的索引,在递归删除时候的版本，重载
    public int getIndex(ChildTreeNode<T> node,int count){
        int index = -1; //默认无索引
        //循环数组（循环所有结点）
        for(int i=0;i<count;i++){
            if(nodes[i]==null){
                continue;  //加上了null判断(因为在delete中可能被删除了中间的值..)
            }
            if(nodes[i].equals(node)){
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        ChildTree<String> Tree = new ChildTree<>(10);//新建一个String类型的容量为10的树
        Tree.addRoot("Sakura!");
//        Tree.addRoot("Onishi");
//        try {
//            Tree.addRoot("Onishi");
//        }catch (Exception e)
//        {
//            System.out.println(e.toString());
//        }
         Tree.addChildNode("Ayane",Tree.nodes[0]);//添加一个子节点
         Tree.addChildNode("Onishi",Tree.nodes[0]);
         Tree.addChildNode("Saori",Tree.nodes[2]);//在Onihsi后添加Saori
         Tree.addChildNode("Test",Tree.nodes[3]);//在Saori后面，
         System.out.println("树深度= "+Tree.getDeep(Tree.nodes[0]));
         for(int i=0;i<Tree.count;i++){
             System.out.println(Tree.nodes[i].toString());
         }
//        System.out.println(Tree.nodes[3].toString());

        Tree.deleteNode(Tree.nodes[2],Tree.count);
        System.out.println("删除后");
        System.out.println("树节点count为？ "+Tree.count);
        for(int i=0;i<Tree.count;i++){
            System.out.println(Tree.nodes[i].toString());
        }

    }
}
