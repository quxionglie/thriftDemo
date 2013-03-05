package com.dianziq.test;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceImpl implements UserService.Iface {
	Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public LoginResponse login(String username, String password)
			throws TException {
		LoginResponse response = new LoginResponse();
		if ("tom".equals(username) && "123456".equals(password)) {
			response.setCode("0000");
			response.setMsg(username + "登录成功");
			response.setToken("" + System.currentTimeMillis());
		} else {
			response.setCode("1000");
			response.setMsg(username + "用户名或密码不正确");
		}
		log.info(username + " 请求");
		return response;
	}

}
