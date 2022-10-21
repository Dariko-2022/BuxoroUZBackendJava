package uz.dariko.collections.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface MenuRepository extends JpaRepository<Menu, UUID> {


    @Query(nativeQuery = true,value = "SELECT * from menu where id = ?1 and is_deleted = false")
    Optional<Menu> findByIdAndDeletedNot(UUID uuid);

    @Query(nativeQuery = true,value = "SELECT * from menu where is_deleted = false")
    List<Menu> findAllByDeletedNot();

    @Modifying
    @Query(nativeQuery = true, value = "update menu set order_number=?2 where id=?1 returning *")
    Menu setOrOrderNumber(UUID id, Integer order);

    @Query(nativeQuery = true,value = "select count(*) from menu where is_deleted=false")
    Integer getTotalCount();

}
