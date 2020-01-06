package oliverdd;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import oliverdd.controllers.MainLayoutController;
import oliverdd.controllers.RootLayoutController;

import java.io.IOException;

public class MainApp extends Application {
    Stage primaryStage;
    private BorderPane rootLayout;

    public Stage getPrimaryStage(){
        return primaryStage;
    }

    public MainApp(){

    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initRootLayout();
        loadMainLayout();
    }
    private void initRootLayout(){
        primaryStage.setTitle("Weather");
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        // Set Icons
        //primaryStage.getIcons().add(new Image(MainApp.class.getResourceAsStream("/icons/weather.ico")));
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/RootLayout.fxml"));
            rootLayout = loader.load();

            /* Used to move the windows
            rootLayout.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });
            rootLayout.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    primaryStage.setX(event.getScreenX() - xOffset);
                    primaryStage.setY(event.getScreenY() - yOffset);
                }
            });
            */
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout, 900, 500);
            final ObservableList<String> stylesheets = scene.getStylesheets();
            stylesheets.addAll(MainApp.class.getResource("/css/main.css").toExternalForm());
            primaryStage.setScene(scene);

            // (For Alert dialog)Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMainLayout(){
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/MainLayout.fxml"));
            StackPane mainOverView = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(mainOverView);

            // Give the controller access to the main app.
            MainLayoutController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
