package com.imooc.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.security.SocialUserDetails;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author steve
 * @since 2018-01-03
 */
public class Users extends Model<Users>{

    private static final long serialVersionUID = 1L;

	@TableId(value="user_id", type= IdType.AUTO)
	private Long userId;
	private String username;
	private String password;
	private Integer enabled;


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	@Override
	protected Serializable pkVal() {
		return this.userId;
	}

	@Override
	public String toString() {
		return "Users{" +
			", userId=" + userId +
			", username=" + username +
			", password=" + password +
			", enabled=" + enabled +
			"}";
	}
}
