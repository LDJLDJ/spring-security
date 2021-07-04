package com.mayikt.config;

import com.mayikt.repository.SysPermissionRepository;
import com.mayikt.repository.SysUserRepository;
import com.mayikt.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Component
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private SysPermissionRepository sysPermissionRepository;

    @Autowired
    private MemberDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            //把用户数据写入内存中
//        auth.inMemoryAuthentication()
//                .withUser("mayikt_admin")
//                .password("123")
//                .authorities("admin")
//                .and()
//                .withUser("mayikt_manager")
//                .password("123")
//                .authorities("manager")
//                .and()
//                .withUser("mayikt_user")
//                .password("123")
//                .authorities("user");
        //用户信息从db中取
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());


    }

    /**
     * java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
     *  原因：升级为Security5.0以上密码支持多种加密方式
     */
//    @Bean
//    private static NoOpPasswordEncoder noOpPasswordEncoder(){
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //也可以自定义加密。比如自己弄个加密算法啥的
//        PasswordEncoder passwordEncoder = new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence rowPassword) {
//                //这里返回加密后的密码
//                return MD5Util.encode((String) rowPassword);
//            }
//
//            @Override
//            public boolean matches(CharSequence rowPassword, String dbPassword) {
//                String encodeRowPassword = encode(rowPassword);
//                return dbPassword.equals(encodeRowPassword);
//            }
//        };
        return passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //配置认证方式token form表单 设置为httpBasic模式
//        http.httpBasic()
//                .and()
//                .authorizeRequests()//授权请求
//                .antMatchers("/**")
//                .fullyAuthenticated();

        //配置认证方式token form表单 设置为formLogin模式
//        http.formLogin()
//                .and()
//                //授权请求
//                .authorizeRequests()
//                .antMatchers("/**")
//                //任何匹配到/*的都需要授权认证
//                .fullyAuthenticated();

        /**
         *
         */
//        http.formLogin()
//                .loginPage("/user/loginPage")
//                .loginProcessingUrl("/user/login")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/user/getUserName").hasAnyAuthority("admin")
//                .antMatchers("/user/getUserSex").hasAnyAuthority("admin","manager")
//                .antMatchers("/user/getUserAge").hasAnyAuthority("admin","manager","user")
//                .antMatchers("/user/loginPage","/user/login").permitAll()
//                .anyRequest()
//                .fullyAuthenticated()
//                .and()
//                //这玩意必须加，要不然一直无法进入，springsecurity 默认是打开的
//                .csrf().disable();

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = http.authorizeRequests();
        //把权限表中的对应关系全部写入到security的配置中去
        sysPermissionRepository.findAll().stream().forEach(i -> {
            authorizeRequests.antMatchers(i.getUrl()).hasAnyAuthority(i.getPermTag());
        });
        authorizeRequests.
                antMatchers("/user/loginPage","/user/login").permitAll()
                .anyRequest()
                .fullyAuthenticated()
                .and()
                .formLogin()
                .loginPage("/user/loginPage")
                .loginProcessingUrl("/user/login")
                .and()
                .csrf().disable();
    }
}
