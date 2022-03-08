package com.collections;

import java.util.*;

public class Theatre {
    //make an app that display all the seats numbers, seats rows and theatres
    //it should be able to reserve a seat and also cancel a seat at the same time
    //my addition--i will add a method to change the seat
    private final String theatreName;
   // private List<Seats> seats = new ArrayList<Seats>();
   //It will still work with this
   //private Collection<Seats> seats = new LinkedHashSet<>(); //still leaves things in order
    private List<Seats> seats = new LinkedList<>(); //this aids more flexibility
   // private Collection<Seats> seats = new HashSet<>(); By using hashSet
    public Theatre(String theatreName, int numRows, int seatsPerRow) {
        this.theatreName = theatreName;

        int lastRow = 'A' + (numRows + 1); //A in addition with the increment of the number of rows
        for (char Row = 'A'; Row <= lastRow; Row++) { //Using for loop to increment num row in alphabet
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) { //increment the seat rows however we got no 0
                Seats seat = new Seats(Row + String.format("%02d", seatNum));
                seats.add(seat);
            }
        }
    }

    public String getTheatreName() { //get the theatre name
        return theatreName;
    }

    public boolean reserveSeat(String seatNumber) { //search for a seat number
//THE BINARY API SOURCE CODE
        int low = 0;
        int high = seats.size()-1;

        while (low <= high){
            System.out.println(".");
            int mid = (low + high) / 2;
            Seats midVal = seats.get(mid);
            int cmp = midVal.getSeatNumber().compareTo(seatNumber);

            if (cmp < 0){
                low = mid + 1;
            }else if (cmp > 0){
                high = mid -1;
            }else {
                return seats.get(mid).reserve();
            }
        }
        System.out.println("There's no new number " + seatNumber);
        return false;
//        Seats requestedSeat = new Seats(seatNumber);
//        int foundSeat = Collections.binarySearch(seats,requestedSeat,null);
//        if (foundSeat > 0){
//            return seats.get(foundSeat).reserve();
//        }else {
//            System.out.println("There's no seat " + seatNumber);
//            return false;
   //     }

//        for (Seats seat :
//                seats) {
//            if (seat.getSeatNumber().equals(seatNumber)) {
//                requestedSeat = seat;
//                break;
//            }
//        }
//        if (requestedSeat == null) {
//            System.out.println("There's no number " + seatNumber);
//            return false;
//        }
//        return requestedSeat.reserve();
   }

    public boolean CancelSeat(String seatNumber) { //search for a seat number
//THE BINARY API SOURCE CODE
        int low = 0;
        int high = seats.size() - 1;

        while (low <= high) {
            System.out.println(".");
            int mid = (low + high) / 2;
          Seats  midVal = seats.get(mid);
            int cmp = midVal.getSeatNumber().compareTo(seatNumber);

            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return seats.get(mid).cancel();
            }
        }
        System.out.println("Never been booked before " + seatNumber);
        return false;
    }
    //for testing
    public void getSeats() {
        for (Seats seat :
                seats) {
            System.out.println(seat.getSeatNumber());
        }
    }


    public class Seats implements Comparable<Seats> {
        private final String seatNumber;
        private boolean reserved = false;

        public Seats(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        @Override
        public int compareTo(Seats seat) {
            return this.seatNumber.compareToIgnoreCase(seat.getSeatNumber());
        }

        public boolean reserve(){
            if (!this.reserved){
                this.reserved = true;
                System.out.println("Seat " + seatNumber + " Number");
                return true;
            }else {
                return false;
            }
        }

        public boolean cancel(){
            if (this.reserved){
                this.reserved = false;
                System.out.println("Reservation seat " + seatNumber + " has been canceled");
                return true;
            }else {
                return false;
            }
        }

       public String getSeatNumber(){
            return seatNumber;
       }
    }
}
