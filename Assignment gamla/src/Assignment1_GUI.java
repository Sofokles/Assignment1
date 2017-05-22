import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JPanel;


public class Assignment1_GUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Assignment1_GUI window = new Assignment1_GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Assignment1_GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		
		JButton btnCalculateYbus = new JButton("Calculate Ybus");
		frame.getContentPane().add(btnCalculateYbus);
	}

}
