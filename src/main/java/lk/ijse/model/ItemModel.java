package lk.ijse.model;


import lk.ijse.db.DbConnection;
import lk.ijse.dto.ItemtDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemModel {
    public boolean saveItem(ItemtDto itemtDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO item VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, itemtDto.getId());
        pstm.setString(2, itemtDto.getName());
        pstm.setDouble(3, itemtDto.getUnitPrice());
        pstm.setInt(4, itemtDto.getQtyOnHand());

        return pstm.executeUpdate() > 0;
    }

    public boolean updateItem(ItemtDto itemtDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE item SET item_name = ?, unit_price = ?, qty_on_hand = ? WHERE item_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, itemtDto.getId());
        pstm.setDouble(2, itemtDto.getUnitPrice());
        pstm.setInt(3, itemtDto.getQtyOnHand());
        pstm.setString(4, itemtDto.getName());

        return pstm.executeUpdate() > 0;
    }

    public ItemtDto searchItem(String ItemId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM item WHERE item_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, ItemId);

        ResultSet resultSet = pstm.executeQuery();

        ItemtDto dto = null;

        if(resultSet.next()) {
            dto = new ItemtDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            );
        }
        return dto;
    }

    public boolean deleteItem(String ItemId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM item WHERE item_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, ItemId);

        return pstm.executeUpdate() > 0;
    }

    public List<ItemtDto> loadAllItems() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM item";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<ItemtDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            var dto = new ItemtDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            );

            dtoList.add(dto);
        }

        return dtoList;
    }
}