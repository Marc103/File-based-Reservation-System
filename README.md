# File-based-Reservation-System
This file-based reservation system keeps track of who is renting which house and for what time period during the weekend. All information regarding the house ID, price and reservations are stored on the database.txt file. The change.txt file represents changes that are to be made to housing rent.

There are two separate runnable classes, Management.class and Client.class. 

Management.class is used to view current reservations as well as apply changes from the changes.txt file.
e.g( "1 150
     "2 300") changes the rent of house with ID 1 to 150 and of ID 2 to 300.
     
Client.class is used to by the client to perform a reservation.

