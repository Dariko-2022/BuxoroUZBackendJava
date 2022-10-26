package uz.dariko.collections.employeeGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeGroupRepository extends JpaRepository<EmployeeGroup, UUID> {


}
