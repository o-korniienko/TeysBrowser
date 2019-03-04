package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("web-view.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        MainBrowser browser = new MainBrowser(controller.getWeb(),controller);
        browser.setEngine();
        controller.setBrowser(browser);


        primaryStage.setTitle("MyBrowser");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(475);
        primaryStage.setMinHeight(550);
        primaryStage.setResizable(true);
        primaryStage.show();

        primaryStage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double width = (double) newValue;
                controller.getAddressBar().setPrefWidth(width-223);
            }
        });
    }


    public static void main(String[] args) {

        launch(args);
    }
}
