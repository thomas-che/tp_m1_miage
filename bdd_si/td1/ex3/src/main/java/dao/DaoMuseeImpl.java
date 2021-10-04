package dao;

import model.Salle;

import java.sql.*;

public class DaoMuseeImpl implements DaoMusee{

    // param de connexion a la bdd
    private final static String URL = "jdbc:mysql://localhost:3307/";
    private final static String BD = "musee";
    private final static String USER_NAME = "root";
    // cree le container sans mdp
    private final static String PASSWORD = "";

    // Pas d'attribut car on ne ferme jama la co sinon
    //private Connection connection;

    public DaoMuseeImpl() {
        try {
            this.init();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void init() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        //this.connection = DriverManager.getConnection(URL+BD,USER_NAME,PASSWORD);

        // commande sql pour cree la bdd

        // (...)
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL+BD,USER_NAME,PASSWORD);
    }

    @Override
    public int creeSalle(String nomSalle) {
        String ajoutSalleSql = "INSERT INTO salle VALUES(?,?)";
        Integer idSalle =null;

        // on me dans le try la connection, comme ca a la fin du try la connection se fermera
        try (Connection connection = this.getConnection()){
            // requette prepare qui retourne la cle
            PreparedStatement ps = connection.prepareStatement(ajoutSalleSql, Statement.RETURN_GENERATED_KEYS);

            // set les param de la requette
            ps.setInt(1,0);
            ps.setString(2,nomSalle);

            // exec la requette
            ps.executeUpdate();

            // recupere le res de la requette
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                idSalle = rs.getInt(1);
            }
            return idSalle;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idSalle;
    }

    @Override
    public Salle getSalleById(Integer idSalle) {
        String getSalle = "SELECT * FROM salle WHERE id=(?)";
        Salle salle = null;

        try (Connection connection = this.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(getSalle);

            ps.setInt(1,idSalle);
            ps.executeQuery();

            ResultSet rs = ps.getResultSet();

            if (rs.next()){
                salle = new Salle(rs.getInt(1), rs.getString(2));
            }

            return salle;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salle;
    }
}
