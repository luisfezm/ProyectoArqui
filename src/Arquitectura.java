import java.util.ArrayList;

public class Arquitectura {
    private ArrayList<Registro> registros;

    public Arquitectura() {
        registros = new ArrayList<>();
        // inicialmente, todos los registros tienen como valor un 0
        for (int i = 0; i <= 16; i++) {
            registros.add(new Registro("rs" + i, 0));
        }
    }

    public boolean ejecutarInstrucciones(ArrayList<Instruccion> instrucciones) {
        System.out.println("REGISTROS EN UN INICIO");
        mostrarRegistros();
        System.out.println();

        for (Instruccion instruccion : instrucciones) {
            System.out.println(instruccion.toString());
            determinarOperacion(instruccion);
            mostrarRegistros(); // para ir debugueando
        }
        return true;
    }

    public void determinarOperacion(Instruccion instruccion) {
        // System.out.println("DEBUG - Op instrucción:"+ instruccion.operacion);
        switch (instruccion.operacion) {
            /* --------- Operación de Add --------- */
            case "add":
                /*
                 * Como existen sumas con registro o imm, debemos determinar de qué tipo se
                 * trata
                 */
                /*
                 * VER CLASE INSTRUCCION: como el registro secundario de una instrucción
                 * inicializa en ""
                 * entonces, si este al instanciar una Instrucción operación add sigue siendo
                 * "", es entonces
                 * una operación con Imm
                 */
                if (instruccion.registroSecundario == "") {
                    operacionAdd(
                            instruccion.registroDestino,
                            instruccion.registroPrincipal,
                            instruccion.valorImm);
                } else { // en caso contrario, es con registro
                    operacionAdd(
                            instruccion.registroDestino,
                            instruccion.registroPrincipal,
                            instruccion.registroSecundario);
                }
                break;
            case "sub":
                /*
                 * Como existen restas con registro o imm, debemos determinar de qué tipo se
                 * trata
                 */
                /*
                 * VER CLASE INSTRUCCION: como el registro secundario de una instrucción
                 * inicializa en ""
                 * entonces, si este al instanciar una Instrucción operación sub sigue siendo
                 * "", es entonces
                 * una operación con Imm
                 */
                if (instruccion.registroSecundario == "") {
                    operacionSub(
                            instruccion.registroDestino,
                            instruccion.registroPrincipal,
                            instruccion.valorImm);
                } else { // en caso contrario, es con registro
                    operacionSub(
                            instruccion.registroDestino,
                            instruccion.registroPrincipal,
                            instruccion.registroSecundario);
                }
                break;
            case "mul":
                /*
                 * Como existen multiplicaciones con registro o imm, debemos determinar de qué
                 * tipo se trata
                 */
                /*
                 * VER CLASE INSTRUCCION: como el registro secundario de una instrucción
                 * inicializa en ""
                 * entonces, si este al instanciar una Instrucción operación mul sigue siendo
                 * "", es entonces
                 * una operación con Imm
                 */
                if (instruccion.registroSecundario == "") {
                    operacionMul(
                            instruccion.registroDestino,
                            instruccion.registroPrincipal,
                            instruccion.valorImm);
                } else { // en caso contrario, es con registro
                    operacionMul(
                            instruccion.registroDestino,
                            instruccion.registroPrincipal,
                            instruccion.registroSecundario);
                }
                break;
            /* --------- Operación de division --------- */
            case "div":
                /*
                 * Como existen divisiones con registro o imm, debemos determinar de qué tipo se
                 * trata
                 */
                /*
                 * VER CLASE INSTRUCCION: como el registro secundario de una instrucción
                 * inicializa en ""
                 * entonces, si este al instanciar una Instrucción operación div sigue siendo
                 * "", es entonces
                 * una operación con Imm
                 */
                if (instruccion.registroSecundario == "") {
                    operacionDivision(
                            instruccion.registroDestino,
                            instruccion.registroPrincipal,
                            instruccion.valorImm);
                } else { // en caso contrario, es con registro
                    operacionDivision(
                            instruccion.registroDestino,
                            instruccion.registroPrincipal,
                            instruccion.registroSecundario);
                }
                break;
            /* --------- Operación mod --------- */
            case "mod":
                if (instruccion.registroSecundario == "") {
                    operacionMod( // con imm
                            instruccion.registroDestino,
                            instruccion.registroPrincipal,
                            instruccion.valorImm);
                } else {
                    operacionMod( // en caso contrario, es con registro
                            instruccion.registroDestino,
                            instruccion.registroPrincipal,
                            instruccion.registroSecundario);
                }
                break;
            case "cmp":
                break;
            case "and":
                if (instruccion.registroSecundario == "") {
                        operacionAnd( // con imm
                                instruccion.registroDestino,
                                instruccion.registroPrincipal,
                                instruccion.valorImm);
                } else {
                    operacionAnd( // en caso contrario, es con registro
                            instruccion.registroDestino,
                            instruccion.registroPrincipal,
                            instruccion.registroSecundario);
                }
                break;
            /* --------- Operación mov --------- */
            case "mov":
                if (instruccion.registroPrincipal == "") { // con imm
                    operacionMov(
                            instruccion.registroDestino,
                            instruccion.valorImm);
                } else { // con registro
                    operacionMov(
                            instruccion.registroDestino,
                            instruccion.registroPrincipal);
                }
                break;
        }
    }

