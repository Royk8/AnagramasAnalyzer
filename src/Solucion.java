import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Solucion extends Archivos {
    static Scanner read;
    static String texto;

    public Solucion(){
        read = new Scanner(System.in);
    }

    /**
     * Metodo que buscara un anagrama en el texto
     * Se usara Arraylist para manejar las palabras en una lista, no se usará ningun metodo que no se haya implementado
     * antes en las clases de listas.
     */
    public void resolverEjercicio(){
        ArrayList<String> palabras = dividirPorPalabras();
        ArrayList<String> anagramas = buscarAnagramas(palabras);
        contarYReportar(palabras, anagramas);
    }

    /**
     * Aqui buscaremos cada palabra que sea un anagrama en la lista
     * @param palabras Lista de palabras
     * @return Lista de palabras anagrama
     */
    public ArrayList<String> buscarAnagramas(ArrayList<String> palabras){
        ArrayList<String> anagramas = new ArrayList<>();
        for(int i = 0; i < palabras.size() - 1; i++){
            for(int j = i+1; j < palabras.size(); j++){
                String palabra1 = palabras.get(i);
                String palabra2 = palabras.get(j);
                //Si tienen el mismo tamaño continuamos evaluando
                if(palabras.get(i).length() == palabras.get(j).length()){
                    //Si no son la misma palabra, continua la evaluacion
                    if(!palabras.get(i).equals(palabras.get(j))){
                        //Ver implementacion del metodo sonAnagrama
                        if(sonAnagrama(palabras.get(i), palabras.get(j))){
                            //Si cumplen agregamos ambas palabras a la lista anagrama, excepto si ya estan en la lista
                            if(!anagramas.contains(palabras.get(i)))
                                anagramas.add(palabras.get(i));
                            if(!anagramas.contains(palabras.get(j)))
                                anagramas.add(palabras.get(j));
                        }
                    }
                }
            }
        }
        return anagramas;
    }

    /**
     * Este metodo me convierte el string en un arrayList con las palabras separadas
     * @return ArrayList de palabras
     */
    public ArrayList<String> dividirPorPalabras(){
        ArrayList<String> palabras = new ArrayList<>();
        int i = 0;
        String letra;
        int longitudTexto = texto.length();
        do{
            //Tomamos una letra
            letra = texto.substring(i,i+1);
            boolean mismaPalabra = false;
            String palabra = "";
            //Probamos si es una letra
            while (esLetra(letra)){
                mismaPalabra = true;
                //La ponemos en una palabra
                palabra = palabra + letra;
                i++;
                //Evita la excepcion del final del texto
                if(i == longitudTexto) break;
                letra = texto.substring(i,i+1);
            }//Si encuentra un caracter que no es una letra, termina la palabra y la guarda
            if(mismaPalabra){
                palabras.add(palabra);
            }
            i++;

        }while(i < longitudTexto);
        return palabras;
    }

    /**
     * Recibe dos palabras y verifica si son anagramas
     * @param palabra1
     * @param palabra2
     * @return Retorna true si son anagramas.
     */
    public boolean sonAnagrama(String palabra1, String palabra2 ){
        int longitud = palabra1.length();
        for(int i = 0; i < longitud; i++){
            //Primero verifico si contiene la letra
            if(!palabra2.contains(palabra1.substring(0,1))) return false;
            //Luego, si la contiene, no es suficiente puesto que podria haber problema con las letras repetidas
            //Asi que las letras que se encuentren en ambas palabras, se deben borrar o reemplazar
            //En la palabra 2 reemplazo la primera ocurrencia de la letra buscada por un *
            palabra2 = palabra2.replaceFirst(palabra1.substring(0,1), "*");
            //En la palabra 1 simplemento la borro. Reemplazando la palabra por una que empiece en su segunda letra
            palabra1 = palabra1.substring(1);

        }
        //Si el proceso termina ambas palabras son anagrama
        return true;
    }

    /**
     * Metodo sencillo que crea un vector donde almacena las repeticiones de cada palabra y al final imprime
     * @param palabras Lista de todas las palabras del texto
     * @param anagramas Lista de las palabras que son anagrama
     */
    public void contarYReportar(ArrayList<String> palabras, ArrayList<String> anagramas){
        int[] repeticiones = new int[anagramas.size()];
        for(int i = 0; i < anagramas.size(); i++){
            for(int j = 0; j < palabras.size(); j++){
                if(anagramas.get(i).equals(palabras.get(j)))
                    repeticiones[i]++;
            }
        }

        System.out.println("Los anagramas encontrados y sus frecuencias son: ");
        for(int i = 0; i < anagramas.size(); i++){
            System.out.println(i + ". " + anagramas.get(i) + ": " + repeticiones[i] + " veces.");
        }
    }

    /**
     * Evalua si un simbolo es una letra del alfabeto español
     * @param simbolo simbolo a evaluar
     * @return Retorna true si el simbolo pertenece al alfabeto
     */
    public boolean esLetra(String simbolo){
        String alfabeto = "abcdefghijklmnñopqrstuvwxyz";
        //String[] letras = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","ñ","o","p","q","r","s","t","u","v","w","x","y","z"};
        return alfabeto.contains(simbolo);/*
        for(int i = 0; i < letras.length; i++){

        }*/
    }

    public void iniciarPrograma(){
        System.out.println("Bienvenido a la solicion de parcial (Ejercicio 6) de Roy Maestre \n");
        int opcion = 0;
        do{
            System.out.println("Ingrese 1 para leer un archivo (el cual tendra que poner en la carpeta del proyecto)\n" +
                    "Ingrese 2 para escribir el texto aqui en consola\n" +
                    "Ingrese 0 para salir del programa");
            opcion = readInt(0,2);
            switch (opcion){
                case 1:
                    System.out.println("Cual es el nombre del archivo? (debe ser un archivo txt, escriba el nombre sin extension)");
                    String nombreArchivo = read.nextLine();
                    texto = Archivos.leerArchivo(nombreArchivo);
                    if(texto!=null)
                        resolverEjercicio();
                    break;
                case 2:
                    System.out.println("Escriba el texto a analizar");
                    texto = read.nextLine();
                    System.out.println("Desea agregar otras linea? \n1. Si\t 2. No");
                    int otraLinea = readInt(1,2);
                    while(otraLinea == 1){
                        texto = texto + "\n" + read.nextLine();
                        System.out.println("Desea agregar otras linea? \n1. Si\t 2. No");
                        otraLinea = readInt(1,2);
                    }
                    resolverEjercicio();
                    break;
                case 0:
                    break;
            }
        }while(opcion != 0);
        System.out.println("Puede escribir \"Ejemplo\" o \"cien años de soledad\", para probarlo");
        System.out.println("Hasta luego");

    }

    /**
     * Metodo para leer enteros y manejar excepciones de dicha lectura
     * @param min
     * @param max
     * @return
     */
    public int readInt(int min, int max){
        int n = min-1;
        while(true){
            try{
                n = Integer.parseInt(read.nextLine());
            }catch (InputMismatchException | NumberFormatException ex){
                System.out.println("El numero debe ser entero y ");
            }
            if(n<= max && n>= min) break;
            System.out.println("Debe estar entre "+min+" y "+max);
        }
        return n;
    }

    public static void main(String[] args) {
        Solucion solucion = new Solucion();
        solucion.iniciarPrograma();
        //Si escribe "cien años de soledad" cuando pida el nombre del archivo puede probarlo con el libro
        //tarda unos 4-5 minutos
        //Tambien hay un archivo llamado "Ejemplo" mas sencillito
    }
}
