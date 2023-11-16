package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.util.Navigation;


import java.io.IOException;

public class DashboardFormController {

    @FXML
    private AnchorPane pagingPane;

    @FXML
    void btncustomerOnAction(ActionEvent event) throws IOException {
        Navigation.ChangePane(pagingPane,"customerForm.fxml");

    }

    @FXML
    void btndeliveryOnAction(ActionEvent event) throws IOException {
        Navigation.ChangePane(pagingPane,"deliveryForm.fxml");


    }

    @FXML
    void btnemployeeOnAction(ActionEvent event) throws IOException {
        Navigation.ChangePane(pagingPane,"employeeForm.fxml");


    }

    @FXML
    void btnhomeOnAction(ActionEvent event) throws IOException {
        Navigation.ChangePane(pagingPane,"homePageForm.fxml");

    }

    @FXML
    void btnhomeOnMouseExited(MouseEvent event) {

    }

    @FXML
    void btnlogoutOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("loginPageForm.fxml" , event);

    }

    @FXML
    void btnordersOnAction(ActionEvent event) throws IOException {
        Navigation.ChangePane(pagingPane,"placeOrderForm.fxml");


    }

    @FXML
    void btnproductOnAction(ActionEvent event) throws IOException {
        Navigation.ChangePane(pagingPane,"itemForm.fxml");


    }

    @FXML
    void btnrawmaterialOnAction(ActionEvent event) throws IOException {
        Navigation.ChangePane(pagingPane,"rawMaterialForm.fxml");


    }

    @FXML
    void btnsalaryOnAction(ActionEvent event) throws IOException {


    }

    @FXML
    void btnsupplierOnAction(ActionEvent event) throws IOException {
        Navigation.ChangePane(pagingPane,"supplierForm.fxml");


    }


}
