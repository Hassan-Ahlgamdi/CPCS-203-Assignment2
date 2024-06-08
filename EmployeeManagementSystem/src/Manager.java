
import java.time.*;


public class Manager extends Employee implements Approver {
    
    // the default constructor
    public Manager(){}
    // the main constructor
    public Manager(String name, int id, double salary){
        super(name,id,salary);
    }
    
    //finish implementing the abstratcted method
    @Override
    public double calculateBonus() {
        return super.getSalary() *0.15;
    }

    //approve leave for the employee
    @Override
    public void approveLeave(Employee employee, LocalDate startDate, int id) {
        Leave l = new Leave(employee, startDate, id);
        employee.addLeaveRecord(l);
    }

    // mark the employee as working
    @Override
    public void markEmployeeAsWorking(Employee employee) {
        employee.setStateWorking();
    }
    
}
