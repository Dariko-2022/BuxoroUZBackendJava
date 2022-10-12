package uz.dariko.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import uz.dariko.collections.admin.Admin;
import uz.dariko.collections.admin.AdminRepository;
import uz.dariko.exception.exceptions.UniversalException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EntityGetter {

    private final AdminRepository adminRepository;
    public Admin getAdmin(String username) {
        Optional<Admin> optional = adminRepository.findByUsername(username);
        return optional.orElseThrow(() -> {
            throw new UniversalException("Admin topilmadi", HttpStatus.NOT_FOUND);
        });
    }
}
