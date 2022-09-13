package com.udacity.jdnd.course3.critter.util;

import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.Pet;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MapperUtils {

    // Customer
    public static CustomerDTO convertToCustomerDto(Customer customer) {
        ModelMapper modelMapper = new ModelMapper();
        CustomerDTO response = modelMapper.map(customer, CustomerDTO.class);
        List<Pet> pets = customer.getPets();
        if (pets != null && !pets.isEmpty()) {
            List<Long> petIds = pets.stream()
                    .map(Pet::getId)
                    .collect(Collectors.toList());
            response.setPetIds(petIds);
        }
        return response;
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

    // Employee
    public static EmployeeDTO convertToEmployeeDto(Employee employee) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public static Employee convertToEmployee(EmployeeDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Employee.class);
    }

    // Pet
    public static PetDTO convertToPetDto(Pet pet) {
        ModelMapper modelMapper = new ModelMapper();
        PetDTO response = modelMapper.map(pet, PetDTO.class);
        if (pet.getCustomer() != null) {
            response.setOwnerId(pet.getCustomer().getId());
        }
        return response;
    }

    public static Pet convertToPet(PetDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Pet.class);
    }

    public static List<PetDTO> convertToPetDtoList(List<Pet> petList) {
        return petList.stream()
                .map(MapperUtils::convertToPetDto)
                .collect(Collectors.toList());
    }
}
