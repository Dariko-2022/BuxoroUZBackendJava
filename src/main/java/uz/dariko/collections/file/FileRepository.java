package uz.dariko.collections.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.dariko.base.repository.BaseRepository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<File, UUID>, BaseRepository {
    List<File> findByGeneratedName(String generatedName);

    Optional<File> findByIdAndIsDeleted(UUID entityID, boolean deleted);


    @Query(nativeQuery = true,value = "SELECT * from file where generated_name = ?1")
    Optional<File> findByName(String name);
}
