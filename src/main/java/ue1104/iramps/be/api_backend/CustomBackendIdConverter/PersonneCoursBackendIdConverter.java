package ue1104.iramps.be.api_backend.CustomBackendIdConverter;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.data.rest.webmvc.spi.BackendIdConverter;
import org.springframework.stereotype.Component;

import ue1104.iramps.be.api_backend.Model.IntermediateTable.PersonneCours;
import ue1104.iramps.be.api_backend.Model.IntermediateTable.PersonneCoursKey;

@Component
class PersonneCoursBackendIdConverter implements BackendIdConverter {

  @Override
  public Serializable fromRequestId(String id, Class<?> entityType) {
    String[] parts = id.split("_");
    return new PersonneCoursKey(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]),LocalDate.parse(parts[2]), LocalDate.parse(parts[3]));
  }

  @Override
  public String toRequestId(Serializable source, Class<?> entityType) {

    PersonneCoursKey id = (PersonneCoursKey) source;
    return String.format("%s_%s_%s_%s", id.getPersonneID(), id.getCoursID(), id.getDateDebut(), id.getDateFin());
  }

  @Override
  public boolean supports(Class<?> type) {
    return PersonneCours.class.equals(type);
  }
}

