package shop.company.entity;

// Generated 2010-11-18 23:02:35 by Hibernate Tools 3.3.0.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * AuthUserShortcutmenu generated by hbm2java
 */
@Entity
@Table(name = "auth_user_shortcutmenu")
public class AuthUserShortcutmenu implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer shortMenuId;
	private AuthPurview menu;
	private AuthUser user;

	public AuthUserShortcutmenu() {
	}

	public AuthUserShortcutmenu(Integer shortMenuId) {
		this.shortMenuId = shortMenuId;
	}

	@Id
	@Column(name = "short_menu_id", nullable = false)
	public int getShortMenuId() {
		return this.shortMenuId;
	}

	public void setShortMenuId(int shortMenuId) {
		this.shortMenuId = shortMenuId;
	}

	@ManyToOne(fetch=FetchType.EAGER,targetEntity=AuthPurview.class)
	@JoinColumn(name = "menu_id")
	public AuthPurview getMenu() {
		return this.menu;
	}

	@ManyToOne(fetch=FetchType.EAGER,targetEntity=AuthUser.class)
	@JoinColumn(name = "user_id")
	public AuthUser getUser() {
		return user;
	}

	public void setShortMenuId(Integer shortMenuId) {
		this.shortMenuId = shortMenuId;
	}

	public void setUser(AuthUser user) {
		this.user = user;
	}

	public void setMenu(AuthPurview menu) {
		this.menu = menu;
	}

}
