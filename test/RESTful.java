/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.text.SimpleDateFormat;
import javax.security.jacc.WebResourcePermission;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author Nichlas
 */
public class RESTful {

    String BASE_URI = "http://localhost:8080/RESTful_V2/webresources/";

    public RESTful() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getHotels() {
        String path = "TravelGood/hotels";
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(BASE_URI).path(path);

        target = target.queryParam("city", "Copenhagen").queryParam("arrival", "15/12/2016").queryParam("departure", "27/12/2016");
        String result = target.request().get(String.class);
        String expResult = "[{\"address\":\"Copenhagen\",\"creditcard\":true,\"hotelReservationService\":\"AndersAnd\",\"name\":\"ChipAndChap\",\"price\":18000.0}]";
        assertEquals(expResult, result);

        WebTarget target2 = client.target(BASE_URI).path(path);
        result = target2.request().get(String.class);
        expResult = "[]";
        assertEquals(expResult, result);
    }

    @Test
    public void createItinerary() {
        String path = "TravelGood/itineraries";
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(BASE_URI).path(path);

        int status_code_result = 0;
        int status_code_expected_result = Response.Status.CREATED.getStatusCode();

        try {
            Itinerary i = new Itinerary();
            i.flightsBookingNumbers.add("123123");
            i.hotelsBookingNumbers.add("68678768");
            ObjectMapper obj = new ObjectMapper();
            String json = obj.writeValueAsString(i);
            Response response = target.request().accept(MediaType.APPLICATION_JSON).post(Entity.entity(json, MediaType.APPLICATION_JSON), Response.class);
            status_code_result = response.getStatus();
        } catch (Exception e) {
            System.out.println("We are doomed.. GL");
        }

        assertEquals(status_code_expected_result, status_code_result);

    }

    @Test
    public void getItinerary() {
        String path = "TravelGood/itineraries/0";
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(BASE_URI).path(path);

        int status_code_result = 0;
        int status_code_expected_result = Response.Status.OK.getStatusCode();

        try {
            Response response = target.request().accept(MediaType.APPLICATION_JSON).get();
            status_code_result = response.getStatus();
        } catch (Exception e) {
            System.out.println("We are doomed.. GL");
        }
        assertEquals(status_code_expected_result, status_code_result);
    }

    @Test
    public void addHotel() {
        String path = "TravelGood/itineraries/0/hotels";
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(BASE_URI).path(path);

        int status_code_result = 0;
        int status_code_expected_result = Response.Status.CREATED.getStatusCode();
        int bookingNumber = 9999;

        try {
            ObjectMapper obj = new ObjectMapper();
            String json = obj.writeValueAsString(bookingNumber);
            Response response = target.request().accept(MediaType.APPLICATION_JSON).post(Entity.entity(bookingNumber, MediaType.TEXT_PLAIN), Response.class);
            status_code_result = response.getStatus();
        } catch (Exception e) {
            System.out.println("We are doomed.. GL");
        }
        assertEquals(status_code_expected_result, status_code_result);
    }

    @Test
    public void cancelHotel() {
        String path = "TravelGood/itineraries/0/hotels/9999";
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(BASE_URI).path(path);
        client.target(BASE_URI).path("TravelGood/itineraries/0/hotels").request().accept(MediaType.APPLICATION_JSON).post(Entity.entity(9999, MediaType.TEXT_PLAIN), Response.class);

        int status_code_result = 0;
        int status_code_expected_result = Response.Status.ACCEPTED.getStatusCode();
        try {
            Response response = target.request().delete();
            status_code_result = response.getStatus();
        } catch (Exception e) {
            System.out.println("We are doomed.. GL");
        }
        assertEquals(status_code_expected_result, status_code_result);
    }

    @Test
    public void getFlights() {
        String path = "TravelGood/flights";
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(BASE_URI).path(path);
        target = target.queryParam("from", "Copenhagen").queryParam("to", "Amsterdam").queryParam("date", "10/11/2016");
        String result = target.request().get(String.class);
        String expResult = "[{\"bookingNumber\":\"CANOV215\",\"dateOfTravel\":\"2016-11-10T00:00:00+01:00\",\"FO\":{\"carrier\":\"B.e.st. Carriers\",\"destinationAirport\":\"Amsterdam\",\"endDate\":\"2016-11-10T00:00:00+01:00\",\"endTime\":\"10:35\",\"startAirport\":\"Copenhagen\",\"startDate\":\"2016-11-10T00:00:00+01:00\",\"startTime\":\"08:15\"},\"nameOfAirline\":\"SAS\",\"price\":215},{\"bookingNumber\":\"CANOV380\",\"dateOfTravel\":\"2016-11-10T00:00:00+01:00\",\"FO\":{\"carrier\":\"Wind Carriers\",\"destinationAirport\":\"Amsterdam\",\"endDate\":\"2016-11-10T00:00:00+01:00\",\"endTime\":\"14:05\",\"startAirport\":\"Copenhagen\",\"startDate\":\"2016-11-10T00:00:00+01:00\",\"startTime\":\"11:45\"},\"nameOfAirline\":\"Norwegian\",\"price\":380}]";
        assertEquals(expResult, result);

        WebTarget target2 = client.target(BASE_URI).path(path);
        result = target2.request().get(String.class);
        expResult = "[]";
        assertEquals(expResult, result);
    }

    @Test
    public void addFlight() {
        String path = "TravelGood/itineraries/0/flights";
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(BASE_URI).path(path);

        int status_code_result = 0;
        int status_code_expected_result = Response.Status.CREATED.getStatusCode();
        int bookingNumber = 9999;

        try {
            ObjectMapper obj = new ObjectMapper();
            String json = obj.writeValueAsString(bookingNumber);
            Response response = target.request().accept(MediaType.APPLICATION_JSON).post(Entity.entity(bookingNumber, MediaType.TEXT_PLAIN), Response.class);
            status_code_result = response.getStatus();
        } catch (Exception e) {
            System.out.println("We are doomed.. GL");
        }
        assertEquals(status_code_expected_result, status_code_result);
    }

    @Test
    public void removeFlight() {
        String path = "TravelGood/itineraries/0/flights/9999";
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(BASE_URI).path(path);

        int status_code_result = 0;
        int status_code_expected_result = Response.Status.ACCEPTED.getStatusCode();
        int bookingNumber = 9999;

        try {
            ObjectMapper obj = new ObjectMapper();
            String json = obj.writeValueAsString(bookingNumber);
            Response response = target.request().delete();
            status_code_result = response.getStatus();
        } catch (Exception e) {
            System.out.println("We are doomed.. GL");
        }
        assertEquals(status_code_expected_result, status_code_result);
    }

    @Test
    public void book() {
        String path = "TravelGood/itineraries/0/book";
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(BASE_URI).path(path);

        int status_code_result = 0;
        int status_code_expected_result = Response.Status.OK.getStatusCode();
        int bookingNumber = 9999;

        try {
            ObjectMapper obj = new ObjectMapper();
            String json = obj.writeValueAsString(bookingNumber);
            Response response = target.request().accept(MediaType.APPLICATION_JSON).post(Entity.entity(bookingNumber, MediaType.TEXT_PLAIN), Response.class);
            status_code_result = response.getStatus();
        } catch (Exception e) {
            System.out.println("We are doomed.. GL");
        }
        assertEquals(status_code_expected_result, status_code_result);
    }
}
