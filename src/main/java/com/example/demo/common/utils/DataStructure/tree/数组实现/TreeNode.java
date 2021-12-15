package com.example.demo.common.utils.DataStructure.tree.数组实现;

import java.util.Objects;

/*
底层实现结构是数组的树
双亲表示结点，结点需要记录自己双亲是谁，以及自身的值
 */
public class TreeNode<E> {

    public E data;//本身结点数据
    //该节点的父结点，记录的是父结点在数组中的索引位置，而不是父结点本身
    public int parent;
    public TreeNode(E data ,int parent){
        this.data = data;
        this.parent = parent;
    }
    public void setData(E data){
        this.data = data;
    }
    public E getData(){
        return this.data;
    }
    public void setParent(int index){
        this.parent = index;
    }
    public int getParent(){
        return this.parent;
    }
    //代码重写了equls https://blog.csdn.net/SnailMann/article/details/88607659
    //比较两个结点是否是同一个结点（不是比较的值，见注释）
    @Override
    public boolean equals(Object o) {
        if(this ==o){
            return true;
        }
        if(!(o instanceof TreeNode)){
            return false;
        }
        TreeNode<?> treeNode = (TreeNode<?>) o;
        //这里当父结点不一致的时候，直接return false了
        if (getParent() != treeNode.getParent()) {
            return false;
        }
        return getData() != null ? getData().equals(treeNode.getData()) : treeNode.getData() == null;
    }
    //重写了equals需要重写hashcode，目的是为了让相同的对象（equals相等的时候）,hashcode计算出相同的值。
    @Override
    public int hashCode() {
        return Objects.hash(getData(), getParent());
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "data=" + data +
                ", parent=" + parent +
                '}';
    }
}
