package fr.epsi.erp.repository;

import fr.epsi.erp.model.Filiale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilialeRepository extends JpaRepository<Filiale, Long> {
}
