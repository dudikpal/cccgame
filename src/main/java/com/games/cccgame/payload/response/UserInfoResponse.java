package com.games.cccgame.payload.response;

import java.util.List;

public class UserInfoResponse {
	private String userId;
	private String username;
	private String email;
	private List<String> roles;
    private String garageId;
	private String jwt;

	public UserInfoResponse(String userId,
                          String username,
                          String email,
                          List<String> roles,
                          String garageId,
                          String jwt) {
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.roles = roles;
        this.garageId = garageId;
		this.jwt = jwt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

    public String getGarageId() {
        return garageId;
    }

    public String getJwt() { return jwt; }
}
