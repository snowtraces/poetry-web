package org.xinyo.domain;

/**
 * Created by chengxinyong on 2018/3/30.
 */
public class SearchResult {
    private Integer id;
    private String keyword;
    private Integer total;
    private String top100Id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getTop100Id() {
        return top100Id;
    }

    public void setTop100Id(String top100Id) {
        this.top100Id = top100Id;
    }
}
