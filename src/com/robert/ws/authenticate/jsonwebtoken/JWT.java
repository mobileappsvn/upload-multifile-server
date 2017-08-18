package com.robert.ws.authenticate.jsonwebtoken;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.robert.utils.Encrypter;
import com.robert.utils.security.EncryptionException;
import com.stormpath.sdk.api.ApiKey;
import com.stormpath.sdk.api.ApiKeys;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWT {
	/**
	 * @author Robert
	 * We need one database consist the tables:
	 * account(id, phone, password, email, first_name, last_name); 
	 * device(device_id, token, app_version, device_info, active_status, time_joined, last_logins, last_position...); 
	 * user_logs(device_id, account_phone, login_time, logout_time, ip_address, jwt);
	 * 
	 * 
	 */
    
    public static void main(String[] args) {
    	
    	try {
    		/*String privateKeyString = 
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
    		
    		System.out.println("==>" + generateToken(Encrypter.encrypt("+84989664386"), Encrypter.encrypt("robert@1983"), "adr_352238062784640"));
			System.out.println("==>" + generateToken(new User(1983, "robert", "developer")));
			*/
    		String access_token = generateToken(Encrypter.encrypt("+84989664386"), Encrypter.encrypt("robert@1983"), "adr_352238062784640");
			
    		System.out.println("==>" + access_token);
    		System.out.println(parseToken(access_token));
		} catch (Exception e) {
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
            
            u.setDeviceId((String) body.get("dvi"));
            u.setPassword(Encrypter.decrypt((String) body.get("pmd")));
            u.setPhone(Encrypter.decrypt((String) body.get("iss")));

            return u;

        } catch (EncryptionException enc) {
        	enc.printStackTrace();
        } catch (ClassCastException e) { 
        	e.printStackTrace();
        } catch (Exception e) { 
        	e.printStackTrace();
        }
        return null;
    }
    
    public static String generateToken(String phoneEncrypt, String passwordEncrypt, String deviceId) {
        Claims claims = Jwts.claims().setSubject(phoneEncrypt);
        claims.put("pmd", passwordEncrypt);
        claims.put("dvi", deviceId);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer(phoneEncrypt)
                .signWith(SignatureAlgorithm.HS512, secret)
                .setHeaderParam("typ", "JWT")
                .compact();
    }
    
    public static String generateToken(String phoneEncrypt, String passwordEncrypt, String deviceId, String appIdentifierEncrypt, String remoteAddressIPEncrypt) {
        Claims claims = Jwts.claims().setSubject(phoneEncrypt);
        claims.put("pmd", passwordEncrypt);
        claims.put("dvi", deviceId);
        claims.put("app_id", appIdentifierEncrypt);
        claims.put("ip", remoteAddressIPEncrypt);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer(phoneEncrypt)
                .signWith(SignatureAlgorithm.HS512, secret)
                .setHeaderParam("typ", "JWT")
                .compact();
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
        claims.put("user_id", u.getId() + "");
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
	
	//----
	//Sample method to construct a JWT
	private String createExpirationJWT(String id, String issuer, String subject, long ttlMillis) {
	 
	    //The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	 
	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);
	 
	    //We will sign our JWT with our ApiKey secret
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret/*apiKey.getSecret()*/);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	 
	    //Let's set the JWT Claims
	    JwtBuilder builder = Jwts.builder().setId(id)
	                                .setIssuedAt(now)
	                                .setSubject(subject)
	                                .setIssuer(issuer)
	                                .signWith(signatureAlgorithm, signingKey);
	 
	    //if it has been specified, let's add the expiration
	    if (ttlMillis >= 0) {
	    long expMillis = nowMillis + ttlMillis;
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }
	 
	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return builder.compact();
	}
}
