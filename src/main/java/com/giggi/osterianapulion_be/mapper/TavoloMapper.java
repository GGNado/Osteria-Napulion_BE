package com.giggi.osterianapulion_be.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import com.giggi.osterianapulion_be.entity.Tavolo;
import com.giggi.osterianapulion_be.dto.request.Tavolo.TavoloCreateRequestDTO;
import com.giggi.osterianapulion_be.dto.request.Tavolo.TavoloUpdateRequestDTO;
import com.giggi.osterianapulion_be.dto.response.Tavolo.TavoloFindDTO;

@Mapper(componentModel = "spring")
public interface TavoloMapper {

    Tavolo convert(TavoloCreateRequestDTO dto);

    Tavolo convert(TavoloUpdateRequestDTO dto);

    TavoloFindDTO convert(Tavolo entity);

    List<TavoloFindDTO> convert(List<Tavolo> entities);
}