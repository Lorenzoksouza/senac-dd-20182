package model.dao.aula05;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dao.Banco;
import model.vo.aula05.Produto;

public class ProdutoDAO {

	public int inserir(Produto p){
		int novoId = -1;

		String sql = "INSERT INTO PRODUTO (NOME, FABRICANTE, VALOR, PESO) "
				+ "VALUES (?,?,?,?)";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao);

		try {
			prepStmt.setString(1, p.getNome());
			prepStmt.setString(2, p.getFabricante());
			prepStmt.setDouble(3, p.getValor());
			prepStmt.setDouble(4, p.getPeso());

			prepStmt.executeUpdate(sql);

			ResultSet rs = prepStmt.getGeneratedKeys();
			novoId = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println("Erro ao inserir produto");
		} finally{
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}

		return novoId;
	}

	public boolean atualizar(Produto p){
		boolean sucessoUpdate = false;

		String sql = "UPDATE PRODUTO P SET NOME=?, FABRICANTE=?, VALOR=?, PESO=?"
				+ "WHERE P.ID = ?";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao);

		try {
			prepStmt.setString(1, p.getNome());
			prepStmt.setString(2, p.getFabricante());
			prepStmt.setDouble(3, p.getValor());
			prepStmt.setDouble(4, p.getPeso());
			prepStmt.setDouble(5, p.getId());

			int codigoRetorno = prepStmt.executeUpdate(sql);
			
			if(codigoRetorno == 1){
				sucessoUpdate = true;
			}

		} catch (SQLException e) {
			System.out.println("Erro ao atualizar produto");
		}finally{
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}

		return sucessoUpdate;
	}
	//DELETE

	//SELECT (TODOS E POR ID)
}