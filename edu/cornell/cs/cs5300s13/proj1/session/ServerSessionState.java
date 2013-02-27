package edu.cornell.cs.cs5300s13.proj1.session;

import java.util.Date;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class ServerSessionState extends ClientSessionState{
	private Date expiration;
	private String message = "";
	private String cookieName = "CS5300S13PROJ1SESSION";
	private static long DEFAULT_SHELF_LIFE = 30000;
	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public void updateExpiration() {
		this.setExpiration(new Date(new Date().getTime() + DEFAULT_SHELF_LIFE));
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		this.incrementVersion();
	}

	public ServerSessionState(int seesionID) {
		this.setSessionID(seesionID);
		this.setVersion(0);
		this.setPrimaryLocation(0);
		this.setSecondaryLocation(1);
		this.setMessage("Welcome, New User! (<- Replace your message here)");
		updateExpiration();
	};
	
	public void setClientSession(HttpServletResponse response) {
		response.addCookie(new Cookie(cookieName, super.generateCookie()));
	}
}
