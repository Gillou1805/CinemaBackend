package ue1104.iramps.be.api_backend.Model.Repositories;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import ue1104.iramps.be.api_backend.Model.IntermediateTable.PersonneCours;
import ue1104.iramps.be.api_backend.Model.IntermediateTable.PersonneCoursKey;

@RepositoryRestResource(collectionResourceRel = "personne_cours", path = "personne_cours")
public interface PersonnesCoursRepository extends PagingAndSortingRepository<PersonneCours,PersonneCoursKey>, CrudRepository<PersonneCours,PersonneCoursKey>{
    List<PersonneCours> findByIdCoursID(@Param("cours_id") int id);
    List<PersonneCours> findByIdPersonneID(@Param("cours_id") int id);
}
