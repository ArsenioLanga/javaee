package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	
	/** The driver. */
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/bdagenda?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "root";

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * Inserir contacto.
	 *
	 * @param contacto the contacto
	 */
	public void inserirContacto(JavaBeans contacto) {
		String sql = "insert into contactos (nome, telefone, email) values (?,?,?)";
		try {
			Connection con = conectar();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, contacto.getNome());
			stmt.setString(2, contacto.getTelefone());
			stmt.setString(3, contacto.getEmail());
			stmt.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Listar contactos.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listarContactos() {
		ArrayList<JavaBeans> contacto = new ArrayList<>();
		String sql = "select * from contactos order by nome";
		try {
			Connection con = conectar();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String nome = rs.getString(2);
				String telefone = rs.getString(3);
				String email = rs.getString(4);
				contacto.add(new JavaBeans(id, nome, telefone, email));
			}
			con.close();
			return contacto;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * Selecionar contacto.
	 *
	 * @param contacto the contacto
	 */
	public void selecionarContacto(JavaBeans contacto) {
		String sql = "select * from contactos where id = ?";
		try {
			Connection con = conectar();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, contacto.getId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				contacto.setId(rs.getString(1));
				contacto.setNome(rs.getString(2));
				contacto.setTelefone(rs.getString(3));
				contacto.setEmail(rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * Update contacto.
	 *
	 * @param contacto the contacto
	 */
	public void updateContacto(JavaBeans contacto) {
		String sql = "update contactos set nome = ?, telefone = ?, email = ? where id = ?";
		try {
			Connection con = conectar();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, contacto.getNome());
			stmt.setString(2, contacto.getTelefone());
			stmt.setString(3, contacto.getEmail());
			stmt.setString(4, contacto.getId());
			stmt.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Delete contacto.
	 *
	 * @param contacto the contacto
	 */
	public void deleteContacto(JavaBeans contacto) {
		String sql = "delete from contactos where id = ?";
		try {
			Connection con = conectar();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, contacto.getId());
			stmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

