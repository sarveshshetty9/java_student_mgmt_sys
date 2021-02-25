
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class UpdateFrame extends JFrame{
	Container c;
	JLabel lblRno, lblName;
	JTextField txtRno, txtName;
	JButton btnUpdate, btnBack;
	JRadioButton rbm, rbf;
	ButtonGroup bg1;
	JPanel p1, p2, p3;

	UpdateFrame(){
		c = getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

		p1 = new JPanel();
		lblRno = new JLabel("Rno : ");
		lblName = new JLabel("Name : ");
		txtRno = new JTextField(4);
		txtName = new JTextField(10);
		
		p1.add(lblRno);
		p1.add(txtRno);
		p1.add(lblName);
		p1.add(txtName);
		c.add(p1);
		
		p2 = new JPanel();
		rbm = new JRadioButton("Male");
		rbm.setActionCommand("Male");
		rbf = new JRadioButton("Female");
		rbf.setActionCommand("Female");
		bg1 = new ButtonGroup();
		bg1.add(rbm);
		bg1.add(rbf);
		p2.add(rbm);
		p2.add(rbf);
		c.add(p2);

		p3 = new JPanel();
		btnBack = new JButton("Back");
		btnUpdate = new JButton("Update");
		p3.add(btnUpdate);
		p3.add(btnBack);
		c.add(p3);



		btnUpdate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				try{
					try{
					String rno = txtRno.getText();
					int r = Integer.parseInt(rno);
					if (r <= 0){
						JOptionPane.showMessageDialog(new JDialog(), "Roll no. cannot be less than 0. ");
					}
					else{
						String name = txtName.getText();
						String gender = bg1.getSelection().getActionCommand();
						DbHandler db = new DbHandler();
						db.updateStudent(Integer.parseInt(rno), name, gender);
					}
					}
					
					catch(NumberFormatException e){
						JOptionPane.showMessageDialog(new JDialog(), "Roll no. can be integer only. ");
					}	
				}
				catch(NullPointerException f){
					JOptionPane.showMessageDialog(new JDialog(), "Please enter the details. ");
				}
				

			}
		});

		btnBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				MainFrame a = new MainFrame();
				dispose();
			}
		});

		setTitle("Update Student Detail");
		setSize(400,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

}