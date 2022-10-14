package uz.dariko.collections.news;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NewsRepository extends JpaRepository<News, UUID> {

    @Query(nativeQuery = true, value = "SELECT * FROM News WHERE actual = ?1 ORDER BY CREATED_AT DESC LIMIT ?2")
    List<News> findNewsByIsActualAndLimit(boolean actual, int limit);

}
