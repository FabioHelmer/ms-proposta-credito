package com.fh.propostaapp.mapper;

import com.fh.propostaapp.dtos.PropostaRequest;
import com.fh.propostaapp.dtos.PropostaResponse;
import com.fh.propostaapp.entity.Proposta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PropostaMapper {

    PropostaMapper INSTANCE = Mappers.getMapper(PropostaMapper.class);


    @Mapping(target = "usuario.nome", source = "nome")
    @Mapping(target = "usuario.sobrenome", source = "sobrenome")
    @Mapping(target = "usuario.cpf", source = "cpf")
    @Mapping(target = "usuario.telefone", source = "telefone")
    @Mapping(target = "usuario.renda", source = "renda")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "aprovado", ignore = true)
    @Mapping(target = "integrada", ignore = true)
    @Mapping(target = "observacao", ignore = true)
    Proposta convertDtoToProposta(PropostaRequest propostaRequest);


    @Mapping(source = "usuario.nome", target = "nome")
    @Mapping(source = "usuario.sobrenome", target = "sobrenome")
    @Mapping(source = "usuario.cpf", target = "cpf")
    @Mapping(source = "usuario.telefone", target = "telefone")
    @Mapping(source = "usuario.renda", target = "renda")
    PropostaResponse convertEntityToDto(Proposta proposta);



}
