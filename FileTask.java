
import java.io.FileReader;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class FileTask extends ServerTask{
  private final String file;

  public FileTask(String uri,String path){
    super(uri);
    String s;
    try{
      FileReader filer = new FileReader(path);
      StringBuilder builder = new StringBuilder();
      char[] buffer = new char[1000];
      int n = 0;
      while((n=filer.read(buffer))>0)
        builder.append(buffer,0,n);
      s = builder.toString();
    }catch(Exception e){
      s = "File not found";
    }
    file = s;
  }

  @Override
  public void handle(HttpExchange t) throws IOException {
      t.sendResponseHeaders(200, file.length());
      OutputStream os = t.getResponseBody();
      os.write(file.getBytes());
      os.close();
  }
}
