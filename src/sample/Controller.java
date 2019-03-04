package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    private MainBrowser browser;

    @FXML
    TextField addressBar;
    @FXML
    WebView web;
    @FXML
    ToolBar toolBar;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addressBar.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    go();
                }
            }
        });

    }


    public void go() {
       browser.go();
    }


    public void goBack() {
       browser.goBack();
    }

    public void goNext() {
        browser.goNext();
    }

    public void setAddress() {
       browser.setAddress();
    }
    public void selectAllText() {
        addressBar.selectAll();
    }

    public TextField getAddressBar() {
        return addressBar;
    }

    public WebView getWeb() {
        return web;
    }

    public void setBrowser(MainBrowser browser){
        this.browser=browser;
    }
}
