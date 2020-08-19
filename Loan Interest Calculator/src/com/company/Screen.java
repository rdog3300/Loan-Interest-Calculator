package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

/**
 * Screen Class
 * Represents the application user interface
 */
public class Screen extends JFrame {
    /*ATTRIBUTES*/
    private JPanel panel = new JPanel();
    private LoanSystem SYS = new LoanSystem();

    /*CONSTRUCTORS*/

    /**
     * Default Argumented Constructor
     * @param title The title of the screen
     */
    public Screen(String title)
    {
        super(title);
        setSize(650,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        panel.setLayout(null);
    }

    /*METHODS*/
    public void setScreen(boolean hasNumPayments)
    {
        setLabels();
        setTextFields(hasNumPayments);
        setButtons(hasNumPayments);
        setTextArea();
        getContentPane().add(panel);
        revalidate();
    }

    private void setLabels()
    {
        JLabel balance_label = new JLabel("Loan Balance");
        JLabel interest_label = new JLabel("Interest Rate");
        JLabel number_payment_label = new JLabel("Number of Payments");
        JLabel monthly_payment_label = new JLabel("Monthly Payment");
        JLabel Loan_Analysis_label = new JLabel("Loan Analysis:");

        balance_label.setBounds(10,10,150,25);
        interest_label.setBounds(10,50,150,25);
        number_payment_label.setBounds(10,90,150,25);
        monthly_payment_label.setBounds(10,130,150,25);
        Loan_Analysis_label.setBounds(350,10,150,25);

        balance_label.setFont(new Font("Arial",Font.PLAIN,16));
        interest_label.setFont(new Font("Arial",Font.PLAIN,16));
        number_payment_label.setFont(new Font("Arial",Font.PLAIN,16));
        monthly_payment_label.setFont(new Font("Arial",Font.PLAIN,16));
        Loan_Analysis_label.setFont(new Font("Arial",Font.PLAIN,16));



        panel.add(balance_label);
        panel.add(interest_label);
        panel.add(number_payment_label);
        panel.add(monthly_payment_label);
        panel.add(Loan_Analysis_label);
    }

    public void setTextFields(boolean hasNumPayments)
    {
        JTextField balance_input = new JTextField();
        JTextField interest_input = new JTextField();
        JTextField number_payment_input = new JTextField();
        JTextField monthly_payment_input = new JTextField();

        balance_input.setBounds(170,10,100,25);
        interest_input.setBounds(170,50,100,25);
        number_payment_input.setBounds(170,90,100,25);
        monthly_payment_input.setBounds(170,130,100,25);


        if(hasNumPayments)
        {
            monthly_payment_input.setEditable(false);
            monthly_payment_input.setBackground(Color.YELLOW);
        }
        else
        {
            number_payment_input.setEditable(false);
             number_payment_input.setBackground(Color.YELLOW);
        }

        panel.add(balance_input);
        panel.add(interest_input);
        panel.add(number_payment_input);
        panel.add(monthly_payment_input);

    }

    private void setButtons(boolean hasNumberPayments)
    {
        JButton ComputeAssessment_BTN = new JButton("Compute Monthly Assesment");
        JButton NewLoan_Button = new JButton("New Loan Analysis");
        JButton X_NumPay_BTN = new JButton("X");
        JButton X_MonPay_BTN = new JButton("X");
        JButton Exit_BTN = new JButton("Exit");

        ComputeAssessment_BTN.setBounds(25,175,225,25);
        NewLoan_Button.setBounds(60,225,150,25);
        X_NumPay_BTN.setBounds(280,90,60,25);
        X_MonPay_BTN.setBounds(280,130,60,25);
        Exit_BTN.setBounds(435,230,80,25);

        NewLoan_Button.setEnabled(false);

        Exit_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        ComputeAssessment_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(hasNumberPayments) {
                    if (!RegEx.isDigit(((JTextField) panel.getComponentAt(170, 10)).getText()) && !RegEx.isDouble(((JTextField) panel.getComponentAt(170, 10)).getText())) {
                        panel.getComponentAt(170, 10).setBackground(Color.RED);
                        panel.getComponentAt(170, 50).setBackground(Color.WHITE);
                        panel.getComponentAt(170, 90).setBackground(Color.WHITE);
                    } else if (!RegEx.isDigit(((JTextField) panel.getComponentAt(170, 50)).getText()) && !RegEx.isDouble(((JTextField) panel.getComponentAt(170, 50)).getText())) {
                        panel.getComponentAt(170, 50).setBackground(Color.RED);
                        panel.getComponentAt(170, 10).setBackground(Color.WHITE);
                        panel.getComponentAt(170, 90).setBackground(Color.WHITE);
                    } else if (!RegEx.isDigit(((JTextField) panel.getComponentAt(170, 90)).getText())) {
                        panel.getComponentAt(170, 90).setBackground(Color.RED);
                        panel.getComponentAt(170, 10).setBackground(Color.WHITE);
                        panel.getComponentAt(170, 50).setBackground(Color.WHITE);

                    } else {
                        panel.getComponentAt(170, 10).setBackground(Color.WHITE);
                        panel.getComponentAt(170, 50).setBackground(Color.WHITE);
                        panel.getComponentAt(170, 90).setBackground(Color.WHITE);

                        clearLoanSystem();
                        getData(hasNumberPayments);
                        Analyze_Data(hasNumberPayments);
                        NewLoan_Button.setEnabled(true);
                    }
                }
                else
                {
                    if (!RegEx.isDouble(((JTextField) panel.getComponentAt(170, 10)).getText())) {
                        panel.getComponentAt(170, 10).setBackground(Color.RED);
                        panel.getComponentAt(170, 50).setBackground(Color.WHITE);
                        panel.getComponentAt(170, 130).setBackground(Color.WHITE);
                    } else if (!RegEx.isDigit(((JTextField) panel.getComponentAt(170, 50)).getText()) && !RegEx.isDouble(((JTextField) panel.getComponentAt(170, 50)).getText())) {
                        panel.getComponentAt(170, 50).setBackground(Color.RED);
                        panel.getComponentAt(170, 10).setBackground(Color.WHITE);
                        panel.getComponentAt(170, 130).setBackground(Color.WHITE);
                    } else if (!RegEx.isDigit(((JTextField) panel.getComponentAt(170, 130)).getText()) && !RegEx.isDouble(((JTextField) panel.getComponentAt(170, 130)).getText())) {
                        panel.getComponentAt(170, 130).setBackground(Color.RED);
                        panel.getComponentAt(170, 10).setBackground(Color.WHITE);
                        panel.getComponentAt(170, 50).setBackground(Color.WHITE);
                    } else {
                        panel.getComponentAt(170, 10).setBackground(Color.WHITE);
                        panel.getComponentAt(170, 50).setBackground(Color.WHITE);
                        panel.getComponentAt(170, 130).setBackground(Color.WHITE);

                        clearLoanSystem();
                        getData(hasNumberPayments);
                        Analyze_Data(hasNumberPayments);
                        NewLoan_Button.setEnabled(true);
                    }
                }
            }
        });

        NewLoan_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearScreen();
                clearLoanSystem();
                panel = new JPanel();
                panel.setLayout(null);
                setScreen(hasNumberPayments);
            }
        });

        X_MonPay_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearScreen();
                clearLoanSystem();
                panel = new JPanel();
                panel.setLayout(null);
                setScreen(true);
            }
        });

        X_NumPay_BTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearScreen();
                clearLoanSystem();
                panel = new JPanel();
                panel.setLayout(null);
                setScreen(false);
            }
        });

        if(hasNumberPayments)
        {
            X_MonPay_BTN.setEnabled(false);
            X_MonPay_BTN.setVisible(false);
        }
        else
        {
            X_NumPay_BTN.setEnabled(false);
            X_NumPay_BTN.setVisible(false);
        }

        panel.add(ComputeAssessment_BTN);
        panel.add(NewLoan_Button);
        panel.add(X_MonPay_BTN);
        panel.add(X_NumPay_BTN);
        panel.add(Exit_BTN);

    }

    public void setTextArea()
    {
        JTextArea area = new JTextArea();
        area.setEditable(false);

        area.setBounds(350,35,250,185);
        area.setFont(new Font("Arial",Font.PLAIN,16));

        panel.add(area);
    }

    private void clearLoanSystem()
    {
        SYS.clear();
    }

    private void clearScreen()
    {
        getContentPane().remove(panel);
    }

    private void getData(boolean hasNumberPayments) throws InputMismatchException {
        Component C;
        C = panel.getComponentAt(170, 10);
            SYS.setBalance(new Double(((JTextField) C).getText()));

        C = panel.getComponentAt(170, 50);
            SYS.setInterest_rate((new Double(((JTextField) C).getText())) / 100);
        if (hasNumberPayments) {
            C = panel.getComponentAt(170, 90);
                SYS.setNumber_payments(new Integer(((JTextField) C).getText()));
        } else {
            C = panel.getComponentAt(170, 130);
                SYS.setMonthly_payment(new Double(((JTextField) C).getText()));
        }
    }

        private void Analyze_Data(boolean hasNumPayments)
        {
            SYS.Analyze(hasNumPayments);
            ((JTextArea)panel.getComponentAt(350,35)).setText(SYS.getAnalysis());

            if(hasNumPayments)
            {
                ((JTextField)panel.getComponentAt(170,130)).setText("" + SYS.getMonthly_payment_Str());

            }
            else
            {
                ((JTextField)panel.getComponentAt(170,90)).setText("" + SYS.getNumber_payments_Str());
            }
            revalidate();
        }



}
