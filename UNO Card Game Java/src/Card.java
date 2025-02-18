import java.awt.*;

//  Uno
//
//  Card class:
//  Defines a Card including its properties and methods for showing appearance of the front and back.

public class Card extends Rectangle {


    public static final int CARD_WIDTH = 60; // Constant definition of the width of a card to be used for calculations.

    public static final int CARD_HEIGHT = 90; // Constant definition of the height of a card to be used for calculations.



    // The Strings to show for each different faceValueID.
    private static final String[] cardFaceValues = {"0","1","2","3","4","5","6","7","8","9", "Draw Two", "Skip", "Reverse", "Draw Four", "Wild"};


    private final String cardLabel; // The label in the centre of the card.


    private final String cornerLabel; // The label in both corners of the card.


    private int colourID; // The ID used to determine which of the four colours it is (or 4 if the card is a wild without colour set yet).



    private final int faceValueID; //  The faceValue to represent what type of number or other visual appearance the card has.



    private Color drawColour; // The colour used for drawing based on the colourID.



    private final int cardID; // The unique ID based on order drawn from the deck.




    public Card(int faceValueID, int colourID, int cardID) {
        super(new Position(0,0), CARD_WIDTH, CARD_HEIGHT);
        this.faceValueID = faceValueID;
        this.cardLabel = cardFaceValues[faceValueID]; // label
        this.colourID = colourID;
        this.drawColour = getColourByID(colourID); // color
        this.cardID = cardID;

        // if face value id is greater than 9 then setting face value id to..

        if(faceValueID == 10) {
            this.cornerLabel = "+2";
        }
        else if(faceValueID == 13) {
            this.cornerLabel = "+4";
        }
        else if(faceValueID == 14) {
            this.cornerLabel = "";
        }
        else {
            this.cornerLabel = cardLabel;
        }
    }




    //      Draws the card face up based on properties and type of card.
    //
    //      g : Reference to the Graphics object for rendering.

    public void paint(Graphics g) {

        g.setColor(Color.WHITE);  // Draw card background with white border and card colour

        g.fillRect(position.x, position.y, width, height);

        g.setColor(drawColour); // filling color in cards

        g.fillRect(position.x+2, position.y+2, width-4, height-4); //


        // condition if non-wild card then creating white oval in center of car else creating 4 colored oval in the center
        // of wild card


        if(colourID != 4) {
            // Draw a white oval for any non-wild in the middle.
            g.setColor(Color.WHITE);
            g.fillOval(position.x + 4, position.y + height / 2 - ((width - 8) / 4),
                    width - 8, (width - 8) / 2); // crating oval in center of non-wild card with white color.
        }

        else {

            // Red, blue, green, yellow segments for any wild card in the middle of card.

            for(int i = 0; i < 4; i++) {
                g.setColor(getColourByID(i));
                g.fillArc(position.x + 4, position.y + height / 2 - ((width - 8) / 4)-5,
                        width - 8, (width - 8) / 2+10, 270+90*i, 90);
            }
        }



        int fontHeight = (cardLabel.length() > 4 ) ? 10 : 20 ; // the size of the font at the center of card
        g.setFont(new Font("Arial", Font.BOLD, fontHeight));

        int strWidth = g.getFontMetrics().stringWidth(cardLabel);
        // Draw shadow (black) text for central label

        if(colourID == 4 || cardLabel.length() <= 4) {
            g.setColor(Color.BLACK);
            g.drawString(cardLabel, position.x+width/2-strWidth/2-1,
                   position.y+height/2+fontHeight/2+1);
        }

        // Colour to make primary text visible based on whether a shadow was added.
        if(colourID == 4) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(cardLabel.length()<=4 ? drawColour : Color.BLACK);
        }
        // Draw central label
        g.drawString(cardLabel, position.x+width/2-strWidth/2, position.y+height/2+fontHeight/2);


        // Draw labels in each of the corners
        fontHeight = (cornerLabel.length() > 2) ? 10 : 20;
        g.setFont(new Font("Arial", Font.BOLD, fontHeight));
        strWidth = g.getFontMetrics().stringWidth(cornerLabel);
        g.setColor(Color.WHITE);
        g.drawString(cornerLabel, position.x+5, position.y+5+fontHeight); // drawing top corner labels of cards.
        g.drawString(cornerLabel, position.x+width-strWidth-5, position.y+height-5); // drawing bottom corner label of the cards.
    }


    //    g : Reference to the Graphics object for rendering.
    //    bounds : Bounds to use for drawing the card back.

    // creating complete card back of uno cards
    public static void paintCardBack(Graphics g, Rectangle bounds) {
        g.setColor(Color.WHITE);
        g.fillRect(bounds.position.x, bounds.position.y, bounds.width, bounds.height); // setting up white rectangle borders of card back.
        g.setColor(Color.BLACK);
        g.fillRect(bounds.position.x+2, bounds.position.y+2, bounds.width-4, bounds.height-4); // filling black color in all back card leaving white borders.
        g.setColor(new Color(147, 44, 44)); // creating red color oval at the center of the back card.
        g.fillOval(bounds.position.x+4, bounds.position.y+bounds.height/2-((bounds.width-8)/4),
                bounds.width-8, (bounds.width-8)/2);
        g.setColor(Color.BLACK); // writing up uno at the back of uno card in black color.
        g.setFont(new Font("Arial", Font.BOLD, 20));
        int strWidth = g.getFontMetrics().stringWidth("UNO");
        g.drawString("UNO", bounds.position.x+bounds.width/2-strWidth/2-2,
                bounds.position.y+bounds.height/2-((bounds.width-8)/4)+2+20);
        g.setColor(new Color(226, 173, 67)); // writing uno also in yellow color at the center of the back of uno card
        g.drawString("UNO", bounds.position.x+bounds.width/2-strWidth/2,
                bounds.position.y+bounds.height/2-((bounds.width-8)/4)+20);
    }



    //    Sets the colour and the colour used to draw.
    //
    //    colourID: The colour to set the card to. 0=Red, 1=Blue, 2=Green, 3=Yellow, 4=Wild

    public void setColour(int colourID) {
        this.colourID = colourID;
        drawColour = getColourByID(colourID);
    }

    //    Gets the current colour on the card.
    //
    //    int: The current colourID of this card.


    public int getColourID() {
        return colourID;
    }





    //     Gets the faceValueID of the card.
    //
    //     int: The current faceValueID of the card.
    public int getFaceValueID() {
        return faceValueID;
    }





    //     Gets the unique number that represents only this card.
    //
    //     int: The unique cardID identifying this card.
    public int getCardID() {
        return cardID;
    }





   //       Gets a mapped colour for the colourID or Black.
   //
   //       colourID:  The colourID to get a Color to draw with.
   //       Color:  A Color mapped to the colourID. 0=Red, 1=Blue, 2=Green, 3=Yellow, Default=Black.

    public static Color getColourByID(int colourID) {
        return switch (colourID) {
            case 0 -> new Color(191, 48, 48);
            case 1 -> new Color(36, 94, 160);
            case 2 -> new Color(115, 187, 54);
            case 3 -> new Color(238, 188, 65);
            default -> Color.BLACK;
        };
    }




   //     Gets the score based on the faceValue of the card.
   //     Numbered cards are their face value, wild and +4 are worth 50,
   //     and others are worth 20.
   //
   //     int: The calculated score for this card.
    public int getScoreValue() {
        if(faceValueID < 10) return faceValueID;
        else if(faceValueID == 13 || faceValueID == 14) return 50;
        else return 20;
    }
}
