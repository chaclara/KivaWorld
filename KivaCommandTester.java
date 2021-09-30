
/**
 * This class is used to test the KivaCommand enum.
 * 
 * @author Clara Chandler
 * @version 09/16/2020
 */
public class KivaCommandTester {
    public void testForward(){
    //Create a new KivaCommand object Forward. 
        KivaCommand command = KivaCommand.FORWARD;
        //Print the command and direction key associate with "Forward"
        System.out.println(command);
        System.out.println(command.getDirectionKey());
    }
    public void testTurnRight(){
        KivaCommand command = KivaCommand.RIGHT;
        System.out.println(command);
        System.out.println(command.getDirectionKey());
    }
    public void testTurnLeft(){
        KivaCommand command = KivaCommand.LEFT;
        System.out.println(command);
        System.out.println(command.getDirectionKey());
    }
    public void testTake(){
        KivaCommand command = KivaCommand.TAKE;
        System.out.println(command);
        System.out.println(command.getDirectionKey());
    }
    public void testDrop(){
        KivaCommand command = KivaCommand.DROP;
        System.out.println(command);
        System.out.println(command.getDirectionKey());
    }
public final void main(String[] args){
testForward();
testTurnRight();
testTurnLeft();
testTake();
testDrop();
}
}
