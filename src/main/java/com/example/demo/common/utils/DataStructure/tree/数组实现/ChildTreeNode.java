package com.example.demo.common.utils.DataStructure.tree.数组实现;

import java.util.ArrayList;
import java.util.List;

/**
 * 孩子表示结点，与双亲结点相似，但是记录的是该节点的孩子
 * 记录的孩子结点可以是数组的坐标index，也可以是孩子结点本身
 */
public class ChildTreeNode<T> {
     public T data; //本身的data属性,值
     public List<Integer> childList;//孩子结点index集合，在数组中的index
     //添加本身属性data
     public ChildTreeNode(T data){
         this.data = data;
         childList = new ArrayList<>();
    }
    //添加子树的index
     public void addChildList(Integer index){
         childList.add(index);
    }
    //判断该节点是否是叶子节点（是否有子树）
    public boolean isLeaf(){
         return childList.size() ==0 ? true :false;
    }
    //返回该节点的度（子树的个数）
    public int getDegree(){
         return childList.size();
    }
    //重写equals方法，就必须重写hashCode，用于比较两个结点是否相同
    @Override
    public boolean equals(Object obj) {
         if(this ==obj){ //如果当前对象等于比较对象obj，this指向当前对象，返回true
             return true;
         }
         //如果传入对象为null，或者getClass不同，返回false
         if(obj==null || obj.getClass() !=this.getClass()){
             return false;
         }
         //将obj对象强制类型转化为比较的对象，
         ChildTreeNode<T> childTreeNode = (ChildTreeNode<T>)obj;
         if(data!=null ? !data.equals(childTreeNode.data):childTreeNode.data!=null){
             return false;
         }
        return childList!=null ? childTreeNode.equals(childTreeNode.childList) : childTreeNode.childList==null;
    }
    //重写hashCode，然后因为重写了equals必须要重写hashcode方法
    @Override
    public int hashCode() {
        return (this.data !=null ? this.data.hashCode():0)+(this.childList !=null?this.childList.hashCode():0);
    }
    public String getList(){
         return new String(childList.toString());
    }
    @Override
    public String toString() {
        return "data? = "+this.data+"  " +
                "List?= "+getList();
    }
}
