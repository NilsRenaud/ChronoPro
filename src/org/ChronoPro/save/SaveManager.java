package org.ChronoPro.save;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Class used to save User's Score into a file which looks like that : 
 * date1 (timestamp)
 * temps1 (in millis)
 * date2
 * temps2
 * ...
 * The file is written for each save.
 */
public class SaveManager {
	
	private File saveFile;
	private Map<String, String> fileMap;
	
	
	public SaveManager(){
		saveFile = null;
		fileMap = new HashMap<String, String>();
	}
	
	public void openFile(String path) throws IOException{
		saveFile = new File(path);
		readFile();
	}
	
	public void save(long time) throws IOException{
		fileMap.put(String.valueOf(System.currentTimeMillis()), String.valueOf(time));
		
		writeFile();
	}
	
	public void createSaveFile(String path) throws Exception{
		saveFile = new File(path);
		boolean ret = saveFile.createNewFile();
		
		if (!ret){
			throw new Exception();
		}
		
	}
	
	private void readFile() throws IOException{
		InputStream ips=new FileInputStream(saveFile); 
		InputStreamReader ipsr=new InputStreamReader(ips);
		BufferedReader br=new BufferedReader(ipsr);
		
		String date;
		String savedTime;
		while ((date=br.readLine())!=null){
			if((savedTime=br.readLine())!=null){
				fileMap.put(date, savedTime);
			}//XXX else malformed file...
		}
		br.close();
	}
	
	private void writeFile() throws IOException{
		OutputStream ops = new FileOutputStream(saveFile);
		OutputStreamWriter opsr = new OutputStreamWriter(ops);
		BufferedWriter bw = new BufferedWriter(opsr);
		
		for(String key : fileMap.keySet()){
			bw.write(key);
			bw.newLine();
			bw.write(fileMap.get(key));
			bw.newLine();
		}
		
		bw.close();
	}
	
}
