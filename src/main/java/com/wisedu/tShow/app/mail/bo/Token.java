package com.wisedu.tShow.app.mail.bo;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-28
 * Time: 下午2:32
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "T_TSHOW_OUTLOOK_TOKEN")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Token implements Serializable {
    private static final long serialVersionUID = 1L;

    // 主键
    @Id
    @TableGenerator(
            name = "tokenGen",
            table = "S_TSHOW_OUTLOOK_TOKEN",
            pkColumnName = "SEQUENCE_NAME",
            pkColumnValue = "TOKEN_ID",
            valueColumnName = "SEQUENCE_NEXT_VALUE",
            initialValue = 0,
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tokenGen")
    @Column(name = "TOKEN_ID", nullable = false, unique = true, precision = 18, scale = 0)
    private Long tokenId;

    // 刷新令牌
    @Column(name = "REFRESH_TOKEN", nullable = false)
    private String refreshToken;

    // 用户ID
    @Column(name = "USER_ID", nullable = false)
    private String userId;

    public Token() {

    }

    public Token(String refreshToken, String userId) {
        this.refreshToken = refreshToken;
        this.userId = userId;
    }

    public Long getTokenId() {
        return tokenId;
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
