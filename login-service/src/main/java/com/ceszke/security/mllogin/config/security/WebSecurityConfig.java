package com.ceszke.security.mllogin.config.security;

import com.ceszke.security.mllogin.client.ValidatorClient;
import com.ceszke.security.mllogin.security.MLSimpleUrlAuthenticationFailureHandler;
import com.ceszke.security.mllogin.security.MLUsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity(debug = false)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ValidatorClient validatorClient;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login*").anonymous()
                //.and()
                .antMatchers("/css/**", "/js/**", "/img/**", "/favicon.ico", "/error").permitAll()
                .anyRequest().authenticated()
//                .and()
//                 .formLogin()
//                 .loginPage("/login")
//                 .defaultSuccessUrl("/")
//                 .failureForwardUrl()
                .and()
                .addFilter(authenticationFilter())
                .exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                .and().logout().invalidateHttpSession(false)
        //.authenticationDetailsSource(new CustomWebAuthenticationDetailsSource())
        ;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("password")).roles("USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MLUsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
        MLUsernamePasswordAuthenticationFilter authenticationFilter = new MLUsernamePasswordAuthenticationFilter(validatorClient);
        authenticationFilter.setAuthenticationSuccessHandler(new SavedRequestAwareAuthenticationSuccessHandler());
        //authenticationFilter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error"));
        authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        authenticationFilter.setAuthenticationManager(authenticationManagerBean());
        authenticationFilter.setAuthenticationFailureHandler(new MLSimpleUrlAuthenticationFailureHandler("/login?error"));
        return authenticationFilter;
    }

//    @Bean
//    public LoginUrlAuthenticationEntryPoint authenticationEntryPoint() {
//        return new LoginUrlAuthenticationEntryPoint("/login");
//    }


//    @Bean
//    public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() {
//        return new MLUsernamePasswordAuthenticationFilter();
//    }

//    @Bean
//    public CustomWebAuthenticationDetailsSource customWebAuthenticationDetailsSource() {
//         return new CustomWebAuthenticationDetailsSource();
//    }
}