    /*
     * -----------------------------------OPERACIONES-------------------------------
     * ---------------
     */
    public void operacionAdd(String registroDestino, String registroPrincipal, String registroSecundario) {
        int resultado = getValorRegistro(registroPrincipal) + getValorRegistro(registroSecundario);
        setValorRegistro(registroDestino, resultado);
    }

    public void operacionAdd(String registroDestino, String registroPrincipal, int Imm) {
        int resultado = getValorRegistro(registroPrincipal) + Imm;
        setValorRegistro(registroDestino, resultado);
    }

    public void operacionSub(String registroDestino, String registroPrincipal, String registroSecundario) {
        int resultado = getValorRegistro(registroPrincipal) - getValorRegistro(registroSecundario);
        setValorRegistro(registroDestino, resultado);
    }

    public void operacionSub(String registroDestino, String registroPrincipal, int Imm) {
        int resultado = getValorRegistro(registroPrincipal) - Imm;
        setValorRegistro(registroDestino, resultado);
    }

    public void operacionMul(String registroDestino, String registroPrincipal, String registroSecundario) {
        int resultado = getValorRegistro(registroPrincipal) * getValorRegistro(registroSecundario);
        setValorRegistro(registroDestino, resultado);
    }

    public void operacionMul(String registroDestino, String registroPrincipal, int Imm) {
        int resultado = getValorRegistro(registroPrincipal) * Imm;
        setValorRegistro(registroDestino, resultado);
    }

    public void operacionDivision(String registroDestino, String registroPrincipal, String registroSecundario) {
        int resultado = getValorRegistro(registroPrincipal) / getValorRegistro(registroSecundario);
        setValorRegistro(registroDestino, resultado);
    }

    public void operacionDivision(String registroDestino, String registroPrincipal, int Imm) {
        int resultado = getValorRegistro(registroPrincipal) / Imm;
        setValorRegistro(registroDestino, resultado);
    }

    public void operacionMod(String registroDestino, String registroPrincipal, String registroSecundario) {
        int resultado = getValorRegistro(registroPrincipal) % getValorRegistro(registroSecundario);
        setValorRegistro(registroDestino, resultado);
    }

    public void operacionMod(String registroDestino, String registroPrincipal, int Imm) {
        int resultado = getValorRegistro(registroPrincipal) % Imm;
        setValorRegistro(registroDestino, resultado);

    }

    public void operacionAnd(String registroDestino, String registroPrincipal, String registroSecundario) {
        String binarioPrin=getBinarioValorRegistro(registroPrincipal);
        String binarioSec=getBinarioValorRegistro(registroSecundario);
        String binarioResultante="";
        for(int i=0;i<16;i++){
            if(binarioPrin.charAt(i)=='1' && binarioSec.charAt(i)=='1'){
                binarioResultante+="1";
            }else{
                binarioResultante+="0";
            }
        }
        setBinarioValorRegistro(registroDestino, binarioResultante);
    }

    public void operacionAnd(String registroDestino, String registroPrincipal, int valorImm) {
        String binarioPrin=getBinarioValorRegistro(registroPrincipal);
        String binarioImm=Integer.toBinaryString(valorImm);
        binarioImm=completarBinario16B(binarioImm);
        String binarioResultante="";
        for(int i=0;i<16;i++){
            if(binarioPrin.charAt(i)=='1' && binarioImm.charAt(i)=='1'){
                binarioResultante+="1";
            }else{
                binarioResultante+="0";
            }
        }
        setBinarioValorRegistro(registroDestino, binarioResultante);
    }

    /* ACÁ IRIÁN OPERACIONES CMP,AND;OR,NOT */

    public void operacionMov(String registroDestino, String registroPrincipal) {
        setValorRegistro(registroDestino, getValorRegistro(registroPrincipal));
    }

    public void operacionMov(String registroDestino, int Imm) {
        setValorRegistro(registroDestino, Imm);
    }
    /*
     * ----------------------------------- FIN
     * OPERACIONES----------------------------------------------
     */

    /*
     * Metodo para obtener el valor de un registro según su id
     */
    public Integer getValorRegistro(String id) {
        for (Registro registro : registros) {
            if (registro.getId().equals(id)) {
                return registro.getValor();
            }
        }
        return null; // no encontró el id del registro
    }

    /*
     * Metodo para obtener el valor binario de un registro según su id
     */
    public String getBinarioValorRegistro(String id) {
        for (Registro registro : registros) {
            if (registro.getId().equals(id)) {
                return registro.getBinarioValor();
            }
        }
        return null; // no encontró el id del registro
    }

    /*
     * Metodo para setear un binario en el valor (númerico) en un registro según su id
     */
    public void setBinarioValorRegistro(String id, String binario) {
        for (Registro registro : registros) {
            if (registro.getId().equals(id)) {
                registro.setBinarioValor(binario);
            }
        }
    }


    /*
     * Metodo para setear un valor en un registro según su id
     */
    public void setValorRegistro(String id, int value) {
        for (Registro registro : registros) {
            if (registro.getId().equals(id)) {
                registro.setValor(value);
            }
        }
    }

    public void mostrarRegistros() {
        // recorremos cada Registro del registro
        System.out.println();
        for (Registro registro : registros) {
            System.out.println(registro.mostrarRegistro());
        }
    }

    public String completarBinario16B(String binario){
        int largoBinario = binario.length();
        if(largoBinario==16){
            return binario;
        }else{
            String binarioCorrecto="";
            for(int i=0;i<(16-largoBinario);i++){
                binarioCorrecto+="0";
            }
            binarioCorrecto+=binario;
            return binarioCorrecto;
        }
    }
}
