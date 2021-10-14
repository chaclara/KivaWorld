/**
 * This class should hold the state and logic of a robot: where it is, which way it's facing , whether it's carrying a pod right now, and whether it has successfully dropped the pod.
 * A Kiva always starts facing UP(one of our provided FacingDirections). not carrying a pod and not having successfully dropped the pod. the move() method prerforms the logic for 
 * any KivaCommand, and the isSuccessfullDrop() method indicates whether the previous command dropped the pod on the drop zone. 
 * 
 * 
 * @author (Clara Chandler) 
 * @version (09/16/2020)
 */
import edu.duke.Point;

public class Kiva {
    
    FloorMap floor;
    Point kivaLocation;
    Point podLocation;
    Point dropZoneLocation;
    KivaCommand command;
    FacingDirection directionFacing;
    boolean isCarryingPod;
    boolean isSuccessfullyDropped;
    
    public Kiva (FloorMap floor){
       
        this.Kivaconstructor(floor, floor.getInitialKivaLocation());
        this.directionFacing  = FacingDirection.UP;
    }
    
    public Kiva (FloorMap floor, Point point){
  
        this.Kivaconstructor(floor, point);
        /* Declare a variable for the direction enum UP */
        this.directionFacing  = FacingDirection.UP;
        
    }
    
    private void Kivaconstructor(FloorMap floor, Point point){
        this.floor = floor;
        this.kivaLocation = point;
        this.podLocation = floor.getPodLocation();
        this.dropZoneLocation = floor.getDropZoneLocation();
        this.isCarryingPod = false;
        this.isSuccessfullyDropped = false;
    }
    public FacingDirection getDirectionFacing(){
        
        return this.directionFacing;
    }
    
    public Point getCurrentLocation(){
        
        return this.kivaLocation;
        
    }
    private void isSuccessfulDropped(){
       
        if(this.podLocation == this.dropZoneLocation){
            this.isSuccessfullyDropped = true;
        }
    }
    public boolean isSuccessfullyDropped(){
        return this.isSuccessfullyDropped;
    }
    
    private void isPodPickedUp(){
        //logic to determine if pod picked up
        
        
        if(this.kivaLocation == this.podLocation){
            this.isCarryingPod = true;     
        }
       
    }
    public boolean isCarryingPod(){
       
        return this.isCarryingPod;
        
    }
    
    private void turnLeft(){
      
        if(this.directionFacing == FacingDirection.UP){
           this.directionFacing = FacingDirection.LEFT;
        }
    
        else if(this.directionFacing == FacingDirection.LEFT){
        
        this.directionFacing = FacingDirection.DOWN;
        }
        else if(this.directionFacing == FacingDirection.DOWN){
        this.directionFacing = FacingDirection.RIGHT;
        }
        else if(this.directionFacing == FacingDirection.RIGHT){
        this.directionFacing = FacingDirection.UP;
        }
    }
    
    
    private void turnRight(){
        
        if(this.directionFacing == FacingDirection.UP){
           this.directionFacing = FacingDirection.RIGHT;
        }
    
        else if(this.directionFacing == FacingDirection.RIGHT){
        
        this.directionFacing = FacingDirection.DOWN;
        }
        else if(this.directionFacing == FacingDirection.DOWN){
        this.directionFacing = FacingDirection.LEFT;
        }
        else if(this.directionFacing == FacingDirection.LEFT){
        this.directionFacing = FacingDirection.UP;
        }
       
        
    }
    
    private void moveForward(){
        int x;
        int y;
       if(this.directionFacing == FacingDirection.UP){
        x = kivaLocation.getX();
        y = kivaLocation.getY() - 1;
        this.kivaLocation = new Point(x, y);
       }
       else if(this.directionFacing == FacingDirection.LEFT){
        x = kivaLocation.getX() - 1;
        y = kivaLocation.getY();
        this.kivaLocation = new Point(x, y);
       }
       else if(this.directionFacing == FacingDirection.DOWN){
        x = kivaLocation.getX();
        y = kivaLocation.getY() + 1;
        this.kivaLocation = new Point(x, y);    
       }
       else if(this.directionFacing == FacingDirection.RIGHT){
        x = kivaLocation.getX() + 1;
        y = kivaLocation.getY();
        this.kivaLocation = new Point(x, y);    
       }
    }
        
    
   
    public void move(KivaCommand command){
        
        if(command == KivaCommand.FORWARD){
         moveForward();
         
        }
        
        else if(command == KivaCommand.LEFT){
         turnLeft();
        
        }
   
        else if(command == KivaCommand.RIGHT){
         turnRight();
        
        }
    }
}
