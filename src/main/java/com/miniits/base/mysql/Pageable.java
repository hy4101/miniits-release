package com.miniits.base.mysql;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/1/7
 * @Time: 13:54
 * <p>
 * Description:
 * ***
 */
public class Pageable {

    private String filters;

    private String sorts;

    private Integer page;

    private Integer size;

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public String getSorts() {
        return sorts;
    }

    public void setSorts(String sorts) {
        this.sorts = sorts;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
