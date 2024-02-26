package ir.sadeqam.spring_RBAC_JWT.controller;

import ir.sadeqam.spring_RBAC_JWT.controller.customization.security.JwtHandler;
import ir.sadeqam.spring_RBAC_JWT.controller.customization.security.UserAuthenticationInfo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(
        path = "api/auth",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHandler jwtHandler;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> login(@Valid @RequestBody UserAuthenticationInfo authenticationInfo) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                authenticationInfo.getUsername(),
                authenticationInfo.getPassword()
        );
        var auth = authenticationManager.authenticate(authenticationToken);
        var authoritiesCollection = auth.getAuthorities().stream().toList();
        var authoritiesArray = new String[authoritiesCollection.size()];
        for (byte i = 0; i < authoritiesCollection.size(); i++) {
            authoritiesArray[i] = authoritiesCollection.get(i).getAuthority();
        }

        var response = new HashMap<String, String>();
        response.put("token", jwtHandler.generate(auth.getName(), authoritiesArray));
        return response;
    }

}
