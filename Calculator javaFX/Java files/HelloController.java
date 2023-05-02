package com.example.calc;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HelloController {
    @FXML private Label lblResult;

    private double x, y;
    private double num1 = 0;
    private boolean b = false;
    private String operator = "+";

    @FXML
    void onNumberClicked(MouseEvent event) {
        if(b == true){
            lblResult.setText(String.valueOf("0"));
            b = false;
        }
        int value = Integer.parseInt(((Pane)event.getSource()).getId().replace("btn",""));
        lblResult.setText(Double.parseDouble(lblResult.getText())==0?String.valueOf((double)value):String.valueOf(Double.parseDouble(lblResult.getText())*10+value));
    }

    @FXML
    void onSymbolClicked(MouseEvent event) {
        String symbol = ((Pane)event.getSource()).getId().replace("btn","");
        b = true;
        if(symbol.equals("Equals")) {
            double num2 = Double.parseDouble(lblResult.getText());
            switch (operator) {
                case "+" -> lblResult.setText((num1+num2) + "");
                case "-" -> lblResult.setText((num1-num2) + "");
                case "*" -> lblResult.setText((num1*num2) + "");
                case "/" -> lblResult.setText((num1/num2) + "");
                case "X^2" -> lblResult.setText(Math.pow(num1, 2.0) + "");
                case "X^1/2" -> lblResult.setText(Math.pow(num1, 0.5) + "");
                case "1/X" -> lblResult.setText(1/num1 + "");
                case "%" -> lblResult.setText((num1*(num2/100)) + "");
            }
        }
        else if(symbol.equals("Clear")) {
            lblResult.setText(String.valueOf(0));

        }
        else {
            switch (symbol) {
                case "Plus" -> operator = "+";
                case "Minus" -> operator = "-";
                case "Multiply" -> operator = "*";
                case "Divide" -> operator = "/";
                case "pow" -> operator = "X^2";
                case "sqrt" -> operator = "X^1/2";
                case "inverse" -> operator = "1/X";
                case "percent" -> operator = "%";
            }
            num1 = Double.parseDouble(lblResult.getText());
            lblResult.setText(String.valueOf(num1));
        }
    }
}