package utilities.menu;

import java.util.function.Consumer;
import java.util.function.Function;

class MenuItem {
    String label;
    Consumer<Object[]> consumer;
    Function<Object[], Object> function;

    Runnable funcToRun;
    Menu subMenu;

        public MenuItem(String label, Runnable funcToRun) {
        this.label = label;
        this.funcToRun = funcToRun;
    }
    public MenuItem(String label, Consumer<Object[]> consumer) {
        this.label = label;
        this.consumer = consumer;
    }

    public MenuItem(String label, Function<Object[], Object> function) {
        this.label = label;
        this.function = function;
    }

    public MenuItem(String label, Menu subMenu) {
        this.label = label;
        this.subMenu = subMenu;
    }

    public void execute(Object... args) {
        if (consumer != null) {
            consumer.accept(args);
        } else if (function != null) {
            function.apply(args);
        }
    }
}
