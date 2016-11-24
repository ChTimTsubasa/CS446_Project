import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Loader {
	public static Set<String> loadValues(String fileName){
		Set<String> values=new HashSet<String>();
		
		try{
			BufferedReader br=new BufferedReader(new FileReader(new File(fileName)));
			String line=br.readLine();
			while(line!=null){
				values.add(line);
				line=br.readLine();
			}
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return values;
	}
	
	public static Map<String,List<String>> loadNameValues(String fileName) {
		Map<String,List<String>> nameValues=new HashMap<>();
		
		try{
			BufferedReader br=new BufferedReader(new FileReader(new File(fileName)));
			String line=br.readLine();
			while(line!=null){
				String[] tokens=line.split("\t");
				String name=tokens[0],value=tokens[1];
				List<String> values;
				if(!nameValues.containsKey(name)){
					values=new LinkedList<String>();
					nameValues.put(name, values);
				}
				else{
					values=nameValues.get(name);
				}
				values.add(value);
				line=br.readLine();
			}
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return nameValues;
	}
	
	public static void store(Map<String,List<String>>nameValues,String fileName){
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File (fileName)));
			for (Map.Entry<String, List<String>> entry : nameValues.entrySet()) {
				String name=entry.getKey();
				List<String>valueList=entry.getValue();
				
				bw.write(name+"\t");
				for(String value:valueList){
					bw.write(value+"\t");
				}
				bw.newLine();
				
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
