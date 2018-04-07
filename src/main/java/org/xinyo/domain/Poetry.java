package org.xinyo.domain;

/**
 * Created by chengxinyong on 2018/3/27.
 */
public class Poetry {
    private Integer id;
    private String title;
    private String author;
    private String paragraphs;
    private String strains;
    private String dynasty;
    private Integer style;
    private Integer authorId;
    private String keywords;
    private String tags;

    public Poetry(){

    }

    public Poetry(Integer id) {
        this.id = id;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
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

    public String getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(String paragraphs) {
        this.paragraphs = paragraphs;
    }

    public String getStrains() {
        return strains;
    }

    public void setStrains(String strains) {
        this.strains = strains;
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

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Poetry{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", paragraphs='" + paragraphs + '\'' +
                ", strains='" + strains + '\'' +
                ", dynasty='" + dynasty + '\'' +
                ", style=" + style +
                ", authorId=" + authorId +
                ", keywords='" + keywords + '\'' +
                '}';
    }
}
