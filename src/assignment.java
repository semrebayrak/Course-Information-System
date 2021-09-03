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
import javax.swing.event.MouseInputListener;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class assignment extends JFrame implements bg {
	private final Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pl", "postgres",
			"1425");
	private JComboBox comboBox;
	private JComboBox comboBox_1 = new JComboBox();
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
					assignment frame = new assignment();
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
	private JTextField textField_4;

	public assignment() throws SQLException {

		// MAKE MOVABLE

		makemovable();

		contentPane = new JPanel();

		JButton btnNewButton_2 = new JButton("Uploads");
		if (!login.teacher)
			btnNewButton_2.setText("Upload");

		else

			btnNewButton_2.setText("Uploads");

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String assid = "";
					if (comboBox.getSelectedItem() != null)
						assid = comboBox.getSelectedItem().toString().split(" ")[0];
					if (login.teacher) {

						uploads cr = new uploads();
						cr.newframe(assid);
					}

					else {

						upload cr = new upload();
						cr.newpanel(assid, false);

					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		btnNewButton_2.setBounds(69, 451, 139, 78);
		contentPane.add(btnNewButton_2);

		comboBox_1.setBounds(10, 75, 352, 20);
		contentPane.add(comboBox_1);

		JLabel lblNewLabel_3_2_1 = new JLabel("Details");
		lblNewLabel_3_2_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		lblNewLabel_3_2_1.setBounds(10, 231, 198, 25);
		contentPane.add(lblNewLabel_3_2_1);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(10, 267, 829, 162);
		if (!login.teacher)
			textField_4.setEditable(false);
		contentPane.add(textField_4);

		JLabel lblNewLabel_3_2 = new JLabel("Assignment Id");
		lblNewLabel_3_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		lblNewLabel_3_2.setBounds(10, 160, 198, 25);
		contentPane.add(lblNewLabel_3_2);

		JLabel lblNewLabel_3_1 = new JLabel("Due");
		lblNewLabel_3_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		lblNewLabel_3_1.setBounds(593, 160, 198, 25);
		contentPane.add(lblNewLabel_3_1);

		JLabel lblNewLabel_3 = new JLabel("Topic");
		lblNewLabel_3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(181, 160, 198, 25);
		contentPane.add(lblNewLabel_3);

		JButton btnNewButton = new JButton("Add assignment");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		comboBox = new JComboBox();

		comboBox.setBounds(10, 129, 823, 20);
		contentPane.add(comboBox);

		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {

					boolean containsnumber = true;

					for (char c : textField.getText().toString().toCharArray()) {
						if (!Character.isDigit(c)) {

							containsnumber = false;
						}
					}
					if (textField.getText().equals(""))
						containsnumber = false;

					if (datecheck(textField_2.getText())) {
						if (containsnumber) {

							Statement stmt = con.createStatement();
							ResultSet resultSet = stmt
									.executeQuery("Select * from assignments where id = '" + textField.getText() + "'");
							if (!resultSet.next()) {

								int resultSett = stmt.executeUpdate(
										"INSERT INTO assignments (id, topic, due,description,lecture) VALUES ('"
												+ textField.getText() + "'" + "," + "'" + textField_1.getText() + "'"
												+ "," + "'" + textField_2.getText() + "','" + textField_4.getText()
												+ "','" + comboBox_1.getSelectedItem().toString().split(" ")[0] + "')");

								JOptionPane.showMessageDialog(null, "Assignment Added");
								comboBox.addItem(textField.getText() + "           -     " + textField_1.getText());
								comboBox.setSelectedItem(
										textField.getText() + "           -     " + textField_1.getText());
								revalidate();
								repaint();

							}

							else
								JOptionPane.showMessageDialog(null, "Assignment Already Exists");
						}

						else
							JOptionPane.showMessageDialog(null, "Invalid Id");
					} else
						JOptionPane.showMessageDialog(null, "Invalid Date");
				} catch (Exception ed) {
					// TODO Auto-generated catch block
					System.out.println(ed);
				}

			}
		});

		btnNewButton.setBounds(704, 440, 135, 23);
		if (login.teacher)
			contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Delete assignment");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {

					Statement stmt = con.createStatement();
					ResultSet resultSet = stmt.executeQuery("Select * from assignments where id = '"
							+ comboBox.getSelectedItem().toString().split(" ")[0] + "'");
					if (resultSet.next()) {

						int resultSett = stmt.executeUpdate("DELETE FROM assignments where id = '"
								+ comboBox.getSelectedItem().toString().split(" ")[0] + "'");

						JOptionPane.showMessageDialog(null, "Assignment Removed");
						comboBox.removeItem(comboBox.getSelectedItem());
						revalidate();
						repaint();

					}
