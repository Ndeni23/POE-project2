package part2;

class Login {
    private final Register register;

    public Login(Register register) {
        this.register = register;
    }

    public String loginUser(String enteredUsername, String enteredPassword) {
        if (register.username != null && register.username.equals(enteredUsername) && register.password != null && register.password.equals(enteredPassword)) {
            return "Welcome " + register.firstName + ", " + register.lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
