package oliverdd.controllers;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXSpinner;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import okhttp3.*;
import oliverdd.MainApp;
import oliverdd.utils.Tools;
import oliverdd.utils.beans.WeaData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainLayoutController {
    @FXML
    JFXButton cityLabel;
    @FXML
    JFXButton updateDateLabel;
    @FXML
    JFXButton weaLabel;
    @FXML
    ImageView weaImage;
    @FXML
    JFXButton day0;
    @FXML
    JFXButton day1;
    @FXML
    JFXButton day2;
    @FXML
    JFXButton day3;
    @FXML
    JFXButton day4;
    @FXML
    JFXButton day5;
    @FXML
    JFXButton day6;
    @FXML
    JFXButton weekLabel;
    @FXML
    JFXButton dateLabel;
    @FXML
    JFXSpinner jfxSpinner;
    @FXML
    AnchorPane whiteAnchorPane;
    MainApp mainApp;
    Tools tools;
    private final OkHttpClient client = new OkHttpClient();
    static String url = "https://www.tianqiapi.com/api/?appid=19939194&appsecret=C1CJfQlS&version=v1";
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        tools = new Tools();
        whiteAnchorPane.setVisible(false);
        jfxSpinner.setVisible(true);
    }
    public MainLayoutController(){

    }
    @FXML
    private void initialize() {
        getData();
    }

    private void getData(){
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Platform.runLater(new Runnable() { // Run in the UI thread
                    @Override
                    public void run() {
                        jfxSpinner.setVisible(false);
                        whiteAnchorPane.setVisible(false);
                        JFXAlert alert = new JFXAlert((Stage)mainApp.getPrimaryStage());
                        //Alert alert = new Alert(alertType);
                        alert.initModality(Modality.APPLICATION_MODAL);
                        alert.setOverlayClose(false);
                        JFXDialogLayout layout = new JFXDialogLayout();
                        layout.setHeading(new Label("Failed to get the Weather data..."));
                        layout.setBody(new Label("Please check you internet first...\n"
                                + "Details:\n" + e.getMessage()));
                        JFXButton stayButton = new JFXButton("Try again");
                        stayButton.getStyleClass().add("dialog-accept");
                        // TODO: failed to load data -> try again
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
                });
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    // Response code check
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                    // Deal with data
                    Gson gson = new Gson();
                    WeaData weaData = gson.fromJson(responseBody.string(), WeaData.class);
                    Platform.runLater(new Runnable() { // Run in the UI thread
                        @Override
                        public void run() {
                            jfxSpinner.setVisible(false);
                            whiteAnchorPane.setVisible(false);
                            // update the ui:load the data on the left
                            cityLabel.setText(weaData.getCity());
                            updateDateLabel.setText(weaData.getUpdate_time());
                            // update the ui:load the data on the center
                            weaLabel.setText(weaData.getData()[0].getWea());
                            weekLabel.setText(weaData.getData()[0].getWeek());
                            dateLabel.setText(weaData.getData()[0].getDate());
                            //System.out.println("DDD" + weaData.getData()[0].getWea_img());
                            weaImage.setImage(new Image("/img/" + weaData.getData()[0].getWea_img() + ".png"));
                            // update the ui:load the data on the right
                            day0.setText(weaData.getData()[0].getDay());
                            day1.setText(weaData.getData()[1].getDay());
                            day2.setText(weaData.getData()[2].getDay());
                            day3.setText(weaData.getData()[3].getDay());
                            day4.setText(weaData.getData()[4].getDay());
                            day5.setText(weaData.getData()[5].getDay());
                            day6.setText(weaData.getData()[6].getDay());
                        }
                    });
                }
            }
        });
    }
}
