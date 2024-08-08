package lk.ijse.possystembackend.bo;

import lk.ijse.possystembackend.dao.CustomerDAOIMPL;
import lk.ijse.possystembackend.dto.CustomerDTO;

import java.util.List;

public final class CustomerBOIMPL implements CustomerBO {
    private final CustomerDAOIMPL customerDAO = new CustomerDAOIMPL();

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    @Override
    public String saveCustomer(CustomerDTO customer) {
        return customerDAO.saveCustomer(customer);
    }

    @Override
    public boolean updateCustomer(String cusId, CustomerDTO customer) {
        return customerDAO.updateCustomer(cusId, customer);
    }

    @Override
    public CustomerDTO searchCustomer(String cusId) {
        return customerDAO.searchCustomer(cusId);
    }

    @Override
    public boolean deleteCustomer(String cusId) {
        return customerDAO.deleteCustomer(cusId);
    }
}
