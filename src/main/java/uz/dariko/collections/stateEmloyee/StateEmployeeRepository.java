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

    @Query(nativeQuery = true, value = "select * from state_employee where is_deleted = ?1")
    List<StateEmployee> findAllByDeleted(boolean deleted);

    @Query(nativeQuery = true, value = "SELECT * FROM state_employee as e JOIN sub_gov_group_employee_list as s ON e.id = s.employee_list_id and s.sub_gov_group_id = ?1 and e.is_deleted = false order by e.order_number, e.updated_at desc ")
    List<StateEmployee> findAllByDeletedAndSubGovGroup(UUID submenuId);


    @Query(nativeQuery = true,value = "SELECT * FROM state_employee where is_deleted = false and is_hokim = true;")
    Optional<StateEmployee> findAllHokim();

    @Query(value = "SELECT * FROM state_employee WHERE is_deleted = ?1",
            countQuery = "SELECT count(*) FROM state_employee WHERE is_deleted = ?1",
            nativeQuery = true)
    Page<StateEmployee> findByIsDeleted(boolean deleted, Pageable pageable);


//    @Query(value = "SELECT * FROM state_employee WHERE is_deleted = ?1",
//            countQuery = "SELECT count(*) FROM state_employee WHERE is_deleted = ?1",
//            nativeQuery = true)
//    Page<StateEmployee> findByIsDeletedAndSubmenu(UUID submenuId, boolean deleted, Pageable pageable);

    @Query(nativeQuery = true,value = "SELECT * FROM state_employee where is_deleted = :deleted limit :size offset :offset")
    List<StateEmployee> findAllByDeleted(boolean deleted, int size, int offset);



//    @Modifying
//    @Query(nativeQuery = true, value = "update state_employee set order_number=?2 where id=?1 returning *")
//    StateEmployee setOrOrderNumber(UUID id, Integer order);

//    @Query(nativeQuery = true,value = "select count(*) from state_employee where is_deleted=false and gov_group_id= ?1")
//    Integer getTotalCount(UUID id);


    @Query(nativeQuery = true,value = "SELECT count(*) FROM state_employee as e JOIN sub_gov_group_employee_list as s ON e.id = s.employee_list_id and s.sub_gov_group_id = ?1")
    Integer getOrderNumberBySubGovGroup(UUID subGovGroupId);

    @Query(nativeQuery = true,value = "SELECT count(*) FROM state_employee as e JOIN employee_group_employee_list as s ON e.id = s.employee_list_id and s.employee_group_id = ?1")
    Integer getOrderNumberByEmployeeGroup(UUID subGovGroupId);


    @Query(nativeQuery = true,value = "SELECT sub_gov_group_employee_list.sub_gov_group_id from sub_gov_group_employee_list where employee_list_id = ?1")
    String getSubGovGroup(UUID stateEmployeeId);

}
