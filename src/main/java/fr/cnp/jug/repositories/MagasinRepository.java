package fr.cnp.jug.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import fr.cnp.jug.domain.Magasin;

public interface MagasinRepository extends PagingAndSortingRepository<Magasin, Long> {

	Magasin findByNom(@Param("nom") String nom);
}
