package uz.dariko.collections.link;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.dariko.base.repository.BaseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LinkRepository extends JpaRepository<Link, UUID>, BaseRepository {
    @Query(nativeQuery = true, value = "select * from link where id = ?1 and is_deleted = ?2")
    Optional<Link> findByIdAndIsDeleted(UUID linkID, boolean deleted);

    @Query(nativeQuery = true, value = "select * from link is_deleted = ?1")
    List<Link> findAllIsDeleted(boolean deleted);

    @Query(nativeQuery = true, value = "select * from link where link_type_code=?1 and is_deleted = ?2")
    List<Link> findAllByLinkTypeCodeIsDeleted(Integer code,boolean deleted);
}
