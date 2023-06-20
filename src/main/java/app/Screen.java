package app;

import model.dto.ContractDto;
import model.dto.EmployeeDto;
import model.dto.FullTimeContractDto;
import model.dto.TotalHourContractDto;

import java.util.List;

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

    public static void printEmployeeDataScreen(EmployeeDto employee) {
        System.out.println();
        System.out.println("***************************************");
        System.out.println("Employee data: ");
        System.out.println("Name: " + employee.getName());
        System.out.println("Phone number: " + employee.getPhoneNumber());
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

    public static void printContractData(ContractDto contract) { //TODO: Revisar instanceOf
        if (contract instanceof TotalHourContractDto) {
            System.out.println("Contract: Total hour contract");
            printContractEmployeeData(contract);
            System.out.println("Total hours: " + ((TotalHourContractDto) contract).getTotalHours());
        }
        else if (contract instanceof  FullTimeContractDto){
            System.out.println("Contract: Full time contract");
            printContractEmployeeData(contract);
            System.out.println("Hours per day: " + ((FullTimeContractDto) contract).getHoursPerPay());
        }
    }

    private static void printContractEmployeeData(ContractDto contract) {
        System.out.println("Starting date: " + contract.getStartDate().toString());
        System.out.println("Finish date: " + contract.getFinishDate().toString());
        System.out.println("Pay per hour: " + contract.getPayPerHour());
    }

    public static void printAllEmployees(List<EmployeeDto> employeeDtos) {
        for (int i = 0; i < employeeDtos.size(); i++) {
            System.out.println((i+1)+") "+employeeDtos.get(i).getName());
        }
    }
}
