package ue1104.iramps.be.api_backend.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ue1104.iramps.be.api_backend.Model.BL.Calendrier;
import ue1104.iramps.be.api_backend.Model.IntermediateTable.CalendrierKey;
import ue1104.iramps.be.api_backend.Model.Repositories.CalendrierRepository;
import ue1104.iramps.be.api_backend.Model.BL.Salle;

import java.util.Optional;

@Service
public class CalendrierService {

    private final CalendrierRepository calendrierRepository;
    

    public CalendrierService(CalendrierRepository calendrierRepository) {
        this.calendrierRepository = calendrierRepository;
    }
    
    @Transactional(readOnly = true)
    public Calendrier getByKeyOrThrow(CalendrierKey key) {
        return calendrierRepository.findById(key)
                   .orElseThrow(() ->
                       new ResponseStatusException(
                         HttpStatus.BAD_REQUEST,
                         "Aucun calendrier trouvé pour idCalendrier="
                           + key.getIdCalendrier()
                           + " & idSalle=" + key.getIdSalle()
                       )
                   );
    }

    public Calendrier getCalendrierBySalle(Long idSalle) {
        Optional<Calendrier> optional = calendrierRepository.findBySalleId(idSalle);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            Calendrier c = new Calendrier();
            CalendrierKey key = new CalendrierKey();

            Integer maxId = calendrierRepository.findMaxIdCalendrier();
            if (maxId == null) {
                maxId = 0;
            }
            key.setIdCalendrier(maxId + 1);
            key.setIdSalle(idSalle);
            c.setId(key);

            Salle s = new Salle();
            s.setIdSalle(idSalle);
            c.setSalle(s);

            return calendrierRepository.save(c);
        }
    }
    
}
