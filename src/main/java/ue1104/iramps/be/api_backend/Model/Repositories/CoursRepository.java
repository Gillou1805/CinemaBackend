package ue1104.iramps.be.api_backend.Model.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ue1104.iramps.be.api_backend.Model.BL.Cours;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "cours", path = "cours")
public interface CoursRepository extends PagingAndSortingRepository<Cours,Long>, CrudRepository<Cours,Long>  {
    List<Cours> findByNom(@Param("nom") String nom);
    Cours findById(@Param("id") int id);
}
