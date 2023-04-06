package com.example.tpcheck.UI.Scenes;

import com.example.tpcheck.UI.Settings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.util.Objects;

public class MainScene {
    private Button loginButton,registerButton;
    private Pane pane;
    private StackPane st;
    private Scene current;

    public MainScene() {
        this.st = new StackPane();
        initializeScene();
        initializeElements();
        eventReactor();
    }

    private void initializeScene() {
        setPane(new Pane());
        getPane().setStyle("-fx-background-color: rgba(146,136,134,0.93);");
        getPane().setPrefSize(20, 20);
        getPane().setMaxWidth(Settings.getInstance().getMaxWidth());
        getPane().setMaxHeight(Settings.getInstance().getMaxHeight()/2.0);
        getSt().getChildren().add(getPane());
        getSt().setAlignment(Pos.CENTER);
        setCurrent(new Scene(getSt(), Settings.getInstance().getMaxWidth(),
                Settings.getInstance().getMaxHeight()));
        getCurrent().getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
    }

    private void eventReactor() {
        getCurrent().widthProperty().addListener((obs, oldVal, newVal) -> {
            double newWidth = (double) newVal;
            getPane().setMaxWidth(newWidth);
        });

        getLoginButton().setOnAction(event -> {
            Dashboard newScene = new Dashboard();
            Stage current = (Stage)getLoginButton().getScene().getWindow();
            current.setScene(newScene.getDashboardScene());
        });

        getRegisterButton().setOnAction(event -> {
            Registration newScene = new Registration();
            Stage current = (Stage)getRegisterButton().getScene().getWindow();
            current.setScene(newScene.getCurrent());
        });
    }

    private void initializeElements() {
        VBox vbox = new VBox(30); // 10 is the vertical spacing between child nodes
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(
                createHeader("Please log in to continue"),
                createLine(),
                createUsernameInput("Username: "),
                createPasswordInput("Password: "),
                createButton()
        );

        getPane().getChildren().addAll(vbox);

    }

    private Line createLine() {
        Line line = new Line(
                0,
                getPane().getHeight(),
                Settings.getInstance().getMaxWidth()-40,
                getPane().getHeight()
        );
        line.setStroke(Color.rgb(81,76,74));
        return line;
    }

    private Label createHeader(String text) {
        Label header = new Label(text.toUpperCase());
        header.setTextFill(Color.rgb(81,76,74));
        header.getStyleClass().addAll("h3","b");
        return header;
    }

    private HBox createPasswordInput(String label) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        Text desc = new Text(label);
        desc.getStyleClass().add("h5");
        desc.setFill(Color.rgb(64,60,59));
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefSize(Settings.getInstance().getMaxWidth()-120,30);
        passwordField.setPromptText("Enter your password");
        passwordField.setStyle("-fx-border-color: #675c5a;");
        hBox.getChildren().addAll(desc,passwordField);
        return hBox;
    }

    private HBox createUsernameInput(String label) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        Text desc = new Text(label);
        desc.setFill(Color.rgb(64,60,59));
        desc.getStyleClass().add("h5");
        TextField usernameInput = new TextField();
        usernameInput.setPrefSize(Settings.getInstance().getMaxWidth()-120,30);
        usernameInput.setPromptText("Enter your username");
        usernameInput.setStyle("-fx-border-color: #675c5a;");
        hBox.getChildren().addAll(desc,usernameInput);
        return hBox;
    }

    private HBox createButton() {
        HBox hbox = new HBox();
        hbox.setSpacing(30);
        hbox.setAlignment(Pos.CENTER);
        setLoginButton(new Button("Log In"));
        getLoginButton().setStyle("-fx-background-color: #675c5a;" +
                "-fx-border-color: #675c5a;");
        getLoginButton().getStyleClass().addAll("btn-primary", "btn-lg");

        setRegisterButton(new Button("Don't have account yet?"));
        getRegisterButton().setStyle("-fx-background-color: transparent;" +
                "-fx-border-color: #5a504e;");
        getRegisterButton().setTextFill(Color.rgb(90,80,78));
        getRegisterButton().getStyleClass().add("btn-sm");
        hbox.getChildren().addAll(getLoginButton(),getRegisterButton());
        return hbox;
    }

    public Scene getCurrent() {
        return current;
    }

    private void setCurrent(Scene current) {
        this.current = current;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public StackPane getSt() {
        return st;
    }

    public void setSt(StackPane st) {
        this.st = st;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(Button loginButton) {
        this.loginButton = loginButton;
    }

    private Button getRegisterButton() {
        return registerButton;
    }

    private void setRegisterButton(Button registerButton) {
        this.registerButton = registerButton;
    }
}
