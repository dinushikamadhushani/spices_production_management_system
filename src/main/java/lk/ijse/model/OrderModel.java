package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.OrderDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderModel {
    public boolean saveOrder(final OrderDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO orders VALUES(?, ?, ?, ?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getOrderId());
        pstm.setString(2, dto.getCustomerId());
        pstm.setString(3, dto.getItemId());
        pstm.setString(4, dto.getDate());
        pstm.setString(5, dto.getQty());
        pstm.setString(6, dto.getPayment());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public boolean updateOrder(final OrderDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE orders SET customer_id = ?, item_id = ?, qty = ?, date = ?, payment = ?, WHERE order_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getOrderId());
        pstm.setString(2, dto.getCustomerId());
        pstm.setString(3, dto.getItemId());
        pstm.setString(4, dto.getDate());
        pstm.setString(5, dto.getQty());
        pstm.setString(6, dto.getPayment());

        return pstm.executeUpdate() > 0;
    }

    public OrderDto searchOrder(String orderId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM order WHERE order_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, orderId);

        ResultSet resultSet = pstm.executeQuery();

        OrderDto dto = null;

        if(resultSet.next()) {
            String od_id = resultSet.getString(1);
            String cus_id = resultSet.getString(2);
            String item_id = resultSet.getString(3);
            String date = resultSet.getString(4);
            String qty = resultSet.getString(5);
            String payment = resultSet.getString(6);

            dto = new OrderDto(od_id, cus_id, item_id, date, qty, payment);
        }

        return dto;
    }

    public List<OrderDto> getAllOrders() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM orders";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<OrderDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String od_id = resultSet.getString(1);
            String cus_id = resultSet.getString(2);
            String item_id = resultSet.getString(3);
            String date = resultSet.getString(4);
            String qty = resultSet.getString(5);
            String payment = resultSet.getString(6);

            var dto = new OrderDto(od_id, cus_id, item_id, date, qty, payment);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public boolean deleteOrder(String orderId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM orders WHERE order_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, orderId);

        return pstm.executeUpdate() > 0;
    }

    public List<OrderDto> loadAllOrders() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM orders";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<OrderDto> odList = new ArrayList<>();

        while (resultSet.next()) {
            odList.add(new OrderDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return odList;
    }




}
