package hu.gyuuu.hrmanager.security.jwt;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import hu.gyuuu.hrmanager.configuration.JWTConfiguration;
import hu.gyuuu.hrmanager.security.AuthenticationDetails;

public class JWTFactory {
    private static final int NOT_BEFORE_MINS                   = 2;
    private static final int TOKEN_VALIDITY_DURATION           = Integer.MAX_VALUE;
    private static final int ALLOWED_CLOCK_SKEW                = 5 * 60;

    private static final String KEY_AUTHORITIES = "authorities";
    private static final String KEY_COMPANY_ID  = "company_id";
    private static final String KEY_NAME        = "name";
    private static final Log    LOG             = LogFactory.getLog(JWTFactory.class);

    private final String issuer;
    private final Key    key;

    @Autowired
    public JWTFactory(JWTConfiguration jwtConfig) {
        super();
        this.issuer = jwtConfig.getIssuer();
        this.key = new SecretKeySpec(jwtConfig.getSecret().getBytes(), AlgorithmIdentifiers.HMAC_SHA512);
    }

    public String generateToken(AuthenticationDetails authDetails, Collection<? extends GrantedAuthority> authorities) {
        JwtClaims claims = new JwtClaims();
        claims.setIssuer(issuer);
        claims.setExpirationTimeMinutesInTheFuture( TOKEN_VALIDITY_DURATION);
        claims.setGeneratedJwtId();
        claims.setIssuedAtToNow();
        claims.setNotBeforeMinutesInThePast(NOT_BEFORE_MINS);
        claims.setSubject("" + authDetails.getUserId());

        String astrAuthorities[] = new String[authorities.size()];
        int i = 0;
        for (GrantedAuthority authority : authorities) {
            astrAuthorities[i++] = authority.getAuthority();
        }

        claims.setStringListClaim(KEY_AUTHORITIES, astrAuthorities);
        claims.setStringClaim(KEY_NAME, authDetails.getName());
        claims.setClaim(KEY_COMPANY_ID, authDetails.getCompanyId());

        JsonWebSignature jws = new JsonWebSignature();

        jws.setPayload(claims.toJson());

        jws.setKey(key);

        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA512);

        String jwt;
        try {
            jwt = jws.getCompactSerialization();
            return jwt;
        } catch (JoseException e) {
            LOG.error(e.getMessage(), e);
            throw new InternalAuthenticationServiceException("Eror occured in JWT subsystem", e);
        }
    }

    public JWTAuthenticationToken validateToken(String token) throws AuthenticationException {
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime().setRequireSubject()
                .setExpectedIssuer(issuer) 
                .setVerificationKey(key) 
                .setAllowedClockSkewInSeconds(ALLOWED_CLOCK_SKEW).build();
        try {
            JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
            String strUserId = jwtClaims.getSubject();
            Long userId = Long.parseLong(strUserId);
            List<String> lStrAuthorities = jwtClaims.getStringListClaimValue(KEY_AUTHORITIES);
            String name = jwtClaims.getStringClaimValue(KEY_NAME);
            Long companyId = jwtClaims.getClaimValue(KEY_COMPANY_ID, Long.class);
            List<GrantedAuthority> authorites = new ArrayList<>();
            for (String strAutority : lStrAuthorities) {
                authorites.add(new SimpleGrantedAuthority(strAutority));
            }
            JWTAuthenticationToken auth = new JWTAuthenticationToken(userId, token, authorites, new AuthenticationDetails(userId, name, companyId));
            return auth;
        } catch (InvalidJwtException | MalformedClaimException e) {
            LOG.error(e.getMessage(), e);
            throw new BadCredentialsException("Invalid Json Web Token");
        }
    }

}
