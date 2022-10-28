package uz.dariko.collections.informations;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.dariko.base.repository.BaseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InformationRepository extends JpaRepository<Information, UUID>, BaseRepository {

    @Query(nativeQuery = true,value = "SELECT * FROM information where is_deleted = ?1")
    List<Information> findAllByDeleted(boolean deleted);

    @Query(nativeQuery = true,value = "Select * from information where id = ?1 and is_deleted = ?2")
    Optional<Information> findByIdAndDeletedNot(UUID uuid,boolean deleted);

    @Query(nativeQuery = true,value = "Select * from information where submenu_id = ?1 and is_deleted = ?2")
    List<Information> findAllByInfoGroup(UUID uuid,boolean deleted);

    @Query(nativeQuery = true,
            value = "SELECT * FROM information WHERE is_deleted = :deleted and submenu_id = :uuid",
            countQuery = "SELECT count(*) FROM information WHERE is_deleted = :deleted and submenu_id = :uuid")
    Page<Information> findBySubmenuIdAndIsDeleted(UUID uuid, Pageable pageable, boolean deleted);

    @Query(nativeQuery = true,value = "SELECT * FROM information WHERE is_deleted = :deleted and submenu_id = :uuid limit :size offset :offset")
    List<Information> findAllBySubmenuIdAndDeletedNot(UUID uuid, int size, int offset, boolean deleted);

}
