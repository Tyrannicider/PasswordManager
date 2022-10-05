package com.example.ts.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ts.mapper.LoginMapper;
import com.example.ts.model.AccountModel;
import com.example.ts.model.DecryptModel;
import com.example.ts.model.DeleteModel;
import com.example.ts.model.LoginModel;
import com.example.ts.model.MasterModel;
import com.example.ts.model.ShowModel;
import com.example.ts.model.UpdateModel;
import com.example.ts.service.MyService;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
public class MainController {
	
	@Autowired
	private LoginMapper loginMapper;
	
	@Autowired
	private MyService serviceInstance;
	
	
    @GetMapping("login")
    public String login() {
        return "login";
    }
    
    @GetMapping("mainpage")
    public String mainpage() {
        return "mainpage";
    }
    
    @GetMapping("register")
    public String toRegister() { 	
    	return "register";
    }
    
    @GetMapping("add")
    public String toAdd() {
    	return "add";
    }
    
    @GetMapping("delete")
    public String toDelete() {
    	return "delete";
    }
   
    @GetMapping("update")
    public String toUpdate() {
    	return "update";
    }   
    
    @GetMapping("show")
    public String toShow() {
    	return "show";
    }
    
    @GetMapping("decrypt")
    public String toDecrypt() {
    	return "decrypt";
    }
    
    @PostMapping("register")
    public String register(@Valid MasterModel masterModel, BindingResult result, Model model) {
    	log.info("---{}", masterModel);
    	if(result.hasErrors()) {
    		log.info("--{}",result.getAllErrors());
    		
    		List<ObjectError> errors = result.getAllErrors();
    		List<String> errorCollection = new ArrayList<String>();
    	
	    	for (ObjectError error : errors) {
	    		errorCollection.add(error.getDefaultMessage());
	    	}
	    	model.addAttribute("message", errorCollection);
	    	return "register";
    	}
    	
    	int c = loginMapper.checkEmail(masterModel);
    	if (c == 0) {
        	loginMapper.register(masterModel);
        	log.info("---{}", masterModel);
    		return "login";
    	}else {
    		model.addAttribute("emailExists", "このメールは既に存在しています！");
    		return "register";
    	}

    	
    }
    
    @PostMapping("intomainpage")
    public String intomainpage(LoginModel loginModel) {
    	int count = loginMapper.checkLogin(loginModel);
    	if (count == 1) {
    		return "mainpage";
    	} else {
    		return "login";
    	}
    }
    
    @PostMapping("add")
    public String add(AccountModel accountModel, Model model) {
    	int c = loginMapper.checkAccount(accountModel);
    	if (c == 0) {
    		String password = accountModel.getPassword();
    		String encrypted = serviceInstance.encryption(password);
        	accountModel.setEncrypted(encrypted);
        	loginMapper.addnew(accountModel);
        	return "mainpage";
        
    	}else {
    		model.addAttribute("accountExists", "このアカウントは既に存在します!");
    		return "add";
    	}
    }
    
    @PostMapping("delete")
    public String delete(DeleteModel deleteModel, Model model) {
    	int c = loginMapper.checkDelete(deleteModel);
    	if (c == 0) {
    		model.addAttribute("wrongInfo", "入力した情報が間違ったので削除できません");
    		return "delete";
    	}else {
        	loginMapper.delete(deleteModel);
    		return "mainpage";
    	}
    }
    
    @PostMapping("update")
    public String update(UpdateModel updateModel, Model model) {

    	String encrypted = serviceInstance.encryption(updateModel.getOldPassword());
    	updateModel.setEncrypted(encrypted);
    	String newlyEncrypted = serviceInstance.encryption(updateModel.getNewPassword());
    	updateModel.setNewlyEncrypted(newlyEncrypted);
       	log.info("---{}", updateModel);
    	int c = loginMapper.checkPassword(updateModel);

    	if (c == 1) {
        	loginMapper.update(updateModel);
    		return "mainpage";
    	}else {
    		model.addAttribute("wrongInfo", "入力した情報が間違ったので更新できません");
    		return "update";
    	}

    }
    
    
      @PostMapping("show")
      public String getAll(AccountModel accountModel, Model model) {
    	  log.info("---{}", accountModel);
    	  if (loginMapper.showAll(accountModel) == null) {
    		  return "show";
    	  } else {
        	  List<ShowModel> info = loginMapper.showAll(accountModel);
      		  model.addAttribute("accounts", info);
      		  log.info("---{}", info);
        	  return "result";
    	  }
      }
      
      @PostMapping("decrypt")
      public String decrypt(DecryptModel decryptModel, Model model) {
	    	int c = loginMapper.checkDecrypt(decryptModel);
	      	if (c == 0) {
	      		model.addAttribute("wrongInfo", "入力した情報が間違ったので復号化できません");
	      		return "decrypt";
	      	}else {
	      	  String encrypted = loginMapper.getEncrypted(decryptModel);
	    	  String decrypted = serviceInstance.decryption(encrypted);
	    	  
	    	  log.info("---{}", decryptModel);
	    	  model.addAttribute("password", decrypted);
	    	  return "decrypt";
	      	}

      }
      

}
