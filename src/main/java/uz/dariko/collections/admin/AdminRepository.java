package uz.dariko.collections.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.dariko.base.repository.BaseRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID>, BaseRepository {
    Optional<Admin> findByUsername(String username);
}
