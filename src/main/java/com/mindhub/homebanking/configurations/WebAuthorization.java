package com.mindhub.homebanking.configurations;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity
@Configuration
public class WebAuthorization extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2-console/**").hasAuthority("ADMIN")
                .antMatchers("/rest/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/clients").hasAuthority("ADMIN")
                .antMatchers("/web/create_loan.html").hasAuthority("ADMIN")
                .antMatchers("clients/current/account").hasAuthority("CLIENT")
                .antMatchers("clients/current/create_cards").hasAuthority("CLIENT")
                .antMatchers("/web/account.html").hasAuthority("CLIENT")
                .antMatchers("/web/transactions.html").hasAuthority("CLIENT")
                .antMatchers("/web/cards.html").hasAuthority("CLIENT")
                .antMatchers("/web/create_cards.html").hasAuthority("CLIENT")
                .antMatchers("/web/transfers.html").hasAuthority("CLIENT")
                .antMatchers("/web/loan-application.html").hasAuthority("CLIENT")
                .antMatchers(HttpMethod.POST,"/api/clients/current/account").hasAuthority("CLIENT")
                .antMatchers(HttpMethod.POST,"/api/transactions").hasAuthority("CLIENT")
                //.antMatchers(HttpMethod.POST,"/api/loans").hasAuthority("CLIENT")
                .antMatchers(HttpMethod.POST,"/api/clients").permitAll()
                .antMatchers("/web/index.html").permitAll()
                .antMatchers("/web/products").permitAll()
                .antMatchers("/web/branchs").permitAll()
                .antMatchers("/web/callus").permitAll()
                .antMatchers("/web/login").permitAll()
                .antMatchers("/web/password_recovery").permitAll()
                .antMatchers("/web/signup").permitAll();

        http.formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage("/api/login");
        http.logout().logoutUrl("/api/logout").deleteCookies("JSESSIONID");

        http.csrf().disable();

        http.headers().frameOptions().disable();

        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
    }
    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}