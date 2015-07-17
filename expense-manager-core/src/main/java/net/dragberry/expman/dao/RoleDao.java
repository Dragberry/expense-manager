package net.dragberry.expman.dao;

import net.dragberry.expman.domain.Role;

public interface RoleDao {

	Role findRoleByName(String name);
}
