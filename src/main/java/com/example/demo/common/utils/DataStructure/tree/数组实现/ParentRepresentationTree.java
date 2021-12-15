package com.example.demo.common.utils.DataStructure.tree.数组实现;

import java.util.ArrayList;
import java.util.List;

/*
底层用数组实现的树，双亲表示法，结点只有自身值以及父结点在数组中的索引
数的度是所有结点中最大的度()
数的深度是层数（也叫树的深度）
该类相当于整个树
 */
public class ParentRepresentationTree<T> {

    //树的容量，因为该树底层是通过数组实现的
    private int capacity;
    //树的结点总数(作为数组插入的标识index
    private int nodeCount;
    //实现树的数组
    private TreeNode<T>[] nodes;

    /**
     * 初始化树
     * @param capacity
     */
    public ParentRepresentationTree(int capacity){
        this.capacity = capacity; //初始化树容量
        this.nodes = new TreeNode[capacity]; //初始化长度为capacity的数组
    }
    //无参数构造,默认容量为10
    public ParentRepresentationTree(){
        this.capacity  = 10;
        this.nodes = new TreeNode[10];
    }
    //设置根结点
    public void addRoot(T data){
        //根结点的父结点为-1，没有父结点
        TreeNode node = new TreeNode(data,-1);
        this.nodes[0]  = node; //设置数组[0]的位置为根结点
        this.nodeCount ++; //结点数+1
    }
    //返回根结点
    public TreeNode getRoot(){
        return this.nodes[0];
    }
    //判断该树是否为空
    public boolean isEmpty(){
        return this.nodeCount==0;
    }
    //在某个结点添加子节点,添加子节点的时候，数组容量++ ,此处为不自动扩容版本
    public void addChilds(T data,TreeNode<T> treeNode ){
            if(this.nodeCount>=capacity){
                //运行时候的抛出异常
                throw new RuntimeException("树容量不够，请扩容");
            }else {
                TreeNode node = new TreeNode(data,selectNodeIndex(treeNode));
                this.nodes[nodeCount]=node;
                nodeCount++;
            }
    }
    //为某个位置的结点添加子节点（如果存在该位置
    public void addChildsByIndex(T data,int index){
        if(index>=this.nodeCount){
            throw new RuntimeException("不存在该index位置的结点！");
        }
        TreeNode node = new TreeNode(data,index);
        this.nodes[nodeCount]=node;
        this.nodeCount++;
    }

    //判断一个结点在数组中的位置（index）
    public int selectNodeIndex(TreeNode treeNode){
        int index = -1; //-1默认不存在，这样return比较麻烦，还是学习网上的异常抛出
        for(int i = 0;i<this.nodeCount;i++){
            //数组中的结点等于传入结点
            if(nodes[i].equals(treeNode)){
                return i;
            }
        }
        throw new RuntimeException("树中不存在该结点数据");
    }
    //求树的深度(高度）,从子项向父项查找方便
    public int getTreeDegree(){
        int max=0; //空树为0
        for(int i=0;i<this.nodeCount;i++){
            int level = 0;
            int parentIndex = nodes[i].parent;
            while (parentIndex!=-1){
                level++;
                parentIndex = nodes[parentIndex].parent;
            }
            max = max >level ? max:level;//比较最大值
        }
        return max;
    }
    //求一个结点的所有孩子（子节点的父节点为该结点的index）
    public List<TreeNode> getChildsList(TreeNode treeNode){
        List<TreeNode> treeNodeList = new ArrayList<>();
        int index = selectNodeIndex(treeNode);//找到该结点的index，找不到会抛出异常
        for(int i=0;i<this.nodeCount;i++){
            //遍历节点的父项指向等于index，指向该结点
            if(this.nodes[i].parent==index){
                treeNodeList.add(nodes[i]);
            }
        }
//        if(treeNodeList.size()==0){
//        }
        return treeNodeList;
    }
    //获取一个节点的所有孩子，index查询方式只是理解逻辑使用
//    public List<TreeNode> getChildsList(int index){
//        List<TreeNode> treeNodeList = new ArrayList<>();
//        //如果index超过数组范围(大于当前节点数量或者index<0
//        if(index>=this.nodeCount||index<0){
//            throw new RuntimeException(" 无效位置！");
//        }
//        //对现有树(数组)进行循环
//        for(int i=0;i<this.nodeCount;i++){
//            //找到父节点为输入节点的值
//            if(this.nodes[i].parent==index){
//                treeNodeList.add(nodes[i]);//添加
//            }
//        }
//        return treeNodeList;
//    }

    /**
     * 查找一个节点的父节点(如果该父节点存在)
     * @param childNode
     * @return 父节点
     */
    public TreeNode<T> getParentNode(TreeNode<T> childNode){
        if(childNode.parent<0||childNode.parent>this.nodeCount){
            throw new RuntimeException("该节点无父节点");
        }
        return nodes[childNode.parent];
    }

    /**
     * 获取某个节点的度(孩子数个数)
     * @param node
     * @return
     */
    public int getChildDegree(TreeNode<T> node){
        List<TreeNode> list = new ArrayList<>();
        list = this.getChildsList(node);
        return list.size();
    }
    //清空树的所有结点(
    public void clear(){
        for(int i=0;i<this.nodeCount;i++){
             nodes[i]=null;//置为空
        }
        this.nodeCount=0;
    }
    public static void main(String[] args) {
        ParentRepresentationTree pp = new ParentRepresentationTree();//无参构造，默认为10
        System.out.println("是否为空？="+pp.isEmpty());
        ParentRepresentationTree pp2 = new ParentRepresentationTree(5);//数组长度为5
        pp.addRoot("sakura"); //根节点属性为sakura
        System.out.println(pp.getRoot().toString());
        pp.addChilds("ayane",pp.getRoot());
        pp.addChilds("siki",pp.getRoot());
        pp.addChildsByIndex("Onishi",2);
        for(int i=0;i<pp.nodeCount;i++){
            System.out.println(pp.nodes[i].toString());
        }
        System.out.println("整个树的度（深度）为="+pp.getTreeDegree());
        System.out.println("根节点的度为："+pp.getChildDegree(pp.getRoot()));
        System.out.println("是否为空？="+pp.isEmpty());
        List<TreeNode> list = pp.getChildsList(pp.getRoot());
        for(TreeNode ll:list){
            System.out.println("孩子节点是："+ll.toString());
        }

    }
}
