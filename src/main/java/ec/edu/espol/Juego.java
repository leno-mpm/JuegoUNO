package ec.edu.espol;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Juego {
    private static Carta ultimaCarta;
    protected static int turno;
    public static void iniciarJuego() {
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
        while (jugador.getMano().size() != 0 && maquina.getMano().size() != 0) {

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
                                System.out.println("Índice fuera de rango. Intente nuevamente.");
                            }
                        } while (indice < 0 || indice >= jugador.getMano().size());
                        cartaRemovida = jugador.removerCartaMano(indice, ultimaCarta);
                        if (cartaRemovida == null) {
                            System.out.println("No se puede tirar esta carta, intente con otra.");
                        }
                    }

                    Utilitaria.efectosCartaJ(cartaRemovida, maquina, baraja);
                    nuevaBaraja.add(ultimaCarta);
                    ultimaCarta = cartaRemovida;
                    
                    if (jugador.getMano().size() == 1) {
                        System.out.println("¡UNOOOOOOOOOOOOO!");
                    }
                }
                else {
                    Carta carta = baraja.robarCarta();
                    if (carta != null) {
                        jugador.getMano().add(carta);
                        System.out.println("Carta añadida a tu mano: " + carta);
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
                            System.out.println("Máquina: ¡UNOOOOOOO!");
                        }
                    }
                } 
                else {
                    Carta carta = baraja.robarCarta();
                    if (carta != null) {
                        maquina.getMano().add(carta);
                        System.out.println("Carta añadida a la mano de la máquina: " + carta);
                    }
                    turno=1;
                }
            }
            if(baraja.getBaraja().size() <= 10){
                System.out.println("SIN CARTASSSSSSSSSSSSSSSSSSSSSSSS");
                for(Carta c: nuevaBaraja){
                    baraja.agregarCarta(c);
                }
                baraja.mezclar();
                nuevaBaraja.clear();
            }
        }

        if (jugador.getMano().isEmpty()) {
            System.out.println("¡Jugador ha ganado! 🥳🏆");
        } else if (maquina.getMano().isEmpty()) {
            System.out.println("¡Máquina ha ganado! 🥳🏆");
        } 
        sc.close();
    }
}
