package workflows;

import java.util.function.Consumer;

public class Node<T> implements Consumer<T>{
    private Consumer<T> operator;

    public void setOperator(Consumer<T> operator){
        this.operator = operator;
    }

    public void accept(T input){
        operator.accept(input);
    }
}
