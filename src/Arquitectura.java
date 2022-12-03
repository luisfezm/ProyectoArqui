import java.util.ArrayList;

public class Arquitectura {
    private ArrayList<Registro> registros;

    public Arquitectura() {
        registros=new ArrayList<>();
        // inicialmente, todos los registros tienen como valor un 0
        for(int i=0;i<=16;i++) {
            registros.add(new Registro("0"));
        }
    }
    public boolean ejecutarInstrucciones(ArrayList<Instruccion> instrucciones ) {
        for(Instruccion instruccion: instrucciones) {
            System.err.println(instruccion.toString());
        }
        return true;
    }

    public void mostrarRegistros(){
        // recorremos cada Registro del registro
        for(int i=0;i<=16;i++){
            System.out.println("[N° registro: "+i+" ; Contenido: "+registros.get(i).getValor()+"]");
        }
    }
}