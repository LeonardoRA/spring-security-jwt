package com.accounts.vo;

import java.util.List;

import com.accounts.entity.AccountEntity;

public class BulkRequestVo {
	
	private List<AccountEntity> users;

	public List<AccountEntity> getUsers() {
		return users;
	}

	public void setUsers(List<AccountEntity> users) {
		this.users = users;
	}

}