//////////// id deðiþtirildiðinde silinmiyor
					else
						JOptionPane.showMessageDialog(null, "Assignment Doesn't Exists");

				} catch (Exception ed) {
					// TODO Auto-generated catch block
					System.out.println(ed);
				}

			}
		});

		btnNewButton_1.setBounds(704, 474, 135, 23);
		if (login.teacher)
			contentPane.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("Edit assignment");
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {

					String temp = textField.getText();
					String temp2 = textField_1.getText();
					String temp3 = textField_2.getText();
					String temp4 = textField_4.getText();
					System.out.println(temp);
					boolean containsnumber = true;

					for (char c : textField.getText().toString().toCharArray()) {
						if (!Character.isDigit(c)) {

							containsnumber = false;
						}
					}
					if (textField.getText().equals(""))
						containsnumber = false;
					if (datecheck(textField_2.getText())) {
						if (containsnumber) {

							Statement stmt = con.createStatement();
							ResultSet resultSet = stmt.executeQuery("Select * from assignments where id = '"
									+ comboBox.getSelectedItem().toString().split(" ")[0] + "'");

							if (comboBox.getSelectedItem().toString().split(" ")[0]
									
									.equals(temp)) {

								// System.out.println(("UPDATE assignments SET id='" + textField.getText()
								// +"',name='" + textField_1.getText() +"',teacher='" + textField_2.getText() +
								// "' WHERE id='" + comboBox.getSelectedItem().toString()+ "'" ));
								int resultSett = stmt.executeUpdate("UPDATE assignments SET id='" + textField.getText()
										+ "',topic='" + textField_1.getText() + "',due='" + textField_2.getText()
										+ "',description='" + textField_4.getText() + "',lecture='"
										+ comboBox_1.getSelectedItem().toString().split(" ")[0] + "' WHERE id='"
										+ comboBox.getSelectedItem().toString().split(" ")[0] + "'");
								comboBox.removeItem(comboBox.getSelectedItem());

								comboBox.addItem(temp + "           -     " + temp2);
								comboBox.setSelectedItem(temp + "           -     " + temp2);
								revalidate();
								repaint();
								JOptionPane.showMessageDialog(null, "Assignment Edited");
							} else {
								JOptionPane.showMessageDialog(null, "Assignment Id cannot be Changed");
							}
						}

						else
							JOptionPane.showMessageDialog(null, "Invalid Id");
					} else
						JOptionPane.showMessageDialog(null, "Invalid Date");
				} catch (Exception ed) {
					// TODO Auto-generated catch block
					System.out.println(ed);
				}

			}
		});

		btnNewButton_1_1.setBounds(704, 511, 135, 23);
		if (login.teacher)
			contentPane.add(btnNewButton_1_1);

		textField = new JTextField();
		textField.setBounds(10, 195, 86, 25);
		if (!login.teacher)
			textField.setEditable(false);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(181, 195, 329, 25);
		if (!login.teacher)
			textField_1.setEditable(false);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(593, 195, 135, 25);
		if (!login.teacher)
			textField_2.setEditable(false);
		contentPane.add(textField_2);

		background(contentPane);

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Map<String, String> map = new HashMap<String, String>();
					String[] selectedarray = comboBox_1.getSelectedItem().toString().split(" ");
					String selected = selectedarray[0];

					Statement stmt = con.createStatement();
					ResultSet resultSett = stmt.executeQuery(
							"SELECT assignments.id,assignments.topic,assignments.due,assignments.description "
									+ "from assignments  inner JOIN courses on courses.id = assignments.lecture where courses.id = '"
									+ selected + "'and assignments.id='"
									+ comboBox.getSelectedItem().toString().split(" ")[0]
									+ "' ORDER BY assignments.id ASC");
					ResultSetMetaData rsmdd = resultSett.getMetaData();
					int columnsNumberr = rsmdd.getColumnCount();
					String[] datas = new String[columnsNumberr];
					String tempid = "";
					while (resultSett.next()) {

						for (int i = 1; i <= columnsNumberr; i++) {

							map.put(String.valueOf(i - 1), resultSett.getString(i));
							// datas[i - 1] = resultSett.getString(i);

						}

					}

					textField.setText(map.get("0"));
					textField_1.setText(map.get("1"));
					textField_2.setText(map.get("2"));
					textField_4.setText(map.get("3"));

					/*
					 * textField.setText(datas[0]); textField_1.setText(datas[1]);
					 * textField_2.setText(datas[2]); textField_4.setText(datas[3]);
					 */

					revalidate();
					repaint();

				} catch (Exception ed) {
					// TODO Auto-generated catch block

				}
			}
		});

		try {

			Statement stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery("SELECT id,name from courses ORDER BY id ASC");
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int columnsNumber = rsmd.getColumnCount();

			while (resultSet.next()) {
				String tempword = "";
				for (int i = 1; i <= columnsNumber; i++) {

					String columnValue = resultSet.getString(i);
					if (i > 1)
						tempword = tempword + "           -     " + columnValue;
					else
						tempword = columnValue;

				}
				comboBox_1.addItem(tempword);

			}

		} catch (Exception er) {
			System.out.println(er);
		}

		Combobox2Call();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				comboBox.removeAllItems();
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_4.setText("");
				Combobox2Call();

			}
		});
		// SECOND BUTTON

		// CENTER IT
		center();
	}

	void Combobox2Call() {
		try {

			Statement stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery("SELECT assignments.id,assignments.topic from assignments "
					+ "inner JOIN courses on courses.id = assignments.lecture where courses.id =  '"
					+ comboBox_1.getSelectedItem().toString().split(" ")[0] + "' ORDER BY id ASC");
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int columnsNumber = rsmd.getColumnCount();

			while (resultSet.next()) {
				String tempword = "";
				for (int i = 1; i <= columnsNumber; i++) {

					String columnValue = resultSet.getString(i);
					if (i > 1)
						tempword = tempword + "           -     " + columnValue;
					else
						tempword = columnValue;

				}
				comboBox.addItem(tempword);

				revalidate();
				repaint();
			}

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
				menu.asopened = false;
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

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(course.class.getResource("bg.png")));
		lblNewLabel_2.setBounds(0, 0, 900, 557);
		contentPane.add(lblNewLabel_2);
	}

	public boolean datecheck(String date) {
		System.out.println(date.split("/")[0]);
		String dates[] = date.split("/");

		System.out.println(date);
		if (dates.length != 3) {
			return false;
		}

		else {

			String temp = dates[0] + dates[1] + dates[2];
			if (dates[1].length() > 2 && dates[0].length() > 2 && dates[2].length() > 4)
				return false;

			else {

				for (char c : temp.toCharArray()) {
					if (!Character.isDigit(c)) {

						return false;
					}
				}
				if (dates[1].length() == 1) {
					dates[1] = "0" + dates[1];
				}
				if (dates[0].length() == 1) {
					dates[0] = "0" + dates[0];
				}

				if (dates[1].equals("04") || dates[1].equals("06") || dates[1].equals("09") || dates[1].equals("11")) {
					if (Integer.valueOf(dates[0]) > 30)
						return false;

				} else if (dates[1].equals("02")) {
					if (Integer.valueOf(dates[2]) % 4 != 0) {
						if (Integer.valueOf(dates[0]) > 28)
							return false;
					} else {
						if (Integer.valueOf(dates[0]) > 29)
							return false;
					}
				} else if (Integer.valueOf(dates[0]) > 31)
					return false;

				
				if(Integer.valueOf(dates[1])> 12) {
					return false;
				}
				return true;


			}

		}

	}
}
