/**
 * This class should hold the state and logic of a robot: where it is, which way it's facing , whether it's carrying a pod right now, and whether it has successfully dropped the pod.
 * A Kiva always starts facing UP(one of our provided FacingDirections). not carrying a pod and not having successfully dropped the pod.
 * 
 * @author (Clara Chandler) 
 * @version (09/16/2021)
 */
import edu.duke.Point;

public class Kiva {
    /**
     * Variables to be used throughout the Kiva class. Each variable is used to update the values of the Kiva robot and test if the robot is successful at picking up and dropping the pod.
     * The motorLifetime variable is use to make sure the Kiva robot doesn't go over the 20,000 hour motor life it has.
     */
    FloorMap floor;
    Point kivaLocation;
    Point podLocation;
    Point dropZoneLocation;
    Point kivaNewLocation;
    KivaCommand command;
    FacingDirection directionFacing;
    boolean isCarryingPod;
    boolean isSuccessfullyDropped;
    boolean isOnDropZone;
    boolean isOnPodLocation;
    int podPickedUp;
    long motorLifetime;
    
    /**
     * Kiva constructor that instantiates the Kiva with the default Facing Direction and Kiva Location
     * based on the Kiva's location on the given map.
     * @param floor This is the FloorMap provided. 
     *              Should contain the location of 
     *              the Kiva, pod, dropzone and obstacles.
     */
    public Kiva (FloorMap floor){
       
        this.Kivaconstructor(floor, floor.getInitialKivaLocation());
        this.directionFacing  = FacingDirection.UP;
    }
    
    /**
     * Kiva constructor that instantiates the Kiva with the default Facing Direction.
     * @param point This will set the location of
     *              of the Kiva to the provided point.
     * @param floor This is the FloorMap provided. 
     *              Should contain the location of 
     *              the Kiva, pod, dropzone and obstacles.
     */
    public Kiva (FloorMap floor, Point point){
  
        this.Kivaconstructor(floor, point);
        /* Declare a variable for the direction enum UP */
        this.directionFacing  = FacingDirection.UP;
        
    }
    /**
     * Method used to help overload the constructor. 
     * The method sets all of the default variables 
     * for a new Kiva when instantiated in one of the constructors.
     */
    private void Kivaconstructor(FloorMap floor, Point point){
        this.floor = floor;
        this.kivaLocation = point;
        this.podLocation = floor.getPodLocation();
        this.dropZoneLocation = floor.getDropZoneLocation();
        this.isCarryingPod = false;
        this.isSuccessfullyDropped = false;
    }
    
    /**
     * Gets the Facing Direction of the Kiva.
     * @return this Facing Direction.
     */
    public FacingDirection getDirectionFacing(){
        
        return this.directionFacing;
    }
    
    /**
     * Gets the Location of the Kiva.
     * @return this Location.
     */
    public Point getCurrentLocation(){
        
        return this.kivaLocation;
        
    }
    
    /**
     * Checks to make sure the kiva is on the drop zone and successfully carrying a pod.
     * If no pod is being carried, throws an exception when trying to drop on the zone.
     */
    private void isOnDropZone(){
        if(this.kivaLocation.getX() == this.dropZoneLocation.getX() && this.kivaLocation.getY() == this.dropZoneLocation.getY() && this.isCarryingPod == true){
            this.isOnDropZone = true;
           
        }else if(this.isCarryingPod == false){
            throw new IllegalMoveException("No pod to drop" + this.kivaLocation);
        
        }
    }
    
    /**
     * If the Kiva is on the drop zone, this drops the pod and resets isCarryingPod.
     * This also resets the podPickedUp variable. The podPickedUp variable is used to
     * keep count of the kiva carrying a robot since the robot can only carry one at a time.
     * If not on drop zone then this throws an exception.
     */
    private void dropPod(){
        isOnDropZone();
        if(isOnDropZone){
            this.isSuccessfullyDropped = true;
            this.isCarryingPod = false;
            podPickedUp = 0;
        }else{
           throw new IllegalDropZoneException("Can't drop pod in this location " + this.kivaLocation);
        
      
        }
        
    }
    
    /**
     * Gets the status of if the pod was dropped succesfully
     * on the drop zone.
     * @return if the pod is succesfully dropped.
     */
    public boolean isSuccessfullyDropped(){
        
        return this.isSuccessfullyDropped;
    }
    
