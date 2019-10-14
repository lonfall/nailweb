package com.lh.nailweb.vo.element;

import java.util.List;

/**
 * @auther: loneyfall
 * @date: 2019/10/14
 * @description: element ui 树节点表
 */
public class EleTreeNode<T> {
    private long id;
    // 数据
    private T data;
    // 子菜单
    private List<EleTreeNode<T>> children;
    // 是否包含子节点
    private boolean hasChildren;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<EleTreeNode<T>> getChildren() {
        return children;
    }

    public void setChildren(List<EleTreeNode<T>> children) {
        this.children = children;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }
}
