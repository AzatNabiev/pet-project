package ru.itis.semwork.lmssystem2.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import ru.itis.semwork.lmssystem2.security.jwt.JwtAuthenticationFilter;
import ru.itis.semwork.lmssystem2.security.jwt.JwtAuthenticationProvider;
import ru.itis.semwork.lmssystem2.security.jwt.JwtLogoutFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private JwtLogoutFilter jwtLogoutFilter;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(jwtLogoutFilter, LogoutFilter.class)
                .authorizeRequests()
                // REG/AUTH/LOGOUT endpoints
                .antMatchers("/*").permitAll()
                .antMatchers("/logout").hasAnyAuthority()
                .antMatchers("api/v1/auth/login").permitAll()
                .antMatchers("api/v1/reg").permitAll()
                // USER endpoints
                .antMatchers(HttpMethod.GET, "/api/v1/user").hasAnyAuthority("ADMIN", "TEACHER", "STUDENT")
                .antMatchers(HttpMethod.GET, "/api/v1/user/{id}").hasAnyAuthority("ADMIN", "TEACHER", "STUDENT")
                .antMatchers(HttpMethod.PUT, "/api/v1/user/{id}").hasAnyAuthority("ADMIN", "STUDENT")
                .antMatchers(HttpMethod.DELETE, "/api/v1/user/{id}").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/v1/user/{user-id}/lesson/{lesson-id}").hasAnyAuthority("ADMIN", "STUDENT", "TEACHER")
                .antMatchers(HttpMethod.DELETE, "/api/v1/user/{user-id}/lesson/{lesson-id}").hasAnyAuthority("ADMIN", "STUDENT", "TEACHER")
                // LESSON endpoints
                .antMatchers(HttpMethod.PUT,"/api/v1/lesson/{id}").hasAnyAuthority("ADMIN", "TEACHER")
                .antMatchers(HttpMethod.GET,"/api/v1/lesson").hasAnyAuthority("ADMIN", "TEACHER", "STUDENT")
                .antMatchers(HttpMethod.POST,"/api/v1/lesson").hasAnyAuthority("ADMIN", "TEACHER")
                .antMatchers(HttpMethod.GET, "/api/v1/lesson/{id}").hasAnyAuthority("ADMIN", "TEACHER", "STUDENT")
                .antMatchers(HttpMethod.DELETE, "/api/v1/lesson/{id}").hasAnyAuthority("ADMIN", "TEACHER")
                // FILE endpoints
                .antMatchers("/api/v1/file/*").hasAnyAuthority("TEACHER", "ADMIN")
                .and()
                .sessionManagement().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(jwtAuthenticationProvider);
    }
}
