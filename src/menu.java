


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class menu extends JFrame {

	
	static boolean copened = false;
	
	static boolean anopened = false;
	
	static boolean asopened = false;
	
	static boolean meopened = false;
	
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void newframe() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu frame = new menu();
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
	static int posX,posY;
	public menu() {
		

		// MAKE MOVABLE

		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				posX = e.getX();
				posY = e.getY();
			}
		});

		this.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent evt) {
				// sets frame position when mouse dragged
				setLocation(evt.getXOnScreen() - posX, evt.getYOnScreen() - posY);

			}
		});
		
		
		setUndecorated(true);

		
		
		
		contentPane = new JPanel();
		
	
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 334, 557);

		
		// if(login.teacher) {
		JButton btnNewButton = new JButton("Courses");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				course cr;
				try {
					if(!copened) {
					
					cr = new course();
					cr.newpanel();
					copened = true;
					
				} }catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(22, 259, 126, 45);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Assignments");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				assignment cr;
				try {
					if(!asopened) {
					cr = new assignment();
					cr.newpanel();
					asopened = true;}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	
		
		
		
		btnNewButton_1.setBounds(22, 345, 126, 45);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Announcements");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				announcement cr;
				try {
					if(!anopened) {
					cr = new announcement();
					cr.newpanel();
					anopened = true;}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_2.setBounds(185, 259, 126, 45);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Message");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				message cr;
				try {
					if(!meopened) {
					cr = new message();
					cr.newpanel();
					meopened = true;}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(185, 345, 126, 45);
		contentPane.add(btnNewButton_3);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {dispose();

			}
		});
		lblNewLabel.setIcon(new ImageIcon(menu.class.getResource("close.png")));
		lblNewLabel.setBounds(262, 11, 23, 23);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon(menu.class.getResource("size.png")));
		lblNewLabel_1.setBounds(232, 18, 23, 16);
		contentPane.add(lblNewLabel_1);
		

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(course.class.getResource("menu.png")));
		lblNewLabel_2.setBounds(0,0, 334, 557);
		contentPane.add(lblNewLabel_2);
		
				contentPane.add(lblNewLabel_2);
		// CENTER IT

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int w = this.getSize().width;
		int h = this.getSize().height;
		int x = (screenSize.width - w) / 2;
		int y = (screenSize.height - h) / 2;

		// Move the window
		this.setLocation(x, y);

	}
}
