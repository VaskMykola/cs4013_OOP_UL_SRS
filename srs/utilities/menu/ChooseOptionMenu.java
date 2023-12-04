package utilities.menu;

import java.util.concurrent.atomic.AtomicReference;

/**
 * This utility class provides a method to create and display a menu for choosing one option.
 */
public class ChooseOptionMenu {

    /**
     * Private constructor to prevent instantiation of this utility class.
     *
     * @throws IllegalStateException if an attempt is made to instantiate this class
     */
    private ChooseOptionMenu() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Displays a menu with a list of options and allows the user to choose one.
     * <p>
     * This method creates a menu with the given label and a set of options.
     * The user's choice is captured and returned.
     * </p>
     *
     * @param label   The label for the menu.
     * @param options An array of options that will be displayed in the menu.
     * @return The selected option as a String. Returns null if no option is selected.
     */
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
}
