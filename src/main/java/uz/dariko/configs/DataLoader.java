package uz.dariko.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.dariko.collections.admin.Admin;
import uz.dariko.collections.admin.AdminRepository;
import uz.dariko.collections.sector.Sector;
import uz.dariko.collections.sector.SectorRepository;

import java.util.UUID;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final SectorRepository sectorRepository;
    private final PasswordEncoder passwordEncoder;


    @Value(value = "${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    public DataLoader(AdminRepository adminRepository, SectorRepository sectorRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.sectorRepository = sectorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (ddl.equalsIgnoreCase("create") || ddl.equalsIgnoreCase("create-drop")) {
            Admin admin = new Admin(UUID.fromString("d3bf68eb-adb9-4e6d-88d1-92e98ca30a81"),"11", passwordEncoder.encode( "11"),null,"Arslonboy","Isorov","dariko@gmail.com",
                    "+998997777777",true);
            adminRepository.save(admin);
            Sector sector1 = new Sector("1-Sektor","1-Сектор","1-Сектор",null,null,"empty","empty","empty","empty","empty","empty",1);
            Sector sector2 = new Sector("2-Sektor","2-Сектор","2-Сектор",null,null,"empty","empty","empty","empty","empty","empty",2);
            Sector sector3 = new Sector("3-Sektor","3-Сектор","3-Сектор",null,null,"empty","empty","empty","empty","empty","empty",3);
            Sector sector4 = new Sector("4-Sektor","4-Сектор","4-Сектор",null,null,"empty","empty","empty","empty","empty","empty",4);
            sectorRepository.save(sector1);
            sectorRepository.save(sector2);
            sectorRepository.save(sector3);
            sectorRepository.save(sector4);
        }
    }
}
