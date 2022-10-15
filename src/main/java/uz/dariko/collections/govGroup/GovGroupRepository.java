package uz.dariko.collections.govGroup;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GovGroupRepository extends JpaRepository<GovGroup, UUID> {

    @Query(nativeQuery = true,value = "SELECT * from gov_group where name = ?1 and is_deleted = false")
    Optional<GovGroup> findByNameAndDeletedNot(String name);

    @Query(nativeQuery = true,value = "SELECT * from gov_group where id = ?1 and is_deleted = false")
    Optional<GovGroup> findByIdAndDeletedNot(UUID id);

    @Query(nativeQuery = true, value = "SELECT * from gov_group where is_deleted = ?")
    List<GovGroup> findAllByDeleted(boolean deleted);

}
