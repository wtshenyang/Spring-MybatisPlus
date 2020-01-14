package com.iflytek.springboot.base.tags;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页信息。
 */
public class PageUtil<T> {
    private static final long serialVersionUID = 1L;
    private int pageSize = 10;
    /**
     * 每页显示几条
     */
    private int total = 0;
    /**
     * 总条数
     */
    private int currentPage = 1;
    /**
     * 当前页
     */
    private int totalPage;
    /**
     * 总页数
     */
    private boolean isQueryTotal = false;//是否已查询总页数,用于检测分页参数是否合法使用

    private int currentResult = 0;
    /**
     * 当前记录起始索引
     */
    private int startNum = -1;
    /**
     * 查询起始位置，特殊情况下使用，即直接查出结果集，不需要分页信息
     */
    private List<T> dataList = new ArrayList<T>();
    /**
     * 存放结果集
     */
    private String sortName;
    private String sortOrder;
    private String dataSource = "oracle";//mysql
    private boolean isCount = true;//是否需要统计总数

    public PageUtil() {

    }

    public PageUtil(int pageSize, int currentPage) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    public PageUtil(int total, int currentPage, int showCount) {
        setTotal(total);
        this.setPageSize(showCount);
        setCurrentPage(currentPage);
        this.getTotalPage();
    }

    /**
     * @return the isCount
     */
    public boolean isCount() {
        return isCount;
    }

    /**
     * @param isCount the isCount to set
     */
    public void setCount(boolean isCount) {
        this.isCount = isCount;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public boolean isQueryTotal() {
        return isQueryTotal;
    }

    public void setQueryTotal(boolean isQueryTotal) {
        this.isQueryTotal = isQueryTotal;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public int getCurrentResult() {
        if (getStartNum() != -1) {
            return getStartNum();
        }
        currentResult = (getLegalCurrentPage() - 1) * getPageSize();
        if (currentResult < 0) {
            currentResult = 0;
        }
        return currentResult;
    }

    public void setCurrentResult(int currentResult) {
        this.currentResult = currentResult;
    }

    public List<T> getDataList() {
        if (dataList == null) {
            return new ArrayList<T>();
        }
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    /**
     * 获取总页数
     */
    public int getTotalPage() {
        if (pageSize <= 0) {
            return 0;
        }
        if (total % pageSize == 0) {
            totalPage = total / pageSize;
        } else {
            totalPage = total / pageSize + 1;
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * 获取总条数
     */
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrentPage() {
        if (currentPage <= 0) {
            currentPage = 1;
        }
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getLegalCurrentPage() {
        if (currentPage <= 0) {
            currentPage = 1;
        }
        if (isQueryTotal) {
            if (currentPage > getTotalPage()) {
                currentPage = getTotalPage();
            }
        }
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int size) {
        if (size == 0) {
            size = 10;
        }
        this.pageSize = size;
    }

    public int getStartNum() {
        return startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

}
