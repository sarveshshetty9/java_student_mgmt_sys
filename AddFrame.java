
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class AddFrame extends JFrame{
	Container c;
	JLabel lblRno, lblName;
	JTextField txtRno, txtName;
	JButton btnSave, btnBack;
	JRadioButton rbm, rbf;
	ButtonGroup bg1;
	JPanel p1, p2, p3;

	AddFrame(){
		setContentPane(new JLabel(new ImageIcon("bg.jpeg")));
		setLayout(new FlowLayout());
		c = getContentPane();
		//c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		
		p1 = new JPanel();
		lblRno = new JLabel("Rno : ");
		txtRno = new JTextField(4);
		lblName = new JLabel("Name : ");
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
		btnSave = new JButton("Save");
		btnBack = new JButton("Back");
		p3.add(btnSave);
		p3.add(btnBack);
		c.add(p3);

		btnSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				try{
					try{
						String rno = txtRno.getText();
						int r = Integer.parseInt(rno);
						if (r <= 0){
							JOptionPane.showMessageDialog(new JDialog(), "Roll no. cannot be less than 1. ");
						}
						else{
							String name = txtName.getText();
							String vname = name.trim();
							if (vname.equals("") || vname == null){
								JOptionPane.showMessageDialog(new JDialog(), "Name cannot be null");		
							}else if (!vname.matches("[a-zA-Z]*")){
								JOptionPane.showMessageDialog(new JDialog(), "Please enter valid name.");		
							}else{

								String gender = bg1.getSelection().getActionCommand();
								DbHandler db = new DbHandler();
								db.addStudent(Integer.parseInt(rno),name,gender);	
							}
						}
							
					}
					catch(NumberFormatException e){
						JOptionPane.showMessageDialog(new JDialog(), "Roll no. can be integers only. "+e);
					}	
				}
				catch(NullPointerException f){
					JOptionPane.showMessageDialog(new JDialog(), "Please enter roll no. and name and gender. "+f);	
				}
			}
		});

		btnBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				MainFrame a = new MainFrame();
				dispose();
			}
		});

		setTitle("Add Student");
		setSize(400,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	
}
