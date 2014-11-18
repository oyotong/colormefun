package shop.common.util;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

/**
 * Created by tong-note on 2014/8/10.
 */
public class SMSUtils {
    private static final Logger logger = Logger.getLogger(SMSUtils.class);
    public static int sendMessage(String mob, String txt){


//        String api = ParamUtils.param("SMS_API","API");
//        String uid = ParamUtils.param("SMS_API","UID");
//        String key = ParamUtils.param("SMS_API","KEY");

        String api = "http://utf8.sms.webchinese.cn";//ParamUtils.param("SMS_API","API");
        String uid = "colormefun";//ParamUtils.param("SMS_API","UID");
        String key = "68a2eb316e847a8c669e";//ParamUtils.param("SMS_API","KEY");

        if(api == null) api = "http://utf8.sms.webchinese.cn";
        if(uid == null) uid = "colormefun";
        if(key == null) key = "68a2eb316e847a8c669e";

        PostMethod post = null;

       try{

           //txt = URLEncoder.encode(txt,"utf8");

           HttpClient client = new HttpClient();
           post = new PostMethod(api);
           post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");//在头文件中设置转码
           NameValuePair[] data ={ new NameValuePair("Uid", uid),new NameValuePair("Key", key),new NameValuePair("smsMob",mob),new NameValuePair("smsText",txt)};
           post.setRequestBody(data);

           client.executeMethod(post);
           Header[] headers = post.getResponseHeaders();
           int statusCode = post.getStatusCode();
           logger.warn("statusCode:"+statusCode);
           for(Header h : headers)
           {
               logger.warn(h.toString());
           }

           int result = 0;
           try{
               String resultTxt = new String(post.getResponseBodyAsString().getBytes("utf8"));
               result = new Integer(resultTxt);
           }catch (Exception e){

           }
           return result;

       }catch (Exception e){
           logger.error(e.getMessage(),e);
           return -200;
       }finally {
           if(null != post){
               post.releaseConnection();
           }
       }
    }
    public static void main(String[] args){//,18227658119
        int result = sendMessage("13228181890","短信测试。");
        System.out.print(result);
    }
}
