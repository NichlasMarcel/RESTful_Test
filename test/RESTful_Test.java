/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.dtu.mmmngg.FlightInfoObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.niceview.HotelReservation;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType.ExpirationDate;

import java.util.List;
import javax.ws.rs.Consumes;
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
public class RESTful_Test {

    String BASE_URI = "http://localhost:8080/RESTful/webresources/TravelGood";
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(BASE_URI);

    public RESTful_Test() {
        //target = 
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

    public CreditCardInfoType creditCardLimited() {
        CreditCardInfoType creditCard = new CreditCardInfoType();
        creditCard.setName("Bech Camilla");
        creditCard.setNumber("50408822");
        ExpirationDate date = new CreditCardInfoType.ExpirationDate();
        date.setMonth(7);
        date.setYear(9);
        creditCard.setExpirationDate(date);

        return creditCard;
    }

    public CreditCardInfoType creditCardUnlimited() {
        CreditCardInfoType creditCard = new CreditCardInfoType();
        creditCard.setName("Anne Strandberg");
        creditCard.setNumber("50408816");
        ExpirationDate date = new CreditCardInfoType.ExpirationDate();
        date.setMonth(5);
        date.setYear(9);
        creditCard.setExpirationDate(date);

        return creditCard;
    }
//
//    @Test
//    public void getHotels() {
//        String path = "/hotels";
//
//        String result = target.path(path).queryParam("city", "Copenhagen")
//                .queryParam("arrival", "15/12/2016")
//                .queryParam("departure", "27/12/2016")
//                .request().get(String.class);
//
//        String expResult = "{\"list\":[{\"bookingNumber\":1,\"hotel\":{\"address\":\"Copenhagen\",\"creditcard\":true,\"hotelReservationService\":\"AndersAnd\",\"name\":\"ChipAndChap\",\"price\":18000.0},\"timePeriod\":{\"end\":\"2016-12-27T00:00:00+01:00\",\"start\":\"2016-12-15T00:00:00+01:00\"},\"reserved\":false}]}";
//        assertEquals(expResult, result);
//
//        WebTarget target2 = client.target(BASE_URI).path(path);
//        result = target2.request().get(String.class);
//        expResult = "{}";
//        assertEquals(expResult, result);
//    }
//
//    @Test
//    public void createItineraryTest() {
//        String path = "itineraries";
//
//        int status_code_result = 0;
//        int status_code_expected_result = Response.Status.CREATED.getStatusCode();
//
////        try {
////            Itinerary_2 i = new Itinerary_2();
////            i.flightsBookingNumbers.add("123123");
////            i.hotelsBookingNumbers.add("68678768");
////            ObjectMapper obj = new ObjectMapper();
////            String json = obj.writeValueAsString(i);
////            Response response = target.path(path).request().accept(MediaType.APPLICATION_JSON).post(Entity.entity(json, MediaType.APPLICATION_JSON), Response.class);
////            status_code_result = response.getStatus();
////        } catch (Exception e) {
////            System.out.println("We are doomed.. GL");
////        }
////
////        assertEquals(status_code_expected_result, status_code_result);
//    }
//
//    @Test
//    public void getItineraryTEST() {
//        String path = "itineraries/0";
//
//        int status_code_result = 0;
//        int status_code_expected_result = Response.Status.OK.getStatusCode();
//
//        try {
//            Response response = target.path(path).request().accept(MediaType.APPLICATION_JSON).get();
//            status_code_result = response.getStatus();
//        } catch (Exception e) {
//            System.out.println("We are doomed.. GL");
//        }
//        assertEquals(status_code_expected_result, status_code_result);
//    }
//
//    @Test
//    public void addHotel() {
//        String path = "itineraries/0/hotels";
//
//        int status_code_result = 0;
//        int status_code_expected_result = Response.Status.CREATED.getStatusCode();
//        int bookingNumber = 9999;
//
//        try {
//            ObjectMapper obj = new ObjectMapper();
//            String json = obj.writeValueAsString(bookingNumber);
//            Response response = target.path(path).request().accept(MediaType.APPLICATION_JSON).post(Entity.entity(bookingNumber, MediaType.TEXT_PLAIN), Response.class);
//            status_code_result = response.getStatus();
//        } catch (Exception e) {
//            System.out.println("We are doomed.. GL");
//        }
//        assertEquals(status_code_expected_result, status_code_result);
//    }
//
//    @Test
//    public void cancelHotel() {
//        String path = "TravelGood/itineraries/0/hotels/9999";
//        Client client = ClientBuilder.newClient();
//        WebTarget target = client.target(BASE_URI).path(path);
//        client.target(BASE_URI).path("TravelGood/itineraries/0/hotels").request().accept(MediaType.APPLICATION_JSON).post(Entity.entity(9999, MediaType.TEXT_PLAIN), Response.class);
//
//        int status_code_result = 0;
//        int status_code_expected_result = Response.Status.ACCEPTED.getStatusCode();
//        try {
//            Response response = target.request().delete();
//            status_code_result = response.getStatus();
//        } catch (Exception e) {
//            System.out.println("We are doomed.. GL");
//        }
//        assertEquals(status_code_expected_result, status_code_result);
//    }
//
//    @Test
//    public void getFlights() {
//        String path = "TravelGood/flights";
//        Client client = ClientBuilder.newClient();
//        WebTarget target = client.target(BASE_URI).path(path);
//        target = target.queryParam("from", "Copenhagen").queryParam("to", "Amsterdam").queryParam("date", "10/11/2016");
//        String result = target.request().get(String.class);
//        String expResult = "[{\"bookingNumber\":\"CANOV215\",\"dateOfTravel\":\"2016-11-10T00:00:00+01:00\",\"FO\":{\"carrier\":\"B.e.st. Carriers\",\"destinationAirport\":\"Amsterdam\",\"endDate\":\"2016-11-10T00:00:00+01:00\",\"endTime\":\"10:35\",\"startAirport\":\"Copenhagen\",\"startDate\":\"2016-11-10T00:00:00+01:00\",\"startTime\":\"08:15\"},\"nameOfAirline\":\"SAS\",\"price\":215},{\"bookingNumber\":\"CANOV380\",\"dateOfTravel\":\"2016-11-10T00:00:00+01:00\",\"FO\":{\"carrier\":\"Wind Carriers\",\"destinationAirport\":\"Amsterdam\",\"endDate\":\"2016-11-10T00:00:00+01:00\",\"endTime\":\"14:05\",\"startAirport\":\"Copenhagen\",\"startDate\":\"2016-11-10T00:00:00+01:00\",\"startTime\":\"11:45\"},\"nameOfAirline\":\"Norwegian\",\"price\":380}]";
//        assertEquals(expResult, result);
//
//        WebTarget target2 = client.target(BASE_URI).path(path);
//        result = target2.request().get(String.class);
//        expResult = "[]";
//        assertEquals(expResult, result);
//    }
//
//    @Test
//    public void addFlightTEST() {
//        String path = "TravelGood/itineraries/0/flights";
//        Client client = ClientBuilder.newClient();
//        WebTarget target = client.target(BASE_URI).path(path);
//
//        int status_code_result = 0;
//        int status_code_expected_result = Response.Status.CREATED.getStatusCode();
//        int bookingNumber = 9999;
//
//        try {
//            ObjectMapper obj = new ObjectMapper();
//            String json = obj.writeValueAsString(bookingNumber);
//            Response response = target.request().accept(MediaType.APPLICATION_JSON).post(Entity.entity(bookingNumber, MediaType.TEXT_PLAIN), Response.class);
//            status_code_result = response.getStatus();
//        } catch (Exception e) {
//            System.out.println("We are doomed.. GL");
//        }
//        assertEquals(status_code_expected_result, status_code_result);
//    }
//
//    @Test
//    public void removeFlight() {
//        String path = "TravelGood/itineraries/0/flights/9999";
//        Client client = ClientBuilder.newClient();
//        WebTarget target = client.target(BASE_URI).path(path);
//
//        int status_code_result = 0;
//        int status_code_expected_result = Response.Status.ACCEPTED.getStatusCode();
//        int bookingNumber = 9999;
//
//        try {
//            ObjectMapper obj = new ObjectMapper();
//            String json = obj.writeValueAsString(bookingNumber);
//            Response response = target.request().delete();
//            status_code_result = response.getStatus();
//        } catch (Exception e) {
//            System.out.println("We are doomed.. GL");
//        }
//        assertEquals(status_code_expected_result, status_code_result);
//    }
//
//    @Test
//    public void book() {
//        String path = "TravelGood/itineraries/0/book";
//        Client client = ClientBuilder.newClient();
//        WebTarget target = client.target(BASE_URI).path(path);
//
//        int status_code_result = 0;
//        int status_code_expected_result = Response.Status.OK.getStatusCode();
//        int bookingNumber = 9999;
//
//        try {
//            ObjectMapper obj = new ObjectMapper();
//            String json = obj.writeValueAsString(bookingNumber);
//            Response response = target.request().accept(MediaType.APPLICATION_JSON).post(Entity.entity(bookingNumber, MediaType.TEXT_PLAIN), Response.class);
//            status_code_result = response.getStatus();
//        } catch (Exception e) {
//            System.out.println("We are doomed.. GL");
//        }
//        assertEquals(status_code_expected_result, status_code_result);
//    }

    @Test
    public void testP1() {
        createItinerary();
        // Plan flight
        List<FlightInfoObject> flights = searchFlights("Copenhagen", "Amsterdam", "10/11/2016");
        addFlight(0, flights.get(0));
        // Plan hotel
        List<HotelReservation> hotels = searchHotels("15/12/2016", "27/12/2016", "Copenhagen");
        addHotel(0, hotels.get(0));
        // Plan flight
        flights = searchFlights("Amsterdam", "Copenhagen", "22/11/2016");
        addFlight(0, flights.get(0));
        // Plan flight
        flights = searchFlights("Copenhagen", "Amsterdam", "27/12/2016");
        addFlight(0, flights.get(0));
        // Plan hotel
        hotels = searchHotels("10/11/2016", "22/11/2016", "Amsterdam");
        addHotel(0, hotels.get(0));

        // Check if they are booked
        Itinerary_2 i = getItinerary(0);
        assertEquals(false, i.checkBooked());

        // book the itinerary
        bookItinerary(0, creditCardUnlimited());

        // Check that they are booked
        i = getItinerary(0);
        assertEquals(true, i.checkBooked());
    }

    @Test
    public void testP2() {
        // Plan itinerary
        createItinerary();
        List<FlightInfoObject> flights = searchFlights("Copenhagen", "Amsterdam", "10/11/2016");
        // add flight
        addFlight(1, flights.get(1));
        // Remove the itinerary without booking it beforehand.
        deleteItinerary(1);
    }

    @Test
    public void testP2b() {
        // Plan an itinerary with three bookings (mixed ights amd hotels)
        createItinerary();

        List<FlightInfoObject> flights = searchFlights("Copenhagen", "Amsterdam", "27/12/2016");
        addFlight(1, flights.get(1));
        List<HotelReservation> hotels = searchHotels("15/12/2016", "27/12/2016", "Copenhagen");
        addHotel(1, hotels.get(1));
        hotels = searchHotels("10/11/2016", "22/11/2016", "Amsterdam");
        addHotel(1, hotels.get(1));

        // Get the itinerary and make sure that the booking status is unconfirmed for each entry.
        Itinerary_2 i = getItinerary(1);
        assertEquals(false, i.checkBooked());

        // Then book the itinerary. During booking, the second booking should fail.
        Response bookResponse = bookItinerary(1, creditCardLimited());
        /*
         Check that the result of the bookTrip operation
         records a failure (either an exception or returns a failure HTTP status code).
         */
        assertEquals(Response.Status.CONFLICT.getStatusCode(), bookResponse.getStatus());
        /*
         Get the itinerary
         and check that the returned itinerary has cancelled as the booking status of the rst booking and
         unconfirmed for the status of the second and third booking.
         */
        i = getItinerary(1);
        assertEquals(false, i.checkBooked());
    }

    @Test
    public void testC1() {
        /*
         (cancel booking) Create an itinerary with three bookings (mixed 
         ights and hotels) and book it.
         Get the itinerary and make sure that the booking status is confirmed for each entry. Cancel the
         trip and check that now the booking status is cancelled for all bookings of the itinerary.
         */
        createItinerary();
        createItinerary();

        List<FlightInfoObject> flights = searchFlights("Amsterdam", "Copenhagen", "22/11/2016");
        addFlight(2, flights.get(1));
        flights = searchFlights("Amsterdam", "Copenhagen", "22/11/2016");
        addFlight(2, flights.get(1));
        List<HotelReservation> hotels = searchHotels("10/11/2016", "22/11/2016", "Amsterdam");
        addHotel(2, hotels.get(1));

        Itinerary_2 i = getItinerary(2);
        assertEquals(false, i.checkBooked());

        assertEquals(Response.Status.ACCEPTED.getStatusCode(), bookItinerary(2, creditCardUnlimited()).getStatus());
        i = getItinerary(2);
        assertEquals(true, i.checkBooked());

    }

    @Test
    public void testC2() {
        /*
         (cancelling fails) 
         Create an itinerary with three bookings and book it. 
         Make sure that the booking status is confirmed for each entry. 
         During cancelling of the trip, the cancellation of the second booking should fail.
         Check that the cancelling resulted in an error condition (e.g. an exception or a
         failure HTTP status code). 
         Get the itinerary and check that the returned itinerary has cancelled
         as the rst and third booking and confirmed for the second booking.
         */
//        testRest(0, "yoo");
        Itinerary_2 i = getItinerary(0);
        assertEquals(true, i.checkBooked());
        Response response = cancelItinerary(0, creditCardUnlimited());
        assertEquals(Response.Status.CONFLICT.getStatusCode(), response.getStatus());

        i = getItinerary(0);

        boolean result = false;

        for (Booking b : i.hotelsReservations) {
            if (b.bookingNumber.equals("1")) {
                continue;
            }

            if (b.isBooked()) {
                result = true;
            }
        }

        for (Booking b : i.flightsBookingNumbers) {
            if (b.isBooked()) {
                result = true;
            }
        }

        assertEquals(false, result);

    }

    private void addFlight(long id, FlightInfoObject flight) {
        try {
            ObjectMapper obj = new ObjectMapper();
            String json = obj.writeValueAsString(flight);
            Response r = target.path("itineraries")
                    .path("" + id)
                    .path("flights")
                    .request()
                    .accept(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(json, MediaType.APPLICATION_JSON));

            assertEquals(Response.Status.CREATED.getStatusCode(), r.getStatus());
        } catch (Exception e) {

        }

    }

    private void addHotel(long id, HotelReservation hotel) {
        try {
            ObjectMapper obj = new ObjectMapper();
            String json = obj.writeValueAsString(hotel);
            Response r = target.path("itineraries/" + id + "/hotels")
                    .request()
                    .accept(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(json, MediaType.APPLICATION_JSON));
            assertEquals(Response.Status.CREATED.getStatusCode(), r.getStatus());
        } catch (Exception e) {

        }

    }

    private void createItinerary() {
        try {
            Itinerary_2 i = new Itinerary_2();
            ObjectMapper obj = new ObjectMapper();
            String json = obj.writeValueAsString(i);
            Response r = target.path("itineraries")
                    .request()
                    .accept(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(json, MediaType.APPLICATION_JSON), Response.class);

            assertEquals(Response.Status.CREATED.getStatusCode(), r.getStatus());
        } catch (Exception e) {

        }
    }

    private List<HotelReservation> searchHotels(
            String arrival,
            String departure,
            String city) {

        GetHotelsResponse hotelsResponse = target.path("hotels")
                .queryParam("arrival", arrival)
                .queryParam("departure", departure)
                .queryParam("city", city)
                .request()
                .get(GetHotelsResponse.class
                );

        assertNotNull(hotelsResponse);
        List<HotelReservation> hotels = hotelsResponse.getList();
        assertEquals(true, hotels.size() > 0);

        return hotels;
    }

    private List<FlightInfoObject> searchFlights(
            String from,
            String to,
            String date) {

        // Search flights
        GetFlightsResponse flightsResponse = target.path("/flights")
                .queryParam("from", from)
                .queryParam("to", to)
                .queryParam("date", date)
                .request()
                .get(GetFlightsResponse.class
                );

        return flightsResponse.getList();
    }

    private Itinerary_2 getItinerary(long id) {
        ObjectMapper mapper = new ObjectMapper();
        String json = target.path("itineraries")
                .path(id + "")
                .request()
                .get(String.class);
        System.out.println(json);
        Itinerary_2 i;
        try {
            i = mapper.readValue(json, Itinerary_2.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            i = null;
        }
        assertNotNull(i);
        return i;
    }

    private void deleteItinerary(long id) {
        ObjectMapper mapper = new ObjectMapper();
        Response response = target.path("itineraries")
                .path(id + "")
                .request()
                .delete();

        assertEquals(Response.Status.ACCEPTED.getStatusCode(), response.getStatus());

    }

    private Response bookItinerary(long id, CreditCardInfoType creditCard) {
        try {
            ObjectMapper obj = new ObjectMapper();
            String json = obj.writeValueAsString(creditCard);
            Response i = target.path("itineraries")
                    .path(id + "")
                    .path("book")
                    .request()
                    .accept(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(json, MediaType.APPLICATION_JSON), Response.class);
            System.out.println(json);
            return i;
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    private Response cancelItinerary(long id, CreditCardInfoType creditCard) {
        try {
            ObjectMapper obj = new ObjectMapper();
            String json = obj.writeValueAsString(creditCard);
            Response i = target.path("itineraries")
                    .path(id + "")
                    .path("cancelBook")
                    .request()
                    .accept(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(json, MediaType.APPLICATION_JSON), Response.class);
            return i;
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    private Response testRest(long id, String creditCard) {
        try {
            ObjectMapper obj = new ObjectMapper();
            String json = obj.writeValueAsString(creditCard);
            Response i = target.path("itineraries")
                    .path("test")
                    .path(id + "")
                    .request()
                    .accept(MediaType.APPLICATION_JSON)
                    .put(Entity.entity("hej", MediaType.TEXT_PLAIN), Response.class);
            assertEquals(Response.Status.CONFLICT.getStatusCode(), i.getStatus());
            return i;
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

//            @POST
//        @Path("/{id}/book")
//        @Consumes("text/plain")
//        @Produces("application/json")
//        public Response bookItineraryREST(@PathParam("id") long id, String text) {
}
