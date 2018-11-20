package com.sami.demo.sec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by mohamed-sami-mejri on 19/11/18.
 */
public class JwtAuthorizationFilter extends OncePerRequestFilter{
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
//pr accepter tous les domains
        response.addHeader("Access-Control-Allow-Origin", "*");
        //pr autoriser les entites
        // accept, access-control-allow-origin, content-type
        response.addHeader("Access-Control-Allow-Headers",
                "Origin,Accept,X-Requested-With,Content-Type,"
                        + "Access-Control-Request-Method,"
                        + "Access-Control-Request-Headers,"
                        + "Authorization");
        //response.addHeader("Access-Control-Allow-Methods", "PUT, POST, GET, OPTIONS, DELETE");

        //pr exposer les entites
        response.addHeader("Access-Control-Expose-Headers",
                "Access-Control-Allow-Origin,"
                        + "Access-Control-Allow-Credentials,Authorization");
        //response.addHeader("Access-Control-Allow-Credentials","true");
        //si la request est envoye avec OPTIONS on  retourne ok
        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else{
            // recuperer token
            //verifier token
            //signer token avec le secret

            String jwt = request.getHeader(SecurityConstantes.HEADER_STRING);
        System.out.println(jwt);

        if (jwt == null || !jwt.startsWith(SecurityConstantes.TOKEN_PRIFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        Claims claims = Jwts.parser().setSigningKey(SecurityConstantes.SECRET)
                .parseClaimsJws(jwt.replace(SecurityConstantes.TOKEN_PRIFIX, "")).getBody();
        String username = claims.getSubject();
        ArrayList<Map<String,String>> roles = (ArrayList<Map<String, String>>) claims.get("roles");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.get("authority")));
        });
        UsernamePasswordAuthenticationToken authenticatedUser = new UsernamePasswordAuthenticationToken(username, null,
                authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        filterChain.doFilter(request, response);
    }
    }
}
