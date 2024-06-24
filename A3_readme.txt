Game can be run by running gradle run scritpt in the terminal
The game starts with easy level and the user can change the level at any time.
Changing the level also restarts the game.
The cue has to be clicked first in order to make the cue (saddlebrown ball) visible. Then the user should drag the cue and release so the cue hits the ball.


Features:

1) Pockets are read from the configuration file. We have used the existing factory pattern and extended on it to create pockets.
I created PocketConfig and PocketsConfig file to read the JSON and create pocket objects.
The table has an additional attribute pockets to save the pocket objects and these are set up in the table class.

2) Coloured balls
Each coloured balls is build using different builders and the ball director registry has been updated with the new coloured ball builders
namely
BlackBallBuilder, BrownBallBuilder, GreenBallBuilder, OrangeBallBuilder, PurpleBallBuilder, YellowBallBuilder
with their corresponding strategy's (Added PocketThrice).

3) Cue
The cue has to be clicked first in order to make the cue (saddlebrown ball) visible. Then the user should drag the cue and release so the cue hits the ball.
These are implemented in the registerCueBallMotion function inside the ball class.

4) Difficulty level
The difficulty has been implemented with factory method with the interface being LevelFactory class
The different levels are read from the config file using each factory namely EasyFactory, HardFactory and NormalFactory.
These different factories are saved in a registry file called LevelFactoryRegistry.

5) Time
The time have been implemented inside the game class by adding another variable for the game class it will be resets when the level changes.
When the user wins the game the timer stops.

6) Score
The score has been implemented with a singleton pattern so all classes have a global access point to it.
It is continually updated in the ball class when the ball falls in the pocket.
The balls have another variable scoreIncrement which is the score that will be added fi that particular ball falls into the pocket.
The balls have these value initialized in the builder pattern.

7) Undo
The user can undo a shot. This has been implemented using the memento pattern. The memento has saved the timer, score and the ballData.
BallData is a class which saves the individual ball positions, fallCounter and the isdisabled attributes.
The Caretaker class saves the Mementos and returns the last memento. The game class is the originator.

8) Cheat
The cheat has been implemented using observer and singleton pattern. here the ball is the concrete observer.
There are concrete subjects which checks if its state has changes (State refers to key pressed). The concrete subjects implement singleton pattern.
These subjects tell the observer(Ball) to update the score 5 times the usual and disables the ball.The concrete objects are:
BlackBallCheat, BrownBallCheat, BlueBallCheat, GreenBallCheat, OrangeBallCheat, PurpleBallCheat, RedBallCheat, YellowBallCheat.
In each of the ball builder it attaches itself to the concrete subjects.


Instructions:

The game starts with the easy level and can be changed using 1,2,3 number keys.
1 - Easy Level
2 - Normal level
3 - Hard level.

Undo can be done by pressing the Z key.

Cheat can be done for different coloured balls.
A - Black ball cheat
S - Blue ball cheat
D - Brown ball cheat
F - Green ball cheat
G - Orange ball cheat
H - Purple ball cheat
J - Red ball cheat
K - Yellow ball cheat
