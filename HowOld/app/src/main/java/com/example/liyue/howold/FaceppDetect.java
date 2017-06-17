package com.example.liyue.howold;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import javax.net.ssl.SSLException;


import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by liyue on 2017/4/24.
 */

public class FaceppDetect
{
    public interface CallBack
    {
        void success(JSONObject jsonObject);

        void error(Exception exception);
    }
    //input: image and callback
    //function: convert image to byte array, post parameters and get the requested json object


    public static void detect(final Bitmap bm, final CallBack callBack)
    {

        new Thread(new Runnable() {
            @Override
            public void run() {
                //compress bitmap to byte stream

                Bitmap bmBin =  Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight());
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmBin.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                //convert bitmap to binary array
                byte byteArray[] = stream.toByteArray();

                String url = "https://api-cn.faceplusplus.com/facepp/v3/detect";
                HashMap<String, String> map = new HashMap<>();
                HashMap<String, byte[]> byteMap = new HashMap<>();
                map.put("api_key", Constant.Key);
                map.put("api_secret", Constant.Secret);
                map.put("return_attributes", Constant.Attributes);
                byteMap.put("image_file", byteArray);
                try
                {
                    byte[] bacd = post(url, map, byteMap);
                    String str = new String(bacd);
                    JSONObject jsonObj = new JSONObject(str);
                    //System.out.println(str);
                    //Log.e("TAG", str);
                    if (callBack != null)
                    {
                        callBack.success(jsonObj);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    if (callBack != null)
                    {
                        callBack.error(e);
                    }
                }

            }
        }).start();
    }
    private final static int CONNECT_TIME_OUT = 30000;
    private final static int READ_OUT_TIME = 50000;
    private static String boundaryString = getBoundary();

    protected static byte[] post(String url, HashMap<String, String> map, HashMap<String, byte[]> fileMap) throws Exception
    {
        HttpURLConnection conne;
        URL url1 = new URL(url);
        conne = (HttpURLConnection) url1.openConnection();
        conne.setDoOutput(true);// set httpUrlConnection true because this is a post request ，
                                //thus the argument should be within the http context
        conne.setUseCaches(false);
        conne.setRequestMethod("POST");
        conne.setConnectTimeout(CONNECT_TIME_OUT);
        conne.setReadTimeout(READ_OUT_TIME);
        conne.setRequestProperty("accept", "*/*");
        conne.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
        conne.setRequestProperty("connection", "Keep-Alive");
        conne.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
        DataOutputStream obos = new DataOutputStream(conne.getOutputStream());
        Iterator iter = map.entrySet().iterator();
        while(iter.hasNext())
        {
            Map.Entry<String, String> entry = (Map.Entry) iter.next();
            String key = entry.getKey();
            String value = entry.getValue();
            obos.writeBytes("--" + boundaryString + "\r\n");
            obos.writeBytes("Content-Disposition: form-data; name=\"" + key
                    + "\"\r\n");
            obos.writeBytes("\r\n");
            obos.writeBytes(value + "\r\n");
        }
        if(fileMap != null && fileMap.size() > 0){
            Iterator fileIter = fileMap.entrySet().iterator();
            while(fileIter.hasNext())
            {
                Map.Entry<String, byte[]> fileEntry = (Map.Entry<String, byte[]>) fileIter.next();
                obos.writeBytes("--" + boundaryString + "\r\n");
                obos.writeBytes("Content-Disposition: form-data; name=\"" + fileEntry.getKey()
                        + "\"; filename=\"" + encode(" ") + "\"\r\n");
                obos.writeBytes("\r\n");
                obos.write(fileEntry.getValue());
                obos.writeBytes("\r\n");
            }
        }
        obos.writeBytes("--" + boundaryString + "--" + "\r\n");
        obos.writeBytes("\r\n");
        obos.flush(); //clear the stream
        obos.close();
        InputStream ins = null;
        int code = conne.getResponseCode();
        try{
            if(code == 200) //succeeded
            {
                ins = conne.getInputStream();
            }
            else
            {
                ins = conne.getErrorStream();
            }
        }catch (SSLException e){
            e.printStackTrace();
            return new byte[0];
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buff = new byte[4096];
        int len;
        while((len = ins.read(buff)) != -1){
            baos.write(buff, 0, len);
        }
        byte[] bytes = baos.toByteArray();
        ins.close();
        return bytes;
    }

    private static String getBoundary()
    {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 32; ++i) {
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-"
                    .charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_"
                            .length())));
        }
        return sb.toString();
    }
    private static String encode(String value) throws Exception
    {
        return URLEncoder.encode(value, "UTF-8");
    }
}

