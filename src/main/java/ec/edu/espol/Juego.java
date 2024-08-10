package ec.edu.espol;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

public class Juego {
    protected static int turno;
    private static final Logger logger = Logger.getLogger("Juego.class.getName()");

    private Juego() {
        // Constructor privado para evitar la instanciación
    }
    
    public static void iniciarJuego() {
        
        Carta ultimaCarta;
        Baraja baraja = new Baraja();
        Jugador jugador = new Jugador("Jugador", baraja.crearMano());
        Maquina maquina = new Maquina("Máquina", baraja.crearMano());
        ultimaCarta = baraja.robarCarta();
        List<Carta> nuevaBaraja = new ArrayList<>();

        while (ultimaCarta != null && (ultimaCarta.getTipo().equals("^") || ultimaCarta.getTipo().equals("%N")  || ultimaCarta.getTipo().equals("+2") || ultimaCarta.getTipo().equals("+4") || ultimaCarta.getTipo().equals("&"))) {
            baraja.getBaraja().add(ultimaCarta);
            ultimaCarta = baraja.robarCarta();
        }
        
        turno = 1;
        Scanner sc = new Scanner(System.in);
        
        while (!jugador.getMano().isEmpty() && !maquina.getMano().isEmpty()) {

            Utilitaria.mostrarEstadoDelJuego(jugador, maquina, ultimaCarta);

            if (turno == 1) {
                if (jugador.verificarExistenciaCarta(ultimaCarta)) {
                    Carta cartaRemovida = null;
                    while (cartaRemovida == null) {
                        int indice;
                        do {
                            System.out.println("Ingrese el índice de la carta que desea jugar: ");
                            indice = sc.nextInt();
                            sc.nextLine();
                            if (indice < 0 || indice >= jugador.getMano().size()) {
                                logger.warning("Jugador ingresó un índice fuera de rango: " + indice);
                            }
                        } while (indice < 0 || indice >= jugador.getMano().size());
                        cartaRemovida = jugador.removerCartaMano(indice, ultimaCarta);
                        if (cartaRemovida == null) {
                            logger.info("Jugador intentó jugar una carta inválida en el índice: " + indice);
                        }
                    }

                    Utilitaria.efectosCartaJ(cartaRemovida, maquina, baraja);
                    nuevaBaraja.add(ultimaCarta);
                    ultimaCarta = cartaRemovida;
                    
                    if (jugador.getMano().size() == 1) {
                        logger.info("¡UNO!");
                    }
                }
                else {
                    Carta carta = baraja.robarCarta();
                    if (carta != null) {
                        jugador.getMano().add(carta);
                        logger.info("Carta añadida a la mano del jugador: " + carta);
                    }
                    turno=2;
                }
            } 
            else {
                if (maquina.verificarExistenciaCarta(ultimaCarta)) {
                    Carta cartaRemovida = maquina.jugarCarta(ultimaCarta);
                    if (cartaRemovida != null) {
                        Utilitaria.efectosCartaM(cartaRemovida, jugador, baraja);
                        nuevaBaraja.add(ultimaCarta);
                        ultimaCarta = cartaRemovida;
                        if (maquina.getMano().size() == 1) {
                            logger.info("¡UNO!");
                        }
                    }
                } 
                else {
                    Carta carta = baraja.robarCarta();
                    if (carta != null) {
                        maquina.getMano().add(carta);
                        logger.info("Carta añadida a la mano de la máquina: " + carta);
                    }
                    turno=1;
                }
            }
            if(baraja.getBaraja().size() <= 10){
                for(Carta c: nuevaBaraja){
                    baraja.agregarCarta(c);
                }
                baraja.mezclar();
                nuevaBaraja.clear();
            }
        }

        if (jugador.getMano().isEmpty()) {
            logger.info("¡Jugador ha ganado! 🥳🏆");
        } else if (maquina.getMano().isEmpty()) {
            logger.info("¡Máquina ha ganado! 🥳🏆");
        } 
        sc.close();
    }
}
