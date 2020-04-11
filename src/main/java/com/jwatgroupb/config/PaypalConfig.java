/*
	@author:Quang Truong
	@date: Feb 10, 2020
*/

package com.jwatgroupb.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

@Configuration
public class PaypalConfig {
	
    private static final String clientId ="AfPJW1s7JgQY68uUbVRWpok_Oe3F-dJ5pKfWFb7DXhky7wQxuoZT1Y6Xw6o7ObHU7GoPGcParLWeV8bb";
    private static final String clientSecret="EDU68CFj9LfJN9EfAAd4M4DgnoYmjph14blCg0Ipd8z8ydWyZLM-_VcTbUA_SmitPbeN8cMsaMdVcmqU";
    private static final String mode="sandbox";
	
	@Bean
	public Map<String, String> paypalSdkConfig(){
		Map<String, String> configMap = new HashMap<String, String>();
		configMap.put("mode",mode);
		return configMap;
	}
    
	@Bean
	public OAuthTokenCredential oAuthTokenCredential() {
		return new OAuthTokenCredential(clientId, clientSecret,paypalSdkConfig());
	}
	
	@Bean
	public APIContext apiContext() throws PayPalRESTException {
		APIContext context= new APIContext(oAuthTokenCredential().getAccessToken());
		context.setConfigurationMap(paypalSdkConfig());
		return context;
	}
	
}
