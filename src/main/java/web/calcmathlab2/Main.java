package web.calcmathlab2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    static final Label HEADER_MESSAGE = new Label();
    static final Button TO_MAIN_BUTTON = new Button("На главную");
    static final Button TO_CHOOSE_EQUATION_BUTTON = new Button("Выбрать уравнение");
    static final Button TO_CHOOSE_SYSTEM_BUTTON = new Button("Выбрать систему уравнений");
    static final HBox MAIN_BUTTONS = new HBox(TO_CHOOSE_EQUATION_BUTTON, TO_CHOOSE_SYSTEM_BUTTON);
    static final Button CHOOSE_EQUATION_1_BUTTON = new Button("4.45x^3 + 7.81x^2 - 9.62x - 8.17");
    static final Button CHOOSE_EQUATION_2_BUTTON = new Button("-2.95x^2 - 10.28x - 3.23");
    static final Button CHOOSE_EQUATION_3_BUTTON = new Button("cos(x) + x^3 - 0.99");
    static final VBox CHOOSE_EQUATION_BUTTONS = new VBox(CHOOSE_EQUATION_1_BUTTON, CHOOSE_EQUATION_2_BUTTON, CHOOSE_EQUATION_3_BUTTON);

    static final TextField LEFT_BORDER_INPUT = new TextField();
    static final TextField RIGHT_BORDER_INPUT = new TextField();
    static final TextField ACCURACY_INPUT = new TextField();
    static final Button SOLVE_EQUATION_1_CHOOSE_FILE_BUTTON = new Button("Ввести значения через файл");
    static final Button SOLVE_EQUATION_2_CHOOSE_FILE_BUTTON = new Button("Ввести значения через файл");
    static final Button SOLVE_EQUATION_3_CHOOSE_FILE_BUTTON = new Button("Ввести значения через файл");
    static final Button SOLVE_SYSTEM_1_CHOOSE_FILE_BUTTON = new Button("Ввести значения через файл");
    static final Button SOLVE_SYSTEM_2_CHOOSE_FILE_BUTTON = new Button("Ввести значения через файл");
    static final Label RESPONSE_MESSAGE = new Label();
    static final Button PLOT_EQUATION_1_BUTTON = new Button("Показать график");
    static final Button PLOT_EQUATION_2_BUTTON = new Button("Показать график");
    static final Button PLOT_EQUATION_3_BUTTON = new Button("Показать график");
    static final Button SOLVE_EQUATION_1_BUTTON = new Button("Решить");
    static final Button SOLVE_EQUATION_2_BUTTON = new Button("Решить");
    static final Button SOLVE_EQUATION_3_BUTTON = new Button("Решить");
    static final TextField X_INITIAL_INPUT = new TextField();
    static final TextField Y_INITIAL_INPUT = new TextField();
    static final Button PLOT_SYSTEM_1_BUTTON = new Button("Показать график");
    static final Button PLOT_SYSTEM_2_BUTTON = new Button("Показать график");
    static final Button SOLVE_SYSTEM_1_BUTTON = new Button("Решить");
    static final Button SOLVE_SYSTEM_2_BUTTON = new Button("Решить");
    static final Button SOLVE_SYSTEM_3_BUTTON = new Button("Решить");

    static final Button CHOOSE_SYSTEM_1_BUTTON = new Button("tan(x*y + 0.2) = x^2\nx^2 + 2y^2 = 1");
    static final Button CHOOSE_SYSTEM_2_BUTTON = new Button("sin(x+y) - 1.2x = 0.2\nx^2 + 2y^2 = 1");
    static final VBox CHOOSE_SYSTEM_BUTTONS = new VBox(CHOOSE_SYSTEM_1_BUTTON, CHOOSE_SYSTEM_2_BUTTON);
    static final VBox ROOT_LAYOUT = new VBox();
    @Override
    public void start(Stage stage) {
        InputHandler inputHandler = new InputHandler();

        TO_MAIN_BUTTON.setOnAction(inputHandler);
        TO_CHOOSE_EQUATION_BUTTON.setOnAction(inputHandler);
        TO_CHOOSE_SYSTEM_BUTTON.setOnAction(inputHandler);
        CHOOSE_EQUATION_1_BUTTON.setOnAction(inputHandler);
        CHOOSE_EQUATION_2_BUTTON.setOnAction(inputHandler);
        CHOOSE_EQUATION_3_BUTTON.setOnAction(inputHandler);
        PLOT_EQUATION_1_BUTTON.setOnAction(inputHandler);
        PLOT_EQUATION_2_BUTTON.setOnAction(inputHandler);
        PLOT_EQUATION_3_BUTTON.setOnAction(inputHandler);
        CHOOSE_EQUATION_2_BUTTON.setOnAction(inputHandler);
        CHOOSE_EQUATION_3_BUTTON.setOnAction(inputHandler);
        SOLVE_EQUATION_1_BUTTON.setOnAction(inputHandler);
        SOLVE_EQUATION_2_BUTTON.setOnAction(inputHandler);
        SOLVE_EQUATION_3_BUTTON.setOnAction(inputHandler);
        CHOOSE_SYSTEM_1_BUTTON.setOnAction(inputHandler);
        CHOOSE_SYSTEM_2_BUTTON.setOnAction(inputHandler);
        SOLVE_SYSTEM_1_BUTTON.setOnAction(inputHandler);
        SOLVE_SYSTEM_2_BUTTON.setOnAction(inputHandler);
        SOLVE_SYSTEM_3_BUTTON.setOnAction(inputHandler);
        PLOT_SYSTEM_1_BUTTON.setOnAction(inputHandler);
        PLOT_SYSTEM_2_BUTTON.setOnAction(inputHandler);
        SOLVE_EQUATION_1_CHOOSE_FILE_BUTTON.setOnAction(inputHandler);
        SOLVE_EQUATION_2_CHOOSE_FILE_BUTTON.setOnAction(inputHandler);
        SOLVE_EQUATION_3_CHOOSE_FILE_BUTTON.setOnAction(inputHandler);
        SOLVE_SYSTEM_1_CHOOSE_FILE_BUTTON.setOnAction(inputHandler);
        SOLVE_SYSTEM_2_CHOOSE_FILE_BUTTON.setOnAction(inputHandler);

        VBox.setMargin(HEADER_MESSAGE, new Insets(5));

        HBox.setMargin(TO_CHOOSE_EQUATION_BUTTON, new Insets(5));
        HBox.setMargin(TO_CHOOSE_SYSTEM_BUTTON, new Insets(5));

        VBox.setMargin(LEFT_BORDER_INPUT, new Insets(5));
        VBox.setMargin(RIGHT_BORDER_INPUT, new Insets(5));
        VBox.setMargin(ACCURACY_INPUT, new Insets(5));
        VBox.setMargin(X_INITIAL_INPUT, new Insets(5));
        VBox.setMargin(Y_INITIAL_INPUT, new Insets(5));

        VBox.setMargin(TO_MAIN_BUTTON, new Insets(20));

        HEADER_MESSAGE.setText("Выберите действие");
        HEADER_MESSAGE.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 24");
        RESPONSE_MESSAGE.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 16");
        LEFT_BORDER_INPUT.setStyle("-fx-max-width: 175;");
        RIGHT_BORDER_INPUT.setStyle("-fx-max-width: 175;");
        ACCURACY_INPUT.setStyle("-fx-max-width: 175;");
        X_INITIAL_INPUT.setStyle("-fx-max-width: 175;");
        Y_INITIAL_INPUT.setStyle("-fx-max-width: 175;");
        TO_CHOOSE_EQUATION_BUTTON.setStyle("-fx-max-width: 175;");
        TO_CHOOSE_SYSTEM_BUTTON.setStyle("-fx-max-width: 175;");
        SOLVE_EQUATION_1_BUTTON.setStyle("-fx-max-width: 175;");
        SOLVE_EQUATION_2_BUTTON.setStyle("-fx-max-width: 175;");
        SOLVE_EQUATION_3_BUTTON.setStyle("-fx-max-width: 175;");
        SOLVE_SYSTEM_1_BUTTON.setStyle("-fx-max-width: 175;");
        SOLVE_SYSTEM_2_BUTTON.setStyle("-fx-max-width: 175;");
        SOLVE_EQUATION_1_CHOOSE_FILE_BUTTON.setStyle("-fx-max-width: 175;");
        SOLVE_EQUATION_2_CHOOSE_FILE_BUTTON.setStyle("-fx-max-width: 175;");
        SOLVE_EQUATION_3_CHOOSE_FILE_BUTTON.setStyle("-fx-max-width: 175;");
        SOLVE_SYSTEM_1_CHOOSE_FILE_BUTTON.setStyle("-fx-max-width: 175;");
        SOLVE_SYSTEM_2_CHOOSE_FILE_BUTTON.setStyle("-fx-max-width: 175;");
        PLOT_EQUATION_1_BUTTON.setStyle("-fx-max-width: 175;");
        PLOT_EQUATION_2_BUTTON.setStyle("-fx-max-width: 175;");
        PLOT_EQUATION_3_BUTTON.setStyle("-fx-max-width: 175;");
        PLOT_SYSTEM_1_BUTTON.setStyle("-fx-max-width: 175;");
        PLOT_SYSTEM_2_BUTTON.setStyle("-fx-max-width: 175;");


        MAIN_BUTTONS.setAlignment(Pos.CENTER);
        CHOOSE_EQUATION_BUTTONS.setAlignment(Pos.CENTER);
        CHOOSE_SYSTEM_BUTTONS.setAlignment(Pos.CENTER);
        ROOT_LAYOUT.setAlignment(Pos.TOP_CENTER);

        ROOT_LAYOUT.setStyle("-fx-background-color: #2d2d2d;");
        ROOT_LAYOUT.getChildren().addAll(HEADER_MESSAGE, MAIN_BUTTONS);

        Scene scene = new Scene(ROOT_LAYOUT, 640, 420);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Вычмат...");
        stage.show();
    }
}