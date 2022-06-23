
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;


public class TaskController {
    
    public List<Task> findAll(int idProject){
        String sql = "SELECT * FROM tasks WHERE idProject = ?";
        
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        List<Task> tasks = new ArrayList<>();
        
        try {
            conn = ConnectionFactory.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, idProject);
            
            rs = stm.executeQuery();
            
            while(rs.next()){
               Task task = new Task();
               task.setId(rs.getInt("id"));
               task.setIdProject(rs.getInt("idProject"));
               task.setName(rs.getString("name"));
               task.setDescription(rs.getString("description"));
               task.setCompleted(rs.getBoolean("completed"));
               task.setNotes(rs.getString("notes"));
               task.setDeadline(rs.getDate("deadline"));
               task.setCreatedAt(rs.getDate("createdAt"));
               task.setUpdatedAt(rs.getDate("updatedAt"));
               
               tasks.add(task);
               
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar as tarefas ", e);
        }finally{
            ConnectionFactory.closeConnection(conn, stm, rs);
        }
        
        
       return tasks; 
    }
    
    public void save(Task task){
      String sql = "INSERT INTO tasks"
 + "(idProject, name, description,completed,notes,deadline,createdAt,updatedAt)"
 + " VALUES(?,?,?,?,?,?,?,?)";
        
        Connection conn = null;
        PreparedStatement stm = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, task.getIdProject());
            stm.setString(2, task.getName());
            stm.setString(3, task.getDescription());
            stm.setBoolean(4, task.isCompleted());
            stm.setString(5, task.getNotes());
            stm.setDate(6, new Date(task.getDeadline().getTime()));
            stm.setDate(7, new Date(task.getCreatedAt().getTime()));            
            stm.setDate(8, new Date(task.getUpdatedAt().getTime()));  
            stm.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar a tarefa ", e);
        }finally{
            ConnectionFactory.closeConnection(conn, stm);
            
        }
        
    }
    
    public void update(Task task){
     String sql = "UPDATE tasks SET idProject = ?, name = ?, description = ?,"
      + "completed = ?, notes = ?, deadline = ?, createdAt = ?, updatedAt = ? "
      + "WHERE id = ?";
     
     Connection conn = null;
     PreparedStatement stm = null;
     
        try {
            conn = ConnectionFactory.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, task.getIdProject());
            stm.setString(2, task.getName());
            stm.setString(3, task.getDescription());
            stm.setBoolean(4, task.isCompleted());
            stm.setString(5, task.getNotes());
            stm.setDate(6, new Date(task.getDeadline().getTime()));
            stm.setDate(7, new Date(task.getCreatedAt().getTime()));            
            stm.setDate(8, new Date(task.getUpdatedAt().getTime()));
            stm.setInt(9, task.getId());
            stm.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar a tarefa ", e);
        }finally{
            
            ConnectionFactory.closeConnection(conn, stm);
        }
     
    }
    
    public void removeById(int taskId) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement stm = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, taskId);
            stm.execute();
            
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar tarefa ", e);
                   
        }finally{
            
            ConnectionFactory.closeConnection(conn, stm);
        }
    }
    
}
