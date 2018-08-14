package org.xinyo.domain;

public class ShangXi {
    private String id;
    private String title;
    private String poemAuthor;
    private String poemBegin;
    private String content;
    private String author;
    private Integer poetryId;
    private String source;

    public ShangXi(){

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

    public String getPoemBegin() {
        return poemBegin;
    }

    public void setPoemBegin(String poemBegin) {
        this.poemBegin = poemBegin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoemAuthor() {
        return poemAuthor;
    }

    public void setPoemAuthor(String poemAuthor) {
        this.poemAuthor = poemAuthor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
