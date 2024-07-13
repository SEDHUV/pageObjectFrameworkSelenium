package data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class jsontoMap {
File file = new File(System.getProperty("user.dir")+"\\src\\test\\java\\data\\data.json");
    public List<HashMap<String, String>> getjsonToMap() throws IOException {
String jsoncontent = FileUtils.readFileToString(file);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
    }
}