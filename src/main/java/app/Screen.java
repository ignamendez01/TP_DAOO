package app;

public class Screen {


    public static void printInitialMenu() {
        System.out.println("***************************************");
        System.out.println("Welcome to Administration");
        System.out.println("1. Calculate Payroll");
        System.out.println("2. Generate Payroll Report");
        System.out.println("3. Manage Employees");
        System.out.println("0. Exit");
    }

    public static void printManageUsersMenu() {
        System.out.println();
        System.out.println("***************************************");
        System.out.println("1. Add new user");
        System.out.println("2. Edit user");
        System.out.println("3. Delete user");
        System.out.println("0. Go back");
    }

    public static void chooseEditScreen() {
        System.out.println();
        System.out.println("***************************************");
        System.out.println("1. Change employee data");
        System.out.println("2. Change employee contract");
    }
}
