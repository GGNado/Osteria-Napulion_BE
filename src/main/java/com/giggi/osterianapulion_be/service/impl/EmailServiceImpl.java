package com.giggi.osterianapulion_be.service.impl;


import com.giggi.osterianapulion_be.entity.Prenotazione;
import com.giggi.osterianapulion_be.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;

    @Override
    @Async
    public void sendRicezionePrenotazioneAsync(String destinatario, Prenotazione prenotazione) {
        String dataOra = formattaData(prenotazione);
        String cliente = formattaCliente(prenotazione);
        String tavolo = formattaTavolo(prenotazione);

        String testo = "Ciao " + cliente + ",\n\n"
                + "📬 Abbiamo ricevuto la tua richiesta di prenotazione!\n\n"
                + "📅 Quando: " + dataOra + "\n"
                + "👥 Coperti: " + prenotazione.getCoperti() + "\n"
                + "🍽️ Tavolo: " + tavolo + "\n\n"
                + "Ti confermeremo a breve la disponibilità.\n\n"
                + "A presto,\n"
                + "Osteria Napulion";

        invia(destinatario, "📬 Richiesta ricevuta - Osteria Napulion", testo);
    }

    @Override
    @Async
    public void sendConfermaPrenotazioneAsync(String destinatario, Prenotazione prenotazione) {
        String dataOra = formattaData(prenotazione);
        String cliente = formattaCliente(prenotazione);
        String tavolo = formattaTavolo(prenotazione);

        String testo = "Ciao " + cliente + ",\n\n"
                + "✅ La tua prenotazione è stata confermata!\n\n"
                + "📅 Quando: " + dataOra + "\n"
                + "👥 Coperti: " + prenotazione.getCoperti() + "\n"
                + "🍽️ Tavolo: " + tavolo + "\n\n"
                + "Se hai bisogno di modificare o annullare, rispondi a questa email.\n\n"
                + "A presto,\n"
                + "Osteria Napulion";

        invia(destinatario, "✅ Prenotazione confermata - Osteria Napulion", testo);
    }

    @Override
    @Async
    public void sendRifiutoPrenotazioneAsync(String destinatario, Prenotazione prenotazione) {
        String dataOra = formattaData(prenotazione);
        String cliente = formattaCliente(prenotazione);

        String testo = "Ciao " + cliente + ",\n\n"
                + "❌ Siamo spiacenti, non siamo riusciti a confermare la tua prenotazione.\n\n"
                + "📅 Data richiesta: " + dataOra + "\n"
                + "👥 Coperti: " + prenotazione.getCoperti() + "\n\n"
                + "Potrebbe essere che non ci sia disponibilità per quella data o orario.\n"
                + "Ti invitiamo a contattarci o a provare con un'altra data.\n\n"
                + "Ci scusiamo per il disagio,\n"
                + "Osteria Napulion";

        invia(destinatario, "❌ Prenotazione non disponibile - Osteria Napulion", testo);
    }

    // --- helper privati ---

    private void invia(String destinatario, String subject, String testo) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(destinatario);
        msg.setSubject(subject);
        msg.setText(testo);
        mailSender.send(msg);
    }

    private String formattaData(Prenotazione p) {
        return p.getDataOra() != null
                ? p.getDataOra().format(DateTimeFormatter.ofPattern("dd/MM/yyyy 'alle' HH:mm"))
                : "data/ora non disponibile";
    }

    private String formattaCliente(Prenotazione p) {
        String nome = p.getNomeCliente() != null ? p.getNomeCliente() : "";
        String cognome = p.getCognomeCliente() != null ? p.getCognomeCliente() : "";
        String cliente = (nome + " " + cognome).trim();
        return cliente.isBlank() ? "Cliente" : cliente;
    }

    private String formattaTavolo(Prenotazione p) {
        return (p.getTavolo() != null && p.getTavolo().getNome() != null)
                ? p.getTavolo().getNome()
                : "da assegnare";
    }
}
