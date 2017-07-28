import java.util.*;  
import java.sql.*;  
  
public class Query {  
  
    public static Connection getConnection(){  
        Connection con=null;  
        try{  
           Class.forName("com.mysql.jdbc.Driver");
            con =DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "");
 
        }catch(Exception e){System.out.println(e);}  
        return con;  
    }  
    public static int save(RegisterValue registervalue){  
        int status=0;  
        try{  
            Connection con=Query.getConnection();  
            PreparedStatement ps=con.prepareStatement(  
                         "insert into crud(name,password,email,phone_no,place) values (?,?,?,?,?)");  
            ps.setString(1,registervalue.getName());  
            ps.setString(2,registervalue.getPassword());  
            ps.setString(3,registervalue.getEmail());
            ps.setString(4,registervalue.getPhoneno());  
            ps.setString(5,registervalue.getPlace());  
              
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception ex){ System.out.println("Your Email ID is already used....!!!");}
        return status;  
    }  
     public static String login(String email,String password){  
        int status=0; 
        String dbname="",dbemail="",dbpass="",userresult=null; 
        try{  
            Connection con=Query.getConnection();
            PreparedStatement ps=con.prepareStatement("select name,password,email from crud where email=?"); 
            ps.setString(1,email);
            ResultSet rs=ps.executeQuery();  
            int i=0;
            if (rs.next()) {
                dbname=rs.getString(1);
                dbpass=rs.getString(2);
                dbemail=rs.getString(3);    
            } 
        }
        catch(Exception e)
        {e.printStackTrace(); }
        if((password.equals(dbpass))&&(email.equals(dbemail)))
        {
                return dbname;
            }
            else
            {
                return userresult;
            }
    }
    public static int update(RegisterValue registervalue){  
        int status=0;  
        try{  
            Connection con=Query.getConnection();  
            PreparedStatement ps=con.prepareStatement(  
                         "update crud set password=?,email=?,phone_no=?,place=? where name=?");  
            
            ps.setString(1,registervalue.getPassword());  
            ps.setString(2,registervalue.getEmail());  
             ps.setString(3,registervalue.getPhoneno());  
            ps.setString(4,registervalue.getPlace());  
            ps.setString(5,registervalue.getName());  
              
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return status;  
    }  
    public static int delete(String name){  
        int status=0;  
        try{  
            Connection con=Query.getConnection();  
            PreparedStatement ps=con.prepareStatement("delete from crud where name=?");  
            ps.setString(1,name);  
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception e){e.printStackTrace();}  
          
        return status;  
    }  
    public static RegisterValue getStudentName(String name){  
        RegisterValue registervalue=new RegisterValue();  
          
        try{  
            Connection con=Query.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from crud where name=?");  
            ps.setString(1,name);  
            ResultSet rs=ps.executeQuery();  
            if(rs.next()){    
                registervalue.setName(rs.getString(1));  
                registervalue.setPassword(rs.getString(2));  
                registervalue.setEmail(rs.getString(3)); 
                registervalue.setPhoneno(rs.getString(4));  
                registervalue.setPlace(rs.getString(5));  
            }  
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return registervalue;  
    }  
    public static List<RegisterValue> getAllStudent(){  
        List<RegisterValue> list=new ArrayList<RegisterValue>();  
          
        try{  
            Connection con=Query.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from crud");  
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
                RegisterValue registervalue=new RegisterValue();  
                registervalue.setName(rs.getString(1));    
                registervalue.setPassword(rs.getString(2));  
                registervalue.setEmail(rs.getString(3));
                registervalue.setPhoneno(rs.getString(4));  
                registervalue.setPlace(rs.getString(5));  
                list.add(registervalue);  
            }  
            con.close();  
        }catch(Exception e){e.printStackTrace();}  
          
        return list;  
    }  
}  