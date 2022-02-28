package user.frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import user.daos.UserRepository;
import user.entities.UserDTO;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTextPane;

public class Index extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6260015785963067777L;
	
	private JPanel contentPane;
	private JTextField idTextField;
	
	// Pointers to panel elements
	private JComboBox<UserDTO> usersComboBox;
	private JTextPane userTextPane;
	
	private UserRepository userRepository = new UserRepository();
	
	private void listUsers() {
		List<UserDTO> users = userRepository.listUsers();
		for (UserDTO user : users) {
			this.usersComboBox.addItem(user);
		}
	}
	
	private void getUser() {
		UserDTO user = (UserDTO) this.usersComboBox.getSelectedItem();
		UserDTO auxUser = userRepository.getUser(user.getId().toString());
		this.userTextPane.setText(auxUser.printData());
	}
	
	private void createUser() {
		UserDTO user = (UserDTO) this.usersComboBox.getSelectedItem();
		user.setId(null);
		UserDTO auxUser = userRepository.createUser(user);
		if (auxUser != null) {
			this.userTextPane.setText(auxUser.printData());
			JOptionPane.showMessageDialog(this, "Success");
		}
	}

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index frame = new Index();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the frame.
	 */
	public Index() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 491, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel instructionsLabel = new JLabel("Instrucciones");
		GridBagConstraints gbc_instructionsLabel = new GridBagConstraints();
		gbc_instructionsLabel.gridwidth = 2;
		gbc_instructionsLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_instructionsLabel.insets = new Insets(0, 0, 5, 5);
		gbc_instructionsLabel.gridx = 0;
		gbc_instructionsLabel.gridy = 0;
		contentPane.add(instructionsLabel, gbc_instructionsLabel);
		
		JComboBox<UserDTO> usersComboBox = new JComboBox<UserDTO>();
		GridBagConstraints gbc_usersComboBox = new GridBagConstraints();
		gbc_usersComboBox.gridwidth = 2;
		gbc_usersComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_usersComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_usersComboBox.gridx = 0;
		gbc_usersComboBox.gridy = 1;
		contentPane.add(usersComboBox, gbc_usersComboBox);
		
		// Take the comboBox pointer
		this.usersComboBox = usersComboBox;
		
		// Get the users list
		listUsers();
		
		idTextField = new JTextField();
		GridBagConstraints gbc_idTextField = new GridBagConstraints();
		gbc_idTextField.gridwidth = 2;
		gbc_idTextField.insets = new Insets(0, 0, 5, 5);
		gbc_idTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_idTextField.gridx = 0;
		gbc_idTextField.gridy = 2;
		contentPane.add(idTextField, gbc_idTextField);
		idTextField.setColumns(10);
		
		JButton findButton = new JButton("Find");
		findButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getUser();
			}
		});
		GridBagConstraints gbc_findButton = new GridBagConstraints();
		gbc_findButton.insets = new Insets(0, 0, 5, 5);
		gbc_findButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_findButton.gridx = 0;
		gbc_findButton.gridy = 3;
		contentPane.add(findButton, gbc_findButton);
		
		JButton createButton = new JButton("Create");
		createButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createUser();
			}
		});
		GridBagConstraints gbc_createButton = new GridBagConstraints();
		gbc_createButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_createButton.insets = new Insets(0, 0, 5, 0);
		gbc_createButton.gridx = 1;
		gbc_createButton.gridy = 3;
		contentPane.add(createButton, gbc_createButton);
		
		JTextPane userTextPane = new JTextPane();
		GridBagConstraints gbc_userTextPane = new GridBagConstraints();
		gbc_userTextPane.gridwidth = 2;
		gbc_userTextPane.insets = new Insets(0, 0, 0, 5);
		gbc_userTextPane.fill = GridBagConstraints.BOTH;
		gbc_userTextPane.gridx = 0;
		gbc_userTextPane.gridy = 4;
		contentPane.add(userTextPane, gbc_userTextPane);
		
		// Take the userTextPane pointer
		this.userTextPane = userTextPane;
	}

}
