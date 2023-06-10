package app;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import controller.EmployeeController;
import controller.EmployeeControllerImpl;
import model.*;
import repository.EmployeeDatabase;
import repository.EmployeeDatabaseImpl;
import usescases.EmployeeUseCase;
import usescases.EmployeeUseCaseImpl;


public class App {

    private static final EmployeeDatabase employeeDatabase = new EmployeeDatabaseImpl();
    private static final EmployeeUseCase employeeService = new EmployeeUseCaseImpl(employeeDatabase);
    private static final EmployeeController employeeController = new EmployeeControllerImpl(employeeService);

    public static void main(String[] args) {
        printInitialMenu();
        Scanner scanner = new Scanner(System.in);
        int decision = scanner.nextInt();
        while (decision != 0) {
            switch (decision) {
                case 1 -> {
                    calculatePayroll();
                }
                case 2 -> {
                    generatePayrollReport();
                }
                case 3 -> {
                    manageUsers();
                }
            }
            printInitialMenu();
            decision = scanner.nextInt();
        }
    }

    private static void printInitialMenu() {
        System.out.println("***************************************");
        System.out.println("Welcome to Administration");
        System.out.println("1. Calculate Payroll");
        System.out.println("2. Generate Payroll Report");
        System.out.println("3. Manage Employees");
        System.out.println("0. Exit");
    }

    private static void calculatePayroll() {
        System.out.println("***************************************");
        System.out.println("Insert day to calculate payroll");
        Scanner scan = new Scanner(System.in);
        // logica para calcular payroll
        // imprimir payroll
    }

    private static void generatePayrollReport() {
        System.out.println("***************************************");
        System.out.println("Insert day to generate payroll report");
        // generar el reporte
        // imprimir el reporte
    }

    private static void manageUsers() {
        printManageUsersMenu();
        Scanner scanner = new Scanner(System.in);
        int decision = scanner.nextInt();
        while (decision != 0) {
            switch (decision) {
                case 1 -> {
                    adduser();
                }
                case 2 -> {
                    editUser();
                }
                case 3 -> {
                    deleteUser();
                }
            }
            printManageUsersMenu();
            decision = scanner.nextInt();
        }
    }

    private static void printManageUsersMenu() {
        System.out.println("1. Add new user");
        System.out.println("2. Edit user");
        System.out.println("3. Delete user");
        System.out.println("0. Go back");
    }

    private static void adduser() {
        String name = InputReader.getString("Enter employee full name: ");
        String phoneNumber = InputReader.getString("Enter employee phoneNumber: ");
        Contract contract = getContract();
        Employee employee = new Employee(name, phoneNumber, contract);
        employeeController.addEmployee(employee);
    }

    private static void editUser() {
        List<Versionable<Employee>> employees = employeeController.getEmployee();
        String employeeName = InputReader.getString("Select a employee to modify: ");
        Employee employee = null;
        for (Versionable<Employee> employeeVersionable : employees) {
            if (employeeVersionable.getActual().getName().equalsIgnoreCase(employeeName)) {
                employee = employeeVersionable.getActual();
                break;
            }
        }
        if (employee == null) {
            System.out.println("That employee does not exist, please select an existing employee");
            editUser();
        }
        else {
            chooseEditScreen();
            chooseEdit(employee);
        }
    }

    private static void chooseEditScreen() {
        System.out.println("1. Change employee data");
        System.out.println("2. Change employee contract");
    }

    private static void chooseEdit(Employee employee) {
        Scanner scanner = new Scanner(System.in);
        int decision = scanner.nextInt();
        Employee updatedEmployee;
        switch (decision) {
            case 1 -> {
                String name = InputReader.getString("Enter employee full name: ");
                String phoneNumber = InputReader.getString("Enter employee phoneNumber: ");
                updatedEmployee = new Employee(name, phoneNumber, employee.getContract());
            }
            case 2 -> {
                Contract contract = getContract();
                updatedEmployee = new Employee(employee.getName(), employee.getPhoneNumber(), contract);

            }
            default -> updatedEmployee = null;
        }
        employeeController.editEmployee(updatedEmployee, employee.getID());
    }

    private static Contract getContract() {
        Contract contract = null;
        System.out.println("Now specify contract");
        Scanner scanner = new Scanner(System.in);
        int decision = scanner.nextInt();
        switch (decision) {
            case 1 -> {
                LocalDate start = InputReader.getDate("Contract start date (dd-MM-yyyy): ");
                LocalDate finish = InputReader.getDate("Contract finish date (dd-MM-yyyy): ");
                int payPerHour = InputReader.getInt("Pay per hour: ");
                contract = new FullTimeContract(start, finish, payPerHour);
            }
            case 2 -> {
                LocalDate start = InputReader.getDate("Contract start date (dd-MM-yyyy): ");
                LocalDate finish = InputReader.getDate("Contract finish date (dd-MM-yyyy): ");
                int payPerHour = InputReader.getInt("Pay per hour: ");
                int totalHours = InputReader.getInt("Total hours: ");
                contract = new TotalHourContract(start, finish, payPerHour, totalHours);
            }
        }
        return contract;
    }


    private static void deleteUser() {

    }



}
