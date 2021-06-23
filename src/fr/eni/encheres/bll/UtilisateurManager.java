package fr.eni.encheres.bll;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurManager {
	private UtilisateurDAO utilisateurDAO;

	
	
	// Méthode de BLL pour inserer une nouvelle inscription

	private static UtilisateurManager instance = null;
	
	
	private UtilisateurManager() throws BusinessException {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	public static synchronized UtilisateurManager getInstance() throws BusinessException {
		if(instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}
	
	// Méthode de BLL pour inserer une nouvelle inscription	
	public boolean AjouterInscription(Utilisateur utilisateur) throws BusinessException {
		
		boolean inscriValideP = false;
		boolean inscriValideE = false;
		
		inscriValideP = validerPseudo(utilisateur.getPseudo());
		inscriValideE = validerMail(utilisateur.getEmail());
		
		
		if(inscriValideP == false & inscriValideE == false) {
			this.utilisateurDAO.insertInscription(utilisateur);
			return true;
		}
		else {
			return false;
		}
	}
	
	

	// Méthode qui vérifie que le pseudo ne soit pas déjà existant
	public boolean validerPseudo(String nomPseudo) throws BusinessException {
				boolean statusValidation = false;
				if(nomPseudo == null | nomPseudo.length() > 30) {
					statusValidation = true;
				}
					int pseudoValide = 0;
					try {
						pseudoValide = this.utilisateurDAO.selectPseudo(nomPseudo);
						System.out.println(pseudoValide);
						if(pseudoValide >= 1) {
							statusValidation = true;
						}			
						else if(pseudoValide == 0) {
							statusValidation = false;
						}
					} catch (BusinessException e) {
						e.printStackTrace();
		 			}
					System.out.println(statusValidation);
					return statusValidation;
			}
	//
	// Méthode qui vérifie que le pseudo ne soit pas déjà existant
		public boolean validerPseudoModifProfil(String nomPseudo) throws BusinessException {
					boolean statusValidation = false;
					if(nomPseudo == null | nomPseudo.length() > 30) {
						statusValidation = true;
					}
						int pseudoValide = 0;
						try {
							pseudoValide = this.utilisateurDAO.selectPseudo(nomPseudo);
							System.out.println(pseudoValide);
							if(pseudoValide >= 2) {
								statusValidation = true;
							}			
							else  {
								statusValidation = false;
							}
						} catch (BusinessException e) {
							e.printStackTrace();
			 			}
						System.out.println(statusValidation);
						return statusValidation;
				}
	
	// Méthode qui vérifie si le mail ne soit pas déjà existant
	public boolean validerMail(String nomMail) throws BusinessException {
					boolean statusValidation = false;
					if(nomMail == null | nomMail.length() > 60) {
						statusValidation = true;
					}
					int emailValide =0;
					try {
						emailValide = this.utilisateurDAO.selectEmail(nomMail);
						System.out.println(emailValide);
						if(emailValide >= 1) {
							statusValidation = true;
						}
						else if(emailValide == 0) {
							statusValidation = false;
						}
					
				} catch (BusinessException e) {
					e.printStackTrace();
								
		 		}
					return statusValidation;
			}
	
	
	//
	// Méthode qui vérifie si le mail est deja existant lors de la modification du profil
	public boolean validerMailModifProfil(String nomMail) throws BusinessException {
					boolean statusValidation = false;
					if(nomMail == null | nomMail.length() > 60) {
						statusValidation = true;
					}
					int emailValide =0;
					try {
						emailValide = this.utilisateurDAO.selectEmail(nomMail);
						System.out.println(emailValide);
						if(emailValide >= 2) {
							statusValidation = true;
						}
						else  {
							statusValidation = false;
						}
					
				} catch (BusinessException e) {
					e.printStackTrace();
								
		 		}
					return statusValidation;
			}
	
	
	// Méthode de verif MPD :
	public boolean validerMDP(String password) throws BusinessException {
		boolean statusValidation = false;
		int passwordValide = 0;
			try {
				passwordValide  = this.utilisateurDAO.selectPassword(password);
				if(passwordValide >= 1) {
					statusValidation = true;
				}
				else if(passwordValide == 0) {
					statusValidation = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		return statusValidation;
	}
	
	
	
	//Méthode modification profil
	public void modificationProfil(Utilisateur utilisateur, String pseudo) throws BusinessException {
		this.utilisateurDAO.updateProfil(utilisateur, pseudo);
	}
	
	//Methode de suppression du profil
	public void suppressionProfil(String pseudo) throws BusinessException {
		this.utilisateurDAO.deleteProfil(pseudo);
	}
	
	//Méthode de connection
			public boolean connection(String identifiant, String password) throws BusinessException{
				boolean testConnection = false;
				System.out.println("je suis dans ma bll avant d'aller dans la DAL");
				testConnection = validerPseudo(identifiant);
				System.out.println(testConnection);
				if (testConnection == true)
				{
					testConnection = validerMDP(password);
					}
				return testConnection;	
			}
			
	//méthode de récuperation d'un utilisateur
			
		public Utilisateur recuperationUtilisateur(String pseudo) throws BusinessException {
			Utilisateur u = null;
			u = this.utilisateurDAO.SelectUser(pseudo);
			return u;
		}
		
	//méthode vérification de mail
		
		public boolean verifmail(String email) throws BusinessException 
		{
			return validerMail(email);
		}
		
	//méthode pour envoyer un mail oublie mot de passe
		
		public void sentmail(String userEmail) throws Exception{
			System.out.println("message en cours d'envoie");
			Properties properties = new Properties();

			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			
			
			final String monEmail = "enienchere@gmail.com";
			final String password = "G&EtN5tT3KBNDjox";
			
			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(monEmail, password);
				}
			});
			
			//Message message = prepareMessage(session,monEmail, userEmail);
			
			try {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(monEmail));
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
				message.setSubject("Réinitialisation de votre mot de passe");
				message.setText("Bonjour, \n cliquez sur le lien ci-dessous pour changer votre mot de passe. \n http://localhost:8080/ENIEncheres/Reinitialisation?userEmail="+userEmail);
				
				Transport.send(message);
				
				System.out.println("message envoyé");
				
			} catch (MessagingException e) {
				e.printStackTrace();
			}			
		}
		
		
		//Méthode pour update mot de passe
		public void UpdatePassword(String password, String verifPassword, String userEmail)throws BusinessException {
			
			this.utilisateurDAO.updatePassword(password, userEmail);
		
	}
		


	
}

	
