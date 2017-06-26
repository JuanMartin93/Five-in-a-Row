package controller;

import java.util.ArrayList;
import java.util.List;

import model.Board;
import model.Game;
import model.Location;
import model.NotImplementedException;
import model.Player;

/**
 * A DumbAI is a Controller that always chooses the blank space with the
 * smallest column number from the row with the smallest row number.
 */
public class DumbAI extends Controller {

	public DumbAI(Player me) {
		super(me);
	}

	protected @Override Location nextMove(Game g) {
		
		List<Location> available = new ArrayList<Location>();
		
		// find available moves
		for (Location loc : Board.LOCATIONS)
			if (g.getBoard().get(loc) == null)
				available.add(loc);
		
		// wait a bit
		delay();
		delay();
		delay();


		return available.get(0);
	}
}
