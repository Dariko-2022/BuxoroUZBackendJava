package uz.dariko.collections.stateEmloyee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StateEmployeeRepository extends JpaRepository<StateEmployee, UUID> {
}
