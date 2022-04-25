package com.uncledavecode.admin.bootstrap;

import java.util.List;

import com.uncledavecode.admin.model.EnumRole;
import com.uncledavecode.admin.model.Role;
import com.uncledavecode.admin.repositories.RoleRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Starter implements CommandLineRunner{

    private RoleRepository roleRepository;
    final Logger logger = LoggerFactory.getLogger(Starter.class);

    public Starter(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        logger.info("Roles:" + roleRepository.count());
        if(roleRepository.count()==0){

            Role rolUser = new Role(EnumRole.USER);
            Role rolModerator = new Role(EnumRole.MODERATOR);
            Role rolAdmin = new Role(EnumRole.ADMIN);
    
            roleRepository.saveAll(List.of(rolUser, rolModerator, rolAdmin));
        }
    }
    
}
