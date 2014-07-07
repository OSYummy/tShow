package com.wisedu.core.common.page;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-6-18
 * Time: 下午2:22
 * To change this template use File | Settings | File Templates.
 */
public interface Paginable {
    /*总记录数*/
    public int getTotalSize();

    /*总页数*/
    public int getTotalPage();

    /*每页记录数*/
    public int getPageSize();

    /*当前页号*/
    public int getPageNo();

    /*是否是首页*/
    public boolean isFirstPage();

    /*是否是末页*/
    public boolean isLastPage();

    /*返回下页页号*/
    public int getNextPage();

    /*返回上页页号*/
    public int getPrePage();
}