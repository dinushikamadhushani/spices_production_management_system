package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dto.*;
import lk.ijse.dto.tm.CartTm;
import lk.ijse.dto.tm.MaterialCartTm;
import lk.ijse.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MaterialDetailFormController {

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXComboBox<?> cmbMaterialId;

    @FXML
    private JFXComboBox<?> cmbSupplierId;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colMaterialId;

    @FXML
    private TableColumn<?, ?> colMaterialName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblMaterialName;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblSupplierName;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private AnchorPane root;


    @FXML
    private TableView<CartTm> tblMaterialCart;

    @FXML
    private TextField txtQty;


    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {

    }

    @FXML
    void btnNewSupplierOnAction(ActionEvent event) throws IOException {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {


    }

    @FXML
    void cmbSupplierOnAction(ActionEvent event) throws SQLException {


    }

    @FXML
    void cmbMaterialIdOnAction(ActionEvent event) {


    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {

    }

}
