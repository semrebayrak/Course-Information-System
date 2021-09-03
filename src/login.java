import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class login extends JFrame {
	public static String username;
	static boolean flag = false;
	static boolean flag2 = false;
	static boolean teacher;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField pwdDeupc;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void newpanel(boolean teache) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					teacher = teache;
					login frame = new login();
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
	
	static int value;
	
	public login() {
		
		setUndecorated(true);
		
		getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 241, 383);
		contentPane = new JPanel();
	
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabelf = new JLabel("");
		lblNewLabelf.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabelf.setBackground(new Color(0, 255, 0));
		lblNewLabelf.setForeground(new Color(0, 0, 0));
		lblNewLabelf.setIcon(new ImageIcon(course.class.getResource("login.png")));
		lblNewLabelf.setBounds(0, 0, 241, 383);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(63, 320, 119, 2);
		contentPane.add(separator_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(63, 260, 119, 2);
		contentPane.add(separator);
		JLabel lblNewLabeld = new JLabel("");
		lblNewLabeld.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
			}
		});
		
		lblNewLabeld.setIcon(new ImageIcon(menu.class.getResource("close.png")));
		lblNewLabeld.setBounds(208, 9, 23, 29);
		contentPane.add(lblNewLabeld);

		JLabel lblNewLabell = new JLabel("");
		lblNewLabell.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		lblNewLabell.setIcon(new ImageIcon(menu.class.getResource("size.png")));
		lblNewLabell.setBounds(185, 13, 42, 25);
		contentPane.add(lblNewLabell);
		

		
		
		
	
	
		
		
		
		
		
		
		
		
	
		
		txtUsername = new JTextField();
		txtUsername.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsername.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		
		
		txtUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(!flag) 
				txtUsername.setText("");
				flag = true;
			}
		});
		txtUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(!flag) 
				txtUsername.setText("");
				flag = true;
			}
		});
		txtUsername.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!flag) 
				txtUsername.setText("");
				flag = true;
			}
		});
		
		if (!teacher) 
			value =0;
		else
			value =1;
		
		
	//	txtUsername.setText("yalcin");
		txtUsername.setBorder(null);
		txtUsername.setBackground(SystemColor.menu);
		txtUsername.setForeground(new Color(0, 0, 0));
		txtUsername.setBounds(69, 240, 109, 19);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		txtUsername.setOpaque(false);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 17));
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setBounds(80, 215, 75, 14);
		contentPane.add(lblNewLabel);
		
		
		
		
		
		final int val = value;
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(69, 280, 100, 14);
		contentPane.add(lblNewLabel_1);
		
		pwdDeupc = new JPasswordField();
		pwdDeupc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(!flag2)
					pwdDeupc.setText("");
					flag2=true;
			}
		});
		pwdDeupc.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(!flag2)
					pwdDeupc.setText("");
					flag2=true;
			}
		});
		pwdDeupc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!flag2)
					pwdDeupc.setText("");
					flag2=true;
			}
		});
		pwdDeupc.setToolTipText("");
		pwdDeupc.setHorizontalAlignment(SwingConstants.CENTER);
		pwdDeupc.setEchoChar('*');
		pwdDeupc.setBorder(null);
		pwdDeupc.setBounds(55, 300, 129, 20);
		contentPane.add(pwdDeupc);
	//	pwdDeupc.setText("deupc");
		pwdDeupc.setOpaque(false);
		
		btnNewButton = new JButton("Login");
		btnNewButton.setBorder(null);
		btnNewButton.setOpaque(false);
		btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "";
					Class.forName("org.postgresql.Driver");
					Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pl","postgres","1425");
					
					Statement stmt=con.createStatement();
					
					

					
					
					switch (val) {
					case 0: 
						query = "Select * from students where id='"+txtUsername.getText()+"' and password='"+pwdDeupc.getText()+"'";
						break;
					case 1: 
						query = "Select * from teachers where id='"+txtUsername.getText()+"' and password='"+pwdDeupc.getText()+"'";
						break;
					}
					
				
						

					ResultSet rs = stmt.executeQuery(query);
					if(rs.next()) {
						username = txtUsername.getText();
						JOptionPane.showMessageDialog(null, "Success");
						menu page = new menu();
						dispose();
						page.newframe();

						
						
					}
					
					else {
						JOptionPane.showMessageDialog(null, "Failed");
					   
					    
					}
					
					}
				catch(Exception ed) {
					JOptionPane.showMessageDialog(null, "An error occured");
					dispose();
				}
			}
		});
		btnNewButton.setBounds(142, 341, 89, 23);
		contentPane.add(btnNewButton);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		contentPane.add(lblNewLabelf);
		// CENTER IT
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (screenSize.width-w)/2;
        int y = (screenSize.height-h)/2;

        // Move the window
        this.setLocation(x, y);
		
	}
	
}
