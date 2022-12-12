import java.util.ArrayList;
import java.util.Iterator;

public class VerificadorPipeline {
    ListaEnlazadaInstrucciones listaInstrucciones;

    public VerificadorPipeline(ListaEnlazadaInstrucciones listaInstrucciones) {
        this.listaInstrucciones=listaInstrucciones;
    }

    /*
     * Devolverá falso si la verificación es correcta, si detecta un error: false
     */
    public void pipeline(boolean reparar) {
        ArrayList<String> permitido1 = new ArrayList<String>() {
            {
                add("nop");
                add("b");
                add("beq");
                add("bgt");
                add("call");
            }
        };
        ArrayList<String> permitido2 = new ArrayList<String>() {
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

        if(listaInstrucciones.instruccionInicial==null){
            System.out.println("No hay instrucciones, por tanto, no hay pipelines");
        }else {
            if (listaInstrucciones.instruccionInicial.instruccionSiguiente == null) {
                System.out.println("Sólo hay una instrucción, por tanto, no hay pipelines");
            } else { // si hay más de una instrucción
                Instruccion instruccionIt = listaInstrucciones.instruccionInicial;
                while (instruccionIt.instruccionSiguiente != null) {
                    Instruccion A = instruccionIt;
                    Instruccion B = instruccionIt.instruccionSiguiente;

                    if (A.regDestInicializado()) {
                        if (B.regDestInicializado()) {
                            if (B.regPrinInicializado()) {
                                // comparo con el principal
                                // si son iguales retorno false
                                if (compararRegistros(A.registroDestino, B.registroPrincipal)) { // si arrojó un true existe
                                    // conflicto
                                    //System.out.println("Encuentra conflicto entre instrucciones: " + i + " y " + (i + 1));
                                    System.out.println("Conflicto entre: \n" +
                                            ""+A.toString()+"\n" +
                                            ""+B.toString()+"\n");
                                    if(reparar){
                                        listaInstrucciones.añadirEntre(A, new Instruccion("nop"));
                                    }
                                }
                                if (B.regSecInicializado()) {
                                    // comparo con el segundo
                                    // si son iguales retorno false
                                    if (compararRegistros(A.registroDestino, B.registroSecundario)) { // si arrojó un true
                                        // existe conflicto
                                        System.out.println("Conflicto entre: \n" +
                                                ""+A.toString()+"\n" +
                                                ""+B.toString()+"\n");
                                        if(reparar) {
                                            listaInstrucciones.añadirEntre(A, new Instruccion("nop"));
                                        }
                                    }
                                }
                            }
                        }
                    }
                    instruccionIt=instruccionIt.instruccionSiguiente;
                }

            }
        }
    }

    public boolean compararRegistros(String A, String B) {
        // System.out.println("compara: " + A + " y " + B);
        if (A.equals(B)) { // si el registro destino de A es igual a un registro de B retorna true
            return true;
        }
        return false;
    }
}
