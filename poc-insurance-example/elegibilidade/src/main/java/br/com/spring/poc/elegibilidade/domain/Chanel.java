package br.com.spring.poc.elegibilidade.domain;

public class Chanel {
	
	private int channelCode;
	private String channelName;
	
	public Chanel(int channelCode, String channelName) {
		this.channelCode = channelCode;
		this.channelName = channelName;
	}
	
	
	public int getChannelCode() {
		return channelCode;
	}
	
	public void setChannelCode(int channelCode) {
		this.channelCode = channelCode;
	}
	
	public String getChannelName() {
		return channelName;
	}
	
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	
	

}
