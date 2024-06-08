
// importing all the important libraries 
import java.util.*;
import java.time.*;
import java.io.*;

public class EmployeeManagementSystem {
    
    //static array list for all the employees
    private static ArrayList<Employee> employees = new ArrayList<Employee>() ;

    //this method will take id for the employee and search for him
    // if found will return the object of the employee, otherwise will return null
    private static Employee findEmployeeById(int id) {
        for (int i = 0; i < employees.size(); i++) {
            if (id == employees.get(i).getId()) {
                return employees.get(i);
            }
        }
        return null;
    }
    
//===================================================================================
    //this mehtod will take an employee object and add it to the array list
    // then will print confirmatino message with the emloyee type
    public static String addEmployee(Employee employee) {
        employees.add(employee);
        if (employee instanceof Developer) {
            return "Developer " + employee.getName() + " added";
        } else if (employee instanceof Designer) {
            return "Designer " + employee.getName() + " added";
        } else {
            return "Manager " + employee.getName() + " added";
        }
    }
//===================================================================================
    //this method will take id for the employee and remove him from the array list if found
    public static String removeEmployee(int id) {
        if (findEmployeeById(id) != null) {
            employees.remove(findEmployeeById(id));
            return "Employee removed successfully.";
        } else {
            return "Employee with ID " + id + " not found.";
        }
    }
//===================================================================================
    //this method will print all the employees informations in the array list
    public static String printAllEmployees() {
        StringBuilder list = new StringBuilder();

        // Append headers
        list.append(String.format("%-16s", "Name"));
        list.append(String.format("%-6s", "ID"));
        list.append(String.format("%-11s", "Salary"));
        list.append(String.format("%-7s", "Bonus"));
        list.append("\n");

        // Append employee details
        for (int i = 0; i < employees.size(); i++) {
            list.append(String.format("%-15s %-5d %-10.2f %-7.2f",
                    employees.get(i).getName(), employees.get(i).getId(),
                    employees.get(i).getSalary(), employees.get(i).calculateBonus()));
            list.append("\n");
        }

        return list.toString();
    }
//===================================================================================
    //this mehtod is going to take manager id and employee id
    //if both are exist, the manager will change the state of the employee as working
    //else, return failer message due to wrong id
    public static String markEmployeeAsWorking(int managerId, int employeeId) {
        Employee m = findEmployeeById(managerId); 
        Employee e = findEmployeeById(employeeId);
        
        if ((m == null) || (e == null)) {
            return "Change working state is faild. Either manager ID is incorrect or employee ID does not exist.";
        } else {
            ((Manager)m).markEmployeeAsWorking(e);
            return "Employee " + e.getName() + " is now marked as working.";
        }

    }
//===================================================================================
    
    //this mehtod will print all the leave recods for the givin employee id
    public static String printEmployeeLeaeveRecords(int employeeId) {
        String list = "";
        if (findEmployeeById(employeeId) == null) {
            return "Employee with ID " + employeeId + " is not found!";
        } else {
            ArrayList<Leave> l = findEmployeeById(employeeId).getLeaveRecords();
            list += "Leave Records for " + findEmployeeById(employeeId).getName() + ":\n";
            list += String.format("%-13s %s", "Start Date", "Days of Leave\n");
            for (int i = 0; i < l.size(); i++) {
                list += l.get(i).displayLeaveDetails();
            }
            return list;
        }
    }
//===================================================================================
    
    //this method will creat leave record for the employee by the maganer if both are exist
    //otherwise failer message is printed 
    public static String approveLeave(int managerId, int employeeId, LocalDate startDate, int days){
        Employee m = findEmployeeById(managerId); 
        Employee e = findEmployeeById(employeeId);
    
    
        if ( (m == null) || (e == null) || !(m instanceof Approver))
            return "Leave approval failed. Either manager ID is incorrect or employee ID does not exist.";
        else if (e.getState().equalsIgnoreCase("Leave"))
            return "Cannot approve leave for " + e.getName() + " as they are already on leave.";
        else {
            ((Manager)m).approveLeave(e, startDate, days);
            e.setStateOnLeave();
            return "Leave approved for " + e.getName();
        }
    }
//===================================================================================
    
