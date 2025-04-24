package workflows;

public class StateGraph<T>{

    public StateGraph() {
    }

    public StateGraph addNode(){
        return this;
    }

    public StateGraph addEdge(){
        return this;
    }

    public void setInitial(String node){

    }

    public void setFinal(String node){

    }

    public String run(T input, boolean debug){

    }
}
