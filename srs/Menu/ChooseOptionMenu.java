package Menu;

import java.util.concurrent.atomic.AtomicReference;


public class ChooseOptionMenu {
    public static String chooseOptionMenu(String label, String[] options) {
        Menu chooseOptionMenu = new Menu(label);
        AtomicReference<String> selectedOption = new AtomicReference<>();

        for (String option : options) {
            chooseOptionMenu.addItem(new MenuItem(option, () -> {
                selectedOption.set(option);
                chooseOptionMenu.exit(); // Exit the menu when an option is selected
            }));
        }

        chooseOptionMenu.display();

        return selectedOption.get();
    }


    public static void main (String[] args) {
        String[] options = {"Option 1", "Option 2", "Option 3"};
        String selectedOption = chooseOptionMenu("Choose an option", options);
        System.out.println("Selected option: " + selectedOption);
    }
}
