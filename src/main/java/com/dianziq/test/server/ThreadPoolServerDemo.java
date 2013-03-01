package com.dianziq.test.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;

import com.dianziq.test.UserService;
import com.dianziq.test.UserServiceImpl;
import com.dianziq.test.UserService.Iface;
import com.dianziq.test.UserService.Processor;

public class ThreadPoolServerDemo {
	public static final int SERVER_PORT = 7090;

	public void start() {
		try {
			System.out.println("UserServer start ....");

			TProcessor tprocessor = new UserService.Processor<UserService.Iface>(
					new UserServiceImpl());

			TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
			TBinaryProtocol.Factory profactory = new TBinaryProtocol.Factory();

			TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(
					serverTransport);
			tArgs.minWorkerThreads(10);
			tArgs.maxWorkerThreads(20);
			tArgs.processor(tprocessor);
			tArgs.protocolFactory(profactory);
			TServer server = new TThreadPoolServer(tArgs);
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
		ThreadPoolServerDemo server = new ThreadPoolServerDemo();
		server.start();
	}

}
