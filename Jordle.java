import javafx.scene.layout.BackgroundFill;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import java.util.Random;

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

    @Override
    public void start(Stage primaryStage) {

        Random r = new Random();
        int rand = r.nextInt(Words.list.size());
        String word = Words.list.get(rand);
        GridPane pane = new GridPane();
        Label label = new Label("Jordle");
        label.setFont(new Font("Arial Bold", 50));
        label.setTextAlignment(TextAlignment.CENTER);
        Button restart = new Button("Restart");
        Button instructions = new Button("How to Play");
        Label inGameText = new Label("Game over. The word was: " + word + ".");
        // if (true) {
        //     Label inGameText = new Label("Game over. The word was: " + word + ".");
        // } else if (true) {
        //     Label inGameText = new Label("Congratulations! You guessed the word!");
        // } else {
        //     Label inGameText = new Label("Try guessing a word!");
        // }
        inGameText.setFont(new Font("Monospaced", 20));

        // inGameText.setTextAlignment(TextAlignment.CENTER);
        inGameText.setWrapText(true);
        // pane.setAlignment(Pos.CENTER);
       
        VBox vbox = new VBox(25);
        HBox hbox = new HBox(250);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(instructions);
        hbox.getChildren().add(label);
        hbox.getChildren().add(restart);
        hbox.setPadding(new Insets(30));
        vbox.setPadding(new Insets(1));
        pane.setPadding(new Insets(20, 50, 0, 195));
        

        HBox hbox2 = new HBox();
        hbox2.getChildren().add(inGameText);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.setPadding(new Insets(20));


        hbox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        vbox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        hbox2.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        // vbox.setFill(Color.WHITE);
        // hbox2.setFill(Color.WHITE);
        
        // Place nodes in the pane
        //add(Node child, int columnIndex, int rowIndex)
        for (int i = 0; i < 6; ++i) {
            for (int u = 0; u < 5; ++u) {
                Rectangle rect = new Rectangle(0, 0, 80, 80);
                rect.setFill(Color.WHITE);
                rect.setStrokeWidth(1.5);
                rect.setStroke(Color.color(.80, 0.80, 0.80, 1.0));
                pane.add(rect, u, i);
                Label text =  new Label(" ");
                pane.add(text, u, i);
                text.setFont(new Font("Monospaced", 40));
                pane.setHalignment(text, HPos.CENTER);
                rectArr[i][u] = rect;
                labArr[i][u] = text;
            }
        }

        // pane.add(incorrectText, 2, 8);
        // pane.add(restart, 4, 0)s;
        // pane.add(instructions, 0, 0);
        pane.setHgap(7);
        pane.setVgap(7);
        vbox.getChildren().add(hbox);
        vbox.getChildren().add(pane);
        vbox.getChildren().add(hbox2);




        

        
        // vbox.setAlignment(Pos.RIGHT);
        Scene scene1 = new Scene(vbox);
        // Scene scene = new Scene(pane);
        primaryStage.setTitle("Jordle"); // Set the stage title
        
        // primaryStage.setScene(scene); // Place the scene in the stage
        scene1.setFill(Color.RED);
        primaryStage.setScene(scene1);
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

        scene1.setOnKeyPressed(new AddWord());

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
        //     scene1.setOnKeyPressed((KeyEvent e) -> {
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

        instructions.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                secondaryStage.show();
            }
        });

        restart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                start(primaryStage);
            }
        });
    }

    class AddWord implements EventHandler<KeyEvent> {
        @Override // Override the handle method
        public void handle(KeyEvent e) {
            if (e.getText().length() > 0 && Character.isLetter(e.getText().charAt(0))) {
                if (column != 5) {
                    labArr[row][column].setText(e.getText().toUpperCase());
                    rectArr[row][column++].setStroke(Color.BLACK);
                }
            } else if (e.getText().length() > 0 && e.getText().equals("\n")) {
                if (column == 5) {
                    //run check if the word is right
                    ;
                } else {
                    // errorStage.show();
                    ;
                }
            }

            labArr[row][column].requestFocus();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }    

}