package org.xinyo.domain;

/**
 * Created by chengxinyong on 2018/4/28.
 */
public class TagRelation {
    private Integer id;
    private String tagA;
    private String tagB;
    private Integer connection;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagA() {
        return tagA;
    }

    public void setTagA(String tagA) {
        this.tagA = tagA;
    }

    public String getTagB() {
        return tagB;
    }

    public void setTagB(String tagB) {
        this.tagB = tagB;
    }

    public Integer getConnection() {
        return connection;
    }

    public void setConnection(Integer connection) {
        this.connection = connection;
    }
}
