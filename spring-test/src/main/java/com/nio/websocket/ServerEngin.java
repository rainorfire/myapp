package com.nio.websocket;

public interface ServerEngin {

	/**
	 * 启动服务
	 * @param port
	 */
	void startServer(Integer port);
	
	/**
	 * 关闭服务
	 */
	void stopServer();
}
