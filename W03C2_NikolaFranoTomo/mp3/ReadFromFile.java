import java.io.*;
import java.util.*;

public class ReadFromFile implements Iterable<String> {

    ArrayList<String> list = new ArrayList<String>();

    public ReadFromFile(String stringFile) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(stringFile));
        String line;
        while((line = br.readLine()) != null) {

            list.add(line);
            line = br.readLine();
        }
    }
    
    public Iterator<String> iterator() {
      return list.iterator();
    }


}