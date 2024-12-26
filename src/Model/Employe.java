package Model;

public class Employe {

        private int id;
        private String nom;
        private String prenom;
        private String email;
        private String telephone;
        private double salaire;
        private Role role;
        private Post poste ;
        private int solde ;

        public Employe(int id ,String nom, String prenom, String email, String telephone, double salaire, Role role, Post post ,int solde){
            this.id = id;
            this.nom = nom;
            this.prenom = prenom;
            this.email = email;
            this.telephone = telephone;
            this.salaire = salaire;
            this.role = role;
            this.poste = post;
            this.solde = solde;
        }


        public int getId(){
            return id;
        }

        public void setId(int id){
            this.id = id;
        }
        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public double getSalaire() {
            return salaire;
        }

        public void setSalaire(double salaire) {
            this.salaire = salaire;
        }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public Post getPost() {
            return poste;
        }

        public void setPost(Post post) {
            this.poste = post;
        }

        public void setSolde (int conge){
            this.solde = conge;
        }

        public int getSolde(){
            return solde;
        }
    }

