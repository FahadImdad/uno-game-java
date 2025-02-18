import java.awt.*;

//   Uno
//
//  Button class:
//  Defines a simple button consisting of a rectangle region,
//  some text to centre in it, a hover state, and an
//  actionID that is available to give the button context.


public class Button extends Rectangle {

    private final int actionID; // A number that can be used to give context for when the button is detected to have been clicked.


    private boolean isHovered; // True when the mouse is over the rectangle causing a colour change.


    private final String text; // Text to centre in the button.



   //      Sets up the Button ready to display and interact with.
   //
   //       position:    Top left corner of the button.
   //       width:       Width of the button.
   //       height:      Height of the button.
   //       text:        Text to centre in the button.
   //       actionID:    A number that can be used to give context for when the button is detected to have been clicked.
    public Button(Position position, int width, int height, String text, int actionID) {
        super(position, width, height);
        this.actionID = actionID;
        isHovered = false;
        this.text = text;
    }


    //      Draws a background, border, and text. Colours change depending on whether it is hovered.
    //
    //      g : Reference to the Graphics object for rendering.


    public void paint(Graphics g) {

        if(isHovered) {
            g.setColor(new Color(115, 156, 58, 204));
            g.fillRect(position.x-3, position.y-3, width+6, height+6);
        }

        else {
            g.setColor(new Color(255, 207, 82, 173));
            g.fillRect(position.x, position.y, width, height);
        }

        if(isHovered) {
            g.setColor(Color.WHITE);
            g.drawRect(position.x-3, position.y-3, width+6, height+6);
        }
        else {
            g.setColor(Color.BLACK);
            g.drawRect(position.x, position.y, width, height);
        }

        g.setFont(new Font("Arial", Font.BOLD, 20));
        int strWidth = g.getFontMetrics().stringWidth(text);
        g.drawString(text, position.x+width/2-strWidth/2, position.y + height/2 + 8);
    }



    //  Can be called to get a custom actionID for determining the result of a button press.
    //
    //  actionID : The actionID to be used for giving the button context.
    public int getActionID() {
        return actionID;
    }



    //       Updates the hover state to the specified value.
    //
    //       isHovering: When true the colours change on the button.
    public void setHovering(boolean isHovering) {
        this.isHovered = isHovering;
    }
}
