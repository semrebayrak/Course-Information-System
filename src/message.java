import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTree;
import javax.swing.JScrollBar;
import javax.swing.ListSelectionModel;

public class message extends JFrame {
	private final Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pl", "postgres", "1425");
	public DefaultTableModel DTM = new DefaultTableModel() {

	    @Override
	    public boolean isCellEditable(int row, int column) {
	       //all cells false
	       return false;
	    }
	};
	public static boolean sendmessage = false;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void newpanel() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					message frame = new message();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	static int posX, posY;
	private JTable table_1;


	
	public message() throws SQLException {

		// MAKE MOVABLE

		makemovable();

		setUndecorated(true);

		contentPane = new JPanel();

		

		setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 557);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				sendmessage= false;
				String index;
				String message = "";
				if (me.getClickCount() == 2) {     // to detect doble click events
					index=table_1.getValueAt(table_1.getSelectedRow(), 0).toString().substring(1);
					
					messageread cr;
					try {
						Statement stmt = con.createStatement();
						ResultSet resultSet = stmt.executeQuery("SELECT message from messages where id='"+ index    +"'");
						ResultSetMetaData rsmd = resultSet.getMetaData();
						int columnsNumber = rsmd.getColumnCount();
					 
						while (resultSet.next()) {
							String tempword= "";
							for (int i = 1; i <= columnsNumber; i++) {

								String columnValue = resultSet.getString(i);
								message = columnValue;
							
								
							}}
							
							
							revalidate();
							repaint();
						
						
						cr = new messageread();
						cr.newpanel(message,sendmessage);
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.setShowHorizontalLines(false);
		table_1.setRowHeight(23);
		table_1.setSelectionForeground(SystemColor.activeCaption);
		table_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
		
		
		
		
		
		
		
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(861, 85, 17, 358);
		contentPane.add(scrollBar);
		
		table_1.setBounds(10, 85, 852, 358);
		contentPane.add(table_1);
		
		JLabel lblNewLabel_3_2_1 = new JLabel("Id");
		lblNewLabel_3_2_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		lblNewLabel_3_2_1.setBounds(10, 49, 198, 25);
		contentPane.add(lblNewLabel_3_2_1);

		JLabel lblNewLabel_3_2 = new JLabel("Message");
		lblNewLabel_3_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		lblNewLabel_3_2.setBounds(95, 49, 198, 25);
		contentPane.add(lblNewLabel_3_2);

		JLabel lblNewLabel_3_1 = new JLabel("Date");
		lblNewLabel_3_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		lblNewLabel_3_1.setBounds(777, 49, 198, 25);
		contentPane.add(lblNewLabel_3_1);

		JLabel lblNewLabel_3 = new JLabel("Sender");
		lblNewLabel_3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(692, 49, 198, 25);
		contentPane.add(lblNewLabel_3);

		
	
		JButton btnNewButton = new JButton("Send New Message");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				   sendmessage = true;

					String index;
					messageread cr;
					try {
						
						cr = new messageread();
						cr.newpanel(" ",sendmessage);
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
			}
		});
		
		
		
		
		
		
		
	
	
		btnNewButton.setBounds(633, 468, 135, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Delete Message");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				if(table_1.getSelectedRow()!=-1) {
				try {

					System.out.println(table_1.getValueAt(table_1.getSelectedRow(), 0).toString().substring(1));

					
					Statement stmt = con.createStatement();
					ResultSet resultSet = stmt.executeQuery("Select * from messages where id = '" + table_1.getValueAt(table_1.getSelectedRow(), 0).toString().substring(1) + "'");
					if(resultSet.next()) {
						
					int resultSett = stmt.executeUpdate("DELETE FROM messages where id = '" +  table_1.getValueAt(table_1.getSelectedRow(), 0).toString().substring(1) + "'");
				
					JOptionPane.showMessageDialog(null, "Message Removed");
					DTM.removeRow(table_1.getSelectedRow());
					table_1.setModel(DTM);
					
					revalidate();
					repaint();
					
					}
					
					else
						JOptionPane.showMessageDialog(null, "Message Doesn't Exists");

				} catch (Exception ed) {
					// TODO Auto-generated catch block
					System.out.println(ed);
				}
				
				}
			}
		});
		
		
		
		
		
		
		
		
		
		btnNewButton_1.setBounds(633, 502, 135, 23);
		contentPane.add(btnNewButton_1);

		background(contentPane);

	   TableCall();

		
		// CENTER IT
	   center();
	}
	
	void TableCall() {
		try {
			String[] arraytemp = new String[4];
			arraytemp[0] = login.username;
		
			DTM.addColumn("first");
			DTM.addColumn("sec");
			DTM.addColumn("third");
			DTM.addColumn("fourth");
			
			
		
			Statement stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery("SELECT id,message,fromwho,date from messages where towho='" +login.username + "' order by id asc");
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
		 
			while (resultSet.next()) {
				
				for (int i = 1; i <= columnsNumber; i++) {

					arraytemp[i-1] = " " + resultSet.getString(i);
					
				
					
				}
				DTM.addRow(arraytemp);
			
			}
			
		
			table_1.setModel(DTM);
			table_1.getColumnModel().getColumn(0).setResizable(false);
			table_1.getColumnModel().getColumn(0).setPreferredWidth(15);
			table_1.getColumnModel().getColumn(0).setMinWidth(0);
			table_1.getColumnModel().getColumn(1).setResizable(false);
			table_1.getColumnModel().getColumn(1).setPreferredWidth(530);
			table_1.getColumnModel().getColumn(2).setResizable(false);
			table_1.getColumnModel().getColumn(2).setPreferredWidth(18);
			table_1.getColumnModel().getColumn(3).setResizable(false);
			table_1.getColumnModel().getColumn(3).setPreferredWidth(15);
			
			revalidate();
			repaint();

		} catch (Exception er) {
			System.out.println(er);
		}
	}
	public void center() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int w = this.getSize().width;
		int h = this.getSize().height;
		int x = (screenSize.width - w) / 2;
		int y = (screenSize.height - h) / 2;

		// Move the window
		this.setLocation(x, y);
	}

	public void makemovable() {
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
	}

	public void background(JPanel contentPane) {

		setUndecorated(true);



		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 557);
		
	
		
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
				menu.meopened = false;
			}
		});
		lblNewLabel.setIcon(new ImageIcon(menu.class.getResource("close.png")));
		lblNewLabel.setBounds(816, 9, 23, 29);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(menu.class.getResource("size.png")));
		lblNewLabel_1.setBounds(791, 13, 42, 25);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(course.class.getResource("bg.png")));
		lblNewLabel_2.setBounds(0, 0, 900, 557);
		contentPane.add(lblNewLabel_2);
	}
}
