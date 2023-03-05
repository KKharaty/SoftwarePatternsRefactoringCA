/*
 * 
 * This is a class for limiting input in text fields
 * 
 * */

import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
// set text field input limits
class JTextFieldLimit extends PlainDocument {
    private final int limit;
    
    
    public JTextFieldLimit(int limit) {
        this.limit = limit;
    }
// end JTextFieldLimit

    
  JTextFieldLimit(int limit, boolean upper) {
    super();
    this.limit = limit;
  }// end JTextFieldLimit

  
  public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
      if (str == null || getLength() + str.length() > limit) {
          JOptionPane.showMessageDialog(null, "For Input " + limit + " characters maximum!");
          return;
      }
      super.insertString(offset, str, attr);
  }// end insertString
}// end class JTextFieldLimits