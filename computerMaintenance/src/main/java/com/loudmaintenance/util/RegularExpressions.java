package com.loudmaintenance.util;

/**
 * @ClassName: RegularExpressions<BR>
 * @Describe：正则表达式<BR>
 * @Author: zhuxunkang
 * @Extends：<BR>
 * @Version:1.0
 * @date:2016-3-28 下午1:54:44
 */
public class RegularExpressions {
    /**
     * 注册验证码正则表达式(只能输入6~10位的数字)
     */
    public static final String a_smscode = "^/d{6,10}$";
    /**
     * 注册密码正则表达式(6到20个字符，支持英文字母、数字、特殊字符)
     */
    public static final String a_password = ".*[\\u4e00-\\u9fa5].*";

}
