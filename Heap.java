import java.util.Scanner;
// No requiere que lea el documento en memoria y lo escriba en el archivo
import org.xml.sax.*;
// leer un archivo XML en la memoria donde está el documento
// almacenado como un montón de objetos. Puedes escribir en el archivo
import org.w3c.dom.*;
// Reunir elementos XML en objetos DOM
import javax.xml.parsers.*;
public class Heap{
	private String[] Heap;
	private int size;
	private int maxsize;
	private static final int FRONT = 1;
	public Heap(int maxsize)
	{
		this.maxsize = maxsize;
		this.size = 0;
		Heap = new String[this.maxsize + 1];
	}
	public static void main(String[] args){
		// Crea un objeto DOM en la memoria. Ahora puedes acceder
		// a datos en el archivo xml
		Document xmlDoc = getDocument("./src/animales.xml");
        // La lista de nodos contiene todos los nodos en el archivo xml
        NodeList nodos = xmlDoc.getElementsByTagName("nodo");
        // El elemento que quieremos imprimir
        String elementName = "oracion";
        // Enviar el NodeList para su procesamiento
        getElement(nodos, elementName);
		// Devuelve un objeto Element representado por el elemento raíz
		// del archivo xml
		Element theRoot = xmlDoc.getDocumentElement();
		// Consigue el primer hijo o elemento del monticulo
		Element oracionElemento = (Element)theRoot.getFirstChild();
		// Objeto que contendrá todos los datos del monticulo
		mundo animales;
		// Recorre todos los elementos del monticulo hasta el final.
		Heap Heap = new Heap(15);
		while(oracionElemento != null){
			// Va a buscar el primer elemento en mostrar que es nombre
			animales = getNodo(oracionElemento);
			Heap.insert(animales.oracion);
			// Conseguir el proximo nodo
			oracionElemento = (Element)oracionElemento.getNextSibling();
		}
		Heap.printRecursive(1);
	}
	// Función para insertar un nodo en el monticulo
	public void insert(String element)
	{
		Heap[++size] = element;
		int current = size;
	}
	// Función para imprimir los contenidos del monticulo
	// de manera recursiva
	public void printRecursive(int i){
		if(Heap[2*i]==null||Heap[2*i+1]==null){
			System.out.println(i+", "+(2*i)+": "+(Heap[2*i]==null)
			+", "+(2*i+1)+": "+(Heap[2*i+1]==null));
			System.out.println("Respuesta: " + Heap[i]);
		}else{
			System.out.println(i+", "+(2*i)+": "+(Heap[2*i]==null)
			+", "+(2*i+1)+": "+(Heap[2*i+1]==null));
			System.out.println("Pregunta: " + Heap[i]);
			System.out.print("Verdadero(1) o Falso(0): ");
			Scanner lector = new Scanner(System.in);
			int respuesta = lector.nextInt();
			switch(respuesta){
				case 1:
					printRecursive(2*i);
					break;
				case 0:
					printRecursive(2*i+1);
					break;
				default:
					break;
			}
		}
	}
	// Lee un archivo XML en un documento DOM
	private static Document getDocument(String docString) {
		try {
			// API utilizada para convertir XML en un árbol de objetos DOM
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			// Ignorar todos los comentarios.
			factory.setIgnoringComments(true);
			// Ignora los espacios en blanco en los elementos.
			factory.setIgnoringElementContentWhitespace(true);
			// Valida el XML a medida que se analiza
			factory.setValidating(true);
			// Proporciona acceso a los datos de documentos
			DocumentBuilder builder = factory.newDocumentBuilder();
			// Toma el documento
			return builder.parse(new InputSource(docString));
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return null;
	}
	private static mundo getNodo(Element ele){
		// Devuelve el primer elemento hijo en el archivo xml.
		// Ese elemento es nombre
		Element nameElement = (Element)ele.getFirstChild();
		// Guarda el valor en este elemento como una cadena
		String oracion = getTextValue(nameElement).trim();
		// Crea un objeto mundo que contenga los valores almacenados
		// en los elementos del espectáculo
		return new mundo(oracion);
	}
	private static String getTextValue(Node theNode) {
		// Obtener el valor del primer elemento hijo
		return theNode.getFirstChild().getNodeValue();
	}

	private static void getElement(NodeList nodos, String elementName){
		try{
			// Recorre el número de nodos
        	for(int i=0; i < nodos.getLength(); i++){
        		// Consigue el primer nodo de show
        		Node nodoMonticulo = nodos.item(i);
        		// Convertir en un elemento para obtener acceso a los métodos de elementos
        		Element oracionElemento = (Element)nodoMonticulo;
        		// Crear una lista de nodos que tienen el nombre definido en elementName
        		NodeList networkList = oracionElemento.getElementsByTagName(elementName);
        		// Consigue el primer y único elemento en esta situación
        		Element networkElement = (Element)networkList.item(0);
        		// Devuelve una lista de elementos de nodo
        		// Cada valor está en un nodo al lado del nodo de red
        		// Es por eso que tienes que conseguir los nodos hijos para la red
                NodeList elementList = networkElement.getChildNodes();
        	}
        }
        catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}

	private static class mundo{
		public String oracion, showNetwork;
		public mundo(String oracion){
			this.oracion = oracion;
		}
	}
}
