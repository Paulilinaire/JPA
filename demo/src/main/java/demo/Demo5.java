package demo;

import entity.oneToMany.Department;
import entity.oneToMany.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class Demo5 {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo");

    public static void main() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Employee employee = new Employee();
        Employee employee1 = new Employee();
        employee.setName("Michel");
        employee1.setName("Patrick");

        Department department = new Department();
        department.setDepName("Comptable");

        employee.setDepartement(department);
        employee1.setDepartement(department);

        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(employee);
        employeeList.add(employee1);

        department.setEmployeeList(employeeList);

        em.persist(department);

        em.getTransaction().commit();

        Department department1 = em.find(Department.class, 1L);

        for (Employee e: department1.getEmployeeList()) {
            System.out.println("Employees du département1: " + e);

        }

        em.close();
        emf.close();


    }


}
