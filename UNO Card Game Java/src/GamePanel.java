import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

//   Uno
//
//  GamePanel class:
//  Manages the primary game with passing off actions from the mouse, keys, and
//  any timer events to the different parts of the game.


public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener {

    public static int PANEL_HEIGHT = 720; // Height of panel

    public static int PANEL_WIDTH = 1280; // Width of Panel


    public PauseInterface pauseWnd; // Reference to the window that appears when the game is paused.
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    public WndInterface activeInterface; // Reference to the active interface.

    public static boolean DEBUG_MODE; // When debug mode is enabled. Additional output and controls are enabled.

    //      Configures the game ready to be played including selection of playing against either
    //      AI or another player.
    public GamePanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT)); //Panel size
        setBackground(new Color(235, 28, 36, 255)); // Background color

        pauseWnd = new PauseInterface(new Rectangle(PANEL_WIDTH/2-100,PANEL_HEIGHT/2-100,200,200), this); // creating pause interface box, and it  will be show only when the game is paused.

        pauseWnd.setEnabled(false); // pause window is not enabled right now

        showLobby(); // showing lobby


////////////////////////////////////////////////////////////////////////////////


        Timer updateTimer = new Timer(20, this); //
        updateTimer.start(); //timer is start

        addMouseListener(this); // allows mouse to listen actions

        addMouseMotionListener(this); // allows to show animation once the mouse is above them

        DEBUG_MODE = false; // Debug mode is initially offed
    }

    // Sets the currently active interface to the lobby removing any existing interface.

    // If this is triggered from the pause interface it just resumes the current interface.

    // showing lobby
    public void showLobby() {
        if(!(activeInterface instanceof LobbyInterface)) // if active interface is not part of lobby interface
        {
            activeInterface = new LobbyInterface(new Rectangle(0, 0, PANEL_WIDTH, PANEL_HEIGHT), this); // creating a lobby interface
        }
        //////////////////////////////
      setPauseState(false);
    }



    // Sets the currently active interface to the post-game interface after a game has ended.

    // playerList List of players who were playing in the round.

    // ruleSet Rules applied during the round.
    public void showPostGame(List<Player> playerList, RuleSet ruleSet)
    {
        activeInterface = new PostGameInterface(new Rectangle(0,0,PANEL_WIDTH, PANEL_HEIGHT), playerList, ruleSet, this); // creating post game interface
    }



    // Creates a new game with the specified list of players. Use this for coming from the lobby.

    // playerList: The player list to start a game with.

    // ruleSet: Definition of how the game is to be played.

    public void startGame(List<LobbyPlayer> playerList, RuleSet ruleSet)
    {
        activeInterface = new CurrentGameInterface(new Rectangle(0,0,PANEL_WIDTH,PANEL_HEIGHT), ruleSet, playerList, this); // creating game interface
    }


    // Creates a new game with the specified list of players. Use this for coming from post-game.

    // playerList: The player list to start a new round with.

    // ruleSet: Definition of how the game is to be played.

    public void startNextRound(List<Player> playerList, RuleSet ruleSet)
    {
        activeInterface = new CurrentGameInterface(new Rectangle(0,0,PANEL_WIDTH,PANEL_HEIGHT), playerList, ruleSet, this); // creating game interface with same players in previous game.
    }



    // Draws the game grid and draws the message at the bottom showing a string representing the game state.

    // Reference to the Graphics object for rendering.

    public void paint(Graphics g)
    {
        super.paint(g);
        if(activeInterface != null)
        {
            activeInterface.paint(g);  // if active interface is not equal to null then draw graphics on active interface.
        }

        if(pauseWnd.isEnabled())
        {
            pauseWnd.paint(g); // is pause window option is enabled then draw graphics of pause interface.
        }

        if(DEBUG_MODE) // if debug mode is ON then write "DEBUG ON" on the top left section of screen
        {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("DEBUG ON", 10,20);
        }
    }


    // Pauses or unpauses the game.

    // isPaused: When true the game is paused and pause window is shown.


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setPauseState(boolean isPaused) {
        if(activeInterface != null) {
            activeInterface.setEnabled(!isPaused);
        }
        pauseWnd.setEnabled(isPaused);
    }


    public void quitGame() //  Quits the game immediately whenever the quit option is clicked
    {
        System.exit(0);
    }



    //      Handles the key input to have Escape open the pause menu.
    //
    //      keyCode: The key that was pressed.

    public void handleInput(int keyCode)  //Handles the key input to have Escape open the pause menu. keyCode The key that was pressed.
    {
        if(keyCode == KeyEvent.VK_ESCAPE)   // if key pressed is equal to Escape
        {
           setPauseState(!pauseWnd.isEnabled()); //pause window is enabled
        }

        else if(keyCode == KeyEvent.VK_0) // if key pressed is 0
        {
            DEBUG_MODE = !DEBUG_MODE; // then enable and disable DEBUG MODE
        }
        else
        {
            activeInterface.handleInput(keyCode);
        }

        repaint();
    }





    @Override
    public void mousePressed(MouseEvent e) // Passes the mouse event on to all the windows. e Information about the mouse event.
    {
        Position mousePosition = new Position(e.getX(), e.getY()); // getting position of mouse

        pauseWnd.handleMousePress(mousePosition, e.getButton() == 1); //handle mouse press on pause window

        if(activeInterface != null) // if active interface is not equal to null, handle mouse press on active interface
        {
            activeInterface.handleMousePress(mousePosition, e.getButton() == 1);
        }

        repaint();
    }




    // Draws a title with UNO! and text below for credits.
    // g Reference to the Graphics object for rendering.
    // bounds Bounds of the game area.

    public void paintUnoTitle(Graphics g, Rectangle bounds) {
//        g.setFont(new Font("Arial", Font.BOLD, 40));
//        g.drawString("UNO!", bounds.width/2-40, 50);
//        g.setFont(new Font("Arial", Font.BOLD, 10));
//        g.drawString("Developed by Muhammad Fahad Imdad", bounds.width/2-70, 65);
//        g.setFont(new Font("Arial", Font.BOLD, 40));
//        g.setColor(Card.getColourByID(0));
//        g.drawString("U", bounds.width/2-40+2, 48);
//        g.setColor(Card.getColourByID(1));
//        g.drawString("N", bounds.width/2-40+2+30, 48);
//        g.setColor(Card.getColourByID(2));
//        g.drawString("O", bounds.width/2-40+2+60, 48);
//        g.setColor(Card.getColourByID(3));
//        g.drawString("!", bounds.width/2-40+2+90, 48);


        Toolkit t=Toolkit.getDefaultToolkit();

        Image i=t.getImage("src/UNO_.png"); // getting image of uno
        g.drawImage(i,bounds.width/2-30,-3,120,80,this); // drawing image of uno

        Image j=t.getImage("src/SHU_LOGO.jpg"); // getting image of SHU logo
        g.drawImage(j,bounds.width/2-630,5,200,70,this); // drawing image of SHU logo


    }



    @Override
    public void mouseMoved(MouseEvent e) //Passes the mouse event on to all the windows.  e Information about the mouse event.
    {
        Position mousePosition = new Position(e.getX(), e.getY()); // getting position of mouse

       pauseWnd.handleMouseMove(mousePosition); // handle mouse moving on pause window

        if(activeInterface != null) // if active interface is not equal to null, handle mouse move on active interface
        {
            activeInterface.handleMouseMove(mousePosition);
        }
        repaint();
    }



    @Override
    public void actionPerformed(ActionEvent e) //Forces the active game to update and forces a repaint. e Information about the event.
    {
        if(activeInterface != null) // if active interface is not equal to null, update active interface
        {
            activeInterface.update(20);
        }
        repaint();
    }



    @Override
    public void mouseClicked(MouseEvent e) {} // not set



    @Override
    public void mouseReleased(MouseEvent e) {} // not set



    @Override
    public void mouseEntered(MouseEvent e) {} // not set



    @Override
    public void mouseExited(MouseEvent e) {} // not set



    @Override
    public void mouseDragged(MouseEvent e) {} // not set
}
