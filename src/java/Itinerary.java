/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nichlas
 */
@XmlRootElement
public class Itinerary {

    ArrayList<String> flightsBookingNumbers = new ArrayList<String>();
    ArrayList<String> hotelsBookingNumbers = new ArrayList<String>();

    public void addFlight(String bookingNumber) {
        flightsBookingNumbers.add(bookingNumber);
    }

    public void addHotel(String bookingNumber) {
        hotelsBookingNumbers.add(bookingNumber);
    }
}
