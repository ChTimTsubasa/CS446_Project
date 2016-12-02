package dataAccess;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class FreeBase {
	public File freeBaseFile;
	public HashMap<String, short[]> memBase;
	public Queue<String> memQueue;
	public int memSize;
	
	public FreeBase(String fileName) throws FileNotFoundException{
		this(fileName,33000);
	}
	
	public FreeBase(String fileName,int memSize) throws FileNotFoundException{
		File file=new File(fileName);
		if(!file.exists())
			throw new FileNotFoundException();
		
		freeBaseFile=file;
		memBase=new HashMap<String,short[]>(memSize);
		memQueue=new LinkedList<String>();
		BufferedReader br=new BufferedReader(new FileReader(file));
		String line;
		try {
			line = br.readLine();
			int lineCount=0;
			while(line!=null&&lineCount<memSize){
				String[] tokens=line.split("\t");
				String name=tokens[0];
				short[] features=lineToFeature(tokens[1]);
				memQueue.add(name);
				memBase.put(name, features);
				line=br.readLine();
				lineCount++;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean contain(String name){
		if(memBase.containsKey(name))
			return true;
		BufferedReader br=null;
		try {
			br=new BufferedReader(new FileReader(freeBaseFile));
			String line;
			line = br.readLine();
			while(line!=null){
				String[] tokens=line.split("\t");
				if (tokens[0].equals(name)){
					br.close();
					return true;
				}
				line=br.readLine();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public short[]get(String name){
		if(memBase.containsKey(name)){
			return memBase.get(name);
		}
		BufferedReader br=null;
		try {
			br=new BufferedReader(new FileReader(freeBaseFile));
			String line;
			line = br.readLine();
			while(line!=null){
				String[] tokens=line.split("\t");
				if(name.equals(tokens[0])){
					short[] features=lineToFeature(tokens[1]);
					memQueue.poll();
					memQueue.add(name);
					memBase.put(name, features);
					br.close();
					return features;
				}
				line=br.readLine();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return null;
		
	}
	
	private short[] lineToFeature(String line){
		String featureTokens[]=line.split(" ");
		short[] features=new short[featureTokens.length];
		int i=0;
		for(String featureString:featureTokens){
			features[i]=Short.parseShort(featureString);
			i++;
		}
		return features;
	}

	public static void main(String args[]){
		String baseFileName="./data/Features/output.txt";
		FreeBase base=null;
		BufferedReader br=null;
		try {
			base=new FreeBase(baseFileName);
			br=new BufferedReader(new FileReader(new File("./data/persons")));
			String line=br.readLine();
			while(line!=null){
				String name=line.split("\t")[0];
				if(!base.contain(name)){
					System.out.println(name);
				}
				line=br.readLine();
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


