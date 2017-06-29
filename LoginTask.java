import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.URI;

import java.util.HashMap;

public class LoginTask extends ServerTask{
  public LoginTask(){
    super("/login");
  }

  @Override
  public void handle(HttpExchange t) throws IOException {
    HashMap<String,String> form = Utils.getQueryMap(t.getRequestURI());
    if(form.containsKey("id") && form.containsKey("password")){
      String s = "false";
      Account a = Main.accounts.get(form.get("id"));
      if(a!=null && a.password.equals(form.get("password"))){
        s = (a instanceof EmployeeAccount) ? "employee" : "manager";
        Main.sessions.put(t.getRemoteAddress().getAddress(),a);
      }
      byte[] b = s.getBytes();
      t.sendResponseHeaders(200, b.length);
      t.getResponseBody().write(b);
    }else{
      t.sendResponseHeaders(400, -1);
    }
  }
}
