package com.tianjian.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.tianjian.data.bean.UserDO;
import com.tianjian.data.service.UserCurd;
import com.tianjian.model.view.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.tianjian.security.service.JwtUserService;
import org.springframework.stereotype.Service;


@Service
public class JsonLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Autowired
	private JwtUserService jwtUserService;

	@Autowired
	private UserCurd userCurd;


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String token = jwtUserService.saveUserLoginInfo((UserDetails)authentication.getPrincipal());
		Map<String, Object> authInfo = new HashMap<>();
		authInfo.put("Authorization", token);
		String accountName = ((UserDetails) authentication.getPrincipal()).getUsername();
		UserDO userDO = userCurd.findByAccount(accountName);
		authInfo.put("userInfo", userDO);
		ResponseData<Map<String, Object>> responseData = new ResponseData<>();
        responseData.setCode(0);
        responseData.setMsg("login success");
        responseData.setData(authInfo);
		response.setHeader("Authorization", token);
		try {
			write(response, responseData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void write(HttpServletResponse response,Object o)throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(JSONObject.toJSONString(o));
		out.flush();
		out.close();
	}


	
}
