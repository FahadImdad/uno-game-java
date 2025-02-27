import java.awt.*;

//  Uno
//
//  WildColourSelectorOverlay class:
//  Defines the overlay for choosing colours for wild and +4 cards.

public class WildColourSelectorOverlay extends WndInterface implements TurnDecisionOverlayInterface {


    private int hoveredRegion, hoverX, hoverY; // Tracking of the area hovered with the current grid position and region number.

    private TurnActionFactory.TurnDecisionAction controllingTurnAction; // Reference to the TurnAction that triggered the display of this overlay.



    //       Initialises the overlay using the specified region.
    //
    //       position: Position to place the overlay.
    //       width: Width of the overlay.
    //       height: Height of the overlay.
    public WildColourSelectorOverlay(Position position, int width, int height) {
        super(new Rectangle(position, width, height));
        setEnabled(false);
    }


    @Override
    public void update(int deltaTime) {  // deltaTime: Time since last update.

        // Does nothing.

    }


    // Draws a selection interface to choose a colour.

    public void paint(Graphics g) { // g : Reference to the Graphics object for rendering.
        g.setColor(Color.BLACK);
        g.fillRect(bounds.position.x-20, bounds.position.y-40, bounds.width+40, bounds.height+60);

        // Red, blue, green, yellow segments for any wild card in the middle.
        for(int i = 0; i < 4; i++) {
            g.setColor(Card.getColourByID(i));

            if(i == hoveredRegion) {

                int offsetX = (hoverX == 0) ? -1 : 1;
                int offsetY = (hoverY == 0) ? -1 : 1;

                g.fillArc(bounds.position.x+offsetX*10, bounds.position.y+offsetY*10,
                        bounds.width, bounds.height, 270 + 90 * i, 90);
            }

            else {
                g.fillArc(bounds.position.x, bounds.position.y, bounds.width, bounds.height,
                        270 + 90 * i, 90);
            }
        }
        g.setColor(Color.WHITE);
        g.drawRect(bounds.position.x-20, bounds.position.y-40, bounds.width+40, bounds.height+60);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        String message = "Choose Colour";
        int strWidth = g.getFontMetrics().stringWidth(message);
        g.drawString(message, bounds.position.x+bounds.width/2-strWidth/2, bounds.position.y-5);
    }

    //      Updates the currently hovered region in the colour wheel.
    //
    //      mousePosition: Position of the mouse during this movement.

    @Override
    public void handleMouseMove(Position mousePosition) {
        hoveredRegion = -1;
        if(bounds.isPositionInside(mousePosition)) {
            hoverX = (mousePosition.x - bounds.position.x) / (bounds.width/2);
            hoverY = (mousePosition.y - bounds.position.y) / (bounds.height/2);
            if(hoverX == 0 && hoverY == 0) hoveredRegion = 2;
            else if(hoverX == 1 && hoverY == 0) hoveredRegion = 1;
            else if(hoverX == 1 && hoverY == 1) hoveredRegion = 0;
            else if(hoverX == 0 && hoverY == 1) hoveredRegion = 3;
        }
    }

    /**
     * Checks if the region clicked is valid for a colour selection and
     * applies the colour as an action if appropriate.
     *
     *   mousePosition Position of the mouse cursor during the press.
     *  isLeft        If true, the mouse button is left, otherwise is right.
     */
    @Override
    public void handleMousePress(Position mousePosition, boolean isLeft) {
        handleMouseMove(mousePosition);
        if(hoveredRegion != -1) {
            controllingTurnAction.injectProperty("colourID", hoveredRegion);
            controllingTurnAction.injectFlagProperty(1);
            setEnabled(false);
        }
    }

    /**
     * Shows the overlay.
     *
     * currentAction The action used to trigger this interface.
     */
    @Override
    public void showOverlay(TurnActionFactory.TurnDecisionAction currentAction) {
        this.controllingTurnAction = currentAction;
        setEnabled(true);
    }
}
