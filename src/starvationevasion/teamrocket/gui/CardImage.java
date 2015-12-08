package starvationevasion.teamrocket.gui;

import javafx.scene.image.Image;
import starvationevasion.common.EnumPolicy;

import java.util.HashMap;
import java.util.Map;

/**
 * Maps every card image to a playable EnumPolicy.
 */
public class CardImage
{
  private static Map<EnumPolicy, Image> cards = new HashMap<>();

  static
  {
    Image cleanRiverIncentive = new Image(CardImage.class.getResourceAsStream("/images/cleanriver.png"));
    cards.put(EnumPolicy.Clean_River_Incentive, cleanRiverIncentive);

    Image covertIntel = new Image(CardImage.class.getResourceAsStream("/images/covertintel.png"));
    cards.put(EnumPolicy.Covert_Intelligence, covertIntel);

    Image educateTheWomen = new Image(CardImage.class.getResourceAsStream("/images/educatewomen.png"));
    cards.put(EnumPolicy.Educate_the_Women_Campaign, educateTheWomen);

    Image ethanolTax = new Image(CardImage.class.getResourceAsStream("/images/ethanoltax.png"));
    cards.put(EnumPolicy.Ethanol_Tax_Credit_Change, ethanolTax);

    Image feedSubsidy = new Image(CardImage.class.getResourceAsStream("/images/feedsubsidy.png"));
    cards.put(EnumPolicy.Fertilizer_Subsidy, feedSubsidy);

    Image foodRelief = new Image(CardImage.class.getResourceAsStream("/images/foodrelief.png"));
    cards.put(EnumPolicy.International_Food_Relief_Program, foodRelief);

    Image foreignAid = new Image(CardImage.class.getResourceAsStream("/images/foreignaid.png"));
    cards.put(EnumPolicy.Foreign_Aid_for_Farm_Infrastructure, foreignAid);

    Image GMO = new Image(CardImage.class.getResourceAsStream("/images/gmo.png"));
    cards.put(EnumPolicy.GMO_Seed_Insect_Resistance_Research, GMO);

    Image irrigation = new Image(CardImage.class.getResourceAsStream("/images/irrigation.png"));
    cards.put(EnumPolicy.Efficient_Irrigation_Incentive, irrigation);

    Image loan = new Image(CardImage.class.getResourceAsStream("/images/loan.png"));
    cards.put(EnumPolicy.Loan, loan);

    Image myPlate = new Image(CardImage.class.getResourceAsStream("/images/myplate.png"));
    cards.put(EnumPolicy.MyPlate_Promotion_Campaign, myPlate);
  }

  /**
   * Gets the correct card image that relates to that specific policy.
   *
   * @param policy Known policy.
   * @return Image related to policy.
   */
  public static Image getCardImage(EnumPolicy policy)
  {
    return cards.get(policy);
  }
}
