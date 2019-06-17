package m2i.cours;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class Servlet_Couleurs
 */
@WebServlet("/Servlet_Couleurs")
public class Servlet_Couleurs extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet_Couleurs() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		
		// Get the printwriter object from response to write the required json object to the output stream      
		PrintWriter out = response.getWriter();
		
		List<Couleurs> lstCouleurs = new ArrayList<Couleurs>();

		for (Couleurs c : Couleurs.values()) {
			lstCouleurs.add(c);
		}

		System.out.println("\n\nListe de couleurs : " + lstCouleurs);
		
		String resultat = new Gson().toJson(lstCouleurs);
		
		System.out.println("\n\nJSON : " + resultat);
		
		// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
		out.print(resultat);
		out.flush();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
