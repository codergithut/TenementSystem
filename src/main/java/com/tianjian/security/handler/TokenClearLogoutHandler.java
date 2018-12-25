package com.tianjian.security.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.tianjian.security.service.JwtUserService;
import org.springframework.stereotype.Service;


@Service
public class TokenClearLogoutHandler implements LogoutHandler {

	@Autowired
	private JwtUserService jwtUserService;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		clearToken(authentication);
	}
	
	protected void clearToken(Authentication authentication) {
		if(authentication == null)
			return;
		UserDetails user = (UserDetails)authentication.getPrincipal();
		if(user!=null && user.getUsername()!=null)
		    jwtUserService.deleteUserLoginInfo(user.getUsername());
	}

}
