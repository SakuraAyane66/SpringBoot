package com.example.demo.common.utils.DataStructure.tree;

import org.apache.poi.ss.formula.functions.T;

import javax.swing.tree.TreeNode;
import java.util.List;

//树接口，具有树共有的方法，实现该接口的子类需要实现该接口所有方法
public interface CommonTree {

    //是否空树
    boolean isEmpty();
    //查询树的节点数量
    int getNodeNumber();
    //查询树的高度
    int getHeight();
    //为某个结点插入子结点
    boolean addNode(); //接口么有标准插入左还是右结点，暂时不考虑
    //设置根结点
    void setRoot(TreeNode treeNode);
    //返回根结点
    TreeNode getRpoot();
    //查询某个结点的所有子结点
    List<TreeNode> getAllChildsNodes(TreeNode node);
    //查询某个结点的父结点
    //查询某个结点的度
    //查询树的度
    //清空所有结点

    T getT();
    TreeNode getTreeNode();
}
