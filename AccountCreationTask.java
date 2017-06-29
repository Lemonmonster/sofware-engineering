


import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.util.HashMap;

public class AccountCreationTask extends ServerTask{
  public AccountCreationTask(){
    super("/createAccount");
  }

  private void success(HttpExchange t) throws IOException{
    byte[] b = "true".getBytes();
    t.sendResponseHeaders(200, b.length);
    t.getResponseBody().write(b);
  }

  @Override
  public void handle(HttpExchange t) throws IOException{
    HashMap<String,String> form = Utils.getQueryMap(t.getRequestURI());
    if(form.containsKey("id") && form.containsKey("password") && form.containsKey("type")){
      if(!Main.accounts.contains(form.get("id"))){
        String type = form.get("type");
        if(type.equalsIgnoreCase("manager")){
          Main.accounts.put(form.get("id"), new ManagerAccount(form.get("id"),form.get("password")));
          success(t);
          return;
        }else if(type.equalsIgnoreCase("client")){
          Account session = Main.sessions.get(t.getRemoteAddress().getAddress());
          if(session==null || session instanceof EmployeeAccount){
              t.sendResponseHeaders(403, -1);
              return;
          }
          ManagerAccount m = (ManagerAccount) session;
          EmployeeAccount newAccount = new EmployeeAccount(form.get("id"),form.get("password"));
          Main.accounts.put(form.get("id"), newAccount);
          m.employees.add(newAccount);
          success(t);
          return;
        }
      }
      byte[] b = "false".getBytes();
      t.sendResponseHeaders(200, b.length);
      t.getResponseBody().write(b);
    }else{
      t.sendResponseHeaders(400, -1);
    }
  }
}
