package com.giggi.osterianapulion_be.mapper;

import com.giggi.osterianapulion_be.dto.request.tavolo.TavoloUpdateSchemaDTO;
import org.mapstruct.Mapper;

import java.util.List;

import com.giggi.osterianapulion_be.entity.Tavolo;
import com.giggi.osterianapulion_be.dto.request.tavolo.TavoloCreateRequestDTO;
import com.giggi.osterianapulion_be.dto.request.tavolo.TavoloUpdateRequestDTO;
import com.giggi.osterianapulion_be.dto.response.tavolo.TavoloFindDTO;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TavoloMapper {

    Tavolo convert(TavoloCreateRequestDTO dto);

    Tavolo convert(TavoloUpdateRequestDTO dto);

    Tavolo convert(TavoloUpdateSchemaDTO dto);

    TavoloFindDTO convert(Tavolo entity);

    List<TavoloFindDTO> convert(List<Tavolo> entities);

    @Mapping(target = "id", ignore = true)
    void updateSchema(@MappingTarget Tavolo esistente, Tavolo input);


}