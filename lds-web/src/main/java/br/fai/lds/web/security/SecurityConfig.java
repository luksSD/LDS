package br.fai.lds.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.fai.lds.web.security.provider.LdsAuthenticationProvider;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LdsAuthenticationProvider authenticationProvider;

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {

		// sugestao de uso bcrypt*
		auth.authenticationProvider(authenticationProvider);
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		http
				.authorizeRequests()
					.antMatchers("/resources/**").permitAll()
					.antMatchers("/").permitAll()
					.antMatchers("/account/register-page").permitAll()
					.antMatchers("/account/create-user").permitAll()
					.antMatchers("/account/password-recover").permitAll()
					.antMatchers("/user/detalhes/**").hasRole("USUARIO")
					.antMatchers("/user/editar/**").hasRole("ADMINISTRADOR")
				.anyRequest().authenticated()
				.and()
				.formLogin()
					.loginPage("/account/login-page")
					.loginProcessingUrl("/login").permitAll()
					.defaultSuccessUrl("/")
				.and()
				.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").and()
					.exceptionHandling().accessDeniedPage("/not-found");
	}

}
