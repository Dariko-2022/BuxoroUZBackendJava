package uz.dariko.collections.informations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.dariko.base.repository.BaseRepository;
import uz.dariko.collections.infoGroup.InfoGroup;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InformationRepository extends JpaRepository<Information, UUID>, BaseRepository {

    @Query(nativeQuery = true,value = "SELECT * FROM information where is_deleted = ?1")
    List<Information> findAllByDeleted(boolean deleted);

    @Query(nativeQuery = true,value = "Select * from information where id = ?1 and is_deleted = ?2")
    Optional<Information> findByIdAndDeletedNot(UUID uuid,boolean deleted);

    @Query(nativeQuery = true,value = "Select * from information where infoGroupid = ?1 and is_deleted = ?2")
    List<Information> findAllByInfoGroup(UUID uuid,boolean deleted);
}
