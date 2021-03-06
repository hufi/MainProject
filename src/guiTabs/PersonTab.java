package guiTabs;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import person.Person;
import retailSystem.PersonDB;
import retailSystem.RetailSystemDriver;

public class PersonTab extends JPanel implements ActionListener, ItemListener {

	protected boolean editMode;
	protected boolean valid;
	protected int submitButtonMode;
	protected boolean emptiedList;
	protected PersonDB personDB;
	protected Vector<String> comboboxItems;
	protected Person person;
	protected String name, email, contactNumber, address, errorMessage;
	protected JButton newPersonButton, editPersonButton, deletePersonButton, submitButton,
			cancelButton, cancelEditButton;
	protected JTextField nameField, emailField, contactNumberField;
	protected JTextArea addressField;
	protected JLabel idLabel, idNumberLabel, nameLabel, emailLabel, contactNumberLabel,
			addressLabel, comboboxLabel;
	protected JScrollPane scrollPane;
	protected JComboBox<String> comboBox;
	protected DefaultComboBoxModel<String> comboboxModel;

	// private final boolean PRIVILEDGED_ACCESS = RetailSystemDriver.isPriviledged();

	/**
	 * Person tab comment
	 * 
	 * @param personDB
	 *            Database for people
	 */
	public PersonTab(PersonDB personDB) {

		// Initialise elements
		this.personDB = personDB;
		valid = false;
		emptiedList = false;
		submitButtonMode = 0;

		comboboxItems = new Vector<String>();
		comboboxModel = new DefaultComboBoxModel<String>(comboboxItems);
		comboBox = new JComboBox<String>(comboboxModel);

		idLabel = new JLabel("ID");
		idNumberLabel = new JLabel("1");
		nameLabel = new JLabel("Name");
		emailLabel = new JLabel("Email");
		contactNumberLabel = new JLabel("Contact Number");
		addressLabel = new JLabel("Address");

		comboboxLabel = new JLabel("");

		nameField = new JTextField();
		contactNumberField = new JTextField();
		emailField = new JTextField();
		addressField = new JTextArea(5, 20);
		addressField.setLineWrap(true);
		addressField.setWrapStyleWord(true);
		newPersonButton = new JButton("Add New");
		editPersonButton = new JButton("Edit");
		deletePersonButton = new JButton("Delete");
		submitButton = new JButton("Submit");
		cancelButton = new JButton("Cancel");
		cancelEditButton = new JButton("Cancel");
		scrollPane = new JScrollPane(addressField);

		/*
		 * if(!RetailSystemDriver.isPriviledged()){ newPersonButton.setEnabled(false);
		 * editPersonButton.setEnabled(false); deletePersonButton.setEnabled(false); }
		 */

		// Add listeners to elements
		newPersonButton.addActionListener(this);
		editPersonButton.addActionListener(this);
		deletePersonButton.addActionListener(this);
		submitButton.addActionListener(this);
		submitButton.setVisible(false);
		cancelButton.addActionListener(this);
		cancelButton.setVisible(false);
		cancelEditButton.addActionListener(this);
		cancelEditButton.setVisible(false);

		// Set the dimensions for elements
		idNumberLabel.setBounds(203, 7, 265, 20);
		nameField.setBounds(200, 30, 265, 20);
		nameField.setColumns(10);
		emailField.setBounds(200, 55, 265, 20);
		emailField.setColumns(10);
		contactNumberField.setBounds(200, 80, 265, 20);
		contactNumberField.setColumns(10);

		idLabel.setBounds(59, 10, 93, 14);
		nameLabel.setBounds(59, 33, 93, 14);
		emailLabel.setBounds(59, 58, 46, 14);
		contactNumberLabel.setBounds(59, 83, 93, 14);
		addressLabel.setBounds(59, 106, 93, 14);
		scrollPane.setBounds(200, 105, 265, 50);

		submitButton.setBounds(200, 250, 106, 23);
		newPersonButton.setBounds(64, 320, 130, 23);
		cancelButton.setBounds(64, 320, 130, 23);
		cancelEditButton.setBounds(199, 320, 130, 23);
		editPersonButton.setBounds(199, 320, 130, 23);
		deletePersonButton.setBounds(335, 320, 130, 23);

		comboboxLabel.setBounds(65, 285, 120, 20);
		comboBox.setBounds(200, 285, 265, 20);
		comboBox.addItemListener(this);

		// Set the layout for the tab as a whole
		setLayout(null);
		setVisible(true);
	}

