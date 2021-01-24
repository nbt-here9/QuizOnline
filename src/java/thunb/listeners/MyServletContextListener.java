/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thunb.listeners;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

/**
 *
 * @author Banh Bao
 */
public class MyServletContextListener implements javax.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        ServletContext context = sce.getServletContext();
        Map<String, String> indexPage = new HashMap<>();
        FileReader siteMap = null;
        BufferedReader br = null;
        try {
            siteMap = new FileReader(context.getRealPath("/WEB-INF/siteMap.txt"));
            br = new BufferedReader(siteMap);
            while (br.ready()) {
                String line = br.readLine();
                indexPage.put(line.split("=")[0], line.split("=")[1]);
                System.out.println(line);
            } //end if EOF

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyServletContextListener.class.getName()).log(Level.SEVERE, "MyServletContextListener_FileNotFoundException:", ex);
        } catch (IOException ex) {
            Logger.getLogger(MyServletContextListener.class.getName()).log(Level.SEVERE, "MyServletContextListener_IOException:", ex);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (siteMap != null) {
                    siteMap.close();
                }
            } catch (IOException e) {
                Logger.getLogger(MyServletContextListener.class.getName()).log(Level.SEVERE, "MyServletContextListener_IOException:", e);

            }

        }

//        Set<String> keys = indexPage.keySet();
//        for (String k : keys) {
//            System.out.print(k + " = ");
//            System.out.println(indexPage.get(k));
//        }
        context.setAttribute("SITE_MAP", indexPage);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
