package ui;

import service.ExpenseService;
import model.Expense;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ExpenseTrackerUI extends JFrame {

    private ExpenseService service = new ExpenseService();

    public ExpenseTrackerUI() {
        setTitle("Expense Tracker");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField(15);

        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField(15);

        JButton addBtn = new JButton("Add Expense");
        JButton totalBtn = new JButton("Show Total");

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> expenseList = new JList<>(listModel);

        // Load existing expenses
        for (Expense e : service.getExpenses()) {
            listModel.addElement(e.toString());
        }

        addBtn.addActionListener((ActionEvent e) -> {
            try {
                String title = titleField.getText();
                double amount = Double.parseDouble(amountField.getText());

                service.addExpense(title, amount);
                listModel.addElement(title + " - ₹" + amount);

                titleField.setText("");
                amountField.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input!");
            }
        });

        totalBtn.addActionListener(e -> {
            double total = service.getTotal();
            JOptionPane.showMessageDialog(this, "Total Expenses: ₹" + total);
        });

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.add(titleLabel);
        inputPanel.add(titleField);
        inputPanel.add(amountLabel);
        inputPanel.add(amountField);
        inputPanel.add(addBtn);
        inputPanel.add(totalBtn);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(expenseList), BorderLayout.CENTER);

        setVisible(true);
    }
}
