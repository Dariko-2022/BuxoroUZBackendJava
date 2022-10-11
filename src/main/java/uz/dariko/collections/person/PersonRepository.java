package uz.dariko.collections.person;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.dariko.base.repository.BaseRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID>, BaseRepository {
}
