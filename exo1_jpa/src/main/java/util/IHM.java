package util;

import model.Todo;
import service.ITodoService;

import java.util.Scanner;

public class IHM {
    private Scanner scanner;

    private String choice;

    private ITodoService _todoService;

    public IHM(ITodoService todoService) {
        scanner = new Scanner(System.in);
        _todoService = todoService;
    }

    public void start() {
        do {
            menu();
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addTodo();
                    break;
                case "2":
                    showAllTodos();
                    break;
                case "3":
//                    updateTodo();
                    break;
                case "4":
//                    deleteTodo();
                    break;
            }

        } while (!choice.equals("0"));
    }

    private void menu() {
        System.out.println("########## Menu Principal ##########");
        System.out.println("1- Ajouter une tâche");
        System.out.println("2- Afficher toutes les tâches ");
        System.out.println("3- Marquer une tâche comme terminée");
        System.out.println("4- Supprimer une tâche de la liste");
        System.out.println("0- Quitter");
        System.out.println(" Votre choix : ");
    }

    private void addTodo(){
        System.out.println("##### Choix 1 #####");
        System.out.println("Merci de saisir le titre de la tâche : ");
        String title = scanner.nextLine();
        System.out.println("Merci de saisir le status de la tâche ('en cours' ou 'terminé'): ");
        String statusInput = scanner.nextLine();
        boolean status;
        if (statusInput.equalsIgnoreCase("terminé")) {
            status = true;
        } else if (statusInput.equalsIgnoreCase("en cours")) {
            status = false;
        } else {
            System.out.println("Statut non reconnu. Utilisation de la valeur par défaut (false).");
            status = false;
        }

        Todo todo = _todoService.addTodo(title, status);
    }

    private void showAllTodos(){
        System.out.println("##### Choix 2 #####");
        _todoService.getAllTodos();
    }


}
