import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class d2 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel xLabel = new JLabel("Enter x:");
        xLabel.setBounds(10, 20, 80, 25);
        panel.add(xLabel);
        JTextField xText = new JTextField(20);
        xText.setBounds(100, 20, 165, 25);
        panel.add(xText);

        JLabel yLabel = new JLabel("Enter y:");
        yLabel.setBounds(10, 50, 80, 25);
        panel.add(yLabel);
        JTextField yText = new JTextField(20);
        yText.setBounds(100, 50, 165, 25);
        panel.add(yText);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBounds(10, 80, 150, 25);
        panel.add(calculateButton);

        JLabel resultLabel = new JLabel("Result:");
        resultLabel.setBounds(10, 110, 300, 25);
        panel.add(resultLabel);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double x = parseDouble(xText.getText());
                    double y = parseDouble(yText.getText());
                    double result = computePower(x, y);
                    resultLabel.setText("Result: " + result);
                } catch (Exception ex) {
                    resultLabel.setText("Invalid input. Please enter valid numbers.");
                }
            }
        });
    }

    private static double parseDouble(String s) throws Exception {
        boolean seenDot = false;
        double result = 0.0;
        double factor = 1.0;
        boolean isNegative = false;
        int start = 0;
        
        if (s.charAt(0) == '-') {
            isNegative = true;
            start = 1;
        }
        
        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '.') {
                if (seenDot) {
                    throw new Exception("Invalid number format");
                }
                seenDot = true;
            } else if (c >= '0' && c <= '9') {
                int digit = c - '0';
                if (seenDot) {
                    factor /= 10;
                    result += digit * factor;
                } else {
                    result = result * 10 + digit;
                }
            } else {
                throw new Exception("Invalid character in number");
            }
        }
        if (isNegative) {
            result = -result;
        }
        return result;
    }

    private static double computePower(double base, double exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent < 0) {
            return 1 / computePower(base, -exponent);
        }
        double result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }
}