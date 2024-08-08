package lk.ijse.possystembackend.bo;

import lk.ijse.possystembackend.dto.CustomerDTO;

import java.util.List;

public sealed interface CustomerBO permits CustomerBOIMPL{
    List<CustomerDTO> getAllCustomers();
    String saveCustomer(CustomerDTO customer);
    boolean updateCustomer(String cusId, CustomerDTO customer);
    CustomerDTO searchCustomer(String cusId);
    boolean deleteCustomer(String cusId);
}
