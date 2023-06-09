package com.example.tpcheck.UI.Scenes;

import com.example.tpcheck.UI.Settings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class Dashboard {
    private Pane pane;
    private Scene dashboardScene;
    private StackPane stackPane;

    public Dashboard() {
        setStackPane(new StackPane());
        initializeScene();
        initializeElements();
        eventReactor();
    }

    private void initializeScene() {
        setPane(new Pane());
        getPane().setStyle("-fx-background-color: #fff;"); //#817371
        getPane().setMaxWidth(Settings.getInstance().getMaxWidth());
        getPane().setMaxHeight(Settings.getInstance().getMaxHeight());
        getStackPane().getChildren().add(getPane());
        getStackPane().setAlignment(Pos.CENTER);
        setDashboardScene(new Scene(getStackPane(), Settings.getInstance().getMaxWidth(),
                Settings.getInstance().getMaxHeight()));
        getDashboardScene().getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
    }

    private void eventReactor() {
        getDashboardScene().widthProperty().addListener((obs, oldVal, newVal) -> {
            double newWidth = (double) newVal;
            getPane().setMaxWidth(newWidth);
        });

        getDashboardScene().heightProperty().addListener((obs, oldVal, newVal) -> {
            double newHeight = (double) newVal;
            getPane().setMaxHeight(newHeight);
        });
    }

    private void initializeElements() {
        HBox hbox = new HBox(30); // 10 is the vertical spacing between child nodes
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(20));
        Button b = new Button("Test");
        Button b2 = new Button("test2");
        hbox.getChildren().addAll(
                createBtnLayout("Add new", "btn-success"),
                createBtnLayout("Edit", "btn-warning"),
                createBtnLayout("Delete", "btn-danger")
        );

        getPane().getChildren().addAll(hbox);
    }

    private Button createBtnLayout(String innerHTML, String type) {
        Button b = new Button(innerHTML);
        b.getStyleClass().addAll(type);
        return b;
    }

    public Scene getDashboardScene() {
        return dashboardScene;
    }

    public void setDashboardScene(Scene dashboardScene) {
        this.dashboardScene = dashboardScene;
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }
}
