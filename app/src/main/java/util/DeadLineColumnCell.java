
package util;

import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import model.Task;


public class DeadLineColumnCell extends DefaultTableCellRenderer{
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int col){
       
        JLabel label;
        label = (JLabel)super.getTableCellRendererComponent(table, value, 
                isSelected, hasFocus, row, col);
        
        label.setHorizontalAlignment(CENTER);
        
        TaskTableModel taskTableModel = (TaskTableModel)table.getModel();
        Task task = taskTableModel.getTasks().get(row);
        
        if(task.getDeadline().after(new Date())){
            label.setBackground(Color.green);
        }else {
               label.setBackground(Color.red);
        }
        
        return label;
    }
}
