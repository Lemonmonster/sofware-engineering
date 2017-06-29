import java.util.ArrayList;
public class ManagerAccount extends Account{

  public ArrayList<EmployeeAccount> employees;

  public String[] tasks;

  public ManagerAccount(String id, String password){
    super(id,password);
  }
}
