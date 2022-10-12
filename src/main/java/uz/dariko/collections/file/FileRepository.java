package uz.dariko.collections.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.dariko.base.repository.BaseRepository;

import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<File, UUID>, BaseRepository {

}
