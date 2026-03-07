package com.giggi.osterianapulion_be.mapper;

import org.mapstruct.Mapper;

import java.util.List;

import com.giggi.osterianapulion_be.entity.Tavolo;
import com.giggi.osterianapulion_be.dto.request.tavolo.TavoloCreateRequestDTO;
import com.giggi.osterianapulion_be.dto.request.tavolo.TavoloUpdateRequestDTO;
import com.giggi.osterianapulion_be.dto.response.tavolo.TavoloFindDTO;

@Mapper(componentModel = "spring")
public interface TavoloMapper {

    Tavolo convert(TavoloCreateRequestDTO dto);

    Tavolo convert(TavoloUpdateRequestDTO dto);

    TavoloFindDTO convert(Tavolo entity);

    List<TavoloFindDTO> convert(List<Tavolo> entities);
}