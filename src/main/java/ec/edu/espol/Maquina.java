package ec.edu.espol;

import java.security.SecureRandom;
import java.util.List;

public class Maquina extends Jugador {
    // Usando Random ya que no es una operación sensible a la seguridad
    private static final SecureRandom rand = new SecureRandom();
    public Maquina(String nombre, List<Carta> mano) {
        super("Máquina", mano); 
    }

    @Override
    public Carta removerCartaMano(int i, Carta c) {

        Carta cartaSeleccionada = getMano().get(i);
        if (cartaSeleccionada.getColor().equals("N")) {
            String[] colores = {"R", "A", "V", "Z"};
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