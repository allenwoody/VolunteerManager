package com.allen.core.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
* @ClassName: JsonTreeNode 
* @Description: 封装fancytree data
* @author wenquan
* @date 2016年1月25日 下午5:52:43 
*
 */
public class JsonTreeNode<T> implements Cloneable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1747359877349665356L;
	
	private String title;
	
	private String key;

	private boolean folder = true;
	
	private boolean lazy = false;
	
	private T data;
	
	private List<JsonTreeNode<T>> children;
	
	public JsonTreeNode() {
		this(null, null);
	}

	public JsonTreeNode(String key, String title) {
		this.key = key;
		this.title = title;
		this.children = new ArrayList<JsonTreeNode<T>>();
	}
	
	public JsonTreeNode(String key, String title, T data) {
		this.key = key;
		this.title = title;
		this.data = data;
		this.children = new ArrayList<JsonTreeNode<T>>();
	}

	public void addChild(JsonTreeNode<T> ctd) {
		this.children.add(ctd);
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean isFolder() {
		return folder;
	}

	public void setFolder(boolean folder) {
		this.folder = folder;
	}

	public boolean isLazy() {
		return lazy;
	}

	public void setLazy(boolean lazy) {
		this.lazy = lazy;
	}

	public List<JsonTreeNode<T>> getChildren() {
		return children;
	}

	public void setChildren(List<JsonTreeNode<T>> children) {
		this.children = children;
	}

	public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
	/**
	 * 利用序列化实现深度克隆
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return SerializationUtils.clone(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
