package com.dianziq.test.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;

import com.dianziq.test.UserService;
import com.dianziq.test.UserServiceImpl;

public class ThreadedSelectorServerDemo {
	public static final int SERVER_PORT = 7090;

	public void start() {
		try {
			System.out.println("UserServer start ....");

			TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(
					SERVER_PORT);
			TBinaryProtocol.Factory profactory = new TBinaryProtocol.Factory();

			TProcessor tprocessor = new UserService.Processor<UserService.Iface>(
					new UserServiceImpl());

			TThreadedSelectorServer.Args tArgs = new TThreadedSelectorServer.Args(
					serverTransport);
			tArgs.processor(tprocessor);
			tArgs.protocolFactory(profactory);

			TServer server = new TThreadedSelectorServer(tArgs);
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
		ThreadedSelectorServerDemo server = new ThreadedSelectorServerDemo();
		server.start();
	}

}
