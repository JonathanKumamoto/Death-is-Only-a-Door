# Death-is-Only-a-Door
A repository for a game engine programmed in Java for Libgdx


An important aspect of this respository is the powerful use of an xml parser to create a game engine which will
"load" and "unload" images in the "text" asset folder. An xml game engine, that is manipulatable for multiple objects 
in multiple dimensions is achieved. While not shown in the direct code of the xml page, a "stair" or "stairs" object can
be implemented inside the Libgdx game (using Java) through the use of the game engine, specifying the stair position, size,
and facing the left/right direction.

All objects in the DOD game engine, are able to be in different or both dimensions of the game-state. More importantly,
the stairs object will "kill" the main character if it changes dimensions and the character is still inside the "stairs"
object.

There are many different functions that the character is able to do, in which any user or game programmar would find useful.
I created this game engine as well as the objects such as "stairs" because the provided libaries for libgdx
and object and game representation did not do what I wanted, and this provided the freedom I needed to express my
creativity and thought. For libgdx users and java game developers concerning character movement and object collision, I have figured out the algorithm and implemention for
characters to jump/bounce, stick (to an object), slide (while stuck on an object), die, level-completion,
and stairs (using a tringle/polygon object collision algorithm). For libgdx users and java game developers 
concerning memory management, I have created an xml parser so that levels are able to be created on 1-sheet, "loading"
and "unloading" system integrated with xml to reduce mobile memory/ram consumption, and correctly
calling the java "garbage" collector in order to reduce and remove un-needed data. 











*Note: DOD = Death is Only a Door
