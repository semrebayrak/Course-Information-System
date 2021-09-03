import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Font;

public class upload extends JFrame {
	private final Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pl", "postgres", "1425");
	private JPanel contentPane;
	private JTextField txtSa;
	private static String assid;
	

	/**
	 * Launch the application.
	 */
	public static void newpanel(String text,boolean messagesend) {
		assid = text;
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					upload frame = new upload();
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
	private int posX, posY;
	private JLabel lblNewLabel_1;

	public upload() throws SQLException {

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

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 335);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			
				dispose();
			
			}
		});
		
	
			JButton btnNewButton = new JButton("Send");
			btnNewButton.setBounds(781, 301, 89, 23);
			contentPane.add(btnNewButton);
			Statement stmt = con.createStatement();
			
			System.out.println(java.time.LocalDate.now());  
			btnNewButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
				
				
					
					try {
						
						
						String tempdata = "";
					
						ResultSet resultSet = stmt.executeQuery("SELECT id from homeworks ORDER BY id ASC ");
						ResultSetMetaData rsmdd = resultSet.getMetaData();
						int columnsNumberr = rsmdd.getColumnCount();
						String[] datas = new String[columnsNumberr];
						String tempid = "";
						while (resultSet.next()) {

							
							for (int i = 1; i <= columnsNumberr; i++) {
	                         
								tempdata = resultSet.getString(i);

							}

						}
	
						int tempdat = Integer.parseInt(tempdata) +1 ;
						
						
						tempdata = String.valueOf(tempdat);

		
					
						int resultSett = stmt.executeUpdate("INSERT INTO homeworks (id, context, sender,assignmentid,date) VALUES('"+ tempdat + "','" +  txtSa.getText() + "','"+ login.username + "','"+ assid+ "','" + java.time.LocalDate.now()+ "')");
					
						revalidate();
						repaint();
						JOptionPane.showMessageDialog(null, "Upload Sent");
						
					

					} catch (Exception ed) {
						// TODO Auto-generated catch block
						System.out.println(ed);
					}
				}
			});
			
			
		
		
		
		lblNewLabel_1.setIcon(new ImageIcon(messageread.class.getResource("close.png")));
		lblNewLabel_1.setBounds(830, 0, 40, 35);
		contentPane.add(lblNewLabel_1);

		txtSa = new JTextField();
	
		txtSa.setMargin(new Insets(0, 20, 200, 2));
	
	
		txtSa.setBackground(new Color(255, 255, 255));
		txtSa.setBounds(10, 46, 860, 244);

		contentPane.add(txtSa);
		txtSa.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(messageread.class.getResource("bg.png")));
		lblNewLabel.setBounds(-20, 0, 936, 335);
		contentPane.add(lblNewLabel);

		
		
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
