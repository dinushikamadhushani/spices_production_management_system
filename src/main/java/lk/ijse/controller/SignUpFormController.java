package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dto.UserDto;
import lk.ijse.model.UserModel;
import lk.ijse.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class SignUpFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtemail;


    @FXML
    private PasswordField txtconfirmpassword;

    @FXML
    private TextField txtcreatepassword;

   /* @FXML
    void btnSignupOnAction(ActionEvent event) throws IOException {
       if (UserModel.createaccount(txtemail.getText(),txtcreatepassword.getText(), txtconfirmpassword.getText())) {
           try {
               Navigation.switchNavigation("loginPageForm.fxml", event);
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
       }
    }

    */

    @FXML
    void hypLoginOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("loginPageForm.fxml" , event);
    }

}