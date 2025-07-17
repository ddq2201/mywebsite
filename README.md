# MyWebsite Portal

A simple Java Spring Boot web portal combining user management, dashboards, mini-games, and notes in a unified web interface.

Features: User Management (View/Add), Dashboard, Match Listings, Snake Game, Rock Paper Scissors, Dragon Flight, Work Materials, Notes (Add/Edit/Delete)

Tech Stack: Java 17, Spring Boot, Thymeleaf, H2/MySQL, HTML/CSS (optional JS)

To run locally: Clone the repo using `git clone https://github.com/your-username/your-repo.git` and `cd your-repo`. Then run with `./mvnw spring-boot:run` or open in IntelliJ and run `MyWebsiteApplication`.

To access from another device: Make sure both devices are on the same Wi-Fi. Set `server.address=0.0.0.0` and `server.port=8080` in `src/main/resources/application.properties`. Then open `http://your-ip:8080` from another device (replace "your-ip" with your actual local IP like 192.168.1.x). Make sure firewall allows access.

Project structure includes folders like `controller`, `model`, `repository`, and `templates`. Use `resources/templates` for HTML and `resources/static` for CSS/JS. Application entry point is `MyWebsiteApplication.java`.

MIT License. Use freely, modify confidently.
