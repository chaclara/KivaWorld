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
    int podPickedUp;
    KivaCommand command;
    FacingDirection directionFacing;
    FloorMapObject object;
    boolean isCarryingPod;
    boolean isSuccessfullyDropped;
    boolean isKivaOnFloor;
    
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
   
    
    private void dropPod(){
       
        if(this.kivaLocation.getX() == this.dropZoneLocation.getX() && this.kivaLocation.getY() == this.dropZoneLocation.getY() && this.isCarryingPod == true){
            this.isSuccessfullyDropped = true;
           
        }else{
            this.isSuccessfullyDropped = false;
        }
    }
    
    public boolean isSuccessfullyDropped(){
        
        return this.isSuccessfullyDropped;
    }
    
    private void takePod(){
        //logic to determine if pod picked up
       if(this.kivaLocation.getX() == this.podLocation.getX() && this.kivaLocation.getY() == this.podLocation.getY()){
            this.isCarryingPod = true;
            this.podPickedUp = 1;
       }else{
            this.isCarryingPod = false;
            throw new NoPodException("Can't take nonexistent pod from location " + this.kivaLocation);
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
 
            isKivaOnFloor();
            this.object = floor.getObjectAtLocation(this.kivaLocation);
            
       if(this.object == FloorMapObject.OBSTACLE){
           throw new IllegalMoveException("IllegalMoveException: Can't move onto an obstacle at " + this.kivaLocation);
            
           }
      
       //if(this.podPickedUp > 0){
           //if(this.object == FloorMapObject.POD){
         //throw new IllegalMoveException("IllegalMoveException: Can't take pod while Kiva is carrying a pod" + this.kivaLocation);
        
       // }
    
    }
    private void isKivaOnFloor(){
        if(this.kivaLocation.getX() < 0 || this.kivaLocation.getY() < 0){
            throw new IllegalMoveException("IllegalMoveException: Can't move outside of the Floor Map" + this.kivaLocation);
        }
        else if(this.kivaLocation.getX() > floor.getMaxColNum() || this.kivaLocation.getY() > floor.getMaxRowNum()){
             throw new IllegalMoveException("IllegalMoveException: Can't move outside of the Floor Map" + this.kivaLocation);
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
       
       takePod();
       this.podPickedUp = 1;
       if(command == KivaCommand.TAKE){
            takePod();
            this.podPickedUp = 1;
            
            
        }
        
       else if(command == KivaCommand.DROP){
            dropPod();
        }
    }
}
