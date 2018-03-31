package org.xinyo.domain;

import java.util.List;

/**
 * Created by CHENG on 2018/3/27.
 */
public class PoetryBean {
    private Integer id;
    private String title;
    private String author;
    private String dynasty;
    private Integer style;
    private Integer authorId;
    private List<String> contentList;
    private String keyWords;
    private String description;

    public PoetryBean(){

    }
    public PoetryBean(Poetry poetry){
        super();
        this.setId(poetry.getId());
        this.setAuthor(poetry.getAuthor());
        this.setTitle(poetry.getTitle());
        this.setDynasty(poetry.getDynasty());
        this.setStyle(poetry.getStyle());
        this.setAuthorId(poetry.getAuthorId());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public List<String> getContentList() {
        return contentList;
    }

    public void setContentList(List<String> contentList) {
        this.contentList = contentList;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PoetryBean{" +
                "contentList=" + contentList +
                ", keyWords='" + keyWords + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
