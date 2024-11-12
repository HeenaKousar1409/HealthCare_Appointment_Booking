
User Management
i. Sign Up
URL: http://localhost:8080/api/auth/signup
Method: POST
Body (raw > JSON):
json
{
  "name": "John Doe",
  "email": "doe12@example.com",
  "password": "password123",
  "phone": "1233211233"
}

Response:
Success: "User registered successfully"
Error (duplicate email): "Error: Email already registered"
ii. Login
URL: http://localhost:8080/api/auth/login
Method: POST
Body (raw > JSON):
json
{
  "email": "doe12@example.com",
  "password": "password123"
}

Response:
Success: JWT token and status code 200 OK
Note: Email must match the one used during signup.
iii. Update Profile
URL: http://localhost:8080/api/auth/profile?userId=1
Method: PUT
Body (raw > JSON):
json
{
  "name": "update name",
  "phone": "1221124421"
}

Response: "Profile updated successfully"
Doctor Management
i. Doctor Registration
URL: http://localhost:8080/api/doctors/register?availability=Full-Time&experience=2&name=Dr.y&specialization=ccc
Method: POST
Response:
Success: "Doctor registered successfully with ID: 4"
Error (duplicate doctor name): "Error: Doctor with this name already exists."
ii. Fetch Doctor List
URLs:
By specialization: http://localhost:8080/api/doctors/specialization?specialization=Cardiology
All doctors: http://localhost:8080/api/doctors/list
Method: GET
Response:
json
[
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

Appointment Booking
i. Create Appointment
URL: http://localhost:8080/api/appointments/book
Method: POST
Body (raw > JSON):
json
{
  "userId": 1,
  "doctorId": 2,
  "appointmentDate": "2024-11-20T14:30:00"
}

Response:
json
{
  "id": 1,
  "doctorId": 2,
  "doctorName": "Dr. John Doe",
  "userId": 1,
  "userName": "Jane Doe",
  "appointmentDate": "2024-11-20T14:30"
}

ii. View Appointment
URL: http://localhost:8080/api/appointments/user/1
Method: GET
Content-Type: application/json
Response:
json
[
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

If no appointments exist, response will be an empty array [].
Error response for invalid user ID might be:
json
{"error": "User not found"}

iii. Cancel Appointment
URL: http://localhost:8080/api/appointments/cancel/{appointmentId}/{userId}
Replace {appointmentId} and {userId} with actual values.
Method: DELETE
Response:
Success (200 OK): "Appointment canceled successfully"
Failure (400 Bad Request):
If not the booker of the appointment: "You can only cancel your own appointments"
If within the cancellation window of less than 24 hours before the appointment time: "Appointment cannot be canceled within 24 hours of the scheduled time"
If appointment does not exist: "Appointment not found"
Database Configuration for MySQL Workbench
text
spring.datasource.url=jdbc:mysql://localhost:3306/healthcare_appointment
spring.datasource.username=root
spring.datasource.password=root


