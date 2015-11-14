package starvationevasion.teamrocket.AI;

import starvationevasion.common.EnumRegion;
import starvationevasion.teamrocket.PlayerInterface;
import starvationevasion.teamrocket.models.Card;


public class AI implements PlayerInterface
{
  private EnumRegion region;
  private EnumAITypes level;
  private PlayerRecord[] records = new PlayerRecord[8];

  public AI(EnumRegion controlledRegion, EnumAITypes aiLevel)
  {
    region = controlledRegion;
    level = aiLevel;
  }

  @Override
  public String getLogIn() {
    return null;
  }
  @Override
  public EnumRegion getRegion(){return region;}

  @Override
  public Card[] playSelectedCards() {
    return new Card[0];
  }

  @Override
  public int vote(Card card, EnumRegion cardPlayedRegion) {
    return 0;
  }

  @Override
  public void discardCard(int cardNum) {

  }

  @Override
  public void drawCards(int numOfDraws) {

  }

  @Override
  public void shuffleDeck() {

  }


  public void checkRecords(int playerID)
  {

  }

  private class PlayerRecord
  {

  }

}
