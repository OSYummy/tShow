package com.wisedu.core.common.page;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-6-18
 * Time: 下午2:21
 * To change this template use File | Settings | File Templates.
 */

public class Pagination extends SimplePage implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<?> list;

    public Pagination() {
    }

    public Pagination(int totalSize, int pageSize, int pageNo) {
        super(totalSize, pageSize, pageNo);
    }

    public Pagination(int totalSize, int pageSize, int pageNo, List<?> list) {
        super(totalSize, pageSize, pageNo);
        this.list = list;
    }

    public int getFirstResult(){
        return (pageNo-1)*pageSize;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}