package ec.edu.espol;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Baraja {
    private List<Carta> cBaraja;

    public Baraja(){
        cBaraja= new ArrayList<>();
        crearBaraja();
        mezclar();
    }

    public List<Carta> getBaraja(){
        return cBaraja;
    }

    public void mezclar(){
        Collections.shuffle(cBaraja);
    }

    public void agregarCarta(Carta c){
        cBaraja.add(c);
    }

    public void crearBaraja(){
        String[] colores={"R", "A", "V", "Z"};
        String[] tipos={"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "^", "&", "+2", "+4","^", "&", "+2", "+4"};

        for (String color:colores){
            for(String tipo:tipos){
                cBaraja.add(new Carta(tipo, color));
            }
        }

        String[] comodinesN={"+4", "%","+2"};
        for (String tipo:comodinesN){
            cBaraja.add(new Carta(tipo,"N"));
            cBaraja.add(new Carta(tipo,"N"));
        }
    }
    
    public Carta robarCarta(){
        if(cBaraja.isEmpty()){//DEMAS
            return null;
        }
        return cBaraja.remove(0);
    }

    public List<Carta> crearMano(){
        List<Carta> mano= new ArrayList<>();
        for (int i=0; i<7; i++){
            mano.add(cBaraja.remove(0));
        }
        return mano;
    }
}
