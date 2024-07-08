package ec.edu.espol;

public class Carta {
    private String color;
    private String tipo;

    public Carta(String tipo, String color){
        this.tipo=tipo;
        this.color=color;
    }

    public String getColor(){
        return color;
    }

    public String getTipo(){
        return tipo;
    }
    
    public void setColor(String color){
        this.color=color;
    }
    
    @Override
    public String toString(){
        return tipo+color;
    }

}
