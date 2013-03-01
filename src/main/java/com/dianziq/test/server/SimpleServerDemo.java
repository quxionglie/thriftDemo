package com.dianziq.test.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

import com.dianziq.test.UserService;
import com.dianziq.test.UserServiceImpl;

public class SimpleServerDemo {
	public static final int SERVER_PORT = 7090;

	public void start() {
		try {
			System.out.println("UserServer start ....");

			TProcessor tprocessor = new UserService.Processor<UserService.Iface>(
					new UserServiceImpl());

			TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
			TServer.Args tArgs = new TServer.Args(serverTransport);
			tArgs.processor(tprocessor);
			tArgs.protocolFactory(new TBinaryProtocol.Factory());
			TServer server = new TSimpleServer(tArgs);
			server.serve();

		} catch (Exception e) {
			System.out.println("Server start error!!!");
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleServerDemo server = new SimpleServerDemo();
		server.start();
	}

}

