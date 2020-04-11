package com.jwatgroupb.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Component;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.Arrays;

@Component
public class LoginFB {
    public static String FACEBOOK_APP_ID = "745329255998966";
    public static String FACEBOOK_APP_SECRET = "dab3b3eb6d7fdf7e59a93732f4806871";
    public static String FACEBOOK_REDIRECT_URL = "https://testloginfb99.herokuapp.com/login-facebook";
    public static String FACEBOOK_LINK_GET_TOKEN = "https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&redirect_uri=%s&code=%s";
    public String url="https://www.facebook.com/dialog/oauth?client_id=745329255998966&redirect_uri=https://testloginfb99.herokuapp.com/login-facebook";
    public String getToken(final String code) throws ClientProtocolException, IOException {
        String link = String.format(FACEBOOK_LINK_GET_TOKEN, FACEBOOK_APP_ID, FACEBOOK_APP_SECRET, FACEBOOK_REDIRECT_URL, code);
        System.out.println("URL:"+link);
        String response = Request.Get(link).execute().returnContent().asString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response).get("access_token");
        return node.textValue();
    }
    public com.restfb.types.User getUserInfo(final String accessToken) {
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, FACEBOOK_APP_SECRET, Version.LATEST);
        return facebookClient.fetchObject("me", com.restfb.types.User.class);
    }
    public String getUserInfomore(final String accessToken) {
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, FACEBOOK_APP_SECRET, Version.LATEST);
        return facebookClient.fetchObjects(Arrays.asList("me", "1382679095266585"), String.class, Parameter.with("fields","name,id,email,picture"));
    }
}
