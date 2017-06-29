import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.util.concurrent.ConcurrentHashMap;

import java.net.InetAddress;

public class Main{
  //add new tasks to this array
  static ServerTask[] tasks = new ServerTask[]{
    new  FileTask("/","pages/login.html"),
    new AccountCreationTask(),
    new LoginTask(),
    new LogoutTask()
  };

  static ConcurrentHashMap<String,Account> accounts = new ConcurrentHashMap<>();
  static ConcurrentHashMap<InetAddress,Account> sessions = new ConcurrentHashMap<>();

  public static void main (String[] args) throws Exception{
      HttpServer server = HttpServer.create(new InetSocketAddress(4242), 0);
      for(ServerTask t: tasks){
        server.createContext(t.uri,t);
      }
      server.start();
  }
}
