package uz.dariko.collections.infoGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface InfoGroupRepository extends JpaRepository<InfoGroup, UUID> {


    @Query(nativeQuery = true,value = "SELECT * FROM info_group where is_deleted = ?")
    List<InfoGroup> findAllByDeleted(boolean deleted);


    @Query(nativeQuery = true,value = "Select * from info_group where id = ?1 and is_deleted = false")
    Optional<InfoGroup> findByIdAndDeletedNot(UUID uuid);

    @Modifying
    @Query(nativeQuery = true, value = "update state_employee set order_number=?2 where id = ?1 returning *")
    InfoGroup setOrOrderNumber(UUID id, Integer order);

    @Query(nativeQuery = true,value = "select count(*) from info_group where is_deleted=false and menu_id = ?1")
    Integer getTotalCount(UUID id);
}
