package org.xinyo.domain;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class ShangXiBean {
    private String id;
    private List<String> contentList;
    private String author;
    private Integer poetryId;
    private String source;

    public ShangXiBean(){

    }
    public ShangXiBean(ShangXi sx){
        this.id = sx.getId();
        this.author = sx.getAuthor();
        this.poetryId = sx.getPoetryId();
        this.source = sx.getSource();

        String content = sx.getContent();
        if (StringUtils.isNotEmpty(content)) {
            String[] split = content.split("\n");
            this.contentList = Arrays.asList(split);
        }

    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getPoetryId() {
        return poetryId;
    }

    public void setPoetryId(Integer poetryId) {
        this.poetryId = poetryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getContentList() {
        return contentList;
    }

    public void setContentList(List<String> contentList) {
        this.contentList = contentList;
    }
}
