package ec.edu.espol;
import java.util.ArrayList;
import java.util.Collections;

public class Baraja {
    private ArrayList<Carta> baraja;

    public Baraja(){
        baraja= new ArrayList<>();
        crearBaraja();
        Collections.shuffle(baraja);
    }

    public ArrayList<Carta> getBaraja(){
        return baraja;
    }

    public void crearBaraja(){
        String[] colores={"R", "A", "V", "Z"};
        String[] tipos={"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "^", "&", "+2", "+4","^", "&", "+2", "+4"};

        for (String color:colores){
            for(String tipo:tipos){
                baraja.add(new Carta(tipo, color));
            }
        }

        String[] comodinesN={"+4", "%","+2"};
        for (String tipo:comodinesN){
            baraja.add(new Carta(tipo,"N"));
            baraja.add(new Carta(tipo,"N"));
        }
    }
    
    public Carta robarCarta(){
        if(baraja.size()==0){
            return null;
        }
        return baraja.remove(0);
    }

    public ArrayList<Carta> crearMano(){
        ArrayList<Carta> mano= new ArrayList<>();
        for (int i=0; i<7; i++){
            mano.add(baraja.remove(i));
        }
        return mano;
    }
}
