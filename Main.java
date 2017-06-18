import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Main{
  //add new tasks to this array
  static ServerTask[] tasks = new ServerTask[]{
    new  FileTask("/","pages/login.html")
  };

  public static void main (String[] args) throws Exception{
      HttpServer server = HttpServer.create(new InetSocketAddress(4242), 0);
      for(ServerTask t: tasks){
        server.createContext(t.uri,t);
      }
      server.start();
  }
}
