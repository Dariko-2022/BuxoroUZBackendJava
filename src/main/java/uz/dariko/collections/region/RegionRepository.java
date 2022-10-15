package uz.dariko.collections.region;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.dariko.collections.link.Link;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RegionRepository extends JpaRepository<Region, UUID> {

    @Query(nativeQuery = true, value = "select * from region where id = ?1 and is_deleted = ?2")
    Optional<Region> findByIdAndIsDeleted(UUID regionID, boolean deleted);
}
