package com.example.backend.dao.custom.impl;

import com.example.backend.dao.custom.CustomerDao;
import com.example.backend.db.ConnectionManager;
import com.example.backend.entity.Customer;
import com.example.backend.utill.SQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomDaoImpl implements CustomerDao {

    @Override
    public boolean save(Customer customer) throws SQLException {

        String sql = "INSERT INTO customer VALUES (?,?,?,?)";
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, customer.getId());
        pstm.setObject(2, customer.getName());
        pstm.setObject(3, customer.getAddress());
        pstm.setObject(4, customer.getSalary());
        return pstm.executeUpdate() > 0;
    }

    @Override
    public Customer getData(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE cust_id=?", id);
        if (resultSet.next()) {
            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
        }
        return null;
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        return SQLUtil.execute("UPDATE customer SET cust_name = ? , cust_address = ? , cust_salary = ? WHERE cust_id=?",
                customer.getName(),
                customer.getAddress(),
                customer.getSalary(),
                customer.getId()
        );

    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM customer WHERE cust_id=?", id);
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer");
        List<Customer> customerList = new ArrayList<>();
        while (resultSet.next()) {
            customerList.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));
        }
        return customerList;
    }
}