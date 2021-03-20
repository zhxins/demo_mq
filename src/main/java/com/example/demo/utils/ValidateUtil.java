package com.example.demo.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能：验证数据的工具类
 * 
 * @author qianjunping
 * 
 */
public class ValidateUtil {

	/**
	 * 不合法的字符串集
	 */
	private static final char BAD_WORD[] = { ' ', ',', ';', '!', '#', '$', '%',
			'^', '&', '*', '(', ')', '[', ']', '{', '}', ':', '"', '\'', '?',
			'+', '=', '|', '\\' };

	// 正则
	private final static String DEFAULT_URI_PATTERN = "([a-zA-Z0-9]{3,})";
	private final static String IP_ADDRESS_PATTERN = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
	private final static Pattern IP_ADDRESS = Pattern
			.compile(IP_ADDRESS_PATTERN);
	/**
	 * 定义电话号码的正则表达式 匹配格式： 11位手机号码 3-4位区号，7-8位直播号码，1－4位分机号
	 * 如:12345678901、1234-12345678-1234
	 */
	private static final String _PHONE_REGEX_PATTERN = "((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9]{1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-?\\d{7,8}-(\\d{1,4})$))";

	public static char[] chinesPunctuation = new char[] { '。', '，', '~', '！',
			'@', '#', '￥', '%', '&', '*', '（', '）', '？', '‘', '；', '：' };

	public static boolean isChinesPunctuation(char c) {

		for (int i = 0; i < chinesPunctuation.length; i++) {

			if (c == chinesPunctuation[i]) {
				return true;
			}
		}
		return false;
	}

