package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dto.ItemtDto;
import lk.ijse.dto.tm.ItemTm;
import lk.ijse.model.ItemModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ItemFormController {

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private AnchorPane pagingPane;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<ItemTm> tblItem;

    @FXML
    private TextField txtItemId;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtUnitPrice;


    private final ItemModel itemModel = new ItemModel();



    public void initialize() throws SQLException {
        setCellValueFactory();
        LoadAllItems();
        setListener();
    }

    private void setCellValueFactory() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    void LoadAllItems()  {
        try {
            List<ItemtDto> dtoList = itemModel.loadAllItems();

            ObservableList<ItemTm> obList = FXCollections.observableArrayList();

            for(ItemtDto dto : dtoList) {
                Button btn = new Button("remove");
                btn.setCursor(Cursor.HAND);

                btn.setOnAction((e) -> {
                    ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                    if(type.orElse(no) == yes) {
                        int selectedIndex = tblItem.getSelectionModel().getSelectedIndex();
                        String itemId = (String) colItemId.getCellData(selectedIndex);

                        deleteItem(itemId);   //delete item from the database

                        obList.remove(selectedIndex);   //delete item from the JFX-Table
                        tblItem.refresh();
                    }
                });

                var tm = new ItemTm(
                        dto.getItemId(),
                        dto.getItemName(),
                        dto.getUnitPrice(),
                        dto.getQtyOnHand(),
                        btn
                );
                obList.add(tm);
            }
            tblItem.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteItem(String ItemId) {
        try {
            boolean isDeleted = itemModel.deleteItem(ItemId);
            if(isDeleted)
                new Alert(Alert.AlertType.CONFIRMATION, "item deleted!").show();
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
        }
    }


    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
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
    void btnSaveOnAction(ActionEvent event) {
        String itemId = txtItemId.getText();
        String itemName = txtItemName.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());

        var itemtDto = new ItemtDto(itemId, itemName, unitPrice, qtyOnHand);

        try {
            boolean isSaved = itemModel.saveItem(itemtDto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "item saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String itemId = txtItemId.getText();
        String itemName = txtItemName.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());

//        var model = new ItemModel();
        try {
            boolean isUpdated = itemModel.updateItem(new ItemtDto(itemId, itemName, unitPrice, qtyOnHand));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "item updated").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String ItemId = txtItemId.getText();

        try {
            boolean isDeleted = itemModel.deleteItem(ItemId);

            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "item deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }




    @FXML
    void productIdSearchOnAction(ActionEvent event) {


    }

    private void setListener() {
        tblItem.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    var dto = new ItemtDto(

                    );
                    setFields(dto);
                });
    }

    private void setFields(ItemtDto dto) {
        txtItemId.setText(dto.getItemId());
        txtItemName.setText(dto.getItemName());
        txtUnitPrice.setText(String.valueOf(dto.getUnitPrice()));
        txtQtyOnHand.setText(String.valueOf(dto.getQtyOnHand()));
    }

    private void clearFields() {
        txtItemId.setText("");
        txtItemName.setText("");
        txtUnitPrice.setText("");
        txtQtyOnHand.setText("");
    }


    public void itemIdSearchOnAction(ActionEvent actionEvent) {
        String code = txtItemId.getText();

        try {
            ItemtDto dto = itemModel.searchItem(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "item not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
}
