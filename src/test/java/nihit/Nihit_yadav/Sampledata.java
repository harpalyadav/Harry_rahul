package nihit.Nihit_yadav;

import java.io.IOException;
import java.util.ArrayList;

public class Sampledata {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		DataDriven_Testing d = new DataDriven_Testing();
			ArrayList<String> data = d.getData("Purchase");
			System.out.println(data.get(1));
			System.out.println(data.get(2));

	}

}
