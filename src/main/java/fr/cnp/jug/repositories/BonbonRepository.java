package fr.cnp.jug.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.cnp.jug.domain.Bonbon;
import fr.cnp.jug.domain.Magasin;

public interface BonbonRepository extends CrudRepository<Bonbon, Long> {

	Magasin findByNom(@Param("nom") String nom);
}
