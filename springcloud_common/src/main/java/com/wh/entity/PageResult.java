package com.wh.entity;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {
    private Long total;//记录数
    private List<T> rows;//结果集

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageResult() {
    }

    public Long getTotal() {
        return total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}