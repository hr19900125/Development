package com.ryan.java.dp.factory2;

public class MailSender implements Sender{

	@Override
	public void send() {
        System.out.println("This is mail Sender!");		
	}

}
