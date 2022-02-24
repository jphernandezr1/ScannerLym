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

	private ArrayList<String> palabras;
	
	private Constantes verificador;

	//Inicio de la lectura del archivo
	public Lectura()
	{
		palabras= new ArrayList<String>();
		verificador= new Constantes();
		leer();
		verificarEntrada();
	}

	/*
	 * Metodo que se encarga de hacer la primera division en cadenas, utilizando los // a�adidos en el lector
	 */
	public void parsearCadena(String cadena)
	{
		String [] lista = cadena.split(" ");
		
		for (int i=0; i<lista.length;i++) {
			String palabra = lista[i];
			String palmod="";
			int cuantas=0;
			if (palabra.contains("(")) {
				palabras.add("(");
				palmod =palabra.substring(1,palabra.length());
				palabra= palmod;
				while (palabra.contains("("))
					{
						palabras.add("(");
						palmod =palabra.substring(1,palabra.length());
						palabra= palmod;
					}
				
			}
			if (palabra.contains(")")) 
			{
				cuantas++;
				if(palabra.length()-1!=0)
				{
					palmod =palabra.substring(0,palabra.length()-1);
					palabra= palmod;
					while (palabra.contains(")"))
					{
						cuantas++;
						if(palabra.length()-1!=0)
						{
							palmod =palabra.substring(0,palabra.length()-1);
							palabra= palmod;
						}
						else
						{
							palabra="";
						}
					}
				}
				else
				{
					palabra="";
				}
			}
			if (palabra.contains(":")) {
				palmod =palabra.substring(1,palabra.length());
				palabra= palmod;
			}
			if (palabra.equals("")==false) {
				palabras.add(palabra);
				while(cuantas>0)
				{
					palabras.add(")");
					cuantas--;
				}
			}
		}
				
	}

	public void verificarEntrada()
	{
		if (palabras.size()!= 0) {
			boolean bien =true;
			for (int i=0;i<palabras.size();i++) {
				String palabraActual= palabras.get(i);
				if (verificador.validacion(palabraActual)) {
					if (palabraActual.equals(verificador.DEFVAR)) {
						if (verificadorDefvar(i)== false) {
							System.out.println("Error en la palabra "+ palabraActual+ " error ortografico");
							bien=false;
							break;
						}
					}
					else if (palabraActual.equals(verificador.DEFUN)) {
						if (verificadorDefun(i)== false) {
							System.out.println("Error en la palabra "+ palabraActual+ " error ortografico");
							bien=false;
							break;
						}
					}
					else if (palabraActual.equals(verificador.IF)) {
						if (verificadorIf(i)== false) {
							System.out.println("Error en la palabra "+ palabraActual+ " error ortografico");
							bien=false;
							break;
						}
					}
					else if (palabraActual.equals(verificador.FOO)) {
						if (verificadorFoo(i)== false) {
							System.out.println("Error en la palabra "+ palabraActual+ " error ortografico");
							bien=false;
							break;
						}
					}
					else if (palabraActual.equals(verificador.MOVE)) {
						if (verificadorMove(i)== false) {
							System.out.println("Error en la palabra "+ palabraActual+ " error ortografico");
							bien=false;
							break;
						}
					}
					else if (palabraActual.equals(verificador.ROTATE)) {
						if (verificadorRotate(i)== false) {
							System.out.println("Error en la palabra "+ palabraActual+ " error ortografico");
							bien=false;
							break;
						}
					}
					else if (palabraActual.equals(verificador.TURN)) {
						if (verificadorTurn(i)== false) {
							System.out.println("Error en la palabra "+ palabraActual+ " error ortografico");
							bien=false;
							break;
						}
					}
					else if (palabraActual.equals(verificador.PICK)) {
						if (verificadorPick(i)== false) {
							System.out.println("Error en la palabra "+ palabraActual+ " error ortografico");
							bien=false;
							break;
						}
					}
					else if (palabraActual.equals(verificador.BALLOONS)) {
						if (verificadorBalloons(i)== false) {
							System.out.println("Error en la palabra "+ palabraActual+ " error ortografico");
							bien=false;
							break;
						}
					}
					else if (palabraActual.equals(verificador.CHIPS)) {
						if (verificadorChips(i)== false) {
							System.out.println("Error en la palabra "+ palabraActual+ " error ortografico");
							bien=false;
							break;
						}
					}
					else if (palabraActual.equals(verificador.CANMOVE)) {
						if (verificadorCanMoveP(i)== false) {
							System.out.println("Error en la palabra "+ palabraActual+ " error ortografico");
							bien=false;
							break;
						}
					}
					else if (palabraActual.equals(verificador.SKIP)) {
						if (verificadorSkip(i)== false) {
							System.out.println("Error en la palabra "+ palabraActual+ " error ortografico");
							bien=false;
							break;
						}
					}
					else if (palabraActual.equals(verificador.RUN)) {
						if (verificadorRunDirs(i)== false) {
							System.out.println("Error en la palabra "+ palabraActual+ " error ortografico");
							bien=false;
							break;
						}
					}
					else if (palabraActual.equals(verificador.DEFUN)) {
						if (verificadorDefun(i)== false) {
							System.out.println("Error en la palabra "+ palabraActual+ " error ortografico");
							bien=false;
							break;
						}
					}

				}
				else {
					System.out.println("Error en la palabra "+ palabraActual+ " no se encontro en el diccionario");
					bien=false;
					break;
				}
			}
			if (bien==true)
			{
				System.out.println("se valido y se encuentra correctamente la entrada");
			}

		}
	}
	public boolean verificadorDefvar(int i)
	{
		if (palabras.get(i+1).equals(verificador.ROTATE) || palabras.get(i+1).equals(verificador.ONE)) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean verificadorIf(int i)
	{
		if (palabras.get(i+1).equals("(") &&  (palabras.get(i+2).equals(verificador.NOT)||palabras.get(i+2).equals(verificador.BLOCKED))) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean verificadorDefun(int i)
	{

		if ((palabras.get(i+1).equals(verificador.BLOCKED) || (palabras.get(i+1).equals(verificador.FOO)||palabras.get(i+1).equals(verificador.GOEND)))) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean verificadorFoo(int i)
	{
		if ((palabras.get(i+1).equals("(") || verificadorNumero(palabras.get(i+1))) && (palabras.get(i+2).equals("c")||verificadorNumero(palabras.get(i+2))) && (palabras.get(i+3).equals("p")||palabras.get(i+3).equals(")"))) {
			return true;
		}
		else {
			return false;
			}
	}
	public boolean verificadorMove(int i)
	{
		
		if (verificadorNumero(palabras.get(i+1)) || palabras.get(i+1).equals(verificador.ONE)||palabras.get(i+1).equals(verificador.ROTATE)){
			return true;
		}
		else {
			return false;
		}
	}
	public boolean verificadorRotate(int i)
	{
		if (verificadorNumero(palabras.get(i+1)) || palabras.get(i+1).equals(")")){
			return true;
		}
		else {
			return false;
		}
	}
	public boolean verificadorTurn(int i)
	{
		if (palabras.get(i+1).equals(verificador.RIGHT) || palabras.get(i+1).equals(verificador.LEFT)){
			return true;
		}
		else {
			return false;
		}
	}
	public boolean verificadorPick(int i)
	{
		if (palabras.get(i+1).equals("Chips")){
			return true;
		}
		else {
			return false;
		}
	}
	public boolean verificadorBalloons(int i)
	{
		if (palabras.get(i+1).equals("c")){
			return true;
		}
		else {
			return false;
		}
	}
	public boolean verificadorChips(int i)
	{
		if (palabras.get(i+1).equals("p")){
			return true;
		}
		else {
			return false;
		}
	}
	public boolean verificadorCanMoveP(int i)
	{
		if (palabras.get(i+1).equals(verificador.RIGHT) || palabras.get(i+1).equals(verificador.LEFT)||palabras.get(i+1).equals(verificador.BACK) || palabras.get(i+1).equals(verificador.FRONT) || palabras.get(i+1).equals(verificador.NORTH)){
			return true;
		}
		else {
			return false;
		}
	}
	public boolean verificadorSkip(int i)
	{
		if (palabras.get(i-1).equals("(") || palabras.get(i+1).equals(")")){
			return true;
		}
		else {
			return false;
		}
	}
	public boolean verificadorRunDirs(int i)
	{
		if (palabras.get(i+1).equals("(")){
			return true;
		}
		else {
			return false;
		}
	}
	public boolean verificadorNot(int i)
	{
		if (verificadorNumero(palabras.get(i+1)) || palabras.get(i+1).equals("(")||palabras.get(i+2).equals(verificador.BLOCKED)) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean verificadorNumero(String palabra) {
		for (int i=0;i<10;i++) {
			if (palabra.equals(Integer.toString(i))) {
				return true;
			}
		}
		return false;
	}
	/*
	 * Lectura del archivo txt, revisa linea por linea, cuando encuentra saltos de linea en blanco, colooca un // para facilitar 
	 * el primer parse, que divide la cadena original en peque�as cadenas
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
				parsearCadena(texto);
				
				
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
