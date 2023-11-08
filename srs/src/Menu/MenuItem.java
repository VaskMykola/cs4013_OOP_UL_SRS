package Menu;
class MenuItem {
    String label;
    Runnable action;
    Menu subMenu;

    public MenuItem(String label, Runnable action) {
        this.label = label;
        this.action = action;
    }

    public MenuItem(String label, Menu subMenu) {
        this.label = label;
        this.subMenu = subMenu;
    }
}
