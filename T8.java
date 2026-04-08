import java.awt.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class T8 {

    JFrame frame = new JFrame();
    JPanel mainPanel;
    JTextField nameField, emailField;
    JPasswordField passwordField;
    JComboBox depField;

    T8() {
        frame.getContentPane().setLayout(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(home());

        frame.setVisible(true);
    }

    JPanel home() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int height = 590,
            width = 850;
        int x = (screen.width / 2) - (width / 2),
            y = (screen.height / 2) - (height / 2);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.GRAY);
        mainPanel.setBounds(x, y, width, height);

        nameField = (JTextField) addComponent("Name", 30, "JTextField");
        emailField = (JTextField) addComponent("Email", 80, "JTextField");
        passwordField = (JPasswordField) addComponent("Password", 130, "Password");
        depField = (JComboBox) addComponent("Department", 180, "comboBox");

        mainPanel.add(tree(30));
        mainPanel.add(button("submit", 230, false));
        mainPanel.add(button("clear", 230, true));

        return mainPanel;
    }

    JComponent addComponent(String labelText, int y_posistion, String type) {
        JLabel label = new JLabel(labelText);
        label.setBounds(50, y_posistion, 100, 25);
        label.setForeground(Color.WHITE);
        JComponent field;

        switch (type) {
            default: case "JTextField":
                field = new JTextField();
                break;
            case "Password":
                field = new JPasswordField();
                break;
            case "comboBox":
                field = new JComboBox<>(new String[] { "IT", "Finance", "HR", "Marketing" });
                break;
        }

        field.setBounds(160, y_posistion, 200, 25);
        mainPanel.add(field);
        mainPanel.add(label);
        return field;
    }

    JTree tree(int y_posistion) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Company");

        DefaultMutableTreeNode it = new DefaultMutableTreeNode("IT");
        it.add(new DefaultMutableTreeNode("Backend Team"));
        it.add(new DefaultMutableTreeNode("Frontend Team"));
        root.add(it);

        DefaultMutableTreeNode finance = new DefaultMutableTreeNode("Finance");
        finance.add(new DefaultMutableTreeNode("Ledger"));
        root.add(finance);

        JTree tree = new JTree(root);
        tree.setBounds(480, y_posistion, 280, 300);
        return tree;
    }

    JButton button(String label, int y_position, boolean clear) {
        JButton button = new JButton(label);
        if (!clear) {
            button.setBounds(60, y_position, 100, 25);
            button.addActionListener(e -> {
                printLogic();
            });
        } else {
            button.setBounds(260, y_position, 100, 25);
            button.addActionListener(e -> {
                clear();
            });
        }

        return button;
    }

    void clear() {
        nameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        depField.setSelectedIndex(0);
    }

    void printLogic() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String dep = (String) (depField.getSelectedItem());
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame,"Make sure to satsfy all Fields");
            return;
        }
        if (!email.contains("@") || !email.contains(".com")) {
            JOptionPane.showMessageDialog(frame, "Email is not Valid");
            return;
        }

        JOptionPane.showMessageDialog(frame,"Name: " + name + "\n" + "Email: " + email +"\n" +"Password: " +
                  "password is hidden, subscribe to my twitter to get it" +
                "\n" +
                "Department: " +
                dep
        );
    }

    public static void main(String[] args) {
        new T8();
    }
}
