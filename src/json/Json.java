/*
 * Author: Reishandy
 */

package json;

import answer.Answer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import rule.Rule;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Json {
    private final Gson gson;
    private String path;

    public Json() {
        gson = new Gson();
    }

    private void setPath(String file) {
        path = Paths.get(file).toAbsolutePath().toString()
                .replace(file, "\\src\\json\\" + file);
    }

    private Reader read() throws FileNotFoundException {
        return new FileReader(path);
    }

    private void write(String content) throws IOException {
        FileWriter file = new FileWriter(path);
        file.write(content);
        file.close();
    }

    public void clear(String fileName) throws IOException {
        setPath(fileName);
        write("");
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

    public ArrayList<Answer> getAnswerJson(String filename) throws FileNotFoundException {
        setPath(filename);
        Type answerListType = new TypeToken<ArrayList<Answer>>(){}.getType();
        return gson.fromJson(read(), answerListType);
    }

    public void storeAnswerJson(ArrayList<Answer> answers, String fileName) throws IOException {
        setPath(fileName);
        write(gson.toJson(answers));
    }

    public HashMap<String, ArrayList<Answer>> getHistoryJson(String fileName) throws FileNotFoundException {
        setPath(fileName);
        Type historyType = new TypeToken<HashMap<String, ArrayList<Answer>>>(){}.getType();
        return gson.fromJson(read(), historyType);
    }

    public void storeHistoryJson (HashMap<String, ArrayList<Answer>> history, String fileName) throws IOException {
        setPath(fileName);
        write(gson.toJson(history));
    }
}
