package org.fyleserver.db.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.OneToMany;

@Entity
@Table(name = "user")
public class User {
	
	private long id;
	private String username;
	private String email;
	private short connected;
	private String registrationDate;
	private String password;
	private String passwordhex;
	private Set<Friend> friends = new HashSet<>(0);
	private UserSecurity userSecurity;
	
	public User () {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID", unique = true, nullable = false)
	public long getId(){
		return this.id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	@Column(name = "REG_DATE", nullable = false, length = 30)
	public String getRegistrationDate() {
		return registrationDate;
	}
	
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	@Column(name = "PASSWORD", nullable = false, length = 128)
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "PASSWORDHEX", nullable = false, length = 32)
	public String getPasswordhex() {
		return passwordhex;
	}
	
	public void setPasswordhex(String passwordhex){
		this.passwordhex = passwordhex;
	}
	
	@Column(name = "EMAIL", nullable = false, unique = true, length = 254)
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	@Column(name = "USERNAME", nullable = false, unique = true, length = 16)
	public String getUsername() {
		return this.username;
	}
 
	public void setUsername(String username) {
		this.username = username;
	}
 
	@Column(name = "IS_CONNECTED", nullable = true, precision = 1)
	public short isConnected(){
		return this.connected;
	}
	
	public void setConnected(short connected){
		this.connected = connected;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Friend> getFriends(){
		return friends;
	}
	
	public void setFriends(Set<Friend> friends){
		this.friends = friends;
	}
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	public UserSecurity getUserSecurity() {
		return userSecurity;
	}

	public void setUserSecurity(UserSecurity userSecurity) {
		this.userSecurity = userSecurity;
	}
}
