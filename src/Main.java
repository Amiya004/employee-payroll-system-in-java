import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Employee {

    private final String name;

    private final int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public abstract double calculateSalary();

    @Override
    public String toString() {
        return " [name="+name+", id="+id+", salary="+calculateSalary()+"]";
    }
}

class FullTimeEmployee extends Employee {

    private final double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary){
        super(name, id); // beacuse we want to run parent class constructor we use super

        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }

    @Override
    public String toString(){
        return "Full-Time Employee Details: " +super.toString();
    }

}

class PartTimeEmployee extends Employee {

    private final int hoursWorked;

    private final double hourlyRate;

    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate){
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate ;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate ;
    }

    @Override
    public String toString(){
        return "Part-Time Employee Details: " +super.toString();
    }
}

class PayrollSystem {
    private final List<Employee> employeeList;

    public PayrollSystem(){
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee){
        employeeList.add(employee);
    }

    public void removeEmployee(int id) {
        Employee employeeToRemove = null;
        for(Employee employee : employeeList){
            if(employee.getId() == id){
                employeeToRemove = employee;
                break;
            }
        }
        if(employeeToRemove != null){
            employeeList.remove(employeeToRemove);
        }
    }

    public void displayEmployees() {
        for(Employee employee : employeeList) {
            System.out.println(employee);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PayrollSystem payrollSystem = new PayrollSystem();

        while(true){
            System.out.println("*****************************************");
            System.out.println("    1. Add Full-Time Employees: ");
            System.out.println("    2. Add Part-Time Employess: ");
            System.out.println("    3. Display All Employees:   ");
            System.out.println("    4. Remove Employee by ID:   ");
            System.out.println("    5. Exit!!!                  ");
            System.out.println("*****************************************");
            System.out.println("Please Choose an Option:");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    System.out.println("Enter FullTime Employee Name:");
                    String ftName = scanner.nextLine();

                    System.out.println("Enter Full-Time Employee ID:");
                    int ftID = scanner.nextInt();

                    System.out.println("Enter monthly salary:");
                    double ftSalary = scanner.nextDouble();

                    scanner.nextLine();

                    FullTimeEmployee ftEmployee = new FullTimeEmployee(ftName, ftID, ftSalary);
                    payrollSystem.addEmployee(ftEmployee);

                    break;


                case 2:
                    System.out.println("Enter Part-Time Employee Name:");
                    String ptName = scanner.nextLine();

                    System.out.println("Enter Part-Time ID:");
                    int ptID = scanner.nextInt();

                    System.out.println("Enter hours worked:");
                    int hours = scanner.nextInt();

                    System.out.println("Enter hourly rate:");
                    double rate = scanner.nextDouble();

                    scanner.nextLine();

                    PartTimeEmployee ptEmployee = new PartTimeEmployee(ptName, ptID, hours, rate);
                    payrollSystem.addEmployee(ptEmployee);

                    break;


                case 3:
                    System.out.println("Employee Details:");
                    payrollSystem.displayEmployees();
                    break;

                case 4:
                    System.out.println("Enter ID of the employee to remove:");
                    int removeId = scanner.nextInt();
                    scanner.nextLine();
                    payrollSystem.removeEmployee(removeId);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    try{
                        Thread.sleep(2500);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println("Exited From The Program🙂");
                    scanner.close();
                    System.exit(0);
                    break;


                default:
                    System.out.println("Invalid Choice!!! Try again...");


            }
        }
    }
}