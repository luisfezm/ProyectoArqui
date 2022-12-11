public class Registro {
    private String id;
    private int valor;

    public Registro(String id,int valor) {
        this.id=id;
        this.valor=valor;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getId(){
        return id;
    }

    /* 
     * Utilizamos este metodo con el fin de obtener el binario que utilizaremos
     * para algunas operaciones
     */
    public String getBinarioValor(){
        String binario= Integer.toBinaryString(valor);
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

    public void setBinarioValor(String binario){
        valor=Integer.parseInt(binario,2);
    }
    
    public String mostrarRegistro(){
        return "[id: "+id+" ; "+"valor: "+valor+"]";
    }

    public static void main(String[] args) {
        Registro r1=new Registro("rs1", 0);
        System.out.println(r1.getBinarioValor()+r1.mostrarRegistro());

        r1.setBinarioValor("101");
        System.out.println(r1.getBinarioValor()+r1.mostrarRegistro());
    }
}