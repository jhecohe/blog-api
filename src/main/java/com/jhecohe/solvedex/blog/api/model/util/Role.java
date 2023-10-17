package com.jhecohe.solvedex.blog.api.model.util;

import java.util.Arrays;
import java.util.List;

public enum Role {
	ADMINISTRATOR (Arrays.asList(
			RolePermissions.CREATE_POST,
			RolePermissions.UPDATE_POST,
			RolePermissions.DELETE_POST,
			RolePermissions.CREATE_COMMENT,
			RolePermissions.UPDATE_COMMENT,
			RolePermissions.DELETE_COMMMENT)
			), 
	EDITOR (Arrays.asList(
			RolePermissions.CREATE_POST,
			RolePermissions.UPDATE_POST,
			RolePermissions.CREATE_COMMENT,
			RolePermissions.UPDATE_COMMENT,
			RolePermissions.DELETE_COMMMENT)
			),
	FOLLOWER (Arrays.asList(
			RolePermissions.CREATE_COMMENT,
			RolePermissions.UPDATE_COMMENT)
			);
	
	private List<RolePermissions> permissions;
	
	Role(List<RolePermissions> permissions){
		this.permissions = permissions;
	}

	public List<RolePermissions> getPermissions() {
		return permissions;
	}
}
