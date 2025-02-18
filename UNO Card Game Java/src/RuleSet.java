//   Uno
//
//  RuleSet class:
//  Refines the class that stores the active rules used to determine how cards are mapped to action
//  and what actions are allowed.


public class RuleSet {


    public enum CardAction { Nothing, Plus2, Plus4, Wild, Skip, Reverse, Swap, PassAll} // Defines the different unique CardActions that can be mapped to faceValueIDs.



    public enum ScoreLimitType {OneRound, Score200, Score300, Score500, Unlimited} // Score limits that determine how long the game will run for.



    private final CardAction[] faceValueToActionMap; // 0 to 14 mapped with CardActions to represent each of the different Uno cards.

    private boolean canStackCards; // True if +2 and +4 cards can be stacked in response.

    private boolean drawnTillCanPlay; // True if when drawing from the deck for turn cards must continue to be drawn till a playable card is found.

    private final int defaultTimeOut; // The time player's have to make their choice during actions.

    private boolean onlyTwoPlayers; // Used to determine if Reverse becomes a skip when true.

    private boolean sevenZeroRule; // When true the 7 becomes a swap action, and 0 becomes a pass all action.

    private boolean noBluffingRule; // When no bluffing is true, the +4 can not be challenged.

    private boolean allowJumpInRule; // When true, players can jump in out of turn order with cards showing the same face value.

    private boolean forcedPlayRule; // When true, there is no keep or play choice, it is forced play.

    private ScoreLimitType scoreLimitType; // Stores the type of score limit to use for managing the end of game.


    public RuleSet() { // Initialises a default RuleSet.
        faceValueToActionMap = new CardAction[15];
        for(int i = 0; i <= 9; i++) {
            faceValueToActionMap[i] = CardAction.Nothing;
        }
        faceValueToActionMap[10] = CardAction.Plus2;
        faceValueToActionMap[11] = CardAction.Skip;
        faceValueToActionMap[12] = CardAction.Reverse;
        faceValueToActionMap[13] = CardAction.Plus4;
        faceValueToActionMap[14] = CardAction.Wild;
        defaultTimeOut = 25;
        setToDefaults();
    }

    public void setToDefaults() {
        setCanStackCards(true);
        setDrawnTillCanPlay(false);
        setTwoPlayers(false);
        setSevenZeroRule(false);
        setForcedPlayRule(false);
        setAllowJumpInRule(false);
        setNoBuffingRule(false);
        setScoreLimitType(ScoreLimitType.OneRound);
    }




    //      Looks up the CardAction that should be activated in relation to a played card.
    //
    //      faceValueID: The faceValue to look up in the action map.
    //      CardAction: The mapped CardAction associated with the specified faceValueID.
    public CardAction getActionForCard(int faceValueID) {
        return faceValueToActionMap[faceValueID];
    }





    //       Checks whether +2 and +4 cards can be played in response to other +2 and +4 cards.
    //
    //        boolean: True if +2 and +4 cards can be stacked in response.
    public boolean canStackCards() {
        return canStackCards;
    }




    //      Changes the state of card stacking.
    //
    //      canStackCards When true +2 and +4 cards can be stacked in response.
    public void setCanStackCards(boolean canStackCards) {
        this.canStackCards = canStackCards;
    }



    //   Checks whether cards must be drawn till one can be played.
    //
    //   boolean: True if when drawing from the deck for turn cards must continue to be drawn till a playable card is found.
    public boolean shouldDrawnTillCanPlay() {
        return drawnTillCanPlay;
    }




    //      Changes the state of drawing to a card can be played.
    //
    //      drawnTillCanPlay:  When true cards must be drawn until one can be played.
    public void setDrawnTillCanPlay(boolean drawnTillCanPlay) {
        this.drawnTillCanPlay = drawnTillCanPlay;
    }





   //   Gets the time in seconds that can be spent maximum for any individual action.
   //
   //   int: The time player's have to make their choice during actions.
    public int getDefaultTimeOut() {
        return defaultTimeOut;
    }




    //      Sets the state of whether there are only two players.
    //
    //      onlyTwoPlayers When true Reverse becomes a skip.
    public void setTwoPlayers(boolean onlyTwoPlayers) {
        this.onlyTwoPlayers = onlyTwoPlayers;
        faceValueToActionMap[12] = onlyTwoPlayers ? CardAction.Skip : CardAction.Reverse;
    }




   //       Gets the current state of the two players.
   //
   //       boolean: True if the only two player rules are active with Reverse set to a skip.
    public boolean getOnlyTwoPlayers() {
        return onlyTwoPlayers;
    }




    //      Changes the state of whether the seven-zero rule is active.
    //
    //      sevenZeroRule:  When true 7 is a swap action and 0 is a pass all action.
    public void setSevenZeroRule(boolean sevenZeroRule) {
        this.sevenZeroRule = sevenZeroRule;
        faceValueToActionMap[0] = sevenZeroRule ? CardAction.Swap : CardAction.Nothing;
        faceValueToActionMap[7] = sevenZeroRule ? CardAction.PassAll : CardAction.Nothing;
    }





    //    Checks whether the Seven-Zero rule is active.
    //
    //    boolean:   When true 7 is a swap action and 0 is a pass all action.
    public boolean getSevenZeroRule() {
        return sevenZeroRule;
    }




    //       Sets the No Buffing rule.
    //
    //       noBluffingRule: When no bluffing is true, the +4 can not be challenged.
    public void setNoBuffingRule(boolean noBluffingRule) {
        this.noBluffingRule = noBluffingRule;
    }





    //      Gets the current state of the no bluffing rule.
    //
    //      boolean: When no bluffing is true, the +4 can not be challenged.
    public boolean getNoBluffingRule() {
        return noBluffingRule;
    }






    //      Sets the current state of the allowing jump in rule.
    //
    //      allowJumpInRule: When true players can jump in with cards of the same face value.

    public void setAllowJumpInRule(boolean allowJumpInRule) {
        this.allowJumpInRule = allowJumpInRule;
    }





    //       Gets the current state of the jump in rule.
    //
    //       boolean: When true players can jump in with cards of the same face value.
    public boolean allowJumpInRule() {
        return allowJumpInRule;
    }





    //   Sets the current state of the forced play rule.
    //
    //   forcedPlayRule: When forced play is true, there should be no keep or play choice.
    public void setForcedPlayRule(boolean forcedPlayRule) {
        this.forcedPlayRule = forcedPlayRule;
    }






    //     Gets the current state of the forced play rule.
    //
    //     boolean: When forced play is true, there should be no keep or play choice.
    public boolean getForcedPlayRule() {
        return forcedPlayRule;
    }






    //      Sets the score limit to wind the rounds.
    //
    //      scoreLimitType: The new score limit to apply.
    public void setScoreLimitType(ScoreLimitType scoreLimitType) {
        this.scoreLimitType = scoreLimitType;
    }






    //       Gets the current score limit setting.
    //
    //       The current score limit to win all the rounds.
    public ScoreLimitType getScoreLimitType() {
        return scoreLimitType;
    }
}
