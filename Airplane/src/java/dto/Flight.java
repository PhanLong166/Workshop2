/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;
/**
 *
 * @author Admin
 */
public class Flight {
    private String flightNumber;
    private String destination;
    private String departureTime;
    private int delayed;

    public Flight() {
    }

    public Flight(String flightNumber, String destination, String departureTime, int delayed) {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.departureTime = departureTime;
        this.delayed = delayed;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public int isDelayed() {
        return delayed;
    }

    public void setDelayed(int delayed) {
        this.delayed = delayed;
    }
    
    
}
