package com.example.ts.model;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class MasterModel {
	
	@NotBlank(message = "メールは必須です") 
	@Email
	private String email;
	
	@NotBlank(message = "パスワードは必須です") 
	private String password;
	@NotBlank(message = "名前は必須です") 
	private String userName;
	
	@NotBlank(message = "年齢は必須です") 
	@Min(value = 0, message = "0歳以上")
	@Max(value = 130, message = "130歳以下")
	private String userAge;
	

}
