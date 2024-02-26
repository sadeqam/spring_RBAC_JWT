package ir.sadeqam.spring_RBAC_JWT.controller.customization.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtHandler jwtHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        doBefore(req, res);
        chain.doFilter(req, res);
    }

    public void doBefore(HttpServletRequest request, HttpServletResponse response) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))
            return;
        String token = authorizationHeader.substring("Bearer".length()).trim();
        try {
            var decoded = jwtHandler.verify(token);
            var auth = UsernamePasswordAuthenticationToken.authenticated(
                    decoded.getSubject(),
                    null,
                    AuthorityUtils.createAuthorityList(decoded.getClaim("authorities").asList(String.class))
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (JWTVerificationException jwtVerificationException) {
            // wrap JWTVerificationException in subclass of AuthenticationException
            // for use ExceptionTranslationFilter and AuthenticationEntryPoint to
            // handling this kind of exception in global Exception Handler class (controller.customization)
            throw new TokenVerificationException(jwtVerificationException.getMessage());
        }
    }

}
