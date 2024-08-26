package com.human.gallery.domain.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsersLoginForm {
	@NotBlank
	String id;
	@NotBlank
	String password;
}
