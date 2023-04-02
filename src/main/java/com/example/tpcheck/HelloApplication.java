package com.example.tpcheck;

import com.example.tpcheck.UI.Scenes.MainScene;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MainScene main = new MainScene();
        String url = "bg.png";
        try {
            Image backgroundImage = new Image(
                    Objects.requireNonNull(getClass().getResource(url)).toExternalForm()
            );
            BackgroundFill bg = new BackgroundFill(new ImagePattern(backgroundImage),CornerRadii.EMPTY, Insets.EMPTY);
            main.getSt().setBackground(new Background(bg));
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        stage.setTitle("TPCheck - Travel Packing Checklist App");
        stage.setScene(main.getCurrent());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}