package com.giggi.osterianapulion_be.service.impl;


import com.giggi.osterianapulion_be.entity.Prenotazione;
import com.giggi.osterianapulion_be.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    @Override
    @Async
    public void sendConfermaPrenotazioneAsync(String destinatario, Prenotazione prenotazione) {
        String dataOra = prenotazione.getDataOra() != null
                ? prenotazione.getDataOra().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy 'alle' HH:mm"))
                : "data/ora non disponibile";

        String nomeCliente = prenotazione.getNomeCliente() != null ? prenotazione.getNomeCliente() : "";
        String cognomeCliente = prenotazione.getCognomeCliente() != null ? prenotazione.getCognomeCliente() : "";
        String cliente = (nomeCliente + " " + cognomeCliente).trim();
        if (cliente.isBlank()) cliente = "Cliente";

        String tavolo = (prenotazione.getTavolo() != null && prenotazione.getTavolo().getNome() != null)
                ? prenotazione.getTavolo().getNome()
                : "da assegnare";

        String testo = ""
                + "Ciao " + cliente + ",\n\n"
                + "✅ La tua prenotazione è stata confermata!\n\n"
                + "📅 Quando: " + dataOra + "\n"
                + "👥 Coperti: " + prenotazione.getCoperti() + "\n"
                + "🍽️ Tavolo: " + tavolo + "\n\n"
                + "Se hai bisogno di modificare o annullare la prenotazione, rispondi a questa email.\n\n"
                + "A presto,\n"
                + "Osteria Napulion";

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(destinatario);
        msg.setSubject("✅ Prenotazione confermata - Osteria Napulion");
        msg.setText(testo);

        mailSender.send(msg);
    }
}
