package com.ryan.java.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 使用静态方法来写模板
 * @author Ryan
 *
 */
public class InputStreamProcessingTemplate3 {

	
	public static void process(String fileName, InputStreamProcessor processor) {
		IOException processException = null;
		InputStream input = null;
		try {
			input= new FileInputStream(fileName);
			processor.process(input);
		} catch(IOException e) {
			processException = e;
		} finally {
			if(input != null) {
				try {
					input.close();
				} catch (IOException e) {
					if (processException != null) {
						throw new MyException(processException, e, "Error message ..." + fileName);
					} else {
						throw new MyException(e, "Error closing InputStream for file " + fileName);
					}
				} 
			}
			
			if(processException != null) {
				throw new MyException(processException, "Error processing InputStream for file " + fileName);
			}
		}
	}
}
