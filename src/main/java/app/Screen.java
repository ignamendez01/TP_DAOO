package app;

import model.entities.employee.Employee;

public class Screen {


    public static void printInitialScreen() {
        System.out.println();
        System.out.println("***************************************");
        System.out.println("Welcome to Administration");
        System.out.println("1. Calculate Payroll");
        System.out.println("2. Generate Payroll Report");
        System.out.println("3. Manage Employees");
        System.out.println("0. Exit");
    }

    public static void printManageUsersScreen() {
        System.out.println();
        System.out.println("***************************************");
        System.out.println("1. Add new employee");
        System.out.println("2. Edit employee");
        System.out.println("3. Delete employee");
        System.out.println("4. View all employees");
        System.out.println("0. Go back");
    }

    public static void printEmployeeDataScreen(Employee employee) {
        System.out.println();
        System.out.println("***************************************");
        System.out.println("Employee data: ");
        System.out.println("Name: " + employee.getName());
        System.out.println("Phone number: " + employee.getPhoneNumber());
        //employee.getContract().print();
    }

    public static void chooseEditScreen() {
        System.out.println();
        System.out.println("***************************************");
        System.out.println("1. Change employee data");
        System.out.println("2. Change employee contract");
        System.out.println("3. Undo");
        System.out.println("4. Redo");
        System.out.println("0. Go back");
    }

    public static void chooseContractScreen() {
        System.out.println();
        System.out.println("***************************************");
        System.out.println("Now specify contract");
        System.out.println("1. Full time contract");
        System.out.println("2. Total hours contract");
    }

//    public static void fullTimeContractScreen() {
//        System.out.println("Contract: Full time contract");
//        System.out.println("Starting date: " + getStartDate().toString());
//        System.out.println("Finish date: " + getFinishDate().toString());
//        System.out.println("Pay per hour: " + getPayPerHour());
//        System.out.println("Hours per day: " + getHoursPerDay());
//
//    }
//
//    public static void totalHourContractScreen() {
//        System.out.println("Contract: Total hour contract");
//        System.out.println("Starting date: " + getStartDate().toString());
//        System.out.println("Finish date: " + getFinishDate().toString());
//        System.out.println("Pay per hour: " + getPayPerHour());
//        System.out.println("Total hours: " + getTotalHours());
//    }
}
