package application;
import java.sql.*;
public class Connexion {
    public static void main(String args[]){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307/musee","root","");

            //musee est ici le nom de notre base de données
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from salle");
            // stmt.executeAndUpdate("Insert ...") pour l'ajout de données
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2));


            // mouvemment des oeuvre : q1
            System.out.println("\n\n------Mouvement-----------\n");
            ResultSet mouv=stmt.executeQuery("select salle.nom, oeuvre.nom, date_deb, date_fin from stockage join salle on salle.id = id_salle join oeuvre on oeuvre.id = id_oeuvre;");
            while(mouv.next())
                System.out.println(mouv.getString(1) +"  "+mouv.getString(2)+"  "+mouv.getString(3)+"  "+mouv.getString(4));


            // ajout de triceratops : q2
            System.out.println("\n\n------Q2-----------\n");
            //stmt.execute("insert into oeuvre values (0, 'triceratops') ");
            //stmt.execute("insert into salle values (0, 'grand hall') ");

            ResultSet id_oeuvre = stmt.executeQuery("SELECT id from oeuvre where nom='triceratops'");

            int id_ouuvre_vrai = 0;
            if(id_oeuvre.next()){
                id_ouuvre_vrai = id_oeuvre.getInt("id");
            }

            ResultSet id_salle = stmt.executeQuery("SELECT id from salle where nom='grand hall'");

            int id_salle_vrai = 0;
            if(id_salle.next()){
                id_salle_vrai = id_salle.getInt("id");
            }


            //stmt.execute("insert into stockage values ("+id_ouuvre_vrai+", "+id_salle_vrai+", '2021-09-28', null) ");
            ResultSet mouv2=stmt.executeQuery("select salle.nom, oeuvre.nom, date_deb, date_fin from stockage join salle on salle.id = id_salle join oeuvre on oeuvre.id = id_oeuvre;");

            System.out.println("Les mouvemments\n");
            while(mouv2.next())
                System.out.println(mouv2.getString(1) +"  "+mouv2.getString(2)+"  "+mouv2.getString(3)+"  "+mouv2.getString(4));



            con.close();
        }
        catch(Exception e){ System.err.println(e);}
    }
}

