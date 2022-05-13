package controller;

import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The contacto. */
	JavaBeans contacto = new JavaBeans();
	
	/** The dao. */
	DAO dao = new DAO();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		if (action.equals("/main")) {
			contactos(request, response);
		} else if (action.equals("/insert")) {
			adicionarContacto(request, response);
		} else if (action.equals("/select")) {
			listarContacto(request, response);
		} else if (action.equals("/update")) {
			editarContacto(request, response);
		} else if (action.equals("/delete")) {
			deletarContacto(request, response);
		} else if (action.equals("/relatorio")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("index.html");
		}

	}

	/**
	 * Contactos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void contactos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<JavaBeans> lista = dao.listarContactos();
		request.setAttribute("contacto", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}

	/**
	 * Adicionar contacto.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void adicionarContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		contacto.setNome(request.getParameter("nome"));
		contacto.setTelefone(request.getParameter("telefone"));
		contacto.setEmail(request.getParameter("email"));
		dao.inserirContacto(contacto);
		response.sendRedirect("main");
	}

	/**
	 * Listar contacto.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void listarContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		contacto.setId(request.getParameter("id"));
		dao.selecionarContacto(contacto);
		request.setAttribute("id", contacto.getId());
		request.setAttribute("nome", contacto.getNome());
		request.setAttribute("telefone", contacto.getTelefone());
		request.setAttribute("email", contacto.getEmail());
		RequestDispatcher rd = request.getRequestDispatcher("editarContacto.jsp");
		rd.forward(request, response);
	}

	/**
	 * Editar contacto.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editarContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		contacto.setId(request.getParameter("id"));
		contacto.setNome(request.getParameter("nome"));
		contacto.setTelefone(request.getParameter("telefone"));
		contacto.setEmail(request.getParameter("email"));
		dao.updateContacto(contacto);
		response.sendRedirect("main");
	}

	/**
	 * Deletar contacto.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void deletarContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			contacto.setId(request.getParameter("id"));
			dao.deleteContacto(contacto);
			response.sendRedirect("main");
		} catch (Exception e) {
		}
	}

	/**
	 * Gerar relatorio.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document document = new Document();
		try {
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "inline; filename=" + "contactos.pdf");
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();
			document.add(new Paragraph("Lista de Contactos"));
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			PdfPTable table = new PdfPTable(3);
			PdfPCell col1 = new PdfPCell(new Paragraph("NOME"));
			PdfPCell col2 = new PdfPCell(new Paragraph("TELEFONE"));
			PdfPCell col3 = new PdfPCell(new Paragraph("EMAIL"));
			table.addCell(col1);
			table.addCell(col2);
			table.addCell(col3);
			ArrayList<JavaBeans> lista = dao.listarContactos();
			for (int i = 0; i < lista.size(); i++) {
				table.addCell(lista.get(i).getNome());
				table.addCell(lista.get(i).getTelefone());
				table.addCell(lista.get(i).getEmail());
			}
			document.add(table);
			document.close();

		} catch (Exception e) {
			System.out.println(e);
			document.close();
		}
	}

}
