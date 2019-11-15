import java.util.ArrayList;

public class Swimmer {
	String name;
	String age;
	String gender;
	int id;//start of swimmer info(line number in the hytek file)
	int NumEvent = 0;//number of events
	ArrayList<String> Elist = new ArrayList<>();
	ArrayList<String> Tlist = new ArrayList<>();
	//constructor
	public Swimmer(String n,String a,String g,int i){
		name = n;
		age = a;
		gender = g;
		id = i;
	}
	//Number of event setter, and also gets all the relevant time and stroke data, SPLITS ARE CURRENTLY NOT SUPPORTED
	public void setEventNum(int n,ArrayList<String> e){
		NumEvent = n;
		Elist = e;
		//getting event info
		String line;
		String time;
		String eName;
		String Dis;
		for(int i = 0;i < Elist.size();i++){
			line = Elist.get(i);
			time = "";
			eName = "";
			Dis = "";
			if(line.charAt(1) == '1'){
				//getting the event type
				//System.out.println(line);
				eName = line.substring(17,22);
				//System.out.println(eName);
				Dis = eName.substring(0,4);
				Dis = Dis.replaceAll("\\s", "");
				eName = "" + eName.charAt(4);
				eName.replaceAll("\\s", "");
				//50A = 50FR, A = FR,E = IM,B = BK,C = BR,D = FY
				if(eName.equals("A")){
					eName = "FR";
				}
				else if(eName.equals("B")){
					eName = "BK";
				}
				else if(eName.equals("C")){
					eName = "BR";
				}
				else if(eName.equals("D")){
					eName = "FY";
				}
				else if(eName.equals("E")){
					eName = "IM";
				}
				Dis = Dis + " " + eName;
				//System.out.println(Dis + " " + eName);
				//getting time for each event
				if(i+1 < Elist.size()){
					line = Elist.get(i+1);
					time = "";
					eName = "";
					//if its an time line
					if(line.charAt(1) == '2'){
						//getting the time
						time = line.substring(5,14);
						time = time.replaceAll("\\s", "");
						//System.out.println(time);
					}
				}
				//Adding all relevant info together
				if(Character.isDigit((Dis + ": " + time).charAt(0))){
					//System.out.println(Dis + ": " + time);
					Tlist.add(Dis + ": " + time);
				}
			}
		}
	}
}
