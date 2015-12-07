package starvationevasion.teamrocket.AI;

import starvationevasion.common.EnumPolicy;
import starvationevasion.common.EnumRegion;
import starvationevasion.common.PolicyCard;
import starvationevasion.common.messages.ClientChatMessage;
import starvationevasion.common.messages.ServerChatMessage;
import starvationevasion.common.messages.VoteStatus;
import starvationevasion.teamrocket.main.GameController;
import starvationevasion.teamrocket.models.Player;

import java.util.Random;

//TODO: AI CHAT!!!!! ASAP
public class AI extends Player
{
  private AIChatResponse chat;
  /*Game info*/
  private final int NUM_US_REGIONS = EnumRegion.US_REGIONS.length;
  private PlayerRecord[] records;
  private Random generator;
  private int actionsPerformed = 2; //decrease when actions are done during drafting phase
  private EnumPolicy[] discardedCards; //get the policies that were discarded
  private EnumRegion cooperatingRegion;
  /**
   * Makes an AI for a region with a specific level while giving it a hand to use.
   *
   * @param controlledRegion The region the AI is supposed to control
   * @param aiLevel The difficulty level that the AI plays at
   * @param gameController The game controller that talks to the ai through
   */
  public AI(EnumRegion controlledRegion, EnumAITypes aiLevel, GameController gameController)
  {
    super(controlledRegion, aiLevel, gameController);
    generator = new Random();

    setup();
  }

  /**
   * Sets up the array needed to keep track of records
   * for each player.
   */
  private void setup()
  {
    records = new PlayerRecord[NUM_US_REGIONS];

    for(int i = 0; i < NUM_US_REGIONS; i++)
    {
      PlayerRecord record = new PlayerRecord();
      records[i] = record;
    }

    chat = new AIChatResponse(records, this, generator);
  }

  /**
   * Removes all cards that the AI has been selected for discard
   * from the hand of the player
   * @param discardCardsPosition the position of cards being discarded
   */
  private void removeDiscardedCards(int[] discardCardsPosition) {
    EnumPolicy[] hand = getHand();
    discardedCards = new EnumPolicy[discardCardsPosition.length];
    int index = 0;
    for (int i : discardCardsPosition)
    {
      discardedCards[index] = hand[i];
      index++;
      hand[i] = null;
    }
    setHand(hand);

  }

  /**
   * AI has a 1 in 5 chance to discard
   * AI picks random choice from 0 to 3 cards to discard
   * @return True if AI chooses to discard cards, False if it doesn't
   */
  public boolean discardCards()
  {
    if(generator.nextInt(5) == 0 && actionsPerformed > 0)
    {
      actionsPerformed--;
      int numCards = generator.nextInt(4);
      if(numCards == 0) numCards++;
      removeDiscardedCards(AI.discardCards(numCards,getHandCards(),generator));
      return true;
    }
    return false;
  }

  /**
   * Sets the targets of cards that are going to be played
   * if those cards need a target selected
   * @param cards array of cards being played
   */
  private void setCardTargets(PolicyCard[] cards)
  {
    for(PolicyCard card : cards)
    {
      AI.setCardTargets(generator, card);
    }
  }

  /**
   * Sets which region the AI is cooperating with
   * when that region through the chat asked for
   * cooperating
   * @param region that AI is working together with
   */
  public void setCooperatingRegion(EnumRegion region){cooperatingRegion = region;}

  /***** Override player voting updating and chat receiving ******/
  @Override
  public synchronized void updateVoteStatus(VoteStatus voteStatus)
  {
    for(PolicyCard card : voteStatus.currentCards)
    {
      EnumRegion owner = card.getOwner();

      if(card.didVoteYes(owner))records[owner.ordinal()].setPlayerCoop(true);
      else records[owner.ordinal()].setPlayerCoop(false);
    }
  }

  @Override
  public synchronized void receiveChatMessage(ServerChatMessage message)
  {
    super.receiveChatMessage(message);
    chat.getMessage(message);
  }

  public ClientChatMessage sendMessage()
  {
    return chat.getResponse();
  }

  /********** Interface Methods *********/
  @Override
  public PolicyCard[] getDraftedCards()
  {
    if(actionsPerformed > 0)
    {
      PolicyCard[] cards = AI.selectCards(getHandCards(),generator, actionsPerformed);
      setCardTargets(cards);
      actionsPerformed = 2;
      return cards;
    }
    return null;
  }

  @Override
  public int vote(EnumPolicy card, EnumRegion cardPlayedRegion) {
    if(cooperatingRegion != null && cooperatingRegion.equals(cardPlayedRegion))
    {
      cooperatingRegion = null;
      return 1;
    }

    records[cardPlayedRegion.ordinal()].setPendingVoteCard(PolicyCard.create(cardPlayedRegion,card));
    return AI.vote(records[cardPlayedRegion.ordinal()], generator, ENUM_REGION);
  }
}
