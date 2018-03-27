package org.xinyo.domain;

import java.util.List;

/**
 * Created by CHENG on 2018/3/27.
 */
public class PoetryBean extends Poetry{
    private List<String> contentList;
    private String keyWords;
    private String description;

    public PoetryBean(){

    }
    public PoetryBean(Poetry poetry){
        super();
        this.setId(poetry.getId());
        this.setAuthor(poetry.getAuthor());
        this.setAuthor_sp(poetry.getAuthor_sp());
        this.setTitle(poetry.getTitle());
        this.setTitle_sp(poetry.getTitle_sp());
        this.setParagraphs(poetry.getParagraphs());
        this.setParagraphs_sp(poetry.getParagraphs_sp());
        this.setStrains(poetry.getStrains());
        this.setDynasty(poetry.getDynasty());
        this.setStyle(poetry.getStyle());
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
