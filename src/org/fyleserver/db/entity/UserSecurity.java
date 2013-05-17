package org.fyleserver.db.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Column;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table (name = "usersecurity")
public class UserSecurity {
	
	private long userId;
	private long failedLoginNum;
	private long lockout;
	private Long loginTime;
	private Long lastLoginTime;
	private User user;
	
	public UserSecurity(){}
	
	@GenericGenerator(name = "generator", strategy = "foreign", 
			parameters = @Parameter(name = "property", value = "user"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "USER_ID", unique = true, nullable = false)
	public long getUserId(){
		return this.userId;
	}
	
	public void setUserId(long userId){
		this.userId = userId;
	}
	
	@Column(name = "FAILED_LOGIN_NUM", nullable = true)
	public long getFailedLoginNum() {
		return failedLoginNum;
	}
	public void setFailedLoginNum(long failedLoginNum) {
		this.failedLoginNum = failedLoginNum;
	}
	
	@Column(name = "LOCKOUT", nullable = true)
	public long getLockout() {
		return lockout;
	}

	public void setLockout(long lockout) {
		this.lockout = lockout;
	}
	
	@Column(name = "LOGIN_TIME", nullable = true)
	public Long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Long loginTime) {
		this.loginTime = loginTime;
	}
	
	@Column(name = "LAST_LOGIN_TIME", nullable = true)
	public Long getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Long lastLoginTim) {
		this.lastLoginTime = lastLoginTim;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
