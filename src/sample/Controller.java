package sample;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    private MainBrowser browser;

    @FXML
    WebView mainWeb;
    @FXML
    TextField addressBar;
    @FXML
    ToolBar toolBar;
    @FXML
    ProgressBar progressBar;
    @FXML
    Circle circle;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                reload();
            }
        });
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
        browser.go(addressBar.getText());
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

    public void reload() {
        browser.reload();
    }

    public TextField getAddressBar() {
        return addressBar;
    }

    public WebView getMainWeb() {
        return mainWeb;
    }

    public void setBrowser(MainBrowser browser) {
        this.browser = browser;
    }

    public void setMainWeb(String url) {
        browser.setWebView(url, mainWeb, circle);
        mainWeb.setVisible(true);
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }
}
