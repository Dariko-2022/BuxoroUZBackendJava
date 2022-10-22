package uz.dariko.collections.sphere;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SphereRepository extends JpaRepository<Sphere, UUID> {

    @Query(nativeQuery = true, value = "SELECT * FROM sphere where is_deleted = ?1")
    List<Sphere> findAllByDeleted(boolean b);

    @Query(nativeQuery = true,value = "SELECT * FROM sphere where id = ?1 AND is_deleted = false")
    Optional<Sphere> findByIdAndDeletedNot(UUID id);

    @Modifying
    @Query(nativeQuery = true, value = "update sphere set order_number=?2 where id=?1 returning *")
    Sphere setOrOrderNumber(UUID id, Integer order);

    @Query(nativeQuery = true,value = "select count(*) from sphere where is_deleted=false and menu_id= ?1")
    Integer getTotalCount(UUID id);

}
