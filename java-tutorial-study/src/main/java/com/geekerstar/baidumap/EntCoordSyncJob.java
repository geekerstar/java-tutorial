package com.geekerstar.baidumap;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

/**
 * @author geekerstar
 * date: 2019/11/12 23:51
 * description:
 */
public class EntCoordSyncJob {
    static String AK = "kDVs6aY9KR5PUFOXLRIbMnrVU6KP8hpH"; // 百度地图密钥

    public static void main(String[] args) {
        String dom = "成都金牛区";
        String coordinate = getCoordinate(dom);
        System.out.println("'" + dom + "'的经纬度为：" + coordinate);
        // System.err.println("######同步坐标已达到日配额6000限制，请明天再试！#####");
    }

    //http://api.map.baidu.com/geocoding/v3/?address=北京市海淀区上地十街10号&output=json&ak=您的ak&callback=showLocation
    // 调用百度地图API根据地址，获取坐标
    public static String getCoordinate(String address) {
        if (address != null && !"".equals(address)) {
            address = address.replaceAll("\\s*", "").replace("#", "栋");
            String url = "http://api.map.baidu.com/geocoding/v3/?address=" + address + "&output=json&ak=" + AK;
            String json = loadJSON(url);
            if (json != null && !"".equals(json)) {
                JSONObject obj = JSONObject.fromObject(json);
                if ("0".equals(obj.getString("status"))) {
                    double lng = obj.getJSONObject("result").getJSONObject("location").getDouble("lng"); // 经度
                    double lat = obj.getJSONObject("result").getJSONObject("location").getDouble("lat"); // 纬度
                    DecimalFormat df = new DecimalFormat("#.######");
                    return df.format(lng) + "," + df.format(lat);
                }
            }
        }
        return null;
    }

    public static String loadJSON(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {} catch (IOException e) {}
        return json.toString();
    }

    // 来自stackoverflow的MD5计算方法，调用了MessageDigest库函数，并把byte数组结果转换成16进制
    /*
     * public String MD5(String md5) { try { java.security.MessageDigest md = java.security.MessageDigest .getInstance("MD5"); byte[] array = md.digest(md5.getBytes()); StringBuffer sb = new StringBuffer(); for (int i = 0; i < array.length; ++i) { sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100) .substring(1, 3)); } return sb.toString(); } catch (java.security.NoSuchAlgorithmException e) {
     * } return null; }
     */
}
