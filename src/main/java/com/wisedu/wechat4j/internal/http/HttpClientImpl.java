package com.wisedu.wechat4j.internal.http;

import com.wisedu.wechat4j.conf.ConfigurationContext;
import com.wisedu.wechat4j.conf.HttpClientConfiguration;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClientImpl extends HttpClientBase implements HttpResponseCode, Serializable {
    private static final long serialVersionUID = 9146333614776061763L;

    public HttpClientImpl(){
        super(ConfigurationContext.getInstance());
    }

    public HttpClientImpl(HttpClientConfiguration conf) {
        super(conf);
    }

    /**
     * @param request
     * @throws IOException
     * <a href="https://www.imququ.com/post/four-ways-to-post-data-in-http.html"></a>
     */
    @Override HttpResponse handleRequest(HttpRequest request) throws IOException {
        HttpResponse response = null;
        int retryCount = conf.getHttpRetryCount();
        for (int retriedConnt=0; retriedConnt<retryCount+1; retriedConnt++){
            int statusCode = -1;
            try {
                OutputStream os = null;
                HttpURLConnection con;
                try {
                    con = getConnection(request.getURL());
                    con.setDoInput(true);
                    setHeaders(request, con);
                    con.setRequestMethod(request.getMethod().name());
                    if (request.getMethod() == RequestMethod.POST){
                        if (HttpParameter.containsFile(request.getParameters())){
                            // file upload
                        } else if (HttpParameter.containsJSON(request.getParameters())){
                            // JSON
                            if (request.getParameters().length > 2){
                                throw new IOException("Invalid Http Parameters.");
                            }

                            con.setRequestProperty("Content-Type", "application/json");
                            con.setDoOutput(true);
                            os = con.getOutputStream();
                            for (HttpParameter param: request.getParameters()){
                                if (param.isJSON()){
                                    byte[] bytes = null;
                                    if (param.isJSONObject()){
                                        bytes = param.getJSONObject().toString().getBytes("utf-8");
                                    } else if (param.isJSONArray()){
                                        bytes = param.getJsonArray().toString().getBytes("utf-8");
                                    }
                                    os.write(bytes);
                                } else if (param.isValue()){
                                }
                            }
                        } else {
                            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                            String postParams = HttpParameter.encodeParameters(request.getParameters());
                            byte[] bytes = postParams.getBytes("utf-8");
                            con.setRequestProperty("Content-Length", Integer.toString(bytes.length));
                            con.setDoOutput(true);
                            os = con.getOutputStream();
                            os.write(bytes);
                        }
                        os.flush();
                        os.close();
                    }

                    response = new HttpResponseImpl(con, conf);
                    statusCode = response.getStatusCode();
                    if (statusCode<OK || (statusCode!=FOUND && statusCode>=MULTIPLE_CHOICES)){
                        if (statusCode==ENHANCE_YOUR_CLAIM
                                || statusCode==BAD_REQUEST
                                || statusCode<INTERNAL_SERVER_ERROR
                                || retriedConnt==conf.getHttpRetryCount()){
                            // throw new WechatException(message, response);
                            throw new IOException(response.getStatusCode() + ": " + response.asString());
                        }
                        // will retry if the status code is INTERNAL_SERVER_ERROR
                    } else {
                        break;
                    }
                } finally {
                    if (os != null){
                        try {
                            os.close();
                        } catch (IOException ioe){
                            // do noting
                        }
                    }
                }
            } catch (IOException ioe){
                if (retriedConnt == retryCount){
                    throw ioe;
                }
            }

            try {
                Thread.sleep(conf.getHttpRetryIntervalSeconds());
            } catch (InterruptedException ite){
                // do noting
            }
        }
        return response;
    }

    private void setHeaders(HttpRequest request, HttpURLConnection con){
        if (request.getRequestHeaders() != null){
            for (String key : request.getRequestHeaders().keySet()){
                con.addRequestProperty(key, request.getRequestHeaders().get(key));
            }
        }
    }

    private HttpURLConnection getConnection(String url) throws IOException{
        HttpURLConnection con = (HttpURLConnection)new URL(url).openConnection();

        if (conf.getHttpConnectionTimeout() > 0){
            con.setConnectTimeout(conf.getHttpConnectionTimeout());
        }
        if (conf.getHttpReadTimeout() > 0){
            con.setReadTimeout(conf.getHttpReadTimeout());
        }
        con.setInstanceFollowRedirects(false);
        return con;
    }
}