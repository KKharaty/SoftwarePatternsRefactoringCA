
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;


public abstract class SearchDialog extends JDialog implements ActionListener {
    EmployeeDetails parent;
    JButton search, cancel;
    JTextField searchField;

    public SearchDialog(EmployeeDetails parent, String title) {
        setTitle(title);
        setModal(true);
        this.parent = parent;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JScrollPane scrollPane = new JScrollPane(searchPane());
        setContentPane(scrollPane);

        getRootPane().setDefaultButton(search);

        setSize(500, 190);
        setLocation(350, 250);
        setVisible(true);
    }

    public Container searchPane() {
        JPanel searchPanel = new JPanel(new GridLayout(3,1));
        JPanel textPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JLabel searchLabel;

        searchPanel.add(new JLabel(getSearchLabel()));

        textPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        textPanel.add(searchLabel = new JLabel(getSearchFieldLabel()));
        searchLabel.setFont(this.parent.font1);
        textPanel.add(searchField = new JTextField(20));
        searchField.setFont(this.parent.font1);
        searchField.setDocument(new JTextFieldLimit(20));

        buttonPanel.add(search = new JButton("Search"));
        search.addActionListener(this);
        search.requestFocus();

        buttonPanel.add(cancel = new JButton("Cancel"));
        cancel.addActionListener(this);

        searchPanel.add(textPanel);
        searchPanel.add(buttonPanel);

        return searchPanel;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            try {
                performSearch();
                dispose();
            }
            catch (NumberFormatException num) {
                searchField.setBackground(new Color(255, 150, 150));
                JOptionPane.showMessageDialog(null, getErrorMessage());
            }
        }
        else if (e.getSource() == cancel)
            dispose();
    }

    public abstract String getSearchLabel();

    public abstract String getSearchFieldLabel();

    public abstract String getErrorMessage();

    public abstract void performSearch();
}

   class SearchByIdDialog extends SearchDialog {

    public SearchByIdDialog(EmployeeDetails parent) {
		// TODO Auto-generated method stub
        super(parent, "Search by ID");
    }

    public String getSearchLabel() {
		// TODO Auto-generated method stub
        return "Search by ID";
    }

    public String getSearchFieldLabel() {
		// TODO Auto-generated method stub
        return "Enter ID:";
    }

    public String getErrorMessage() {
		// TODO Auto-generated method stub
        return "Wrong ID format!";
    }

    public void performSearch() {
        Double.parseDouble(searchField.getText());
        parent.searchByIdField.setText(searchField.getText());
        parent.searchEmployeeById();
    }
}

  class SearchBySurnameDialog extends SearchDialog {

    public SearchBySurnameDialog(EmployeeDetails parent) {
		// TODO Auto-generated method stub
        super(parent, "Search by Surname");
    }

    public String getSearchLabel() {
		// TODO Auto-generated method stub
        return "Search by Surname";
    }

	@Override
	public String getSearchFieldLabel() {
		// TODO Auto-generated method stub
		return "Enter Surname:";
	}

	@Override
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return "No Surname!";
	}

	@Override
	public void performSearch() {
		// TODO Auto-generated method stub
		  Double.parseDouble(searchField.getText());
	        parent.searchBySurnameField.setText(searchField.getText());
	        parent.searchEmployeeBySurname();
	}


}