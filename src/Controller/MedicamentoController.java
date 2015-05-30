/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Medicamento;
import Model.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sistema.Util;

/**
 *
 * @author Mikaele
 */
public class MedicamentoController {

    public void inserirMedicamento(Medicamento m) throws SQLException {
        try {

            Util util = new Util();
            Connection conexao = util.conecta();
            String sql = "INSERT INTO medicamento  (nome,codigo) VALUES (?, ?)";
            PreparedStatement statement = conexao.prepareStatement(sql);// note que agora criamos um Statement de forma diferente
            statement.setString(1, m.getNome());
            statement.setString(2, m.getCodigo());

            int rowsInserted = statement.executeUpdate(); // Executa a inserção e retorna valor != 0 se inseriu (ID de inserção do banco)
            if (rowsInserted > 0) {
                System.out.println("Novo Medicamento inserido com sucesso");
            }
            statement.close();
            conexao.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
