import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Arquitectura arq = new Arquitectura();
        VerificadorPipeline verificador = null;
        Scanner sc= new Scanner(System.in);

        try {
            System.out.println("Ingrese el nombre del archivo de entrada:");
            String nombreArchivo = sc.nextLine();

            Archivo archivo1 = new Archivo(nombreArchivo);
            archivo1.leerArchivo();
            //verificador= new VerificadorPipeline(archivo1.getInstrucciones());;
            if (archivo1.getFormatoCorrecto()) {
                System.out.println("Archivo en formato correcto \n");

                int opcionEscogida = 0;
                verificador=new VerificadorPipeline(archivo1.getInstrucciones());
                while(opcionEscogida!=4){
                    System.out.println("\n---------\nIngrese \n" +
                            "1. Si desea ejecutar las instrucciones \n" +
                            "2. Si desea verificar pipelines \n" +
                            "3. Si desea reparar pipelines \n" +
                            "4. SI desea salir");
                    opcionEscogida=Integer.parseInt(sc.nextLine());
                    switch (opcionEscogida){
                        case 1:
                            //arq.ejecutarInstrucciones(archivo1.getInstrucciones());
                            break;
                        case 2:
                            archivo1.getInstrucciones().mostrarLista();
                            System.out.println("----");
                            verificador.pipeline();
                            System.out.println("----");
                            verificador.listaInstrucciones.mostrarLista();
                            break;
                        case 3:
                            break;
                    }
                }
            } else {
                System.out.println(
                        "El formato del archivo es incorrecto, por favor ve en la carpeta DEBUG_ARchivosDePrueba \n" +
                                "el archivo: DEBUG_" + nombreArchivo);
            }

        }catch (Exception e){
            System.err.println(e);
        }

    }
}