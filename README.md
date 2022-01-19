# KivaWorld

The Kiva robots are finished and they're incredible! They know how to move forward (F), turn left (L), turn right (R), take (T), and drop (D). 
What are they lifting and dropping? They are lifting and dropping pods filled with products and carrying them between the pod's storage location and a drop zone, where the Kiva robot remains, and a Fulfillment Center employee removes products from the pod to pack an order. 
We have created a program that prints out a map showing where the robot currently is, where the pod to pick up is, and where it needs to be dropped off.
We need you to program a Kiva Robot Remote Control! Right now we've programmed the Remote Control to print out the map of the FC and then take in directions from the driver, but those directions aren't being sent to the robot.
We need you to interpret the directions and send them to the robot. Kiva Robots are incredibly expensive to make so you need to make sure they won't drive into any walls or obstacles when you make a move.

The RemoteControl class is responsible for running the program, interacting with the user, and moving the Kiva robot. 
We have provided you this class with some starter code in it. It contains logic to interact with the console by printing out the map of the floor, and taking in the string of directions from the user.

Project Goals
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
