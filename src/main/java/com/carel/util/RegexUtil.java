
package com.carel.util;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * Description:
 * @author Matthew Xu
 * @date Jan 13, 2021
 */
public class RegexUtil {

	public static boolean isPosOrNegIntegerOrFloat(String str) {
        return StringUtils.isNotBlank(str) ? Pattern.compile("^[-\\+]?(0|[1-9]\\d*)(\\.\\d+)?$").matcher(str).matches() : false;
    }
 
    public static boolean isPosOrNegFloat(String str) {
    	return StringUtils.isNotBlank(str) ? Pattern.compile("^[-\\+]?[\\d]+[.{1}][\\d]+$").matcher(str).matches() : false;
    }
 
    public static boolean isPosOrNegInteger(String str) {
    	return StringUtils.isNotBlank(str) ? Pattern.compile("^[-\\+]?[\\d]+$").matcher(str).matches() : false;
    }
    
}
