package tp1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author rbastide
 */
public class NewServlet extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String customid = request.getParameter("customerId");
                        DataSource = myDataSource;
                        String sql = "select * from customer where customer_id = ?";
                try (   Connection connection = myDataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
                ) {
                        // Définir la valeur du paramètre
			stmt.setInt(1, customid);
                        ResultSet rs = stmt.executeQuery(); // Un ResultSet pour parcourir les enregistrements du résultat
			 
			if (rs.next()) {
				int id = rs.getInt("customer_id");
                                String name = rs.getString("name");
                                String adr = rs.getString("addressline1");
                                res = new CustomerEntity(id,name,adr);
			}
			
			

		}  catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new DAOException(ex.getMessage());
                    }

			if (parametreCorrect(nom, sexe)) {
				String message = "";
				switch (sexe) {
					case "H":
						message = "monsieur";
						break;
					case "F":
						message = "madame";
						break;
				}
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Bienvenue</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1>Bienvenue</h1>");
				out.printf("Bonjour %s %s <br>", message, nom);
				out.println("<a href='formulaire.html'>Recommencer</a>");
				out.println("</body>");
				out.println("</html>");
			} else {
				response.sendRedirect("formulaire.html");
			}
		}
	}

	private boolean parametreCorrect(String nom, String sexe) {
		return ((nom != null) && !nom.isEmpty() && ("H".equals(sexe) || "F".equals(sexe)));
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
