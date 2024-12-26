package DAO;
import Model.Employe;
import Model.Post;
import Model.Role;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeDAOimpl implements GenericDAOI<Employe>{


        @Override
        public void add(Employe e) {
            // Do not include the 'id' column in the INSERT statement because it is AUTO_INCREMENT
            String sql = "INSERT INTO employe (nom, prenom, email, telephone, salaire, role, poste, solde) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = DBConnexion.getConnexion().prepareStatement(sql)) {
                stmt.setString(1, e.getNom());
                stmt.setString(2, e.getPrenom());
                stmt.setString(3, e.getEmail());
                stmt.setString(4, e.getTelephone());
                stmt.setDouble(5, e.getSalaire());
                stmt.setString(6, e.getRole().name());  // Assuming Role is an enum and needs to be converted to string
                stmt.setString(7, e.getPost().name());  // Assuming Post is an enum and needs to be converted to string
                stmt.setInt(8, e.getSolde());
                stmt.executeUpdate();
            } catch (SQLException exception) {
                System.err.println("Failed to add employee: " + exception.getMessage());
                exception.printStackTrace();  // Prints the full stack trace for debugging
            } catch (ClassNotFoundException ex) {
                System.err.println("Failed to connect to the database: " + ex.getMessage());
                ex.printStackTrace();  // Prints the full stack trace for debugging
            }
        }

        @Override
        public void delete(int id) {
            String sql = "DELETE FROM employe WHERE id = ?";
            try (PreparedStatement stmt = DBConnexion.getConnexion().prepareStatement(sql)) {
                stmt.setInt(1,id);
                stmt.executeUpdate();
            } catch (SQLException exception) {
                System.err.println("failed of delete employe");
            } catch (ClassNotFoundException ex) {
                System.err.println("failed connexion with data base");
            }
        }

        @Override
        public void update(Employe e) {
            String sql = "UPDATE employe SET nom = ?, prenom = ?, email = ?, telephone = ?, salaire = ?, role = ?, poste = ? WHERE id = ?";
            try (PreparedStatement stmt = DBConnexion.getConnexion().prepareStatement(sql)) {
                stmt.setString(1, e.getNom());
                stmt.setString(2, e.getPrenom());
                stmt.setString(3, e.getEmail());
                stmt.setString(4, e.getTelephone());
                stmt.setDouble(5, e.getSalaire());
                stmt.setString(6, e.getRole().name());
                stmt.setString(7, e.getPost().name());
                stmt.setInt(8,e.getId());
                stmt.executeUpdate();
            } catch (SQLException exception) {
                System.err.println("failed of update employe");
            } catch (ClassNotFoundException ex) {
                System.err.println("failed connexion with data base");
            }
        }
        @Override
        public List<Employe> display() {
            String sql = "SELECT * FROM employe";
            List<Employe> Employes = new ArrayList<>();
            try (PreparedStatement stmt = DBConnexion.getConnexion().prepareStatement(sql)) {
                ResultSet re = stmt.executeQuery();
                while (re.next()) {
                    int id = re.getInt("id");
                    String nom = re.getString("nom");
                    String prenom = re.getString("prenom");
                    String email = re.getString("email");
                    String telephone = re.getString("telephone");
                    double salaire = re.getDouble("salaire");
                    String role = re.getString("role");
                    String poste = re.getString("poste");
                    int solde = re.getInt("solde");
                    Employe e = new Employe(id,nom, prenom, email, telephone, salaire, Role.valueOf(role), Post.valueOf(poste),solde);
                    Employes.add(e);
                }
                return Employes;
            } catch (ClassNotFoundException ex) {
                System.err.println("failed connexion with data base");
                return null;
            } catch (SQLException ex) {
                System.err.println("failed of display employe");
                return null;
            }
        }


        public void updateSolde(int id, int solde) {
            String sql = "UPDATE employe SET solde = ? WHERE id = ?";
            try (PreparedStatement stmt = DBConnexion.getConnexion().prepareStatement(sql)) {
                stmt.setInt(1, solde);
                stmt.setInt(2, id);
                stmt.executeUpdate();
            } catch (SQLException exception) {
                System.err.println("failed of update solde employe");
            } catch (ClassNotFoundException ex) {
                System.err.println("failed connexion with data base");
            }
        }

    }

