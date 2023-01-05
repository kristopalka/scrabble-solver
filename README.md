
Scrabble is one of the most popular word games in the world, consisting of arranging words on a special board. The repository contains the code for a mobile application that allows the player to quickly find well-scoring words while playing scrabble. The program helps players plan their moves better, increases the chances of winning, and teaches new words. It is implemented in the React Native framework.
The application analyzes a photo from the phone's camera, which improves the input of game status information. It uses image processing with the OpenCV library in Python. After the scanning stage, the mobile app suggests correct moves to the player, based on a scrabble-solving algorithm. All calculations are performed on the server, by a dedicated application backend. It is implemented in Java in the Spring Boot framework.

<p align="center">
<kbd>
<img src="https://github.com/kristopalka/scrabble-solver/blob/master/resources/gitresources/demo.gif?raw=true" alt="drawing" width="300" border="5"/>
</kbd>
</p>


## How to run
You need **docker**, **docker-compose** and **npm** installed on your computer. Also, you need **Expo Go** mobile app installed on your mobile device.
Go to backend folder and run:
> docker-compose up -d backend

Then go to mobile and run:
> npm start

There will show QR code, scan it by Expo Go app.

## Todo
 - upgrade character recognition method
 - add support for blanks
 - repair algorithm parallel words
