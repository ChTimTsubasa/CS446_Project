package dataAccess;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Loader {
	public static void storeSet(String fileName,Iterable<String> iter){
		FileWriter fw=null;
		try{
			fw=new FileWriter(new File(fileName));	
			for(String line:iter){
				fw.write(line.trim()+"\n");
				
			}
			fw.flush();
			fw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
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
	public static int countLines(File aFile) throws IOException {
	    LineNumberReader reader = null;
	    try {
	        reader = new LineNumberReader(new FileReader(aFile));
	        while ((reader.readLine()) != null);
	        return reader.getLineNumber();
	    } catch (Exception ex) {
	        return -1;
	    } finally { 
	        if(reader != null) 
	            reader.close();
	    }
	}
	public static void main(String []countLines){
		try {
			System.out.println(Loader.countLines(new File("./data/professions")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
