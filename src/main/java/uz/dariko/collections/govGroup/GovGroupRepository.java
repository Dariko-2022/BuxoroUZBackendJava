package uz.dariko.collections.govGroup;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GovGroupRepository extends JpaRepository<GovGroup, UUID> {

    @Query(nativeQuery = true,value = "SELECT * from gov_group where name = ?1 and is_deleted = ?2")
    Optional<GovGroup> findByNameAndIsDeleted(String name,boolean isDeleted);

    @Query(nativeQuery = true,value = "SELECT * from gov_group where id = ?1 and is_deleted = ?2")
    Optional<GovGroup> findByIdAndIsDeleted(UUID id,boolean isDeleted);

    @Query(nativeQuery = true, value = "SELECT * from gov_group where is_deleted = ?")
    List<GovGroup> findAllByDeleted(boolean deleted);

    @Query(nativeQuery = true,value = "SELECT * FROM gov_group where menu_id = ?1 and is_deleted = ?2")
    List<GovGroup> findByMenuAndDeleted(UUID uuid,boolean deleted);


    @Modifying
    @Query(nativeQuery = true, value = "update gov_group set order_number=?2 where id=?1 returning *")
    GovGroup setOrOrderNumber(UUID id, Integer order);

    @Query(nativeQuery = true,value = "select count(*) from state_employee where is_deleted=false and menu_id= ?1")
    Integer getTotalCount(UUID id);
}
