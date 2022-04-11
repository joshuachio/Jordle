import javafx.scene.layout.BackgroundFill;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import java.util.Random;
import java.util.ArrayList;
import javafx.scene.input.KeyCode;
import javafx.scene.text.FontWeight;

import org.w3c.dom.css.Rect;

import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.TextAlignment;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.control.Slider;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;
import javafx.scene.layout.HBox;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Jordle extends Application {

    int row = 0;
    int column = 0;
    boolean check = true;
    Rectangle[][] rectArr = new Rectangle[6][5];
    Label[][] labArr = new Label[6][5];
    Random r = new Random();
    int rand = r.nextInt(Words.list.size());
    String word = Words.list.get(rand);
    Stage errorStage = new Stage();
    Label inGameText = new Label();

    private Stage makeStage() {
        errorStage.setTitle("Error");
        Label errorText = new Label("Not enough letters");
        errorText.setWrapText(true);
        errorText.setTextAlignment(TextAlignment.CENTER);
        errorText.setMaxWidth(150);
        errorText.setMaxHeight(30);
        errorText.setTranslateX(15);
        errorText.setTranslateY(15);
        errorText.setFont(new Font("nyt-franklin", 14));
        FlowPane errorPane = new FlowPane();
        errorPane.getChildren().add(errorText);
        errorPane.setPadding(new Insets(20, 50, 30, 20));
        Scene errorScene = new Scene(errorPane);
        errorStage.setScene(errorScene);
        return errorStage;
    }

    private void gameText() {
        inGameText.setText("Try guessing a word!");
        inGameText.setFont(new Font("Monospaced", 20));
        inGameText.setFont(new Font("Monospaced", 20));
        inGameText.setWrapText(true);
    }

    @Override
    public void start(Stage primaryStage) {

        GridPane pane = new GridPane();
        Label label = new Label("Jordle");
        label.setFont(new Font("Arial Bold", 50));
        // label.setTextAlignment(TextAlignment.CENTER);
        Button restart = new Button("Restart");
        Button instructions = new Button("How to Play");
        
        // if (true) {
        //     Label inGameText = new Label("Game over. The word was: " + word + ".");
        // } else if (true) {
        //     Label inGameText = new Label("Congratulations! You guessed the word!");
        // } else {
        //     Label inGameText = new Label("Try guessing a word!");
        // }
        

        // inGameText.setTextAlignment(TextAlignment.CENTER);
        
        // pane.setAlignment(Pos.CENTER);
       
        VBox vbox = new VBox(25);
        HBox hbox = new HBox(250);
        
        hbox.getChildren().add(instructions);
        hbox.getChildren().add(label);
        hbox.getChildren().add(restart);
        hbox.setPadding(new Insets(30, 25, 30, 25));
        hbox.setAlignment(Pos.CENTER);
        // vbox.setPadding(new Insets(1));
        // pane.setPadding(new Insets(20, 100, 20, 100));
        pane.setAlignment(Pos.CENTER);
        gameText();
        HBox hbox2 = new HBox();
        hbox2.getChildren().add(inGameText);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.setPadding(new Insets(20));
        hbox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        vbox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        hbox2.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        for (int i = 0; i < 6; ++i) {
            for (int u = 0; u < 5; ++u) {
                Rectangle rect = new Rectangle(0, 0, 80, 80);
                rect.setFill(Color.WHITE);
                rect.setStrokeWidth(1.5);
                rect.setStroke(Color.color(.80, 0.80, 0.80, 1.0));
                pane.add(rect, u, i);
                Label text =  new Label(" ");
                pane.add(text, u, i);
                text.setFont(Font.font("Monospaced", FontWeight.BOLD, 30));
                pane.setHalignment(text, HPos.CENTER);
                rectArr[i][u] = rect;
                labArr[i][u] = text;
            }
        }

        pane.setHgap(7);
        pane.setVgap(7);
        vbox.getChildren().add(hbox);
        vbox.getChildren().add(pane);
        vbox.getChildren().add(hbox2);

        Scene scene = new Scene(vbox);

        primaryStage.setTitle("Jordle"); // Set the stage title

        primaryStage.setScene(scene);
        primaryStage.show(); // Display the stage

        // Stage secondaryStage = new Stage(); // Create A new stage
        // secondaryStage.setTitle("Second Stage"); // Set the stage title
        // // Set A scene with A button in the stage
        // Button button = new Button("Hi");
        // secondaryStage.setScene(new Scene(button, 100, 100));        

        Stage secondaryStage = new Stage(); // Create A new stage
        secondaryStage.setTitle("Instructions"); // Set the stage title
        // Set A scene with A button in the stage
        Label instructionsText = new Label("Welcome to Jordle!\n\nGuess the mystery 5-letter word in 6 tries. " +
                                    "Each guess must be a valid five-letter word. Hit the enter button to submit. " +
                                    "After each guess, the color of the tiles will change to show how close your guess " +
                                    "was to the word.");
        instructionsText.setWrapText(true);
        instructionsText.setTextAlignment(TextAlignment.CENTER);
        instructionsText.setMaxWidth(230);
        instructionsText.setTranslateX(15);
        instructionsText.setTranslateY(15);
        instructionsText.setFont(new Font("nyt-franklin", 14));
        FlowPane flowPane = new FlowPane();
        flowPane.getChildren().add(instructionsText);
        // Group root = new Group();
        // root.getChildren().add(label);
    //   button.setOnAction((event) -> System.out.println("*crack*"));
        secondaryStage.setScene(new Scene(flowPane, 260, 190));        
        secondaryStage.show(); // Display the secondary stage

        

        

        // rectArr[0][0].setOnKeyPressed((KeyEvent e) ->{

        //     if (e.getText().length() > 0 && Character.isLetter(e.getText().charAt(0))) {
        //         if (column == 5) {
        //             check = false;
        //         } else {
        //             labArr[row][column].setText(e.getText().toUpperCase());
        //             rectArr[row][column].setStroke(Color.BLACK);
        //         }   
        //     } else if (e.getText().length() > 0 && e.getText().equals("\n")) {
        //         if (column == 5) {
        //             //run check if the word is right
        //             ;
        //         } else {
        //             // errorStage.show();
        //             ;
        //         }
        //     }
        // });
        // while (check) {
        //     scene.setOnKeyPressed((KeyEvent e) -> {
        //             if (e.getText().length() > 0 && Character.isLetter(e.getText().charAt(0))) {
        //                 labArr[row][column].setText(e.getText().toUpperCase());
        //                 rectArr[row][column].setStroke(Color.BLACK);
        //             }
        //     });
        //     row++;
        //     column++;
        //     check = false;
        // }
        
                // rectArr[0][0].setStroke(Color.BLACK);
            //     if (column == 5) {
            //         // check = false;
            //     } else {
            //         labArr[0][0].setText(e.getText().toUpperCase());
            //         rectArr[0][0].setStroke(Color.BLACK);
            //     }   
            // } else if (e.getText().length() > 0 && e.getText().equals("\n")) {
            //     if (column == 5) {
            //         //run check if the word is right
            //         ;
            //     } else {
            //         // errorStage.show();
            //         ;
            //     }

        // public void test () {
        //     if (e.getText().length() > 0 && Character.isLetter(e.getText().charAt(0))) {
        //         if (column == 5) {
        //             check = false;
        //         } else {
        //             labArr[row][column++].setText(e.getText().toUpperCase());
        //             rectArr[row][column++].setStroke(Color.BLACK);
        //         }   
        //     } else if (e.getText().length() > 0 && e.getText().equals("\n")) {
        //         if (column == 5) {
        //             //run check if the word is right
        //             ;
        //         } else {
        //             // errorStage.show();
        //             ;
        //         }
        //     }

        //     labArr[row][column].requestFocus();
        // }

        // instructions.setOnMouseClicked(new EventHandler<ActionEvent>() {
        //     @Override
        //     public void handle(ActionEvent event) {
        //         secondaryStage.show();
        //     }
        // });
        
        instructions.setOnMouseClicked(e -> {secondaryStage.show();});
        

        restart.setOnMouseClicked(e -> {
            row = 0;
            column = 0;
            start(primaryStage);
        });

        AddWord test = new AddWord();
        scene.setOnKeyPressed(test); 



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
                    ArrayList<Character> inputArr = new ArrayList();
                    ArrayList<Character> wordArr = new ArrayList();
                    int counter = 0;
                    for (int i = 0; i < 5; ++i) {
                        wordArr.add(word.charAt(i));
                        inputArr.add(labArr[row][i].getText().toLowerCase().charAt(0));
                    }
                    System.out.println(inputArr.toString());
                    System.out.println(wordArr.toString());
                    for (int i = 0; i < 5; ++i) {
                        if (wordArr.get(i) == inputArr.get(i)) {
                            rectArr[row][i].setFill(new Color(115.0 / 255.0, 171.0 / 255.0, 120.0 / 255.0, 1));
                            rectArr[row][i].setStroke(Color.WHITE);
                            labArr[row][i].setTextFill(Color.WHITE);
                        } else if (wordArr.contains(inputArr.get(i))) {
                            rectArr[row][i].setFill(new Color(201.0 / 255.0, 180.0 / 255.0, 88.0 / 255.0, 1));
                            rectArr[row][i].setStroke(Color.WHITE);
                            labArr[row][i].setTextFill(Color.WHITE);
                        } else {
                            rectArr[row][i].setFill(Color.GRAY);
                            rectArr[row][i].setStroke(Color.WHITE);
                            labArr[row][i].setTextFill(Color.WHITE);
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