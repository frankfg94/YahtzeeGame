import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;

public class FrameTest extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameTest frame = new FrameTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 467);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNouvelle = new JMenu("Game");
		menuBar.add(mnNouvelle);
		
		JMenu mnArrter = new JMenu("New");
		mnNouvelle.add(mnArrter);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Singleplayer Game");
		mnArrter.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Multiplayer Game");
		mnArrter.add(mntmNewMenuItem_1);
		
		JMenuItem mntmRestart = new JMenuItem("Restart");
		mnNouvelle.add(mntmRestart);
		
		JMenuItem mntmLeaderboard = new JMenuItem("Leaderboard");
		mnNouvelle.add(mntmLeaderboard);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 0));
		panel_1.setBounds(81, 303, 357, 78);
		contentPane.add(panel_1);
		
		JButton button1 = new JButton("D\u00E9 1");
		button1.setPreferredSize(new Dimension(60,60));
		panel_1.add(button1);
		
		JButton button2 = new JButton("D\u00E9 2");
		button2.setPreferredSize(new Dimension(60,60));
		panel_1.add(button2);
		
		JButton button3 = new JButton("D\u00E9 3");
		button3.setPreferredSize(new Dimension(60,60));
		panel_1.add(button3);
		
		JButton button4 = new JButton("D\u00E9 4");
		button4.setPreferredSize(new Dimension(60,60));
		panel_1.add(button4);
		
		JButton button5 = new JButton("D\u00E9 5");
		button5.setPreferredSize(new Dimension(60,60));
		panel_1.add(button5);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
}
