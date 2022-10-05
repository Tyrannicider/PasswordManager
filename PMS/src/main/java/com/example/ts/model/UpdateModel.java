package com.example.ts.model;

import lombok.Data;

@Data
public class UpdateModel {
	private int id;
	private String website;
	private String account;
	private String oldPassword;
	private String newPassword;
	private String encrypted;
	private String newlyEncrypted;
}
