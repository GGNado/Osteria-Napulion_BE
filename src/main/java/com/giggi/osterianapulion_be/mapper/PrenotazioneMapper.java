package com.giggi.osterianapulion_be.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import com.giggi.osterianapulion_be.entity.Prenotazione;
import com.giggi.osterianapulion_be.dto.request.Prenotazione.PrenotazioneCreateRequestDTO;
import com.giggi.osterianapulion_be.dto.request.Prenotazione.PrenotazioneUpdateRequestDTO;
import com.giggi.osterianapulion_be.dto.response.Prenotazione.PrenotazioneFindDTO;

@Mapper(componentModel = "spring")
public interface PrenotazioneMapper {

    Prenotazione convert(PrenotazioneCreateRequestDTO dto);

    Prenotazione convert(PrenotazioneUpdateRequestDTO dto);

    PrenotazioneFindDTO convert(Prenotazione entity);

    List<PrenotazioneFindDTO> convert(List<Prenotazione> entities);
}