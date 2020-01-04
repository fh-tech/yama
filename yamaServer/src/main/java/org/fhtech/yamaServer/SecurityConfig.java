package org.fhtech.yamaServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.security.NoSuchAlgorithmException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource datasource;

    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/api/**").hasAuthority("MSRead")
                    .antMatchers(HttpMethod.PUT, "/api/**").hasAuthority("MSWrite")
                    .antMatchers(HttpMethod.PATCH, "/api/**").hasAuthority("MSWrite")
                    .antMatchers(HttpMethod.POST, "/api/**").hasAuthority("MSWrite")
                    .antMatchers(HttpMethod.DELETE, "/api/**").hasAuthority("MSWrite")
                .and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/ws/**").hasAuthority("MSWrite")
                    .antMatchers(HttpMethod.POST, "/ws/movies/searchMovies").hasAuthority("MSRead")
                .and()
                .authorizeRequests().anyRequest().permitAll()
                .and().csrf().disable();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(datasource)
                .usersByUsernameQuery(
                        "select username,password, enabled from user where username=?")
                .authoritiesByUsernameQuery(
                        "SELECT username, rolename FROM ((user JOIN user_role ur on user.id = ur.user_id) JOIN role on role_id = role.id) where username=?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() throws NoSuchAlgorithmException {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl r = new RoleHierarchyImpl();
        r.setHierarchy("MSWrite > MSRead > MSOther");
        return r;
    }

    @Bean
    public HttpTraceRepository httpTraceRepository() {
        return new InMemoryHttpTraceRepository();
    }


}
