package com.example.demo.common.utils.DataStructure.tree.兄弟孩子表示法;

import javafx.scene.CacheHint;

import java.util.ArrayList;
import java.util.List;

/*
 兄弟孩子表示方法，底层只能用链表实现，有一个数据域（本身数据），两个指针域，使用链表实现，一个指向孩子的第一个子结点，一个是自己的兄弟结点。
 兄弟结点不是使用List来存储，是通过层层递归推算的兄弟，一个结点只有自己的一个兄弟结点（指向自己下一个的），如果有多个兄弟，也是如此，最后一个兄弟结点
 只是由它的前一个兄弟结点的sibling来指向的，而不是前面所有的兄弟都有List来进行包含，这样的空间消耗太大了
 data存放结点的数据
 child指向当前结点第一个子结点
 sibling指向自己的兄弟结点
 */
public class ChildSiblingPepresentationTree<T>{
     //树的结点数量
    private int conut;
    //
    private TreeNode<T> root; //根结点
    //内部结点类实现
    class TreeNode<T>{
        private T data; //数据域
        private TreeNode<T> child,sibling; //第一个孩子结点，以及兄弟结点（只留下一个的）

        TreeNode(T data){
            this.data = data;
            //初始默认也为null
            this.child = null;
            this.sibling = null;
        }
        TreeNode(T data,TreeNode<T> child,TreeNode<T> sibling){
            this.data = data;
            this.child = child;
            this.sibling = sibling;
        }
        //判断两个结点是否相等
        @Override
        public boolean equals(Object obj) {
            if(this ==obj){ //如果当前对象等于比较对象obj，this指向当前对象，返回true
                return true;
            }
            //如果传入对象为null，或者getClass不同，返回false
            if(obj==null || obj.getClass() !=this.getClass()){
                return false;
            }
            //强制类型转换
            TreeNode node = (TreeNode)obj;
            //如果两个对象的data不一致，返回false（按照idea的推荐更改）
            if(!node.data.equals(this.data) || !node.child.equals(this.child)||!node.sibling.equals(this.sibling)){
                return false;
            }
            return true;
        }
        //重写equals重写hashCode
        @Override
        public int hashCode() {
            int index = (this.data !=null ? this.data.hashCode():0) + (this.child!=null ? this.child.hashCode():0)+(
                    this.sibling!=null ? this.sibling.hashCode():0);
            return index;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "data=" + data +
                    ", child=" + child +
                    ", sibling=" + sibling +
                    '}';
        }
    }

    public ChildSiblingPepresentationTree(){
        this.conut=0;
        this.root= null;
    }
    public ChildSiblingPepresentationTree(TreeNode<T> node){
        this.root = node;
        this.conut++;//count++
    }
    //添加根结点
    public void addRoot(T data){
        if(!isEmpty()){
            return;
        }
        TreeNode node = new TreeNode(data);
        this.root = node;
    }
    //为某一个结点添加子结点
    public void addNode(TreeNode<T> parent,T data){
        TreeNode<T> node = new TreeNode<>(data);//新建一个需要添加进树的结点
        this.conut++;//树的结点数量+1
        //如果该父结点没有孩子结点
        if(parent.child==null){
            parent.child = node; //该父结点孩子结点指向新结点
        }else{
            //如果该父结点孩子结点不为null(有孩子结点了)，找到该孩子结点的最后一个sibling结点，添加到后面
            TreeNode child = parent.child; //父结点的第一个孩子
            while (parent.child.sibling!=null){
                 child = child.sibling;
            }
             child.sibling = node;//在父结点的孩子结点末尾添加新的结点
        }
    }
    //判断是否为空树
    public boolean isEmpty(){
        return conut ==0 ? true:false;
    }
    //判断树的深度(还是无法写出代码,参考的是网上的那个代码)
    public int getDeep(TreeNode<T> treeNode){
        if (treeNode == null) {
            return 0;
        }
        if (listChildNode(treeNode).size() == 0) {
            return 1;
        }
        //说白了就是比较子树层级的最大值，的最大子树高
        int max = 0;
        for (TreeNode<T> childNode : listChildNode(treeNode)) {
            int level = getDeep(childNode);
            max = max > level ? max : level;
        }
        //然后最大子树的高 + 1 ，就是本树的高，反馈给上一级别
        return max + 1;
    }
    //求出该结点的所有孩子结点
    public List<TreeNode<T>> listChildNode(TreeNode<T> treeNode){
        List<TreeNode<T>> childList = new ArrayList<>();//存储孩子结点的list
        if(treeNode==null){
            return childList;//空结点没有结点
        }
        //该结点的孩子
        if(treeNode.child !=null){
            childList.add(treeNode.child);//将该结点的孩子结点添加
        }else if(treeNode.child.sibling!=null){
            //孩子结点的兄弟结点不为null
            TreeNode<T> node = treeNode.child; //孩子结点
            while (node.sibling!=null){
                //添加进list
                childList.add(node.sibling);
                node = node.sibling;
            }
        }
        return childList;
    }
    //判断树的度（树的最大度，最大叶子结点树) ,遍历树
    private int getDegree(){
        return degreeForTree(this.root);//返回根的度
    }
    //求子树的度
    private int degreeForTree(TreeNode<T> node) {
        //空树的度为0
        if (node == null) {
            return 0;
        }

        //默认最大值
        int max = listChildNode(node).size();

        //叶子结点的度也为0
        if (listChildNode(node).size() <= 0) {
            return 0;
        }

        for (TreeNode<T> childNode : listChildNode(node)) {
            int degree = degreeForTree(childNode);
            max = max > degree ? max : degree;
        }
        return max;
    }

    //判断树有多少叶子结点
    //判断树有多少非根和非叶子结点

}
