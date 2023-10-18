package com.udacity.jdnd.course3.critter.customer;

import com.udacity.jdnd.course3.critter.pet.PetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PetRepository petRepository;
    private final CustomerMapper customerMapper;

    public CustomerDTO save(CustomerDTO customerDTO){
        Customer customer = getCustomer(customerDTO);
        customer = customerRepository.save(customer);
        return customerMapper.map(customer);
    }

    public CustomerDTO getCustomerByPetId(Long petId){
        return customerMapper.map(petRepository.findById(petId).get().getCustomer());
    }

    public List<CustomerDTO> getAll() {
        return customerRepository.findAll().stream().map(customer -> customerMapper.map(customer)).collect(Collectors.toList());
    }

    private Customer getCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        List<Long> petIds = customerDTO.getPetIds();
        if (petIds != null && !petIds.isEmpty()) {
            customer.setPets(petIds.stream().map(id -> petRepository.findById(id).get()).collect(Collectors.toList()));
        }else{
            customer.setPets(new ArrayList<>());
        }
        customer.setNotes(customer.getNotes());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        return customer;
    }
}
