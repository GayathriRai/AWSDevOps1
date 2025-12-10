package service;

import model.Expense;
import java.io.*;
import java.util.*;

public class ExpenseService {
    private List<Expense> expenses = new ArrayList<>();
    private final String FILE_NAME = "expenses.txt";

    public ExpenseService() {
        loadFromFile();
    }

    public void addExpense(String title, double amount) {
        expenses.add(new Expense(title, amount));
        saveToFile();
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public double getTotal() {
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }

    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Expense e : expenses) {
                pw.println(e.getTitle() + "," + e.getAmount());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                expenses.add(new Expense(parts[0], Double.parseDouble(parts[1])));
            }
        } catch (Exception ignored) {
        }
    }
}