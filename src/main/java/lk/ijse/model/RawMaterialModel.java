package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.RawMaterialDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RawMaterialModel {
    public boolean saveRawMaterial(final RawMaterialDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO raw_material VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getRawMaterialId());
        pstm.setString(2, dto.getRawMaterialName());
        pstm.setString(3, dto.getQtyOnStock());


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public boolean updateRawMaterial(final RawMaterialDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE raw_material SET material_name = ?, qty_on_stock = ?, WHERE raw_material_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getRawMaterialId());
        pstm.setString(2, dto.getRawMaterialName());
        pstm.setString(3, dto.getQtyOnStock());


        return pstm.executeUpdate() > 0;
    }

    public RawMaterialDto searchRawMaterial(String rawMaterialId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM raw_material WHERE raw_material_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, rawMaterialId);

        ResultSet resultSet = pstm.executeQuery();

        RawMaterialDto dto = null;

        if(resultSet.next()) {
            String raw_id = resultSet.getString(1);
            String raw_name = resultSet.getString(2);
            String qty = resultSet.getString(3);


            dto = new RawMaterialDto(raw_id, raw_name, qty);
        }

        return dto;
    }

    public List<RawMaterialDto> getAllMaterials() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM raw_material";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<RawMaterialDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String raw_id = resultSet.getString(1);
            String raw_name = resultSet.getString(2);
            String qty = resultSet.getString(3);


            var dto = new RawMaterialDto(raw_id, raw_name, qty);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public boolean deleteRawMaterial(String rawMaterialId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM raw_material WHERE raw_material_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, rawMaterialId);

        return pstm.executeUpdate() > 0;
    }

    public List<RawMaterialDto> loadAllMaterials() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM raw_material";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<RawMaterialDto> rawList = new ArrayList<>();

        while (resultSet.next()) {
            rawList.add(new RawMaterialDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)

            ));
        }
        return rawList;
    }

}
