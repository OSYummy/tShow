package com.wisedu.core.page;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-6-18
 * Time: 下午2:22
 * To change this template use File | Settings | File Templates.
 */
public class SimplePage implements Paginable{
    protected final static Integer DEFAULT_PAGESIZE=20;

    protected int totalSize = 0;
    protected int pageSize = 20;
    protected int pageNo = 1;

    public SimplePage(){
    }

    public SimplePage(int totalSize, int pageSize, int pageNo) {
        setTotalSize(totalSize);
        setPageSize(pageSize);
        setPageNo(pageNo);
    }

    public static int checkPageNo(Integer pageNo){
        return (pageNo==null || pageNo<1)? 1: pageNo;
    }

    public void adjustPageNo(){
        if (pageNo == 1) return;

        int tp=getTotalPage();

        if (pageNo>tp) pageNo=tp;
    }
    @Override
    public int getTotalSize() {
        return totalSize;
    }

    @Override
    public int getTotalPage() {
        int totalPage=totalSize/pageSize;

        if (totalPage==0 || totalSize%pageSize!=0)
            totalPage++;

        return totalPage;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getPageNo() {
        return pageNo;
    }

    @Override
    public boolean isFirstPage() {
        return pageNo <= 1;
    }

    @Override
    public boolean isLastPage() {
        return pageNo >= getTotalPage();
    }

    @Override
    public int getNextPage() {
        if (isLastPage()) return pageNo;
        else return pageNo+1;
    }

    @Override
    public int getPrePage() {
        if (isFirstPage()) return pageNo;
        else return pageNo-1;
    }


    public void setTotalSize(int totalSize) {
        if (totalSize < 0) this.totalSize=0;
        else this.totalSize = totalSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 1) this.pageSize=DEFAULT_PAGESIZE;
        else this.pageSize = pageSize;
    }

    public void setPageNo(int pageNo) {
        if (pageNo < 1) this.pageNo=1;
        else this.pageNo = pageNo;
    }
}