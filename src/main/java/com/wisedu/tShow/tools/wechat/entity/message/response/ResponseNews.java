package com.wisedu.tShow.tools.wechat.entity.message.response;

import com.wisedu.tShow.tools.wechat.entity.message.BaseMessage;
import com.wisedu.tShow.tools.wechat.utils.AdapterCDATA;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-15
 * Time: 下午5:09
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"ToUserName", "FromUserName", "CreateTime", "MsgType", "ArticleCount", "Articles"})
public class ResponseNews extends BaseMessage{
    // 开发者微信号
    @XmlElement(name = "ToUserName", required = true)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String ToUserName;

    // 发送方帐号
    @XmlElement(name = "FromUserName", required = true)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String FromUserName;

    // 消息创建时间
    @XmlElement(name = "CreateTime", required = true)
    private Integer CreateTime;

    // 消息类型,news
    @XmlElement(name = "MsgType", required = true)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private String MsgType;

    // 图文消息个数，限制为10条以内
    @XmlElement(name = "ArticleCount", required = true)
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    private int ArticleCount;

    // 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
    @XmlElementWrapper(name = "Articles")
    @XmlElement(name = "item", required = true)
    private List<Article> Articles;

    public ResponseNews() {

    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Integer getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Integer createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        Articles = articles;
    }
}
