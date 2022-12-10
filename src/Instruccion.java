public class Instruccion {
    public String operacion;
    /**
     * Notar que como el tipo int inicializa en 0 y no posee tipo nulo (gracias Java)
     * puede suceder la situación que se opera sobre registros 0 o con valores imm 0,
     * por tanto, se inicializan las valores en el menor integer para saber que cuando
     * no poseen este valor significa que se inicializaron
     */
    public String registroDestino="";
    public String registroPrincipal="";
    public String registroSecundario="";
    public int valorImm=Integer.MIN_VALUE;

    /**
     * Constructor para instruccion nop o ret
     */
    public Instruccion(String operacion){
        this.operacion=operacion;
    }

    /**
     * Constructor para instrucción cmp|not|mov
     */
    public Instruccion(String operacion,String registroDestino,int valorImm) {
        this.operacion=operacion;
        this.registroDestino=registroDestino;
        this.valorImm=valorImm;
    }

    /**
     * Constructor para operacion add|sub|mul|div|mod|and|or|lsl|lsr|asr
     * sin valor inmutable
     */
    public Instruccion(String operacion, String registroDestino, String registroPrincipal, String registroSecundario){
        this.operacion=operacion;
        this.registroDestino=registroDestino;
        this.registroPrincipal=registroPrincipal;
        this.registroSecundario=registroSecundario;
    }

    /**
     * Constructor para operacion add|sub|mul|div|mod|and|or|lsl|lsr|asr
     * con valor inmutable
     */
    public Instruccion(String operacion, String registroDestino, String registroPrincipal, int valorImm){
        this.operacion=operacion;
        this.registroDestino=registroDestino;
        this.registroPrincipal=registroPrincipal;
        this.valorImm=valorImm;
    }
    
    
    /* A continuación vienen los metodos para verificar si se inicializaron los registros o valor inmutable,
     * más que nada por seguridad
    */

    public boolean regDestInicializado() {
        if(registroDestino!="") {
            return true;
        }
        return false;
    }

    public boolean regPrinInicializado() {
        if(registroPrincipal!="") {
            return true;
        }
        return false;
    }

    public boolean regSecInicializado() {
        if(registroSecundario!="") {
            return true;
        }
        return false;
    }

    public boolean valorImmInicializado() {
        if(valorImm!=Integer.MIN_VALUE) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Instruccion [operacion=" + operacion + ", registroDestino=" + registroDestino
                + ", registroPrincipal=" + registroPrincipal + ", registroSecundario="
                + registroSecundario + ", valorImm=" + valorImm + "]";
    }
}