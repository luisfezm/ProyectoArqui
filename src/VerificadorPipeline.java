import java.util.ArrayList;
import java.util.Iterator;

public class VerificadorPipeline {
    ArrayList<Instruccion> instrucciones;

    public VerificadorPipeline(ArrayList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

    /*
     * Devolverá falso si la verificación es correcta, si detecta un error: false
     */
    public void pipeline() {
        int cantidadInstrucciones = instrucciones.size();
        ArrayList<String> permitido1 = new ArrayList<>() {
            {
                add("nop");
                add("b");
                add("beq");
                add("bgt");
                add("call");
            }
        };
        ArrayList<String> permitido2 = new ArrayList<>() {
            {
                add("nop");
                add("b");
                add("beq");
                add("bgt");
                add("ret");
                add("cmp");
                add("st");
            }
        };

        // boolean flag = false;
        for (int i = 0; i < cantidadInstrucciones - 1; i++) {
            Instruccion A = instrucciones.get(i);
            Instruccion B = instrucciones.get(i + 1);

            if (A.regDestInicializado()) {
                if (B.regDestInicializado()) {
                    if (B.regPrinInicializado()) {
                        // comparo con el principal
                        // si son iguales retorno false
                        if (!compararRegistros(A.registroDestino, B.registroPrincipal)) { // si arrojó un falso la
                                                                                          // comparacion = return false
                            // return false; // hubo un conflicto con el destino A y principal B
                            // System.out.println("encuentra conflicto para instrucciones: " + A.toString()
                            // + " y " + B.toString());
                            System.out.println("Encuentra conflicto entre instrucciones: " + i + " y " + (i + 1));
                            // flag = true;
                        }
                        if (B.regSecInicializado()) {
                            // comparo con el segundo
                            // si son iguales retorno false
                            if (compararRegistros(A.registroDestino, B.registroSecundario)) {
                                // System.out.println("encuentra conflicto para instrucciones: " + A.toString()
                                // + " y " + B.toString());
                                System.out.println("Encuentra conflicto entre instrucciones: " + i + " y " + (i + 1));
                                // flag = true;
                            }
                        } else {
                            // return true;
                        }
                    } else {
                        // return true;
                    }
                } else { // como no tiene registro destino, menos tendrá principal y secundario/imm
                    // return true;
                }
            } else { // como no tiene registro destino inicializado el actual, nada que analizar
                // return true;
            }
        }

    }

    public boolean compararRegistros(String A, String B) {
        if (A == B) { // si el registro destino de A es igual a un registro de B retorna false
            return false;
        }
        return true;
    }
}
