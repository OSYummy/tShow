package com.wisedu.core.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-6-18
 * Time: 下午2:24
 * To change this template use File | Settings | File Templates.
 */
public class Finder {
    private StringBuilder hqlBulder;
    private int firstResult=0;
    private int maxResult=0;
    private boolean cacheable=false;

    private List<String> params;
    private List<Object> values;
    private List<Type> types;

    private List<String> paramsList;
    private List<Collection<Object>> valuesList;
    private List<Type> typesList;

    private List<String> paramsArray;
    private List<Object[]> valuesArray;
    private List<Type> typesArray;

    public Finder() {
    }

    public Finder(String hql) {
        this.hqlBulder = new StringBuilder(hql);
    }

    public Finder create(){
        return new Finder();
    }

    public static Finder create(String hql){
        return new Finder(hql);
    }

    public Finder append(String hql){
        hqlBulder.append(hql);
        return this;
    }

    public String getOriginHQL(){
        return hqlBulder.toString();
    }

    public String getRowCountHQL(){
        String hql=hqlBulder.toString();

        int fromIndex=hql.toLowerCase().indexOf("from");
        String selectHQL=hql.substring(0, fromIndex);

        hql=hql.substring(fromIndex);

        String countHQL=hql.replace("fetch", "");
        int orderIndex=countHQL.indexOf("order");

        if (orderIndex > 0){
            countHQL=countHQL.substring(0, orderIndex);
        }

        return wrapSelectHQL(selectHQL)+countHQL;
    }

    public String wrapSelectHQL(String selectHQL){
        if (selectHQL.indexOf("select") == -1)
            return "select count(*) ";
        else return selectHQL.replace("select", "select count(")+")";
    }

    public Query createQuery(Session session){
        Query query=setParamsToQuery(session.createQuery(getOriginHQL()));

        if (getFirstResult() > 0)
            query.setFirstResult(getFirstResult());

        if (getMaxResult() > 0)
            query.setMaxResults(getMaxResult());

        query.setCacheable(isCacheable());

        return query;
    }

    public Query setParamsToQuery(Query query){
        if (params != null){
            for (int i=0; i<params.size(); i++){
                if (types.get(i) == null)
                    query.setParameter(params.get(i), values.get(i));
                else query.setParameter(params.get(i), values.get(i), types.get(i));
            }
        }

        if (paramsList != null){
            for (int i=0; i<paramsList.size(); i++){
                if(typesList.get(i) == null)
                    query.setParameter(paramsList.get(i), valuesList.get(i));
                else query.setParameter(paramsList.get(i), valuesList.get(i), typesList.get(i));
            }
        }

        if (paramsArray != null){
            for (int i=0; i<paramsArray.size(); i++){
                if (typesArray.get(i) == null)
                    query.setParameter(paramsArray.get(i), valuesArray.get(i));
                else query.setParameter(paramsArray.get(i), valuesArray.get(i), typesArray.get(i));
            }
        }

        return query;
    }

    public int getFirstResult() {
        return firstResult;
    }

    public void setFirstResult(int firstResult) {
        this.firstResult = firstResult;
    }

    public int getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(int maxResult) {
        this.maxResult = maxResult;
    }

    public boolean isCacheable() {
        return cacheable;
    }

    public void setCacheable(boolean cacheable) {
        this.cacheable = cacheable;
    }

    public List<Type> getTypes() {
        if (types == null)
            types=new ArrayList<Type>();
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public List<String> getParams() {
        if (params==null)
            params=new ArrayList<String>();
        return params;
    }

    public void setParams(Map<String, Object> params) {
        for (Map.Entry<String, Object> entry: params.entrySet()){
            setParam(entry.getKey(), entry.getValue());
        }
    }

    public Finder setParam(String param, Object value){
        return setParam(param, null, value);
    }

    public Finder setParam(String param, Type type, Object value){
        getParams().add(param);
        getTypes().add(type);
        getValues().add(value);

        return this;
    }

    public List<Object> getValues() {
        if (values == null)
            values=new ArrayList<Object>();
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }

    public List<String> getParamsList() {
        return paramsList;
    }

    public Finder setParamsList(String param, Collection<Object> value, Type type) {
        getParamsList().add(param);
        getValuesList().add(value);
        getTypesList().add(type);
        return this;
    }

    public Finder setParamsList(String param, Collection<Object> value) {
        return setParamsList(param, value, null);
    }

    public Finder setParamsList(String param, Object[] value, Type type) {
        getParamsArray().add(param);
        getValues().add(value);
        getTypesArray().add(type);
        return this;
    }

    public Finder setParamsList(String param, Object[] value) {
        return setParamsList(param, value, null);
    }

    public List<Collection<Object>> getValuesList() {
        return valuesList;
    }

    public void setValuesList(List<Collection<Object>> valuesList) {
        this.valuesList = valuesList;
    }

    public List<Type> getTypesList() {
        return typesList;
    }

    public void setTypesList(List<Type> typesList) {
        this.typesList = typesList;
    }

    public List<String> getParamsArray() {
        return paramsArray;
    }

    public void setParamsArray(List<String> paramsArray) {
        this.paramsArray = paramsArray;
    }

    public List<Object[]> getValuesArray() {
        return valuesArray;
    }

    public void setValuesArray(List<Object[]> valuesArray) {
        this.valuesArray = valuesArray;
    }

    public List<Type> getTypesArray() {
        return typesArray;
    }

    public void setTypesArray(List<Type> typesArray) {
        this.typesArray = typesArray;
    }
}