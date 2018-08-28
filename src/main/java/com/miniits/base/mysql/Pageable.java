package com.miniits.base.mysql;

import org.springframework.util.ObjectUtils;

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

    private String sorts = "-createDate";

    //显示条数
    private Integer pageSize = 15;

    //页码
    private Integer pageNumber = 1;

    public Pageable addFilters(String filter) {
        this.filters += filter;
        return this;
    }

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

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Pageable() {
    }

    public Pageable(Object filters, Object pageSize, Object pageNumber) {
        this.filters = ObjectUtils.isEmpty(filters) ? "" : String.valueOf(filters);
        this.pageSize = ObjectUtils.isEmpty(pageSize) ? 15 : Integer.valueOf(String.valueOf(pageSize));
        this.pageNumber = ObjectUtils.isEmpty(pageNumber) ? 1 : Integer.valueOf(String.valueOf(pageNumber));
    }

    public Pageable(Integer pageSize, Integer pageNumber) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }
}
