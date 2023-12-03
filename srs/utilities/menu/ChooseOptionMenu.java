package utilities.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


public class ChooseOptionMenu {
    public static String chooseOneOptionMenu(String label, String[] options) {
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

    public static List<String> chooseManyOptionsMenu(String label, String[] options) {
        Menu chooseOptionMenu = new Menu(label);
        List<String> selectedOptions = new ArrayList<>();

        for (String option : options) {
            chooseOptionMenu.addItem(new MenuItem(option, () -> {
                // Check if the option is already in selectedOptions before adding
                if (!selectedOptions.contains(option)) {
                    selectedOptions.add(option);
                }
            }));
        }

        chooseOptionMenu.display();

        return selectedOptions;
    }


    public static void main(String[] args) {
        String[] options = {"Option 1", "Option 2", "Option 3"};
        List<String> selectedOption = chooseManyOptionsMenu("Choose an option", options);
        System.out.println("Selected option: " + selectedOption);
    }
}
