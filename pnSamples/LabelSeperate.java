import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.io.*;
public class LabelSeperate {
	//sample input: LabelSeperate ./data/pnSamples/profession ./data/professions
	public static void main(String args[]){
		System.out.println(args[0]+" "+args[1]);
		Set<String> nameSet;//professions or nationalities
		String outputPath="./data/samples/";
		String nameValueFileName=args[0];
		String nameSetFileName=args[1];
		nameSet=Loader.loadValues(nameSetFileName);
		labelSeperate(nameSet,nameValueFileName,".positive",outputPath);
		labelSeperate(nameSet,nameValueFileName,".negative",outputPath);
		}

	private static void labelSeperate(Set<String> nameSet, String nameValueFileName, 
			String pn, String outputPath) {
		File inFile=new File(nameValueFileName+pn);

		for(String name:nameSet){
			System.out.println(name);
			BufferedReader reader=null;
			Set<String>values=new HashSet<String>();
			try{
				reader=new BufferedReader(new FileReader(inFile));
				String line=reader.readLine();
				while(line!=null){
					String [] tokens=line.split("\t");
					line=reader.readLine();
					String nameRead=tokens[0];
					if(!nameRead.equals(name))
						continue;
					values.add(tokens[1]);
				}

				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				}
			
			if(values.isEmpty())
				continue;
			Loader.storeSet(outputPath+name+pn, values);
		}
		
	}
}
