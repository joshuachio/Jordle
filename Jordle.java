import java.util.Random;
import java.util.ArrayList;

import javafx.scene.layout.BackgroundFill;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;
import javafx.scene.text.FontWeight;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.scene.layout.Background;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.TextAlignment;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import java.util.Hashtable;

public class Jordle extends Application {

    int row = 0;
    int column = 0;
    boolean check = true;
    Rectangle[][] rectArr = new Rectangle[6][5];
    Label[][] labArr = new Label[6][5];
    Hashtable<Character, Label> letterTab = new Hashtable<Character, Label>();
    Hashtable<Character, Rectangle> rectTab = new Hashtable<Character, Rectangle>();

    Random r = new Random();
    int rand = r.nextInt(Words.list.size());
    String word = Words.list.get(rand);
    Stage errorStage = new Stage();
    Label inGameText = new Label();

    @Override
    public void start(Stage primaryStage) {

        //Title HBox
        
        HBox titleBox = new HBox(250);

        Label title = new Label("Jordle");
        Button restart = new Button("Restart");
        Button instructions = new Button("How to Play");

        title.setFont(new Font("Clear Sans", 50));

        titleBox.getChildren().add(instructions);
        titleBox.getChildren().add(title);
        titleBox.getChildren().add(restart);
        titleBox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        titleBox.setPadding(new Insets(30, 25, 0, 25));
        titleBox.setAlignment(Pos.CENTER);

        //Main GridPane body

        GridPane gamePane = new GridPane();

        for (int i = 0; i < 6; ++i) {
            for (int u = 0; u < 5; ++u) {
                Rectangle rect = new Rectangle(0, 0, 80, 80);
                rect.setFill(Color.WHITE);
                rect.setStrokeWidth(1.5);
                rect.setStroke(Color.color(.80, 0.80, 0.80, 1.0));
                gamePane.add(rect, u, i);
                Label text =  new Label(" ");
                gamePane.add(text, u, i);
                text.setFont(Font.font("Clear Sans", FontWeight.BOLD, 30));
                gamePane.setHalignment(text, HPos.CENTER);
                rectArr[i][u] = rect;
                labArr[i][u] = text;
            }
        }

        gamePane.setAlignment(Pos.CENTER);
        gamePane.setHgap(7);
        gamePane.setVgap(7);

        //A-Z GridPane

        GridPane alphaPane = new GridPane();

        Character[] letters = new Character[30];
        String az = "QWERTYUIOP ASDFGHJKL  ZXCVBNM ";
        for (int i = 0; i < az.length(); ++i) {
                letters[i] = az.charAt(i);
        }
        int letter = 0;
        for (int i = 0; i < 3; ++i) {
            for (int u = 0; u < 10; ++u) {
                Rectangle rect = new Rectangle(0, 0, 30, 30);
                rect.setFill(Color.WHITE);
                rect.setStrokeWidth(1.5);
                if (letter == 10 || letter == 20 || letter == 21 || letter == 29) {
                    rect.setStroke(Color.WHITE);
                } else {
                    rect.setStroke(Color.color(.80, 0.80, 0.80, 1.0));
                }
                alphaPane.add(rect, u, i);
                Label text =  new Label(letters[letter].toString());
                alphaPane.add(text, u, i);
                text.setFont(Font.font("Clear Sans", FontWeight.BOLD, 16));
                alphaPane.setHalignment(text, HPos.CENTER);
                letterTab.put(Character.toLowerCase(letters[letter]), text);
                rectTab.put(Character.toLowerCase(letters[letter]), rect);
                ++letter;
            }
        }
        
        alphaPane.setAlignment(Pos.CENTER);
        alphaPane.setHgap(5);
        alphaPane.setVgap(5);
        alphaPane.setPadding(new Insets(10, 25, 10, 25));

        //Footer HBox

        HBox footerBox = new HBox();

        gameText();

        footerBox.getChildren().add(inGameText);
        footerBox.setAlignment(Pos.CENTER);
        // footerBox.setPadding(new Insets(10, 0, 50, 0));
        footerBox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        
        //VBox combining all the boxes

        VBox vbox = new VBox(10);

        vbox.getChildren().add(titleBox);
        vbox.getChildren().add(footerBox);
        vbox.getChildren().add(gamePane);
        vbox.getChildren().add(alphaPane);
        vbox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

        //Scene and Stage creation

        Scene jordleScene = new Scene(vbox);

        primaryStage.setTitle("Jordle");
        primaryStage.setScene(jordleScene);
        primaryStage.show();    

        //Instruction Stage Creation

        Stage introStage = new Stage();
        
        FlowPane flowPane = new FlowPane();

        Label instructionsText = new Label("Welcome to Jordle!\n\nGuess the mystery 5-letter word in 6 tries. " +
                                    "Each guess must be a valid five-letter word. Hit the \"ENTER\" button to submit. " +
                                    "After each guess, the color of the tiles will change to show how close your guess " +
                                    "was to the word.");

        instructionsText.setWrapText(true);
        instructionsText.setTextAlignment(TextAlignment.CENTER);
        instructionsText.setMaxWidth(230);
        instructionsText.setTranslateX(15);
        instructionsText.setTranslateY(15);
        instructionsText.setFont(new Font("Clear Sans", 14));
        flowPane.getChildren().add(instructionsText);

        introStage.setTitle("Instructions");
        introStage.setScene(new Scene(flowPane, 260, 210));
        if (check) {
            introStage.show();
        }

        //Action/Key Events
        
        instructions.setOnMouseClicked(e -> {introStage.show();});

        restart.setOnMouseClicked(e -> {
            row = 0;
            column = 0;
            rand = r.nextInt(Words.list.size());
            word = Words.list.get(rand);
            check = false;
            start(primaryStage);
        });

        jordleScene.setOnKeyPressed(new AddWord()); 

    }

