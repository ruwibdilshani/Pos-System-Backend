package lk.ijse.possystembackend.dao;

import lk.ijse.possystembackend.dto.CustomerDTO;

import java.util.List;

public sealed interface CustomerDAO permits CustomerDAOIMPL{
    List<CustomerDTO> getAllCustomers();
    String saveCustomer(CustomerDTO customer);
    boolean updateCustomer(String cusId, CustomerDTO customer);
    CustomerDTO searchCustomer(String cusId);
    boolean deleteCustomer(String cusId);
}
