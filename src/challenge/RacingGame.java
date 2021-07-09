/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenge;

import com.csvreader.CsvWriter;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

/**
 *
 * @author Acer
 */
public class RacingGame {

    protected Map<PlayerId, Player> players = new HashMap<>();
    protected Track track;
    protected Boolean playing;
    protected Podium podium1 = new Podium();
    protected ArrayList<Track> tracks = new ArrayList<>();
    protected ArrayList<Car> carsInPlay = new ArrayList<>();
    protected ArrayList<WinsCSV> ganadores = new ArrayList<>();
    protected ArrayList<Lane> lanesInPlay = new ArrayList<>();
    private final Car car = new Car();
    private Boolean firstGame = true;

    public RacingGame() {
    }

    // Crear jugador y la lista de jugadores con sus respectivos id
    public void creatPlayer(PlayerId playerId, Name nombre, Color colour) {
        Player player = new Player(nombre, colour, 0);
        players.put(playerId, player);
        creatDriver(nombre);
    }

    // Elegir si se desea que el jugador sea un conductor y crear el conductor y asignarle un carro.
    public void creatDriver(Name name) {
        UUID id;
        Scanner in = new Scanner(System.in);
        System.out.println("Desea que el jugador con nombre: " + name.getName() + " sea conductor ? " + "Y/N");
        while (!in.hasNext("[yYnN]")) {
            System.out.println("Solo se reciben como respuesta Y/N ó y/n");
            in.next();
        }
        String consultDrivers = in.next();
        if (consultDrivers.equals("Y") || consultDrivers.equals("y")) {
            Driver driver = new Driver(name.getName());
            id = UUID.randomUUID();
            CarId carId = new CarId(id);
            car.addDriver(carId, driver);
        }
    }

    /* 
        Eligir pistas que se crean según cantidad de carros (pueden existir tantos carros como pistas)
        y dar kilometros  aleatorios a cada pista con limites de kilometros 100 y cada pista tiene la misma
        cantidad de carriles que carros existentes para que todos los conductores puedan participar de la carrera.        
     */
    public void creatTrack() {
        int kmRandom;
        int numberLanes = car.numbersCars();
        for (int i = 0; i < car.numbersCars(); i++) {
            kmRandom = (int) (Math.random() * 100 + 1);
            Track track = new Track(kmRandom, numberLanes);
            tracks.add(track);
        }
    }

    public void addFirstPlace1(PlayerId playerId) {
        podium1.addFirstPlace(players.get(playerId));
        System.out.println("**********" + players.get(playerId).name().getName() + ": Primer Lugar" + "***********");

    }

    public void addSecondPlace2(PlayerId playerId) {
        podium1.addSecondPlace(players.get(playerId));
        System.out.println("**********" + players.get(playerId).name().getName() + ": Segundo Lugar" + "***********");
    }

    public void addThirdPlace3(PlayerId playerId) {
        podium1.addThirdPlace(players.get(playerId));
        System.out.println("**********" + players.get(playerId).name().getName() + ": Tercer Lugar" + "***********");

    }

    public void startGame() {
        //Identificado para iniciar el juego
        UUID id;
        id = UUID.randomUUID();
        PlayId playId = new PlayId(id);

        //Elegir pista en cuál jugar
        Scanner in = new Scanner(System.in);
        System.out.println("Para iniciar el juego, elige la  pista (numero) en la que deseas jugar:  ");
        System.out.println("Pistas: ");
        int counter = 1;
        for (Track t : tracks) {
            System.out.println(counter + "." + " Kilometros: " + t.km() + " Número de carriles:  " + t.laneNumers());
            counter++;
        }
        while (!in.hasNextInt()) {
            in.next();
        }
        int pistaElegida = in.nextInt();

        // Crear lista de carros en juego
        car.cars().forEach((key, value) -> {
            Car carsPlay = new Car(value, 0, Color.yellow, playId);
            carsInPlay.add(carsPlay);

            //añadir carros a los Carriles para jugar
            int kmToMeters = tracks.get(pistaElegida - 1).km() * 1000;
            Position position = new Position(0, kmToMeters);
            Lane lanes = new Lane(key, playId, position, kmToMeters, false);
            lanesInPlay.add(lanes);
        });

        //Iniciar JUEGO
        playing = true;
        Driver driver = new Driver();
        System.out.println("----------Inicia la carrera--------");

        //Mientras no hayan 3 ganadores el juego continua
        while (playing) {
            int cont = 0;
            System.out.println("--------Avance----- " + "--------- Meta: " + lanesInPlay.get(cont).meters() + " metros");
            for (Car cars : carsInPlay) {

                //Si el carro no ha ganado sigue jugando
                if (!wonCar(cars.driver().name())) {
                    int move = driver.trowDice() * 100;
                    cars.setDistance(cars.distance() + move);
                    lanesInPlay.get(cont).moveCar(lanesInPlay.get(cont).position(), move);
                    System.out.println(cars.driver().name() + ":" + " mueve: " + move + " Nueva posición: " + car.distance());

                    //Si el jugador llego a la final, asignarle la posición y el podio
                    if (lanesInPlay.get(cont).finalDesplecement()) {
                        if (podium1.firstPlace() == null) {
                            addFirstPlace1(playerId(cars.driver().name()));
                        } else if (podium1.secondPlace() == null) {
                            addSecondPlace2(playerId(cars.driver().name()));
                        } else if (podium1.thirdPlace() == null) {
                            addThirdPlace3(playerId(cars.driver().name()));
                        }
                    }
                }
                cont++;
            }
            if (podium1.isFull()) {
                break;
            }
        }

        showPodium();
        guardarRegistroCSV();
        repeatGame();
    }

