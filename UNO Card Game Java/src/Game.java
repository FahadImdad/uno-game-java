import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Uno
//
// Game class:
// Defines the entry point for the game by creating the frame,and populating it with a GamePanel.


public class Game implements KeyListener
{

    public static void main(String[] args) {
        new Game(); // Entry point for the application to create an instance of the Game class.
    }


    public GamePanel gamePanel; // Reference to the GamePanel object to pass key events to.



    //  Creates the JFrame with a GamePanel inside it, attaches a key listener,
    //  and makes everything visible.
    public Game() {
        JFrame frame = new JFrame("Uno"); //creating a GUI window and setting title to UNO
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // to close to game on Pressing the close (X) button
        frame.setResizable(false); // screen is not resizeable

        gamePanel = new GamePanel();  // creating game Panel
        frame.getContentPane().add(gamePanel);

        frame.addKeyListener(this); // adding key listener to game
        frame.pack(); // it sizes the frame so that all its contents are at or above their preferred sizes
        frame.setVisible(true); // to visible screen

        ImageIcon image = new ImageIcon("src/UNO_logo.png"); // ImageIcon for UNO Game
        frame.setIconImage(image.getImage()); // setting up ImageIcon
    }


    @Override
    public void keyPressed(KeyEvent e) // Called when the key is pressed down. Passes the key press on to the GamePanel. e Information about what key was pressed.
    {
        gamePanel.handleInput(e.getKeyCode());
    }


    @Override
    public void keyTyped(KeyEvent e) {} // Not used


    @Override
    public void keyReleased(KeyEvent e) {} // Not used
}
