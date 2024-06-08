

import java.util.*;
import java.io.*;
public class Main {

    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner read = new Scanner(System.in);
        //you should write the file in this format 'name_of_the_file.txt'
        //or you can just press Enter to make it read the default file 'input.txt'
        System.out.println("Enter the File Path: Ex(input.txt), or (c:\\users\\..\\input.txt): ");
        String filePath = read.nextLine();
        if (filePath.equalsIgnoreCase(".*.txt")){
            EmployeeManagementSystem.readInput(filePath);
        }
        else 
        EmployeeManagementSystem.readInput("input.txt");
    }
    
}
