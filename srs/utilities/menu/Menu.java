package utilities.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a simple text-based menu system.
 * This class allows for the creation of a menu with multiple items,
 * each associated with an action. Users interact with the menu
 * via console input to select options.
 */
public class Menu {
    private List<MenuItem> items = new ArrayList<>();
    private String title;
    private boolean exitRequested = false;

    /**
     * Constructs a Menu with the specified title.
     *
     * @param title The title of the menu.
     */
    public Menu(String title) {
        this.title = title;
    }

    /**
     * Adds a menu item to this menu.
     *
     * @param item The MenuItem to be added to the menu.
     */
    public void addItem(MenuItem item) {
        items.add(item);
    }

    /**
     * Displays the menu and handles user interactions.
     * The menu will continue to display until the exit condition is met.
     * Users can select a menu item by entering the corresponding number.
     * Entering 0 exits the menu.
     */
    public void display() {
        Scanner scanner = new Scanner(System.in);
        while (!exitRequested) {
            System.out.println(title);
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i).label);
            }
            System.out.print("Choose an option (or 0 to exit): ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }

            int choice = scanner.nextInt();

            if (choice == 0) {
                exitRequested = true;
                break;
            }
            if (choice > 0 && choice <= items.size()) {
                MenuItem selectedItem = items.get(choice - 1);
                if (selectedItem.subMenu != null) {
                    selectedItem.subMenu.display();
                } else {
                    selectedItem.funcToRun.run();
                }
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
    }

    /**
     * Requests an exit from the menu.
     * This method will cause the menu to stop displaying after the current cycle.
     */
    public void exit() {
        exitRequested = true;
    }
}
