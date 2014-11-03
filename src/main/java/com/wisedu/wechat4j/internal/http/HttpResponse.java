package com.wisedu.wechat4j.internal.http;

import com.wisedu.wechat4j.WechatException;
import com.wisedu.wechat4j.conf.ConfigurationContext;
import com.wisedu.wechat4j.conf.HttpClientConfiguration;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.List;
import java.util.Map;

public abstract class HttpResponse {
    protected final HttpClientConfiguration conf;
    protected int statusCode;
    protected String responseAsString;
    protected InputStream is;
    private boolean streamConsumed = false;

    private JSONObject jsonObject;
    private JSONArray jsonArray;

    public HttpResponse(){
        this.conf = ConfigurationContext.getInstance();
    }

    public HttpResponse(HttpClientConfiguration conf){
        this.conf = conf;
    }

    public int getStatusCode(){
        return statusCode;
    }

    public abstract String getResponseHeader(String name);

    public abstract Map<String, List<String>> getResponseHeaderFields();

    /**
     * Returns the response stream.<br>
     * This method cannot be called after calling asString() or asDcoument()<br>
     * It is suggested to call disconnect() after consuming the stream.
     * <p/>
     * Disconnects the internal HttpURLConnection silently.
     *
     * @return response body stream
     * @see #disconnect()
     */
    public InputStream asStream(){
        if (streamConsumed){
            throw new IllegalStateException("Stream has already been consumed.");
        }
        return is;
    }

    /**
     * Returns the response body as string.<br>
     * Disconnects the internal HttpURLConnection silently.
     *
     * @return response body
     * @throws WechatException
     */
    public String asString() throws IOException{
        if (responseAsString == null){
            BufferedReader reader = null;
            InputStream stream = null;
            try {
                stream = asStream();
                if (stream == null){
                    return null;
                }

                String line = null;
                StringBuffer sb = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(stream, "utf-8"));
                while ((line=reader.readLine()) != null){
                    sb.append(line + "\n");
                }
                responseAsString = sb.toString();
                streamConsumed = true;
            } catch (IOException ioe){
                throw new IOException("asString failed", ioe);
            } finally {
                if (stream != null){
                    try {
                        stream.close();
                    } catch (IOException ioe){
                    }
                }
                if (reader != null){
                    try {
                        reader.close();
                    } catch (IOException ioe){
                    }
                }
                disconnect();
            }
        }
        return responseAsString;
    }

    public Reader asReader(){
        try {
            return new BufferedReader(new InputStreamReader(is, "utf-8"));
        } catch (IOException ioe){
        }
        return null;
    }

    public JSONObject asJSONObject() throws IOException{
        if (jsonObject == null){
            Reader reader = null;
            try {
                if (responseAsString == null){
                    reader = asReader();
                    jsonObject = new JSONObject(new JSONTokener(reader));
                } else {
                    jsonObject = new JSONObject(responseAsString);
                }
            } catch (JSONException jsne){
                if (responseAsString == null){
                    throw new IOException("asJSON failed", jsne);
                } else {
                    throw new IOException("asJSON failed: " + responseAsString, jsne);
                }
            } finally {
                if (reader != null){
                    try {
                        reader.close();
                    } catch (IOException ioe){
                    }
                }
                disconnect();
            }
        }
        return jsonObject;
    }

    public JSONArray asJSONArray() throws IOException{
        if (jsonArray == null){
            Reader reader = null;
            try {
                if (responseAsString == null) {
                    reader = asReader();
                    jsonArray = new JSONArray(new JSONTokener(reader));
                } else {
                    jsonArray = new JSONArray(responseAsString);
                }
            } catch (JSONException jsne) {
                if (responseAsString == null){
                    throw new IOException("asJSONArray failed", jsne);
                } else {
                    throw new IOException("asJSONArray failed: " + responseAsString, jsne);
                }
            } finally {
                if (reader != null){
                    try {
                        reader.close();
                    } catch (IOException ioe){
                    }
                }
                disconnect();
            }
        }
        return jsonArray;
    }

    public abstract void disconnect();

    @Override public String toString(){
        return "HttpResponse"
                + "{"
                + "statusCode=" + statusCode + ","
                + "responseAsString=" + "\'" + responseAsString + "\',"
                + "is=" + is + ","
                + "streamConsumed=" + streamConsumed + ","
                + "}";
    }
}