	/**
	 * Refreshes the elements in the combo box when a person has been added to the list
	 * 
	 * @param list
	 *            The updated list
	 */
	public void addItemsToCombobox(ArrayList<Person> list) {
		comboboxItems.clear();
		String item = "";
		for (Person person : list) {
			item = person.getName();
			comboboxItems.add(item);
		}
		revalidate();
		repaint();
	}

	/**
	 * Change the text displayed in the text field
	 * 
	 * @param index
	 *            The selected index
	 * @param list
	 *            The list on people
	 */
	public void setTextField(int index, ArrayList<Person> list) {

		// Display data in the text field if the list isn't empty
		if (list.size() > 0) {
			person = list.get(index);
			idNumberLabel.setText("" + person.getId());
			nameField.setText(person.getName());
			addressField.setText(person.getAddress());
			contactNumberField.setText(person.getContactNumber());
			emailField.setText(person.getEmail());
			emptiedList = false;
		}

		// Update the combo box
		addItemsToCombobox(list);
		comboBox.setSelectedIndex(index);
	}

	/**
	 * Clears all data in all text fields
	 * 
	 * @param list
	 *            The list of people
	 */
	public void clearTextFields(ArrayList<Person> list) {
		// Get the ID of the person
		if (list.size() > 0) {
			idNumberLabel.setText("" + Person.getUniqueId());
		}
		else {
			idNumberLabel.setText("");
			emptiedList = true;
		}
		nameField.setText("");
		contactNumberField.setText("");
		emailField.setText("");
		addressField.setText("");
	}

	/**
	 * Adds all elements to the panel
	 */
	public void addAllElements() {

		add(newPersonButton);
		add(deletePersonButton);
		add(submitButton);
		add(editPersonButton);
		add(cancelButton);
		add(cancelEditButton);
		add(comboBox);
		add(comboboxLabel);

		add(nameField);
		add(contactNumberField);
		add(emailField);
		add(scrollPane);

		add(idLabel);
		add(idNumberLabel);
		add(nameLabel);
		add(contactNumberLabel);
		add(emailLabel);
		add(addressLabel);
	}

	/**
	 * Toggles whether fields can be edited or not
	 * 
	 * @param editable
	 *            Editable status of fields
	 */
	public void setFieldEditable(boolean editable) {
		nameField.setEditable(editable);
		contactNumberField.setEditable(editable);
		emailField.setEditable(editable);
		addressField.setEditable(editable);
	}

	/**
	 * Toggle enabling buttons
	 * 
	 * @param enabled
	 *            Enabled status of buttons
	 */
	public void enableButtons(boolean enabled) {
		newPersonButton.setEnabled(enabled);
		editPersonButton.setEnabled(enabled);
		deletePersonButton.setEnabled(enabled);
	}

	/**
	 * Removes a person from the database
	 * 
	 * @param person
	 *            The person to remove
	 * @param list
	 *            The list of people
	 */
	public void deletePerson(Person person, ArrayList<Person> list) {

		// Ask for confirmation and continue if yes is clicked
		int answer = JOptionPane.showConfirmDialog(null, "Do you really want to delete "
				+ person.getName() + "?", " CONFIRMATION ", JOptionPane.YES_NO_OPTION);
		if (answer == JOptionPane.YES_OPTION) {
			personDB.deletePersonFromList(person);
		}

		// Reset the data in the list to account for the removed person
		if (list.size() > 0) {
			setTextField(0, list);
		}
		else {
			setTextField(list.size() - 1, list);
			clearTextFields(list);
			deletePersonButton.setEnabled(false);
			editPersonButton.setEnabled(false);
			submitButton.setVisible(false);
		}
		revalidate();
		repaint();
	}

