package org.xinyo.domain;

import org.xinyo.util.JsonUtil;

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
    private String description;
    private List keywords;

    public PoetryBean(){

    }
    public PoetryBean(Poetry poetry){
        this.id = poetry.getId();
        this.author = poetry.getAuthor();
        this.authorId = poetry.getAuthorId();
        this.title = poetry.getTitle();
        this.dynasty = poetry.getDynasty();
        this.style = poetry.getStyle();
        this.keywords = JsonUtil.jsonToList(poetry.getKeywords());
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List getKeywords() {
        return keywords;
    }

    public void setKeywords(List keywords) {
        this.keywords = keywords;
    }
}
