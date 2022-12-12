public class ListaEnlazadaInstrucciones {
    Instruccion instruccionInicial;

    public ListaEnlazadaInstrucciones(){}

    public void añadirDelante(Instruccion instruccionNueva){
        if(instruccionInicial==null){
            instruccionInicial=instruccionNueva;
        }else {
            Instruccion instruccionIterada = instruccionInicial;
            while (true) {
                // si el nodo inicial es el único, se añade justo delante y ya
                if (instruccionIterada.instruccionSiguiente == null) {
                    instruccionIterada.instruccionSiguiente = instruccionNueva;
                    break;
                } else { // en caso contrario, debemos llegar al último para resolverlo
                    instruccionIterada = instruccionIterada.instruccionSiguiente;
                }
            }
        }
    }

    public void añadirEntre(Instruccion instruccion, Instruccion instruccionNueva){
        // antes: ... -> instruccion --> instruccion.instruccionSiguiente --> ...
        // despues: // ... -> instruccion -->  instruccionNueva-->instruccion.instruccionSiguiente --> ...
        instruccionNueva.instruccionSiguiente=instruccion.instruccionSiguiente;
        instruccion.instruccionSiguiente=instruccionNueva;
    }

    public void mostrarLista(){
        Instruccion instruccionIterada= instruccionInicial;
        while(true){
            if(instruccionIterada==null){
                break;
            }else{
                System.out.println(instruccionIterada.toString());
                instruccionIterada=instruccionIterada.instruccionSiguiente;
            }
        }
    }

    public static void main(String[] args) {
        Instruccion instruccion1=new Instruccion("add","rs1","rs2");
        Instruccion instruccion2=new Instruccion("mul","rs3","rs4");
        Instruccion instruccion3=new Instruccion("mod","rs1","rs2");

        ListaEnlazadaInstrucciones instrucciones = new ListaEnlazadaInstrucciones();
        instrucciones.añadirDelante(instruccion1);
        instrucciones.añadirDelante(instruccion2);
        instrucciones.añadirDelante(instruccion3);

        instrucciones.mostrarLista();

        System.out.println("\n ---------------- DESPUES -----");
        // ahora añadiré una instrucción fome entre medio de la instruccion 2 y 3
        Instruccion interInstruccion= new Instruccion("nop");
        instrucciones.añadirEntre(instruccion1,interInstruccion);

        instrucciones.mostrarLista();

    }
}
