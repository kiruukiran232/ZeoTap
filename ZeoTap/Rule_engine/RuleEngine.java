package Rule_engine;

import java.util.*;
interface ASTNode{
    boolean evaluate(User user);
}
class User{
    private int age;
    private String department;
    private double income;
    private double spend;
    public User(int age,String department,double income,double spend){
        this.age=age;
        this.department=department;
        this.income=income;
        this.spend=spend;
    }
    public int getAge(){
        return age;
    }
    public String getDepartment(){
        return department;
    }
    public double getIncome(){
        return income;
    }
    public double getSpend(){
        return spend;
    }
}
class AgeCondition implements ASTNode{
    private int minAge;
    public AgeCondition(int minAge){
        this.minAge=minAge;
    }
    @Override
    public boolean evaluate(User user){
        return user.getAge()>=minAge;
    }
}
class DepartmentCondition implements ASTNode{
    private String department;
    public DepartmentCondition(String department) {
        this.department=department;
    }
    @Override
    public boolean evaluate(User user){
        return user.getDepartment().equals(department);
    }
}
class IncomeCondition implements ASTNode{
    private double minIncome;
    public IncomeCondition(double minIncome){
        this.minIncome=minIncome;
    }
    @Override
    public boolean evaluate(User user){
        return user.getIncome()>=minIncome;
    }
}
class AndNode implements ASTNode{
    private ASTNode left;
    private ASTNode right;
    public AndNode(ASTNode left,ASTNode right){
        this.left=left;
        this.right=right;
    }
    @Override
    public boolean evaluate(User user){
        return left.evaluate(user) && right.evaluate(user);
    }
}
class OrNode implements ASTNode{
    private ASTNode left;
    private ASTNode right;
    public OrNode(ASTNode left, ASTNode right){
        this.left=left;
        this.right=right;
    }
    @Override
    public boolean evaluate(User user){
        return left.evaluate(user) || right.evaluate(user);
    }
}
public class RuleEngine{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Age:");
        int age=sc.nextInt();
        System.out.println("Enter Department:");
        String dept =sc.next();
        System.out.println("Enter Income:");
        int income=sc.nextInt();
        System.out.println("Enter spend:");
        int spend=sc.nextInt();
        System.out.println("Enter MinAge:");
        int minage=sc.nextInt();
        System.out.println("Enter Depatment Condition:");
        String deptc=sc.next();
        System.out.println("Enter MinIncome:");
        int minIncome=sc.nextInt();
        User user=new User(age,dept,income,spend);
        ASTNode ageCondition=new AgeCondition(minage);
        ASTNode departmentCondition=new DepartmentCondition(deptc);
        ASTNode incomeCondition=new IncomeCondition(minIncome);
        ASTNode andNode=new AndNode(ageCondition,departmentCondition);
        ASTNode rule=new OrNode(andNode,incomeCondition);
        boolean isEligible=rule.evaluate(user);
        System.out.println("User eligibility: "+isEligible);
    }
}