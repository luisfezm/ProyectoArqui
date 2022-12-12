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

    public boolean ejecutarInstrucciones(ListaEnlazadaInstrucciones listaInstrucciones) {
        System.out.println("REGISTROS EN UN INICIO");
        mostrarRegistros();
        System.out.println();

        /*
        for (Instruccion instruccion : instrucciones) {
            System.out.println(instruccion.toString());
            determinarOperacion(instruccion);
            mostrarRegistros(); // para ir debugueando
        }
         */
        Instruccion instruccionIterada=listaInstrucciones.instruccionInicial;
        while(instruccionIterada!=null){
            System.out.println("\n----\n"+instruccionIterada.toString());
            determinarOperacion(instruccionIterada);
            mostrarRegistros();
            instruccionIterada=instruccionIterada.instruccionSiguiente;
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
            case "or":
                if (instruccion.registroSecundario == "") {
                    operacionOr( // con imm
                            instruccion.registroDestino,
                            instruccion.registroPrincipal,
                            instruccion.valorImm);
                } else {
                    operacionOr( // en caso contrario, es con registro
                            instruccion.registroDestino,
                            instruccion.registroPrincipal,
                            instruccion.registroSecundario);
                }
                break;
            case "not":
                if (instruccion.registroSecundario == "") {
                    operacionNot( // con imm
                            instruccion.registroDestino,
                            instruccion.valorImm);
                } else {
                    operacionNot( // en caso contrario, es con registro
                            instruccion.registroDestino,
                            instruccion.registroPrincipal);
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
            case "lsl":
                if (instruccion.registroSecundario == "") {
                    operacionLsl(instruccion.registroDestino, instruccion.registroPrincipal, instruccion.valorImm);
                } else {
                    operacionLsl(instruccion.registroDestino, instruccion.registroPrincipal,
                            instruccion.registroSecundario);
                }
                break;
            case "lsr":
                if (instruccion.registroSecundario == "") {
                    operacionLsr(instruccion.registroDestino, instruccion.registroPrincipal, instruccion.valorImm);
                } else {
                    operacionLsr(instruccion.registroDestino, instruccion.registroPrincipal,
                            instruccion.registroSecundario);
                }
                break;
            case "asr":
                if (instruccion.registroSecundario == "") {
                    operacionAsr(instruccion.registroDestino, instruccion.registroPrincipal, instruccion.valorImm);
                } else {
                    operacionAsr(instruccion.registroDestino, instruccion.registroPrincipal,
                            instruccion.registroSecundario);
                }
                break;
            case "nop":
                // no hace nada
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
        String binarioPrin = getBinarioValorRegistro(registroPrincipal);
        String binarioSec = getBinarioValorRegistro(registroSecundario);
        String binarioResultante = "";
        for (int i = 0; i < 16; i++) {
            if (binarioPrin.charAt(i) == '1' && binarioSec.charAt(i) == '1') {
                binarioResultante += "1";
            } else {
                binarioResultante += "0";
            }
        }
        setBinarioValorRegistro(registroDestino, binarioResultante);
    }

    public void operacionAnd(String registroDestino, String registroPrincipal, int valorImm) {
        String binarioPrin = getBinarioValorRegistro(registroPrincipal);
        String binarioImm = Integer.toBinaryString(valorImm);
        binarioImm = completarBinario16B(binarioImm);
        String binarioResultante = "";
        for (int i = 0; i < 16; i++) {
            if (binarioPrin.charAt(i) == '1' && binarioImm.charAt(i) == '1') {
                binarioResultante += "1";
            } else {
                binarioResultante += "0";
            }
        }
        setBinarioValorRegistro(registroDestino, binarioResultante);
    }

    public void operacionOr(String registroDestino, String registroPrincipal, String registroSecundario) {
        String binarioPrin = getBinarioValorRegistro(registroPrincipal);
        String binarioSec = getBinarioValorRegistro(registroSecundario);
        String binarioResultante = "";
        for (int i = 0; i < 16; i++) {
            if (binarioPrin.charAt(i) == '1' || binarioSec.charAt(i) == '1') {
                binarioResultante += "1";
            } else {
                binarioResultante += "0";
            }
        }
        setBinarioValorRegistro(registroDestino, binarioResultante);
    }

    public void operacionOr(String registroDestino, String registroPrincipal, int valorImm) {
        String binarioPrin = getBinarioValorRegistro(registroPrincipal);
        String binarioImm = Integer.toBinaryString(valorImm);
        binarioImm = completarBinario16B(binarioImm);
        String binarioResultante = "";
        for (int i = 0; i < 16; i++) {
            if (binarioPrin.charAt(i) == '1' || binarioImm.charAt(i) == '1') {
                binarioResultante += "1";
            } else {
                binarioResultante += "0";
            }
        }
        setBinarioValorRegistro(registroDestino, binarioResultante);
    }

    public void operacionNot(String registroDestino, String registroPrincipal) {
        String binarioPrin = getBinarioValorRegistro(registroPrincipal);
        String binarioNot = "";
        for (int i = 0; i < 16; i++) {
            if (binarioPrin.charAt(i) == '1') {
                binarioNot += 0;
            } else { // si es cero
                binarioNot += 1;
            }
        }
        setBinarioValorRegistro(registroDestino, binarioNot);
    }

    public void operacionNot(String registroDestino, int valorImm) {
        // recordar que el binario del valor imm no necesariamente tiene los 16B que
        // estamos
        // trabajando, por tanto, hay que completarlo antes de realizar la operación NOT
        String binarioImm = completarBinario16B(Integer.toBinaryString(valorImm));
        String binarioNot = "";
        for (int i = 0; i < 16; i++) {
            if (binarioImm.charAt(i) == '1') {
                binarioNot += 0;
            } else { // si es cero
                binarioNot += 1;
            }
        }
        setBinarioValorRegistro(registroDestino, binarioNot);
    }

    /* ACÁ IRIÁN OPERACIONES CMP,AND;OR,NOT */

    public void operacionMov(String registroDestino, String registroPrincipal) {
        setValorRegistro(registroDestino, getValorRegistro(registroPrincipal));
    }

    public void operacionMov(String registroDestino, int Imm) {
        setValorRegistro(registroDestino, Imm);
    }

    /* ACÁ IRIÁN OPERACIONES LSL,LSR,ASR */

    public void operacionLsl(String registroDestino, String registroPrincipal, String registroSecundario) {
        if (getValorRegistro(registroSecundario) >= 16) {
            setBinarioValorRegistro(registroDestino, "0000000000000000");
        } else {
            String binarioPrin = getBinarioValorRegistro(registroPrincipal);
            String nuevoBinario = binarioPrin.substring(getValorRegistro(registroSecundario), binarioPrin.length());
            for (int i = 0; i < getValorRegistro(registroSecundario); i++) {
                nuevoBinario += "0";
            }
            setBinarioValorRegistro(registroDestino, nuevoBinario);
        }
    }

    public void operacionLsl(String registroDestino, String registroPrincipal, int valorImm) {
        if (valorImm >= 16) {
            setBinarioValorRegistro(registroDestino, "0000000000000000");
        } else {
            String binarioPrin = getBinarioValorRegistro(registroPrincipal);
            String nuevoBinario = binarioPrin.substring(valorImm, binarioPrin.length());
            for (int i = 0; i < valorImm; i++) {
                nuevoBinario += "0";
            }
            setBinarioValorRegistro(registroDestino, nuevoBinario);
        }
    }

    public void operacionLsr(String registroDestino, String registroPrincipal, String registroSecundario) {
        if (getValorRegistro(registroSecundario) >= 16) {
            setBinarioValorRegistro(registroDestino, "0000000000000000");
        } else {
            String binarioPrin = getBinarioValorRegistro(registroPrincipal);
            String nuevoBinario = "";
            for (int i = 0; i < getValorRegistro(registroSecundario); i++) {
                nuevoBinario += "0";
            }
            nuevoBinario += binarioPrin.substring(0, binarioPrin.length() - getValorRegistro(registroSecundario));

            setBinarioValorRegistro(registroDestino, nuevoBinario);
        }
    }

    public void operacionLsr(String registroDestino, String registroPrincipal, int valorImm) {
        if (valorImm >= 16) {
            setBinarioValorRegistro(registroDestino, "0000000000000000");
        } else {
            String binarioPrin = getBinarioValorRegistro(registroPrincipal);
            String nuevoBinario = "";
            for (int i = 0; i < valorImm; i++) {
                nuevoBinario += "0";
            }
            nuevoBinario += binarioPrin.substring(0, binarioPrin.length() - valorImm);

            setBinarioValorRegistro(registroDestino, nuevoBinario);
        }
    }

    public void operacionAsr(String registroDestino, String registroPrincipal, String registroSecundario) {
        if (getValorRegistro(registroSecundario) >= 16) {
            String binarioPrin = getBinarioValorRegistro(registroPrincipal);
            if (binarioPrin.charAt(0) == '0') {
                setBinarioValorRegistro(registroDestino, "0000000000000000");
            } else {
                setBinarioValorRegistro(registroDestino, "1111111111111111");
            }

        } else {
            String binarioPrin = getBinarioValorRegistro(registroPrincipal);
            String nuevoBinario = "";
            for (int i = 0; i < getValorRegistro(registroSecundario); i++) {
                if (binarioPrin.charAt(0) == '0') {
                    nuevoBinario += "0";
                } else {
                    nuevoBinario += "1";
                }
            }
            nuevoBinario += binarioPrin.substring(0, binarioPrin.length() - getValorRegistro(registroSecundario));
            setBinarioValorRegistro(registroDestino, nuevoBinario);
        }
    }

    public void operacionAsr(String registroDestino, String registroPrincipal, int valorImm) {
        if (valorImm >= 16) {
            String binarioPrin = getBinarioValorRegistro(registroPrincipal);
            if (binarioPrin.charAt(0) == '0') {
                setBinarioValorRegistro(registroDestino, "0000000000000000");
            } else {
                setBinarioValorRegistro(registroDestino, "1111111111111111");
            }

        } else {
            String binarioPrin = getBinarioValorRegistro(registroPrincipal);
            String nuevoBinario = "";
            for (int i = 0; i < valorImm; i++) {
                if (binarioPrin.charAt(0) == '0') {
                    nuevoBinario += "0";
                } else {
                    nuevoBinario += "1";
                }
            }
            nuevoBinario += binarioPrin.substring(0, binarioPrin.length() - valorImm);
            setBinarioValorRegistro(registroDestino, nuevoBinario);
        }
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
     * Metodo para setear un binario en el valor (númerico) en un registro según su
     * id
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

    public String completarBinario16B(String binario) {
        int largoBinario = binario.length();
        if (largoBinario == 16) {
            return binario;
        } else {
            String binarioCorrecto = "";
            for (int i = 0; i < (16 - largoBinario); i++) {
                binarioCorrecto += "0";
            }
            binarioCorrecto += binario;
            return binarioCorrecto;
        }
    }
}
