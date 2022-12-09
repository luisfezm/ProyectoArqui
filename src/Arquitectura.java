import java.util.ArrayList;

public class Arquitectura {
    private ArrayList<Registro> registros;

    public Arquitectura() {
        registros=new ArrayList<>();
        // inicialmente, todos los registros tienen como valor un 0
        for(int i=0;i<=16;i++) {
            registros.add(new Registro("rs"+i,0));
        }
    }
    public boolean ejecutarInstrucciones(ArrayList<Instruccion> instrucciones ) {
        for(Instruccion instruccion: instrucciones) {
            System.err.println(instruccion.toString());
        }
        return true;
    }

    public Registro getRegistro(String id){
        for(Registro registro: registros){
            if( registro.getId() == id){
                return registro;
            }
        }
        return null; // no encontró el id del registro
    }

    public void mostrarRegistros(){
        // recorremos cada Registro del registro
        for(Registro registro: registros){
            System.out.println(registro.mostrarRegistro());
        }
    }

    
}