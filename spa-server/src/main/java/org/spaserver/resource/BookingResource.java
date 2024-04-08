package org.spaserver.resource;

import java.util.Date;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.spaserver.model.Booking;
import org.spaserver.service.BookingService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingResource {
    
    @Inject
    BookingService bookingService;

    @GET
    @Operation(summary = "Find a booking", description = "Enter a booking reference (String) to retrieve a booking (JSON object)")
    @APIResponse(responseCode = "404", description = "No booking found")
    @APIResponse(responseCode = "200", description = "Booking found")
    @Path("/find-booking")
    public Response findBooking(@HeaderParam("bookingId") Long bookingId) {
        return bookingService.findBookingbyId(bookingId);
    }

    @POST
    @Operation(summary = "Make a booking", description = "Make a booking by posting JSON object with booking details")
    @APIResponse(responseCode = "404", description = "Error: No response from database.")
    @APIResponse(responseCode = "200", description = "Booking Successful.")
    @APIResponse(responseCode = "500", description = "Internal Server Error.")
    @Path("/create-booking")
    public Response createBooking(@RequestBody Booking booking) {
        return bookingService.createNewBooking(booking);
    }

    @GET
    @Operation(summary = "Check session availability", description = "Checks session availability by date, returns a list of UNAVAILABLE sessions")
    @APIResponse(responseCode = "404", description = "Error: No response from database.")
    @APIResponse(responseCode = "200", description = "Booking found")
    @Path("/find-sessions")
    public Response checkSessionAvailability(@HeaderParam("date") String date) {
        System.out.println(date);
        return bookingService.findSessionsByDate(date);
    }

    @DELETE
    @Operation(summary = "Cancel a booking", description = "Cancel a booking using the booking ID.")
    @APIResponse(responseCode = "404", description = "No booking found.")
    @APIResponse(responseCode = "200", description = "Booking successfully cancelled")
    @Path("/cancel-booking")
    public Response cancelBooking(@HeaderParam("BookingId") String bookingId) {
        return bookingService.cancelBookingByBookingId(bookingId);
    }

}