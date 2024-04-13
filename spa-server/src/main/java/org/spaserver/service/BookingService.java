package org.spaserver.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.spaserver.model.Booking;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@Transactional(Transactional.TxType.SUPPORTS)
@ApplicationScoped
public class BookingService {
    
    @Inject
    EntityManager em;
    
    public Response findBookingbyId(Long bookingId) {

        Object booking = em.find(Booking.class, bookingId);

        if (booking != null) {
            return Response.ok(booking).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Booking not found. Make sure you have the right booking reference.").build();
        }
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Response createNewBooking(Booking booking) {

        boolean duplicateBooking = true;
        int randomNumber;

        do {
            Random random = new Random();
            randomNumber = random.nextInt(100000000, 999999999);
            Object checkBooking = em.find(Booking.class, randomNumber);
            
            if (checkBooking == null) {
                booking.setBookingNumber(randomNumber);
                em.persist(booking);
                duplicateBooking = false;
            }
        } while (duplicateBooking);

        return Response.ok(em.find(Booking.class, randomNumber)).build();
    }

    public Response findSessionsByDate(String date) {
        List<Booking> bookedSessionObjects = em.createQuery("SELECT b FROM Booking b WHERE b.date = :date", Booking.class)
                                        .setParameter("date", date)
                                        .getResultList();
        List<String[]> bookedSessions = new ArrayList<>();
        for (Booking booking : bookedSessionObjects) {
            String[] bookedObject = {booking.getSession(), booking.getSessionType()};
            bookedSessions.add(bookedObject);
        } 
        return Response.ok(bookedSessions).build();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Response cancelBookingByBookingId(String bookingId) {
        
        Object booking = em.find(Booking.class, bookingId);

        if (booking != null) {
            em.remove(booking);
            return Response.ok().entity("Booking cancelled.").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Booking not found. Make sure you have the right booking reference.").build();
        }
    }
}