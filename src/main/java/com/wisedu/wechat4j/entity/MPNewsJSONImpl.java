package com.wisedu.wechat4j.entity;

import java.io.Serializable;
import java.util.List;

public final class MPNewsJSONImpl implements Serializable {
    private static final long serialVersionUID = 7170334539093514524L;

    private List<String> touser;
    private List<String> toparty;
    private List<String> totag;
    private static final String msgtype;
    private String agentid;
    private List<Article> articles;

    static {
        msgtype = "mpnews";
    }

    public MPNewsJSONImpl() {
    }

    public MPNewsJSONImpl(List<String> touser, List<String> toparty, List<String> totag, String agentid, List<Article> articles) {
        this.touser = touser;
        this.toparty = toparty;
        this.totag = totag;
        this.agentid = agentid;
        this.articles = articles;
    }

    public List<String> getTouser() {
        return touser;
    }

    public void setTouser(List<String> touser) {
        this.touser = touser;
    }

    public List<String> getToparty() {
        return toparty;
    }

    public void setToparty(List<String> toparty) {
        this.toparty = toparty;
    }

    public List<String> getTotag() {
        return totag;
    }

    public void setTotag(List<String> totag) {
        this.totag = totag;
    }

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
