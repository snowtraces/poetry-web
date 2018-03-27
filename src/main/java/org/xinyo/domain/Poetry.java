package org.xinyo.domain;

/**
 * Created by chengxinyong on 2018/3/27.
 */
public class Poetry {
    private Integer id;
    private String title;
    private String title_sp;
    private String author;
    private String author_sp;
    private String paragraphs;
    private String paragraphs_sp;
    private String strains;
    private String dynasty;
    private Integer style;

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

    public String getTitle_sp() {
        return title_sp;
    }

    public void setTitle_sp(String title_sp) {
        this.title_sp = title_sp;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor_sp() {
        return author_sp;
    }

    public void setAuthor_sp(String author_sp) {
        this.author_sp = author_sp;
    }

    public String getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(String paragraphs) {
        this.paragraphs = paragraphs;
    }

    public String getParagraphs_sp() {
        return paragraphs_sp;
    }

    public void setParagraphs_sp(String paragraphs_sp) {
        this.paragraphs_sp = paragraphs_sp;
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

    @Override
    public String toString() {
        return "Poetry{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", title_sp='" + title_sp + '\'' +
                ", author='" + author + '\'' +
                ", author_sp='" + author_sp + '\'' +
                ", paragraphs='" + paragraphs + '\'' +
                ", paragraphs_sp='" + paragraphs_sp + '\'' +
                ", strains='" + strains + '\'' +
                ", dynasty='" + dynasty + '\'' +
                ", style=" + style +
                '}';
    }
}
