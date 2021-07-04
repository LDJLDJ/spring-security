package com.mayikt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.stereotype.Component;

/**
 * oauth2认证授权Server端
 */
@Component
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //允许表单提交
        security.allowFormAuthenticationForClients()
                .checkTokenAccess("permitAll()");
    }

    /*
      *1. http://127.0.0.1:8080/oauth/authorize?client_id=mayikt&response_type=code 授权地址
     *2.然后得到带code的回调地址 http://127.0.0.1:8080/showMember?code=HFGWVN
     *可以通过code去获取access_token
     *POST请求 替换里边相应的属性
     *3.http://127.0.0.1:8080/oauth/token?grant_type=authorization_code&code=HFGWVN&client_id=mayikt&client_secret=123456&redirect_uri=http://127.0.0.1:8080/showMember
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // appid
                .withClient("mayikt")
                // appsecret
                .secret(passwordEncoder.encode("123456"))
                //授权码
                .authorizedGrantTypes("authorization_code")
                //作用域
                .scopes("all")
                //资源的id
                .resourceIds("mayikt_resource")
                //回调地址
                .redirectUris("http://127.0.0.1:8080/showMember");
    }
}