	/**
	 * Sets the fields editable for creating or editing person details
	 */
	public void personDetailsForm() {

		// Initially clear the text fields
		name = null;
		email = null;
		contactNumber = null;
		address = null;

		errorMessage = "";

		// Name field
		if (!nameField.getText().equals("")) {
			name = nameField.getText();
			nameLabel.setForeground(Color.black);
		}
		else {
			errorMessage = errorMessage + "Name field cannot be empty!\n";
			nameLabel.setForeground(Color.red);
		}

		// Email field
		if (!emailField.getText().equals("")) {

			email = (emailField.getText());
			emailLabel.setForeground(Color.black);
			if (!RetailSystemDriver.validateEmail(email)) {
				email = null;
				errorMessage = errorMessage
						+ "\nInvalid email address!!\nValid format is john.dough@example.com";
				emailLabel.setForeground(Color.red);
			}
		}
		else {
			errorMessage = errorMessage + "Email field cannot be empty!\n";
			emailLabel.setForeground(Color.red);
		}

		// Contact number field
		if (!contactNumberField.getText().equals("")) {

			contactNumber = (contactNumberField.getText());
			contactNumberLabel.setForeground(Color.black);

			if (!RetailSystemDriver.validateContactNumber(contactNumber)) {
				contactNumber = null;
				errorMessage = errorMessage
						+ "\nInvalid phone number!!\nValid format is 0xxxxxxxxx\nOnly numbers are allowed!";
				emailLabel.setForeground(Color.red);
			}
		}
		else {
			errorMessage = errorMessage + "Contact number field cannot be empty!\n";
			contactNumberLabel.setForeground(Color.red);
		}

		// Address field
		if (!addressField.getText().equals("")) {
			address = (addressField.getText());
			addressLabel.setForeground(Color.black);
		}
		else {
			errorMessage = errorMessage + "Address field cannot be empty!\n";
			addressLabel.setForeground(Color.red);
		}
	}

	/**
	 * Action listeners
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// New button clicked
		if (e.getSource() == newPersonButton) {
			submitButton.setVisible(true);
			newPersonButton.setVisible(false);
			deletePersonButton.setEnabled(false);
			editPersonButton.setEnabled(false);
			cancelButton.setVisible(true);
			submitButtonMode = 1;
			if (emptiedList) {
				idNumberLabel.setText("" + Person.getUniqueId());
			}
		}

		// Edit button clicked
		if (e.getSource() == editPersonButton) {
			submitButtonMode = 2;
			editPersonButton.setVisible(false);
			submitButton.setVisible(true);
			cancelEditButton.setVisible(true);
			newPersonButton.setEnabled(false);
			deletePersonButton.setEnabled(false);
		}

		// Cancel button clicked
		if (e.getSource() == cancelButton) {
			submitButton.setVisible(false);
			newPersonButton.setVisible(true);
			cancelButton.setVisible(false);

			if (!emptiedList) {
				deletePersonButton.setEnabled(true);
				editPersonButton.setEnabled(true);
			}
			nameLabel.setForeground(Color.black);
			emailLabel.setForeground(Color.black);
			addressLabel.setForeground(Color.black);
			contactNumberLabel.setForeground(Color.black);
		}

		// Cancel button clicked in edit more
		if (e.getSource() == cancelEditButton) {
			submitButton.setVisible(false);
			cancelEditButton.setVisible(false);
			deletePersonButton.setEnabled(true);
			newPersonButton.setEnabled(true);
			editPersonButton.setEnabled(true);
			editPersonButton.setVisible(true);

			nameLabel.setForeground(Color.black);
			emailLabel.setForeground(Color.black);
			addressLabel.setForeground(Color.black);
			contactNumberLabel.setForeground(Color.black);
		}

		// Refresh the view
		revalidate();
		repaint();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {

	}

}
