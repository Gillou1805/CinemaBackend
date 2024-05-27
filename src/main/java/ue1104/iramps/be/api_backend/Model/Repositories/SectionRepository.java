package ue1104.iramps.be.api_backend.Model.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ue1104.iramps.be.api_backend.Model.BL.Section;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "section", path = "section")
public interface SectionRepository extends PagingAndSortingRepository<Section,Long>, CrudRepository<Section,Long>  {
    List<Section> findByNom(@Param("nom") String nom);
    Section findById(@Param("id") int id);
}
