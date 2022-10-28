package uz.dariko.collections.submenu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubmenuRepository extends JpaRepository<Submenu, UUID> {

    @Query(nativeQuery = true, value = "SELECT * FROM submenu where is_deleted = ?1")
    List<Submenu> findAllByDeleted(boolean b);

    @Query(nativeQuery = true,value = "SELECT * FROM submenu where id = ?1 AND is_deleted = false")
    Optional<Submenu> findByIdAndDeletedNot(UUID id);

    @Modifying
    @Query(nativeQuery = true, value = "update submenu set order_number=?2 where id=?1 returning *")
    Submenu setOrOrderNumber(UUID id, Integer order);

    @Query(nativeQuery = true,value = "select count(*) from submenu where is_deleted=false and menu_id= ?1")
    Integer getTotalCount(UUID id);

    @Query(nativeQuery = true,value = "SELECT * FROM submenu where menu_id = ?1 and is_deleted = ?2")
    List<Submenu> findByMenuAndDeleted(UUID uuid, boolean deleted);

}