    /**
     * Checks to make sure the Kiva robot is on the location of the pod.
     */
    private void isOnPodLocation(){
        if(this.kivaLocation.getX() == this.podLocation.getX() && this.kivaLocation.getY() == this.podLocation.getY()){
            this.isOnPodLocation = true;
            
        }
    
    }
    
    /**
     * If the Kiva is on the correct location this essentially takes the pod.
     * If there is no pod available this throws an exception.
     */
    private void takePod(){
        //logic to determine if pod picked up
        isOnPodLocation();
       if(isOnPodLocation){
            this.isCarryingPod = true;
            podPickedUp = 1;
            
       }else{
           throw new NoPodException("No pod to TAKE from location " + this.kivaLocation + "!");
        
      
        }
        
       
    }
    
    /**
     * Gets the status of if the Kiva robot is carrying the pod.
     * @return if the kiva is carrying a pod.
     */
    public boolean isCarryingPod(){
      
        return this.isCarryingPod;
        
    }
    
    /**
     * This sets the Kiva robot's facing direction based on the command 
     * provided in the move method down below. This will allow the Kiva
     * to turn left appropriately.
     */
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
    
    /**
     * This sets the Kiva robot's facing direction based on the command 
     * provided in the move method down below. This will allow the Kiva
     * to turn right appropriately.
     */
    
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
    
    /**
     * This first checks to make sure Kiva is inside of the map with the
     * isKivaOnFloor method. If it is then this moves the Kiva robot forward
     * and sets the new kiva location. If there is an obstacle in the new
     * location or if the kiva is already carrying a pod an exception is thrown.
     */
    private void moveForward(){
       isKivaOnFloor();
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
 
       FloorMapObject object;
       object = floor.getObjectAtLocation(this.kivaLocation);
            
       if(object == FloorMapObject.OBSTACLE){
           throw new IllegalMoveException("IllegalMoveException: Can't move onto an obstacle at " + this.kivaLocation);
            
           }
      
       if(this.podPickedUp > 0){
           if(object == FloorMapObject.POD){
               throw new IllegalMoveException("IllegalMoveException: Can't take pod while Kiva is carrying a pod" + this.kivaLocation);
        
           }
       }
    
    }
    
    /**
     * This checks to make sure the kiva is inside of the bounds of the Floor Map.
     */
    private void isKivaOnFloor(){
        if(this.kivaLocation.getX() < 0 || this.kivaLocation.getY() < 0){
            throw new IllegalMoveException("IllegalMoveException: Can't move outside of the Floor Map" + this.kivaLocation);
        }
        else if(this.kivaLocation.getX() > floor.getMaxColNum() || this.kivaLocation.getY() > floor.getMaxRowNum()){
             throw new IllegalMoveException("IllegalMoveException: Can't move outside of the Floor Map" + this.kivaLocation);
        }
        
    }
    
    /**
     * This increments the motor lifetime each time the kiva needs to
     * move forward or turn left or right. If the motor lifetime exceeds
     * 20,000 hours an exception is thrown.
     */
    public void incrementMotorLifetime(){
        this.motorLifetime += 1000;
    
        if(motorLifetime == 7.2e10){
            throw new IllegalMoveException ("Motor needs to be replaced or retired");
        }
    }
    
    /**
     * Gets the motor lifetime of the kiva robot.
     * @return this motor lifetime.
     */
    public long getMotorLifetime(){
        
        return this.motorLifetime;
    }
    
    /**
     * This takes in the command from the user and either moves,
     * takes or drops the pod or turns the kiva accordingly. After
     * each command that uses motor lifetime, motor lifetime is increased.
     */
    public void move(KivaCommand command){
        
       if(command == KivaCommand.FORWARD){
           moveForward();
           incrementMotorLifetime();
        }
        
       else if(command == KivaCommand.LEFT){
           turnLeft();
           incrementMotorLifetime();
        }
   
       else if(command == KivaCommand.RIGHT){
           turnRight();
           incrementMotorLifetime();
        }
      
       if(command == KivaCommand.TAKE){
           
            takePod();
           
            
        }
        
       else if(command == KivaCommand.DROP){
            dropPod();
        }
    }
}
