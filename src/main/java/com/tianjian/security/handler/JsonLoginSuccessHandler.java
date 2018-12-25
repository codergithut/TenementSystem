package com.tianjian.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String token = jwtUserService.saveUserLoginInfo((UserDetails)authentication.getPrincipal());
		response.setHeader("Authorization", token);
	}
	
}
