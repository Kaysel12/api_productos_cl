//package com.api.productos.security;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/api/auth/**").permitAll()  // Permitir rutas de autenticación sin protección
//                .anyRequest().authenticated();  // Proteger todas las demás rutas
//        // Registrar el filtro para JWT
//        http.addFilter(new JwtAuthenticationFilter());
//    }
//}