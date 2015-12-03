package starvationevasion.teamrocket.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import starvationevasion.common.EnumPolicy;
import starvationevasion.common.PolicyCard;

import java.util.HashMap;
import java.util.Map;

/**
 * Maps every card image to a playable policy card.
 */
public class CardImage
{
  private static Map<EnumPolicy,ImageView> cards = new HashMap<>();

  static
  {
    ImageView cleanRiverIncentive = new ImageView( new Image(CardImage.class.getResourceAsStream("/images/cleanriver.png")));
    cards.put(EnumPolicy.Clean_River_Incentive, cleanRiverIncentive);

    ImageView covertIntel = new ImageView(new Image(CardImage.class.getResourceAsStream("/images/covertintel.png")));
    cards.put(EnumPolicy.Covert_Intelligence, covertIntel);

    ImageView educateTheWomen = new ImageView(new Image(CardImage.class.getResourceAsStream("/images/educatewomen.png")));
    cards.put(EnumPolicy.Educate_the_Women_Campaign, educateTheWomen);

    ImageView ethanolTax = new ImageView(new Image(CardImage.class.getResourceAsStream("/images/ethanoltax.png")));
    cards.put(EnumPolicy.Ethanol_Tax_Credit_Change, ethanolTax);

    ImageView feedSubsidy = new ImageView(new Image(CardImage.class.getResourceAsStream("/images/feedsubsidy.png")));
    cards.put(EnumPolicy.Fertilizer_Subsidy, feedSubsidy);

    ImageView foodRelief = new ImageView(new Image(CardImage.class.getResourceAsStream("/images/foodrelief.png")));
    cards.put(EnumPolicy.International_Food_Relief_Program, foodRelief);

    ImageView foreignAid = new ImageView(new Image(CardImage.class.getResourceAsStream("/images/foreignaid.png")));
    cards.put(EnumPolicy.Foreign_Aid_for_Farm_Infrastructure, foreignAid);

    ImageView GMO = new ImageView(new Image(CardImage.class.getResourceAsStream("/images/gmo.png")));
    cards.put(EnumPolicy.GMO_Seed_Insect_Resistance_Research, GMO);

    ImageView irrigation = new ImageView(new Image(CardImage.class.getResourceAsStream("/images/irrigation.png")));
    cards.put(EnumPolicy.Efficient_Irrigation_Incentive, irrigation);

    ImageView loan = new ImageView(new Image(CardImage.class.getResourceAsStream("/images/loan.png")));
    cards.put(EnumPolicy.Loan, loan);

    ImageView myPlate = new ImageView(new Image(CardImage.class.getResourceAsStream("/images/myplate.png")));
    cards.put(EnumPolicy.MyPlate_Promotion_Campaign, myPlate);
  }


  public static ImageView getCardImage(EnumPolicy policy)
  {
    return cards.get(policy);
  }
}
