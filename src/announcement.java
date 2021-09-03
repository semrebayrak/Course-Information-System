import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

public class announcement extends JFrame implements bg {
	JComboBox comboBox_1 = new JComboBox();
	private final Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pl", "postgres",
			"1425");
	Statement stmt = con.createStatement();
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
					announcement frame = new announcement();
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

	public announcement() throws SQLException {

		// MAKE MOVABLE

		makemovable();

		contentPane = new JPanel();

		comboBox_1.setBounds(10, 73, 425, 20);
		contentPane.add(comboBox_1);

		JLabel lblNewLabel_3_2_1 = new JLabel("Announcement");
		lblNewLabel_3_2_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		lblNewLabel_3_2_1.setBounds(10, 242, 198, 25);
		contentPane.add(lblNewLabel_3_2_1);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(10, 278, 829, 162);
		if (!login.teacher)
			textField_4.setEditable(false);
		contentPane.add(textField_4);

		JLabel lblNewLabel_3_2 = new JLabel("Announcement Id");
		lblNewLabel_3_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		lblNewLabel_3_2.setBounds(10, 170, 198, 25);
		contentPane.add(lblNewLabel_3_2);

		JLabel lblNewLabel_3_1 = new JLabel("Date");
		lblNewLabel_3_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		lblNewLabel_3_1.setBounds(583, 170, 198, 25);
		contentPane.add(lblNewLabel_3_1);

		JLabel lblNewLabel_3 = new JLabel("Announcement Name");
		lblNewLabel_3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(181, 170, 198, 25);
		contentPane.add(lblNewLabel_3);

		JButton btnNewButton = new JButton("Add Announcement");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		comboBox = new JComboBox();

		comboBox.setBounds(10, 139, 823, 20);
		contentPane.add(comboBox);

		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				boolean containsnumber = true;

				for (char c : textField.getText().toString().toCharArray()) {
					if (!Character.isDigit(c)) {

						containsnumber = false;
					}
				}
				if (textField.getText().equals(""))
					containsnumber = false;

				if (!containsnumber) {
					try {

						String tempdata = "";

						ResultSet resultSet = stmt.executeQuery("SELECT id from announcements ORDER BY id ASC ");
						ResultSetMetaData rsmdd = resultSet.getMetaData();
						int columnsNumberr = rsmdd.getColumnCount();
						String[] datas = new String[columnsNumberr];
						String tempid = "";
						while (resultSet.next()) {

							for (int i = 1; i <= columnsNumberr; i++) {

								tempdata = resultSet.getString(i);

							}

						}

						int tempdat = Integer.parseInt(tempdata) + 1;

						tempdata = String.valueOf(tempdat);
						System.out.println(tempdata);
						Insert(tempdata);
					} catch (Exception d) {
						System.out.println(d);
					}

				} else {

					Insert();

				}

			}
		});

		btnNewButton.setBounds(619, 450, 135, 23);
		if (login.teacher)
			contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Delete Announcement");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				try {

					Connection con;

					con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pl", "postgres", "1425");

					Statement stmt = con.createStatement();
					ResultSet resultSet = stmt.executeQuery("Select * from announcements where id = '"
							+ comboBox.getSelectedItem().toString().split(" ")[0] + "'");
					if (resultSet.next()) {

						int resultSett = stmt.executeUpdate("DELETE FROM announcements where id = '"
								+ comboBox.getSelectedItem().toString().split(" ")[0] + "'");

						JOptionPane.showMessageDialog(null, "Announcement Deleted");
						comboBox.removeItem(comboBox.getSelectedItem());
						revalidate();
						repaint();

					}

					else
						JOptionPane.showMessageDialog(null, "Announcement Doesn't Exists");

				} catch (Exception ed) {
					// TODO Auto-generated catch block
					System.out.println(ed);
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

		btnNewButton_1.setBounds(619, 484, 135, 23);
		if (login.teacher)
			contentPane.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("Edit announcement");
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					String temp = textField.getText();
					String temp2 = textField_1.getText();
					String temp3 = textField_2.getText();
					Connection con;

					con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pl", "postgres", "1425");

					boolean containsnumber = true;

					for (char c : textField.getText().toString().toCharArray()) {
						if (!Character.isDigit(c)) {

							containsnumber = false;
						}
					}
					if (textField.getText().equals(""))
						containsnumber = false;
					if (datecheck(textField_2.getText()) == true) {
						if (containsnumber) {

							Statement stmt = con.createStatement();
							ResultSet resultSet = stmt.executeQuery("Select * from announcements where id = '"
									+ comboBox.getSelectedItem().toString().split(" ")[0] + "'");

							// System.out.println(("UPDATE announcements SET id='" + textField.getText()
							// +"',name='" + textField_1.getText() +"',teacher='" + textField_2.getText() +
							// "' WHERE id='" + comboBox.getSelectedItem().toString()+ "'" ));

							if (comboBox.getSelectedItem().toString().split(" ")[0]
									.equals(temp)) {

								int resultSett = stmt.executeUpdate("UPDATE announcements SET id='"
										+ textField.getText() + "',announcement='" + textField_1.getText() + "',date='"
										+ textField_2.getText() + "',description='" + textField_4.getText()
										+ "',lecture='" + comboBox_1.getSelectedItem().toString().split(" ")[0]
										+ "' WHERE id='" + comboBox.getSelectedItem().toString().split(" ")[0] + "'");
								comboBox.removeItem(comboBox.getSelectedItem());

								comboBox.addItem(temp + "           -     " + temp2 + "           -     " + temp3);
								comboBox.setSelectedItem(
										temp + "           -     " + temp2 + "           -     " + temp3);
								revalidate();
								repaint();
								JOptionPane.showMessageDialog(null, "Announcement Edited");
							}

							else {
								JOptionPane.showMessageDialog(null, "Announcement Id Cannot be Changed");
							}
						} else
							JOptionPane.showMessageDialog(null, "Invalid Id");
					} else
						JOptionPane.showMessageDialog(null, "Invalid Date");

				} catch (Exception ed) {
					// TODO Auto-generated catch block
					System.out.println(ed);
				}

			}
		});

		btnNewButton_1_1.setBounds(619, 520, 135, 23);
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
		textField_2.setBounds(583, 195, 256, 25);
		if (!login.teacher)
			textField_2.setEditable(false);
		contentPane.add(textField_2);

		background(contentPane);

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String[] selectedarray = comboBox.getSelectedItem().toString().split(" ");
					String selected = selectedarray[0];
					Connection con;

					con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pl", "postgres", "1425");

					Statement stmt = con.createStatement();
					ResultSet resultSett = stmt
							.executeQuery("Select * from announcements where id = '" + selected + "'");
					ResultSetMetaData rsmdd = resultSett.getMetaData();
					int columnsNumberr = rsmdd.getColumnCount();

					List<String> mylist = new ArrayList<String>();
					String[] datas = new String[columnsNumberr];

					while (resultSett.next()) {

						for (int i = 1; i <= columnsNumberr; i++) {

							mylist.add(resultSett.getString(i));
							// datas[i - 1] = resultSett.getString(i);

						}

					}

					textField.setText(mylist.get(0));
					textField_1.setText(mylist.get(1));
					textField_2.setText(mylist.get(2));
					textField_4.setText(mylist.get(3));
					/*
					 * textField.setText(datas[0]); textField_1.setText(datas[1]);
					 * textField_2.setText(datas[2]); textField_4.setText(datas[3]);
					 */

				} catch (Exception ed) {
					// TODO Auto-generated catch block

				}
			}
		});

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
		// CENTER IT

		center();
	}

	void Insert() {
		try {

			Connection con;

			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pl", "postgres", "1425");

			Statement stmt = con.createStatement();
			ResultSet resultSet = stmt
					.executeQuery("Select * from announcements where id = '" + textField.getText() + "'");
			if (datecheck(textField_2.getText()) == true) {
			if (!resultSet.next()) {

				int resultSett = stmt
						.executeUpdate("INSERT INTO announcements (id, announcement, date,description) VALUES ('"
								+ textField.getText() + "'" + "," + "'" + textField_1.getText() + "'" + "," + "'"
								+ textField_2.getText() + "','" + textField_4.getText() + "')");

				JOptionPane.showMessageDialog(null, "Announcement Added");
				comboBox.addItem(textField.getText() + "           -     " + textField_1.getText() + "           -     "
						+ textField_2.getText());
				comboBox.setSelectedItem(textField.getText() + "           -     " + textField_1.getText()
						+ "           -     " + textField_2.getText());
				revalidate();
				repaint();

			}

			else
				JOptionPane.showMessageDialog(null, "Announcement Already Exists");}
			else
				JOptionPane.showMessageDialog(null, "Invalid Date");

		} catch (Exception ed) {
			// TODO Auto-generated catch block
			System.out.println(ed);
		}

	}

	void Insert(String id) {
		try {

			ResultSet resultSet = stmt
					.executeQuery("Select * from announcements where id = '" + textField.getText() + "'");
			if (!resultSet.next()) {

				int resultSett = stmt
						.executeUpdate("INSERT INTO announcements (id, announcement, date,description) VALUES ('" + id
								+ "'" + "," + "'" + textField_1.getText() + "'" + "," + "'" + textField_2.getText()
								+ "','" + textField_4.getText() + "')");

				JOptionPane.showMessageDialog(null, "Announcement Added");
				comboBox.addItem(
						id + "           -     " + textField_1.getText() + "           -     " + textField_2.getText());
				comboBox.setSelectedItem(
						id + "           -     " + textField_1.getText() + "           -     " + textField_2.getText());
				revalidate();
				repaint();

			}

			else
				JOptionPane.showMessageDialog(null, "Announcement Already Exists");

		} catch (Exception ed) {
			// TODO Auto-generated catch block
			System.out.println(ed);
		}

	}

	void Combobox2Call() {
		try {

			Statement stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery(
					"SELECT announcements.id,announcements.announcement,announcements.date from announcements "
							+ "inner JOIN courses on courses.id = announcements.lecture where courses.id =  '"
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