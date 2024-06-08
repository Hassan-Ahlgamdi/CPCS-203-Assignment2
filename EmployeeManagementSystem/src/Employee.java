
import java.util.*;
import java.time.*;


public abstract class Employee {
    
    private String name; 
    private int id; 
    private double salary; 
    private String state; 
    private ArrayList<Leave> leaveRecords = new ArrayList<Leave>();
    
    // the default constructor
    public Employee() {
    }
    // the main constructor
    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
        this.state = "Working";
    }
    
    //abstract method, every employee has diffrente bonus calculation
    public abstract double calculateBonus();
    
    //some setters and getter
    
    //update the state to leave
    public void setStateOnLeave(){
        this.state = "leave";   
    }
    
    //update the state to working
    public void setStateWorking(){
        this.state = "Working";
    }
    
    
    public ArrayList getLeaveRecords(){
        return this.leaveRecords;
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getId(){
        return this.id; 
    }
    
    public String getState(){
        return this.state;
    }
    
    //add leave reacord from the manager class
    public void addLeaveRecord(Leave l){
        this.leaveRecords.add(l);
    }
    
    public double getSalary(){
        return this.salary;
    }

    @Override
    public String toString() {
        return "Employee{" + "name=" + name + ", id=" + id + ", salary=" + salary + ", state=" + state + '}';
    }
 
    
}
