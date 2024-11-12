# Healthcare Appointment Booking System

This is a RESTful API for managing healthcare appointments, including user management, doctor management, and appointment booking. This API can be tested using the Postman application by importing the provided Postman collection.

## Table of Contents
1. [User Management](#user-management)
   - Sign Up
   - Login
   - Update Profile
2. [Doctor Management](#doctor-management)
   - Doctor Registration
   - Fetch Doctor List
3. [Appointment Booking](#appointment-booking)
   - Create Appointment
   - View Appointment
   - Cancel Appointment
4. [Database Configuration](#database-configuration)
5. [Postman Collection](#postman-collection)

1.User Management

    i.Sign Up :                                         
    url: http://localhost:8080/api/auth/signup          
    method: post                                           
    Body>raw>json :{                                       
    "name": "John Doe",                                        
    "email": "doe12@example.com",
    "password": "password123",
    "phone":"1233211233"
}
Response:User registered successfully
if we try again signup with same email we will get
Error: Email already registered
   ii.Login :
    url: http://localhost:8080/api/auth/login
    method: post                                         
    Body>raw>json :{ 
     "email": "doe12@example.com",
    "password": "password123"
}
Response:JWT token and Stutescode: 200 OK
Email id should be same while login it should match with signup email id
iii.Update Profile :
      url: http://localhost:8080/api/auth/profile?userId=1
      method: put
      Body>raw>json:{
        "name":"update name",
        "phone":"1221124421"
}
Response: Profile updated successfully


2. Doctor Management
   i.Doctor Registration :
   
         url: http://localhost:8080/api/doctors/register?availability=Full-Time&experience=2&name=Dr.y&specialization=ccc
         method: post
Response:Doctor registered successfully with ID: 4
If we try to register with same doctorname we will get
Error: Doctor with this name already exists. doctor name should be unique.

ii.Fetch Doctor List :
url:http://localhost:8080/api/doctors/specialization?specialization=Cardiology
url:http://localhost:8080/api/doctors/list
method: Get
Response : [
    {
        "id": 1,
        "name": "Dr. John Doe",
        "specialization": "Cardiology",
        "availability": "Mon-Fri, 9 AM - 5 PM"
    },
    {
        "id": 2,
        "name": "Dr. Jane Smith",
        "specialization": "Dermatology",
        "availability": "Mon-Fri, 10 AM - 4 PM"
    }
]
3.Appointment Booking
      i.Create Appointment
          url:http://localhost:8080/api/appointments/book
          method:Post
          Body>raw>json: {
                     "userId": 1,
                     "doctorId": 2,
                     "appointmentDate": "2024-11-20T14:30:00"
        }
Response: {
    "id": 1,
    "doctorId": 2,
    "doctorName": "Dr. John Doe",
    "userId": 1,
    "userName": "Jane Doe",
    "appointmentDate": "2024-11-20T14:30"
}

     ii.View Appointment

         url:http://localhost:8080/api/appointments/user/1
         method:Get
         Content-Type: application/json
         Response:[
    {
        "id": 1,
        "userId": 1,
        "doctorId": 2,
        "appointmentDate": "2024-11-20T14:30:00",
        "status": "Scheduled",
        "doctorName": "Dr. John Doe",
        "userName": "John Smith"
    },
    {
        "id": 2,
        "userId": 1,
        "doctorId": 3,
        "appointmentDate": "2024-11-22T10:00:00",
        "status": "Scheduled",
        "doctorName": "Dr. Jane Doe",
        "userName": "John Smith"
    }
   ]
   If the user has no appointments, you'll get an empty array :[]
   If there is an error (e.g., invalid userId), you can expect a 400 or 404 error with a relevant message : {"error": "User not found" }

   iii.Cancel Appointment
       url:http://localhost:8080/api/appointments/cancel/{appointmentId}/{userId} 
       Replace {appointmentId} with the ID of the appointment you want to cancel, and {userId} with the ID of the user who booked the appointment
       method:Delete
       After sending the request, you should receive a response like: Success (200 OK):
       Response : "Appointment canceled successfully"
       Failure (400 Bad Request):
       If the user is not the one who booked the appointment: "You can only cancel your own appointments"
       If the appointment is within 24 hours: "Appointment cannot be canceled within 24 hours of the scheduled time"
       If the appointment doesn’t exist: "Appointment not found"


Database Configuration for MySQL Workbench
spring.datasource.url=jdbc:mysql://localhost:3306/healthcare_appointment
spring.datasource.username=root
spring.datasource.password=root

### Key Features:
1. **User Management**: Endpoints for user sign-up, login, and profile updates.
2. **Doctor Management**: Includes doctor registration and fetching doctor lists.
3. **Appointment Booking**: Endpoints to create, view, and cancel appointments.
4. **Database Configuration**: MySQL setup details.
5. **Postman Collection**: Instructions on how to import and use the Postman collection for API testing.


