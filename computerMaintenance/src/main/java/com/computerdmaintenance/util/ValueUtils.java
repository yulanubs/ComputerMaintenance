/**
 * 
 */
package com.computerdmaintenance.util;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 类说明： 数值判断帮助类
 * 
 * @author tianshuguang@tomstaff.com
 * @date 2014-7-25
 * @version 1.0
 */
public class ValueUtils {

	/**
	 * @description 判断List是否非空
	 * @param noteList
	 * @return boolean
	 */
	public static boolean isListNotEmpty(List<?> noteList) {
		return null != noteList && noteList.size() > 0;
	}

	/**
	 * @description 判断List是否为空
	 * @param noteList
	 * @return boolean
	 */
	public static boolean isListEmpty(List<?> noteList) {
		return null == noteList || noteList.size() == 0;
	}

	/**
	 * @description 判断String字符串是否为空
	 * @param value
	 * @return boolean
	 */
	public static boolean isStrEmpty(String value) {
		if (null == value || "".equals(value.trim())||value.equals("null")) {
			return true;
		}
		return false;
	}

	/**
	 * @description 判断String字符串是否非空
	 * @param value
	 * @return boolean
	 */
	public static boolean isStrNotEmpty(String value) {
		return !isStrEmpty(value);
	}

	/**
	 * @description 判断对象是否非空
	 * @param object
	 * @return boolean
	 */
	public static boolean isNotEmpty(Object object) {
		return null != object;
	}

	/**
	 * @description 判断对象是否非空
	 * @param object
	 * @return boolean
	 */
	public static boolean isEmpty(Object object) {
		return null == object;
	}

	/**
	 * 判断在多个EditText或者TextView的内容中有一个为空就返回true
	 * 
	 * @param views
	 * @return
	 */
	public static boolean isHasEmptyView(View... views) {
		for (View v : views) {
			if (!v.isShown()) {// 不可见的不做判断
				continue;
			}
			if (v instanceof EditText) {
				EditText et = (EditText) v;
				if (TextUtils.isEmpty(et.getText().toString().trim())) {
					return true;
				}
			} else if (v instanceof TextView) {
				TextView tv = (TextView) v;
				if (TextUtils.isEmpty(tv.getText().toString().trim())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 将boolean true变成"1" false变成"0"
	 * 
	 * @param b
	 * @return
	 */
	public static String bolean2String(boolean b) {
		return b ? "1" : "0";
	}

	public static boolean isChinese(String str) throws PatternSyntaxException {
		/** 此正则表达式来进行判断输入是否为中文 **/
		String regEx = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";
		Pattern p = Pattern.compile(regEx);
		Matcher matcher = p.matcher(str);
		return matcher.matches();
	}

	/**
	 * 精确到百分位，小数点后不足补零。 例如：20>20.00, 21.1111>21.11, 21.55555>21.56
	 */
	public static String format2Percentile(String number) {
		//		包含逗号
//		String strFormat = "%,.2f";
		String strFormat = "%.2f";
		Double doubleMoney = 0.00;

		if (number == null || number.length() < 1) {
			doubleMoney = 0.00;
		} else {
			try {
				doubleMoney = Double.valueOf(number);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				doubleMoney = 0.00;
				return String.format(strFormat, doubleMoney);
			}
		}
		return String.format(strFormat, doubleMoney);
	}

	/**
	 * 精确到百分位，小数点后不足补零。 例如：20>20.00, 21.1111>21.11, 21.55555>21.56
	 */
	public static String format2Percentile(double number) {
		return format2Percentile(Double.toString(number));
	}

	/**
	 * upc格式 加空格
	 */
	public static String formatUpc(String upc) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(upc);
		int index = 0;
		while (index < buffer.length()) {
			if ((index == 1 || index == 8)) {
				buffer.insert(index, ' ');
			}
			index++;
		}
		char[] tempChar = new char[buffer.length()];
		buffer.getChars(0, buffer.length(), tempChar, 0);
		String str = buffer.toString();
		return str;
	}

	// 通过正则表达式来判断。下面的例子只允许显示字母、数字和汉字。
	public static String stringFilter(String str) throws PatternSyntaxException {
		// 只允许字母、数字和汉字
		String regEx = "[^a-zA-Z\u4E00-\u9FA5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
	// 判断号码是否为手机格式
	public static boolean checkTelephoneNum(String input) {
		Pattern pattern = Pattern.compile("^1\\d{10}$");
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}
}
