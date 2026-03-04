package com.giggi.osterianapulion_be.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import com.giggi.osterianapulion_be.entity.BloccaPrenotazione;
import com.giggi.osterianapulion_be.dto.request.BloccaPrenotazione.BloccaPrenotazioneCreateRequestDTO;
import com.giggi.osterianapulion_be.dto.request.BloccaPrenotazione.BloccaPrenotazioneUpdateRequestDTO;
import com.giggi.osterianapulion_be.dto.response.BloccaPrenotazione.BloccaPrenotazioneFindDTO;

@Mapper(componentModel = "spring")
public interface BloccaPrenotazioneMapper {

    BloccaPrenotazione convert(BloccaPrenotazioneCreateRequestDTO dto);

    BloccaPrenotazione convert(BloccaPrenotazioneUpdateRequestDTO dto);

    BloccaPrenotazioneFindDTO convert(BloccaPrenotazione entity);

    List<BloccaPrenotazioneFindDTO> convert(List<BloccaPrenotazione> entities);
}