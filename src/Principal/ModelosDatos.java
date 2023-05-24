package Principal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class ModelosDatos {
	HashMap<String, ArrayList<String>>mapEstudiantes;
	
	public void ModeloDatos() {
		mapEstudiantes=new HashMap<String, ArrayList<String>>();
	}
	public void consultarEstudiante (String documento) {
		if (mapEstudiantes.containsKey(documento)) {
			System.out.println(mapEstudiantes.get(documento));
		}else {
			JOptionPane.showInputDialog(null,"Documento no estiste",
					"Advertencia",JOptionPane.WARNING_MESSAGE);
			
		}
	}
	public HashMap<String, ArrayList<String>> getMapaEstudiantes(){
		return mapEstudiantes;
	}
	public void guardarDatos (ArrayList<String>listaEstudiantes) {
	mapEstudiantes.put(listaEstudiantes.get(0), listaEstudiantes);
	JOptionPane.showMessageDialog(null, "Registro exitoso!");
	}
	public int getSizeMap() {
		return mapEstudiantes.size();
	}
	public void imprimirMapa() {
		Iterator<String> itera=mapEstudiantes.keySet().iterator();
		String llave;
		while (itera.hasNext()) {
			llave= itera.next();
			System.out.println(llave + " - "+mapEstudiantes.get(llave));
		}
	}
}

