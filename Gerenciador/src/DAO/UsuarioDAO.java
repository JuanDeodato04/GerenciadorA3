package DAO;
import DTO.JogadorDTO;
import DTO.UsuarioDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UsuarioDAO {
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<JogadorDTO> lista = new ArrayList<>();
    
    public ResultSet autenticarUsuario(UsuarioDTO objusuariodto){
        conn = new Db().ConectBD();
        try {
            
            String sql = "select * from usuarios where nome_usuario = ? and senha_usuario = ? ";
            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objusuariodto.getNome());
            pstm.setString(2, objusuariodto.getSenha_usuario());
            
            rs = pstm.executeQuery();
            return rs;
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro AutenticarUsuario: "+erro.getMessage());
            return null;
            
        }       
    }
    
    public ResultSet autenticarUsuarioCpf(UsuarioDTO objusuariodto){
        conn = new Db().ConectBD();
        try {
            
            String Sql = "select * from usuarios where cpf_usuario = ? and senha_usuario = ? ";
            
            pstm = conn.prepareStatement(Sql);
            pstm.setString(1, objusuariodto.getCpf_usuario());
            pstm.setString(2, objusuariodto.getSenha_usuario());
            
            rs = pstm.executeQuery();
            return rs;
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro AutenticarUsuarioCPF" + erro.getMessage());
            return null;
        }
    }
    
    public void cadastrarJogador(JogadorDTO objjogadordto){
        conn = new Db().ConectBD();
        try {
            String sql = "insert into ELENCO_ATUAL (nome, posicao, salario, numero, situacao, idade) values (?,?,?,?,?,?)";
            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objjogadordto.getNome());
            pstm.setString(2, objjogadordto.getPosicao_jogador());
            pstm.setDouble(3, objjogadordto.getSalario_jogador());
            pstm.setInt(4, objjogadordto.getNumero_jogador());
            pstm.setString(5, objjogadordto.getSituacao_jogador());
            pstm.setInt(6, objjogadordto.getIdade());
            
            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {
            
            JOptionPane.showMessageDialog(null, "Erro UsuarioDAO: " + erro);
        }
    }
    public void excluirJogador(int id){
        conn = new Db().ConectBD();
        try {
            String sql = "delete from ELENCO_ATUAL where id = ?";
            
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            
            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {
            
            JOptionPane.showMessageDialog(null, "Erro UsuarioDAO: " + erro);
        }
    }
    
    public ArrayList<JogadorDTO> listarJogadores(){
        conn = new Db().ConectBD();
        try {
            String sql = "select * from ELENCO_ATUAL";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                
                JogadorDTO objjogadordto = new JogadorDTO();
                objjogadordto.setId_jogador(rs.getInt("ID"));
                objjogadordto.setIdade(rs.getInt("IDADE"));
                objjogadordto.setNome(rs.getString("NOME"));
                objjogadordto.setNumero_jogador(rs.getInt("NUMERO"));
                objjogadordto.setPosicao_jogador(rs.getString("POSICAO"));
                objjogadordto.setSalario_jogador(rs.getDouble("SALARIO"));
                objjogadordto.setSituacao_jogador(rs.getString("SITUACAO"));
                
                lista.add(objjogadordto);
                
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro Listar Jogadores: " + erro);
        }
        return lista;
    }
    
    public void alterarStatus(JogadorDTO objjogadordto){
        conn = new Db().ConectBD();
        
        try {
            String sql = "update ELENCO_ATUAL set nome = ?, posicao = ?, salario = ?, numero = ?, situacao = ?, idade = ? where id = ?";
            pstm = conn.prepareStatement(sql);
            
            if("lesionado".equals(objjogadordto.getSituacao_jogador().toLowerCase())){
                objjogadordto.setSalario_jogador(objjogadordto.getSalario_jogador()-(0.10*objjogadordto.getSalario_jogador()));
            }
            
            pstm.setString(1, objjogadordto.getNome());
            pstm.setString(2, objjogadordto.getPosicao_jogador());
            pstm.setDouble(3, objjogadordto.getSalario_jogador());
            pstm.setInt(4, objjogadordto.getNumero_jogador());
            pstm.setString(5, objjogadordto.getSituacao_jogador());
            pstm.setInt(6, objjogadordto.getIdade());
            pstm.setInt(7, objjogadordto.getId_jogador());
            
            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ERRO Alterar Status: " + erro);
        }
    }
}
 