package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dto.OrderDto;
import lk.ijse.dto.tm.OrderTm;
import lk.ijse.model.OrderModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OrdersFormController {

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colOderId;

    @FXML
    private TableColumn<?, ?> colPayment;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<OrderTm> tblOrders;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtItemId;

    @FXML
    private TextField txtOderId;

    @FXML
    private TextField txtPayment;

    @FXML
    private TextField txtQty;

    public void initialize() {
        setCellValueFactory();
        loadAllOrders();
    }

    private void setCellValueFactory() {
        colOderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
    }

    private void loadAllOrders() {
        var model = new OrderModel();

        ObservableList<OrderTm> obList = FXCollections.observableArrayList();

        try {
            List<OrderDto> dtoList = model.getAllOrders();

            for(OrderDto dto : dtoList) {
                obList.add(
                        new OrderTm(
                                dto.getOrderId(),
                                dto.getCustomerId(),
                                dto.getItemId(),
                                dto.getQty(),
                                dto.getDate(),
                                dto.getPayment()
                        )
                );
            }

            tblOrders.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard");
        stage.centerOnScreen();

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String orderId = txtOderId.getText();

        var orderModel = new OrderModel();
        try {
            boolean isDeleted = orderModel.deleteOrder(orderId);

            if(isDeleted) {
                tblOrders.refresh();
                new Alert(Alert.AlertType.CONFIRMATION, "order deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String orderId = txtOderId.getText();
        String customerId = txtCustomerId.getText();
        String itemId = txtItemId.getText();
        String qty = txtQty.getText();
        String date = txtDate.getText();
        String payment = txtPayment.getText();

        var dto = new OrderDto(orderId, customerId, itemId, qty, date, payment);

        var model = new OrderModel();
        try {
            boolean isSaved = model.saveOrder(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String orderId = txtOderId.getText();
        String customerId = txtCustomerId.getText();
        String itemId = txtItemId.getText();
        String qty = txtQty.getText();
        String date = txtDate.getText();
        String payment = txtPayment.getText();

        var dto = new OrderDto(orderId, customerId, itemId, qty, date, payment);

        var model = new OrderModel();
        try {
            boolean isUpdated = model.updateOrder(dto);
            System.out.println(isUpdated);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }




    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String orderId = txtOderId.getText();

        var model = new OrderModel();
        try {
            OrderDto dto = model.searchOrder(orderId);

            if(dto != null) {
                fillFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "order not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void fillFields(OrderDto dto) {
        txtOderId.setText(dto.getOrderId());
        txtCustomerId.setText(dto.getCustomerId());
        txtItemId.setText(dto.getItemId());
        txtDate.setText(dto.getDate());
        txtQty.setText(dto.getQty());
        txtPayment.setText(dto.getPayment());
    }

    void clearFields() {
        txtOderId.setText("");
        txtCustomerId.setText("");
        txtItemId.setText("");
        txtDate.setText("");
        txtQty.setText("");
        txtPayment.setText("");
    }

}
