package uz.dariko.collections.informations;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.dariko.base.repository.BaseRepository;

import java.util.UUID;

public interface InformationRepository extends JpaRepository<Information, UUID>, BaseRepository {

}
