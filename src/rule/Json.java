package rule;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Json {
    private final Gson gson;
    private String path;

    public Json() {
        gson = new Gson();
    }

    private void setPath(String file) {
        path = Paths.get(file).toAbsolutePath().toString()
                .replace(file, "\\src\\rule\\" + file);
    }

    private Reader read() throws FileNotFoundException {
        return new FileReader(path);
    }

    private void write(String content) throws IOException {
        FileWriter file = new FileWriter(path);
        file.write(content);
        file.close();
    }

    public ArrayList<Rule> getRuleJson(String fileName) throws FileNotFoundException {
        setPath(fileName);
        Type ruleListType = new TypeToken<ArrayList<Rule>>(){}.getType();
        return gson.fromJson(read(), ruleListType);
    }

    public void storeRuleJson(ArrayList<Rule> rules, String fileName) throws IOException {
        setPath(fileName);
        write(gson.toJson(rules));
    }
}