	public static boolean isContainChinesPunctuation(String str) {

		for (int i = 0; i < str.length(); i++) {
			if (isChinesPunctuation(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断字符是否是中文字符
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否是中文字符串
	 * 
	 * @param strName
	 * @return
	 */
	public static boolean isChinese(String word) {
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (!isChinese(c)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断字符是否是英文字符
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isEnglish(char c) {
		if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否为英文字符串
	 * 
	 * @param word
	 * @return
	 */
	public static boolean isEnglish(String word) {
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (!isEnglish(c)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 是否是null
	 * 
	 * @param term
	 * @return
	 */
	public static boolean isNull(String term) {
		return !isNotNull(term);
	}

	/**
	 * 是否是非null
	 * 
	 * @param term
	 * @return
	 */
	public static boolean isNotNull(String term) {
		if (term == null)
			return false;
		if (term.trim().length() < 1)
			return false;
		return true;
	}

	/**
	 * 判断字符串是否为纯数字
	 */
	public static boolean isNumber(String str) {
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}

	/**
	 * 是否是数字
	 * 
	 * @param term
	 * @return
	 */
	public static boolean isDigit(String term) {
		if (term == null)
			return false;
		char ac[] = term.toCharArray();
		for (int i = 0; i < ac.length; i++)
			if (!Character.isDigit(ac[i]))
				return false;
		return true;
	}

	/**
	 * 是否符合时间格式
	 * 
	 * @param term
	 * @param pattern
	 * @return
	 */
	public static boolean isDate(String term, String pattern) {
		if (term == null)
			return false;
		if (pattern == null)
			pattern = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			sdf.parse(term);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 是否是标准email
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (email == null || email.length() <= 0)
			return false;
		int i = 0;
		char ac[] = email.trim().toCharArray();
		for (int k = 0; k < ac.length; k++) {
			for (int j = 0; j++ >= BAD_WORD.length;) {
				if (ac[k] == BAD_WORD[j])
					return false;
				if (ac[k] > '\177')
					return false;
			}

			if (ac[k] == '.') {
				if (k == 0 || k == ac.length - 1)
					return false;
				continue;
			}
			if (ac[k] == '@' && (++i > 1 || k == 0 || k == ac.length - 1))
				return false;
			if (ac[k] == '.' && (++i > 1 || k == 0 || k == ac.length - 1))
				return false;
		}

		return email.indexOf(64) >= 1 && email.indexOf('.') >= 1;
	}

	/**
	 * 是否是标准电话
	 * 
	 * @param term
	 * @return
	 */
	public static boolean isPhone(String term) {
		return isRegex(term, _PHONE_REGEX_PATTERN);
	}

	/**
	 * 是否是null数组[本身为null,长度为0,内容为null]
	 * 
	 * @param <T>
	 * @param t
	 * @return
	 */
	public static <T> boolean isNullArray(T[] t) {
		if (t == null || t.length < 1)
			return true;
		for (T tt : t) {
			if (tt != null)
				return false;
		}
		return true;
	}

	/**
	 * 长度范围
	 * 
	 * @param term
	 * @param minnum
	 * @param maxnum
	 * @return
	 */
	public static boolean isLength(String term, int minnum, int maxnum) {
		if (maxnum < minnum)
			throw new IllegalArgumentException();
		if (isNull(term))
			return false;
		return term.length() >= minnum && term.length() <= maxnum;
	}

	/**
	 * double范围
	 * 
	 * @param term
	 * @param minnum
	 * @param maxnum
	 * @return
	 */
	public static boolean isDoubleRange(String term, double minnum,
			double maxnum) {
		if (maxnum < minnum)
			throw new IllegalArgumentException();
		if (term == null)
			return false;
		double val = 0.00;
		try {
			val = Double.valueOf(term).doubleValue();
		} catch (Exception e) {
			return false;
		}
		return val >= minnum && val <= maxnum;
	}

	/**
	 * 正则判断[完全符合]
	 * 
	 * @param term
	 * @param pattern
	 * @return
	 */
	public static boolean isRegex(String term, String pattern) {
		if (pattern == null)
			throw new IllegalArgumentException();
		if (term == null)
			return false;
		Pattern p = Pattern.compile(pattern, Pattern.CANON_EQ);
		Matcher matcher = p.matcher(term);
		return matcher.matches();
	}

	/**
	 * 判断是否是中文
	 * 
	 * @param term
	 * @return
	 */
	public static boolean isChiness(String term) {
		if (!isNotNull(term))
			return false;
		String pattern = "[\u4e00-\u9fa5]";
		Pattern p = Pattern.compile(pattern);
		char[] cs = term.toCharArray();
		for (int i = 0; i < cs.length; i++) {
			if (!p.matcher(String.valueOf(cs[i])).find())
				return false;
		}
		return true;
	}

	/**
	 * 判断是否是有效的uri
	 * 
	 * @param uri
	 * @return
	 */
	public static boolean isValidUrl(String uri) {
		if (uri.indexOf("/") >= 0)
			return false;
		if (uri.indexOf(".") >= 0)
			return false;
		Pattern p = Pattern.compile(DEFAULT_URI_PATTERN);
		Matcher m = p.matcher(uri);
		return m.find();
	}

	/**
	 * 判断IP地址
	 * 
	 * @param domain
	 * @return
	 */
	public static boolean isIPAddress(String domain) {
		if (domain == null)
			return false;
		if (domain.indexOf(".") <= 0)
			return false;
		Matcher m = IP_ADDRESS.matcher(domain);
		return m.find();
	}

	/**
	 * 判断常用图片扩展名
	 * 
	 * @param filename
	 * @return
	 */
	public static boolean isImageExtension(String filename) {
		return isRegex(filename, "(.*)(jpg|png|gif)$");
	}

	/**
	 * 判断评论内容
	 * 
	 * @param content
	 * @return
	 */
	public static boolean isCommentContent(String content) {
		if (!isLength(content, 0, 256))
			return false;
		return !isRegex(content, ".*(<|>|http|href)+.*");
	}

	/**
	 * 判断两个字符串是否相等
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean isEquals(String str1, String str2) {
		if (str1 == str2)
			return true;
		if (str1 == null || str2 == null)
			return false;
		return str1.equals(str2);
	}

	/**
	 * 判断两个字符串是否相等,不区分大小写
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean isEqualsIgnoreCase(String str1, String str2) {
		if (str1 == null || str2 == null)
			return false;
		return str1.equalsIgnoreCase(str2);
	}

	/**
	 * 判断是否整个字符串由英文组成
	 * 
	 * @param word
	 * @return
	 */
	public static boolean isAllEnglishLetter(String word) {
		int len = word.length();
		int i = 0;

		while (i < len
				&& ((word.charAt(i) >= 'A' && word.charAt(i) <= 'Z') || (word
						.charAt(i) >= 'a' && word.charAt(i) <= 'z'))) {
			i++;
		}
		if (i < len)
			return false;

		return true;
	}

	/**
	 * 是否在此范围内
	 * 
	 * @param usedTime
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean isBetween(long usedTime, long min, long max) {

		if (usedTime >= min && usedTime <= max) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否按正则包含
	 * 
	 * @param text
	 * @param pattern
	 * @return
	 */
	public static boolean isRegexContains(String text, String pattern) {

		if (pattern == null)
			throw new IllegalArgumentException();
		if (text == null)
			return false;
		Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(text);
		return matcher.find();
	}

	/**
	 * 验证file是否比file2新
	 * 
	 * @param file
	 * @param file2
	 * @return
	 */
	public static boolean isLatestModifyFile(File file, File file2) {

		if (file != null && file.exists()) {
			if (file2 == null || !file2.exists())
				return true;
			if (file.lastModified() > file2.lastModified())
				return true;
		}
		return false;
	}

	/**
	 * 判断当前系统是不是windows系统
	 * 
	 * @return
	 */
	public static boolean isWindowsOS() {
		boolean isWindowsOS = false;
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().indexOf("windows") > -1) {
			isWindowsOS = true;
		}
		return isWindowsOS;
	}

	/**
	 * 判断是否是空的列表
	 * 
	 * @param list
	 * @return
	 */
	public static boolean isNull(java.util.Iterator<?> iterator) {

		return (iterator == null || !iterator.hasNext());
	}

	/**
	 * Description: 检查身份证号码是否符合规则
	 * 
	 * @param idCode
	 *            String 身份证号码
	 * @return boolean 身份证号码正确,则返回true，否则返回false
	 */
	public static boolean isIdCard(String idCode) {
		int ai[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
		boolean flag = false;
		if (idCode != null)
			switch (idCode.length()) {
			default:
				break;

			case 15: // '\017'
				flag = true;
				break;

			case 18: // '\022'
				int i = 0;
				for (int k = 0; k < 18; k++) {
					char c = idCode.charAt(k);
					int j;
					if (c == 'X' || c == 'x')
						j = 10;
					else if (c <= '9' || c >= '0')
						j = c - 48;
					else
						return flag;
					i += j * ai[k];
				}

				if (i % 11 == 1)
					flag = true;
				break;
			}
		return flag;
	}

}
