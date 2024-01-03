import service.TodoListService;
import util.IHM;

public class Main {
    public static void main(String[] args) {
        new IHM(new TodoListService()).start();
    }
}
