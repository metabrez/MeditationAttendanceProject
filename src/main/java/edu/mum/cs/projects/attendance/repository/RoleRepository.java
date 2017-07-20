package edu.mum.cs.projects.attendance.repository;

import org.springframework.data.repository.CrudRepository;

import edu.mum.cs.projects.attendance.domain.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
	Role findById(int roleId);
}
