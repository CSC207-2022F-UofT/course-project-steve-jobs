package user.signup;

//DS that contains field info of the form user submits

public class SignUpInputDS {

    private final String firstName;

    private final String lastName;
    private final String password;
    private final String confirmPassword;
    private final String email;

    private final String username;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public SignUpInputDS(String firstName, String lastName, String password, String confirmPassword, String email, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.username = username;
    }
}
