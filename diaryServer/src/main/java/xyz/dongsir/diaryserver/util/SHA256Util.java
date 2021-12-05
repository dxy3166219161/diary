package xyz.dongsir.diaryserver.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Description:
 *
 * @author dongxingyu
 * @version 2.0.0
 *
 * <p>
 * History:
 * Date                Author         Version     Description
 * --------------------------------------------------------------------
 * 2021/11/26 16:02     dongxingyu        2.0.0       To create
 * </p>
 */
public class SHA256Util {
    public static void main(String[] args) {
//        System.out.println(getSHA256StrJava("130"));
////        System.out.println(getSHA256StrJava("130"));

    }

    public static String getSHA256Str(String str) {

        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
//1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}