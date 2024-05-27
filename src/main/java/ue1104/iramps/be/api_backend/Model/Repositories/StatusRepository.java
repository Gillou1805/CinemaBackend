package ue1104.iramps.be.api_backend.Model.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ue1104.iramps.be.api_backend.Model.BL.Status;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "status", path = "status")
public interface StatusRepository extends PagingAndSortingRepository<Status,Long>, CrudRepository<Status,Long>  {
    List<Status> findByNom(@Param("nom") String nom);
    Status findById(@Param("id") int id);
}
