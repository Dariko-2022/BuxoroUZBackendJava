package uz.dariko.collections.subGovGroup;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubGovGroupRepository extends JpaRepository<SubGovGroup, UUID> {

    @Query(nativeQuery = true,value = "SELECT * from sub_gov_group where name = ?1 and is_deleted = ?2")
    Optional<SubGovGroup> findByNameAndIsDeleted(String name, boolean isDeleted);

    @Query(nativeQuery = true,value = "SELECT * from sub_gov_group where id = ?1 and is_deleted = ?2")
    Optional<SubGovGroup> findByIdAndIsDeleted(UUID id, boolean isDeleted);

    @Query(nativeQuery = true, value = "SELECT * from sub_gov_group where is_deleted = ?")
    List<SubGovGroup> findAllByDeleted(boolean deleted);

    @Query(nativeQuery = true,value = "SELECT * from sub_gov_group where id = ?1 and is_deleted = false")
    Optional<SubGovGroup> findByIdAndDeletedNot(UUID id);

    @Query(nativeQuery = true, value = "SELECT * from sub_gov_group where submenu_id = ?1")
    List<SubGovGroup> findAllBySubmenuId(UUID id);

    @Query(nativeQuery = true, value = "SELECT * from sub_gov_group where is_deleted = false and submenu_id = ?1")
    List<SubGovGroup> findAllBySubmenuIdAndDeletedNot(UUID id);


//    @Modifying
//    @Query(nativeQuery = true,value = "")
//    void update(SubGovGroup subGovGroup);


}
