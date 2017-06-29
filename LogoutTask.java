import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.URI;

import java.util.HashMap;

public class LogoutTask extends ServerTask{
  public LogoutTask(){
    super("/logout");
  }

  @Override
  public void handle(HttpExchange t) throws IOException {   
    Main.sessions.remove(t.getRemoteAddress().getAddress());
    t.sendResponseHeaders(200, -1);
  }
}
