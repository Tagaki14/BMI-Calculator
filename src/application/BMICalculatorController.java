package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.event.ActionEvent;

public class BMICalculatorController {

    @FXML
    private TextField inchesInput;
    @FXML
    private TextField centimetersInput;
    @FXML
    private TextField poundsInput;
    @FXML
    private TextField kilosInput;
    @FXML
    private TextField bmiOutput;

    @FXML
    private Label bmiCategory;

    @FXML
    private RadioButton metricRadio;
    @FXML
    private RadioButton imperialRadio;

    @FXML
    public void initialize() {
        ToggleGroup unitGroup = new ToggleGroup();

        metricRadio.setToggleGroup(unitGroup);
        imperialRadio.setToggleGroup(unitGroup);

        selectMetric();
    }

    @FXML
    private void selectMetric() {
        centimetersInput.setVisible(true);
        centimetersInput.setManaged(true);

        kilosInput.setVisible(true);
        kilosInput.setManaged(true);

        inchesInput.setVisible(false);
        inchesInput.setManaged(false);

        poundsInput.setVisible(false);
        poundsInput.setManaged(false);
    }

    @FXML
    private void selectImperial() {
        inchesInput.setVisible(true);
        inchesInput.setManaged(true);

        poundsInput.setVisible(true);
        poundsInput.setManaged(true);

        centimetersInput.setVisible(false);
        centimetersInput.setManaged(false);

        kilosInput.setVisible(false);
        kilosInput.setManaged(false);
    }

    @FXML
    private void calculate(ActionEvent event) {
        try {
            double weight;
            double height;
            double bmi;

            if (metricRadio.isSelected()) {
                weight = Double.parseDouble(kilosInput.getText().trim());
                height = Double.parseDouble(centimetersInput.getText().trim());

                if (weight <= 0 || height <= 0) {
                    throw new IllegalArgumentException();
                }

                bmi = weight / Math.pow(height / 100, 2);

            } else {
                weight = Double.parseDouble(poundsInput.getText().trim());
                height = Double.parseDouble(inchesInput.getText().trim());

                if (weight <= 0 || height <= 0) {
                    throw new IllegalArgumentException();
                }

                bmi = (weight * 703) / Math.pow(height, 2);
            }

            bmiOutput.setText(String.format("%.2f", bmi));

            if (bmi < 18.5) {
                bmiCategory.setText("Underweight");
                bmiCategory.setStyle("-fx-text-fill: #3B82F6; -fx-background-color: #EFF6FF; -fx-background-radius: 8; -fx-padding: 12; -fx-font-weight: bold;");

            } else if (bmi < 25) {
                bmiCategory.setText("Normal Weight");
                bmiCategory.setStyle("-fx-text-fill: #16A34A; -fx-background-color: #F0FDF4; -fx-background-radius: 8; -fx-padding: 12; -fx-font-weight: bold;");

            } else if (bmi < 30) {
                bmiCategory.setText("Overweight");
                bmiCategory.setStyle("-fx-text-fill: #D97706; -fx-background-color: #FFFBEB; -fx-background-radius: 8; -fx-padding: 12; -fx-font-weight: bold;");

            } else {
                bmiCategory.setText("Obese");
                bmiCategory.setStyle("-fx-text-fill: #DC2626; -fx-background-color: #FEF2F2; -fx-background-radius: 8; -fx-padding: 12; -fx-font-weight: bold;");
            }

        } catch (IllegalArgumentException e) {
            bmiOutput.setText("--");
            bmiCategory.setText("Enter valid positive numbers");
            bmiCategory.setStyle(
                    "-fx-text-fill: #DC2626; "
                    + "-fx-background-color: #FEF2F2; "
                    + "-fx-background-radius: 8; "
                    + "-fx-padding: 12;"
            );
        }
    }

    @FXML
    private void reset() {
        inchesInput.clear();
        centimetersInput.clear();
        poundsInput.clear();
        kilosInput.clear();

        bmiOutput.setText("--");
        bmiCategory.setText("Enter your measurements");
        bmiCategory.setStyle("-fx-text-fill: #718096; -fx-background-color: #F3F6FB; -fx-background-radius: 8; -fx-padding: 12;");
    }
}
