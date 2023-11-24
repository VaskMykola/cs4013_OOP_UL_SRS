package users;

public class User2 {
    private String login;
    private String password;
    private String role;

    public User2(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void showProfileInfo() {
        System.out.println("Profile info:");
        System.out.println("Login: " + this.login);
        System.out.println("Role: " + this.role);
        // Add more profile information here as needed
    }

}
