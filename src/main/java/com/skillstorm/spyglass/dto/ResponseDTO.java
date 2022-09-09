package com.skillstorm.spyglass.dto;


public class ResponseDTO {
    private String sessionId;
    private String username;
    private int id;
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}