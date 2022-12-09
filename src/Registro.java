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
    
    public String mostrarRegistro(){
        return "[id: "+id+" ; "+"valor: "+valor+"]";
    }
}