package ec.edu.espol;

public class Utilitaria {
    private static final String LINEA_SEPARADORA = "*****************************************";
    private Utilitaria(){
        // Constructor privado para evitar la instanciación
    }
    public static void mostrarMensajeBienvenida() {
        System.out.println(LINEA_SEPARADORA);
        System.out.println("*                                       *");
        System.out.println("*            ¡Bienvenidos a             *");
        System.out.println("*                  UNO!                 *");
        System.out.println("*                                       *");
        System.out.println(LINEA_SEPARADORA);
        System.out.println("        ¡Que comience el juego!");
        System.out.println(LINEA_SEPARADORA);

    }
    
    public static void mostrarEstadoDelJuego(Jugador j, Jugador m, Carta ultimaCarta) {
        System.out.println("\n========================================");
        System.out.println(j);
        System.out.println(m);
        System.out.println("Carta en juego: " + ultimaCarta);
        System.out.println("========================================\n");
    }

    
    public static void efectosCartaJ (Carta carta, Jugador maquina, Baraja baraja){
        switch (carta.getTipo()){
            case "+2" -> robarCartas(maquina, baraja, 2);
            case "+4" -> robarCartas(maquina, baraja, 4);
            case "^","&" -> Juego.turno=1;  
            default -> Juego.turno=2;
        }
    }

    public static void efectosCartaM (Carta carta, Jugador jugador, Baraja baraja){
        switch (carta.getTipo()){
            case "+2" -> robarCartas(jugador, baraja, 2);
            case "+4" -> robarCartas(jugador, baraja, 4);
            case "^","&" -> Juego.turno=2;
            default -> Juego.turno=1;
        }
    }

    public static void robarCartas(Jugador jugador, Baraja baraja, int cantidad){
        Juego.turno= (Juego.turno==1)? 2:1;
        for (int i = 0; i < cantidad; i++) {
            Carta cartaRobada = baraja.robarCarta();
            jugador.agregarCarta(cartaRobada);
        }
        
        System.out.println("Se añadieron "+ cantidad + " cartas a mano " + jugador.getNombre());
    }
}
