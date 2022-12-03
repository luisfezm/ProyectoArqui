public class Instruccion {
    public String operacion;
    /**
     * Notar que como el tipo int inicializa en 0 y no posee tipo nulo (gracias Java)
     * puede suceder la situación que se opera sobre registros 0 o con valores imm 0,
     * por tanto, se inicializan las valores en el menor integer para saber que cuando
     * no poseen este valor significa que se inicializaron
     */
    public int indexRegistroDestino=Integer.MIN_VALUE;
    public int indexRegistroPrincipal=Integer.MIN_VALUE;
    public int indexRegistroSecundario=Integer.MIN_VALUE;
    public int valorImm;

    /**
     * Constructor para instruccion nop o ret
     */
    public Instruccion(String operacion){
        this.operacion=operacion;
    }

    /**
     * Constructor para instrucción cmp|not|mov
     */
    public Instruccion(String operacion,int indexRegistroDestino,int valorImm) {
        this.operacion=operacion;
        this.indexRegistroDestino=indexRegistroDestino;
        this.valorImm=valorImm;
    }

    /**
     * Constructor para operacion add|sub|mul|div|mod|and|or|lsl|lsr|asr
     * sin valor inmutable
     */
    public Instruccion(String operacion, int indexRegistroDestino, int indexRegistroPrincipal, int valFinal, boolean conImm) {
        this.operacion=operacion;
        this.indexRegistroDestino=indexRegistroDestino;
        this.indexRegistroPrincipal=indexRegistroPrincipal;
        if(conImm) {
            this.valorImm=valFinal;
        }else {
            this.indexRegistroSecundario=valFinal;
        }
    }




    public void add() {}
    public void sub() {}
    public void mul() {}
    public void div() {}
    public void mod() {}
    public void and() {}
    public void or() {}
    public void lsl() {}
    public void lsr() {}
    public void asr() {}
    public void cmp() {}
    public void not() {}
    public void mov() {}
    public void nop() {}
    public void ret() {}

    public boolean regDestInicializado() {
        if(indexRegistroDestino!=Integer.MIN_VALUE) {
            return true;
        }
        return false;
    }

    public boolean regPrinInicializado() {
        if(indexRegistroPrincipal!=Integer.MIN_VALUE) {
            return true;
        }
        return false;
    }

    public boolean regSecInicializado() {
        if(indexRegistroSecundario!=Integer.MIN_VALUE) {
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
        return "Instruccion [operacion=" + operacion + ", indexRegistroDestino=" + indexRegistroDestino
                + ", indexRegistroPrincipal=" + indexRegistroPrincipal + ", indexRegistroSecundario="
                + indexRegistroSecundario + ", valorImm=" + valorImm + "]";
    }
}