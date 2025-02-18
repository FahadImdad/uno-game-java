import java.awt.*;

//  Uno
//
//  UnoButton class:
//  A special variation of button that appears differently for the Uno calling.
//  Pressing the button is intended when a player reaches 2 or less cards.

public class UnoButton extends WndInterface implements GeneralOverlayInterface {


    public static final int WIDTH = 80; // Width of the button.


    public static final int HEIGHT = 60; // Height of the button.


    private boolean isHovered; // Current hover status of the button.


    public final Player bottomPlayer; // Reference to the BottomPlayer.


    public boolean isActive; // When isActive is active the button can be interacted with and is visible.



    //      Initialises the UnoButton.     
    //
    //      position: Position to place the Uno button.
    public UnoButton(Position position) {
        super(new Rectangle(position, WIDTH, HEIGHT));
        isHovered = false;
        setEnabled(true);
        bottomPlayer = CurrentGameInterface.getCurrentGame().getBottomPlayer();
        isActive = false;
    }


    @Override
    public void showOverlay() {
        setEnabled(true);
    } // Shows the overlay.






    //      deltaTime: Time since last update.
    @Override
    public void update(int deltaTime) { //      Enables the button when it should be available.
        isActive = bottomPlayer.getUnoState() == Player.UNOState.NotSafe
                || (bottomPlayer.getUnoState() == Player.UNOState.Safe
                && CurrentGameInterface.getCurrentGame().getCurrentPlayer() == bottomPlayer
                && bottomPlayer.getHand().size() == 2);
    }





    //       g: Reference to the Graphics object for rendering.
    @Override
    public void paint(Graphics g) { // Draws the Uno button with an expanding oval on hover with the UNO text in the middle.
        if(!isActive) return;

        drawButtonBackground(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        int strWidth = g.getFontMetrics().stringWidth("UNO");
        g.drawString("UNO", bounds.position.x+bounds.width/2-strWidth/2-2, bounds.position.y+bounds.height/2+2+10);
        g.setColor(new Color(226, 173, 67));
        g.drawString("UNO", bounds.position.x+bounds.width/2-strWidth/2, bounds.position.y+bounds.height/2+10);
    }


    public void drawButtonBackground(Graphics g) {
        g.setColor(new Color(147, 44, 44));
        int expandAmount = isHovered ? 20 : 0;
        g.fillOval(bounds.position.x-expandAmount/2, bounds.position.y-expandAmount/2,
                bounds.width+expandAmount,bounds.height+expandAmount);
    }



    //      Updates the hover state of the Uno button.
    //
    //      mousePosition: Position of the mouse during this movement.

    @Override
    public void handleMouseMove(Position mousePosition) {
        isHovered = bounds.isPositionInside(mousePosition);
    }





    //      When the button is available and is clicked the player is flagged as having called and the called signal is flashed.
    //
    //      mousePosition Position of the mouse cursor during the press.
    //      isLeft:        If true, the mouse button is left, otherwise is right.
    @Override
    public void handleMousePress(Position mousePosition, boolean isLeft) {
        if(isActive && bounds.isPositionInside(mousePosition)) {
            bottomPlayer.setUnoState(Player.UNOState.Called);
            CurrentGameInterface.getCurrentGame().showGeneralOverlay("UNOCalled"+bottomPlayer.getPlayerID());
        }
    }
}
