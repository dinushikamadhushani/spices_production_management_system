package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.ijse.model.UserModel;
import lk.ijse.util.Navigation;

import java.io.IOException;

public class LoginPageFormController {


    @FXML
    private PasswordField txtpassword;

    @FXML
    private TextField txtusername;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        if (UserModel.verifyCredential(txtusername.getText(),txtpassword.getText())){
            try {
                Navigation.switchNavigation("dashboardForm.fxml",event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @FXML
    void hypforgotpasswordOnAction(ActionEvent event) {

    }

    @FXML
    void hypsignupOnAction(ActionEvent event) {

    }

}
