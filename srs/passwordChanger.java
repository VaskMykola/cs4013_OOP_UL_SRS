public class passwordChanger {
    private static Scanner scanner = new Scanner(System.in);

    private static String getUserInput() {
        return scanner.nextLine();
    }

    public void changePassword() {
        System.out.println("Password changing...");
        while (true) {
            System.out.println("Enter old password: ");
            String oldPassword = getUserInput();
            System.out.println("Enter new password: ");
            String newPassword = getUserInput();
            System.out.println("Enter new password again: ");
            String newPasswordAgain = getUserInput();

            if (!newPassword.equals(newPasswordAgain)) {
                System.out.println("Passwords do not match.");
                continue;
            }

            if (oldPassword.equals(this.password)) {
                this.password = newPassword;
                System.out.println("Password changed successfully.");
                updatePasswordInCSV(newPassword);
                break;
            } else {
                System.out.println("Wrong password.");
            }
        }
    }

    private void updatePasswordInCSV(String newPassword) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(CSV_FILE_PATH));
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] fields = line.split(CSV_DELIMITER);
                if (fields.length == 4 && fields[0].equals(this.login) && fields[2].equals(this.role)) {
                    fields[1] = newPassword;
                    lines.set(i, String.join(CSV_DELIMITER, fields));
                    break;
                }
            }
            Files.write(Paths.get(CSV_FILE_PATH), lines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ex) {
            System.out.println("Error updating password in CSV file: " + ex.getMessage());
        }
    }

}
