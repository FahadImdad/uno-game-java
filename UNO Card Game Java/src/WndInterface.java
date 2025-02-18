import java.awt.*;

// Uno
//
// WndInterface class:
// Defines a generic abstraction to use for multiple interfaces.

public abstract class WndInterface {

    private boolean isEnabled; // State of whether the object is enabled, so it can be used for managing updates.

    public final Rectangle bounds; // Bounds of this interface.

   //   Initialise the interface with bounds and make it enabled.
   //
   //   bounds: Bounds of the interface.


    public WndInterface(Rectangle bounds) {
        isEnabled = true;
        this.bounds = bounds;
    }



    //      Update the interface elements.
    //
    //      deltaTime: Time since last update.
    public abstract void update(int deltaTime);




    //   Draw all elements to the interface.
    //
    //   g : Reference to the Graphics object for rendering.

    public abstract void paint(Graphics g);




    //      Handle updates related to the mouse pressing at the specified position.
    //
    //      mousePosition : Position of the mouse cursor during the press.
    //      isLeft : If true, the mouse button is left, otherwise is right.
    public void handleMousePress(Position mousePosition, boolean isLeft) {}





    //      Handle updates related to the mouse being moved.
    //
    //      mousePosition:  Position of the mouse during this movement.
    public void handleMouseMove(Position mousePosition) {}





    //     Change the enabled state of this object.
    //
    //     enabled New state to set the enabled/disabled state of this object.

    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }




    //  Get the current enabled state of the object.
    //
    //  boolean: True if the object is enabled.

    public boolean isEnabled() {
        return isEnabled;
    }



     //     Handles the key input from a keyboard action.
     //
     //     keyCode : The key that was pressed.
    public void handleInput(int keyCode) {}
}
