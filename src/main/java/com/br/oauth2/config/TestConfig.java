package com.br.oauth2.config;

import com.br.oauth2.models.RoleModel;
import com.br.oauth2.models.UserModel;
import com.br.oauth2.models.enums.RoleStatus;
import com.br.oauth2.repository.RoleRepository;
import com.br.oauth2.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public TestConfig(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        roleRepository.deleteAll();

        RoleModel roleAdmin = new RoleModel(1L, RoleStatus.ADMIN.name(), "Grants the user permission to access specific administrator endpoints", RoleStatus.ADMIN);
        RoleModel roleBasic = new RoleModel(2L, RoleStatus.BASIC.name(), "Grants the user permission to access basic endpoints", RoleStatus.BASIC);

        roleRepository.saveAll(Arrays.asList(roleAdmin, roleBasic));

        var userAdmin = userRepository.findByUsername("ADMIN");

        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("User já existe!");
                },
                () -> {
                    var u1 = new UserModel();
                    u1.setName("ADMIN");
                    u1.setSurname("ADMIN");
                    u1.setEmail("admin@gmail.com");
                    u1.setUsername("ADMIN");
                    u1.setPassword(bCryptPasswordEncoder.encode("123"));
                    u1.getRoles().add(roleAdmin);
                    userRepository.save(u1);
                }
        );
    }
}
