package uz.dariko.collections.sector;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface SectorRepository extends JpaRepository<Sector, UUID> {

    @Query(nativeQuery = true,value = "SELECT * FROM Sector where region_id = ?1")
    Optional<List<Sector>> findAllById(UUID uuid);


    @Query(nativeQuery = true,value = "SELECT * FROM Sector where is_deleted = false")
    List<Sector> findAllByDeletedNot();


    @Query(nativeQuery = true,value = "SELECT * FROM Sector where id = ?1 and is_deleted = false")
    Optional<Sector> findByIdAndDeletedNot(UUID id);

    @Modifying
    @Query(nativeQuery = true, value = "update state_employee set order_number=?2 where id=?1 returning *")
    Sector setOrOrderNumber(UUID id, Integer order);

    @Query(nativeQuery = true,value = "select count(*) from state_employee where is_deleted=false")
    Integer getTotalCount();
}
