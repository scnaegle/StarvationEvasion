package starvationevasion.teamrocket.main;

/**
 * Created by Tyler on 11/27/2015.
 */
public class ImproperInputException extends Throwable
{
  public ImproperInputException(String improperString)
  {
    System.out.println(improperString+" is a not a valid input");
  }
}
