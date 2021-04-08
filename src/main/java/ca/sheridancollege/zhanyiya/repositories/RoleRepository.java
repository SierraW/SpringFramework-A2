package ca.sheridancollege.zhanyiya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.zhanyiya.beans.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	public Role findByRolename(String rolename);
}
