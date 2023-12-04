package utilities.menu;

/**
 * Represents a single item in a menu.
 * <p>
 * Each MenuItem has a label to display and an associated action that can be
 * either a {@link Runnable} to execute or a submenu to display.
 */
class MenuItem {
    /**
     * The label of the menu item, displayed in the menu.
     */
    String label;

    /**
     * The function to be executed when this menu item is selected.
     * This can be any {@link Runnable} action.
     */
    Runnable funcToRun;

    /**
     * An optional submenu associated with this menu item.
     * If not null, selecting this item will display the submenu.
     */
    Menu subMenu;

    /**
     * Constructs a new MenuItem with the specified label and action.
     * <p>
     * This constructor is used for menu items that perform an action when selected.
     *
     * @param label     The text to be displayed for this menu item.
     * @param funcToRun The {@link Runnable} action to be executed when this item is selected.
     */
    public MenuItem(String label, Runnable funcToRun) {
        this.label = label;
        this.funcToRun = funcToRun;
    }
}
