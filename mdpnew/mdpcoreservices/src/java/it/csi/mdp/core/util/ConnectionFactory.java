/* Generated by Together */

package it.csi.mdp.core.util;

import it.csi.mdp.core.util.Constants;

import java.sql.Connection;
import javax.sql.DataSource;
import org.apache.log4j.*;
/**
 * Il metodo statico(?) getDaoInstance restituisce una istanza della classe individuata dal parametro daoName.
 * Il metodo statico(?) getConnection restituisce una connessione al db ottenuta dal data source.
 * Il data source e singletone e viene mantenuto in in un attributo statico(?) .
 * Il nome del data source e' incapsulato(?).
 */
public abstract class ConnectionFactory {

  private static DataSource dataSource = null;
  private static int numConnessioni = 0;

  /** Restitusce una connessione ottenendola dal data source. Il data source
   *   viene posto nel corrispondene attributo s t a t i c o di classe.
   */
   public static synchronized Connection getConnection() {
     Connection conn = null;
     Logger logger;
     logger = Logger.getLogger(Constants.APPLICATION_CODE);
     logger.info("[ConnectionFactory::getConnection]: BEGIN");
     try {
//          System.out.println("VALORE DATASOURCE : "+dataSource);
        logger.debug("VALORE DATASOURCE : "+dataSource);
            if (dataSource == null) {

               logger.debug("[ConnectionFactory::getConnection]: Il dataSource e' nullo!");
              javax.naming.Context context = new javax.naming.InitialContext();
              dataSource = (DataSource)
                  // context.lookup("java:comp/env/jdbc/PeasDS");
              	context.lookup("java:mdpcoreservicessrv/jdbc/MdpnewDS");
            }

       logger.debug("[ConnectionFactory::getConnection]: Connessione numero "+(++numConnessioni)+"\t****");
        conn = dataSource.getConnection();
      }
      catch (Exception e) {
        e.printStackTrace();
        // throw new EJBException("Errore nella look up del dataSource:" + e.toString());
      }
      logger.info("[ConnectionFactory::getConnection]: END");
      return conn;
   }
}