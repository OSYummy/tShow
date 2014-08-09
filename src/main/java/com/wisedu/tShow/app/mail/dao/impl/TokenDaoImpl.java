package com.wisedu.tShow.app.mail.dao.impl;

import com.wisedu.core.hibernate.Finder;
import com.wisedu.core.hibernate.HibernateBaseDao;
import com.wisedu.tShow.app.mail.bo.Token;
import com.wisedu.tShow.app.mail.dao.TokenDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-29
 * Time: 上午9:29
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class TokenDaoImpl extends HibernateBaseDao<Token, Long> implements TokenDao{
    private final static String SELECT_TOKEN_HQL
            = "from Token t where 1=1 ";

    @Override
    protected Class<Token> getEntityClass() {
        return Token.class;
    }

    @Override
    public Token getToken(Long id) {
        return (Token)getSession().load(Token.class, id);
        /*return get(id);*/
    }

    @Override
    public Long saveToken(Token entity) {
        return (Long)getSession().save(entity);
    }

    @Override
    public void updateToken(Token entity) {
        getSession().update(entity);
    }

    @Override
    public List<Token> listToken(Map<String, Object> params) {
        Finder finder = Finder.create(SELECT_TOKEN_HQL);

        String userId = (String)params.get("userId");
        if (userId!=null && !"".equals(userId)){
            finder.append("and t.userId=:userId ");
            finder.setParam("userId", userId);
        }

        finder.append("order by t.tokenId asc");

        finder.setCacheable(true);
        return find(finder);
    }
}
