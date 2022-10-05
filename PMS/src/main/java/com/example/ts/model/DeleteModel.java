package com.example.ts.model;

import lombok.Data;

@Data
public class DeleteModel {
	private int id;
	private String website;
	private String account;
	private String password;
	private String encrypted;
}
