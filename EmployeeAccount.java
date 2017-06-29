import java.util.ArrayList;

public class EmployeeAccount extends Account{

  public ArrayList<ScheduleEntry> schedulEntries;

  public EmployeeAccount(String id, String password){
    super(id,password);
  }
}
