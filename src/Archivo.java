import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Archivo {
    private File archivo = null;
    private File archivoDebug = null;
    private FileReader fr = null;
    private FileWriter fw = null;
    private BufferedReader br = null;
    private String nombreArchivo;
    private ArrayList<Instruccion> instrucciones;
    private boolean formatoCorrecto = true;

    public Archivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        instrucciones = new ArrayList<>();
    }

    /**
     * Metodo que permite leer el archivo creado donde cada linea formateada por
     * espacios es agregada al contenido.
     */
    public void leerArchivo() {
        try {
            archivo = new File("./ArchivosDePrueba/" + nombreArchivo);
            archivoDebug = new File("./DEBUG_ArchivosDePrueba/DEBUG_" + nombreArchivo);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            fw = new FileWriter(archivoDebug);

            String instruccion; // cada linea contiene una instrucción
            while ((instruccion = br.readLine()) != null) {
                boolean instruccionValida = verificarLineaTexto(instruccion);
                fw.write(instruccion + " <-- Formato valido: " + Boolean.toString(instruccionValida) + "\n");
                // si la linea de instrucción es válida
                if (!instruccionValida) {
                    formatoCorrecto = false;
                }
            }
            fw.close();
        } catch (Exception e) {
            e.printStackTrace(); // mostrar el error
        } finally { // cerramos siempre el archivo
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método para verificar si la linea de texto es válida
     *
     * @param lineaTexto linea de texto ingresada
     * @return retorna True si es permitda, False si no es permitida (formato incorrecto)
     */
    public boolean verificarLineaTexto(String lineaTexto) {
        String regNum = "(rs[0-9]|rs10|rs11|rs12|rs13|r14|rs15)";
        String regImm = "((\\d)+)";
        String op1 = "^(add|sub|mul|div|mod|and|or|lsl|lsr|asr)\\s" + regNum + "," + regNum + "," + regNum + "$";
        String op2 = "^(add|sub|mul|div|mod|and|or|lsl|lsr|asr)\\s" + regNum + "," + regNum + "," + regImm + "$";
        String op3 = "^(cmp|not|mov)\\s" + regNum + "," + regImm + "$";
        String op3_2 ="^(cmp|not|mov)\\s" + regNum + "," + regNum + "$";
        String op4 = "^(nop|ret)$";
        // splitear linea de texto
        Pattern pattern = Pattern.compile("(\\s|,)", Pattern.CANON_EQ);
        String[] lineaSpliteada = pattern.split(lineaTexto);
        //System.out.println("DEBUG - SALIDA SPLIT:"+Arrays.toString(lineaSpliteada));

        // TODO Llegué hasta el nop, del ld hacia abajo esta incompleto
        pattern = Pattern.compile(op1, Pattern.CANON_EQ);
        Matcher matcher = pattern.matcher(lineaTexto);
        boolean match = matcher.find();
        if (match) {
            instrucciones.add(crearInstruccion("op1", lineaSpliteada));
        } else {
            pattern = Pattern.compile(op2, Pattern.CANON_EQ);
            matcher = pattern.matcher(lineaTexto);
            match = matcher.find();
            if (match) {
                instrucciones.add(crearInstruccion("op2", lineaSpliteada));
            } else {
                pattern = Pattern.compile(op3, Pattern.CANON_EQ);
                matcher = pattern.matcher(lineaTexto);
                match = matcher.find();
                if (match) {
                    instrucciones.add(crearInstruccion("op3", lineaSpliteada));
                } else {
                    pattern = Pattern.compile(op3_2, Pattern.CANON_EQ);
                    matcher = pattern.matcher(lineaTexto);
                    match = matcher.find();
                    if(match) {
                        instrucciones.add(crearInstruccion("op3_2", lineaSpliteada));
                    }else{
                        pattern = Pattern.compile(op4, Pattern.CANON_EQ);
                        matcher = pattern.matcher(lineaTexto);
                        match = matcher.find();
                        if (match) {
                            instrucciones.add(crearInstruccion("op4", lineaSpliteada));
                        }
                    }
                }
            }
        }
        return match;
    }

    public Instruccion crearInstruccion(String op, String[] lineaSpliteada) {
        String regExpDig = "((\\d)+)";
        if (op == "op4") {
            return new Instruccion(lineaSpliteada[0]);
        }
        if (op == "op3") {
            return new Instruccion(lineaSpliteada[0], lineaSpliteada[1], Integer.parseInt(lineaSpliteada[2]));
        }

        if(op== "op3_2"){
            return new Instruccion(lineaSpliteada[0], lineaSpliteada[1], lineaSpliteada[2]);
        }

        if (op == "op1") {
            return new Instruccion(lineaSpliteada[0], lineaSpliteada[1],lineaSpliteada[2],lineaSpliteada[3]);
        } else if (op == "op2") {
            return new Instruccion(lineaSpliteada[0], lineaSpliteada[1],lineaSpliteada[2],Integer.parseInt(lineaSpliteada[3]));
        }
        return null;
    }

    public boolean getFormatoCorrecto(){
        return formatoCorrecto;
    }

    public ArrayList<Instruccion> getInstrucciones() {
        return instrucciones;
    }
}