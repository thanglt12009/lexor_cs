package com.lexor.cs.resource;

import com.lexor.cs.domain.ApiUser;
import com.lexor.cs.domain.Token;
import com.lexor.cs.domain.User;
import com.lexor.cs.service.BaseUserServiceInterface;
import com.lexor.cs.service.UserService;
import com.lexor.cs.util.JWTReader;
import java.sql.SQLException;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

@Stateless
@Path("/user")
public class AuthFacadeREST extends UserAbstractFacade<ApiUser> {
    
    private UserService service;

    public AuthFacadeREST() {
        super(ApiUser.class);
        this.service = new UserService();
    }

    @Override
    protected BaseUserServiceInterface getService() {
        return this.service;
    }

    @POST
    @Path("/login")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public Token login(User user) throws SQLException, Exception {
        if(user.getUsername() != null && user.getPassword() != null && service.count(user.getUsername(), user.getPassword()) > 0) {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(JWTReader.getKey()));

            return new Token(
               Jwts.builder().setSubject(user.getUsername()).setPayload(user.getPassword()).signWith(key, SignatureAlgorithm.RS512).compact()
            );
        }
        
        throw new Exception("User not found");
    }
}
