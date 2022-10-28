package uz.dariko.collections.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface GovGroupOrderRepository extends JpaRepository<GovGroupOrder, UUID> {
    @Query(nativeQuery = true,value = "SELECT * FROM gov_group_order where id = ?1 and is_deleted = false")
    Optional<GovGroupOrder> findByIdAndDeletedNot(UUID uuid);
}
