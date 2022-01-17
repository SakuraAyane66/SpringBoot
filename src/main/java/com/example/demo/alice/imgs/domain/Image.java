package com.example.demo.alice.imgs.domain;

/**
 * 图片实体类
 * url 只是图片最后路径的url，前面的path通过serviceImpl添加（为了后续方便移植等）
 */
public class Image {
    private int id;
    private String url;
    public Image(){}

    public Image(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
