
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;


public class ProjectController {
    
    public List<Project> findAll(){
        String sql = "SELECT * FROM projects";
        
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        List<Project> projects = new ArrayList<>();
        
        try {
            conn = ConnectionFactory.getConnection();
            stm = conn.prepareStatement(sql);
            
            rs = stm.executeQuery();
            
            while(rs.next()){
                Project project = new Project();
                
                project.setId(rs.getInt("id"));
                project.setName(rs.getString("name"));
                project.setDescription(rs.getString("description"));
                project.setCreatedAt(rs.getDate("createdAt"));
                project.setUpdatedAt(rs.getDate("updatedAt"));
                
                projects.add(project);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar os projetos ", e);
        }finally{
            ConnectionFactory.closeConnection(conn, stm, rs);
        }
        return projects;
    }
    
    public void save(Project project){
        String sql = "INSERT INTO projects(name,description,createdAt,updatedAt)"
                + "VALUES(?,?,?,?)";
              
        Connection conn = null;
        PreparedStatement stm = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, project.getName());
            stm.setString(2, project.getDescription());
            stm.setDate(3, new Date(project.getCreatedAt().getTime()));
            stm.setDate(4, new Date(project.getUpdatedAt().getTime()));
            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar o projeto ", e);
        }finally{
            ConnectionFactory.closeConnection(conn, stm);
            
        }
        
    }
    
    
    public void update(Project project){
        String sql = "UPDATE projects SET name = ?, description = ?,"
                + " createdAt = ?, updatedAt = ? WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement stm = null;
        
        try {
            
            conn = ConnectionFactory.getConnection();
            stm = conn.prepareStatement(sql);
            
            stm.setString(1, project.getName());
            stm.setString(2, project.getDescription());
            stm.setDate(3, new Date(project.getCreatedAt().getTime()));
            stm.setDate(4, new Date(project.getUpdatedAt().getTime()));
            stm.setInt(5, project.getId());
            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar projeto ", e);
        }finally{
            
            ConnectionFactory.closeConnection(conn, stm);
        }
    }
    
    public void removeById(int projectId){
        String sql = "DELETE FROM projects WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement stm = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, projectId);
            stm.execute();
            
               
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar projeto ", e);
                   
        }finally{
            
            ConnectionFactory.closeConnection(conn, stm);
        }
    }
}
