package uz.dariko.collections.govSphere;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface GovSphereRepository extends JpaRepository<GovSphere, UUID> {
}
