package com.ryan.java.dp.command;

public class MyCommand implements Command{

	private Receiver receiver;
	
	public MyCommand(Receiver receiver){
		this.receiver = receiver;
	}
	
	@Override
	public void exe() {
        receiver.action();		
	}


}
