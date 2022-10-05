package com.example.ts.service;

import org.springframework.stereotype.Service;

@Service
public class MyService {
	public String encryption(String s) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int n = (int)c + 1;
			sb.append((char)n);
		}
		return sb.toString();
	}
	
	public String decryption(String a) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < a.length(); i++) {
			char c = a.charAt(i);
			int n = c - 1;
			sb.append((char)n);
		}
		return sb.toString();
	}
}
