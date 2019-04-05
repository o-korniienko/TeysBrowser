package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

public class MainBrowser {

    private final String htLink = "http://";
    private String adrsLink;
    private WebView webView;
    private WebEngine engine;
    private WebHistory webHistory;
    private Controller controller;
    private Worker worker;
    private ProgressBar progressBar;
    private Circle circle;

    public MainBrowser(Controller controller) {
        this.controller = controller;
        this.progressBar = controller.getProgressBar();
    }

    public void go(String url) {
        this.adrsLink = url;
        if (adrsLink.contains("http")) {
            engine.load(adrsLink);
            loadOfPage();
        } else {
            engine.load(htLink + adrsLink);
            loadOfPage();
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

    public void setWebView(String url, WebView view, Circle circle) {
        this.webView = view;
        this.engine = webView.getEngine();
        this.circle = circle;
        if (url.contains("http")) {
            go(url);
        } else {
            go(url);
        }
        engine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue,
                                Worker.State newValue) {
                if (newValue == Worker.State.SUCCEEDED) {
                    setAddress();
                } else if (newValue == oldValue) {
                    setAddress();
                }
            }
        });

        this.webHistory = engine.getHistory();
    }

    public void reload() {
        engine.reload();
        loadOfPage();
    }


    public void loadOfPage() {
        engine.getLoadWorker().progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double nw = (double) newValue;
                if (nw > 0 && nw < 1) {
                    showProgressBar();
                    circle.setStroke(new Color(0.1f, 0.1f, 0.1f, 1f));
                    circle.setStrokeWidth(3);
                }

                if (nw == 1) {
                    circle.setStroke(Color.RED);
                    circle.setStrokeWidth(2);
                    progressBar.setVisible(false);
                }
            }
        });
    }

    private void showProgressBar() {
        progressBar.setVisible(true);
        worker = engine.getLoadWorker();
        progressBar.progressProperty().bind(worker.progressProperty());
    }
}
