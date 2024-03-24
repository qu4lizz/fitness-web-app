# eFitness

## Introduction

A web project consisted of four applications that is used for creating, buying and participating fitness programs, tracking your progress, recommending training programs.
This web project comprises four applications designed for creating, purchasing, and participating in fitness programs, tracking progress, and receiving recommendations for training programs.

## Warning
Please note that security was not prioritized in this project. If you're interested in viewing a secure web application, check out the <a href="https://github.com/qu4lizz/forum">Forum application</a>.

## Technologies

<ul>
    <li>Angular (JS/TS)</li>
    <li>Java Spring Boot</li>
    <li>Java Servlets</li>
    <li>JSP (Java Server Pages)</li>
    <li>PostgreSQL</li>
</ul>


## Applications Descriptions

### Client (Angular)
<p>
    A responsive Angular application serves as the front-end, utilizing PrimeNG as the UI library. Version 17 of Angular is employed.
</p>
<p align="justify">
    On the home screen, it displays fitness facts from an RSS feed, along with the top 10 daily exercises. Users can browse all available programs, filter them by category and difficulty, and sort them by name, price, and date. The application also facilitates the creation of new programs. Users can view programs they've created or participated in. Additionally, they can send questions to fitness counselors.
</p>
<p align="justify">
    Users can exchange messages, track their progress by adding results after each exercise, view their progress on graphs and tables, download progress as PDF, and modify their information and password.
</p>

![Home Screenshot](screenshots/home.jpg)
![All Programs Screenshot](screenshots/all-programs.jpg)
![My Programs Screenshot](screenshots/my-programs.jpg)
![Program Details Screenshot](screenshots/program-details.jpg)
![My Activities Screenshot](screenshots/my-activities.jpg)
![Messages Screenshot](screenshots/messages.jpg)
![Edit Profile Screenshot](screenshots/edit-profile.jpg)

### Server (Spring Boot)
<p align="justify">
    The backend of the application provides resources to the Client, offering functionalities such as REST services, email sending, RSS consuming, scheduled tasks, logging, and authentication (register, login, mail confirmation).
</p>

### Counseling App (JSP)
<p align="justify">
    This application is used by fitness counselors to respond to user questions. In addition to messages, counselors can attach files that will be sent to the user via email.
</p>

![Counseling App Home Screenshot](screenshots/counselingapp.jpg)
![Counseling App Details Screenshot](screenshots/counselingapp-details.jpg)

### Admin App (JSP M2 and Servlets)
<p align="justify">
    Admins have access to application logs and functionalities for adding, deleting, and editing users, advisors, and categories. Attributes can be added to each category.
</p>

![Admin App Screenshot](screenshots/admin.jpg)
