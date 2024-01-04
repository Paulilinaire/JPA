import service.TodoListService;
import controller.IHM;

public class Main {
    public static void main(String[] args) {
        new IHM(new TodoListService()).start();
    }
}
