package uz.dariko.collections.infoGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface InfoGroupRepository extends JpaRepository<InfoGroup, UUID> {


    @Query(nativeQuery = true,value = "SELECT * FROM infogroup where is_deleted = ?")
    List<InfoGroup> findAllByDeleted(boolean deleted);


    @Query(nativeQuery = true,value = "Select * from infogroup where id = ?1 and is_deleted = false")
    Optional<InfoGroup> findByIdAndDeletedNot(UUID uuid);
}
