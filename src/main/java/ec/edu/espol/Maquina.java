package ec.edu.espol;

import java.util.List;
import java.util.Random;

public class Maquina extends Jugador {
    public Maquina(String nombre, List<Carta> mano) {
        super("Máquina", mano);
    }

    @Override
    public Carta removerCartaMano(int i, Carta c) {

        Carta cartaSeleccionada = getMano().get(i);
        if (cartaSeleccionada.getColor().equals("N")) {
            // Máquina elige un color aleatorio
            String[] colores = {"R", "A", "V", "Z"};
            Random rand = new Random();
            String color = colores[rand.nextInt(colores.length)];
            cartaSeleccionada.setColor(color);
            return mano.remove(i);
        } else if (cartaSeleccionada.getColor().equals(c.getColor()) || cartaSeleccionada.getTipo().equals(c.getTipo())) {
            return mano.remove(i);
        }
        return null;
    }

    public Carta jugarCarta(Carta cartaActual) {
        for (int i = 0; i < getMano().size(); i++) {
            Carta carta = getMano().get(i);
            if (carta.getColor().equals(cartaActual.getColor()) || carta.getTipo().equals(cartaActual.getTipo())) {
                return removerCartaMano(i, cartaActual);
            }
        }

        // Jugar carta negra si no hay otra opción
        for (int i = 0; i < getMano().size(); i++) {
            Carta carta = getMano().get(i);
            if (carta.getColor().equals("N")) {
                return removerCartaMano(i, cartaActual);
            }
        }
        return null;
    }
}