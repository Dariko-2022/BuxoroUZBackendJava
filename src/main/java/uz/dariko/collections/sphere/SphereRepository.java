package uz.dariko.collections.sphere;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SphereRepository extends JpaRepository<Sphere, UUID> {
}
