import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class mainpage extends JFrame {
	static boolean teacher;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainpage frame = new mainpage();
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
	static int posX, posY;

	public mainpage() {

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

		getContentPane().setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 241, 383);
		contentPane = new JPanel();

		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				login nw = new login();
				dispose();
				nw.newpanel(false);
			}
		});
		
				JLabel lblNewLabeld = new JLabel("");
				lblNewLabeld.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						dispose();
					}
				});
				
				
				
				
				JLabel lblNewLabell = new JLabel("");
				lblNewLabell.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent arg0) {
						setState(JFrame.ICONIFIED);
					}
				});
				lblNewLabell.setIcon(new ImageIcon(menu.class.getResource("size.png")));
				lblNewLabell.setBounds(185, 13, 42, 25);
				contentPane.add(lblNewLabell);
				
				lblNewLabeld.setIcon(new ImageIcon(menu.class.getResource("close.png")));
				lblNewLabeld.setBounds(208, 9, 23, 29);
				contentPane.add(lblNewLabeld);
		lblNewLabel.setIcon(new ImageIcon(mainpage.class.getResource("student.png")));
		lblNewLabel.setBounds(31, 209, 60, 60);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				login nw = new login();
				dispose();
				nw.newpanel(true);
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon(mainpage.class.getResource("teacher.png")));
		lblNewLabel_1.setBounds(144, 209, 60, 60);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(0, 0, 241, 383);
		lblNewLabel_2.setBackground(new Color(0, 255, 0));
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setIcon(new ImageIcon(course.class.getResource("login.png")));
		
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
