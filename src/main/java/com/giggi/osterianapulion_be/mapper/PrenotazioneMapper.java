package com.giggi.osterianapulion_be.mapper;

import org.mapstruct.Mapper;

import java.util.List;

import com.giggi.osterianapulion_be.entity.Prenotazione;
import com.giggi.osterianapulion_be.dto.request.prenotazione.PrenotazioneCreateRequestDTO;
import com.giggi.osterianapulion_be.dto.request.prenotazione.PrenotazioneUpdateRequestDTO;
import com.giggi.osterianapulion_be.dto.response.prenotazione.PrenotazioneFindDTO;

@Mapper(componentModel = "spring")
public interface PrenotazioneMapper {

    Prenotazione convert(PrenotazioneCreateRequestDTO dto);

    Prenotazione convert(PrenotazioneUpdateRequestDTO dto);

    PrenotazioneFindDTO convert(Prenotazione entity);

    List<PrenotazioneFindDTO> convert(List<Prenotazione> entities);
}