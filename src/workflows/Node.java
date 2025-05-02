package workflows;

import java.util.function.Consumer;

public class Node<T> {
    private final Consumer<T> operator;

    public Node(Consumer<T> operator){
        this.operator = operator;
    }

    public void run(T input){
        operator.accept(input);
    }
}
