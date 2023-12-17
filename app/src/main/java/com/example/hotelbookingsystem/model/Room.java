package com.example.hotelbookingsystem.model;

public class Room {

    private String hotelName;
    private String hotelLocation;
    private String roomType;
    private String numberOfBeds;
    private String roomFacilities;
    private String checkInDate;
    private String checkOutDate;
    private String numberOfNights;
    private String pricePerNight;
    private String numberOfRooms;
    private String numberOfAdults;
    private String numberOfChildren;
    private String bookingId;
    private String totalPrice;
    private String roomNumber;
    private String roomStatus;

    private Long id;

    private String room_number;
    private int room_floor;
    private String room_type;
    private int room_price;
    private String room_desciption;
    private int number_of_beds;
    private String room_status;

    private Long createdById;
    private String createdByName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public int getRoom_floor() {
        return room_floor;
    }

    public void setRoom_floor(int room_floor) {
        this.room_floor = room_floor;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public int getRoom_price() {
        return room_price;
    }

    public void setRoom_price(int room_price) {
        this.room_price = room_price;
    }

    public String getRoom_desciption() {
        return room_desciption;
    }

    public void setRoom_desciption(String room_desciption) {
        this.room_desciption = room_desciption;
    }

    public int getNumber_of_beds() {
        return number_of_beds;
    }

    public void setNumber_of_beds(int number_of_beds) {
        this.number_of_beds = number_of_beds;
    }

    public String getRoom_status() {
        return room_status;
    }

    public void setRoom_status(String room_status) {
        this.room_status = room_status;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public Room(String hotelName, String hotelLocation, String roomType, String numberOfBeds, String roomFacilities, String checkInDate, String checkOutDate, String numberOfNights, String pricePerNight, String numberOfRooms, String numberOfAdults, String numberOfChildren, String bookingId, String totalPrice, String roomNumber, String roomStatus, Long id, String room_number, int room_floor, String room_type, int room_price, String room_desciption, int number_of_beds, String room_status, Long createdById, String createdByName) {
        this.hotelName = hotelName;
        this.hotelLocation = hotelLocation;
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.roomFacilities = roomFacilities;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfNights = numberOfNights;
        this.pricePerNight = pricePerNight;
        this.numberOfRooms = numberOfRooms;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
        this.bookingId = bookingId;
        this.totalPrice = totalPrice;
        this.roomNumber = roomNumber;
        this.roomStatus = roomStatus;
        this.id = id;
        this.room_number = room_number;
        this.room_floor = room_floor;
        this.room_type = room_type;
        this.room_price = room_price;
        this.room_desciption = room_desciption;
        this.number_of_beds = number_of_beds;
        this.room_status = room_status;
        this.createdById = createdById;
        this.createdByName = createdByName;
    }

    public Room(String checkInDate, String checkOutDate, String roomNumber, String roomStatus) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomNumber = roomNumber;
        this.roomStatus = roomStatus;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Room() {
    }

    //For View Room
    public Room(String hotelName, String hotelLocation, String roomType, String numberOfBeds, String roomFacilities, String checkInDate, String checkOutDate, String numberOfNights, String pricePerNight, String totalPrice) {
        this.hotelName = hotelName;
        this.hotelLocation = hotelLocation;
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.roomFacilities = roomFacilities;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfNights = numberOfNights;
        this.pricePerNight = pricePerNight;
        this.totalPrice = totalPrice;
    }

    //For Reserve Room
    public Room(String hotelName, String hotelLocation, String roomType, String checkInDate, String checkOutDate, String numberOfNights, String pricePerNight, String numberOfRooms, String numberOfAdults, String numberOfChildren, String bookingId, String totalPrice) {
        this.hotelName = hotelName;
        this.hotelLocation = hotelLocation;
        this.roomType = roomType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfNights = numberOfNights;
        this.pricePerNight = pricePerNight;
        this.numberOfRooms = numberOfRooms;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
        this.bookingId = bookingId;
        this.totalPrice = totalPrice;
    }

    //For Pending Reservations
    public Room(String hotelName, String checkInDate, String checkOutDate, String numberOfNights, String numberOfRooms, String numberOfAdults, String numberOfChildren, String bookingId, String totalPrice) {
        this.hotelName = hotelName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfNights = numberOfNights;
        this.numberOfRooms = numberOfRooms;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
        this.bookingId = bookingId;
        this.totalPrice = totalPrice;
    }

    public Room(String hotelName, String hotelLocation, String roomType, String numberOfBeds, String roomFacilities, String pricePerNight, String roomNumber, String roomStatus) {
        this.hotelName = hotelName;
        this.hotelLocation = hotelLocation;
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.roomFacilities = roomFacilities;
        this.pricePerNight = pricePerNight;
        this.roomNumber = roomNumber;
        this.roomStatus = roomStatus;
    }

    @Override
    public String toString() {
        return "Room{" +
                "hotelName='" + hotelName + '\'' +
                ", hotelLocation='" + hotelLocation + '\'' +
                ", roomType='" + roomType + '\'' +
                ", numberOfBeds='" + numberOfBeds + '\'' +
                ", roomFacilities='" + roomFacilities + '\'' +
                ", checkInDate='" + checkInDate + '\'' +
                ", checkOutDate='" + checkOutDate + '\'' +
                ", numberOfNights='" + numberOfNights + '\'' +
                ", pricePerNight='" + pricePerNight + '\'' +
                ", numberOfRooms='" + numberOfRooms + '\'' +
                ", numberOfAdults='" + numberOfAdults + '\'' +
                ", numberOfChildren='" + numberOfChildren + '\'' +
                ", bookingId='" + bookingId + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                '}';
    }


    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelLocation() {
        return hotelLocation;
    }

    public void setHotelLocation(String hotelLocation) {
        this.hotelLocation = hotelLocation;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(String numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public String getRoomFacilities() {
        return roomFacilities;
    }

    public void setRoomFacilities(String roomFacilities) {
        this.roomFacilities = roomFacilities;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(String numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public String getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(String pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(String numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(String numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public String getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(String numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
