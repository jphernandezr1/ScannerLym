package Scanner;

public class Main 
{
	private static Lectura lectura;

	public static void main(String[] args) 
	{
		try 
		{
			lectura = new Lectura();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

}
