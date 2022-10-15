package uz.dariko.collections.stateEmloyee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.dariko.base.repository.BaseRepository;
import uz.dariko.collections.region.Region;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StateEmployeeRepository extends JpaRepository<StateEmployee, UUID> , BaseRepository {
    @Query(nativeQuery = true, value = "select * from state_employee where id = ?1 and is_deleted = ?2")
    Optional<StateEmployee> findByIdAndIsDeleted(UUID stateEmployeeID, boolean deleted);


}
