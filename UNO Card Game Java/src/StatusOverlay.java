import java.awt.*;

//  Uno
//
//  StatusOverlay class:
//  Defines the overlay used for showing information status about a TurnDecisionAction.

public class StatusOverlay extends WndInterface implements TurnDecisionOverlayInterface {


    private String statusText; // Text to display showing the current status.

    private final Font statusFont = new Font("Arial", Font.BOLD, 20); // The font to render the status text with.


    private final Position centre; // Centre of the bounds to draw the text at.


    private double timeOut; // Timeout representing time remaining to complete the action.

    private String timeOutStr; // String showing the number representing the time remaining.



    //  Initialise the interface ready to show a status.
    //
    //  bounds: The bounds of the entire game area.

    public StatusOverlay(Rectangle bounds) {
        super(bounds);
        setEnabled(false);
        centre = bounds.getCentre();
        timeOutStr = "";
    }


    //       Updates the timeOut remaining.
    //
    //       deltaTime: Time since last update.

    @Override
    public void update(int deltaTime) {
        timeOut -= deltaTime / 1000.0;
        if(timeOut < 0) timeOut = 0;
        timeOutStr = (int)timeOut + "s";
    }



    //      Draws the text for the status and timer.
    //
    //      g: Reference to the Graphics object for rendering.

    @Override
    public void paint(Graphics g) {
        g.setFont(statusFont);
        int strWidth = g.getFontMetrics().stringWidth(statusText);
        g.setColor(new Color(184, 154, 143, 204));
        g.fillRect(centre.x-strWidth/2-10, centre.y-65, strWidth+20, 60);
        g.setColor(Color.BLACK);
        g.drawString(statusText, centre.x-strWidth/2, centre.y-20);
        strWidth = g.getFontMetrics().stringWidth(timeOutStr);
        g.drawString(timeOutStr, centre.x-strWidth/2-2, centre.y-40+2);
        g.setColor(timeOut < 6 ? Color.RED : Color.YELLOW);
        g.drawString(timeOutStr, centre.x-strWidth/2, centre.y-40);
    }

    //      Shows the overlay by generating a status depending on the currentAction.
    //
    //      currentAction: The action used to trigger this interface.

    @Override
    public void showOverlay(TurnActionFactory.TurnDecisionAction currentAction) {
        setEnabled(true);
        statusText = createContextString(currentAction);
        timeOut = CurrentGameInterface.getCurrentGame().getRuleSet().getDefaultTimeOut();
        timeOutStr = (int)timeOut + "s";
    }



    //      Checks whether the action is one the player has to do or if it is someone else,
    //      and constructs a message relevant to the current situation.
    //
    //      currentAction: The action to use for context.
    //      string: A String representing the status message to be displayed.

    private String createContextString(TurnActionFactory.TurnDecisionAction currentAction) {
        String playerName = CurrentGameInterface.getCurrentGame().getCurrentPlayer().getPlayerName();
        String result;
        switch(currentAction.flagName) {
            case "keepOrPlay" -> result = "choosing to Keep or Play.";
            case "wildColour" -> result = "choosing Wild Colour.";
            case "isChallenging" -> result = "choosing Response to +4.";
            case "otherPlayer" -> result = "choosing Other Player to Swap With.";
            default -> result = "thinking...";
        }
        if(CurrentGameInterface.getCurrentGame().getCurrentPlayer().getPlayerType() == Player.PlayerType.ThisPlayer) {
            return "You are " + result;
        }
        return playerName + " is " + result;
    }
}