    public static void readInput(String filePath) throws FileNotFoundException{
        //save the file if File object
        File file = new File(filePath);
        //creating scanner to read from the file
        Scanner readTheFile = new Scanner(file);
        //creating print writer to write the informations and the output in the output.txt file
        PrintWriter pw = new PrintWriter("output.txt");
        //String variables to take the informations red from the scanner
        String Line = ""; 
        String command = "";
        //check if the file is exist or not
        if (!(file.exists())) {
            System.out.println("File not found!!!");
            System.exit(0);
        }
        
        //loop over all the lines in the file
        while(readTheFile.hasNext()){
            //read line by line and split them by ',' 
            Line = readTheFile.nextLine();
            String[] inputs = Line.split(",");
            // the rest of the code is just checking the command input
            // and then send the arguments to the right methods above

            if (inputs[0].trim().equalsIgnoreCase("Add_Manager")){
                pw.println(addEmployee(new Manager(inputs[1].trim(),
                    Integer.parseInt(inputs[2].trim()),
                    Double.parseDouble(inputs[3].trim()))));
            } 
            //===================================================================================
            else if (inputs[0].trim().equalsIgnoreCase("Add_Developer")){
                pw.println(addEmployee(new Developer(inputs[1].trim(),
                    Integer.parseInt(inputs[2].trim()),
                    Double.parseDouble(inputs[3].trim()))));
            }
            //===================================================================================
            else if (inputs[0].trim().equalsIgnoreCase("Add_Designer")){
                pw.println(addEmployee(new Designer(inputs[1].trim(),
                    Integer.parseInt(inputs[2].trim()),
                    Double.parseDouble(inputs[3].trim()))));
            }
            //===================================================================================
            else if (inputs[0].trim().equalsIgnoreCase("Give_Leave")){
                pw.println(approveLeave(Integer.parseInt(inputs[1].trim()),
                        Integer.parseInt(inputs[2].trim()),
                        LocalDate.parse(inputs[3].trim()),
                        Integer.parseInt(inputs[4].trim())));
            }
            //===================================================================================
            else if(inputs[0].trim().equalsIgnoreCase("Change_State")){
                pw.println(markEmployeeAsWorking(Integer.parseInt(inputs[1].trim()),
                           Integer.parseInt(inputs[2].trim())));
            }
            //===================================================================================
            else if(inputs[0].trim().equalsIgnoreCase("del_Employee")){
                pw.println(removeEmployee(Integer.parseInt(inputs[1].trim())));
            }
            //===================================================================================
            else if (inputs[0].trim().equalsIgnoreCase("printAllEmployees")){
                pw.println(printAllEmployees());
            }
            //===================================================================================
            else if (inputs[0].trim().equalsIgnoreCase("print_Leave_Records")){
                pw.println(printEmployeeLeaeveRecords(Integer.parseInt(inputs[1].trim())));
            }
            
        }
        //if this line is printed in the console means evreything is done and you can check the file
        //output.txt
        System.out.println("Check the file");
        //close the reasources to save the memory 
        pw.flush();
        pw.close();
        readTheFile.close();
        
        //end of the program
    }
//===================================================================================
    
    // run the program from 'Main' class
    public static void main(String[] args) throws FileNotFoundException {
//        //save the file if File object
//        File file = new File("input.txt");
//        //creating scanner to read from the file
//        Scanner readTheFile = new Scanner(file);
//        //creating print writer to write the informations and the output in the output.txt file
//        PrintWriter pw = new PrintWriter("output.txt");
//        //String variables to take the informations red from the scanner
//        String Line = "";
//        String command = "";
//
//        //check if the file is exist or not
//        if (!(file.exists())) {
//            System.out.println("File not found!!!");
//            System.exit(0);
//        }
//        
//        
//        
//        
//        //loop over all the lines in the file
//        while(readTheFile.hasNext()){
//            //read line by line and split them by ',' 
//            Line = readTheFile.nextLine();
//            String[] inputs = Line.split(",");
//            // the rest of the code is just checking the command input
//            // and then send the arguments to the right methods above
//
//            if (inputs[0].trim().equalsIgnoreCase("Add_Manager")){
//                pw.println(addEmployee(new Manager(inputs[1].trim(),
//                    Integer.parseInt(inputs[2].trim()),
//                    Double.parseDouble(inputs[3].trim()))));
//            } 
//            //===================================================================================
//            else if (inputs[0].trim().equalsIgnoreCase("Add_Developer")){
//                pw.println(addEmployee(new Developer(inputs[1].trim(),
//                    Integer.parseInt(inputs[2].trim()),
//                    Double.parseDouble(inputs[3].trim()))));
//            }
//            //===================================================================================
//            else if (inputs[0].trim().equalsIgnoreCase("Add_Designer")){
//                pw.println(addEmployee(new Designer(inputs[1].trim(),
//                    Integer.parseInt(inputs[2].trim()),
//                    Double.parseDouble(inputs[3].trim()))));
//            }
//            //===================================================================================
//            else if (inputs[0].trim().equalsIgnoreCase("Give_Leave")){
//                pw.println(approveLeave(Integer.parseInt(inputs[1].trim()),
//                        Integer.parseInt(inputs[2].trim()),
//                        LocalDate.parse(inputs[3].trim()),
//                        Integer.parseInt(inputs[4].trim())));
//            }
//            //===================================================================================
//            else if(inputs[0].trim().equalsIgnoreCase("Change_State")){
//                pw.println(markEmployeeAsWorking(Integer.parseInt(inputs[1].trim()),
//                           Integer.parseInt(inputs[2].trim())));
//            }
//            //===================================================================================
//            else if(inputs[0].trim().equalsIgnoreCase("del_Employee")){
//                pw.println(removeEmployee(Integer.parseInt(inputs[1].trim())));
//            }
//            //===================================================================================
//            else if (inputs[0].trim().equalsIgnoreCase("printAllEmployees")){
//                pw.println(printAllEmployees());
//            }
//            //===================================================================================
//            else if (inputs[0].trim().equalsIgnoreCase("print_Leave_Records")){
//                pw.println(printEmployeeLeaeveRecords(Integer.parseInt(inputs[1].trim())));
//            }
//            
//        }
//        //if this line is printed in the console means evreything is done and you can check the file
//        //output.txt
//        System.out.println("Check the file");
//        //close the reasources to save the memory 
//        pw.flush();
//        pw.close();
//        readTheFile.close();
//        
//        //end of the program
    }

}
