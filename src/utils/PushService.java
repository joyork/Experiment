package utils;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;


public class PushService {
//
//	public static Logger logger = Logger.getLogger(PushService.class);
//	/**
//	 * 所有连接的容器
//	 */
//	public static Map<Long, Socket> socketContainer = new HashMap<Long, Socket>();
//	/**
//	 * 初始化连接的请求
//	 */
//	public static final int ACTION_CONNECTION = 1;
//
//	/**
//	 * 是否初始化
//	 */
//	public static boolean inted = false;
//
//	/**
//	 * 初始化
//	 */
//	public static void init() {
//
//		logger.info("initing PushService...");
//		ExecutorService singleExecutor = Executors.newSingleThreadExecutor();
//		singleExecutor.execute(new Runnable() {
//			@Override
//			public void run() {
//				this.serverReceviedByTcp();
//			}
//
//			public final void serverReceviedByTcp() {
//				// 声明一个ServerSocket对象
//				ServerSocket serverSocket = null;
//				try {
//					// 创建一个ServerSocket对象，并让这个Socket在1989端口监听
//					serverSocket = new ServerSocket(SystemGlobals
//							.getIntPreference("push.server.port", 1988));
//					// 调用ServerSocket的accept()方法，接受客户端所发送的请求，
//					InputStream in = null;
//					OutputStream out = null;
//					BufferedReader reader = null;
//					PrintWriter writer = null;
//					logger.info("PushService init success !");
//					while (true) {
//						Socket socket = serverSocket.accept();
//						// 从Socket当中得到InputStream对象
//						in = socket.getInputStream();
//						reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
//						String inputStr = reader.readLine();
//						JSONObject json = JSONUtil.parseJSONObject(inputStr);
//						if (json != null) {
//							int action = json.getInt("action");
//							// 连接
//							if (action == PushService.ACTION_CONNECTION) {
//								Long uid = json.getLong("uid");
//								// 要写出去的流对象
//								out = socket.getOutputStream();
//								writer = new PrintWriter(new OutputStreamWriter(out,"UTF-8"));
//								// 需要加上 “\n”否则flush认为这一行没有完成
//								writer.write("{success:true,action:1}" + "\n");
//								writer.flush();
//
//								socketContainer.put(uid, socket);
//							}
//						}
//						logger.info("PushService is running...");
//						// socket.close();
//					}
//				} catch (Exception e) {
//					logger.info("PushService error : " + e);
//					e.printStackTrace();
//				}
//			}
//		});
//
//	}
}