    private Stage makeStage() {
        errorStage.setTitle("Error");
        Label errorText = new Label("Not enough letters");
        errorText.setWrapText(true);
        errorText.setTextAlignment(TextAlignment.CENTER);
        // errorText.setMaxHeight(30);
        // errorText.setTranslateX(15);
        // errorText.setTranslateY(15);
        errorText.setFont(new Font("nyt-franklin", 14));
        FlowPane errorPane = new FlowPane();
        errorPane.getChildren().add(errorText);
        errorPane.setPadding(new Insets(30, 70, 30, 30));
        Scene errorScene = new Scene(errorPane, 175, 90);
        errorStage.setScene(errorScene);
        return errorStage;
    }

    private void gameText() {
        inGameText.setText("Try guessing a word!");
        inGameText.setFont(new Font("Monospaced", 20));
        inGameText.setFont(new Font("Monospaced", 20));
        inGameText.setWrapText(true);
    }

    class AddWord implements EventHandler<KeyEvent> {

        @Override // Override the handle method
        public void handle(KeyEvent e) {
            labArr[0][0].requestFocus();
            if (e.getCode() == KeyCode.BACK_SPACE) {
                if (column > 0 && column < 6) {
                    labArr[row][--column].setText(e.getText());
                    rectArr[row][column].setStroke(Color.color(.80, 0.80, 0.80, 1.0));
                }
            } else if (e.getCode() == KeyCode.ENTER) {
                if (column == 5) {
                    System.out.println(word);
                    ArrayList<Character> inputArr = new ArrayList();
                    ArrayList<Character> wordArr = new ArrayList();
                    int counter = 0;
                    for (int i = 0; i < 5; ++i) {
                        wordArr.add(word.charAt(i));
                        inputArr.add(labArr[row][i].getText().toLowerCase().charAt(0));
                    }
                    for (int i = 0; i < 5; ++i) {
                        if (wordArr.get(i) == inputArr.get(i)) {
                            rectArr[row][i].setFill(new Color(115.0 / 255.0, 171.0 / 255.0, 120.0 / 255.0, 1));
                            rectArr[row][i].setStroke(Color.WHITE);
                            labArr[row][i].setTextFill(Color.WHITE);
                            rectTab.get(Character.valueOf((char) inputArr.get(i))).setFill(new Color(115.0 / 255.0, 171.0 / 255.0, 120.0 / 255.0, 1));
                            rectTab.get(Character.valueOf((char) inputArr.get(i))).setStroke(Color.WHITE);
                            letterTab.get(Character.valueOf((char) inputArr.get(i))).setTextFill(Color.WHITE);
                        } else if (wordArr.contains(inputArr.get(i))) {
                            rectArr[row][i].setFill(new Color(201.0 / 255.0, 180.0 / 255.0, 88.0 / 255.0, 1));
                            rectArr[row][i].setStroke(Color.WHITE);
                            labArr[row][i].setTextFill(Color.WHITE);
                            rectTab.get(Character.valueOf((char) inputArr.get(i))).setFill(new Color(201.0 / 255.0, 180.0 / 255.0, 88.0 / 255.0, 1));
                            rectTab.get(Character.valueOf((char) inputArr.get(i))).setStroke(Color.WHITE);
                            letterTab.get(Character.valueOf((char) inputArr.get(i))).setTextFill(Color.WHITE);
                        } else {
                            rectArr[row][i].setFill(Color.GRAY);
                            rectArr[row][i].setStroke(Color.WHITE);
                            labArr[row][i].setTextFill(Color.WHITE);
                            rectTab.get(Character.valueOf((char) inputArr.get(i))).setFill(Color.GRAY);
                            rectTab.get(Character.valueOf((char) inputArr.get(i))).setStroke(Color.WHITE);
                            letterTab.get(Character.valueOf((char) inputArr.get(i))).setTextFill(Color.WHITE);
                        }
                        if (rectArr[row][i].getFill().equals(new Color(115.0 / 255.0, 171.0 / 255.0, 120.0 / 255.0, 1))) {
                            ++counter;
                        }
                    }
                    if (counter == 5) {
                        gameText();
                        inGameText.setText("Congratulations! You guessed the word!");
                    } else if (row == 5) {
                        gameText();
                        inGameText.setText("Game over. The word was: " + word + ".");
                    } else {
                        column = 0;
                        ++row;
                    }
                } else {
                    makeStage();
                    errorStage.show();
                }
            } else {
                try {
                    if (e.getText().charAt(0) >= (int) 'a' && e.getText().charAt(0) <= (int) 'z') {
                        if (column != 5) {
                            labArr[row][column].setText(e.getText().toUpperCase());
                            rectArr[row][column++].setStroke(Color.BLACK);
                        }
                    }
                } catch(Throwable b) {
                    ;
                } finally {
                    ;
                }
            }
            }
    }

    public static void main(String[] args) {
        launch(args);
    }    

}