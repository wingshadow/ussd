package com.inspur.ussdplate;

import com.inspur.ussdplate.ussdpara.USSDPara;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import ljwf.Log;

public class SocketManager
{

	private static SocketManager _instance;
	private USSDPara up;
	private Socket s;

	public static synchronized SocketManager getInstance()
	{
		if (_instance == null)
			_instance = new SocketManager();
		return _instance;
	}

	private SocketManager()
	{
		up = USSDPara.getInstance();
		s = null;
	}

	public Socket getSocket()
		throws IOException
	{
		s = new Socket(up.getIsmgIP(), up.getIsmgPort());
		if (s == null)
		{
			try
			{
				Thread.sleep(3000L);
				Log.info("网络连接不上USSD网关");
			}
			catch (InterruptedException e)
			{
				Log.info("网络连接网关失败:"+e.getLocalizedMessage());
			}
			s = getSocket();
		}
		Log.info("网络连接成功");
		return s;
	}

	public boolean checkSocket()
	{
		if (s == null)
			return false;
		DataInputStream in;
		try
		{
			in = new DataInputStream(s.getInputStream());
		}
		catch (Exception ex)
		{
			return false;
		}
		return true;
	}

	public void freeSocket()
	{
		try
		{
			if (s != null)
				s.close();
		}
		catch (Exception exception) { }
		s = null;
	}
}
