package com.wisedu.tShow.app.mail.service.impl;

import com.wisedu.tShow.app.mail.bo.Token;
import com.wisedu.tShow.app.mail.dao.TokenDao;
import com.wisedu.tShow.app.mail.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-29
 * Time: 上午9:55
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class TokenServiceImpl implements TokenService{
    @Autowired
    private TokenDao tokenDao;

    @Override
    @Transactional(readOnly = true)
    public Token getToken(Long id) {
        return tokenDao.getToken(id);
    }

    @Override
    public Long saveToken(Token entity) {
        return tokenDao.saveToken(entity);
    }

    @Override
    public void updateToken(Token entity) {
        tokenDao.updateToken(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Token> listToken(Map<String, Object> params) {
        return tokenDao.listToken(params);
    }
}
