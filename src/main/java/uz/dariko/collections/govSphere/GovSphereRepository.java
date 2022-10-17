package uz.dariko.collections.govSphere;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface GovSphereRepository extends JpaRepository<GovSphere, UUID> {


    @Query(nativeQuery = true,value = "SELECT * FROM gov_sphere where is_deleted = ?")
    List<GovSphere> findAllByDeleted(boolean isDeleted);



    @Query(nativeQuery = true,value = "SELECT * FROM gov_sphere where id = ?1 AND is_deleted = false")
    Optional<GovSphere> findByIdAndDeletedNot(UUID id);
}
