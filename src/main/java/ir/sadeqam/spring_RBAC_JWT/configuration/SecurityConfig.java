package ir.sadeqam.spring_RBAC_JWT.configuration;

import ir.sadeqam.spring_RBAC_JWT.controller.customization.security.JwtAuthenticationFilter;
import ir.sadeqam.spring_RBAC_JWT.controller.customization.security.MyAccessDeniedHandler;
import ir.sadeqam.spring_RBAC_JWT.controller.customization.security.MyAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;
    @Autowired
    private MyAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {

        security.logout(AbstractHttpConfigurer::disable);
        security.csrf(AbstractHttpConfigurer::disable);
        security.sessionManagement(AbstractHttpConfigurer::disable);
        security.requestCache(AbstractHttpConfigurer::disable);

        security.addFilterAfter(jwtAuthenticationFilter, ExceptionTranslationFilter.class);
        security.authorizeHttpRequests(matcherRegistry -> {
            matcherRegistry.requestMatchers("api/books").authenticated();
            matcherRegistry.requestMatchers("api/books/*").authenticated();
            matcherRegistry.anyRequest().permitAll();
        });

        security.exceptionHandling(exceptionHandlingConfigurer -> {
            exceptionHandlingConfigurer.authenticationEntryPoint(authenticationEntryPoint);
            exceptionHandlingConfigurer.accessDeniedHandler(myAccessDeniedHandler);
        });


        return security.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return new ProviderManager(daoAuthenticationProvider);
    }

    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> disableFilterAutoRegistration(JwtAuthenticationFilter filter) {
        var registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
