
import java.net.URI;
import java.util.HashMap;

public class Utils{
  public static HashMap<String,String> getQueryMap(URI uri){
    String[] pairs = uri.getQuery().split("&");
    HashMap<String,String> map = new HashMap<String,String>();
    for(int i = 0; i<pairs.length; i++) {
      String[] split = pairs[i].split("=");
      map.put(split[0],split[1]);
    }
    return map;
  }
}
