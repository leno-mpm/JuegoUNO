package ec.edu.espol;
import java.util.Scanner;
public class Juego {
    private static Carta ultimaCarta;
    
    public static void iniciarJuego() {
        Baraja baraja = new Baraja();
        Jugador jugador = new Jugador("Jugador", baraja.crearMano());
        Maquina maquina = new Maquina("Máquina", baraja.crearMano());
        ultimaCarta = baraja.robarCarta();

        while (ultimaCarta != null && (ultimaCarta.getTipo().equals("^") || ultimaCarta.getTipo().equals("%N")  || ultimaCarta.getTipo().equals("+2") || ultimaCarta.getTipo().equals("+4") || ultimaCarta.getTipo().equals("&"))) {
            baraja.getBaraja().add(ultimaCarta);
            ultimaCarta = baraja.robarCarta();
        }

        if (ultimaCarta == null) {
            System.out.println("No hay suficientes cartas para iniciar el juego.");
            return;
        }

        int turno = 1;
        boolean enJuego = true;
        Scanner sc = new Scanner(System.in);

        while (enJuego) {
            if (jugador.getMano().size() == 0) {
                System.out.println("¡Jugador ha ganado! 🥳🏆");
                enJuego = false;
            } else if (maquina.getMano().size() == 0) {
                System.out.println("¡Máquina ha ganado! 🥳🏆");
                enJuego = false;
            }

            if (!enJuego) {
                return;
            }

            System.out.println();
            System.out.println("========================================");
            System.out.println(jugador);
            System.out.println(maquina);
            System.out.println("Carta en juego: " + ultimaCarta);
            System.out.println("========================================");
            System.out.println();

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

                    // Efectos de la carta
                    if (cartaRemovida.getTipo().equals("+2")) {
                        Carta carta1 = baraja.robarCarta();
                        Carta carta2 = baraja.robarCarta();
                        if (carta1 == null || carta2 == null) {
                            System.out.println("No hay más cartas en la baraja. El juego termina en empate.");
                            enJuego = false;
                            return;
                        }
                        maquina.getMano().add(carta1);
                        maquina.getMano().add(carta2);
                        System.out.println("Se agregaron dos cartas a la mano de la Máquina.");
                        turno = 2;
                    } else if (cartaRemovida.getTipo().equals("+4")) {
                        for (int i = 0; i < 4; i++) {
                            Carta carta = baraja.robarCarta();
                            if (carta == null) {
                                System.out.println("No hay más cartas en la baraja. El juego termina en empate.");
                                enJuego = false;
                                return;
                            }
                            maquina.getMano().add(carta);
                        }
                        System.out.println("Se agregaron cuatro cartas a la mano de la Máquina.");
                        turno = 2;
                    } else if (cartaRemovida.getTipo().equals("^") || cartaRemovida.getTipo().equals("&")) {
                        // Turno permanece con el jugador actual
                    } else {
                        turno = 2;
                    }
                    ultimaCarta = cartaRemovida;
                    System.out.println("Carta en juego: " + ultimaCarta);
                    if (jugador.getMano().size() == 1) {
                        System.out.println("Jugador: ¡UNOOOOOOOOOOOOO!");
                    }
                } else {
                    Carta carta = baraja.robarCarta();
                    if (carta == null) {
                        System.out.println("No hay más cartas en la baraja. El juego termina en empate.");
                        enJuego = false;
                        return;
                    }
                    jugador.getMano().add(carta);
                    System.out.println("Carta añadida a tu mano: " + carta);
                    turno = 2;
                }
            } 
            else {
                if (maquina.verificarExistenciaCarta(ultimaCarta)) {
                    Carta cartaRemovida = maquina.jugarCarta(ultimaCarta);
                    if (cartaRemovida != null) {
                        if (cartaRemovida.getTipo().equals("+2")) {
                            Carta carta1 = baraja.robarCarta();
                            Carta carta2 = baraja.robarCarta();
                            if (carta1 == null || carta2 == null) {
                                System.out.println("No hay más cartas en la baraja. El juego termina en empate.");
                                enJuego = false;
                                return;
                            }
                            jugador.getMano().add(carta1);
                            jugador.getMano().add(carta2);
                            System.out.println("Se agregaron dos cartas a tu mano.");
                            turno = 1;
                        } else if (cartaRemovida.getTipo().equals("+4")) {
                            for (int i = 0; i < 4; i++) {
                                Carta carta = baraja.robarCarta();
                                if (carta == null) {
                                    System.out.println("No hay más cartas en la baraja. El juego termina en empate.");
                                    enJuego = false;
                                    return;
                                }
                                jugador.getMano().add(carta);
                            }
                            System.out.println("Se agregaron cuatro cartas a tu mano.");
                            turno = 1;
                        } else if (cartaRemovida.getTipo().equals("^") || cartaRemovida.getTipo().equals("&")) {
                            // Turno permanece con la máquina
                        } else {
                            turno = 1;
                        }
                        ultimaCarta = cartaRemovida;
                        System.out.println("La máquina juega: " + ultimaCarta);
                        if (maquina.getMano().size() == 1) {
                            System.out.println("Máquina: ¡UNOOOOOOO!");
                        }
                    } else {
                        Carta carta = baraja.robarCarta();
                        if (carta == null) {
                            System.out.println("No hay más cartas en la baraja. El juego termina en empate.");
                            enJuego = false;
                            return;
                        }
                        maquina.getMano().add(carta);
                        System.out.println("Carta añadida a la mano de la máquina: " + carta);
                        turno = 1;
                    }
                } else {
                    Carta carta = baraja.robarCarta();
                    if (carta == null) {
                        System.out.println("No hay más cartas en la baraja. El juego termina en empate.");
                        enJuego = false;
                        return;
                    }
                    maquina.getMano().add(carta);
                    System.out.println("Carta añadida a la mano de la máquina: " + carta);
                    turno = 1;
                }
            }
        }
        sc.close();
    }
}
