/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenge;

/**
 *
 * @author Acer
 */
public class Lane {

    protected CarId carId;
    protected PlayId playId;
    protected Position position;
    protected Integer meters;
    protected Boolean finalDesplacement;

    public Lane(CarId carId, PlayId playId, Position position, Integer meters, Boolean finalDesplacement) {
        this.carId = carId;
        this.playId = playId;
        this.position = position;
        this.meters = meters;
        this.finalDesplacement = finalDesplacement;
    }

    public Integer meters() {
        return meters;
    }

    public Position position() {
        return position;
    }

    public Integer positionActual() {
        return position.actual();
    }

    public Integer desiredPosition() {
        return position.finish();
    }

    public Boolean finalDesplecement() {
        return finalDesplacement;
    }

    public void achieveTheGoal() {
        if (positionActual() >= desiredPosition()) {
            finalDesplacement = true;
        }
    }

    public void moveCar(Position position, Integer quantity) {
        this.position = position;
        position.setActual(position.actual() + quantity);
        achieveTheGoal();
    }

}
