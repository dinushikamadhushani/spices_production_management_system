package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.util.Navigation;
import lk.ijse.util.Navigation;

import java.io.IOException;

public class DashboardFormController {

    @FXML
    private AnchorPane pagingPane;

    @FXML
    void btncustomerOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane,"customerForm.fxml","dASHBORD");

    }

    @FXML
    void btndeliveryOnAction(ActionEvent event) {

    }

    @FXML
    void btnemployeeOnAction(ActionEvent event) {

    }

    @FXML
    void btnhomeOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(pagingPane,"homePageForm.fxml","Dashbord");

    }

    @FXML
    void btnhomeOnMouseExited(MouseEvent event) {

    }

    @FXML
    void btnlogoutOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("loginPageForm.fxml" , event);

    }

    @FXML
    void btnordersOnAction(ActionEvent event) {

    }

    @FXML
    void btnproductOnAction(ActionEvent event) {

    }

    @FXML
    void btnrawmaterialOnAction(ActionEvent event) {

    }

    @FXML
    void btnsalaryOnAction(ActionEvent event) {

    }

    @FXML
    void btnsupplierOnAction(ActionEvent event) {

    }

}
