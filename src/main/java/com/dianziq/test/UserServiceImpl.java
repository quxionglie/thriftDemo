package com.dianziq.test;

import org.apache.thrift.TException;

public class UserServiceImpl implements UserService.Iface {

	@Override
	public LoginResponse login(String username, String password)
			throws TException {
		LoginResponse response = new LoginResponse();
		if ("tom".equals(username) && "123456".equals(password)) {
			response.setCode("0000");
			response.setMsg("登录成功");
			response.setToken("" + System.currentTimeMillis());
		} else {
			response.setCode("1000");
			response.setMsg("用户名或密码不正确");
		}

		return response;
	}

}
