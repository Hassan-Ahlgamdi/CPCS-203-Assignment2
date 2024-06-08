
import java.time.*;




public interface Approver {
    
    //interface with two methods for the manager class
    public abstract void approveLeave(Employee employee, LocalDate startDate, int id);
    public abstract void markEmployeeAsWorking(Employee employee);
}
