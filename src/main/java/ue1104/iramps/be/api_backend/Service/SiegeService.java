package ue1104.iramps.be.api_backend.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ue1104.iramps.be.api_backend.Model.BL.Reservation;
import ue1104.iramps.be.api_backend.Model.BL.Siege;
import ue1104.iramps.be.api_backend.Model.Repositories.ReservationRepository;
import ue1104.iramps.be.api_backend.Model.Repositories.SiegeRepository;

import java.util.List;

@Service
public class SiegeService {

    private final SiegeRepository       siegeRepo;
    private final ReservationRepository reservationRepo;

    public SiegeService(SiegeRepository siegeRepo,
                        ReservationRepository reservationRepo) {
        this.siegeRepo       = siegeRepo;
        this.reservationRepo = reservationRepo;
    }

    /**
     * Récupère tous les Siege pour un créneau donné
     */
    public List<Siege> findByCreneau(Long creneauId) {
        return siegeRepo.findByReservation_Creneau_IdCreneau(creneauId);
    }

    /**
     * Après création d’une Reservation, persiste les Siege associés
     */
    @Transactional
    public void createSiegesForReservation(Long reservationId, List<Integer> positions) {
        Reservation reso = reservationRepo.findById(reservationId)
            .orElseThrow(() -> new IllegalArgumentException("Réservation introuvable: " + reservationId));

        for (Integer pos : positions) {
            Siege s = new Siege();
            s.setPosition(pos);
            s.setReservation(reso);
            siegeRepo.save(s);
        }
    }
}
