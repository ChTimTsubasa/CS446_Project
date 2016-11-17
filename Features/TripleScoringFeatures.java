import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TripleScoringFeatures {
    public static void main(String[] args) throws IOException {
        ArrayList<String> dictionary = getDictionary();
        storeData(dictionary);
    }
    
    public static ArrayList<String> getDictionary() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("entity-ontos.txt"));
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("dictionary.txt"), "utf-8"));
        String line = null;
        ArrayList<String> dictionary = new ArrayList<String>();
        Set<String> tmpSet = new HashSet<String>();
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split("\t");
            if (tokens.length < 3) continue;
            String[] features = tokens[2].split(" ");
            for (String f : features) {
                if (tmpSet.contains(f)) {
                    continue;
                }
                tmpSet.add(f);
                dictionary.add(f);
                writer.write(f + "\n");
            }
        }
        reader.close();
        writer.close();
        return dictionary;
    }
    public static void storeData(ArrayList<String> dictionary) throws IOException {
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "utf-8"));
        BufferedReader reader = new BufferedReader(new FileReader("entity-ontos.txt"));
        String line = null;
        ArrayList<Integer> featureList;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split("\t");
            String output = tokens[0] + "\t";
            if (tokens.length == 3) {
                featureList = new ArrayList<Integer>(Collections.nCopies(dictionary.size(), 0));
                String[] features = tokens[2].split(" ");
                for (String f : features) {
                    int index = dictionary.indexOf(f);
                    featureList.set(index, 1);
                }
                for (int i = 0; i < featureList.size(); i++) {
                    output = output + featureList.get(i) + " ";
                }
            }
            writer.write(output.trim() + "\n");
        }
        reader.close();
        writer.close();
    }
}
