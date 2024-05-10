package web;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import javax.ejb.EJB;
import сonverter.ejb.ConverterLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Notebook
 */
@WebServlet(urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {

    public static ArrayList<String[]> values2 = new ArrayList<String[]>();
    public static String name;
    public static String halls, sits;
    public static boolean add_check = false;
    public static boolean isNumeric(String str) { 
  try {  
    Double.parseDouble(str);  
    return true;
  } catch(NumberFormatException e){  
    return false;  
  }  
}
    @EJB
    private ConverterLocal converter;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String amount = request.getParameter("amount");
            

            if (amount != null && amount.length() > 0) {
                
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Конвертер валют</title>");
                out.println("</head>");
                out.println("<body>");
                for(int i = 0; i < NewServlet.values2.size(); i++)
                {
                boolean check = converter.Action(amount, NewServlet.values2.get(i)[2]);
                if(check == true)
                {
                out.println("<p>Название = " + NewServlet.values2.get(i)[0] + "</p>");
                out.println("<p>Количество залов = " + NewServlet.values2.get(i)[1] + "</p>");
                out.println("<p>Количество мест = " + NewServlet.values2.get(i)[2] + "</p><br><br>");
                }
                }
               
                out.println("</body>");
                out.println("</html>");
            }
            else
            {
                this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }

        }
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
