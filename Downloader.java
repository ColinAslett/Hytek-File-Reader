import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Downloader {
	File file;
	//All lines of the Hytek File
	ArrayList<String> lines = new ArrayList<>();
	//All Team Lines of the Hytek File
	ArrayList<String> Tline = new ArrayList<>();
	ArrayList<Integer> Tid = new ArrayList<>();//corresponding line index for each TLine
	//Swimmers
	ArrayList<Swimmer> Slist = new ArrayList<>();
	ArrayList<Integer> Sid = new ArrayList<>();//corresponding line index for each swimmer
	public Downloader(File f){
		file = f;
		//reads the file but we got to have a try-catch block for some reason
		try {
			readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//getting all team and swimmer info(Excluding Event Info)
		extractFile();
		//getting all the event info for each swimmer
		getEventInfo();
		//System.out.println(Slist.get(0).name + "," + Slist.get(0).Elist.size());
		for(int i = 0;i < Slist.get(0).Tlist.size();i++){
			System.out.println("      " + Slist.get(0).Tlist.get(i));
		}
		for(int i = 0;i < Slist.size();i++){
			System.out.println(Slist.get(i).name + " , " + Slist.get(i).age + " , " + Slist.get(i).gender);
			for(int a = 0;a < Slist.get(i).Tlist.size();a++){
				System.out.println("      " + Slist.get(i).Tlist.get(a));
			}
		}
	}
	private void getEventInfo() {
		ArrayList<String> eList = new ArrayList<>();
		//getting number of events
		for(int i = 0;i < Slist.size();i++){
			eList.clear();
			int num = -1;//idk why this works but it does so im going to keep it
			int id = Sid.get(i)+1;//starting line is swimmer info, +1 is the start of the event info
			String temp = lines.get(id);
			while(temp.charAt(0) == 'E'){
				//this will give a error if the last line is a swimmer event line because there are no more lines,but it should not be a problem
				if((id+1) > lines.size()){
					break;
				}
				temp = lines.get(id++);
				eList.add(temp);
				num++;
			}
			num = num/2;
			Slist.get(i).setEventNum(num,eList);
		}
		//getting specific info for each event
	}
	//extracting info out of each line
	private void extractFile() {
		String temp;
		for(int i = 0;i < lines.size();i++){
			temp = lines.get(i);
			//team line
			if(temp.charAt(0) == 'C' && temp.charAt(1) == '1'){
				temp = temp.substring(2);//getting rid of the C1 chars
				Tline.add(temp);
				Tid.add(i);
			}
			//swimmer info line
			if(temp.charAt(0) == 'D' && temp.charAt(1) == '1'){
				String g = "";
				g += temp.charAt(2);
				temp = temp.substring(8,27);//First Name
				temp = temp.replaceAll("\\s","");//replacing all the empty spaces
				temp += "," + lines.get(i).substring(28,47);//last name
				temp = temp.replaceAll("\\s","");
				//97 and 98 for age
				String age = lines.get(i).substring(97,99);
				age = age.replaceAll("\\s", "");
				//combining all the info
				Swimmer s = new Swimmer(temp,age,g,i);
				Sid.add(i);
				Slist.add(s);
			}
		}
	}
	//getting all the necessary lines
	private void readFile() throws IOException {
		//reading all the lines in the file
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		while((str = br.readLine()) != null){
			lines.add(str);
		}
	}
}
