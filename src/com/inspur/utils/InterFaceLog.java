package com.inspur.utils;

import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InterFaceLog {
	private String mobile;
	private String sendID;
	private Date sendTime;
	private String recvID;
	private Date recvTime;
	private String msgType;
	private String msgCotent;
	private Date createTime;
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSendID() {
		return sendID;
	}
	public void setSendID(String sendID) {
		this.sendID = sendID;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getRecvID() {
		return recvID;
	}
	public void setRecvID(String recvID) {
		this.recvID = recvID;
	}
	public Date getRecvTime() {
		return recvTime;
	}
	public void setRecvTime(Date recvTime) {
		this.recvTime = recvTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsgCotent() {
		return msgCotent;
	}
	public void setMsgCotent(String msgCotent) {
		this.msgCotent = msgCotent;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public InterFaceLog() {
		
	}
	
	public InterFaceLog(String mobile, String sendID, Date sendTime,
			String recvID, Date recvTime, String msgType, String msgCotent,
			Date createTime) {
		super();
		this.mobile = mobile;
		this.sendID = sendID;
		this.sendTime = sendTime;
		this.recvID = recvID;
		this.recvTime = recvTime;
		this.msgType = msgType;
		this.msgCotent = msgCotent;
		this.createTime = createTime;
	}
	
	public void log() {
		 try{ 
			 	DateFormat serialDateFormat = new SimpleDateFormat("yyyyMMdd");
		    	String fname = "/logs/ussd_log"+serialDateFormat.format(new Date())+".txt";
		    	RandomAccessFile rf=new RandomAccessFile(fname,"rw"); 
		    	rf.seek(rf.length());
		    	byte [] b; 
		    	StringBuffer sb = new StringBuffer();
		    	sb.append(this.mobile).append(" ")
		    	.append(this.sendID).append(" ");
		    	
		    	if(null!=this.sendTime)	sb.append(DateUtil.getDateyyyyMMddHHmmss(this.sendTime)).append(" ");
		    	else sb.append("null").append(" ");
		    	
		    	sb.append(this.recvID).append(" ");
		    	
		    	if(null!=this.recvTime)	sb.append(DateUtil.getDateyyyyMMddHHmmss(this.recvTime)).append(" ");
		    	else sb.append("null").append(" ");
		    	
		    	sb.append(this.msgType).append(" ")
		    	.append(this.msgCotent).append(" ")
		    	.append(DateUtil.getDateyyyyMMddHHmmss(this.createTime));
		    	
		    	String s = sb.toString()+"\r\n";
		    	b=s.getBytes(); 
		    	rf.write(b);//涓枃涓�鏍峰彲浠ュ啓鍏ユ枃鏈枃浠躲��
		    	rf.close();
		    } catch(Exception e){
		    	e.printStackTrace();
		    } 
	}
	
	
}
