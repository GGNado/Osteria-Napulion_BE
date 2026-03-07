package com.giggi.osterianapulion_be.policy.prenotazione;

import com.giggi.osterianapulion_be.entity.Prenotazione;
import com.giggi.osterianapulion_be.entity.StatoPrenotazione;
import com.giggi.osterianapulion_be.repository.PrenotazioneRepository;
import com.giggi.osterianapulion_be.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HandlerPrenotazionePolicy implements HandlePrenotazione{

    private final EmailService emailService;

    @Override
    public void handleStateChangeEvent(Prenotazione prenotazione) {
        if (prenotazione.getStato() == StatoPrenotazione.CONFERMATA){
            emailService.sendConfermaPrenotazioneAsync(prenotazione.getEmailCliente(), prenotazione);
        } else if (prenotazione.getStato() == StatoPrenotazione.ANNULLATA){
            emailService.sendRifiutoPrenotazioneAsync(prenotazione.getEmailCliente(), prenotazione);
        }
    }
}
