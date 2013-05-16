package org.fyleserver.db.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "friend")
public class Friend {

	private long friendId;
	private String username;
	private User user;
	private short accepted;
	
	public Friend(){}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FRIEND_ID", unique = true, nullable = false)
	public long getFriendId() {
		return friendId;
	}

	public void setFriendId(long friendId) {
		this.friendId = friendId;
	}
	
	@Column(name = "FRIEND_USERNAME", unique = true, nullable = false, length = 16)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "IS_FRIEND_ACCEPTED", nullable = true, precision = 1)
	public short getAccepted() {
		return accepted;
	}

	public void setAccepted(short accepted) {
		this.accepted = accepted;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
