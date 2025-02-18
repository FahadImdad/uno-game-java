//  Uno
//
//  TurnDecisionOverlayInterface interface:
//  Defines an interface to be used for defining overlays that can be
//  made to appear using a TurnDecisionAction.


public interface TurnDecisionOverlayInterface {



    //   Show the overlay.
    void showOverlay(TurnActionFactory.TurnDecisionAction currentAction); // currentAction: The action used to trigger this interface.
}
