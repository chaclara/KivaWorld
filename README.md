# KivaWorld

PROJECT OVERVIEW
The Kiva robots are finished and they're incredible! They know how to move forward (F), turn left (L), turn right (R), take (T), and drop (D). What are they lifting and dropping? They are lifting and dropping pods filled with products and carrying them between the pod's storage location and a drop zone, where the Kiva robot remains, and a Fulfillment Center employee removes products from the pod to pack an order. We have created a program that prints out a map showing where the robot currently is, where the pod to pick up is, and where it needs to be dropped off.

We need you to program a Kiva Robot Remote Control! Right now we've programmed the Remote Control to print out the map of the FC and then take in directions from the driver, but those directions aren't being sent to the robot. We need you to interpret the directions and send them to the robot. Kiva Robots are incredibly expensive to make so you need to make sure they won't drive into any walls or obstacles when you make a move.

REMOTE CONTROL(Already Included)
The RemoteControl class is responsible for running the program, interacting with the user, and moving the Kiva robot. We have provided you this class with some starter code in it. It contains logic to interact with the console by printing out the map of the floor, and taking in the string of directions from the user.

In addition to RemoteControl, the project will utilize some code that has already been written by the Warehouse Team. The Warehouse Team had already written this code, so they've packaged it up for our team to use. You will not need to make changes to any of these classes, but we wanted to explain how this works so it doesn't seem like too much magic. These classes are tucked away inside a JAR (Java ARchive) file inside KivaWorld/+libs/. A JAR file is a convenient way to bundle a bunch of related Java classes (often called a library) into a single file that the JVM can access. (Optional: Here is a good intro on JARs, though a little more information than you need right now.)

Here's a quick summary of the classes and enumerations provided by the Warehouse Team:

FloorMap: Represents a Kiva's environment, including pickup/dropoff points, obstacles and Kiva's starting location. It allows identifying what exists at any given location, as well as rendering the entire map as a String for display.
FacingDirection: Represents the direction a robot is facing. There are four orientations, and each includes a “delta” point which moves the robot one space in that direction when added to the current location.
FloorMapObject: Represents what is located at any given location of the FloorMap.​

PROJECT GOALS
Your goal is to complete all of the project tasks, which will result in RemoteControl interacting with the user in the following way.

Please select a map file.

---------------
|        P   *|
|   **       *|
|   **       *|
| *K       D *|
---------------

Current Kiva Robot location: (3,4)
Facing: UP
Please enter the directions for the Kiva Robot to take.
FFFRFFFFFFTFFRFFFD


Successfully picked up the pod and dropped it off. Thank you!
The user will not always provide a path that successfully picks up and drops off the pod. We will detail out these error cases later and how to handle them, but as an example, here the path provided never picks up the pod.

Please select a map file.

 -------------|
         P   *|
    ---      -|
    |*|      *|
   K---    D -|
  *     * *  *|
 -------------|

Current Kiva Robot location: (2,4)
Facing: UP
Please enter the directions for the Kiva Robot to take.
FLFFFFFF

I'm sorry. The Kiva Robot did not pick up the pod and then drop it off in the right place.​

IMPLEMENTATION OVERVIEW
KivaCommand describes the “moves” a Kiva robot can make: turning left or right, moving forwards, taking a pod, and dropping a pod. It also includes each move's one-letter abbreviation.

Kiva holds the state and logic of a robot: where it is, which way it's facing, whether it's carrying a pod right now, and whether it has successfully dropped the pod. A Kiva always starts facing UP (one of our provided FacingDirections), not carrying a pod, and not having successfully dropped the pod. The move() method performs the logic for any KivaCommand, and the isSuccessfulDrop() method indicates whether the previous command dropped the pod on the drop zone. We normally don't add getters to class diagrams, but this is the first time you're encountering a common naming convention that ATA will follow: boolean getters often follow the pattern “isFieldName()” or “hasFieldName()”.

When an impossible move is attempted, move() throws an exception. IllegalMoveException is appropriate for attempts to move the robot off the warehouse floor or into an obstacle; NoPodException is expected when there's an attempt to TAKE at a location without a POD; and IllegalDropZoneException should occur when we try to drop a pod anywhere but a DROP_ZONE. These exceptions are provided to you by the Warehouse Team.

Running the whole show is RemoteControl, initiated by executing its run() method. RemoteControl prompts the user to select a text file containing a FloorMap to load, prompts the user to input a chain of KivaCommands using single-letter abbreviations, and then executes the KivaCommands until Kiva drops the pod successfully into the drop zone (or encounters an error).
