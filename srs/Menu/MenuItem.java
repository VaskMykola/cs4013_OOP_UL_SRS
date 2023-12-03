package Menu;

import java.util.function.Consumer;
import java.util.function.Function;

class MenuItem {
    String label;
    Consumer<Object[]> consumer;
    Function<Object[], Object> function;

    Runnable func_to_run;
    Menu subMenu;

        public MenuItem(String label, Runnable func_to_run) {
        this.label = label;
        this.func_to_run = func_to_run;
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
