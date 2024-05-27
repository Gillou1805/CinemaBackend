package ue1104.iramps.be.api_backend.Model.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ue1104.iramps.be.api_backend.Model.BL.Personne;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "personne", path = "personne")
public interface PersonneRepository extends PagingAndSortingRepository<Personne,Long>, CrudRepository<Personne,Long>  {
    @Query("SELECT p FROM Personne p WHERE p.nom = :nom AND p.prenom = :prenom")
    List<Personne> findByNomAndPrenom(@Param("nom") String nom, @Param("prenom") String prenom);
    Personne findById(@Param("id") int id);
}
