import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TrainingGenerate {
	//arg[0] is nationality.kb/profession.kb   
	//arg[1] is nationalities/professions
	public static void main(String args[]){
		
		Map<String,List<String>> nameValues;
		Set<String> nameSet;
		String outputPath="./data/pnSamples/";
		String nameValueFileName=args[0];
		String nameSetFileName=args[1];
		
		nameSet=Loader.loadValues(nameSetFileName);
		nameValues=Loader.loadNameValues(nameValueFileName);
		getPositiveExample(nameValues,outputPath+"positive");
		getNegativeExample(nameValues,nameSet,outputPath+"negative");
		
	}
	
	private static void getNegativeExample(Map<String, List<String>> nameValues, Set<String> valueSet,String fileName) {
		int i=0;
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File (fileName)));
			for (Map.Entry<String, List<String>> entry : nameValues.entrySet()) {
				String name=entry.getKey();
				List<String>valueList=entry.getValue();
				for(String value:valueSet){
					if(!valueList.contains(value)){
						bw.write(value+"\t"+name);
						bw.newLine();
					}
				}
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static void getPositiveExample(Map<String, List<String>> nameValues,String fileName) {

		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File (fileName)));
			for (Map.Entry<String, List<String>> entry : nameValues.entrySet()) {
				String name=entry.getKey();
				List<String>values=entry.getValue();
				if(values.size()==1){
					bw.write(values.get(0)+"\t"+name);
					bw.newLine();
				}
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
