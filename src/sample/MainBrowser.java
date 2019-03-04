package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

public class MainBrowser {

    private String htLink = "http://";
    private String adrsLink;
    private WebEngine engine;
    private WebHistory webHistory;
    private WebView web;
    private Controller controller;

    public MainBrowser(WebView web, Controller controller) {
        this.web = web;
        this.controller = controller;
    }

    public void go() {
        adrsLink = controller.getAddressBar().getText();
        if (adrsLink.contains("http")) {
            engine.load(adrsLink);
        } else {
            engine.load(htLink + adrsLink);
        }
    }


    public void goBack() {
        int index = webHistory.getCurrentIndex();
        if (index == 0) {
            return;
        } else {
            webHistory.go(-1);
            setAddress();
        }
    }

    public void goNext() {
        try {
            webHistory.go(1);
            setAddress();
        } catch (IndexOutOfBoundsException e) {
            return;
        }
    }

    public void setAddress() {
        int index = webHistory.getCurrentIndex();
        String current_url = webHistory.getEntries().get(index).getUrl();
        controller.getAddressBar().setText(current_url);
    }

    public WebEngine getEngine() {
        return engine;
    }

    public void setEngine() {
        this.engine = web.getEngine();
        engine.loadContent("<video width='320' height='240'controls='controls'>" +
                "<source src='http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv'/>" +
                "Your browser does not support the video tag." +
                "</video>");
        engine.load(htLink + "google.com.ua");

        engine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                if (newValue == Worker.State.SUCCEEDED) {
                    setAddress();
                } else if (newValue == oldValue) {
                    setAddress();
                }
            }
        });

        this.webHistory=engine.getHistory();
    }
}
