import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

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

public class course extends JFrame implements bg {
	private JComboBox comboBox;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void newpanel() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					course frame = new course();
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

	public course() throws SQLException {

		makemovable();

		contentPane = new JPanel();
		JLabel lblNewLabel_3_2 = new JLabel("Course Id");
		lblNewLabel_3_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		lblNewLabel_3_2.setBounds(10, 86, 198, 25);
		contentPane.add(lblNewLabel_3_2);

		JLabel lblNewLabel_3_1 = new JLabel("Lecturer");
		lblNewLabel_3_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		lblNewLabel_3_1.setBounds(583, 86, 198, 25);
		contentPane.add(lblNewLabel_3_1);

		JLabel lblNewLabel_3 = new JLabel("Course Name");
		lblNewLabel_3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(181, 86, 198, 25);
		contentPane.add(lblNewLabel_3);

		

		JButton btnNewButton = new JButton("Add Course");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		comboBox = new JComboBox();

		comboBox.setBounds(10, 52, 86, 20);
		contentPane.add(comboBox);

		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {

					Connection con;

					con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pl", "postgres", "1425");

					Statement stmt = con.createStatement();
					ResultSet resultSet = stmt
							.executeQuery("Select * from courses where id = '" + textField.getText().toString() + "'");
					if (!resultSet.next()) {

						int resultSett = stmt.executeUpdate(
								"INSERT INTO courses (id, name, teacher) VALUES ('" + textField.getText() + "'" + ","
										+ "'" + textField_1.getText() + "'" + "," + "'" + textField_2.getText() + "')");

						JOptionPane.showMessageDialog(null, "Course Added");
						comboBox.addItem(textField.getText());
						comboBox.setSelectedItem(textField.getText());
						revalidate();
						repaint();

					}

					else
						JOptionPane.showMessageDialog(null, "Course Already Exists");

				} catch (Exception ed) {
					// TODO Auto-generated catch block
					System.out.println(ed);
				}

			}
		});

		btnNewButton.setBounds(619, 390, 135, 23);
		if (login.teacher)
			contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Delete Course");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {

					Connection con;

					con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pl", "postgres", "1425");

					Statement stmt = con.createStatement();
					ResultSet resultSet = stmt.executeQuery(
							"Select * from courses where id = '" + comboBox.getSelectedItem().toString() + "'");
					if (resultSet.next()) {

						int resultSett = stmt
								.executeUpdate("DELETE FROM courses where id = '" + comboBox.getSelectedItem().toString()+ "'");

						JOptionPane.showMessageDialog(null, "Course Deleted");
						comboBox.removeItem(comboBox.getSelectedItem());
						revalidate();
						repaint();

					}

					else
						JOptionPane.showMessageDialog(null, "Course Doesn't Exists");

				} catch (Exception ed) {
					// TODO Auto-generated catch block
					System.out.println(ed);
				}

			}
		});

		btnNewButton_1.setBounds(619, 424, 135, 23);
		if (login.teacher)
			contentPane.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("Edit Course");
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					String temp = textField.getText();
					Connection con;

					con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pl", "postgres", "1425");

					Statement stmt = con.createStatement();
					ResultSet resultSet = stmt
							.executeQuery("Select * from courses where id = '" + textField.getText() + "'");

					if (comboBox.getSelectedItem().toString().split(" ")[0].equals(temp)) {
						// System.out.println(("UPDATE courses SET id='" + textField.getText()
						// +"',name='" + textField_1.getText() +"',teacher='" + textField_2.getText() +
						// "' WHERE id='" + comboBox.getSelectedItem().toString()+ "'" ));
						int resultSett = stmt.executeUpdate("UPDATE courses SET id='" + textField.getText() + "',name='"
								+ textField_1.getText() + "',teacher='" + textField_2.getText() + "' WHERE id='"
								+ comboBox.getSelectedItem().toString() + "'");
						int resultSett2 = stmt.executeUpdate("UPDATE assignments SET lecture='" + textField.getText()
								+ "' WHERE lecture='" + comboBox.getSelectedItem().toString() + "'");

						comboBox.removeItem(comboBox.getSelectedItem());

						comboBox.addItem(temp);
						comboBox.setSelectedItem(temp);
						revalidate();
						repaint();
						JOptionPane.showMessageDialog(null, "Course Edited");

					}

					else {
						JOptionPane.showMessageDialog(null, "Course ID cannot be changed");
					}

				} catch (Exception ed) {
					// TODO Auto-generated catch block
					System.out.println(ed);
				}

			}
		});

		btnNewButton_1_1.setBounds(619, 461, 135, 23);
		if (login.teacher)
			contentPane.add(btnNewButton_1_1);

		DefaultTableModel DTM = new DefaultTableModel();
		DTM.addColumn("id");
		DTM.addColumn("name");
		DTM.addColumn("teacher");

		textField = new JTextField();
		textField.setBounds(10, 111, 86, 25);
		if (!login.teacher)
			textField.setEditable(false);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(181, 111, 329, 25);
		if (!login.teacher)
			textField_1.setEditable(false);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(583, 111, 256, 25);
		if (!login.teacher)
			textField_2.setEditable(false);
		contentPane.add(textField_2);

		background(contentPane);

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String selected = comboBox.getSelectedItem().toString();
					Connection con;

					con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pl", "postgres", "1425");

					Statement stmt = con.createStatement();
					ResultSet resultSett = stmt.executeQuery("Select * from courses where id = '" + selected + "'");
					ResultSetMetaData rsmdd = resultSett.getMetaData();
					int columnsNumberr = rsmdd.getColumnCount();
					String[] datas = new String[columnsNumberr];
					while (resultSett.next()) {

						for (int i = 1; i <= columnsNumberr; i++) {

							datas[i - 1] = resultSett.getString(i);

						}

					}

					textField.setText(datas[0]);
					textField_1.setText(datas[1]);
					textField_2.setText(datas[2]);

					table.setModel(DTM);
				} catch (Exception ed) {
					// TODO Auto-generated catch block

				}
			}
		});

		try {

			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pl", "postgres", "1425");
			Statement stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery("SELECT id from courses ORDER BY id ASC");
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			String[] columvalues = new String[columnsNumber];
			while (resultSet.next()) {

				for (int i = 1; i <= columnsNumber; i++) {

					columvalues[i - 1] = resultSet.getString(i);
					// String columnValue = resultSet.getString(i);

					// comboBox.addItem(columnValue);
				}
				for (String cont : columvalues) {
					comboBox.addItem(cont);

				}

			}

		} catch (Exception er) {
			System.out.println(er);
		}

		// CENTER IT

		center();
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
				menu.copened = false;
			}
		});
		lblNewLabel.setIcon(new ImageIcon(menu.class.getResource("close.png")));
		lblNewLabel.setBounds(816, 9, 23, 29);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				setState(JFrame.ICONIFIED);
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon(menu.class.getResource("size.png")));
		lblNewLabel_1.setBounds(791, 13, 42, 25);
		contentPane.add(lblNewLabel_1);
		

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(course.class.getResource("bg.png")));
		lblNewLabel_2.setBounds(0, 0, 900, 557);
		contentPane.add(lblNewLabel_2);
	}
}
