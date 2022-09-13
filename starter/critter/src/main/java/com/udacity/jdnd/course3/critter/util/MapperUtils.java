package com.udacity.jdnd.course3.critter.util;

import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MapperUtils {

    public static CustomerDTO convertToCustomerDto(Customer customer) {
        ModelMapper modelMapper = new ModelMapper();
        CustomerDTO dto = modelMapper.map(customer, CustomerDTO.class);
        List<Pet> pets = customer.getPets();
        if (pets != null && !pets.isEmpty()) {
            List<Long> petIds = pets.stream()
                    .map(Pet::getId)
                    .collect(Collectors.toList());
            dto.setPetIds(petIds);
        }
        return dto;
    }

    public static Customer convertToCustomer(CustomerDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Customer.class);
    }

    public static List<CustomerDTO> convertToCustomerDtoList(List<Customer> customerList) {
        List<CustomerDTO> response = new ArrayList<>();
        if (!customerList.isEmpty()) {
            response = customerList.stream()
                    .map(MapperUtils::convertToCustomerDto)
                    .collect(Collectors.toList());
        }
        return response;
    }
}
