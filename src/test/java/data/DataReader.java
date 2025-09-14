package data;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

        public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
           String jsonContent = FileUtils.readFileToString(new File("C://Users//z0053vpk//IdeaProjects//Selenium_Framework//src//test//java//data//Purchaseorder.json"));
          //String to HashMap
           ObjectMapper objectMapper = new ObjectMapper();
            List<HashMap<String, String>> data = objectMapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
            return data;
        }
}
