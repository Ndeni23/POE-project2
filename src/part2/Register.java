package part2;


import javax.swing.JOptionPane;

/**
 *
 * @author RC_Student_lab
 */

class Register {
    protected String username;
    protected String password;
    protected String firstName;
    protected String lastName;

    public Register() {
    }

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        boolean hasUpperCase = false;
        boolean hasNumber = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
        }

        return password.length() >= 8 && hasUpperCase && hasNumber && hasSpecialChar;
    }

    // Modify registerUser to use JOptionPane
    public String registerUser() {
        while (true) {
            username = JOptionPane.showInputDialog("Enter your username (must contain an underscore and be no more than 5 characters):");
            password = JOptionPane.showInputDialog("Enter your password (must be at least 8 characters long, contain a capital letter, a number, and a special character):");

            if (checkUserName(username) && checkPasswordComplexity(password)) {
                firstName = JOptionPane.showInputDialog("Enter your first name:");
                lastName = JOptionPane.showInputDialog("Enter your last name:");
                return "User successfully registered.";
            } else {
                JOptionPane.showMessageDialog(null, "Your username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.\nOR\nYour password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.");
            }
        }
    }
}
