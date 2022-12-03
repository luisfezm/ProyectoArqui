public class Main {
    public static void main(String[] args) {
        Arquitectura arq=new Arquitectura();

        String nombreArchivo="archivoPrueba2";
        Archivo archivo1=new Archivo(nombreArchivo);
        archivo1.leerArchivo();

        if(archivo1.getFormatoCorrecto()){
            System.out.println("Archivo en formato correcto");
            arq.ejecutarInstrucciones(archivo1.getInstrucciones());
        }else{
            System.out.println("El formato del archivo es incorrecto, por favor ve en la carpeta DEBUG_ARchivosDePrueba \n" +
                    "el archivo: DEBUG_"+nombreArchivo);
        }
    }
}