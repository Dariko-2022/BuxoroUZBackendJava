package uz.dariko.collections.employeeGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.dariko.collections.subGovGroup.SubGovGroup;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeGroupRepository extends JpaRepository<EmployeeGroup, UUID> {


    @Query(nativeQuery = true, value = "SELECT * from employee_group where submenu_id = ?1 and is_deleted = false")
    Optional<EmployeeGroup> findBySubmenuId(UUID id);

    @Query(nativeQuery = true,value = "SELECT * from employee_group where is_deleted = false and id = ?")
    Optional<EmployeeGroup> findByIdAndDeletedNot(UUID uuid);
}
