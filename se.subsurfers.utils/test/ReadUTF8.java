import java.io.*;

public class ReadUTF8{
	public static void main(String[] args)throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter File name : ");
		String str = in.readLine();
		File file = new File(str);
		if(!file.exists())
		{
			System.out.println("File does not exist");
			System.exit(0);
		}
		else
		{
			try{
				BufferedReader i = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"));
				String str1 = i.readLine();
				System.out.println("File text : "+ str1);
				System.out.println("Reading Process Completly Successfully.");
			}
			
			catch(UnsupportedEncodingException ue){
				System.out.println("Not supported : ");
			}
			catch(IOException e){
				System.out.println(e.getMessage());
			}
		}
	}
}
