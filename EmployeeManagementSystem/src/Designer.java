
public class Designer extends Employee {
    // the default constructor
    public Designer(){}
    // the main constructor
    public Designer(String name, int id, double salary){
        super(name,id,salary);
    }
    
    //finish implementing the abstratcted method
    
    @Override
    public double calculateBonus() {
        return super.getSalary() *0.10;
    }
    
    
}
