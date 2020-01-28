package fr.epsi.erp.repository;

import fr.epsi.erp.model.FournisseurProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FournisseurProduitRepository extends JpaRepository<FournisseurProduit, Long> {

}
