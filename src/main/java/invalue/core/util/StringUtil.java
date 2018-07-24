
package invalue.core.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;


/**
 * The Class StringUtil.
 */
public final class StringUtil {

	/** The Constant EMPTY. */
	public static final String EMPTY = "";

	/** The Constant REPLACE_CHARS. */
	private static final String REPLACE_CHARS = "aáàảãạâấầẩẫậăắằẳẵặeéèẻẽẹêếềểễệiíìỉĩịuúùủũụưứừửữựoóòỏõọôốồổỗộơớờởỡợyýỳỷỹỵ";

	/** The Constant LINK_PATTERN. */
	private static final Pattern LINK_PATTERN = Pattern
			.compile("https?://[^ <>]*");

	/** The Constant HTML_TAG_PATTERN. */
	private static final Pattern HTML_TAG_PATTERN = Pattern
			.compile("<(.|\r|\n)*?>");

	/** The Constant charset. */
	private static final String charset = "!0123456789abcdefghijklmnopqrstuvwxyz";

	public static final String NUMBERS = "0123456789";
	public static final String CHARS = "qwertyuiopplkjhgfdsazxcvbnm";
	public static final String UPCASECHARS = "QWERTYUIOPLKJHGFDSAZXCVBNM";
	public static final String SPECIALCHARS = "@#$%^&+=";

	private static final String COUNTRY_CODE = "84";

	/** The Constant REF_HTML_DEV_PATTERN. */
	private static final Pattern REF_HTML_DEV_PATTERN = Pattern
			.compile("<div id=\"refHTML\">&nbsp;</div>");

	private static final String[] htmlChar = new String[] { "&", "<", ">", "'",
			"\"" };
	private static final String[] htmlNameCode = new String[] { "&amp;",
			"&lt;", "&gt;", "&apos;", "&quot;" };

	private static final Pattern SPECIAL_CHAR_PATTERN = Pattern
			.compile("[,.?;:\'\"`~!@#$%&*()^<>{}\\[\\]\\\\/ ]");

	private static final Pattern SPECIAL_CHAR_BADWORD_PATTERN = Pattern
			.compile("([,.?;:\'\"`~!@#$%&*()^<>{}\\[\\]\\\\/])");

	private static final Pattern PATTERN_NONE_WORD = Pattern
			.compile("([^a-zA-Z0-9 ])");

	/** STRING_SEARCH_LIKE_ALL. */
	public static final String STRING_SEARCH_LIKE_ALL = "%%";

	public static final String SMS_SERVICE_TEMPLATE = "\\{P\\d+\\}";
	
	private static final String[] oracleTextKeywords = new String[] { "ACCUM",
			"ABOUT", "NOT", "OR", "AND", "BT", "BTG", "BTP", "BTI", "NT",
			"NTG", "NTP", "NTI", "PT", "RT", "RT", "SQE", "SYN", "TR", "TRSYN",
			"TT", "FUZZY", "HASPATH", "INPATH", "MINUS", "NEAR", "WITHIN" };


	private static enum OrlSearchOperator {

		AND(";"), OR(",");

		private String value;

		public String getValue() {
			return value;
		}

		OrlSearchOperator(String Value) {
			this.value = Value;
		}
	}


	public static boolean isNullOrEmpty(final String s) {
		return (s == null || s.trim().length() == 0);
	}


	public static boolean isNullOrEmptyNotTrim(final String s) {
		return (s == null || s.length() == 0);
	}
	
	public static boolean validateMaxLength(String t, int max){
		if(isNullOrEmpty(t))
			return true;
		if(t.length()<=max)
			return true;
		return false;
		
	}
}
