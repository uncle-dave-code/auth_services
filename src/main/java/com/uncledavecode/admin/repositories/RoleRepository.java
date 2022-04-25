package com.uncledavecode.admin.repositories;

import java.util.Optional;

import com.uncledavecode.admin.model.EnumRole;
import com.uncledavecode.admin.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findByName(EnumRole name);
}
