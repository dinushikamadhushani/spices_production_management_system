package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.model.UserModel;
import lk.ijse.util.Navigation;

import java.io.IOException;

public class ForgotPasswordFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtemail;

    @FXML
    private PasswordField txtnewpassword;

    @FXML
    private TextField txtusername;

    @FXML
    void btnResetpasswordOnAction(ActionEvent event) throws IOException{

                Navigation.switchNavigation("loginPageForm.fxml", event);



    }



}
