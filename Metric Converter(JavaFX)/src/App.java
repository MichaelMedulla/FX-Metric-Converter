import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

public class App extends Application
{
    private final Map<String, Double> conv = new HashMap<>();

    @Override
    public void start(Stage primaryStage)
    {
        conv.put("kg_to_lb", 2.20462);
        conv.put("gram_to_ounces", 0.03527396);
        conv.put("km_to_mile", 0.621371);
        conv.put("mm_to_inch", 0.0393701);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(25));
        grid.setHgap(35);
        grid.setVgap(35);

        ComboBox<String> convType = new ComboBox<>();

        convType.getItems().addAll("kg to lb", "gram to ounces", "km to mile", "mm to inch");

        TextField input = new TextField();
        Label resultLabel = new Label();

        Button button = new Button("Convert");
        button.setOnAction
        (e -> {
            try
            {
                String convChose = convType.getSelectionModel().getSelectedItem();
                if (convChose == null)
                {
                    resultLabel.setText("Please select a conversion type.");
                    return;
                }
                String[] convOption = convChose.split(" ");
                double value = Double.parseDouble(input.getText());
                double result = convert(value, convOption[0] + "_to_" + convOption[2]);
                resultLabel.setText(String.format("%.3f %s = %.3f %s", value, convOption[0], result, convOption[2]));
            }
            catch (NumberFormatException | NullPointerException ex)
            {
                resultLabel.setText("Invalid input. Please try again.");
            }
        });

        grid.add(new Label("Conversion Type:"), 0, 0);
        grid.add(convType, 1, 0);
        grid.add(new Label("Value:"), 0, 1);
        grid.add(input, 1, 1);
        grid.add(button, 0, 2, 2, 1);
        grid.add(resultLabel, 0, 3, 2, 1);

        Scene scene = new Scene(grid, 325, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Metric Converter");
        primaryStage.show();
    }

    private double convert(double value, String conversion)
    {
        return value * conv.get(conversion);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
