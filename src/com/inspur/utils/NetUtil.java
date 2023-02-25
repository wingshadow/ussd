package com.inspur.utils;

import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class NetUtil
{

	public NetUtil()
	{
	}

	public static boolean isReachable(String host, int port)
	{
		try
		{
			(new Socket()).connect(new InetSocketAddress(host, port), 200);
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}

	public static void main(String args[])
	{
		if (isReachable("218.57.146.149", 81))
			System.out.println("OK");
		else
			System.out.println("Error");
	}
}
