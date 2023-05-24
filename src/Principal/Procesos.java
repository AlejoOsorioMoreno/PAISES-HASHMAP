package Principal;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class Procesos {

	ModelosDatos miData;
	ArrayList<String>listEstudiantes;
	int cantGanan=0,cantRecuperan=0,cantPierden=0;
	public void iniciar() {
		String resp="";
		do {
			ingresarDatos();
			resp=JOptionPane.showInputDialog("Ingrese si, para continuar "
					+ "registrando estudiantes");
		} while (resp.equalsIgnoreCase("si"));
		miData.imprimirMapa();
		imprimirResultado();
		
		String documento=JOptionPane.showInputDialog("IIngrese el documento a buscar");
		miData.consultarEstudiante(documento);
	}
	private void ingresarDatos() {
		listEstudiantes=new ArrayList<String>();
		String documento,nombre,materia;
		double nota1,nota2,nota3;
		documento=JOptionPane.showInputDialog("Ingrese el documento");
		nombre=JOptionPane.showInputDialog("Ingrese el nombre del estudiante");
		materia=JOptionPane.showInputDialog("Ingrese la materia del estudiante");
		nota1=validarNotas("Ingrese la nota 1");
		nota2=validarNotas("Ingrese la nota 2");
		nota3=validarNotas("Ingrese la nota 3");
		
		listEstudiantes.add(documento);
		listEstudiantes.add(nombre);
		listEstudiantes.add(materia);
		listEstudiantes.add(nota1+"");
		listEstudiantes.add(nota2+"");
		listEstudiantes.add(nota3+"");
		
		miData.guardarDatos(listEstudiantes);
		
		calcularPromedio(nota1,nota2,nota3);
		
	}
	public double validarNotas(String msj) {
		double nota=0;
		
		do {
			nota=Double.parseDouble(JOptionPane.showInputDialog(msj));
			if (nota<0 || nota>5) {
				JOptionPane.showMessageDialog(null,"Nota incorrecta, "
						+ "no esta en el rango, repitala","ERROR",JOptionPane.ERROR_MESSAGE);
			}			
			
		} while (nota<0 || nota>5);
		
		
		return nota;
		}
	
	public void imprimirResultado() {
		double sumaTotal=sumaTotalNotas();
		int cantNotas=miData.getSizeMap()*3;
		
		System.out.println("Cantidad total estudiantes: "+miData.getSizeMap());
		System.out.println("Cantidad total notas ingresadas: "+cantNotas);
		System.out.println("Suma total de notas: "+sumaTotal);
		System.out.println("promedio total de notas: "+(sumaTotal/cantNotas));
		System.out.println("Promedio total de notas finales: "+calcularPromedioFinal());
		System.out.println("Cantidad que ganan: "+cantGanan);
		System.out.println("Cantidad que recuperan: "+cantRecuperan);
		System.out.println("Cantidad que pierden: "+cantPierden);
		
	}
	private double sumaTotalNotas() {

	Iterator<String> itera=miData.getMapaEstudiantes().keySet().iterator();
		
		double suma=0;
		
		while (itera.hasNext()) {
			String doc = itera.next();
			
			ArrayList<String> listTemp=miData.getMapaEstudiantes().get(doc);
			
			for (int i = 3; i <listTemp.size(); i++) {
				suma+=Double.parseDouble(listTemp.get(i));
			}	
		}
		
		return suma;
	}
	private double calcularPromedioFinal() {
		
		Iterator<String> itera=miData.getMapaEstudiantes().keySet().iterator();
		
		double n1,n2,n3;
		double suma=0,prom;
		
		while (itera.hasNext()) {
			String doc = itera.next();
					
			ArrayList<String> listTemp=miData.getMapaEstudiantes().get(doc);
			
			n1=Double.parseDouble(listTemp.get(3));
			n2=Double.parseDouble(listTemp.get(4));
			n3=Double.parseDouble(listTemp.get(5));
			
			prom=(n1+n2+n3)/3;
			calcularEstados(prom);
			suma+=prom;
		}
		
		return suma/miData.getSizeMap();
	}
public void calcularEstados(double promedio) {
		
		if (promedio>=3.5) {
			cantGanan++;
		}else {
			if (promedio>=2) {
				cantRecuperan++;
			}else {
				cantPierden++;
			}
		}
	}

	private void calcularPromedio(double nota1, double nota2, double nota3) {
		
		double promedio=(nota1+nota2+nota3)/3;
		
		System.out.println("("+nota1+"+"+nota2+"+"+nota3+")/3="+promedio);
		
		if (promedio>=3.5) {
			System.out.println("Gana la materia");
		}else {
			if (promedio>=2) {
				System.out.println("Pierde, pero puede recuperar");
			}else {
				System.out.println("Pierde la materia");
			}
		}
		System.out.println();
	}
}