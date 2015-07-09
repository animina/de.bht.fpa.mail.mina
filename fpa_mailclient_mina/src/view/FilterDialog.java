package view;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import model.Message;

import java.util.Arrays;
/**
 * Created by mina on 30.06.15.
 */
public class FilterDialog {
    private Node lastNode;
    private GridPane grid = new GridPane();
    private String[] messageComboItemList = new String[]{"From", "To", "Subject", "Message"};
    private String[] conditionFieldComboItemList = new String[]{"is", "contains", "contains not"};

    public FilterDialog() {
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 0, 10));
        grid.setPrefSize(500, 200);
    }

    /**
     * Call this method to filter the dialog.
     */
    public ObservableList<Message> filter(final ObservableList<Message> messageList) {
        if (messageList == null) {
            System.err.println("Trying to use NULL as message list");
            return messageList;
        }

        Dialog filterDialog = new Dialog<>();
        filterDialog.setTitle("Set mail filter");
        filterDialog.setHeaderText("Enter filter criterias (max: 5)");
        filterDialog.getDialogPane().getButtonTypes().addAll(
                new ButtonType("Filter", ButtonBar.ButtonData.OK_DONE),
                ButtonType.CANCEL);

        filterDialog.setResultConverter(new Callback<ButtonType, Void>() {
            @Override
            public Void call(ButtonType dialogButton) {
                if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {

                    // The code below will be replaced by "Teilaufgabe 8"
                    ObservableList<Node> children = grid.getChildren();
                    for (Node node : children) {
                        System.out.println("Element at col, row: " + GridPane.getColumnIndex(node) + ", " + GridPane.getRowIndex(node) + " has value: ");
                        if (node instanceof ComboBox) {
                            ComboBox<String> comboBox = (ComboBox<String>) node;
                            System.out.println(comboBox.getValue());

                        } else if (node instanceof TextField) {
                            TextField textfield = (TextField) node;
                            System.out.println(textfield.getText());
                        }
                    }
                    // End of replacement through "Teilaufgabe 8"
                }
                // Does actually nothing but to fulfill Void return type
                return null;
            }
        });

        addRow(filterDialog);

        filterDialog.showAndWait();

        return messageList;
    }

    private void addRow(final Dialog dialog) {
        int currentRow = getCurrentRow();
        // Limit to 5 rows
        if (currentRow > 4) {
            return;
        }

        ComboBox<String> messageFieldCombo = new ComboBox<>();
        ComboBox<String> conditionFieldCombo = new ComboBox<>();
        TextField conditionPayloadText = new TextField();
        Button addFilterButton = new Button("+");

        messageFieldCombo.setItems(FXCollections.observableList(Arrays.asList(messageComboItemList)));
        conditionFieldCombo.setItems(FXCollections.observableList(Arrays.asList(conditionFieldComboItemList)));

        addFilterButton.setOnAction(event -> addRow(dialog));

        grid.add(messageFieldCombo, 0, currentRow);
        grid.add(conditionFieldCombo, 1, currentRow);
        grid.add(conditionPayloadText, 2, currentRow);
        grid.add(addFilterButton, 3, currentRow);

        lastNode = addFilterButton;

        dialog.getDialogPane().setContent(grid);
    }

    private int getCurrentRow() {
        int currentRow;
        if (lastNode == null) {
            currentRow = 0;
        } else {
            Integer rowIndex = GridPane.getRowIndex(lastNode);
            currentRow = rowIndex.intValue() + 1;
        }
        return currentRow;
    }

}
