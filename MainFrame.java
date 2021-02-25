
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class MainFrame extends JFrame{
	
	ImageIcon image1;
	JLabel label1;
	Container c;
	JButton btnAdd, btnView, btnUpdate, btnDelete;
	JPanel p1, p2;
	MainFrame(){
		//c = getContentPane();
		setContentPane(new JLabel(new ImageIcon("bg.jpeg")));
		c = getContentPane();
		setLayout(new FlowLayout());
		//c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		//frame.add(new JLabel(new ImageIcon("image.jpg")));
		
		// image1 = new ImageIcon(getClass().getResource("image.jpg"));
		// label1 = new JLabel(image1);
		// p1 = new JPanel();
		// p1.add(label1);
		// c.add(label1);
		

		p2 = new JPanel();
		p2.setBackground(Color.RED);
		p2.setMaximumSize(new Dimension(200, 200));

		btnAdd = new JButton("Add");
		btnView = new JButton("View");
		btnUpdate = new JButton("Update");
		btnDelete = new JButton("Delete");
		
		p2.add(btnAdd);
		p2.add(btnView);
		p2.add(btnUpdate);
		p2.add(btnDelete);
		c.add(p2);

		btnAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				AddFrame a = new AddFrame();
				dispose();
			}
		});

		btnView.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				ViewFrame a = new ViewFrame();
				dispose();
			}
		});

		btnUpdate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				UpdateFrame a = new UpdateFrame();
				dispose();
			}
		});

		btnDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				DeleteFrame a = new DeleteFrame();
				dispose();
			}
		});

		setTitle("Student Management System");
		setSize(350,200);
		setSize(400,400);
		setLocationRelativeTo(null);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				int x = JOptionPane.showConfirmDialog(null, "Do you want to exit ?", "Are you sure?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (x == JOptionPane.YES_OPTION)
					e.getWindow().dispose();
			}
		});
		setVisible(true);
	}
	public static void main(String args[]){
		MainFrame m = new MainFrame();
	}
}

class DbHandler{
	public void addStudent(int rno, String name, String gender){
			try{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
			String sql = "insert into student values(?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1,rno);
			pst.setString(2,name);
			pst.setString(3,gender);
			int r = pst.executeUpdate();
			JOptionPane.showMessageDialog(new JDialog(), r + " records inserted.");
			con.close();
			}
			catch(SQLException e){
				JOptionPane.showMessageDialog(new JDialog(), "Issue : " + e);
			}
	}

	public String viewStudent(){
		StringBuffer sb = new StringBuffer();
		try{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
			String sql = "select * from student order by rno";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				int rno = rs.getInt(1);
				String name = rs.getString(2);
				String gender = rs.getString(3);
				// String format = "|%1$-[B]11s[/B]|%2$-30s|%3$-10s|%4$-20s|%3$-10s|%4$-20s\n";
				// sb.append(String.format(format,"Rno", rno, "Name", name, "Gender", gender));
				sb.append(" | Rno : "+rno+" | Name : "+name+" | Gender : "+gender+"\n");
			}
			rs.close();			
			con.close();
		}
		catch(SQLException e){
		
		}
		return sb.toString();
	}

	public void updateStudent(int ro, String n, String g){
		try{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
			Statement s1 = con.createStatement();
			String s2 = "update student set name = '"+n+"', gender = '"+g+"' where rno = '"+ro+"'";
			int r = s1.executeUpdate(s2);
			if (r==0){
				JOptionPane.showMessageDialog(new JDialog(), "Roll no "+ro+" does not found." );
			}
			else{
				JOptionPane.showMessageDialog(new JDialog(), r + " record updated.");
			}
			
			con.close();
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(new JDialog(), "Issue : " + e);
		}
	}

	public void deleteStudent(int rn){
		try{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
			Statement s1 =con.createStatement();
			String s2 = "delete from student where rno='"+rn+"'";
			int r1 = s1.executeUpdate(s2);
			if (r1==0)
				JOptionPane.showMessageDialog(new JDialog(),"Roll no "+rn+" not found.");
			else
				JOptionPane.showMessageDialog(new JDialog(), r1 + " record deleted.");
			con.close();
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(new JDialog(), "Issue : " + e);
		}
	}
}