package uz.dariko.collections.news;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NewsRepository extends JpaRepository<News, UUID> {

    @Query(nativeQuery = true, value = "SELECT * FROM News WHERE actual = ?1 ORDER BY CREATED_AT DESC LIMIT ?2")
    Optional<List<News>> findNewsByIsActualAndLimit(boolean actual, int limit);


    @Query(nativeQuery = true,value = "SELECT * FROM News WHERE is_deleted = ?")
    Optional<List<News>> findAllByDeleted(boolean isDeleted);


    @Query(nativeQuery = true,value = "SELECT * FROM news where id = ?1 and is_deleted = false")
    Optional<News> findByIdAndDeletedNot(UUID id);


    @Query(nativeQuery = true,value = "SELECT * FROM news where sphere_id = ?1 and is_deleted = ?2")
    List<News> findBySphereAndDeleted(UUID id,boolean b);

    @Query(value = "SELECT * FROM news WHERE is_deleted = ?1",
            countQuery = "SELECT count(*) FROM news WHERE is_deleted = ?1",
            nativeQuery = true)
    Page<News> findByIsDeleted(boolean deleted, Pageable pageable);



//    Page<News> findAll(boolean deleted,Pageable pageable);

//    Page<News> findAllByDeleted(Boolean is_deleted,Pageable pageable);

//    Page<News> findByDeletedNot(Boolean deleted,Pageable pageable);

    @Query(nativeQuery = true,value = "SELECT * FROM News where is_deleted = :deleted limit :size offset :offset")
    List<News> findAllByDeleted(boolean deleted, int size, int offset);

}
