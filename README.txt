READ_ME
Allyson Goodman, Sean Naegle, Tyler Shelton, James Holland, Zachariah Falgout

Animation Made by Beatiz Palacios
================================================================================
Starvation Evasion
================================================================================

================================================================================
How it is played
================================================================================
We have created a game called Starvation Evasion. It is a card based game where
the goal of the game is to keep the world from having a massive famine.
How the player is supposed to keep the world from having a famine is they
initiate policies as a politician, which affect other players and the rest of the world.

The game is designed to be played with 7 players. Each player will be in control
of a region. The 7 regions are California, Pacific Northwest & Mountain States,
Northern Plains States, Southern Plains & Delta States, Heartland States,
Southeastern States, and The Northern Crescent States. Each region will have it's
have a deck of cards *associated* with it. The decks will contain both generic
policy cards as well as policy cards that are specfic to a region.

How the game is played is a player chooses if he wants to play single player,
host a mutiplayer game, or join a multiplayer game. From there, he must choose
a region to be a senator for in the United States. The game will then begin
after all the players have chosen the region that they want to control.

For each turn a player has 5 minutes to choose the policies that they want to draft
for all the players to vote on. They have another 3 minutes to vote to then vote
on all of the policies that players have drafted. Each turn will take roughly 8
minutes. The game is played from 1980 to 2050, and each turn will be 3 years.
there will be roughly 24 turns, so the maximum time for a full game will be about
3 hours. This is unlikely though because players will be able to end turns early.

For the drafting phase in the game a player is then handed cards till he has a
full hand of 7 "policy cards" where he will be able to draft 2 them to be voted
on by all of the players, discard 3 of them, or do nothing. The player can also
do combinations of these types of plays with the cards.

During the voting phase a player a player is allowed to vote on the policies
that other players have created. A player can choose to abstain from a vote (not
vote), push the policy (vote for a policy), or vote against a policy.

Depending on what cards are played and passed for polices will determine how
farmers of a particular region will plant their crops in the players regions.
The player does not have direct control of how the the crops are planted, but
can influence it by the cards that he chooses to draft. Each region will
distribute food throughout their region, other regions in the United States,
and the rest of the world.

If the players are able to make it to 2050 without there being a mass famine
anywhere in the world, they will then compare which region has the most wealth.
The region with the most wealth will be the first pace winner, second most wealth
will be second place, and so on. It is a good idea to work with other players in
order to assure that everybody does not lose, but at the end of the game you will
have wanted to be better than the other players as well.

================================================================================
Setup
================================================================================
Notes:
  - Our group had used JavaFX and used scenes in order to keep track of the
    different types of actions that players would be using during their turn.

