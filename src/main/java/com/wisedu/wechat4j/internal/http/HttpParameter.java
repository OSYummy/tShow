package com.wisedu.wechat4j.internal.http;

import com.wisedu.wechat4j.internal.json.JSONObject;
import weibo4j.org.json.JSONArray;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * A data class representing HTTP Post parameter
 */
public class HttpParameter implements Comparable<HttpParameter>, Serializable {
    private static final long serialVersionUID = -5372286948430380870L;

    private String name = null;
    private String value = null;
    private JSONObject jsonObject = null;
    private JSONArray jsonArray = null;
    private File file = null;
    private InputStream fileBody = null;

    public HttpParameter(String name, File file) {
        this.name = name;
        this.file = file;
    }

    public HttpParameter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public HttpParameter(JSONObject jsonObject){
        this.jsonObject = jsonObject;
    }

    public HttpParameter(JSONArray jsonArray){
        this.jsonArray = jsonArray;
    }

    public HttpParameter(String name, int value){
        this.name = name;
        this.value = Integer.toString(value);
    }

    public HttpParameter(String name, float value){
        this.name = name;
        this.value = Float.toString(value);
    }

    public HttpParameter(String name, boolean value){
        this.name = name;
        this.value = Boolean.toString(value);
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public JSONObject getJSONObject(){
        return jsonObject;
    }

    public JSONArray getJsonArray(){
        return jsonArray;
    }

    public File getFile() {
        return file;
    }

    public InputStream getFileBody() {
        return fileBody;
    }

    public boolean isFile(){
        return file != null;
    }

    public boolean hasFileBody(){
        return fileBody != null;
    }

    private static final String JPEG = "image/jpeg";
    private static final String GIF = "image/gif";
    private static final String PNG = "image/png";
    private static final String OCTET = "application/octet-stream";

    public String getContentType(){
        if (!isFile()){
            throw new IllegalStateException("not a file");
        }
        String contentType;
        String extensions = file.getName();
        int index = extensions.lastIndexOf('.');
        if (-1 == index){
            // no extension
            contentType = OCTET;
        } else {
            String extension = extensions.substring(index+1).toLowerCase();
            if ("jpg".equals(extension)){
                contentType = JPEG;
            } else if ("jpeg".equals(extension)){
                contentType = JPEG;
            } else if ("gif".equals(extension)){
                contentType = GIF;
            } else if ("png".equals(extension)){
                contentType = PNG;
            } else {
                contentType = OCTET;
            }
        }
        return contentType;
    }

    public static boolean containsJSON(HttpParameter[] params) {
        boolean containsJSON = false;
        for (HttpParameter param: params){
            if (param.isJSONObject() || param.isJSONArray()){
                containsJSON = true;
                break;
            }
        }
        return containsJSON;
    }

    public static boolean containsJSON(List<HttpParameter> params) {
        boolean containsJSON = false;
        for (HttpParameter param: params){
            if (param.isJSONObject() || param.isJSONArray()){
                containsJSON = true;
                break;
            }
        }
        return containsJSON;
    }

    public boolean isJSONObject(){
        return jsonObject != null;
    }

    public boolean isJSONArray(){
        return jsonArray != null;
    }

    public boolean isJSON(){
        return isJSONObject() || isJSONArray();
    }

    public boolean isValue() {
        return value != null;
    }

    public static boolean containsFile(HttpParameter[] params){
        boolean containsFiles = false;
        if (params == null){
            return containsFiles;
        }
        for (HttpParameter param : params){
            if (param.isFile()){
                containsFiles = true;
                break;
            }
        }
        return containsFiles;
    }

    public static boolean containsFile(List<HttpParameter> params){
        boolean containsFiles = false;
        for (HttpParameter param : params){
            if (param.isFile()){
                containsFiles = true;
                break;
            }
        }
        return containsFiles;
    }

    public static String encodeParameters(HttpParameter[] params){
        if (params == null){
            return "";
        }

        StringBuffer sb = new StringBuffer();
        for (int i=0; i<params.length; i++){
            if (i != 0){
                sb.append("&");
            }
            sb.append(encode(params[i].name))
                    .append("=").append(encode(params[i].value));
        }
        return sb.toString();
    }

    /**
     * @param value String to be encoded
     * @return encoded String
     * @see <a href="http://tools.ietf.org/html/rfc3986#section-2.1">RFC 3986 - Uniform Resource Identifier (URI): Generic Syntax - 2.1. Percent-Encoding</a>
     */
    public static String encode(String value){
        String encode = null;
        try {
            encode = URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException uee){

        }
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<encode.length(); i++){
            char c = encode.charAt(i);
            switch (c){
                case '*':
                    sb.append("%2A");
                    break;
                case '+':
                    sb.append("%20");
                    break;
                case '%':
                    if (i+1<encode.length()
                            && encode.charAt(i+1)=='7' && encode.charAt(i+2)=='E'){
                        sb.append('~');
                    } else {
                        sb.append(c);
                    }
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }

    public static HttpParameter[] merge(HttpParameter[] params_1, HttpParameter[] params_2){
        HttpParameter[] parameters;
        if (params_1==null && params_2==null){
            parameters =  new HttpParameter[]{};
        } else if (params_1 == null){
            parameters =  params_2;
        } else if (params_2 == null){
            parameters = params_1;
        } else {
            parameters = new HttpParameter[params_1.length + params_2.length];
            System.arraycopy(params_1, 0, parameters, 0, params_1.length);
            System.arraycopy(params_2, 0, parameters, params_1.length, params_2.length);
        }
        return parameters;
    }

    @Override public int compareTo(HttpParameter o){
        int compared = name.compareTo(o.name);
        if (compared==0){
            compared = value.compareTo(o.value);
        }
        return compared;
    }

    @Override public boolean equals(Object o){
        if (o == this) return true;
        if (o==null || getClass()!=o.getClass())
            return false;

        HttpParameter that = (HttpParameter)o;
        if (name!=null? !name.equals(that.name): that.name!=null)
            return false;
        if (value!=null? !value.equals(that.value): that.value!=null)
            return false;
        if (jsonObject!=null? !jsonObject.equals(that.jsonObject): that.jsonObject!=null)
            return false;
        if (jsonArray!=null? !jsonArray.equals(that.jsonArray): that.jsonArray!=null)
            return false;
        if (file!=null? !file.equals(that.file): that.file!=null)
            return false;
        if (fileBody!=null? !fileBody.equals(that.fileBody): that.fileBody!=null)
            return false;

        return true;
    }

    @Override public int hashCode(){
        int result = 0;
        result = result*31 + (name!=null? name.hashCode(): 0);
        result = result*31 + (value!=null? value.hashCode(): 0);
        result = result*31 + (jsonObject!=null? jsonObject.hashCode(): 0);
        result = result*31 + (jsonArray!=null? jsonArray.hashCode(): 0);
        result = result*31 + (file!=null? file.hashCode(): 0);
        result = result*31 + (fileBody!=null? fileBody.hashCode(): 0);
        return result;
    }

    @Override public String toString(){
        return "HttpParameter"
                + "{"
                + "name=" + "\'" + name + "\',"
                + "value=" + "\'" + value + "\',"
                + "file=" + "\'" + file + "\',"
                + "fileBody=" + "\'" + fileBody + "\',"
                + "jsonObject=" + "\'" + jsonObject + "\',"
                + "jsonArray=" + "\'" + jsonArray + "\',"
                + "}";
    }
}
