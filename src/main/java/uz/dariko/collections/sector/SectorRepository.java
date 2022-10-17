package uz.dariko.collections.sector;

import org.springframework.data.jpa.repository.JpaRepository;
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
}
