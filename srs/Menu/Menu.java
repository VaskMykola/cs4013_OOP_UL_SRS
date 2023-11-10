package Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Represents a single menu item

// Represents a menu with multiple items
class Menu {
    List<MenuItem> items = new ArrayList<>();
    String title;

    public Menu(String title) {
        this.title = title;
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(title);
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i).label);
            }
            System.out.print("Choose an option (or 0 to exit): ");
            int choice = scanner.nextInt();
            if (choice == 0) break;
            if (choice > 0 && choice <= items.size()) {
                MenuItem selectedItem = items.get(choice - 1);
                if (selectedItem.subMenu != null) {
                    selectedItem.subMenu.display();
                } else {
                    selectedItem.action.run();
                }
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
    }
}


