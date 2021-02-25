
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class DeleteFrame extends JFrame{
	Container c;
	JLabel lblRno;
	JTextField txtRno;
	JButton btnDelete;
	JButton btnBack;
	JPanel p1, p2;
	DeleteFrame(){
		c = getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		
		p1 = new JPanel();
		lblRno = new JLabel("Rno : ");
		txtRno = new JTextField(4);
		
		p1.add(lblRno);
		p1.add(txtRno);
		c.add(p1);

		p2 = new JPanel();
		btnDelete = new JButton("Delete");
		btnBack = new JButton("Back");
		p2.add(btnDelete);
		p2.add(btnBack);
		c.add(p2);

		btnDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				try{
					try{
						String rno = txtRno.getText();
						DbHandler db = new DbHandler();
						db.deleteStudent(Integer.parseInt(rno));
					}
					catch(NullPointerException f){
						JOptionPane.showMessageDialog(new JDialog(), "Please enter the roll number. "+ f);
					}	
				}
				catch(NumberFormatException e){
						JOptionPane.showMessageDialog(new JDialog(), "Please enter integers only. "+e);
					}
			}
		});

		btnBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				MainFrame a = new MainFrame();	
				dispose();
			}
		});

		setTitle("Delete Student");
		setSize(400,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
}


