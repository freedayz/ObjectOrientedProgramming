package com.example.tpcheck.UI.Scenes;

import com.example.tpcheck.Core.LocalDB.LocalDB;
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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class Registration {

    private PasswordField passwordField;
    private TextField usernameField;
    private PasswordField passwordAgainField;
    private Button loginButton;
    private Pane pane;
    private StackPane st;
    private Scene current;

    public Registration() {
        this.st = new StackPane();
        initializeScene();
        initializeElements();
        eventReactor();
    }

    private void initializeScene() {
        setPane(new Pane());
        setUsernameField(new TextField());
        setPasswordField(new PasswordField());
        setPasswordAgainField(new PasswordField());
        getPane().setStyle("-fx-background-color: rgba(146,136,134,0.93);");
        getPane().setPrefSize(20, 20);
        getPane().setMaxWidth(Settings.getInstance().getMaxWidth());
        getPane().setMaxHeight(Settings.getInstance().getMaxHeight()-200);
        getSt().getChildren().add(getPane());
        getSt().setAlignment(Pos.CENTER);
        setCurrent(new Scene(getSt(), Settings.getInstance().getMaxWidth(),
                Settings.getInstance().getMaxHeight()));
        getCurrent().getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        String url = "bg.png";
        try {
            Image backgroundImage = new Image(
                    Objects.requireNonNull(getClass().getResource(url)).toExternalForm()
            );
            BackgroundFill bg = new BackgroundFill(new ImagePattern(backgroundImage),CornerRadii.EMPTY, Insets.EMPTY);
            getSt().setBackground(new Background(bg));
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void eventReactor() {
        getCurrent().widthProperty().addListener((obs, oldVal, newVal) -> {
            double newWidth = (double) newVal;
            getPane().setMaxWidth(newWidth);
        });

        getLoginButton().setOnAction(event -> {
            if (checkInputs() == 0) {
                System.out.println("register_success");
                try {
                    LocalDB.insert(
                            getUsernameField().getText(),
                            getPasswordField().getText()
                    );
                } catch (SQLException | ClassNotFoundException | IOException e) {
                    throw new RuntimeException(e);
                }
                Dashboard newScene = new Dashboard();
                Stage current = (Stage)getLoginButton().getScene().getWindow();
                current.setScene(newScene.getDashboardScene());
            }
            else {
                System.out.println("register_error");
            }
        });
    }

    private int checkInputs() {
        int err = 0;
        if (!getPasswordField().getText().equals(getPasswordAgainField().getText())) {
            err++;
        }
        if (getPasswordField().getLength() < 3 || getPasswordAgainField().getLength() < 3) {
            err++;
        }
        if (getUsernameField().getLength() < 3 || getUsernameField().getLength() > 25) {
            err++;
        }
        return err;
    }

    private void initializeElements() {
        VBox vbox = new VBox(30); // 10 is the vertical spacing between child nodes
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(
                createHeader("Create your account."),
                createLine(),
                createUsernameInput("Username: "),
                createPasswordInput("Password: ", Settings.getInstance().getMaxWidth()-120),
                createPasswordAgainInput("Password again: ", Settings.getInstance().getMaxWidth()-160),
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

    private HBox createPasswordInput(String label,int width) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        Text desc = new Text(label);
        desc.getStyleClass().add("h5");
        desc.setFill(Color.rgb(64,60,59));
        getPasswordField().setPrefSize(width,30);
        getPasswordField().setPromptText("Enter your password");
        getPasswordField().setStyle("-fx-border-color: #675c5a;");
        hBox.getChildren().addAll(desc,getPasswordField());
        return hBox;
    }

    private HBox createPasswordAgainInput(String label,int width) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        Text desc = new Text(label);
        desc.getStyleClass().add("h5");
        desc.setFill(Color.rgb(64,60,59));
        getPasswordAgainField().setPrefSize(width,30);
        getPasswordAgainField().setPromptText("Enter your password");
        getPasswordAgainField().setStyle("-fx-border-color: #675c5a;");
        hBox.getChildren().addAll(desc,getPasswordAgainField());
        return hBox;
    }

    private HBox createUsernameInput(String label) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        Text desc = new Text(label);
        desc.setFill(Color.rgb(64,60,59));
        desc.getStyleClass().add("h5");
        getUsernameField().setPrefSize(Settings.getInstance().getMaxWidth()-120,30);
        getUsernameField().setPromptText("Enter your username");
        getUsernameField().setStyle("-fx-border-color: #675c5a;");
        hBox.getChildren().addAll(desc,getUsernameField());
        return hBox;
    }

    private HBox createButton() {
        HBox hbox = new HBox();
        hbox.setSpacing(30);
        hbox.setAlignment(Pos.CENTER);
        setLoginButton(new Button("Sign Up"));
        getLoginButton().setStyle("-fx-background-color: #675c5a;" +
                "-fx-border-color: #675c5a;");
        getLoginButton().getStyleClass().addAll("btn-primary", "btn-lg");
        hbox.getChildren().add(getLoginButton());
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

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(PasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(TextField usernameField) {
        this.usernameField = usernameField;
    }

    public PasswordField getPasswordAgainField() {
        return passwordAgainField;
    }

    public void setPasswordAgainField(PasswordField passwordAgainField) {
        this.passwordAgainField = passwordAgainField;
    }
}
