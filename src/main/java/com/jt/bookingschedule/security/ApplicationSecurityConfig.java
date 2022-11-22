package com.jt.bookingschedule.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig {

    private PasswordEncoder passwordEncoder;
    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
               .csrf().disable()
                .authorizeHttpRequests(
                        authorizationManagerRequestMatcherRegistry ->
                                authorizationManagerRequestMatcherRegistry
                                        .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                                        .antMatchers(HttpMethod.POST,"/management/api/**")
                                            .hasAuthority(ApplicationUserPermission.CLIENT_WRITE.name())
                                        .antMatchers(HttpMethod.PUT,"/management/api/**")
                                            .hasAuthority(ApplicationUserPermission.CLIENT_WRITE.name())
                                        .antMatchers(HttpMethod.DELETE,"/management/api/**")
                                            .hasAuthority(ApplicationUserPermission.CLIENT_WRITE.name())
                                        . antMatchers("/management/api/**").hasAnyRole(ApplicationUserRole.ADMIN.name())

                                        .anyRequest()
                                        .authenticated())
                .httpBasic();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails alinaTolkaAdmin =
                User.builder()
                        .username("alina")
                        .password(passwordEncoder.encode("12345"))
                        .roles(ApplicationUserRole.ADMIN.name()) //ROLE_ADMIN
                        .build();

        return new InMemoryUserDetailsManager(
                alinaTolkaAdmin
        );
    }
}
