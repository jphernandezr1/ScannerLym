package Scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class Lectura
{
	private static final String RUTA = "data/input.txt";

	private String cadenaOriginal;

	private ArrayList<String> cadenas;

	private ArrayList<String> cadenasDivididas;

	private Stack pila;

	
	//Inicio de la lectura del archivo
	public Lectura()
	{
		pila = new Stack();
		cadenaOriginal = new String();
		cadenas = new ArrayList<String>();
		cadenasDivididas= new ArrayList<String>();
		leer();
		parsearCadenaOriginal();
		dividirBloques();
	}
	
	public void verificarEstructuraPorCadena()
	{
		
	}

	/*
	 * Metodo que se encarga de hacer la primera division en cadenas, utilizando los // añadidos en el lector
	 */
	public void parsearCadenaOriginal()
	{
		String cadena = cadenaOriginal;

		String[] parts = cadena.split("//");

		for(int i = 0; i < parts.length ; i++)
		{
			cadenas.add(parts[i]);
		}
	}

	/*
	 * division de las primeras cadenas por bloques, teniendo en cuenta cuantos comandos tiene, y si cumple los parentesis, 
	 * para hacer el corte, valida hasta 4 comandos en una única linea.
	 * da las cadenas ya divididas que seran analizadas para ver si cumplen la estructura correspondiente a su comando
	 */
	public void dividirBloques()
	{
		for(int i = 0; i < cadenas.size() ; i++)
		{
			String cadenaActual = cadenas.get(i);
			ArrayList<Integer> verificar = identificarEstructura(cadenas.get(i));

			Collections.sort(verificar);

			if(verificar.size() == 0)
			{
				cadenasDivididas.add(cadenas.get(i));
			}

			else if(verificar.size() == 1)
			{
				if(verificar.get(0) > 5)
				{
					String c1 = cadenas.get(i).substring(0, (verificar.get(0)-1)); 
					String c2 = cadenas.get(i).substring(verificar.get(0)-1);
					cadenasDivididas.add(c1);
					cadenasDivididas.add(c2);
				}
				else
				{
					cadenasDivididas.add(cadenas.get(i));
				}
			}

			else if (verificar.size() == 2)
			{
				int v0 = verificar.get(0);
				int v1 = verificar.get(1);

				if(cadenas.get(i).charAt(v1-1) == '(' && cadenas.get(i).charAt(v1-2) == ')' && cadenas.get(i).charAt(v1-3) == ')' )
				{
					String c1 = cadenas.get(i).substring(0, (v1-1));
					String c2 = cadenas.get(i).substring((v1-1));
					cadenasDivididas.add(c1);
					cadenasDivididas.add(c2);
				}
				else
				{
					cadenasDivididas.add(cadenaActual);
				}
			}

			else if(verificar.size() == 3)
			{
				int v0 = verificar.get(0);
				int v1 = verificar.get(1);
				int v2 = verificar.get(2);

				if(cadenas.get(i).charAt(v1-1) == '(' && cadenas.get(i).charAt(v1-2) == ')' && cadenas.get(i).charAt(v1-3) == ')' )
				{
					String c1 = cadenas.get(i).substring(0, (v1-1));
					String c2 = cadenas.get(i).substring((v1), (v2-1));
					cadenasDivididas.add(c1);
					cadenasDivididas.add(c2);
				}

				if(cadenas.get(i).charAt(v2-1) == '(' && cadenas.get(i).charAt(v2-2) == ')' && cadenas.get(i).charAt(v2-3) == ')' )
				{
					String c3 = cadenas.get(i).substring((v2));
					cadenasDivididas.add(c3);
				}
				else
				{
					cadenasDivididas.add(cadenaActual);
				}
			}

			else if(verificar.size() == 4)
			{
				int v0 = verificar.get(0);
				int v1 = verificar.get(1);
				int v2 = verificar.get(2);
				int v3 = verificar.get(3);

				if(cadenas.get(i).charAt(v1-1) == '(' && cadenas.get(i).charAt(v1-2) == ')' && cadenas.get(i).charAt(v1-3) == ')' )
				{
					String c1 = cadenas.get(i).substring(0, (v1-1));
					String c2 = cadenas.get(i).substring((v1), (v2-1));
					cadenasDivididas.add(c1);
					cadenasDivididas.add(c2);
				}

				if(cadenas.get(i).charAt(v2-1) == '(' && cadenas.get(i).charAt(v2-2) == ')' && cadenas.get(i).charAt(v2-3) == ')' )
				{
					String c3 = cadenas.get(i).substring((v2), (v3-1));
					cadenasDivididas.add(c3);
				}

				if(cadenas.get(i).charAt(v3-1) == '(' && cadenas.get(i).charAt(v3-2) == ')' && cadenas.get(i).charAt(v3-3) == ')' )
				{
					String c4 = cadenas.get(i).substring((v3));
					cadenasDivididas.add(c4);
				}
				else
				{
					cadenasDivididas.add(cadenaActual);
				}
			}
		}
	}


	/*
	 * Metodo que identifica la estructura de una unica cadena y determina que tantos comandos tiene
	 * retorna un array con los indexer de los comandos, para facilitar el split
	 */
	
	public ArrayList<Integer> identificarEstructura(String pCadena)
	{
		ArrayList<Integer> comandos = new ArrayList<Integer>();

		if(pCadena.contains(Constantes.DEFVAR))
		{		
			comandos.add(pCadena.indexOf(Constantes.DEFVAR));
		}

		if(pCadena.contains(Constantes.DEFUN))
		{
			comandos.add(pCadena.indexOf(Constantes.DEFUN));
		}

		if(pCadena.contains(Constantes.IF))
		{
			comandos.add(pCadena.indexOf(Constantes.IF));
		}


		if(pCadena.contains(Constantes.LOOP))
		{
			comandos.add(pCadena.indexOf(Constantes.LOOP));
		}

		if(pCadena.contains(Constantes.REPEAT))
		{
			comandos.add(pCadena.indexOf(Constantes.REPEAT));
		}	

		return comandos;
	}

	//Validacion en los parentesis de todas las cadenas ya divididas.
	public void validarParentesis()
	{
		for(int i = 0; i < cadenasDivididas.size() ; i++)
		{
			Parentesis(cadenasDivididas.get(i));
		}
	}

	/*
	 * Metodo para verificar que una expresion tiene balanceados sus parentesis
	 * @param linea -- cadena que tiene la expresion a examinar
	 */

	public void Parentesis (String linea) 
	{
		for (int i = 0; i < linea.length(); i++) 
		{
			if (linea.charAt(i) == '(') pila.push(new Character(')'));
			else if (linea.charAt(i) == '{') pila.push(new Character('}'));
			else if (linea.charAt(i) == '[') pila.push(new Character(']'));
			else if (linea.charAt(i) == ')') verifica(')');
			else if (linea.charAt(i) == '}') verifica('}');
			else if (linea.charAt(i) == ']') verifica(']');
		}

		if (pila.isEmpty())
			System.out.println("Parentesis balanceados");
		else 
			System.out.println("Parentesis NO balanceados");	  
	}

	/*
	 * Metodo privado que recibe un parentesis de cerrado y verifica que en
	 * el tope de la pila se encuentre el de apertura
	 */
	private void verifica (char c) 
	{
		if (pila.isEmpty()) 
		{
			System.out.println("Parentesis NO balanceados"); 
			System.exit(0); 
		} 
		else 
		{
			Character s = (Character) pila.pop();  
			if (c != s.charValue()) 
			{
				System.out.println("Parentesis NO balanceados"); 
				System.exit(0); 
			}
		}
	}


	public void indexOfJeje(String pPalabra, String pCadena)
	{
		System.out.println(pCadena); 

		// Carácter en la posición      
		System.out.println("Índice: " + pCadena.indexOf(pPalabra));
	}

	
	//Metodo que permite detectar si un salto de linea es en blanco o contiene texto.
	public boolean sonEspacios(String cad)
	{
		if(cad == null)
		{
			return false;
		}
		for(int i =0; i<cad.length(); i++)
			if(cad.charAt(i) != ' ')
				return false;

		return true;
	}

	public void imprimirCadena()
	{
		for(int i = 0 ; i < cadenasDivididas.size() ; i++)
		{ 
			System.out.println("Cadenas divididas");
			String texto = cadenasDivididas.get(i);
			System.out.println("cadena " + i + ": " + texto + "\n");
		}
	}

	/*
	 * Lectura del archivo txt, revisa linea por linea, cuando encuentra saltos de linea en blanco, colooca un // para facilitar 
	 * el primer parse, que divide la cadena original en pequeñas cadenas
	 */
	public void leer() 
	{
		BufferedReader br = null;
		try 
		{
			br = new BufferedReader(new FileReader(RUTA));

			String texto = br.readLine();
			while(texto != null)
			{
				if(sonEspacios(texto) == true)
				{
					texto = "//";
				}

				cadenaOriginal += texto;
				texto = br.readLine();
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("Error: Fichero no encontrado");
			System.out.println(e.getMessage());
		}
		catch(Exception e) {
			System.out.println("Error de lectura del fichero");
			System.out.println(e.getMessage());
		}
		finally {
			try {
				if(br != null)
					br.close();
			}
			catch (Exception e) {
				System.out.println("Error al cerrar el fichero");
				System.out.println(e.getMessage());
			}
		}
	}

}
