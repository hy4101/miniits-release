package com.miniits.base.utils;

import java.util.List;

/**
 * @author: wq
 * @Date: 2018/1/5
 * @Time: 17:13
 * <p>
 * Description:
 * ***
 */
public class Result {

    private boolean isSuccess;

    /**
     * 每页条数
     */
    private int total = 15;

    /**
     * 当前页码
     */
    private int page = 1;

    /**
     * 总页码数
     */
    private int totalPage;

    /**
     * 总记录数
     */
    private long totalCount;

    private Integer errorCode;

    private String message;

    private Object object;

    private List<Object> rows;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public List<Object> getRows() {
        return rows;
    }

    public void setRows(List<Object> rows) {
        this.rows = rows;
    }

    public Result() {
    }

    public Result(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Result size(int size) {
        this.total = total;
        return this;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Result page(int page) {
        this.page = page;
        return this;
    }

    public int getTotalPage() {
        if (this.getTotalCount() % this.getTotal() > 0) {
            this.setTotalPage(((int) this.getTotalCount() / this.getTotal()) + 1);
        } else {
            this.setTotalPage((int) this.getTotalCount() / this.getTotal());
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public Result totalCount(long totalCount) {
        this.totalCount = totalCount;
        return this;
    }
}
