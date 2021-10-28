package br.fai.lds.web.security.provider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class LdsAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

		final String username = authentication.getName();
		final String password = authentication.getCredentials().toString();

		System.out.println("Username: " + username + "  Password: " + password);

		final List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
		grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"));

		return new UsernamePasswordAuthenticationToken(username, password, grantedAuthorityList);
	}

	@Override
	public boolean supports(final Class<?> authentication) {

		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
