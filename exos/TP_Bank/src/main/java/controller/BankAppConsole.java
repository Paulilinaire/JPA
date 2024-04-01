package controller;


import entity.Account;
import entity.Agency;
import entity.Customer;
import impl.AccountDAOImpl;
import impl.AgencyDAOImpl;
import impl.CustomerDAOImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class BankAppConsole {

    private static AccountDAOImpl accountDAO;
    private static CustomerDAOImpl customerDAO;
    private static AgencyDAOImpl agencyDAO;

    public static void main() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bank_db");
        accountDAO = new AccountDAOImpl(entityManagerFactory);
        customerDAO = new CustomerDAOImpl(entityManagerFactory);
        agencyDAO= new AgencyDAOImpl(entityManagerFactory);

        Scanner scanner = new Scanner(System.in);

        int choice;

        do {
            System.out.println("#### Bank ####");
            System.out.println("1. Ajouter une agence de banque");
            System.out.println("2. Ajouter un client");
            System.out.println("3. Ajouter un compte");
            System.out.println("4. Quitter l'application");
            System.out.println("Choix : ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consomme la nouvelle ligne

            switch (choice) {
                case 1:
                    addBankAgency(scanner);
                    break;
                case 2:
                    addCustomer(scanner);
                    break;
                case 3:
                    addBankAccount(scanner);
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choice != 3);
    }

    private static void addBankAgency(Scanner scanner) {
        System.out.println("Entrer l'adresse de la banque : ");
        String agencyAddress = scanner.nextLine();
        Agency agency = new Agency(agencyAddress);
        agencyDAO.addAgency(agency);
        System.out.println("Banque ajoutée");
    }

    private static void addCustomer(Scanner scanner){
        System.out.println("Entrer le prénom du client: ");
        String firstName = scanner.nextLine();

        System.out.println("Entrer le nom du client : ");
        String lastName = scanner.nextLine();

        System.out.println("Entrer la date de naissance du client (dd.MM.yyyy): ");
        String birthDateStr = scanner.nextLine();

        LocalDate birthDate = LocalDate.parse(
                birthDateStr,
                DateTimeFormatter.ofPattern("dd.MM.yyyy")
        );

        System.out.println("Entrer l'id de la banque auquelle appartient le client");
        int agencyId = scanner.nextInt();

        scanner.nextLine();

        Customer customer = new Customer(firstName, lastName, birthDate);
        customerDAO.addCustomer(customer, agencyId);
        System.out.println("Client ajouté");

    }

    private static void addBankAccount(Scanner scanner) {
        System.out.println("Entrer le libellé du compte : ");
        String accountName = scanner.nextLine();

        System.out.println("Entrer l'IBAN du compte : ");
        String accountIBAN = scanner.nextLine();

        System.out.println("Entrer le solde du compte : ");
        BigDecimal accountBalance = scanner.nextBigDecimal();

        System.out.println("Entrer l'id du client auquel appartient le compte");
        int customerId = scanner.nextInt();

        scanner.nextLine();

        Account account = new Account(accountName, accountIBAN, accountBalance);
        accountDAO.addAccount(account, customerId);
        System.out.println("Compte ajouté");
    }

}
