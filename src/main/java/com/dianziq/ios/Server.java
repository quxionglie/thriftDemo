package com.dianziq.ios;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

public class Server {

	private void start() {
		try {
			BulletinBoard.Processor processor = new BulletinBoard.Processor(
					new BulletinBoardImpl());
			TServerTransport serverTransport = new TServerSocket(7911);
			TServer server = new TSimpleServer(
					new Args(serverTransport).processor(processor));

			System.out.println("Starting server on port 7911 ...");
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void startSimple() {
		try {
			System.out.println("Starting server on port 7911 ...");

			TProcessor tprocessor = new BulletinBoard.Processor<BulletinBoard.Iface>(
					new BulletinBoardImpl());

			TServerSocket serverTransport = new TServerSocket(7911);
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

	private void startThreadPool() {
		try {
			BulletinBoard.Processor processor = new BulletinBoard.Processor(
					new BulletinBoardImpl());
			TServerTransport serverTransport = new TServerSocket(7911);
			TBinaryProtocol.Factory profactory = new TBinaryProtocol.Factory();

			TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(
					serverTransport);
			tArgs.minWorkerThreads(10);
			tArgs.maxWorkerThreads(20);
			tArgs.processor(processor);
			tArgs.protocolFactory(profactory);
			TServer server = new TThreadPoolServer(tArgs);

			// TServer server = new TSimpleServer(new
			// Args(serverTransport).processor(processor));

			System.out.println("Starting server on port 7911 ...");
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void startNonBlocking() {
		try {
			BulletinBoard.Processor processor = new BulletinBoard.Processor(
					new BulletinBoardImpl());

			TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(
					7911);

			TBinaryProtocol.Factory profactory = new TBinaryProtocol.Factory();

			TNonblockingServer.Args tArgs = new TNonblockingServer.Args(
					serverTransport);
			tArgs.processor(processor);
			tArgs.protocolFactory(profactory);
			TServer server = new TNonblockingServer(tArgs);

			// TServer server = new TSimpleServer(new
			// Args(serverTransport).processor(processor));

			System.out.println("Starting server on port 7911 ...");
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		Server srv = new Server();
		// srv.startSimple();
		 srv.startThreadPool();
		//srv.startNonBlocking();
	}
}