Main
  - Contains Game Controller (gameController)
  - Contains GUI Controller (guiController)
  - Starts with mp4 file of a opening credits like most games have


  - Contains Scene Switcher
    - Scenes are matched up with integers in to keep track of which scene we want
      - The Different scenes point to each other
        - The welcome scene will point to the Login Scene if it is multiplayer,
          game, or go straight to the Region Scene for a single player game
        - The login Screen will point to the game room scene if it is a multiplayer
          game
        - The Game Room Scene will point to the Drafting Scene
        - The Region Scene will be pointing to Drafting Scene if it is a single
          player game
        - The Drafting Scene will point to the Voting Scene
        - The Voting Scene will point to the Drafting Scene
          - The Voting Scene and Drafting Scene will become a cycle of scenes
            until the game ends
      - Scene:
         1: Welcome Scene (welcomeScene)
         2: Region Scene (regionScene)
         3: Draft Scene (cardDraftScene)
         4: Voting Scene (voting)
         5: Login Scene (loginScene)
         6: Game Room Scene (gameRoom)

  - Welcome Scene
    - Initial scene that is accessed when the game starts
      -Made by our good friend in the IFDM department, Beatriz.
    - Shows a small intro video to our game that just represents our game as a
      whole, showing images of food, and a person, and goals of the game
    - Displays a question boxes that will determine what type of game a player
      wants to play
        - New Single Player Game
        - New Multiplayer Game
        - Join Multiplayer Game

  - Login Scene
    - Is accessed if player picks Host, or Join Multiplayer game
    - Contains a text box where a player will enter his username
    - Contains a text box where a player will enter his password
    - Contains a text box that a player will input the IP address
      - If the IP address is entered wrong a try catch method is used
        message will appear saying that it was inputted wrong
    - Contains a text box that a player will input the port number
      - If the port number is entered wrong a try catch method is used
        message will appear saying that it was inputted wrong

  - Region Scene
    - Is accesed if Single Player is chosen from the Welcome Scene
    - Player picks the region that they want to use
    - Regions will become highlighted depending on what region the player has
      selected
    - There are seven Regions to pick from
      - California
      - Pacific Northwest & Mountain States
      - Northern Plains States
      - Southern Plains & Delta States
      - Heartland States
      - Southeastern States,
      - Northern Crescent States

  - Game Room Scene
    - Is accessed if Join Multiplayer Game, or New Multiplayer Game is picked from
      the Login Scene
    - A chat box is opened so players can communicate with each other
    - The player picks a region to play as similar to the region scene

  -Drafting Scene
    - World Visualization (Top Left)
      - A rotating world that displays different events, such as natural disaters,
        famines, wars, and other random events throughout the world
    - Map of the United States (Top Right)
      - Displays different regions
      - Clicking on the map will highlight regions
      - Highlighted region will display a pie chart (Middle Right) of that regions
        revenue from the crops it produces
    - Timer (Middle Middle)
      - Amount of time left in drafting phase
      - The timer will change color depending on how much time is left
    - Drafting Cards (Middle Middle)
      - The Two policy cards that player can choose to draft when ready
    - Hand
      - All of the policy cards that a player can choose from
      - It has 7 cards
      - It has a discard pile of cards
      - It has the deck of cards
      - Policy Cards
        - When mouse is over a card it scales the card, so the player will be able
          to read the policy that the card contains
        - When a card is chosen a text box appears so the player can give values
          of what they want to use with the policy being drafted
    - Crop Display (Bottom Bottom)
      - Has all the different and pictures of crops that a region can produce
      - When mouse is held over a picture it says what the crop is
      - When a crop is selected accesses a panel
        - Panel will contain
          - A scaled picture of the crop
          - Fun facts about the crops allowing for the player to see what
            crops to encourage farmers to grow for his region
          - It will have a line chart that displays the entire revenue for crop
            from every region

  - Voting Scene
    - Timer (Top Left)
      - Amount of time left in the voting phase     - The timer will change color depending on how much time is left
    - Graphs (Top Middle)
      - A pie chart that displays the players HDI for the nurished vs.
        malnurished people this turn (Left)
      - A line graph showing a regions total revenue (Middle)
      - A line graph showing the HDI for a players region over the entire game (Right)
    - Region Display (Middle Middle)
      - Displays the region
    - Cards below the regions
      - The transparent cards are cards that a player cannot  vote on
      - The highlighted cards are one that a player can vote on
        - When a player clicks a card a new window is displayed showing the policy card
        - The player has 3 options to vote with the card
          - Abstain (not vote)
            - If a player does not vote the card is automatically abstained
          - Push (vote for)
          - Vote against


== Server Setup
First, create a tsv file (tab delimited) with the usernames, passwords, and Region (if you want to lock it to those). There is an example of this in:
  data/config/example_password_file.tsv

To start the server you can do something like this:
  java -classpath out/production/StarvationEvasion/ starvationevasion.server.Server data/config/password_file.tsv  java -classpath out/production/StarvationEvasion/ starvationevasion.teamrocket.server.Client --environment

In IntelliJ just add this part to your Program Arguments:
  data/config/password_file.tsv  java -classpath out/production/StarvationEvasion/ starvationevasion.teamrocket.server.Client --environment

================================================================================
AI Cooperation
================================================================================
To team up with an AI to vote on the player's card, a chat message needs to be sent to the ai.
For cooperation, the AI is expecting at least one of the following words in the message, or the card
you are proposing to play, in order to decide if the AI will team up with the player

keyword list: "coop", "cooperate", "co-operate", "join", "together", "team"

If non of these words are in the message sent to the AI and random response will
be sent back to the player.
