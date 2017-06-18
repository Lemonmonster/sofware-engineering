
import com.sun.net.httpserver.HttpHandler;

public abstract class ServerTask implements HttpHandler{
  public final String uri;

  ServerTask(String uri){
    this.uri = uri;
  }

}
