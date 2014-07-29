package com.wisedu.tShow.app.mail.dao;

import com.wisedu.tShow.app.mail.bo.Token;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-29
 * Time: 上午9:28
 * To change this template use File | Settings | File Templates.
 */
public interface TokenDao {
    public Token getToken(Long id);

    public Long saveToken(Token entity);

    public void updateToken(Token entity);

    public List<Token> listToken(Map<String, Object> params);
}
