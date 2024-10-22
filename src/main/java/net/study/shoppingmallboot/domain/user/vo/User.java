package net.study.shoppingmallboot.domain.user.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class User {

	private String userId;
	private String userName;
	private String password;
	private String role;
	private String ssn;
	private String phone;
	private String phone1;
	private String phone2;
	private String phone3;
	private String addr;
	private String email;
	private Date regDate;
	private int rowNum;

	public User(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}
	
	public String getPhone1() {
		if (Objects.nonNull(this.phone))
 			this.phone1 = this.phone.split("-")[0];
		return phone1;

	}

	public String getPhone2() {
		if (Objects.nonNull(this.phone))
			this.phone2 = this.phone.split("-")[1];
		return phone2;
	}

	public String getPhone3() {
		if (Objects.nonNull(this.phone))
			this.phone3 = this.phone.split("-")[2];
		return phone3;
	}

}
