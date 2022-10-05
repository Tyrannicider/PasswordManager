package com.example.ts.mapper;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.example.ts.model.AccountModel;
import com.example.ts.model.DecryptModel;
import com.example.ts.model.DeleteModel;
import com.example.ts.model.LoginModel;
import com.example.ts.model.MasterModel;
import com.example.ts.model.ShowModel;
import com.example.ts.model.UpdateModel;

@Mapper
public interface LoginMapper {

	int checkLogin(LoginModel loginModel);

	void register(MasterModel masterModel);

	void addnew(AccountModel accountModel);

	void delete(DeleteModel deleteModel);

	void update(UpdateModel updateModel);

	int checkPassword(UpdateModel updateModel);

	ShowModel show(ShowModel showModel);
	
	List<ShowModel> showAll(AccountModel accountModel);

	String getEncrypted(DecryptModel decryptModel);

	int checkAccount(AccountModel accountModel);

	int checkEmail(@Valid MasterModel masterModel);

	int checkDelete(DeleteModel deleteModel);

	int checkDecrypt(DecryptModel decryptModel);

	




}
