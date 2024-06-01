package library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Book {
    String title;
    String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String toString() {
        return title + " by " + author;
    }
}

public class LibraryManagementSystem extends JFrame implements ActionListener {
    private ArrayList<Book> books;
    private JTextField titleField, authorField;
    private JTextArea displayArea;

    public LibraryManagementSystem() {
        books = new ArrayList<>();

        setTitle("Library Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel titleLabel = new JLabel("Title: ");
        titleField = new JTextField();
        JLabel authorLabel = new JLabel("Author: ");
        authorField = new JTextField();
        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(this);
        JButton removeButton = new JButton("Remove Book");
        removeButton.addActionListener(this);

        displayArea = new JTextArea();
        displayArea.setEditable(false);

        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(authorLabel);
        panel.add(authorField);
        panel.add(addButton);
        panel.add(removeButton);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Book")) {
            String title = titleField.getText();
            String author = authorField.getText();

            if (!title.isEmpty() && !author.isEmpty()) {
                Book book = new Book(title, author);
                books.add(book);
                displayBooks();
                titleField.setText("");
                authorField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in both fields.");
            }
        } else if (e.getActionCommand().equals("Remove Book")) {
            String selectedBook = displayArea.getSelectedText();
            if (selectedBook != null) {
                for (int i = 0; i < books.size(); i++) {
                    if (books.get(i).toString().equals(selectedBook)) {
                        books.remove(i);
                        displayBooks();
                        break;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a book to remove.");
            }
        }
    }

    private void displayBooks() {
        displayArea.setText("");
        for (Book book : books) {
            displayArea.append(book.toString() + "\n");
        }
    }

    public static void main(String[] args) {
        new LibraryManagementSystem();
    }
}
