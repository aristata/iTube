package com.itube.domain;

public class UserVO {
	private String uid;
	private String id;
	private String pwd;
	private String email;
	private String nickname;
	private int grade;
	private boolean emailConfirm;

	public boolean isEmailConfirm() {
		return emailConfirm;
	}

	public void setEmailConfirm(boolean emailConfirm) {
		this.emailConfirm = emailConfirm;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "UserVO [uid=" + uid + ", id=" + id + ", pwd=" + pwd + ", nickname=" + nickname + ", email=" + email + ", grade=" + grade + "]";
	}

}
