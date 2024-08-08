package lk.ijse.possystembackend.dao;

import lk.ijse.possystembackend.dto.CustomerDTO;
import lk.ijse.possystembackend.util.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class CustomerDAOIMPL implements CustomerDAO {
    private final Connection connection = DBConnection.getInstance().getConnection();

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> customers = new ArrayList<>();

        try {
            var pst = connection.prepareStatement("Select * from customer");
            var rs = pst.executeQuery();

            while (rs.next()){
                CustomerDTO customer = new CustomerDTO(
                        rs.getString("cus_id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("salary")
                );
                customers.add(customer);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public String saveCustomer(CustomerDTO customer) {
        try {
            var ps = connection.prepareStatement("insert into customer(cus_id, name, address, salary) values (?,?,?,?)");
            ps.setString(1, customer.getCusId());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getSalary());

            if (ps.executeUpdate() != 0){
                return "Customer saved";
            } else {
                return "Failed to saved";
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateCustomer(String cusId, CustomerDTO customer) {
        try {
            var ps = connection.prepareStatement("update customer set cus_id=?, name=?, address=?, salary=? where cus_id=?");
            ps.setString(1, customer.getCusId());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getSalary());


            return ps.executeUpdate() != 0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public CustomerDTO searchCustomer(String cusId) {
        try {
            CustomerDTO customerDTO = new CustomerDTO();
            var ps = connection.prepareStatement("select * from customer where cus_id=?");
            ps.setString(1, cusId);
            var rst = ps.executeQuery();

            while (rst.next()){
                customerDTO.setCusId(rst.getString("cus_id"));
                customerDTO.setName(rst.getString("name"));
                customerDTO.setAddress(rst.getString("address"));
                customerDTO.setSalary(rst.getString("salary"));
            }
            return customerDTO;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteCustomer(String cusId) {
        try {
            var ps = connection.prepareStatement("delete from customer where cus_id=?");
            ps.setString(1, cusId);
            return ps.executeUpdate() != 0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
