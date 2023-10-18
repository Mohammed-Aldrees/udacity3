package com.udacity.jdnd.course3.critter.pet;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetMapper {

    PetMapper MAPPER = Mappers.getMapper(PetMapper.class);
    default PetDTO map(Pet pet){
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setName(pet.getName());
        petDTO.setType(pet.getType());
        petDTO.setOwnerId(pet.getCustomer().getId());
        petDTO.setBirthDate(pet.getBirthDate());
        petDTO.setNotes(pet.getNotes());
        return petDTO;
    }
    Pet map(PetDTO PetDTO);
    List<PetDTO> map(List<Pet> Pets);
}
