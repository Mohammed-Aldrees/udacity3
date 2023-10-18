package com.udacity.jdnd.course3.critter.pet;


import com.udacity.jdnd.course3.critter.customer.Customer;
import com.udacity.jdnd.course3.critter.customer.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class PetService {

    private final CustomerRepository customerRepository;
    private final PetRepository petRepository;
    private final PetMapper petMapper;

    public PetDTO save(PetDTO petDTO){
        Pet pet = petMapper.map(petDTO);
        Customer customer = customerRepository.getOne(petDTO.getOwnerId());
        pet.setCustomer(customer);
        pet = petRepository.save(pet);
        customer.insertPet(pet);
        customerRepository.save(customer);
        return petMapper.map(pet);
    }
    public PetDTO get(Long petId){
        Pet pet = petRepository.findById(petId).get();
        return petMapper.map(pet);
    }
    public List<PetDTO> getAll() {
        return petRepository.findAll().stream().map(pet -> petMapper.map(pet)).collect(Collectors.toList());
    }
    public List<PetDTO> getPetsByOwner(long ownerId) {
        List<PetDTO> petDTOList = petRepository.getAllByCustomerId(ownerId).stream().map(pet -> petMapper.map(pet)).collect(Collectors.toList());
        return petDTOList;
    }
}