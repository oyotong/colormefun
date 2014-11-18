package shop.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ognl.Ognl;
import ognl.OgnlException;

public class OgnlUtils {
	public static String getValue(String exp,Object root){
		if(null == exp){
			return "";
		}
		try {
			StringBuffer sb = new StringBuffer();
			Pattern p = Pattern.compile("\\$\\{(.+?)\\}");
			Matcher m = p.matcher(exp);
			while(m.find()){
				String temp = m.group(1);
				m.appendReplacement(sb, Ognl.getValue(temp, root).toString());
			}
			m.appendTail(sb);
			return sb.toString();
		} catch (OgnlException e) {
			e.printStackTrace();
			return "";
		}
	}
}
