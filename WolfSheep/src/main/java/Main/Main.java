package Main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import wolfSheepSimulation.WolfSheepSimulation;

public class Main extends JFrame {
    public JTextField widthField;
    public JTextField heightField;
    public JTextField msecondsField;
    public JTextField sheepAmountField;
    public JLabel errorLabel;

    Main() {
        InitUI();
    }
    
    void InitUI()
    {
        setTitle("InfoInput");
        setSize(500, 500);
        setLocationRelativeTo(null);
        /*JLabel background = new JLabel();
        ImageIcon ii = new ImageIcon(this.getClass().getResource("leszyani.gif"));
        background.setIcon(ii);
        this.add(background, BorderLayout.WEST);*/
        GridLayout layout = new GridLayout(5,2);
        this.setLayout(layout);
        
        JLabel widthLabel = new JLabel("Podaj szerokość:");
        widthField = new JTextField("Width",10);

        JLabel heightLabel = new JLabel("Podaj wysokość:");
        heightField = new JTextField("Height",10);

        JLabel msecondsLabel = new JLabel("Podaj odstęp czasowy:");
        msecondsField = new JTextField("Timer",10);

        JLabel sheepAmountLabel =new JLabel("Podaj ilość owiec:");
        sheepAmountField = new JTextField("Number of Sheeps",10);

        errorLabel =new JLabel("");
        MyConfirmButton OKbutton = new MyConfirmButton("OK",this);

        this.add(widthLabel);
        this.add(widthField);
        this.add(heightLabel);
        this.add(heightField);
        this.add(msecondsLabel);
        this.add(msecondsField);
        this.add(sheepAmountLabel);
        this.add(sheepAmountField);
        this.add(OKbutton);
        this.add(errorLabel);
        this.pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Main InsertInfo = new Main();
        InsertInfo.setVisible(true);
    }
}

class MyConfirmButton extends JButton implements ActionListener {
    Main Input;
    public MyConfirmButton(String text,Main InfoInput) {
        super.setText(text);
        addActionListener(this);
        Input = InfoInput;
    }

    @Override
	public void actionPerformed(ActionEvent e) {
        String widthString, heightString, msecondsString, sheepAmountString;
        int width, height, mseconds, sheepAmount;
        try{
            widthString = Input.widthField.getText();
            heightString = Input.heightField.getText();
            msecondsString = Input.msecondsField.getText();
            sheepAmountString = Input.sheepAmountField.getText();
            width = Integer.parseInt(widthString);
            height = Integer.parseInt(heightString);
            mseconds = Integer.parseInt(msecondsString);
            sheepAmount = Integer.parseInt(sheepAmountString);
            new WolfSheepSimulation(width, height, mseconds, sheepAmount);
            Input.setVisible(false);
        } catch(NumberFormatException ex) {
            Input.errorLabel.setText("Invalid input");
        } catch(NullPointerException ex) {
            Input.errorLabel.setText("Empty input");
        }
    }
}