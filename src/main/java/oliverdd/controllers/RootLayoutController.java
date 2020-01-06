package oliverdd.controllers;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import oliverdd.MainApp;
import oliverdd.utils.Tools;

import java.util.ArrayList;
import java.util.List;

public class RootLayoutController {
    // Used for mouse drag
    private double xOffset = 0;
    private double yOffset = 0;
    // Reference to the main application
    private MainApp mainApp;
    // The tools of this class
    private Tools tools;
    @FXML
    MenuBar menuBar;
    /*
     * called by the main application to give a reference back to itself.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        tools = new Tools();
    }
    @FXML
    private void initialize() {
        menuBar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        menuBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mainApp.getPrimaryStage().setX(event.getScreenX() - xOffset);
                mainApp.getPrimaryStage().setY(event.getScreenY() - yOffset);
            }
        });
    }
    @FXML
    private void handleRefresh(){
        mainApp.loadMainLayout();

    }
    @FXML
    private void handleExit(){
        JFXAlert alert = new JFXAlert((Stage)mainApp.getPrimaryStage());
        //Alert alert = new Alert(alertType);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setOverlayClose(false);
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Label("Are you sure to quit?"));
        layout.setBody(new Label("You have clicked the quit button.\n"
                                + "So this confirmation pops up."));
        JFXButton stayButton = new JFXButton("Stay");
        stayButton.getStyleClass().add("dialog-accept");
        stayButton.setOnAction(event -> alert.hideWithAnimation());
        JFXButton quitButton = new JFXButton("Quit");
        quitButton.getStyleClass().add("dialog-reject");
        quitButton.setOnAction(event -> System.exit(0));
        List<Node> butList = new ArrayList<>();
        butList.add(stayButton);
        butList.add(quitButton);
        layout.setActions(butList);
        alert.setContent(layout);
        alert.show();
    }
}
