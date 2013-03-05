package com.dianziq.test.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianziq.test.LoginResponse;
import com.dianziq.test.UserService;

public class SimpleAsyncClientDemo {
	Logger log = LoggerFactory.getLogger(SimpleAsyncClientDemo.class);

	public static final String SERVER_IP = "localhost";
	public static final int SERVER_PORT = 7090;
	public static final int TIMEOUT = 3000;

	public void login(final String userName, final String passwd) {
		try {
			final TTransport transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
			// 协议要和服务端一致
			TProtocol protocol = new TBinaryProtocol(transport);
			final UserService.Client client = new UserService.Client(protocol);
			transport.open();
			ExecutorService executor = Executors.newSingleThreadExecutor(); // Handy way to run code in a thread
			final MyClient myClient = new MyClient();
			Runnable task = new Runnable() { 
			    public void run() {  
					LoginResponse loginResponse;
					try {
						loginResponse = client.login(userName, passwd);
						myClient.sendResult(loginResponse);
					} catch (TException e) {
						e.printStackTrace();
					} 
			    }
			};
			executor.submit(task);  
			//Thread.sleep(1000);
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

   class MyClient {
		public  void sendResult(LoginResponse response) {
			try {
				log.info(response.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleAsyncClientDemo client = new SimpleAsyncClientDemo();
		client.login("tom", "123456");
		client.login("mark", "123456");
	}

}