package com.robert.ws.authenticate.jsonwebtoken.test;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.robert.utils.Encrypter;
import com.robert.utils.security.EncryptionException;
import com.robert.ws.authenticate.jsonwebtoken.User;
import com.stormpath.sdk.api.ApiKey;
import com.stormpath.sdk.api.ApiKeys;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import oracle.security.restsec.jwt.JwtException;
import oracle.security.restsec.jwt.JwtToken;
import oracle.security.restsec.jwt.SigningException;

public class JWTTest {
	/**
	 * @author robet
	 * We need one database consist users; devices; login_session tables
	 * 
	 * 
	 SELECT A.*,(CASE (SELECT count(DetailA.id) FROM DetailA WHERE DetailA.id=A.id and status !=1) WHEN >0 THEN 0 ELSE 1 END) as status 
	 FROM A
	 GROUP BY A.id;
	 * 
	 */
    
    public static void main(String[] args) {
    	
    	/*Key key = MacProvider.generateKey();

    	String compactJws = Jwts.builder()
    	  .setSubject("Joe")
    	  .signWith(SignatureAlgorithm.HS512, key)
    	  .compact();
    	
    	System.out.println(compactJws);*/
    	
    	try {
    		String privateKeyString = 
    	            "-----BEGIN PRIVATE KEY-----\n"
    	                    + "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAM7t8Ub1DP+B91NJ\n"
    	                    + "nC45zqIvd1QXkQ5Ac1EJl8mUglWFzUyFbhjSuF4mEjrcecwERfRummASbLoyeMXl\n"
    	                    + "eiPg7jvSaz2szpuV+afoUo9c1T+ORNUzq31NvM7IW6+4KhtttwbMq4wbbPpBfVXA\n"
    	                    + "IAhvnLnCp/VyY/npkkjAid4c7RoVAgMBAAECgYBcCuy6kj+g20+G5YQp756g95oN\n"
    	                    + "dpoYC8T/c9PnXz6GCgkik2tAcWJ+xlJviihG/lObgSL7vtZMEC02YXdtxBxTBNmd\n"
    	                    + "upkruOkL0ElIu4S8CUwD6It8oNnHFGcIhwXUbdpSCr1cx62A0jDcMVgneQ8vv6vB\n"
    	                    + "/YKlj2dD2SBq3aaCYQJBAOvc5NDyfrdMYYTY+jJBaj82JLtQ/6K1vFIwdxM0siRF\n"
    	                    + "UYqSRA7G8A4ga+GobTewgeN6URFwWKvWY8EGb3HTwFkCQQDgmKtjjJlX3BotgnGD\n"
    	                    + "gdxVgvfYG39BL2GnotSwUbjjce/yZBtrbcClfqrrOWWw7lPcX1d0v8o3hJfLF5dT\n"
    	                    + "6NAdAkA8qAQYUCSSUwxJM9u0DOqb8vqjSYNUftQ9dsVIpSai+UitEEx8WGDn4SKd\n"
    	                    + "V8kupy/gJlau22uSVYI148fJSCGRAkBz+GEHFiJX657YwPI8JWHQBcBUJl6fGggi\n"
    	                    + "t0F7ibceOkbbsjU2U4WV7sHyk8Cei3Fh6RkPf7i60gxPIe9RtHVBAkAnPQD+BmND\n"
    	                    + "By8q5f0Kwtxgo2+YkxGDP5bxDV6P1vd2C7U5/XxaN53Kc0G8zu9UlcwhZcQ5BljH\n"
    	                    + "N24cUWZOo+60\n"
    	                    + "-----END PRIVATE KEY-----";
    		byte[] privateKeyBytes = privateKeyString.getBytes();
    		
			JwtToken jwtToken = new JwtToken();
			jwtToken.setAlgorithm(JwtToken.SIGN_ALGORITHM.HS256.toString());
			jwtToken.setType(JwtToken.JWT);
			jwtToken.setExpiryTime(new Date());
			jwtToken.setIssuer("jupitech.co");
			jwtToken.setPrincipal("robert.hoang");
			try {
				jwtToken.setClaimParameter("courier", Encrypter.encrypt("{\"phone\":\"84989664386\", \"password\":\"123456789\"}"));
			} catch (EncryptionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			jwtToken.setHeaderParameter("user-agent","Android-4.1.0-KITKAT");
			
			
			try {
				String jwtString = jwtToken.signAndSerialize(privateKeyBytes);
				System.out.println(jwtString);
				System.out.println("=========================");
				readJWTFromHttp(jwtString);
			} catch (SigningException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*13.4.4 Serializing the JWT Token without Signing
			If the JWT token is not required to be digitally signed, you can serialize the token without signing, as shown in the following example:
			 */
			
			JwtToken jwtTokens = new JwtToken();
			jwtTokens.setType(JwtToken.JWT);
			jwtTokens.setIssuer("mobile.apps.pro.vn");
			jwtTokens.setPrincipal("robert.hoang");
			
			System.out.println(jwtToken.serializeUnsigned());
			
			System.out.println("==>" + generateToken(new User(1983, "robert", "developer")));
			
		} catch (JwtException e) {
			e.printStackTrace();
		} 
    	
	}

 // Read the JWT token as a String from HTTP header
    public static void readJWTFromHttp(String jwtStr) {
    	 
		try {
			JwtToken token = new JwtToken(jwtStr);
			/*if (token == null) {
				System.out.println("Null me may roai");
		    	return;
			}
			// Validate the issued and expiry time stamp.
	    	if (token.getExpiryTime() != null && token.getExpiryTime().after(new Date())) {
		    	System.out.println("Expired");
		    	return;
	    	}*/
	    	 
	    	// Get the issuer from the token
	    	System.out.println("Issuer=" + token.getIssuer());
	    	// Get the principal from the token
	    	System.out.println("Principal=" + token.getPrincipal());
	    	
		} catch (JwtException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private static String secret = "-----BEGIN PRIVATE KEY-----\n"
            + "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAM7t8Ub1DP+B91NJ\n"
            + "nC45zqIvd1QXkQ5Ac1EJl8mUglWFzUyFbhjSuF4mEjrcecwERfRummASbLoyeMXl\n"
            + "eiPg7jvSaz2szpuV+afoUo9c1T+ORNUzq31NvM7IW6+4KhtttwbMq4wbbPpBfVXA\n"
            + "IAhvnLnCp/VyY/npkkjAid4c7RoVAgMBAAECgYBcCuy6kj+g20+G5YQp756g95oN\n"
            + "dpoYC8T/c9PnXz6GCgkik2tAcWJ+xlJviihG/lObgSL7vtZMEC02YXdtxBxTBNmd\n"
            + "upkruOkL0ElIu4S8CUwD6It8oNnHFGcIhwXUbdpSCr1cx62A0jDcMVgneQ8vv6vB\n"
            + "/YKlj2dD2SBq3aaCYQJBAOvc5NDyfrdMYYTY+jJBaj82JLtQ/6K1vFIwdxM0siRF\n"
            + "UYqSRA7G8A4ga+GobTewgeN6URFwWKvWY8EGb3HTwFkCQQDgmKtjjJlX3BotgnGD\n"
            + "gdxVgvfYG39BL2GnotSwUbjjce/yZBtrbcClfqrrOWWw7lPcX1d0v8o3hJfLF5dT\n"
            + "6NAdAkA8qAQYUCSSUwxJM9u0DOqb8vqjSYNUftQ9dsVIpSai+UitEEx8WGDn4SKd\n"
            + "V8kupy/gJlau22uSVYI148fJSCGRAkBz+GEHFiJX657YwPI8JWHQBcBUJl6fGggi\n"
            + "t0F7ibceOkbbsjU2U4WV7sHyk8Cei3Fh6RkPf7i60gxPIe9RtHVBAkAnPQD+BmND\n"
            + "By8q5f0Kwtxgo2+YkxGDP5bxDV6P1vd2C7U5/XxaN53Kc0G8zu9UlcwhZcQ5BljH\n"
            + "N24cUWZOo+60\n"
            + "-----END PRIVATE KEY-----";

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     * 
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is invalid.
     */
    public static User parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            User u = new User();
            u.setUsername(body.getSubject());
            u.setId(Long.parseLong((String) body.get("userId")));
            u.setEmail((String) body.get("email"));

            return u;

        } catch (ClassCastException e) {  
        }
        return null;
    }

    /**
     * Generates a JWT token containing username as subject, and userId and role as additional claims. These properties are taken from the specified
     * User object. Tokens validity is infinite.
     * 
     * @param u the user for which the token will be generated
     * @return the JWT token
     */
    public static String generateToken(User u) {
        Claims claims = Jwts.claims().setSubject(u.getUsername());
        claims.put("userId", u.getId() + "");
        claims.put("email", u.getEmail());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    
    
	// Sample method to construct a JWT
	public String createJWT(String id, String issuer, String subject, long ttlMillis) {
		String path = "resources/.stormpath/apiKey.properties";
	    ApiKey apiKey = ApiKeys.builder().setFileLocation(path).build();
	    
		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		// We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiKey.getSecret());
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
				.signWith(signatureAlgorithm, signingKey);

		// if it has been specified, let's add the expiration
		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		// Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}

	// Sample method to validate and read the JWT
	public void parseJWT(String jwt) {
		String path = "resources/.stormpath/apiKey.properties";
	    ApiKey apiKey = ApiKeys.builder().setFileLocation(path).build();
	    
		// This line will throw an exception if it is not a signed JWS (as
		// expected)
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(apiKey.getSecret()))
				.parseClaimsJws(jwt).getBody();
		System.out.println("ID: " + claims.getId());
		System.out.println("Subject: " + claims.getSubject());
		System.out.println("Issuer: " + claims.getIssuer());
		System.out.println("Expiration: " + claims.getExpiration());
	}
}
