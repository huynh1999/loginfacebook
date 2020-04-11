/*
	@author:Quang Truong
	@date: Jan 15, 2020
*/

package com.jwatgroupb.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.jwatgroupb.util.SecurityUtils;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		String targetUrl = determineTargetUrl(authentication);
		if (response.isCommitted()) {
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	private String determineTargetUrl(Authentication authentication) {
		String url = "";

		List<String> roles = SecurityUtils.getAuthorities();
		if (isCustomer(roles)) {
			url = "/LoginSuccessful";
		}else {
			url="/customerAccessDenied";
		}
		return url;
	}


	private boolean isCustomer(List<String> roles) {
		if (roles.contains("customer")) {
			return true;
		}
		return false;
	}
	
}
