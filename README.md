# IBM SkillsBuild Gamification Web Application

## Table of Contents

- [About the Project](#about-the-project)
- [Installation and Running](#installation-and-running)
- [Technologies Used](#technologies-used)
- [Contact](#contact)

## About the Project

This project is a gamification web application of IBM's SkillsBuild, a free skills-based learning program.

It features a secure login system, a dashboard to view your progress, and a leaderboard to compare yourself with others.

For more information on IBM SkillsBuild, please visit [IBM SkillsBuild](https://skillsbuild.org/).

## Installation and Running

These instructions will get the web application running on your local machine for use and development.

### Prerequisites

You will need:

- [Java JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Gradle](https://gradle.org/)
- [MySQL](https://www.mysql.com/)
- Any IDE for Java (e.g., [IntelliJ IDEA](https://www.jetbrains.com/idea/), [Eclipse](https://www.eclipse.org/ide/), [VS Code](https://code.visualstudio.com/))

### Installation

1. **Clone the repository**

   ```bash
   git clone https://campus.cs.le.ac.uk/gitlab/co2201-2024/group-01
   ```

2. **Navigate to the project directory**

   ```bash
   cd .\group-01\ibm-skillsbuild-app\
   ```

3. **Install dependencies**

   ```bash
   ./gradlew build
   ```

4. **Set up MySQL connection**

   Edit `application.properties`, replacing the placeholder values to include your MySQL connection and user.

### Running the Program

How to run the program on your local machine.

1. **Start the application**

   ```bash
   ./gradlew bootRun
   ```

2. **Use the application**

   Access `http://localhost:8080` with your choice of web browser.

## Technologies Used

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Gradle](https://gradle.org/)
- [MySQL](https://www.mysql.com/)

## Contact

- **Ben Millington** - @bm308 - [bm308@student.le.ac.uk](mailto:bm308@student.le.ac.uk)
- **Yash Parmar** - @yp114 - [yp114@student.le.ac.uk](mailto:yp114@student.le.ac.uk)
- **Enzo Brown** - @eb433 - [eb433@student.le.ac.uk](mailto:eb433@student.le.ac.uk)
- **Miracle Ndu** - @mn303 - [mn303@student.le.ac.uk](mailto:mn303@student.le.ac.uk)
- **Jahnavi Muniraj** - @jm958 - [jm958@student.le.ac.uk](mailto:jm958@student.le.ac.uk)
- **Kevin Han** - @jh892 - [jh892@student.le.ac.uk](mailto:jh892@student.le.ac.uk)
- **Mikail Arioz** - @ma1002 - [ma1002@student.le.ac.uk](mailto:ma1002@student.le.ac.uk)
- **Leandro De Noronha** - @ldn5 - [ldn5@student.le.ac.uk](mailto:ldn5@student.le.ac.uk)
