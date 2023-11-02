package huy.java.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/users/*")
public class UserFilter implements Filter {

	private String keyString = "prufzDPgBTA6IFx5nPnOEfBqlre8I5PX87ckbFt+OE93z3iEbW+h09tP1rtdpNqur4aVOY6mgck6Gmr5fci3nA==";

	public UserFilter() {

	}

	@Override
	public void init(FilterConfig fconfig) {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse) response;
		String token = "";
		Cookie[] cookies = httpReq.getCookies();
		if (cookies == null || cookies.length == 0) {
			httpRes.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("token")) {
				token = cookies[i].getValue();
			}
		}
		if (token.equals("") || token == null) {
			httpRes.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		Jws<Claims> jws;
		SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(keyString));
		jws = Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
		
		String userName = (String) jws.getPayload().get("userName");
		
		if(userName == null || userName.equals("")) {
			httpRes.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		httpReq.setAttribute("userName", userName);
		
	
		chain.doFilter(request, response);
	}

}
