import java.io.File;

import javax.swing.JFrame;

public class Main extends JFrame{
	//Frame Settings
	static final int WIDTH = 600;
	static final int HEIGHT = 600;
	//Downloader class
	File file = new File("C:\\Users\\colin\\SwimProject\\Hytek\\test.txt");//File path needed
	Downloader dw = new Downloader(file);
	public Main(){
		initFrame();
	}
	private void initFrame() {
		this.setSize(600,600);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Hytek File Reader");
	}
	public static void main(String[] args) {
		Main main = new Main();
	}

}
