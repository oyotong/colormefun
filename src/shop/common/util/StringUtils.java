package shop.common.util;


import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    // 国标码和区位码转换常量
    static final int GB_SP_DIFF = 160;

    // 存放国标一级汉字不同读音的起始区位码
    static final int[] secPosValueList = {1601, 1637, 1833, 2078, 2274, 2302,
            2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858, 4027,
            4086, 4390, 4558, 4684, 4925, 5249, 5600};

    // 存放国标一级汉字不同读音的起始区位码对应读音
    static final char[] firstLetter = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'w', 'x',
            'y', 'z'};

    // 存放密码元素
    static final char[] passwordChar = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'w', 'x',
            'y', 'z', 'A', 'B', 'B', 'D', 'E', 'F', 'G', 'H',
            'J', 'K', 'L', 'N', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'X',
            'Y', 'Z', '#', '@', '!', '%', '*', '(', ')', '_', '-', '+', '=', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    // 获取一个字符串的拼音码
    public static String getFirstLetter(String oriStr) {
        String str = oriStr.toLowerCase();
        StringBuffer buffer = new StringBuffer();
        char ch;
        char[] temp;
        for (int i = 0; i < str.length(); i++) { // 依次处理str中每个字符
            ch = str.charAt(i);
            temp = new char[]{ch};
            byte[] uniCode = new String(temp).getBytes();
            if (uniCode[0] < 128 && uniCode[0] > 0) { // 非汉字
                buffer.append(temp);
            } else {
                buffer.append(convert(uniCode));
            }
        }
        return buffer.toString();
    }

    /**
     * 获取一个汉字的拼音首字母。 GB码两个字节分别减去160，转换成10进制码组合就可以得到区位码
     * 例如汉字“你”的GB码是0xC4/0xE3，分别减去0xA0（160）就是0x24/0x43
     * 0x24转成10进制就是36，0x43是67，那么它的区位码就是3667，在对照表中读音为‘n’
     */

    static char convert(byte[] bytes) {

        char result = '-';
        int secPosValue = 0;
        int i;
        for (i = 0; i < bytes.length; i++) {
            bytes[i] -= GB_SP_DIFF;
        }
        secPosValue = bytes[0] * 100 + bytes[1];
        for (i = 0; i < 23; i++) {
            if (secPosValue >= secPosValueList[i]
                    && secPosValue < secPosValueList[i + 1]) {
                result = firstLetter[i];
                break;
            }
        }
        return result;
    }

    /**
     * 汉字转拼音缩写
     *
     * @param str //要转换的汉字字符串
     * @return String //拼音缩写
     */
    public static String getPYString(String str) {
        String tempStr = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ((int) c >= 33 && (int) c <= 126) {// 字母和符号原样保留
                tempStr += String.valueOf(c);
            } else {// 累加拼音声母
                tempStr += getPYChar(String.valueOf(c));
            }
        }
        return tempStr;
    }

    /**
     * 取单个字符的拼音声母
     *
     * @param c //要转换的单个汉字
     * @return String 拼音声母
     */
    public static String getPYChar(String c) {
        byte[] array = new byte[2];
        array = String.valueOf(c).getBytes();
        int i = (short) (array[0] - '\0' + 256) * 256
                + ((short) (array[1] - '\0' + 256));
        if (i < 0xB0A1)
            return "*";
        if (i < 0xB0C5)
            return "a";
        if (i < 0xB2C1)
            return "b";
        if (i < 0xB4EE)
            return "c";
        if (i < 0xB6EA)
            return "d";
        if (i < 0xB7A2)
            return "e";
        if (i < 0xB8C1)
            return "f";
        if (i < 0xB9FE)
            return "g";
        if (i < 0xBBF7)
            return "h";
        if (i < 0xBFA6)
            return "j";
        if (i < 0xC0AC)
            return "k";
        if (i < 0xC2E8)
            return "l";
        if (i < 0xC4C3)
            return "m";
        if (i < 0xC5B6)
            return "n";
        if (i < 0xC5BE)
            return "o";
        if (i < 0xC6DA)
            return "p";
        if (i < 0xC8BB)
            return "q";
        if (i < 0xC8F6)
            return "r";
        if (i < 0xCBFA)
            return "s";
        if (i < 0xCDDA)
            return "t";
        if (i < 0xCEF4)
            return "w";
        if (i < 0xD1B9)
            return "x";
        if (i < 0xD4D1)
            return "y";
        if (i < 0xD7FA)
            return "z";
        return "*";
    }

    public static String substring(String str, Integer length) {// length:英文长度
        if (null == str)
            return " ";
        if (str.getBytes().length <= length) {
            return str;
        }
        StringBuffer substr = new StringBuffer(length + 3);
        substr.append(str.substring(0, length - 2 > str.length() ? str.length()
                : length - 2));
        while (substr.toString().getBytes().length > length - 2) {
            substr.deleteCharAt(substr.length() - 1);
        }
        return substr.toString() + "..";
    }

    public static boolean isNotNull(String str) {
        return null == str ? false : str.trim().length() == 0 ? false : true;
    }

    public static int getInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return 0;
        }
    }

    public static boolean isboolIp(String ipAddress) {
        String ip = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pattern = Pattern.compile(ip);
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }

    public static int compare(String ip1, String ip2) {

        try {
            if (null == ip1 && null == ip2) {
                return 0;
            }
            if (null == ip1) {
                return -1;
            }
            if (null == ip2) {
                return 1;
            }
            String[] bip1 = ip1.split("\\.");
            String[] bip2 = ip2.split("\\.");
            if (bip1.length > bip2.length) {
                return 1;
            } else if (bip1.length < bip2.length) {
                return -1;
            }

            for (int i = 0; i < bip1.length; i++) {

                int a = Integer.parseInt(bip1[i]);
                int b = Integer.parseInt(bip2[i]);

                if (a > b) {
                    return 1;
                } else if (a < b) {
                    return -1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static boolean isNull(String str) {
        return null == str || str.trim().length() == 0;
    }

    public static Integer int2obj(int i) {
        return i;
    }

    public static String urlEncode(String url) {
        try {
            if (null == url) return "";
            return URLEncoder.encode(url, "UTF8").replaceAll("%", "+");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return url;
        }
    }

    public static String urlDecode(String url) {
        try {
            if (null == url) return "";
            return URLDecoder.decode(url.replaceAll(" ", "+").replaceAll("\\+", "%"), "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return url;
        }
    }

    public static String generateFileName(String fileName) {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String formatDate = format.format(new Date());

        int random = new Random().nextInt(10000);

        int position = fileName.lastIndexOf(".");
        String extension = fileName.substring(position);

        return formatDate + random + extension;
    }

    public static String fmtMyMoney(Object d, String fmt) {
        if (null == d) {
            d = 0.00;
        }
        if (d instanceof BigDecimal) {
            d = ((BigDecimal) d).doubleValue();
        }

        //system.format.money
        DecimalFormat decimalFormat = new DecimalFormat(fmt);
        return decimalFormat.format(d);
    }

    public static String fmtMoney(Object d) {
        ActionSupport actionSupport = new ActionSupport();
        String format = "￥#,##0.00";//actionSupport.getText("system.format.money", "#,##0.00");
        return fmtMyMoney(d,format);
    }

    public static String random() {
        return ((int) (Math.random() * 1000000)) + "";
    }

    public static java.lang.String concat(java.lang.Object s1, java.lang.Object s2) {
        return "" + s1 + s2;
    }

    public static String makeId1() {
        return Long.toHexString(System.currentTimeMillis() + System.nanoTime() + (int) (Math.random() * 10000));
    }

    public static String getPassword(int length) {
        String pwd = "";
        int arrLength = passwordChar.length;
        for (int i = 0; i < length; i++) {
            int idx = ((int) (Math.random() * arrLength));
            pwd += passwordChar[idx];
        }
        return pwd;
    }

    /**
     * MD5 加密
     *
     * @param plainText 明文
     * @return 32位密文
     */
    public static String toMD5(String plainText){
        return toMD5(plainText, 32);
    }
    public static String toMD5(String plainText, Integer bit) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            re_md5 = bytesToHex(b);
            if(bit == 16){
                return re_md5.substring(8,24);
            }
        } catch (NoSuchAlgorithmException e) {
        }
        return re_md5;
    }

    private final static char[] hexDigits = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        int t;
        for (int i = 0; i < 16; i++) {
            t = bytes[i];
            if (t < 0)
                t += 256;
            sb.append(hexDigits[(t >>> 4)]);
            sb.append(hexDigits[(t % 16)]);
        }
        return sb.toString();
    }

    public static String enBase64(byte[] bytes) {
        Base64 base64 = new Base64();
        return new String(base64.encode(bytes));
    }

    public static byte[] deBase64(String base64String) {
        Base64 base64 = new Base64();
        return base64.decode(base64String.getBytes());
    }

    public static String fmtCouponNo(String couponNo){
        if(null == couponNo || couponNo.length() != 16){
            return "";
        }
        return couponNo.substring(0,4)+" "+couponNo.substring(4,8)+" "+couponNo.substring(8,12)+" "+couponNo.substring(12,16);
    }
    public static void main(String[] args) {
        System.out.println(fmtCouponNo(toMD5("1234567890",16)));
    }
}
