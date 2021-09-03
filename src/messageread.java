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

public class messageread extends JFrame {
	private final Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pl", "postgres", "1425");
	private JPanel contentPane;
	private JTextField txtSa;
	private static String message;
	private static boolean newmessage;

	/**
	 * Launch the application.
	 */
	public static void newpanel(String text,boolean messagesend) {
		message = text;
		newmessage = messagesend;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					messageread frame = new messageread();
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
	private JTextField textField;

	public messageread() throws SQLException {

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
	
		if(newmessage) {
			JButton btnNewButton = new JButton("Send");
			btnNewButton.setBounds(781, 301, 89, 23);
			contentPane.add(btnNewButton);

			JLabel lblNewLabel_2 = new JLabel("Receiver");
			lblNewLabel_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
			lblNewLabel_2.setBounds(10, 14, 73, 20);
			contentPane.add(lblNewLabel_2);
			
			
			textField = new JTextField();
			textField.setToolTipText("");
			textField.setBounds(68, 13, 110, 20);
			contentPane.add(textField);
			textField.setColumns(10);
			Statement stmt = con.createStatement();
			
			System.out.println(java.time.LocalDate.now());  
			btnNewButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
				
					System.out.println(textField.getText());
					
					try {
						
						
						String tempdata = "";
					
						ResultSet resultSet = stmt.executeQuery("SELECT id from messages ORDER BY id ASC ");
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

		
					
						int resultSett = stmt.executeUpdate("INSERT INTO messages (id, message, towho,fromwho,date) VALUES('"+ tempdat + "','" +  txtSa.getText() + "','"+ textField.getText() + "','"+ login.username+ "','" + java.time.LocalDate.now()+ "')");
					
						revalidate();
						repaint();
						JOptionPane.showMessageDialog(null, "Message Sent");
						
					

					} catch (Exception ed) {
						// TODO Auto-generated catch block
						System.out.println(ed);
					}
				}
			});
			
			
		}
		
		
		lblNewLabel_1.setIcon(new ImageIcon(messageread.class.getResource("close.png")));
		lblNewLabel_1.setBounds(830, 0, 40, 35);
		contentPane.add(lblNewLabel_1);

		txtSa = new JTextField();
		txtSa.setText(message);
		txtSa.setMargin(new Insets(0, 20, 200, 2));
		if(!newmessage)
		txtSa.setEditable(false);
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
