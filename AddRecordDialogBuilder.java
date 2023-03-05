
public class AddRecordDialogBuilder {
	private EmployeeDetails parent;

	public AddRecordDialogBuilder parent(EmployeeDetails parent) {
		 this.parent = parent;
		return this;
	}

	public AddRecordDialog build() {
		AddRecordDialog dialog = new AddRecordDialog(parent);
		  dialog.setTitle("Add Record");
		 dialog.setModal(true);
		dialog.getRootPane().setDefaultButton(dialog.save);
		dialog.setSize(500, 370);
		dialog.setLocation(350, 250);
		dialog.setVisible(true);
		return dialog;
	}
}