    public Map<PlayerId, Player> players() {

        return players;

    }

    public Boolean playing() {

        return playing;

    }

    // Retorna el id del jugador dando el nombre del jugador
    public PlayerId playerId(String name) {
        PlayerId lookId = null;
        for (PlayerId keys : players.keySet()) {
            if (players.get(keys).name().getName().equals(name)) {
                lookId = keys;
            }
        }
        return lookId;
    }

    public void guardarRegistroCSV() {
        int id = 1;
        int contador = 0;
        //PersistenceController controller = new PersistenceController();
        for (Car cars : carsInPlay) {
            int vecesPrimero = 0;
            int vecesSegundo = 0;
            int vecesTercero = 0;
            String nombreCondParticipantes = cars.driver().name();
            if (!firstGame) {
                vecesPrimero = ganadores.get(contador).getTimesFirst();
                vecesSegundo = ganadores.get(contador).getTimesSecond();
                vecesTercero = ganadores.get(contador).getTimesThird();
            }
            if (podium1.firstPlace().name().getName().equals(nombreCondParticipantes)) {
                vecesPrimero += 1;

            } else if (podium1.secondPlace().name().getName().equals(nombreCondParticipantes)) {
                vecesSegundo += 1;

            } else if (podium1.thirdPlace().name().getName().equals(nombreCondParticipantes)) {
                vecesTercero += 1;
            }

            if (firstGame) {
                //WinsCSV conductoresG = new WinsCSV(id, nombreCondParticipantes, vecesPrimero, vecesSegundo, vecesTercero);
                // ganadores.add(conductoresG);
                for (WinsCSV g : ganadores) {
                    WinsCSV conductoresG = new WinsCSV(id, nombreCondParticipantes, vecesPrimero, vecesSegundo, vecesTercero);

                    if (firstGame) {
                        CsvWriter csvWriter = new CsvWriter("Fichero.csv");
                        String[] datos = g.getArray();
                        //csvWriter.writeRecord(ganadores);

                    }
                }

                id++;

            } else {
                ganadores.get(contador).setTimesFirst(vecesPrimero);
                ganadores.get(contador).setTimesSecond(vecesSegundo);
                ganadores.get(contador).setTimesThird(vecesTercero);
                contador++;
            }

            for (WinsCSV g : ganadores) {
                if (firstGame) {
                    CsvWriter csvWriter = new CsvWriter("Fichero.csv");
                    String[] datos = g.getArray();
                  //  csvWriter.writeRecord(datos);

                }
            }
            firstGame = false;
        }
    }
    //Retorna True  si el carro en la carrera ya ganó

    public Boolean wonCar(String name) {
        boolean won = false;
        if (podium1.thirdPlace() == players.get(playerId(name))
                || podium1.firstPlace() == players.get(playerId(name))
                || podium1.secondPlace() == players.get(playerId(name))) {
            won = true;
        }
        return won;
    }

    // Método para saber si repetir el juego y limpiar listas de juego anterior
    public void repeatGame() {
        Scanner in = new Scanner(System.in);
        System.out.println("Desea jugar otra carrera?  Y/N");
        while (!in.hasNext("[yYnN]")) {
            System.out.println("Solo se reciben como respuesta Y/N ó y/n");
            in.next();
        }
        String playAnother = in.next();
        if (playAnother.equals("Y") || playAnother.equals("y")) {
            carsInPlay.clear();
            lanesInPlay.clear();
            Podium newPodium = new Podium();
            podium1 = newPodium;
            startGame();

        }

    }

    //Método para mostrar los conductores que quedaron en el podio 
    public void showPodium() {
        System.out.println("--------Podio--------");
        System.out.println("Primer Lugar:  " + podium1.firstPlace().name().getName());
        System.out.println("Segundo Lugar:  " + podium1.secondPlace().name().getName());
        System.out.println("Tercer Lugar:  " + podium1.thirdPlace().name().getName());
        System.out.println("----------------------");

    }

}
