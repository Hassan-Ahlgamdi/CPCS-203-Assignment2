
public class Developer extends Employee {
    // the default constructor
    public Developer(){}
    // the main constructor
    public Developer(String name, int id, double salary){
        super(name,id,salary);
    }
    
    //finish implementing the abstratcted method
    
    @Override
    public double calculateBonus() {
        return super.getSalary() *0.12;
    }
}
