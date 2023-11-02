package jdbc1;

import java.awt.EventQueue;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTable table;
	private JTextField txtbookid;
	PreparedStatement pst;
	Connection conn;
	ResultSet rs;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ClassNotFoundException 
	 */
	public Login() throws ClassNotFoundException {
		initialize();
		table_load();
	
	
	}
	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		 conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xepdb1","system","tiger");
		
	}
	public void table_load() {
		try {
			connect();
			pst=conn.prepareStatement("select*from tbl_book");
			rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			conn.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	
		
		
		
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 632, 416);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Snap ITC", Font.ITALIC, 14));
		lblNewLabel.setBounds(247, 38, 116, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registration", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		panel.setBounds(38, 71, 265, 160);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 41, 86, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Edition");
		lblNewLabel_2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 77, 86, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Price");
		lblNewLabel_3.setFont(new Font("Tempus Sans ITC", Font.BOLD, 14));
		lblNewLabel_3.setBounds(10, 115, 46, 14);
		panel.add(lblNewLabel_3);
		
		txtbname = new JTextField();
		txtbname.setBounds(117, 39, 119, 20);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(117, 75, 119, 20);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(117, 113, 119, 20);
		panel.add(txtprice);
		
		JButton btnsave = new JButton("Save");
		btnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bname,edition,price;
				bname=txtbname.getText();
				edition=txtedition.getText();
				price=txtprice.getText();
				
				try {
//					Class.forName("oracle.jdbc.driver.OracleDriver");
//					Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xepdb1","system","tiger");
					connect();
					pst=conn.prepareStatement("insert into tbl_book values(sequence1.nextval,?,?,?)");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record added");
					table_load();
					txtbname.setText(" ");
					txtedition.setText(" ");
					txtprice.setText("");
					conn.close();
					
					
					
				}
				catch(SQLException e1) {
					e1.printStackTrace();
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnsave.setBounds(38, 242, 73, 23);
		frame.getContentPane().add(btnsave);
		
		JButton btndelete = new JButton("Delete");
		btndelete.setBounds(500, 304, 73, 23);
		frame.getContentPane().add(btndelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(339, 83, 237, 182);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(38, 287, 265, 79);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("Book Id");
		lblNewLabel_2_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(10, 24, 86, 14);
		panel_1.add(lblNewLabel_2_1);
		
		txtbookid = new JTextField();
		txtbookid.setColumns(10);
		txtbookid.setBounds(121, 22, 119, 20);
		panel_1.add(txtbookid);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(134, 242, 73, 23);
		frame.getContentPane().add(btnClear);
		
		JButton btnexit = new JButton("Exit");
		btnexit.setBounds(230, 242, 73, 23);
		frame.getContentPane().add(btnexit);
		
		JButton btnupdate = new JButton("Update");
		btnupdate.setBounds(343, 304, 89, 23);
		frame.getContentPane().add(btnupdate);
	}
}
