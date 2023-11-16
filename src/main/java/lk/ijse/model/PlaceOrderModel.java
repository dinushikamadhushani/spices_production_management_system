package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.ItemtDto;
import lk.ijse.dto.PlaceOrderDto;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderModel {
    private final OrderModel orderModel = new OrderModel();
    private final ItemModel itemModel = new ItemModel();
    private final OrderDetailModel orderDetailModel = new OrderDetailModel();

    public boolean placeOrder(PlaceOrderDto pDto) throws SQLException {
        boolean result = false;
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = OrderModel.saveOrder(pDto.getOrderId(), pDto.getCustomerId(), pDto.getDate());
            if (isOrderSaved) {
                boolean isUpdated = itemModel.updateItem((ItemtDto) pDto.getTmList());
                if(isUpdated) {
                    boolean isOrderDetailSaved = orderDetailModel.saveOrderDetail(pDto.getOrderId(), pDto.getTmList());
                    if(isOrderDetailSaved) {
                        connection.commit();
                        result = true;
                    }
                }
            }
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return result;
    }
}