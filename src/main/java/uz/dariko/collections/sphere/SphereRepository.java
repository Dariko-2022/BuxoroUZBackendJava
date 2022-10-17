package uz.dariko.collections.sphere;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SphereRepository extends JpaRepository<Sphere, UUID> {

    @Query(nativeQuery = true, value = "SELECT * FROM sphere where is_deleted = ?1")
    Optional<List<Sphere>> findAllByDeleted(boolean b);

    @Query(nativeQuery = true,value = "SELECT * FROM sphere where id = ?1 AND is_deleted = false")
    Optional<Sphere> findByIdAndDeletedNot(UUID id);
}
