package uz.dariko.collections.stateEmloyee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.dariko.base.repository.BaseRepository;
import uz.dariko.collections.news.News;;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StateEmployeeRepository extends JpaRepository<StateEmployee, UUID> , BaseRepository {
    @Query(nativeQuery = true, value = "select * from state_employee where id = ?1 and is_deleted = ?2")
    Optional<StateEmployee> findByIdAndIsDeleted(UUID stateEmployeeID, boolean deleted);

    @Query(nativeQuery = true, value = "select * from link where is_deleted = ?1")
    List<StateEmployee> findAllByDeleted(boolean deleted);

    @Query(value = "SELECT * FROM state_employee WHERE is_deleted = ?1",
            countQuery = "SELECT count(*) FROM state_employee WHERE is_deleted = ?1",
            nativeQuery = true)
    Page<StateEmployee> findByIsDeleted(boolean deleted, Pageable pageable);

    @Query(nativeQuery = true,value = "SELECT * FROM state_employee where is_deleted = :deleted limit :size offset :offset")
    List<StateEmployee> findAllByDeleted(boolean deleted, int size, int offset);

//    @Modifying
//    @Query(nativeQuery = true, value = "update state_employee set order_number=?2 where id=?1 returning *")
//    StateEmployee setOrOrderNumber(UUID id, Integer order);

//    @Query(nativeQuery = true,value = "select count(*) from state_employee where is_deleted=false and gov_group_id= ?1")
//    Integer getTotalCount(UUID id);

}
