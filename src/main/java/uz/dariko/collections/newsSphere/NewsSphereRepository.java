package uz.dariko.collections.newsSphere;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.dariko.collections.submenu.Submenu;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NewsSphereRepository extends JpaRepository<NewsSphere, UUID> {


    @Query(nativeQuery = true,value = "SELECT * FROM news_sphere where id = ?1 AND is_deleted = false")
    Optional<NewsSphere> findByIdAndDeletedNot(UUID id);


    @Query(nativeQuery = true,value = "SELECT * FROM news_sphere where is_deleted = false")
    List<NewsSphere> findAllByDeletedNot();
}
