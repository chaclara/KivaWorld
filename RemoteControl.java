import edu.duke.FileResource;

/**
 * This is the class that controls Kiva's actions. Implement the <code>run()</code>
 * method to deliver the pod and avoid the obstacles.
 *
 */
import java.util.Arrays;
import edu.duke.Point;
public class RemoteControl {
    KeyboardResource keyboardResource;

    /**
     * Build a new RemoteControl.
     */
    public RemoteControl() {
        keyboardResource = new KeyboardResource();
    }

    /**
     * The controller that directs Kiva's activity. Prompts the user for the floor map
     * to load, displays the map, and asks the user for the commands for Kiva to execute.
     *
     * [Here's the method you'll execute from within BlueJ. It may or may not run successfully
     * as-is, but you'll definitely need to add more to complete the project.]
     */
    public void run() {
        System.out.println("Please select a map file.");
        FileResource fileResource = new FileResource();
        String inputMap = fileResource.asString();
        FloorMap floorMap = new FloorMap(inputMap);
        System.out.println(floorMap);
        Kiva kiva = new Kiva(floorMap);
        Point kivaLocation = kiva.getCurrentLocation();
        FacingDirection kivaDirection = kiva.getDirectionFacing();
        System.out.println("Kiva's location is: " + kivaLocation);
        System.out.println("Kiva's facing direction is: " + kivaDirection);
        System.out.println("Please enter the directions for the Kiva Robot to take.");
        String directions = keyboardResource.getLine();
        System.out.println("Directions that you typed in: " + directions);
        KivaCommand kivaCommands[] = convertToKivaCommands(directions);
        for(KivaCommand command: kivaCommands){
            kiva.move(command);
        }
        if(kiva.isSuccessfullyDropped() == true){
            System.out.println("Successfully picked up the pod and dropped it off. Thank you!");
        }else{
            System.out.println("I'm sorry. The Kiva Robot did not pick up the pod then drop it off in the right place.");
    }
}
        
public KivaCommand [] convertToKivaCommands(String userCommands){
        
    int size = userCommands.length();
    KivaCommand [] kivaCommands = new KivaCommand[size];
    KivaCommand [] actualCommands = new KivaCommand[size];
    kivaCommands = KivaCommand.values();
        
   for(int i = 0; i < size; i++){
       for(KivaCommand command : kivaCommands) {
           if(userCommands.charAt(i) == command.getDirectionKey()){
               actualCommands[i] = command;
                }else{
                    continue; 
           }
       }
           
           // throw new IllegalArgumentException("Character does not correspond to command!");
        
   }
       return actualCommands;
}
}

    

    
    


