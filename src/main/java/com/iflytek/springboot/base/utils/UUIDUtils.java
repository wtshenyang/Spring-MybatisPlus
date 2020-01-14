package com.iflytek.springboot.base.utils;
import java.util.UUID;

public class UUIDUtils {
	
	/**
	 * 获取UUID字符串，不带"-"
	 * @see {@link UUID#toString()}
	 * @return
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
        long mostSigBits = uuid.getMostSignificantBits();
        long leastSigBits = uuid.getLeastSignificantBits();
		return (digits(mostSigBits  >> 32, 8) + 
                digits(mostSigBits >> 16, 4) + 
                digits(mostSigBits, 4) + 
                digits(leastSigBits >> 48, 4) + 
                digits(leastSigBits, 12));
    }

    /** Returns val represented by the specified number of hex digits. */
    private static String digits(long val, int digits) {
        long hi = 1L << (digits * 4);
        return Long.toHexString(hi | (val & (hi - 1))).substring(1);
    }
	
	/*public static String getUUID() {
		String s = UUID.randomUUID().toString();

		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
				+ s.substring(19, 23) + s.substring(24);
	}*/

	public static String[] getUUID(int number) {
		if (number < 1) {
			return null;
		}
		String[] ss = new String[number];
		for (int i = 0; i < number; ++i) {
			ss[i] = getUUID();
		}
		return ss;
	}

	public static String getRandomUUID() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "");
		return uuid;
	}

	public static void main(String[] args) {
		System.out.println(getRandomUUID());
	}
}
