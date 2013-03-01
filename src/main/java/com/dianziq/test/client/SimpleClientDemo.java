package com.dianziq.test.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.dianziq.test.LoginResponse;
import com.dianziq.test.UserService;

public class SimpleClientDemo {

	public static final String SERVER_IP = "localhost";
	public static final int SERVER_PORT = 7090;
	public static final int TIMEOUT = 30000;

	public void login(String userName, String passwd) {
		TTransport transport = null;
		try {
			transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
			// 协议要和服务端一致
			TProtocol protocol = new TBinaryProtocol(transport);
			UserService.Client client = new UserService.Client(protocol);
			transport.open();
			LoginResponse loginResponse = client.login(userName, passwd);
			System.out.println("Thrify client result =: " + loginResponse);
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		} finally {
			if (null != transport) {
				transport.close();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleClientDemo client = new SimpleClientDemo();
		client.login("tom", "123456");
		client.login("mark", "123456");
	}

}