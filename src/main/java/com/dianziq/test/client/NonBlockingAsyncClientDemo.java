package com.dianziq.test.client;

import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianziq.test.UserService;
import com.dianziq.test.UserService.AsyncClient.login_call;

public class NonBlockingAsyncClientDemo {
	Logger log = LoggerFactory.getLogger(NonBlockingAsyncClientDemo.class);

	public static final String SERVER_IP = "localhost";
	public static final int SERVER_PORT = 7090;

	public void login(String userName, String passwd) {
		TNonblockingSocket transport = null;
		try {
			TAsyncClientManager clientManager = new TAsyncClientManager();

			transport = new TNonblockingSocket(SERVER_IP, SERVER_PORT);
			TProtocolFactory protocol = new TBinaryProtocol.Factory();

			UserService.AsyncClient asyncClient = new UserService.AsyncClient(
					protocol, clientManager, transport);
			MyCallback callBack = new MyCallback();
			asyncClient.login(userName, passwd, callBack);

			log.info("Client calls .....");
			while (!callBack.isComplete()) {
				Thread.sleep(1);
			}
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}

	class MyCallback implements AsyncMethodCallback<login_call> {
		boolean isComplete = false;

		public boolean isComplete() {
			return isComplete;
		}

		// 返回结果
		@Override
		public void onComplete(login_call response) {
			log.info("onComplete");
			try {
				log.info(response.getResult().toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			isComplete = true;
		}

		// 返回异常
		@Override
		public void onError(Exception exception) {
			System.out.println("onError");
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NonBlockingAsyncClientDemo client = new NonBlockingAsyncClientDemo();
		client.login("tom", "123456");
		client.login("moon", "123456");
	}

}
