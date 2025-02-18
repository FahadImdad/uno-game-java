import java.awt.*;

//   Uno
//
//  ChallengeSuccessOverlay class:
//  Displays a short time flashing cross to show the challenge was failed.
public class ChallengeFailedOverlay extends WndInterface implements GeneralOverlayInterface {

    private double displayTimer; // Timer till the overlay is hidden again.

    private final int[] polyXCoords; // X Coordinates to make the graphic appear.

    private final int[] polyYCoords; // Y Coordinates to make the graphic appear.



//   Initialise the interface with bounds and makes it ready to be enabled.
//
//   bounds: Region where the object is shown.
    public ChallengeFailedOverlay(Rectangle bounds) {
        super(bounds);
        setEnabled(false);

        int x = bounds.position.x;
        int y = bounds.position.y;
        int sValX = bounds.width / 8;
        int sValY = bounds.height / 8;

        polyXCoords = new int[] {x+sValX,x+2*sValX,x+4*sValX,x+6*sValX,x+7*sValX,
                x+5*sValX,x+7*sValX,x+6*sValX,x+4*sValX,x+2*sValX,x+sValX,x+3*sValX}; // coordinates of polygon

        polyYCoords = new int[] {y+2*sValY,y+sValY,y+3*sValY,y+sValY,y+2*sValY,y+4*sValY,
                y+6*sValY,y+7*sValY,y+5*sValY,y+7*sValY,y+6*sValY,y+4*sValY}; // coordinates of polygon
    }





    @Override
    public void showOverlay() {  // Shows the overlay and sets a timer for how long it will appear.
        setEnabled(true);
        displayTimer = 2000;
    }





    @Override
    public void update(int deltaTime) {  // deltaTime: Time since last update.
        displayTimer -= deltaTime;

        if(displayTimer <= 0) { // Updates the timer to hide the overlay and hides it when it hits 0.
            setEnabled(false);
        }
    }





    @Override
    public void paint(Graphics g) { //  g : Reference to the Graphics object for rendering.
        if(displayTimer % 200 < 150) { // Draws the Cross flashing with showing 75% of the time.

            g.setColor(new Color(179, 50, 38));
            g.fillPolygon(polyXCoords,polyYCoords,polyXCoords.length);
            g.setColor(Color.BLACK);
            g.drawPolygon(polyXCoords,polyYCoords,polyXCoords.length);
        }
    }
}
