package edu.miracosta.cs112.lotaria;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Random;

public class HelloApplication extends Application {
    private static final LoteriaCard[] LOTERIA_CARDS = {
            new LoteriaCard("Las matematicas", "1.png", 1),
            new LoteriaCard("Las ciencias", "2.png", 2),
            new LoteriaCard("La Tecnología", "8.png", 8),
            new LoteriaCard("La ingeniería", "9.png", 9)
    };

    private boolean[] drawnCards = new boolean[LOTERIA_CARDS.length];
    private int cardsDrawn = 0;
    private final Random rand = new Random();

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 20px; -fx-background-color: #fdf6e3;");

        Label titleLabel = new Label("EChALE STEM Loteria!");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        ImageView cardImageView = new ImageView(LOTERIA_CARDS[0].getImage());
        cardImageView.setFitWidth(200);
        cardImageView.setFitHeight(300);
        cardImageView.setPreserveRatio(true);

        Label messageLabel = new Label("Click the button to draw a card!");

        Button drawCardButton = new Button("Draw Random Card");

        ProgressBar gameProgressBar = new ProgressBar(0.0);
        gameProgressBar.setPrefWidth(200);

        drawCardButton.setOnAction(event -> {
            if (cardsDrawn >= LOTERIA_CARDS.length) return;

            int index;
            do {
                index = rand.nextInt(LOTERIA_CARDS.length);
            } while (drawnCards[index]);

            drawnCards[index] = true;
            cardsDrawn++;

            LoteriaCard card = LOTERIA_CARDS[index];
            cardImageView.setImage(card.getImage());
            messageLabel.setText("You drew: " + card.getCardName());

            gameProgressBar.setProgress((double) cardsDrawn / LOTERIA_CARDS.length);

            if (cardsDrawn == LOTERIA_CARDS.length) {
                cardImageView.setImage(new LoteriaCard().getImage()); // Echale logo
                messageLabel.setText("GAME OVER. No more cards! Exit and run again to reset ^_^");
                gameProgressBar.setStyle("-fx-accent: red;");
                drawCardButton.setDisable(true);
            }
        });

        root.getChildren().addAll(titleLabel, cardImageView, messageLabel, drawCardButton, gameProgressBar);

        Scene scene = new Scene(root, 350, 500);
        stage.setTitle("Loteria!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
