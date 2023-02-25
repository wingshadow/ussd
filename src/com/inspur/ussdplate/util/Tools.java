package com.inspur.ussdplate.util;


public class Tools
{

	private static String HexCode[] = {
		"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", 
		"a", "b", "c", "d", "e", "f"
	};

	private Tools()
	{
	}

	public static String byteToHexString(byte b)
	{
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return (new StringBuilder(String.valueOf(HexCode[d1]))).append(HexCode[d2]).toString();
	}

	public static String byteArrayToHexString(byte b[])
	{
		String result = "";
		for (int i = 0; i < b.length; i++)
			result = (new StringBuilder(String.valueOf(result))).append(byteToHexString(b[i])).toString();

		return result;
	}

	public static String byteToString(byte b[])
	{
		for (int i = 0; i < b.length; i++)
			if (b[i] == 0)
			{
				byte r[] = new byte[i];
				System.arraycopy(b, 0, r, 0, i);
				return (new String(r)).trim();
			}

		return (new String(b)).trim();
	}

	public static int byte2int(byte b[])
	{
		return b[3] & 0xff | (b[2] & 0xff) << 8 | (b[1] & 0xff) << 16 | (b[0] & 0xff) << 24;
	}

	public static byte[] byteH2L(byte b[])
	{
		byte tmp[] = new byte[4];
		for (int i = 0; i < b.length; i++)
		{
			tmp[0] = b[b.length - 1];
			tmp[1] = b[b.length - 2];
			tmp[2] = b[b.length - 3];
			tmp[3] = b[b.length - 4];
		}

		return tmp;
	}

	public static int byte2int(byte b[], int offset)
	{
		return b[offset + 3] & 0xff | (b[offset + 2] & 0xff) << 8 | (b[offset + 1] & 0xff) << 16 | (b[offset] & 0xff) << 24;
	}

	public static byte[] long2byte(long n)
	{
		byte b[] = new byte[8];
		b[0] = (byte)(int)(n & 255L);
		b[1] = (byte)(int)(n >> 8 & 255L);
		b[2] = (byte)(int)(n >> 16 & 255L);
		b[3] = (byte)(int)(n >> 24 & 255L);
		b[4] = (byte)(int)(n >> 32 & 255L);
		b[5] = (byte)(int)(n >> 40 & 255L);
		b[6] = (byte)(int)(n >> 48 & 255L);
		b[7] = (byte)(int)(n >> 56 & 255L);
		return b;
	}

	public static void long2byte(long n, byte buf[], int offset)
	{
		buf[offset] = (byte)(int)(n & 255L);
		buf[offset + 1] = (byte)(int)(n >> 8 & 255L);
		buf[offset + 2] = (byte)(int)(n >> 16 & 255L);
		buf[offset + 3] = (byte)(int)(n >> 24 & 255L);
		buf[offset + 4] = (byte)(int)(n >> 32 & 255L);
		buf[offset + 5] = (byte)(int)(n >> 40 & 255L);
		buf[offset + 6] = (byte)(int)(n >> 48 & 255L);
		buf[offset + 7] = (byte)(int)(n >> 56 & 255L);
	}

	public static byte[] int2byte(int n)
	{
		byte b[] = new byte[4];
		b[0] = (byte)(n >> 24 & 0xff);
		b[1] = (byte)(n >> 16 & 0xff);
		b[2] = (byte)(n >> 8 & 0xff);
		b[3] = (byte)(n & 0xff);
		return b;
	}

	public static byte[] int2Hbyte(int n)
	{
		byte b[] = new byte[4];
		b[0] = (byte)(n & 0xff);
		b[1] = (byte)(n >> 8 & 0xff);
		b[2] = (byte)(n >> 16 & 0xff);
		b[3] = (byte)(n >> 24 & 0xff);
		return b;
	}

	public static void int2byte(int n, byte buf[], int offset)
	{
		buf[offset] = (byte)(n >> 24 & 0xff);
		buf[offset + 1] = (byte)(n >> 16 & 0xff);
		buf[offset + 2] = (byte)(n >> 8 & 0xff);
		buf[offset + 3] = (byte)(n & 0xff);
	}

	public static void int2Hbyte(int n, byte buf[], int offset)
	{
		buf[offset] = (byte)(n >> 24 & 0xff);
		buf[offset + 1] = (byte)(n >> 16 & 0xff);
		buf[offset + 2] = (byte)(n >> 8 & 0xff);
		buf[offset + 3] = (byte)(n & 0xff);
	}

	public static byte[] short2byte(int n)
	{
		byte b[] = new byte[2];
		b[0] = (byte)(n >> 8 & 0xff);
		b[1] = (byte)(n & 0xff);
		return b;
	}

	public static void short2byte(int n, byte buf[], int offset)
	{
		buf[offset] = (byte)(n >> 8 & 0xff);
		buf[offset + 1] = (byte)(n & 0xff);
	}

	public static long byte2long(byte b[])
	{
		return byte2long(b, 0);
	}

	public static long byte2long(byte b[], int offset)
	{
		long tmp1 = (0xff & b[3 + offset]) << 24 | (0xff & b[2 + offset]) << 16 | (0xff & b[1 + offset]) << 8 | 0xff & b[offset];
		long tmp2 = (0xff & b[7 + offset]) << 24 | (0xff & b[6 + offset]) << 16 | (0xff & b[5 + offset]) << 8 | 0xff & b[4 + offset];
		long tmp = (tmp2 << 32 & 0xffffffff00000000L) + (tmp1 & 0xffffffffL);
		return tmp;
	}

	public static boolean checkMobile(String sMobile)
	{
		String sF6;
		String sB7;
		String sF2;
		sF6 = "";
		sB7 = "";
		sF2 = "";
		if (sMobile == null)
			return false;
		if (sMobile.length() != 11)
			return false;
		sF6 = sMobile.substring(0, 7);
		sF2 = sMobile.substring(0, 2);
		sB7 = sMobile.substring(7);
		int iT = Integer.valueOf(sF6).intValue();
		iT = Integer.valueOf(sB7).intValue();
		return sF2.equals("13");
	}

	public static byte[] byteAdd(byte src[], byte add[], int start, int end)
	{
		byte dst[] = new byte[(src.length + end) - start];
		for (int i = 0; i < src.length; i++)
			dst[i] = src[i];

		for (int i = 0; i < end - start; i++)
			dst[src.length + i] = add[start + i];

		return dst;
	}

}
