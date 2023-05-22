package projectTodo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class App3 extends JFrame {
    private JTextField taskInputField;
    private DefaultListModel<String> todoListModel;
    private JList<String> todoList;

    public App3() {
        setTitle("TODO List App3lication");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Set a consistent look and feel for the App3lication
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create the task input field and buttons panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        taskInputField = new JTextField();
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        JButton prioritizeButton = new JButton("Prioritize");

        // Create the todo list
        todoListModel = new DefaultListModel<>();
        todoList = new JList<>(todoListModel);
        todoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(todoList);

        // Add components to the input panel
        inputPanel.add(taskInputField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        // Add components to the main panel
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add buttons to a separate panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 0));
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(prioritizeButton);

        // Add the button panel to the main panel
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(mainPanel);

        // Set a minimum size for the frame
        setMinimumSize(new Dimension(400, 300));

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // Set the frame visible
        setVisible(true);

        // Add event listeners to the buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = taskInputField.getText().trim();
                if (!task.isEmpty()) {
                    todoListModel.addElement(task);
                    taskInputField.setText("");
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = todoList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String updatedTask = JOptionPane.showInputDialog(App3.this, "Enter updated task:",
                            todoListModel.getElementAt(selectedIndex));
                    if (updatedTask != null && !updatedTask.isEmpty()) {
                        todoListModel.setElementAt(updatedTask, selectedIndex);
                    }
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = todoList.getSelectedIndex();
                if (selectedIndex != -1) {
                    todoListModel.remove(selectedIndex);
                }
            }
        });

        prioritizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = todoList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String task = todoListModel.remove(selectedIndex);
                    todoListModel.add(0, task);
                    todoList.setSelectedIndex(0);
                }
            }
        });
    }

    public static void main(String[] args) {
        // Use the Event Dispatch Thread for Swing components
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new App3();
            }
        });
    }
}
