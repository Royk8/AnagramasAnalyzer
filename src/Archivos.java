import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Archivos {
    /**
     * Metodos para leer el archivo
     * @param nombreArchivo Se envia el nombre del archivo como parametro
     */
    public static String leerArchivo(String nombreArchivo){
        BufferedReader readFile = null;
        File archivo = new File(nombreArchivo + ".txt");
        if (!archivo.exists()){
            System.out.println("No se encontró el archivo");
            return null;
        }
        String texto = "";
        try {
            readFile = new BufferedReader(new FileReader(archivo));
            String line;
            while ((line = readFile.readLine()) != null) {
                texto = texto + "\n" + line;
            }
        }catch(IOException ioe){
            System.out.println("El archivo no se pudo leer");
        }finally {
            try {
                if (readFile != null) {
                    readFile.close();
                }
            }catch(IOException ioe) {
                System.out.println("El archivo no se pudo cerrar");
            }
        }
        return texto;
    }
}
