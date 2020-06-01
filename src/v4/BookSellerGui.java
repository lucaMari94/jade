package v4;

import jade.core.AID;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
  @author Giovanni Caire - TILAB
 */
class BookSellerGui extends JFrame {	// JFrame
	private BookSellerAgent myAgent;
	
	private JTextField titleField, priceField; // 2 campi di testo
	
	BookSellerGui(BookSellerAgent a) {
		super(a.getLocalName());
		
		myAgent = a;
		
		JPanel p = new JPanel(); // pannello
		p.setLayout(new GridLayout(2, 2)); // layout Grid
		
		// LABEL AND FIELD
		p.add(new JLabel("Book title:")); // label
		titleField = new JTextField(15); // text Field
		p.add(titleField); // aggiunta al panello
		
		p.add(new JLabel("Price:"));
		priceField = new JTextField(15);
		p.add(priceField);
		
		getContentPane().add(p, BorderLayout.CENTER); // Layout
		
		JButton addButton = new JButton("Add"); // BUTTON
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) { // AZIONE
				try {
					String title = titleField.getText().trim();
					String price = priceField.getText().trim();
					
					
					
					// ------------------------------------------------
					// AGGIUNTA AL CATALOGO
					myAgent.updateCatalogue(title, Integer.parseInt(price)); 
					// ------------------------------------------------
					
					
					
					titleField.setText("");
					priceField.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(BookSellerGui.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );
		p = new JPanel();
		p.add(addButton);
		getContentPane().add(p, BorderLayout.SOUTH);
		
		// Make the agent terminate when the user closes 
		// the GUI using the button on the upper right corner	
		addWindowListener(new	WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				myAgent.doDelete();
			}
		} );
		
		setResizable(false);
	}
	
	public void showGui() {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		super.setVisible(true);
	}	
}