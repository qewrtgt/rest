package com.spring.rest.security;

import com.spring.rest.security.handler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);

        /*pass = 1234*/
//        String pass = "$2a$12$Z5SWWnir8gCltkVXVM1iLebnjlFpz8ROq4xwrtnn1zzj0jQ7ef1IG";
//        auth.inMemoryAuthentication().withUser("admin").password(pass).roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("user").password(pass).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                // указываем страницу с формой логина
                .loginPage("/login")
                //указываем логику обработки при логине
                .successHandler(new LoginSuccessHandler())
                // указываем action с формы логина
                .loginProcessingUrl("/login")
                // Указываем параметры логина и пароля с формы логина
                .usernameParameter("j_email")
                .passwordParameter("j_password")
                // даем доступ к форме логина всем
                .permitAll();

        http.logout()
                // разрешаем делать логаут всем
                .permitAll()
                // указываем URL логаута
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // указываем URL при удачном логауте
                .logoutSuccessUrl("/login?logout")
                //выклчаем кроссдоменную секьюрность (на этапе обучения неважна)
                .and().csrf().disable();


        http

                // делаем страницу регистрации недоступной для авторизированных пользователей
                .authorizeRequests()
                //страницы аутентификаци доступна всем
//                .antMatchers("/api/**").permitAll()
//                .antMatchers("/**").permitAll()
                .antMatchers("/login").anonymous()
                // защищенные URL
//                .antMatchers("/api/**").access("hasAuthority('ADMIN')")
//                 .antMatchers("/admin/**").access("hasAuthority('ADMIN')")
                .antMatchers("/**").access("hasAuthority('USER') or hasAuthority('ADMIN')")
                .anyRequest().authenticated();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
//                 .allowedOrigins("http://localhost:63342")
                .allowedMethods("*");
    }


}
