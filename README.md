
Scrabble is one of the most popular word games in the world, consisting of arranging words on a special board. The repository contains the code for a mobile application that allows the player to quickly find well-scoring words while playing scrabble. The program helps players plan their moves better, increases the chances of winning, and teaches new words. It is implemented in the React Native framework.
The application analyzes a photo from the phone's camera, which improves the input of game status information. It uses image processing with the OpenCV library in Python. After the scanning stage, the mobile app suggests correct moves to the player, based on a scrabble-solving algorithm. Typical user workflow is presented on the gif:
<p align="center">
<img src="https://github.com/kristopalka/scrabble-solver/blob/master/resources/gitresources/app_demo.gif?raw=true" width="300"/>
</p>

All calculations are performed on the server, by a dedicated server-side application. It is implemented in Java and the Spring Boot framework, containerized by Docker. The simple schema of application architecture is as follows:
<p align="center">
<img src="https://github.com/kristopalka/scrabble-solver/blob/master/resources/gitresources/architekture.png?raw=true" width="400"/>
</p>


## How to run

### Backend
You need **docker**, **docker-compose** installed on your computer. Go to backend folder and run the following command. It will automatically build and run an app on port 8080.
> docker-compose up -d scrabble-solver-backend


### Mobile app
Download built version of app and install on your mobile phone. Then enter the ip address of your computer in the application. 
If you want to develop this app, you need **npm** package manager on computer, and **Expo Go** mobile app installed on mobile device. Then go to mobile folder and run the following command. There will show QR code, scan it by Expo Go app.
> npm start


## Todo
 - upgrade character recognition method
 - add support for blanks
 - repair algorithm parallel words
