package huy.java.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.google.gson.Gson;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.Password;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/authServlet")
public class AuthServlet extends HttpServlet {

	private Gson gson;
	
	private String keyString = "mrufzDPgBTA6IFx5nPnOEfBqlre8I5PX87ckbFt+OE93z3iEbW+h09tP1rtdpNqur4aVOY6mgck6Gmr5fci3nA==";
	
	public void init() {
		gson = new Gson();
	}

	private void sendAsJSON(HttpServletResponse response, Object obj) throws ServletException, IOException {
		response.setContentType("application/json");
		String result = gson.toJson(obj);
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		RequestDispatcher reqDis;
		if(userName.equals("huy") && password.equals("password")) {
			// SecretKey key = Jwts.SIG.HS512.key().build();
			// String secretString = Encoders.BASE64.encode(key.getEncoded());
			SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(keyString));
			// System.out.println(secretString);
			String jwt = Jwts.builder().subject("securityToken").claim("userName", userName).signWith(key).compact();
			response.addCookie(new Cookie("token", jwt));
			sendAsJSON(response, jwt);  
		} else {
			reqDis = request.getRequestDispatcher("login.jsp");
			reqDis.include(request, response);
		}
	}
}
