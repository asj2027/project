package com.human.gallery.domain.mypage;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Form {
    @NotBlank
    String password;
}
