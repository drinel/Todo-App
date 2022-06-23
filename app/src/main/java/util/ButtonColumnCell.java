
package util;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;


public class ButtonColumnCell extends DefaultTableCellRenderer{
    
    private String buttonType;

    public ButtonColumnCell(String buttonType) {
        this.buttonType = buttonType;
    }

  
    

    public String getButtonType() {
        return buttonType;
    }

    public void setButtonType(String buttonType) {
        this.buttonType = buttonType;
    }
    
    
      @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int col){
       
        JLabel label;
        label = (JLabel)super.getTableCellRendererComponent(table, value, 
                isSelected, hasFocus, row, col);
        
        label.setHorizontalAlignment(CENTER);
        
        label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/" + buttonType + ".png")));
            
        return label;
        
    }
    
}
