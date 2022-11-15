package uz.dariko.collections.footerElements;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FooterElementRepository extends JpaRepository<FooterElement, UUID> {

    @Query(nativeQuery = true,value = "SELECT * from footer_element where type = ?1 limit 1")
    Optional<FooterElement> findByTypeId(int typeId);

}
