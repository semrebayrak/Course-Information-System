import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class uploads extends JFrame {

	private static String assid;
	private JPanel contentPane;
	private final Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pl", "postgres", "1425");
	/**
	 * Launch the application.
	 */
	public static void newframe(String ass) {
		assid = ass;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					uploads frame = new uploads();
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
	private JTable table_1;
    private int posX,posY;
    

	public DefaultTableModel DTM = new DefaultTableModel() {

	    @Override
	    public boolean isCellEditable(int row, int column) {
	       //all cells false
	       return false;
	    }
	};
	public uploads() throws SQLException {
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

		setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 557);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
			
				String index;
				String message = "";
				if (me.getClickCount() == 2) {     // to detect doble click events
					index=table_1.getValueAt(table_1.getSelectedRow(), 0).toString().substring(1);
					
					messageread cr;
					try {
						Statement stmt = con.createStatement();
						ResultSet resultSet = stmt.executeQuery("SELECT context from homeworks where id='"+ index    +"'");
						ResultSetMetaData rsmd = resultSet.getMetaData();
						int columnsNumber = rsmd.getColumnCount();
					 
						while (resultSet.next()) {
							String tempword= "";
							for (int i = 1; i <= columnsNumber; i++) {

								String columnValue = resultSet.getString(i);
								message = columnValue;
							
								
							}
							
							
							revalidate();
							repaint();
						
							cr = new messageread();
							cr.newpanel(message,false);
						}
						
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

		JLabel lblNewLabel_3_2 = new JLabel("Context");
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

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
dispose();
menu.anopened = false;
			}
		});
		lblNewLabel.setIcon(new ImageIcon(menu.class.getResource("close.png")));
		lblNewLabel.setBounds(816, 9, 23, 29);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setState(JFrame.ICONIFIED);
				
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon(menu.class.getResource("size.png")));
		lblNewLabel_1.setBounds(791, 13, 42, 25);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = 	new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(message.class.getResource("bg.png")));
		lblNewLabel_2.setBounds(0, 0, 900, 557);
		contentPane.add(lblNewLabel_2);

	   TableCall();

		
		// CENTER IT

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int w = this.getSize().width;
		int h = this.getSize().height;
		int x = (screenSize.width - w) / 2;
		int y = (screenSize.height - h) / 2;

		// Move the window
		this.setLocation(x, y);
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
			ResultSet resultSet = stmt.executeQuery("SELECT id,context,sender,date from homeworks where assignmentid='" +assid + "' order by id asc");
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
	}


