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
import lk.ijse.dto.RawMaterialDto;
import lk.ijse.dto.tm.RawMaterialTm;
import lk.ijse.model.RawMaterialModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RawMaterialFormController {

    @FXML
    private TableColumn<?, ?> colMaterialName;

    @FXML
    private TableColumn<?, ?> colQtyOnStock;

    @FXML
    private TableColumn<?, ?> colRawId;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<RawMaterialTm> tblRawMaterial;

    @FXML
    private TextField txtQtyOnStock;

    @FXML
    private TextField txtRawId;

    @FXML
    private TextField txtRawName;

    public void initialize() {
        setCellValueFactory();
        loadAllMaterials();
    }

    private void setCellValueFactory() {
        colRawId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMaterialName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQtyOnStock.setCellValueFactory(new PropertyValueFactory<>("qty"));

    }

    private void loadAllMaterials() {
        var model = new RawMaterialModel();

        ObservableList<RawMaterialTm> obList = FXCollections.observableArrayList();

        try {
            List<RawMaterialDto> dtoList = model.getAllMaterials();

            for(RawMaterialDto dto : dtoList) {
                obList.add(
                        new RawMaterialTm(
                                dto.getRawMaterialId(),
                                dto.getRawMaterialName(),
                                dto.getQtyOnStock()

                        )
                );
            }

            tblRawMaterial.setItems(obList);
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
        String rawId = txtRawId.getText();

        var rawMaterialModel = new RawMaterialModel();
        try {
            boolean isDeleted = rawMaterialModel.deleteRawMaterial(rawId);

            if(isDeleted) {
                tblRawMaterial.refresh();
                new Alert(Alert.AlertType.CONFIRMATION, "material deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtRawId.getText();
        String name = txtRawName.getText();
        String qtyOnStock = txtQtyOnStock.getText();


        var dto = new RawMaterialDto(id, name, qtyOnStock);

        var model = new RawMaterialModel();
        try {
            boolean isSaved = model.saveRawMaterial(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "material saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtRawId.getText();
        String name = txtRawName.getText();
        String qtyOnStock = txtQtyOnStock.getText();


        var dto = new RawMaterialDto(id, name, qtyOnStock);

        var model = new RawMaterialModel();
        try {
            boolean isUpdated = model.updateRawMaterial(dto);
            System.out.println(isUpdated);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "material updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void txtRawIdSearchOnAction(ActionEvent event) {
        String rawId = txtRawId.getText();

        var model = new RawMaterialModel();
        try {
            RawMaterialDto dto = model.searchRawMaterial(rawId);

            if(dto != null) {
                fillFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "material not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void fillFields(RawMaterialDto dto) {
        txtRawId.setText(dto.getRawMaterialId());
        txtRawName.setText(dto.getRawMaterialName());
        txtQtyOnStock.setText(dto.getQtyOnStock());

    }

    void clearFields() {
        txtRawId.setText("");
        txtRawName.setText("");
        txtQtyOnStock.setText("");

    }

}
