package com.udacity.jdnd.course3.critter.customer;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper MAPPER = Mappers.getMapper(CustomerMapper.class);
    default CustomerDTO map(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setPetIds(customer.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList()));
        customerDTO.setNotes(customer.getNotes());
        customerDTO.setPhoneNumber(customerDTO.getPhoneNumber());
        return customerDTO;
    };
    Customer map(CustomerDTO CustomerDTO);
    List<CustomerDTO> map(List<Customer> Customers);
}
