package com.example.ts.model;

import lombok.Data;

@Data
public class DecryptModel {

	private String website;
	private String account;
	private String encrypted;
	private String decrypted;
}
