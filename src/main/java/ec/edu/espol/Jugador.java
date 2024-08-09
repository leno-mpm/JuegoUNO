package ec.edu.espol;

import java.util.List;
import java.util.Scanner;

public class Jugador {
    protected List<Carta> mano;
    private String nombre;

    public Jugador(String nombre, List<Carta> mano) {
        this.nombre = nombre;
        this.mano = mano;
    }

    public List<Carta> getMano() {
        return mano;
    }

    public String getNombre() {
        return nombre;
    }

    public Carta removerCartaMano(int i, Carta c) {
        Carta cartaSeleccionada = getMano().get(i);
        if (cartaSeleccionada.getColor().equals("N")) {
            Scanner sc = new Scanner(System.in);
            String color = "";
            while(!color.equalsIgnoreCase("A")&& !color.equalsIgnoreCase("Z") && !color.equalsIgnoreCase("V") &&  !color.equalsIgnoreCase("R")){
                System.out.println("¿A que color desea cambiar la carta? ==> ");
                color = sc.nextLine();
                if(!color.equalsIgnoreCase("A")&&  !color.equalsIgnoreCase("Z") &&  !color.equalsIgnoreCase("V") &&  !color.equalsIgnoreCase("R")){
                    System.out.println("Color incorrecto, debes! elegir entre (A-Z-V-R)");
                }
            }
            cartaSeleccionada.setColor(color.toUpperCase());
            sc.close();
            return mano.remove(i);
        } else if (cartaSeleccionada.getColor().equals(c.getColor()) || cartaSeleccionada.getTipo().equals(c.getTipo())) {
            return mano.remove(i);
        }
        return null;
    }

    public boolean verificarExistenciaCarta(Carta c) {
        for (Carta carta : mano) {
            if (carta.getColor().equals(c.getColor()) || carta.getTipo().equals(c.getTipo()) || carta.getColor().equals("N")) {
                return true;
            }
        }
        return false;
    }

    public void agregarCarta(Carta carta) {
        this.mano.add(carta);
    }

    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder();
        for (Carta c : mano) {
            texto.append(c + " ");
        }
        return "Nombre: " + nombre + "\n Mano: [" + texto.toString() + "]";
    }
}