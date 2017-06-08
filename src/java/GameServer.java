import java.io.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(urlPatterns = {"/GameServer","/UpdateGame"})
public final class GameServer extends HttpServlet {

    private Board board = new Board();
    private Players players = new Players();
    
     

    @Override
    public void init(final ServletConfig config) {
        board.intGameBoard();
        players.initPlayers();
        Logger.getGlobal().log(Level.INFO, "Started Game");
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/event-stream;charset=UTF-8");

        try (final PrintWriter out = response.getWriter()) {

            
                synchronized (board) {
                    out.println("data:{"+board.printBoard()+", "+players.printPlayers()+"}");
                    out.println();
                    out.flush();
                }
                
            
    }    }
    
     @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        
       
          int count=1;
          PrintWriter out = response.getWriter();
            HttpSession session = request.getSession();
            String keyPressed=request.getParameter("keypress");
          
            
            if (session.isNew()) {

                session.setAttribute("player",count);
             
                count++;
            } else {
                int player = (int) session.getAttribute("player");
               
              
                
            }
          
    }

    @Override
    public void destroy() {
       
    }
    
   
}