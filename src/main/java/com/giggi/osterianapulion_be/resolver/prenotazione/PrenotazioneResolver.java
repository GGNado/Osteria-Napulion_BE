package com.giggi.osterianapulion_be.resolver.prenotazione;

import com.giggi.osterianapulion_be.entity.Prenotazione;
import com.giggi.osterianapulion_be.entity.Tavolo;
import com.giggi.osterianapulion_be.repository.PrenotazioneRepository;
import com.giggi.osterianapulion_be.repository.TavoloRepository;
import com.giggi.osterianapulion_be.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PrenotazioneResolver {

    private final TavoloRepository tavoloRepository;
    private final PrenotazioneRepository prenotazioneRepository;

    @Value("${prenotazione.finestra-ore-prima}")
    private int finestraOrePrima;

    @Value("${prenotazione.finestra-ore-dopo}")
    private int finestraOreDopo;

    public PrenotazioneContext resolve(Prenotazione request) {

        List<Tavolo> tavoliCompatibili = tavoloRepository
                .findAllByMinimoPostiLessThanEqualAndMassimoPostiGreaterThanEqualAndAttivoTrue(
                        request.getCoperti(),
                        request.getCoperti()
                );

        List<Prenotazione> prenotazioniEsistenti = prenotazioneRepository
                .findByDataOraBetweenAndTavoloIsNotNull(
                        request.getDataOra().minusHours(finestraOrePrima),
                        request.getDataOra().plusHours(finestraOreDopo)
                );

        return new PrenotazioneContext(tavoliCompatibili, prenotazioniEsistenti, request);
    }
}