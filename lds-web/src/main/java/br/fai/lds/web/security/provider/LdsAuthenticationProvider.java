package br.fai.lds.web.security.provider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.fai.lds.model.Usuario;
import br.fai.lds.web.security.CustomUserDetails;
import br.fai.lds.web.service.UserService;

@Component
public class LdsAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserService userService;

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

		final String username = authentication.getName();
		final String password = authentication.getCredentials().toString();

		System.out.println("Username: " + username + "  Password: " + password);

		final Usuario user = userService.validateUserNameAndPassword(username, password);

		if (user == null) {
			return null;
		}

		final List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
		grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_" + user.getTipo()));

		final UserDetails principal = new CustomUserDetails(username, password, grantedAuthorityList, user);

		return new UsernamePasswordAuthenticationToken(principal, password, grantedAuthorityList);
	}

	@Override
	public boolean supports(final Class<?> authentication) {

		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	public Usuario getAuthenticatedUser() {

		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		final CustomUserDetails userDatails = (CustomUserDetails) authentication.getPrincipal();

		return userDatails.getUsuario();

	}

}
