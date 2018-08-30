package org.xinyo.domain;

import org.apache.commons.lang3.StringUtils;
import org.xinyo.util.JsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
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
    private List tags;

    public PoetryBean(){

    }
    public PoetryBean(Poetry poetry){
        this.id = poetry.getId();
        this.author = poetry.getAuthor();
        this.authorId = poetry.getAuthorId();
        this.title = poetry.getTitle();
        this.dynasty = poetry.getDynasty();
        this.style = poetry.getStyle();
        this.keywords = JsonUtils.jsonToList(poetry.getKeywords());
        this.tags = JsonUtils.jsonToList(poetry.getTags());

        String paragraphs = poetry.getParagraphs();
        if (StringUtils.isNotEmpty(paragraphs)) {
            String[] split = paragraphs.split("\n");
            this.contentList = Arrays.asList(split);
        }
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

    public List getTags() {
        return tags;
    }

    public void setTags(List tags) {
        this.tags = tags;
    }
}
