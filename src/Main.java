import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    //Frame set up
    public static JFrame Frame = new JFrame("Calculator");
    //Declaring components
    //Panels
    public static JPanel topPanel = new JPanel();
    public static JPanel digitPanel = new JPanel();
    public static JPanel functionalPanel = new JPanel();
    //Labels
    public static JLabel resultLabel = new JLabel("result goes here");
    //Text fields
    public static JTextField inputField = new JTextField(20);

    public static String input = "";

    public static String currentText;

    public static Double Result = 0.0;


    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {Calculator();}
        });
    }

    public static void Calculator(){

        Frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Declaration at the top of Main class
        inputField.setEditable(false);  //Set this field read-only. Declaration at the top of Main class

        GridLayout digitLayout = new GridLayout(4,3);
        GridLayout functionalLayout = new GridLayout(4,3);
        digitPanel.setLayout(digitLayout);
        functionalPanel.setLayout(functionalLayout);



        //Buttons
        JButton [] digitButtons = new JButton[10];

        topPanel.add(resultLabel);
        topPanel.add(inputField);

        //Digit buttons and their action listeners setup
        for(int i = 9 ; i >= 0 ; i--)
        {
            digitButtons[i] = new JButton(String.valueOf(i));
            digitPanel.add(digitButtons[i]);
            DigitButtonActionListener(digitButtons[i], i);
        }

        //Functional buttons setup
        JButton additionButton = new JButton("+");
        functionalPanel.add(additionButton);
        JButton deductionButton = new JButton("-");
        functionalPanel.add(deductionButton);
        JButton multiplicationButton = new JButton("×");
        functionalPanel.add(multiplicationButton);
        JButton divisionButton = new JButton("÷");
        functionalPanel.add(divisionButton);
        JButton percentageButton = new JButton("%");
        functionalPanel.add(percentageButton);
        JButton clearButton = new JButton("C");
        functionalPanel.add(clearButton);
        JButton equalsButton = new JButton("=");
        functionalPanel.add(equalsButton);
        JButton commaButton = new JButton(".");
        digitPanel.add(commaButton);
        JButton eraseButton = new JButton("⌫");
        digitPanel.add(eraseButton);

        //Functional buttons action listeners setup
        additionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentText = inputField.getText();
                inputField.setText(currentText + additionButton.getText());
            }
        });

        deductionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentText = inputField.getText();
                inputField.setText(currentText + deductionButton.getText());
            }
        });

        multiplicationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentText = inputField.getText();
                inputField.setText(currentText + multiplicationButton.getText());
            }
        });

        divisionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentText = inputField.getText();
                inputField.setText(currentText + divisionButton.getText());
            }
        });

        percentageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentText = inputField.getText();
                inputField.setText(currentText + percentageButton.getText());
                Percentage();
            }
        });

        eraseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Erase();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputField.setText("");
            }
        });

        commaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentText = inputField.getText();
                inputField.setText(currentText + commaButton.getText());
            }
        });

        equalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentText = inputField.getText();
                inputField.setText(currentText + equalsButton.getText());
                getResult();
            }
        });




        Frame.setLayout(new BorderLayout());
        Frame.add(topPanel, BorderLayout.NORTH);
        Frame.add(digitPanel, BorderLayout.WEST);
        Frame.add(functionalPanel, BorderLayout.CENTER);
        Frame.pack();
        Frame.setLocationRelativeTo(null);
        Frame.setVisible(true);


}

private static void DigitButtonActionListener(JButton button, int i){

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentText = inputField.getText();
                inputField.setText(currentText + String.valueOf(i));
            }
        });
}

    private static void Erase() {
        currentText = inputField.getText();
        char [] currentTextArray = currentText.toCharArray();
        int i = currentTextArray.length;
        currentText = "";

        for(int j = 0 ; j < i - 1 ; j++) {
            currentText += currentTextArray[j];
            System.out.println("Current text = " + currentText);
        }
        inputField.setText(currentText);
    }

    private static void Percentage() {

        char [] inputArr;
        int i = 0;
        int j = 0;
        double comp1 = 0;
        double comp2 = 0;
        boolean comp1Filled = false;
        String resultString = "";
        input = inputField.getText();
        inputArr = input.toCharArray();

        while (i < inputArr.length) {

            resultString += inputArr[i];

            if(     inputArr[i+1] == '%'
                    || inputArr[i+1] == '×') {

                if(comp1Filled) {
                    comp2 = Double.parseDouble(resultString);
                    break;
                }

                if(!comp1Filled) {
                    comp1 = Double.parseDouble(resultString);
                    i++;
                    j = i;
                    resultString = "";
                    comp1Filled = true;
                }

            }
            i++;

        }

        if (inputArr[j] == '×') {
            Result = (comp2 / comp1) * 100;
        } else {
            System.out.println("Default for percentage key method, " +
                    "this should appear if you chose a different " +
                    "operation key than multiplication.");
        }

        inputField.setText(Double.toString(Result));
    }

    private static void getResult() {

        char [] inputArr;
        int i = 0;
        int j = 0;
        double comp1 = 0;
        double comp2 = 0;
        boolean comp1Filled = false;
        String resultString = "";
        input = inputField.getText();
        inputArr = input.toCharArray();


        while (i < inputArr.length) {

            resultString += inputArr[i];

            if(     inputArr[i+1] == '+'
                    || inputArr[i+1] == '-'
                    || inputArr[i+1] == '×'
                    || inputArr[i+1] == '÷'
                    || inputArr[i+1] == '%'
                    || inputArr[i+1] == '=') {

                if(comp1Filled) {
                    comp2 = Double.parseDouble(resultString);
                    break;
                }

                if(!comp1Filled) {
                    comp1 = Double.parseDouble(resultString);
                    i++;
                    j = i;
                    resultString = "";
                    comp1Filled = true;
                }


            }
                i++;

        }

    switch (inputArr[j]) {

        case '+':
            Result = comp1 + comp2;
            break;

        case '-':
            Result = comp1 - comp2;
            break;

        case '×':
            Result = comp1 * comp2;
            break;

        case '÷':
            Result = comp1 / comp2;
            break;

        case '%':
            Result = comp1 * comp2;
            Result /= 100;
            System.out.println("!!!Yet to be implemented!!!");
            break;

        default:
            System.out.println("Default for operation sign choice, this shouldn't appear.");
    }

        inputField.setText(Double.toString(Result));

    }
}




