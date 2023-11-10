package Menu;
class MenuItem {
    /**************************************
     *      D O N ' T   T O U C H
     *     /                       \
     *    /    T H I S   F I L E   \
     *   /                           \
     *  /                             \
     * /                               \
     * ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! !
     **************************************/

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
