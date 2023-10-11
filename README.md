# Spring Boot MVC Online Food Delivery System

## Overview
This is a web-based project that allows users to browse, select, and order food from a restaurant. 
To enhance user experience and security, the system includes user authentication and a discount functionality.

## Photos
### Project photos     
https://github.com/DanielBaykov0/Spring_Boot_MVC_Food_Delivery_App/blob/52a9e639409554837048a0fcaadcd2112899aeec/photos
### Test coverage photo 
https://github.com/DanielBaykov0/Spring_Boot_MVC_Food_Delivery_App/blob/115e943c5fdf08a315d9a36f4b0978d0bb18e392/photos/tests/test_coverage_photo.png

## Setup backend environment
**0. Open cmd with "Run as administrator"**

**1. Clone the repository**

```bash
git clone https://github.com/DanielBaykov0/Spring_Boot_MVC_Food_Delivery_App.git
```

**2.Navigate to project directory**
```bash
cd <path to project>
```
replace < path to project > with the path where you cloned the repository

**3. After navigating to the project directory,
replace Postgres credentials with yours if you don't have them as environment variables**

**4. Run the backend locally**

## Key Features
Data for both products and users is already added to the system.

### ADMIN - There is a single admin.
        email: admin@gmail.com
        password: !Admin123
####       Admin privileges include:
* Viewing all user profiles with the ability to change their roles (adding or removing the "WORKER" role).
* Accessing the complete order history and the ability to mark orders as finished or canceled.
* Managing the menu by adding, editing, or deleting products.
* Viewing personal information but no editing capabilities.

### USER - Three regular users are manually designated.

        email: daniel@gmail.com
        password: !Daniel123

        email: alex@gmail.com
        password: !Alex123

        email: emilia@gmail.com
        password: !Emilia123

####       User privileges include:
* Browsing the menu and adding products to their cart.
* Removing products from their cart.
* Placing orders.
* Viewing their order history and order details.
* Accessing their own cart.
* Viewing and editing their personal information, including names.
* Accessing and submitting messages through the contact form.

### WORKER - Two workers are manually assigned.

        email: sinan@gmail.com
        password: !Sinan123

        email: yulia@gmail.com
        password: !Yulia123

####       Worker privileges include:
* Access to view the menu.
* Access to the complete order history and the ability to mark orders as finished or canceled.
* Viewing personal information with the ability to edit names.

### Interceptor: 
During the restaurant's operational hours, which are from 08 AM to 10 PM, logged-in users can place orders as usual. 
However, if a logged-in user attempts to place an order outside of these hours, 
they will be automatically redirected to a dedicated page that notifies them of the restaurant's working hours.

### Fetch: 
The system allows users to view the details of their orders, 
providing them with a way to access and review the specifics of their placed orders.

### Error Handling: 
The application employs a comprehensive approach to error handling. 
Firstly, there is a global controller advice mechanism in place to handle situations where objects are not found, 
ensuring that the user receives an appropriate response. 
Additionally, specific controller exceptions are utilized to address issues related to incorrect menu categories. 
Furthermore, the standard error page (Whitelabel error page) has been replaced with a customized "error.html" page 
to enhance the user experience when errors occur.

### Scheduled Jobs: 
Every day, the system offers promotional discounts on certain products. 
The specific discounts are determined based on the day of the week. 
This automated process adjusts product prices to provide users with lower prices on selected items, 
depending on which day they place their orders.