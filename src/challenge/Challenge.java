package challenge;

import java.awt.Color;
import java.util.Scanner;
import java.util.UUID;

public class Challenge {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        UUID id;
        int numberPlayers;
        String playerName;

        RacingGame newPlay = new RacingGame();

        //Datos jugadores para iniciar el juego 
        System.out.println("Iniciando un nuevo juego ...");
        System.out.println("Para poder empezar a jugar. Tiene que registrarse con mas de 2 jugadores");
        do {
            //Consulta  cuántos jugadores se quieren crear y se crean dichos jugadores con nombres "jugador"+n
            System.out.print("Digite cunatos jugadores van a iniciar la partidad >> ");
            while (!in.hasNextInt()) {
                in.next();
            }
            numberPlayers = in.nextInt();

            if (numberPlayers <= 2) {
                System.out.println("Recuerde que tiene que registrar mas de dos jugadores");
            }

        } while (numberPlayers <= 2);

        for (int i = 0; i < numberPlayers; i++) {
            id = UUID.randomUUID();
            PlayerId playerId = new PlayerId(id);
            System.out.print("Digite el nombre del jugador >> " + (i + 1));
            String nameP = in.next();
            playerName = nameP + (i + 1);
            Name name = new Name(playerName);
            newPlay.creatPlayer(playerId, name, Color.yellow);
        }

        // Crear pistas
        newPlay.creatTrack();

        // Iniciar el Juego
        newPlay.startGame();

    }
}
