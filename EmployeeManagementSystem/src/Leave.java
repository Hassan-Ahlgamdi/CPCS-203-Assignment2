import java.util.*; 
import java.time.*;


public class Leave {
    private Employee employee; 
    private LocalDate startDate; 
    private int daysOfLeave; 
    
    // the main constructor
    public Leave(Employee employee, LocalDate startDate, int daysOfLeave) {
        this.employee = employee;
        this.startDate = startDate;
        this.daysOfLeave = daysOfLeave;
    }
    
    // print the leave details for the employee
    public String displayLeaveDetails(){
        return String.format("%-13s %s%n", this.startDate, this.daysOfLeave);
    }
}
