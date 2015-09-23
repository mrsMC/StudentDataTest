package invoicedata;

/**
 *
 * @author mel76_000
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Invoice {
    public static void main(String[]args)throws SQLException{
        
        String URL = "jdbc:mysql://localhost:3306/";
        String DB = "mynewdatabase";
        String USERNAME = "root";
        String PASSWORD = "7007";
        
        Connection conn = null;
        
        try{
            //setup the connection with the DB
            conn = DriverManager.getConnection(URL+DB,USERNAME,PASSWORD);
            System.out.println("Connected");
            Statement stmt = conn.createStatement();
            
            
            stmt.execute("DROP TABLE Invoice");
            stmt.execute("CREATE TABLE if not exists Invoice(Invoice_number INTEGER, Customer_number INTEGER,Payment DECIMAL(10,2))");
            stmt.execute("INSERT INTO INVOICE VALUES(11731,3175,0)");
            stmt.execute("INSERT INTO INVOICE VALUES(11732,3176,249.50)");
            stmt.execute("INSERT INTO INVOICE VALUES(11733,3175,0)");
       
           
           ResultSet result = stmt.executeQuery("SELECT * FROM Invoice"); 
           while(result.next()!= false)
            {
                System.out.println("Invoice number "+result.getInt("Invoice_number"));
                System.out.println("Customer number "+result.getInt("Customer_number"));
                System.out.println("Payment "+result.getInt("Payment"));
            }
           
    stmt.execute("DROP TABLE Customer");      
    stmt.execute("CREATE TABLE if not exists Customer(cust_num INTEGER, name VARCHAR(40), address VARCHAR(40), city VARCHAR(30), state CHAR(2), zip CHAR(5))");
    stmt.execute("INSERT INTO Customer VALUES(3175,'Sams Small Applicances','100 Main St', 'Anytown','CA','98765')");
    stmt.execute("INSERT INTO Customer VALUES(3176,'SElectronics Unlimited','1175 Lierty Ave', 'Pleasantville','MI','45066')");

    ResultSet result2 = stmt.executeQuery("SELECT * FROM Customer"); 
           while(result2.next()!= false)
            {
                System.out.println("cust_num "+result2.getInt("cust_num"));
                System.out.println("name "+result2.getString("name"));
                System.out.println("address "+result2.getString("address"));
                System.out.println("city "+result2.getString("city"));
                System.out.println("state "+result2.getString("state"));
                System.out.println("zip "+result2.getString("zip"));
            }
           
    stmt.execute("DROP TABLE Product");      
    stmt.execute("CREATE TABLE if not exists Product(product_code CHAR(7), description VARCHAR(40), price DECIMAL(10,2))");
    stmt.execute("INSERT INTO Product VALUES('116-064','Toaster',24.95)");
    stmt.execute("INSERT INTO Product VALUES('257-535','HairDryer',29.95)");
    stmt.execute("INSERT INTO Product VALUES('643-119','Car Vacuum',19.99)"); 
    
        ResultSet result3 = stmt.executeQuery("SELECT * FROM Product"); 
           while(result3.next()!= false)
            {
                System.out.println("Product Code "+result3.getString("product_code"));
                System.out.println("Description "+result3.getString("description"));
                System.out.println("Price "+result3.getString("price"));
            }
                
              
    stmt.execute("DROP TABLE LineItem"); 
    stmt.execute("CREATE TABLE if not exists LineItem(invoice_num INTEGER, product_code CHAR(7), quantity INTEGER)");
    stmt.execute("INSERT INTO LineItem VALUES(11731,'116-064',3)");
    stmt.execute("INSERT INTO LineItem VALUES(11731,'275-535',1)");
    stmt.execute("INSERT INTO LineItem VALUES(11731,'643-119',2)");
    stmt.execute("INSERT INTO LineItem VALUES(11732,'116-064',10)");
    stmt.execute("INSERT INTO LineItem VALUES(11733,'116-064',2)");
    stmt.execute("INSERT INTO LineItem VALUES(11733,'643-119',1)");
    
            ResultSet result4 = stmt.executeQuery("SELECT * FROM LineItem"); 
           while(result4.next()!= false)
            {
                System.out.println("Invoice Number "+result4.getString("invoice_num"));
                System.out.println("Product Code "+result4.getString("product_code"));
                System.out.println("Quantity "+result4.getString("quantity"));
            }     
           
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
        finally
        {
            if(conn!=null)
            {
                conn.close();
            }
        }
    }//METHOD
    
}//CLASS
