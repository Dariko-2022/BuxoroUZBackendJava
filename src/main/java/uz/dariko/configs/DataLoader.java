package uz.dariko.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import uz.dariko.collections.admin.Admin;
import uz.dariko.collections.admin.AdminRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final AdminRepository adminRepository;


    @Value(value = "${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    public DataLoader(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (ddl.equalsIgnoreCase("create") || ddl.equalsIgnoreCase("create-drop")) {
            Admin admin = new Admin(UUID.fromString("d3bf68eb-adb9-4e6d-88d1-92e98ca30a81"),"dariko","123",null,"Najmiddin","Ibrohimov","ariko@gmail.com",
                    "+998997777777",true);
            adminRepository.save(admin);

        }
    }
}
