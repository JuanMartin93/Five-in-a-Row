/* NetId(s): jam935, jhb334. Time spent: 10 hours, 00 minutes. */

package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clui.BoardPrinter;
import clui.ConsoleController;
import controller.Controller;
import controller.DumbAI;
import controller.RandomAI;
import controller.SmartAI;
import model.Game;
import model.NotImplementedException;
import model.Player;


public class Main {

	private Controller [] c = new Controller[2]; // Store the Controller that each player chose

	public  void  runGame(Player px, Player p0 ) {

		JPanel panelPlaX = new JPanel(); //Contains components asking for Player X
		JPanel panelPla0 = new JPanel(); //Contains components asking for Player )

		JLabel labelPlayX= new JLabel( "Choose who is gonna play player " + px.name());
		JLabel labelPlayO= new JLabel( "Choose who is gonna play player " + p0.name());
		
		JButton human = new JButton("Human"); // Button to select human Controller for Player X
		JButton dumbAI = new JButton("DumbAI"); // Button to select dubmAi Controller for Player X
		JButton randomAI = new JButton("RandomAI");// Button to select randomAI Controller for Player X
		JButton smartAI = new JButton("smartAI");// Button to select smartAI Controller for Player X
		
		JButton human1 = new JButton("Human"); // Button to select human Controller for Player O
		JButton dumbAI1 = new JButton("DumbAI"); // Button to select dubmAi Controller for Player O
		JButton randomAI1 = new JButton("RandomAI"); // Button to select randomAI Controller for Player O
		JButton smartAI1 = new JButton("smartAI"); // Button to select smartAI Controller for Player O
		
		
		// Adding buttons and label to panel
		panelPlaX.add(labelPlayX, BorderLayout.WEST);
		panelPlaX.add(human);
		panelPlaX.add(dumbAI);
		panelPlaX.add(randomAI);
		panelPlaX.add(smartAI);

		panelPla0.add(labelPlayO, BorderLayout.WEST);		
		panelPla0.add(human1);
		panelPla0.add(dumbAI1);
		panelPla0.add(randomAI1);
		panelPla0.add(smartAI1);



		JFrame frame = new JFrame("Prompt User");
		frame.add(panelPlaX, BorderLayout.NORTH);  
		frame.add(panelPla0, BorderLayout.SOUTH);  

		frame.setVisible(true);
		frame.pack();


		
		human.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c[0] = new GuiController(px);
				
				/* If the game wans't created, we know this is the first submitting its
				 * controller, so we hide the rest of the buttons in this panel 
				 * */
				if (!createrGame()){
					setInvisible(human, dumbAI ,randomAI, smartAI, labelPlayX);
					panelPlaX.add(new JLabel( px.name() + " choose to play Human" ));
				}else{
					frame.setVisible(false);
				}

			}
		});

		dumbAI.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				c[0] = new DumbAI(px);
				
				/* If the game wans't created, we know this is the first submitting its
				 * controller, so we hide the rest of the buttons in this panel 
				 * */
				if (!createrGame()){
					setInvisible(human, dumbAI ,randomAI, smartAI, labelPlayX);
					panelPlaX.add(new JLabel( px.name() + " choose to play DumbAI" ));
				}else{
					frame.setVisible(false);
				}
			}
		});
		randomAI.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c[0] = new RandomAI(px);
				
				/* If the game wans't created, we know this is the first submitting its
				 * controller, so we hide the rest of the buttons in this panel 
				 * */
				if (!createrGame()){
					setInvisible(human, dumbAI ,randomAI, smartAI, labelPlayX);
					panelPlaX.add(new JLabel( px.name() + " choose to play RandomAI" ));
				}else{
					frame.setVisible(false);
				}
			}
		});

		smartAI.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c[0] = new SmartAI(px);
				
				/* If the game wans't created, we know this is the first submitting its
				 * controller, so we hide the rest of the buttons in this panel 
				 * */
				if (!createrGame()){
					setInvisible(human, dumbAI ,randomAI, smartAI, labelPlayX);
					panelPlaX.add(new JLabel( px.name() + " choose to play SmartAI" ));
				}else{
					frame.setVisible(false);
				}
			}
		});	



		human1.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c[1] = new GuiController(p0);

				/* If the game wans't created, we know this is the first submitting its
				 * controller, so we hide the rest of the buttons in this panel 
				 * */
				if (!createrGame()){
					setInvisible(human1, dumbAI1 ,randomAI1, smartAI1, labelPlayO);
					panelPla0.add(new JLabel( p0.name() + " choose to play Human" ));
				}else{
					frame.setVisible(false);
				}
			}
		});

		dumbAI1.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c[1] = new DumbAI(p0);

				/* If the game wans't created, we know this is the first submitting its
				 * controller, so we hide the rest of the buttons in this panel 
				 * */
				if (!createrGame()){
					setInvisible(human1, dumbAI1 ,randomAI1, smartAI1, labelPlayO);
					panelPla0.add(new JLabel( p0.name() + " choose Dumb AI" ));
				}else{
					frame.setVisible(false);
				}
			}
		});
		randomAI1.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c[1] = new RandomAI(p0);
				
				/* If the game wans't created, we know this is the first submitting its
				 * controller, so we hide the rest of the buttons in this panel 
				 * */
				if (!createrGame()){
					setInvisible(human1, dumbAI1 ,randomAI1, smartAI1, labelPlayO);
					panelPla0.add(new JLabel( p0.name() + " choose Radom AI" ));
				}else{
					frame.setVisible(false);
				}
			}
		});

		smartAI1.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c[1] = new SmartAI(p0);
				
				/* If the game wans't created, we know this is the first submitting its
				 * controller, so we hide the rest of the buttons in this panel 
				 * */
				if (!createrGame()){
					setInvisible(human1, dumbAI1 ,randomAI1, smartAI1, labelPlayO);
					panelPla0.add(new JLabel( p0.name() + " choose SmartAI" ));
				}else{
					frame.setVisible(false);
				}

			}
		});	



	}
	
	// Set all component to Invisible but not the panel itself 
	private void setInvisible(JButton human, JButton dumbAI, JButton randomAI, 
			JButton smartAI, JLabel jl){
		
		human.setVisible(false);
		dumbAI.setVisible(false);
		randomAI.setVisible(false);
		smartAI.setVisible(false);
		jl.setVisible(false);
	}
	
	/*Creates a game only if the array of controllers is completely filled.
	 * this means that the two player had chosen its controllers 
	 */
	private  boolean createrGame(){
		if (c[1] != null && c[0] != null){
			Game g = new Game();
			GBoard gb = new GBoard();
			g.addListener(gb);
			gb.setVisible(true);
			g.addListener(c[0]);
			g.addListener(c[1]);
			return true;
		}
		return false;
	};

	public static void main(String[] args) {

		Main m = new Main();
		m.runGame(Player.X, Player.O);

	}


}



