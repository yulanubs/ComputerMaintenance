package com.computerdmaintenance.util;

import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;

public class StringHelper {

    public static final String NULL_STRING = "";
    public static DecimalFormat priceformat = new DecimalFormat("0.00");
    public static DecimalFormat pricethousandformat = new DecimalFormat("###,##0.00");
    private static char[] base64EncodeChars = new char[]{'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '+', '/'};
    private static byte[] base64DecodeChars = new byte[]{-1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59,
            60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
            10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1,
            -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37,
            38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1,
            -1, -1};

    public static String encode(byte[] data) {
        StringBuffer sb = new StringBuffer();
        int len = data.length;
        int i = 0;
        int b1, b2, b3;

        while (i < len) {
            b1 = data[i++] & 0xff;
            if (i == len) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
                sb.append("==");
                break;
            }
            b2 = data[i++] & 0xff;
            if (i == len) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[((b1 & 0x03) << 4)
                        | ((b2 & 0xf0) >>> 4)]);
                sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
                sb.append("=");
                break;
            }
            b3 = data[i++] & 0xff;
            sb.append(base64EncodeChars[b1 >>> 2]);
            sb.append(base64EncodeChars[((b1 & 0x03) << 4)
                    | ((b2 & 0xf0) >>> 4)]);
            sb.append(base64EncodeChars[((b2 & 0x0f) << 2)
                    | ((b3 & 0xc0) >>> 6)]);
            sb.append(base64EncodeChars[b3 & 0x3f]);
        }
        return sb.toString();
    }

    public static byte[] decode(String str) {
        ByteArrayOutputStream buf = null;
        try {
            byte[] data = str.getBytes();
            int len = data.length;
            buf = new ByteArrayOutputStream(len);
            int i = 0;
            int b1, b2, b3, b4;
            while (i < len) {

				/* b1 */
                do {
                    b1 = base64DecodeChars[data[i++]];
                } while (i < len && b1 == -1);
                if (b1 == -1) {
                    break;
                }

				/* b2 */
                do {
                    b2 = base64DecodeChars[data[i++]];
                } while (i < len && b2 == -1);
                if (b2 == -1) {
                    break;
                }
                buf.write((b1 << 2) | ((b2 & 0x30) >>> 4));

				/* b3 */
                do {
                    b3 = data[i++];
                    if (b3 == 61) {
                        return buf.toByteArray();
                    }
                    b3 = base64DecodeChars[b3];
                } while (i < len && b3 == -1);
                if (b3 == -1) {
                    break;
                }
                buf.write(((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2));

				/* b4 */
                do {
                    b4 = data[i++];
                    if (b4 == 61) {
                        return buf.toByteArray();
                    }
                    b4 = base64DecodeChars[b4];
                } while (i < len && b4 == -1);
                if (b4 == -1) {
                    break;
                }
                buf.write(((b3 & 0x03) << 6) | b4);
            }
        } catch (OutOfMemoryError e) {
            return null;
        }

        return buf.toByteArray();

    }

    /**
     * ������ת�ַ�
     */
    public static String byte2hex(byte[] b) throws Exception {
        String result = null;
        StringBuffer hs = new StringBuffer();
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs.append("0").append(stmp);
            else
                hs.append(stmp);
        }
        result = hs.toString().toUpperCase();
        return result;
    }

    /**
     * �Ƚ���Base64���룬�ٹ��˵��س����У�Ȼ���ٽ���URLEncode��UTF-8���룩
     *
     * @param src md5�������
     * @param enc �ַ����
     * @return
     * @throws Exception
     */
    public static String doBase64URLEncode(String src, String enc) throws Exception {
        String result = null;

        if (null == src || "".equals(src.trim())) {
            return "";
        }
        byte[] raw = src.getBytes();
        String base64data = StringHelper.encode(raw);
        base64data = doURLEncode(base64data, enc);
        result = base64data;

        return result;
    }

    /**
     * ���ַ����URLEncode���루UTF-8���룩
     *
     * @param src ����������
     * @param enc �ַ����
     * @return
     * @throws Exception
     */
    public static String doURLEncode(String src, String enc) throws Exception {
        String result = "";

        result = URLEncoder.encode(src, enc);
        return result;
    }
}
