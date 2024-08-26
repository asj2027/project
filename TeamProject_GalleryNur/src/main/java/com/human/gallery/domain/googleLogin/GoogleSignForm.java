package com.human.gallery.domain.googleLogin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class GoogleSignForm {

    @NotBlank
    private String id;
    @NotBlank
    @Pattern(regexp="^[ㄱ-ㅎ가-힣]*$")
    @Size(max = 20)
    private String name;
    @NotBlank
    private String address;

    @NotBlank
    private String dtaddress;
    @NotBlank
    private String refAddress;
    @NotBlank
    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
    private String mobile;
    @NotNull
    private int postcode;
}
