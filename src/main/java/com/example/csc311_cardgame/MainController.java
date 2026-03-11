package com.example.csc311_cardgame;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML private ImageView card1Image, card2Image, card3Image, card4Image;
    @FXML private TextField expressionField;
    @FXML private Button verifyButton, refreshButton, findSolutionButton;
    @FXML private Label statusLabel;

    private final Deck deck = new Deck();
    private List<Card> currentCards = new ArrayList<>();
    private final ExpressionEvaluator evaluator = new ExpressionEvaluator();
    private final SolutionFinder solutionFinder = new SolutionFinder();

    @Override
    public void initialize(URL url, ResourceBundle rb) { dealNewCards(); }

    @FXML
    private void handleRefresh() {
        expressionField.clear();
        statusLabel.setText("");
        dealNewCards();
    }

    @FXML
    private void handleVerify() {
        String expr = expressionField.getText().trim();
        if (expr.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Empty Expression", "Please enter an expression before verifying.");
            return;
        }

        List<Integer> usedNumbers = ExpressionEvaluator.extractNumbers(expr);
        List<Integer> cardValues = new ArrayList<>();
        for (Card c : currentCards) cardValues.add(c.getValue());

        List<Integer> sortedUsed = new ArrayList<>(usedNumbers);
        List<Integer> sortedCard = new ArrayList<>(cardValues);
        java.util.Collections.sort(sortedUsed);
        java.util.Collections.sort(sortedCard);

        if (!sortedUsed.equals(sortedCard)) {
            showAlert(Alert.AlertType.ERROR, "Invalid Numbers",
                    "The numbers in your expression do not match the four card values.\nCard values: " + cardValues);
            return;
        }

        try {
            double result = evaluator.evaluate(expr);
            if (Math.abs(result - 24.0) < 1e-9) {
                showAlert(Alert.AlertType.INFORMATION, "Correct!",
                        "Great job! Your expression equals 24!\n\n" + expr + " = 24");
                statusLabel.setText("Correct!");
                statusLabel.setStyle("-fx-text-fill: #16a34a;");
            } else {
                showAlert(Alert.AlertType.ERROR, "Incorrect",
                        "Your expression evaluates to " + String.format("%.4f", result) + ", not 24.\nTry again!");
                statusLabel.setText(" Not 24 — got " + String.format("%.2f", result));
                statusLabel.setStyle("-fx-text-fill: #dc2626;");
            }
        } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Expression",
                    "Could not evaluate the expression:\n" + e.getMessage());
        }
    }

    @FXML
    private void handleFindSolution() {
        String solution = solutionFinder.findSolution(currentCards);
        if (solution != null) {
            expressionField.setText(solution);
            showAlert(Alert.AlertType.INFORMATION, "Solution Found",
                    "A possible solution is:\n\n" + solution);
        } else {
            showAlert(Alert.AlertType.WARNING, "No Solution",
                    "No solution exists for these four cards.\nClick Refresh to get new cards.");
        }
    }

    private void dealNewCards() {
        currentCards = deck.dealFour();
        card1Image.setImage(loadCardImage(currentCards.get(0)));
        card2Image.setImage(loadCardImage(currentCards.get(1)));
        card3Image.setImage(loadCardImage(currentCards.get(2)));
        card4Image.setImage(loadCardImage(currentCards.get(3)));
        statusLabel.setText("");
    }

    private Image loadCardImage(Card card) {
        String path = "/com/example/csc311_cardgame/png/" + card.getImageFilename();
        URL resource = getClass().getResource(path);
        if (resource == null) {
            System.err.println("Image not found: " + path);
            return null;
        }
        return new Image(resource.toExternalForm());
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}