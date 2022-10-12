package uz.dariko.collections.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.dariko.collections.admin.Admin;
import uz.dariko.collections.admin.AdminRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Admin> optional = adminRepository.findByUsername(login);
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("User topilmadi");
        }
        return optional.get();
    }
}
