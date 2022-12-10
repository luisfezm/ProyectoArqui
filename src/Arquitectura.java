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

    /*
     * Metodo para obtener el valor de un registro según su id
     */
    public Integer getRegistro(String id){
        for(Registro registro: registros){
            if( registro.getId() == id){
                return registro.getValor();
            }
        }
        return null; // no encontró el id del registro
    }

    /*
     * Metodo para setear un valor en un registro según su id
     */
    public void setValueOnRegistro(String id,int value){
        for(Registro registro: registros){
            if( registro.getId() == id){
                registro.setValor(value);
            }
        }
    }

    public void mostrarRegistros(){
        // recorremos cada Registro del registro
        for(Registro registro: registros){
            System.out.println(registro.mostrarRegistro());
        }
    }


}