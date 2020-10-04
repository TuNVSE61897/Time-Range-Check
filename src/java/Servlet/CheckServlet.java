/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Core.TimeCore;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author グエン　トゥ
 */
public class CheckServlet extends HttpServlet {

    private final String invalid = "invalid.html";
    private final String index = "index.jsp";

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
        PrintWriter out = response.getWriter();
        PrintStream out2 = new PrintStream(System.out, true, "UTF-8");
        try {
            System.out.println("Check");
            boolean empty = false;
            String url = invalid;

            /*============
            入力データ処理　START
            ============*/
            
            String sSpecTimeHour = request.getParameter("txtSpecificTimeHour");
            String sSpecTimeMin = request.getParameter("txtSpecificTimeMin");
            String sHourFrom = request.getParameter("txtHourFrom");
            String sMinFrom = request.getParameter("txtMinFrom");
            String sHourTo = request.getParameter("txtHourTo");
            String sMinTo = request.getParameter("txtMinTo");
            String day = request.getParameter("rdbnDay");
            int specTimeHour = 0;
            int hourFrom = 0;
            int hourTo = 0;
            int minFrom = 0;
            int minTo = 0;
            int specTimeMin = 0;

            if (sMinFrom.length() != 0) {
                minFrom = Integer.parseInt(sMinFrom);
            }
            if (sMinTo.length() != 0) {
                minTo = Integer.parseInt(sMinTo);
            }
            if (sSpecTimeMin.length() != 0) {
                specTimeMin = Integer.parseInt(sSpecTimeMin);
            }

            /*============
            入力データ処理　END
            ============*/
            
            /*============
            入力データエラー確認　START
            ============*/

            //確認したい時刻は空の
            if (sSpecTimeHour.length() == 0) {
                request.setAttribute("ERRORSPECTIME", "確認したい時刻を入力してください！");
                empty = true;
                System.out.println("確認したい時刻を入力してください");
            } else {
                specTimeHour = Integer.parseInt(sSpecTimeHour);

            }

            //開始時刻は空の
            if (sHourFrom.length() == 0) {
                request.setAttribute("ERRORHOURFROM", "開始時刻を入力してください！");
                empty = true;
            } else {
                hourFrom = Integer.parseInt(sHourFrom);
            }

            //終了時刻は空の
            if (sHourTo.length() == 0) {
                request.setAttribute("ERRORHOURTO", "終了時刻を入力してください！");
                empty = true;
            } else {
                hourTo = Integer.parseInt(sHourTo);
            }

            //終了時刻は翌日
            if (day.equals("tomorrow")) {
                hourTo = hourTo + 24;
            }

            //終了時刻は開始時刻の後になるかどうかを確認
            if (hourFrom > hourTo) {
                request.setAttribute("RESULTERROR", "終了時刻は開始時刻の後になければならない！");
                empty = true;
            }
            if (hourFrom == hourTo) {
                if (minFrom > minTo) {
                    request.setAttribute("RESULTERROR", "終了時刻は開始時刻の後になければならない！");
                    empty = true;
                }
            }

            /*============
            入力データエラー確認　END
            ============*/
            
            
            /*============
            指定した時間の範囲内に含まれるかどうかを調べる　START
            ============*/
            
            if (empty) {
                url = index;
            } else {
                
                TimeCore core = new TimeCore();
                boolean result = core.checkResult(specTimeHour, specTimeMin, hourFrom, minFrom, hourTo, minTo);
                if (result) {
                    String output = String.format("%s時%s分が、%s時%s分～%s時%s分の内に含まれる！", sSpecTimeHour, sSpecTimeMin, sHourFrom, sMinFrom, sHourTo, sMinTo);
                    request.setAttribute("RESULT", output);
                    url = index;
                } else {
                    String output = String.format("%s時%s分が、%s時%s分～%s時%s分の内に含まれない！", sSpecTimeHour, sSpecTimeMin, sHourFrom, sMinFrom, sHourTo, sMinTo);
                    request.setAttribute("RESULTERROR", output);
                    url = index;
                }

            }
            
            /*============
            指定した時間の範囲内に含まれるかどうかを調べる　END
            ============*/

            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);

        } catch (Exception ex) {
            log("Check_Class" + ex.getMessage());
        } finally {
            out.close();
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
