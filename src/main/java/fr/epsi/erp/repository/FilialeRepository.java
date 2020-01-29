package fr.epsi.erp.repository;

import fr.epsi.erp.model.Filiale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilialeRepository extends JpaRepository<Filiale, Long> {

    Optional<Filiale> findFirstByNom(String nom);
}
