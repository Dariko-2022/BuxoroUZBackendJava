package uz.dariko.collections.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.dariko.base.repository.BaseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID>, BaseRepository {
    Optional<Admin> findByUsername(String username);

    @Query(nativeQuery = true, value = "select * from state_employee where id = ?1 and is_deleted = ?2")
    Optional<Admin> findByIdAndDeleted(UUID uuid,Boolean deleted);

    @Query(nativeQuery = true, value = "select * from state_employee where  is_deleted = ?1")
    List<Admin> findAllByDeleted(Boolean deleted);
}
