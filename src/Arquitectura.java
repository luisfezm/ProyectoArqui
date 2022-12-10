import java.util.ArrayList;

public class Arquitectura {
    private ArrayList<Registro> registros;

    public Arquitectura() {
        registros=new ArrayList<>();
        // inicialmente, todos los registros tienen como valor un 0
        for(int i=0;i<=16;i++) {
            registros.add(new Registro("rs"+i,2));
        }
    }
    public boolean ejecutarInstrucciones(ArrayList<Instruccion> instrucciones ) {
        for(Instruccion instruccion: instrucciones) {
            System.out.println(instruccion.toString());
            determinarOperacion(instruccion);
            mostrarRegistros(); // para ir debugueando
        }
        return true;
    }

    public void determinarOperacion(Instruccion instruccion){
        //System.out.println("DEBUG - Op instrucción:"+ instruccion.operacion);
        switch(instruccion.operacion){
            /* --------- Operación de division ---------*/
            case "div":
                /* Como existen divisiones con registro o imm, debemos determinar de qué tipo se trata */
                /* VER CLASE INSTRUCCION: como el registro secundario de una instrucción inicializa en ""
                 * entonces, si este al instanciar una Instrucción operación div sigue siendo "", es entonces
                 * una operación con Imm
                 */
                if(instruccion.registroSecundario == ""){
                    operacionDivision(
                        instruccion.registroDestino,
                        instruccion.registroPrincipal,
                        instruccion.valorImm
                    );
                }else{ // en caso contrario, es con registro
                    operacionDivision(
                        instruccion.registroDestino,
                        instruccion.registroPrincipal,
                        instruccion.registroSecundario
                    );
                }
                break;
            /* --------- Operación mod --------- */   
            case "mod":
                break;
            /* --------- Operación and --------- */
            case "and":
                break;
        }
    }

    /* -----------------------------------OPERACIONES---------------------------------------------- */
    public void operacionDivision(String registroDestino, String registroPrincipal, String registroSecundario){
        int resultado= getValorRegistro(registroPrincipal)/getValorRegistro(registroSecundario);
        setValorRegistro(registroDestino, resultado);
    }

    public void operacionDivision(String registroDestino, String registroPrincipal, int Imm){
        int resultado= getValorRegistro(registroPrincipal)/Imm;
        setValorRegistro(registroDestino, resultado);   
    }


    /*
     * Metodo para obtener el valor de un registro según su id
     */
    public Integer getValorRegistro(String id){
        for(Registro registro: registros){
            if( registro.getId().equals(id)){
                return registro.getValor();
            }
        }
        return null; // no encontró el id del registro
    }

    /*
     * Metodo para setear un valor en un registro según su id
     */
    public void setValorRegistro(String id,int value){
        for(Registro registro: registros){
            if( registro.getId().equals(id)){
                registro.setValor(value);
            }
        }
    }

    public void mostrarRegistros(){
        // recorremos cada Registro del registro
        System.out.println();
        for(Registro registro: registros){
            System.out.println(registro.mostrarRegistro());
        }
    }


}