package com.jwatgroupb.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwatgroupb.dto.MyUser;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import org.apache.http.client.ClientProtocolException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class LoginFB {
    public static String FACEBOOK_APP_ID = "1144816859195777";
    public static String FACEBOOK_APP_SECRET = "f22967430ae09cc10f6ac01294299969";
    public static String FACEBOOK_REDIRECT_URL = "https://testloginfb99.herokuapp.com/login-facebook";
    public static String FACEBOOK_LINK_GET_TOKEN = "https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&redirect_uri=%s&code=%s";
    public String url = "https://www.facebook.com/dialog/oauth?client_id=1144816859195777&redirect_uri=https://testloginfb99.herokuapp.com/login-facebook";

    public String getToken(final String code) throws ClientProtocolException, IOException {
        String link = String.format(FACEBOOK_LINK_GET_TOKEN, FACEBOOK_APP_ID, FACEBOOK_APP_SECRET, FACEBOOK_REDIRECT_URL, code);
        System.out.println("URL:" + link);
        String response = Request.Get(link).execute().returnContent().asString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response).get("access_token");
        return node.textValue();
    }

    public com.restfb.types.User getUserInfo(final String accessToken) {
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, FACEBOOK_APP_SECRET, Version.LATEST);
        return facebookClient.fetchObject("me", com.restfb.types.User.class, Parameter.with("fields", "name,id,email,picture"));
    }

    public UserDetails buildUser(com.restfb.types.User user) {
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("customer"));
        UserDetails userDetail = new MyUser(user.getId(), "123", enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities);
        ((MyUser)userDetail).setName(user.getName());
        ((MyUser)userDetail).setEmail(user.getEmail());
        System.out.println(user.getName());
        return userDetail;
    }
}