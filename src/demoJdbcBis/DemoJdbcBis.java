package demoJdbcBis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DemoJdbcBis {

	public static void main(String[] args) throws SQLException {
		
				String url = "jdbc:mysql://localhost/formation?useSSL=false"; 
				String user = "root";
			    String pwd= "19My10SQL81"; 
			    
			    // Variable sql requette 1.
			    String sql = "SELECT * FROM formation.clients";
			    
			    // Variable sql requette 2.
			    String sql2 = "SELECT NP, NOMP FROM formation.produits\n" + "WHERE COUL = 'rouge' AND PDS > 2000;";
			    
			    // Variable sql requette 3.
			    String sql3 = "SELECT NOMR FROM formation.REPRESENTANTS\n" + "INNER  JOIN formation.VENTES ON REPRESENTANTS.NR = VENTES.NR \n" + "GROUP BY NOMR;";
			    
			    // Variable sql requette 4.
			    String sql4 = "SELECT NOMC FROM formation.CLIENTS\n" + "INNER JOIN formation.VENTES ON CLIENTS.NC = VENTES.NC\n" + "WHERE VILLE = 'Lyon' AND QI>180\n" + "GROUP BY NOMC;";
			    
			    // Variable sql requette 5.
			    String sql5 = "SELECT REPRESENTANTS.NOMR, CLIENTS.NOMC FROM (((formation.VENTES\n" + 
			    		"INNER JOIN formation.CLIENTS ON VENTES.NC = CLIENTS.NC)\n" + 
			    		"INNER JOIN formation.PRODUITS ON VENTES.NP = PRODUITS.NP)\n" + 
			    		"INNER JOIN formation.REPRESENTANTS ON VENTES.NR = REPRESENTANTS.NR)\n" + 
			    		"WHERE VENTES.QI>100 AND PRODUITS.COUL = 'Rouge' \n" + 
			    		"GROUP BY REPRESENTANTS.NOMR, CLIENTS.NOMC;";
			    
		        Statement st;
		        ResultSet result ;
		        Connection cn ;
		        
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			cn=  DriverManager.getConnection(url, user, pwd);
			
			st =  cn.createStatement();
			
			result= st.executeQuery(sql);
			
			int ncVar;
			String nomcVar;
			String villeVar;
			
			// Variables requette 2.
			int npVar;
			String nompVar;
			
			// Variables requette 3.
			String nomrVar;
			
			// Variable requette 4.
			String nomClientVar;
			
			// Variable requette 5.
			String nomRepresentant;
			String nomClient;
			
			while (result.next()) {
				
				// Récupérer le "nc".
				ncVar = result.getInt("nc");
				
				// Récupérer le "nomc".
				nomcVar = result.getString("nomc");
				
				// Récupérer "ville".
				villeVar = result.getString("ville");
				
				System.out.println("Numéro Client: " + ncVar + ", NomClient: " + nomcVar + ", Ville: " + villeVar + ".");
				
			}
			
			// Requette 2.
			ResultSet result2;
			result2 = st.executeQuery(sql2);
			
			while (result2.next()) {
				
				// Récupérer le "NP" de la table PRODUITS.
				npVar = result2.getInt("NP");
				
				// Récupérer le "NOMP" de la table PRODUITS.
				nompVar = result2.getString("NOMP");
				
				System.out.println("Numéro Produit: " + npVar + ", NomProduit: " + nompVar + " est un produit de couleur rouge et de poids supérieur à 2000.");
				
			}
			
			// Requette 3.
			ResultSet result3;
			result3 = st.executeQuery(sql3);
			
			while (result3.next()) { 
				
				// Récupérer le "NOMR" de la table REPRESENTANTS. 
				nomrVar = result3.getString("NOMR");
				
				System.out.println("Le représentant " + nomrVar + ", a vendu au moins un produit.");
				
			}
			
			// Requette 4.
			ResultSet result4;
			result4 = st.executeQuery(sql4);
			
			while (result4.next()) { 
				
				// Récupérer le "NOMC" de la table CLIENTS. 
				nomClientVar = result4.getString("NOMC");
				
				System.out.println("Le client de Lyon: " + nomClientVar + ", a acheté un produit pour une quantité supérieure à 180.");
				
			}
			
			// Requette 5.
			ResultSet result5;
			result5 = st.executeQuery(sql5);
			
			while (result5.next()) {
				
				// Récupérer le "NOMR" de la table REPRESENTANTS. 
				nomRepresentant = result5.getString("NOMR");
				
				// Récupérer le "NOMC" de la table CLIENTS. 
				nomClient = result5.getString("NOMC");
				
				System.out.println("Le représentant: " + nomRepresentant + " a vendu un produit de couleur rouge pour une quantité supérieure à 100 au client: " + nomClient + ".");
				
			}
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
	   
	    		
	}

}
