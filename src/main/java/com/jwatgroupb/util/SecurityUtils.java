/*
	@author:Quang Truong
	@date: Jan 16, 2020
*/

package com.jwatgroupb.util;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jwatgroupb.dto.MyUser;

public class SecurityUtils {

	public static MyUser getPrincipal() {
		MyUser myUser = (MyUser) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return myUser;
	}

	public static boolean isAuthenticanted() {
		if (SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
				// when Anonymous Authentication is enabled
				!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static List<String> getAuthorities() {
		List<String> results = new ArrayList<String>();

		List<GrantedAuthority> authorities = (List<GrantedAuthority>) (SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities());
		for (GrantedAuthority authority : authorities) {
			results.add(authority.getAuthority());
		}
		return results;
	}
	
	public static String passwordEncoder(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}
	
	public static String replaceNull(String string){
	    if (string == null) {
			return "";
		}
	    return string;
	}
	
}
