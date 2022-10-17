package uz.dariko.collections.news;

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

}
