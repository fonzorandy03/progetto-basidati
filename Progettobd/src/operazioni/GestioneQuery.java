package operazioni;
//per connettere il tutto abbiamo utilizzato il mysql-connector-j-8.0.33
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Connettiamoci con il database
public class GestioneQuery {

	 Connection con;

	public void Connessione(String url, String usr, String psw) {
		try {
			this.con = DriverManager.getConnection(url, usr, psw);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void RegistrazioneScuderia(int idScuderia, String nome, String paese, int numVetture) {
	    String query = "INSERT INTO Scuderia (Id_Scuderia, NumeroVetture, Paese, Nome) VALUES (?, ?, ?, ?)";

	    try (PreparedStatement pst = con.prepareStatement(query)) {
	        pst.setInt(1, idScuderia);
	        pst.setInt(2, numVetture);
	        pst.setString(3, paese);
	        pst.setString(4, nome);

	        int rows = pst.executeUpdate();

	        if (rows > 0) {
	            JOptionPane.showMessageDialog(null, "Scuderia registrata perfettamente!", "Successo", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            JOptionPane.showMessageDialog(null, "Inserimento non andato a buon fine. Controlla i dati della Query.", "Errore", JOptionPane.ERROR_MESSAGE);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Errore query: " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
	    }
	}
	public boolean isIdScuderiaPresente(int idScuderia) {
	    String query = "SELECT COUNT(*) FROM Scuderia WHERE Id_Scuderia = ?";

	    try (PreparedStatement pst = con.prepareStatement(query)) {
	        pst.setInt(1, idScuderia);
	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) {
	            int count = rs.getInt(1);
	            return count > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Si è verificato un errore durante l'esecuzione della query. " + e.getMessage());
	    }

	    return false;
	}
	public boolean isIdVetturaPresente(int idVettura) {
	    try {
	        String query = "SELECT COUNT(*) FROM Vettura WHERE Id_Vettura = ?";
	        try (PreparedStatement pst = con.prepareStatement(query)) {
	            pst.setInt(1, idVettura);
	            ResultSet rs = pst.executeQuery();
	            rs.next();
	            int count = rs.getInt(1);
	            return count > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public boolean isIdComponentePresente(int idComponente) {
	    try {
	        String query = "SELECT COUNT(*) FROM Componente WHERE CodiceComponente = ?";
	        try (PreparedStatement pst = con.prepareStatement(query)) {
	            pst.setInt(1, idComponente);
	            ResultSet rs = pst.executeQuery();
	            rs.next();
	            int count = rs.getInt(1);
	            return count > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public boolean isIdPilotaPresente(String codfisc) {
	    try {
	        String query = "SELECT COUNT(*) FROM Piloti WHERE Codice_Fiscale = ?";
	        try (PreparedStatement pst = con.prepareStatement(query)) {
	            pst.setString(1, codfisc);
	            ResultSet rs = pst.executeQuery();
	            rs.next();
	            int count = rs.getInt(1);
	            return count > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public List<Integer> caricaIdEquipaggio() {
	    List<Integer> idEquipaggioList = new ArrayList<>();
	    try {
	        String query = "SELECT Id_Equipaggio FROM Equipaggio";
	        try (PreparedStatement pst = con.prepareStatement(query)) {
	            ResultSet rs = pst.executeQuery();
	            while (rs.next()) {
	                idEquipaggioList.add(rs.getInt("Id_Equipaggio"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return idEquipaggioList;
	}

	public void InserimentoAutovetturaComp(int id_vettura, int ide, int numerog, String modello, int id_s, int codice,
			String rag, Integer cil, String tipo, String tipom, Double costo, Integer numc, Integer nummarcie,
			Double peso, String mat) {
		try {
			String queryVettura = "INSERT INTO Vettura (Id_Vettura, Id_Equipaggio, numero_gara, modello_veicolo, Id_Scuderia) VALUES (?, ?, ?, ?, ?)";
			String queryComponente = "INSERT INTO Componente (CodiceComponente, RagioneSociale, Id_Vettura, Cilindrata, tipo_componente, tipo_motore, costo, NumeroCilindri, NumeroMarcie, Peso, Materiale) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			try (PreparedStatement pstVettura = con.prepareStatement(queryVettura);
					PreparedStatement pstComponente = con.prepareStatement(queryComponente)) {
				pstVettura.setInt(1, id_vettura);
				pstVettura.setInt(2, ide);
				pstVettura.setInt(3, numerog);
				pstVettura.setString(4, modello);
				pstVettura.setInt(5, id_s);
				pstVettura.executeUpdate();
				pstComponente.setInt(1, codice);
				pstComponente.setString(2, rag);
				pstComponente.setInt(3, id_vettura);
				pstComponente.setInt(4, cil);
				pstComponente.setString(5, tipo);

				if (tipom == null || tipom.trim().isEmpty()) {
					pstComponente.setNull(6, java.sql.Types.VARCHAR);
				} else {
					pstComponente.setString(6, tipom);
				}

				pstComponente.setDouble(7, costo);

				if (numc == null) {
					pstComponente.setNull(8, java.sql.Types.INTEGER);
				} else {
					pstComponente.setInt(8, numc);
				}

				if (nummarcie == null) {
					pstComponente.setNull(9, java.sql.Types.INTEGER);
				} else {
					pstComponente.setInt(9, nummarcie);
				}

				pstComponente.setObject(10, peso);
				pstComponente.setString(11, mat);

				pstComponente.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void AggiuntaPilota(String cod, int id_equipaggio, String nome, String cognome, String data, String naz,
	        String tipo, int numl, Date dataPrimaLicenza) {
	    String query = "INSERT INTO Piloti VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
	    try {
	        PreparedStatement pst;
	        pst = con.prepareStatement(query);
	        pst.setString(1, cod);
	        pst.setInt(2, id_equipaggio);
	        pst.setString(3, nome);
	        pst.setString(4, cognome);
	        pst.setString(5, data);
	        pst.setString(6, naz);
	        pst.setInt(7, numl);
	        pst.setString(8, tipo);

	        // Converte l'oggetto java.util.Date in java.sql.Date solo se dataPrimaLicenza non è null
	        java.sql.Date sqlDate = (dataPrimaLicenza != null) ? new java.sql.Date(dataPrimaLicenza.getTime()) : null;
	        pst.setDate(9, sqlDate);

	        int rows = pst.executeUpdate();
	        if (rows > 0)
	            System.out.println("Il nuovo pilota è stato aggiunto con successo!");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Errore nella query");
	    }
	}


	public void AggiungiFinanziamento(int id, double importo, String data, String tipo,String codfisc) {
		String query = "INSERT INTO Finanziamento VALUES (?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, id);
			pst.setString(2, codfisc);
			pst.setDouble(3, importo);
			pst.setString(4, tipo);
			pst.setString(5, data);
			int rows = pst.executeUpdate();
			if (rows > 0)
				System.out.println("L'ultima registrazione del finanziamento è avvenuta con successo!");
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Errore nella query");
		}
		
	}

	public void registraRisultato(int posizionamento, String motivoRitiro, String nomeGara, java.sql.Date data, int Id_Circuito, int idVettura) {
	    String query = "UPDATE Partecipazione SET Posizionamento = ?, MotivoRitiro = ? " +
	                   "WHERE Nome = ? AND Data = ? AND Id_Circuito = ? AND Id_Vettura = ?";
	    
	    try (PreparedStatement pst = con.prepareStatement(query)) {
	        pst.setInt(1, posizionamento);
	        if (motivoRitiro == null || motivoRitiro.trim().isEmpty()) {
	            pst.setNull(2, java.sql.Types.VARCHAR);  
	        } else {
	            pst.setString(2, motivoRitiro);
	        }
	        pst.setString(3, nomeGara);
	        pst.setDate(4, data);  
	        pst.setInt(5, Id_Circuito);
	        pst.setInt(6, idVettura);
	        
	        int rows = pst.executeUpdate();
	        if (rows > 0) {
	            System.out.println("Esito registrato correttamente!");
	        } else {
	            System.out.println("Errore nell'aggiornamento del database. Verifica i dati della query.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Errore nella query");
	    }
	}



	public void IscrizioneVettura(String nomev, String data, int idc, int risultato, String MotivoRitiro, int idv) {
	    String query = "INSERT INTO `partecipazione` (`Id_Vettura`, `nome`, `data`, `Id_Circuito`, `MotivoRitiro`, `Posizionamento`) VALUES (?, ?, ?, ?, ?, ?)";

	    try {
	        PreparedStatement pst = con.prepareStatement(query);
	        pst.setInt(1, idv);
	        pst.setString(2, nomev);
	        pst.setString(3, data);
	        pst.setInt(4, idc);
	        if (MotivoRitiro == null || MotivoRitiro.trim().isEmpty()) {
	            pst.setNull(5, java.sql.Types.VARCHAR);
	        } else {
	            pst.setString(5, MotivoRitiro);  
	        }

	        pst.setInt(6, risultato);

	        
	        int rows = pst.executeUpdate();

	        if (rows > 0) {
	            System.out.println("Il nuovo risultato è stato inserito con successo!");
	        } else {
	            System.out.println("Il nuovo risultato non è stato inserito. Controlla i dati");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Errore query: " + e.getMessage());
	    }
	}




	public String RicercaComponente(int idv, int idc) {
	    String query = "SELECT * FROM Componente WHERE Id_Vettura = ? AND CodiceComponente = ?";
	    try {
	        PreparedStatement pst = con.prepareStatement(query);
	        pst.setInt(1, idv);
	        pst.setInt(2, idc);

	        try (ResultSet resultSet = pst.executeQuery()) {
	            if (resultSet.next()) {
	                return "Componente installato - Informazioni: " +
	                        "CodiceComponente: " + resultSet.getInt("CodiceComponente") +
	                        ", RagioneSociale: " + resultSet.getString("RagioneSociale") +
	                        ", Id_Vettura: " + resultSet.getInt("Id_Vettura") +
	                        ", Cilindrata: " + resultSet.getInt("Cilindrata") +
	                        ", Tipo_Componente: " + resultSet.getString("Tipo_Componente") +
	                        ", Tipo_Motore: " + resultSet.getString("Tipo_Motore") +
	                        ", Costo: " + resultSet.getDouble("Costo") +
	                        ", NumeroCilindri: " + resultSet.getInt("NumeroCilindri") +
	                        ", NumeroMarcie: " + resultSet.getInt("NumeroMarcie") +
	                        ", Peso: " + resultSet.getDouble("Peso") +
	                        ", Materiale: " + resultSet.getString("Materiale");
	            } else {
	                return "Componente non installato";
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Errore nella query");
	        return "Errore nella query";
	    }
	}


	public String StampaSommaTotaleFinanziamentiPerOgniScuderia() {
		String query = "SELECT s.Nome AS NomeScuderia, SUM(f.Importo) AS SommaFinanziamenti " + "FROM Scuderia s "
				+ "LEFT JOIN Finanziamento f ON s.Id_Scuderia = f.Id_Scuderia " + "GROUP BY s.Nome";
		String result="";
		try (PreparedStatement preparedStatement = con.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				String nomeScuderia = resultSet.getString("NomeScuderia");
				double sommaFinanziamenti = resultSet.getDouble("SommaFinanziamenti");

				result+="Scuderia: " + nomeScuderia + ", Somma Finanziamenti: " + sommaFinanziamenti+"\n";;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			result+="Errore nella query";
		}
		return result;
		
	}
	
	public String StampaAnnualeScuderieConFinanziamenti() {
	    String query = "SELECT s.Nome AS NomeScuderia, COUNT(DISTINCT g.nome) AS NumeroGarePartecipate, COUNT(f.Id_Scuderia) AS NumeroFinanziamenti " +
	                   "FROM Scuderia s " +
	                   "LEFT JOIN vettura v ON s.Id_Scuderia = v.Id_Scuderia " +
	                   "LEFT JOIN Partecipazione p ON v.Id_Vettura = p.Id_Vettura " +
	                   "LEFT JOIN Gara g ON p.nome = g.nome AND p.data = g.data AND p.Id_Circuito = g.Id_Circuito " +
	                   "LEFT JOIN Finanziamento f ON s.Id_Scuderia = f.Id_Scuderia " +
	                   "GROUP BY s.Nome";
	    String report="";
	    try {
	    	PreparedStatement pst = con.prepareStatement(query);
	         ResultSet resultSet = pst.executeQuery(query); 

	        while (resultSet.next()) {
	            String nomeScuderia = resultSet.getString("NomeScuderia");
	            int numeroGarePartecipate = resultSet.getInt("NumeroGarePartecipate");
	            int numeroFinanziamenti = resultSet.getInt("NumeroFinanziamenti");

	            report+="Scuderia: " + nomeScuderia + ", Numero Gare Partecipate: " + numeroGarePartecipate +
	                    ", Numero Finanziamenti: " + numeroFinanziamenti+"\n";
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        report+="Errore nella query";
	    }
	    return report;
	}

	public void PilotiVincitoriInCasa(String nazionalitaPilota,String paese) {
		String query = "SELECT p.Codice_Fiscale, p.nome, p.cognome " + 
				"FROM Piloti p "
				+ "JOIN Equipaggio e ON p.Id_Equipaggio = e.Id_Equipaggio "
				+ "JOIN Vettura v ON e.Id_Equipaggio = v.Id_Equipaggio "
				+ "JOIN Partecipazione pa ON v.Id_Vettura = pa.Id_Vettura "
				+ "JOIN Gara g ON pa.nome = g.nome AND pa.data = g.data AND pa.Id_Circuito = g.Id_Circuito "
				+ "JOIN Circuito c ON g.Id_Circuito = c.Id_Circuito "
				+ "WHERE p.nazionalita = '?'  AND pa.Posizionamento = 1 AND c.paese = '?' ";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery(query);

			while (rs.next()) {
				String codiceFiscale = rs.getString("Codice_Fiscale");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");

				System.out.println("IL pilota che ha vinto nel circuito di casa: " + "Codice Fiscale: " + codiceFiscale
						+ ", Nome: " + nome + ", Cognome: " + cognome);
			}

		} catch (

		SQLException e) {
			e.printStackTrace();
			System.out.println("Errore nella query");
		}
	}

	public String VisualizzaPercentualeGentlemanDriver() {

		String query = "SELECT s.Nome AS NomeScuderia, " + "COUNT(DISTINCT p.Codice_Fiscale) AS NumeroPiloti, "
				+ "COUNT(DISTINCT gd.Codice_Fiscale) AS NumeroGentlemanDriver, "
				+ "(COUNT(DISTINCT gd.Codice_Fiscale) / COUNT(DISTINCT p.Codice_Fiscale) * 100) AS PercentualeGentlemanDriver "
				+ "FROM Scuderia s " + "JOIN Vettura v ON s.Id_Scuderia = v.Id_Scuderia "
				+ "JOIN Equipaggio e ON v.Id_Equipaggio = e.Id_Equipaggio "
				+ "JOIN Piloti p ON e.Id_Equipaggio = p.Id_Equipaggio "
				+ "JOIN GentlmanDriver gd ON p.Codice_Fiscale = gd.Codice_Fiscale " + "GROUP BY s.Nome";
		String report="";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery(query);

			while (rs.next()) {
				String nomeScuderia = rs.getString("NomeScuderia");
				int numeroPiloti = rs.getInt("NumeroPiloti");
				int numeroGentlemanDriver = rs.getInt("NumeroGentlemanDriver");
				double percentualeGentlemanDriver = rs.getDouble("PercentualeGentlemanDriver");

				report+="Scuderia: " + nomeScuderia + ", Numero Piloti: " + numeroPiloti
						+ ", Numero Gentleman Driver: " + numeroGentlemanDriver + ", Percentuale Gentleman Driver: "
						+ percentualeGentlemanDriver + "%"+"\n";
			}

		} catch (SQLException e) {
			e.printStackTrace();
			report+="Errore nella query";
		}
		return report;
	}

	public String stampaMensileCostruttori() {
		String query = "SELECT c.RagioneSociale AS NomeCostruttore, "
				+ "COUNT(DISTINCT com.CodiceComponente) AS NumeroComponentiForniti " + "FROM Costruttore c "
				+ "LEFT JOIN Componente com ON c.RagioneSociale = com.RagioneSociale " + "GROUP BY c.RagioneSociale";
		String report="";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery(query);
			
			while (rs.next()) {
				String nomeCostruttore = rs.getString("NomeCostruttore");
				int numeroComponentiForniti = rs.getInt("NumeroComponentiForniti");

				report+="Costruttore: " + nomeCostruttore + ", Numero Componenti Forniti: " + numeroComponentiForniti+"\n";
			}

		} catch (SQLException e) {
			e.printStackTrace();
			report+="Errore nella query";
		}
		return report;
	}
	
	public String stampaClassificaFinaleVetture() {
		String query = "SELECT v.numero_gara, v.Id_Scuderia, "
				+ "SUM(CASE WHEN par.Posizionamento IS NOT NULL THEN 1 ELSE 0 END) AS NumeroGarePartecipate, "
				+ "SUM(CASE WHEN par.Posizionamento IS NOT NULL THEN par.Posizionamento ELSE 0 END) AS PuntiTotali "
				+ "FROM Vettura v " 
				+ " JOIN Partecipazione par ON v.Id_Vettura = par.Id_Vettura "
				+ " JOIN Gara g ON par.nome = g.nome AND par.data = g.data AND par.Id_Circuito = g.Id_Circuito "
				+ "GROUP BY v.numero_gara, v.Id_Scuderia ";
		String result="";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery(query);
			
			while (rs.next()) {
				int numeroGara = rs.getInt("numero_gara");
				int idScuderia = rs.getInt("Id_Scuderia");
				int numeroGarePartecipate = rs.getInt("NumeroGarePartecipate");
				int puntiTotali = rs.getInt("PuntiTotali");
				result += "Numero Gara: " + numeroGara + ", Id Scuderia: " + idScuderia
						+ " , Numero Gare Partecipate: "
						+ numeroGarePartecipate + ", Punti Totali: " + puntiTotali+"\n";
			}

		} catch (SQLException e) {
			e.printStackTrace();
			result += "Errore nella query";
		}
		return result;
	}
	
	public String StampaClassificheFinaliPuntiPerTipoMotore() {
		String query = "SELECT c.tipo_motore, SUM(punti) AS PuntiTotali " +
                "FROM (SELECT v.Id_Scuderia, v.numero_gara, comp.tipo_motore, " +
                "      CASE WHEN par.Posizionamento IS NOT NULL THEN 10 - par.Posizionamento + 1 ELSE 0 END AS punti " +
                "      FROM Vettura v " +
                "      LEFT JOIN Partecipazione par ON v.Id_Vettura = par.Id_Vettura " +
                "      LEFT JOIN Componente comp ON v.Id_Vettura = comp.Id_Vettura) c " +
                "GROUP BY c.tipo_motore";
		 String result = "";

		try { 
		PreparedStatement pst = con.prepareStatement(query); 
		ResultSet rs = pst.executeQuery(); 
		result += "Classifica Finali Punti Per Tipo Motore:\n";
			System.out.println("Classifica finale dei punti per tipo di motore:");
			while (rs.next()) {
				String tipoMotore = rs.getString("tipo_motore");
				int puntiTotali = rs.getInt("PuntiTotali");
				result += "Tipo motore : " + tipoMotore +
				        ", Punti Totali: " + puntiTotali + "\n";

				System.out.println("Tipo Motore: " + tipoMotore + ", Punti Totali: " + puntiTotali);
			}

		} catch (SQLException e) {
			result += "Errore nella query";
			e.printStackTrace();
		}
		return result;
	}

	public String StampaReportPuntiMinutiPerScuderia() {
		String query = "SELECT s.Nome AS NomeScuderia, "
				+ "SUM(CASE WHEN par.Posizionamento IS NOT NULL THEN 10 - par.Posizionamento + 1 ELSE 0 END) AS PuntiTotali, "
				+ "SUM(g.DurataOre) AS MinutiTotali, "
				+ "SUM(CASE WHEN par.Posizionamento IS NOT NULL THEN 10 - par.Posizionamento + 1 ELSE 0 END) / SUM(g.DurataOre) AS Rapporto "
				+ "FROM Scuderia s " + "LEFT JOIN Vettura v ON s.Id_Scuderia = v.Id_Scuderia "
				+ "LEFT JOIN Partecipazione par ON v.Id_Vettura = par.Id_Vettura "
				+ "LEFT JOIN Gara g ON par.nome = g.nome AND par.data = g.data AND par.Id_Circuito = g.Id_Circuito "
				+ "GROUP BY s.Nome";

	    String result = "";

	    try (PreparedStatement pst = con.prepareStatement(query);
	         ResultSet rs = pst.executeQuery()) {

	        result += "Report Punti/Minuti per Scuderia:\n";

	        while (rs.next()) {
	            String nomeScuderia = rs.getString("NomeScuderia");
	            int puntiTotali = rs.getInt("PuntiTotali");
	            double minutiTotali = rs.getDouble("MinutiTotali");
	            double rapporto = rs.getDouble("Rapporto");

	            result += "Scuderia: " + nomeScuderia +
	                    ", Punti Totali: " + puntiTotali +
	                    ", Minuti Totali: " + minutiTotali +
	                    ", Rapporto Punti/Minuti: " + rapporto + "\n";
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        result += "Errore nella query";
	    }

	    return result;
	}

	public List<Integer> caricaIdScuderia() {
	    List<Integer> idScuderiaList = new ArrayList<>();
	    try {
	        String query = "SELECT Id_Scuderia FROM Scuderia";
	        try (PreparedStatement pst = con.prepareStatement(query)) {
	            ResultSet rs = pst.executeQuery();
	            while (rs.next()) {
	            	idScuderiaList.add(rs.getInt("Id_Scuderia"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return idScuderiaList;
	}

	public List<String> caricaGentleman() {
	    List<String> gentleman = new ArrayList<>();
	    try {
	        String query = "SELECT Codice_Fiscale FROM gentlmandriver";
	        try (PreparedStatement pst = con.prepareStatement(query)) {
	            ResultSet rs = pst.executeQuery();
	            while (rs.next()) {
	            	gentleman.add(rs.getString("Codice_Fiscale"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return gentleman;
	}
	public List<Integer> caricaidv() {
	    List<Integer> idv = new ArrayList<>();
	    try {
	        String query = "SELECT Id_Vettura FROM vettura";
	        try (PreparedStatement pst = con.prepareStatement(query)) {
	            ResultSet rs = pst.executeQuery();
	            while (rs.next()) {
	            	idv.add(rs.getInt("Id_Vettura"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return idv;
	}
	public List<String> caricaNomegara() {
	    List<String> nomeg = new ArrayList<>();
	    try {
	        String query = "SELECT nome FROM gara";
	        try (PreparedStatement pst = con.prepareStatement(query)) {
	            ResultSet rs = pst.executeQuery();
	            while (rs.next()) {
	            	nomeg.add(rs.getString("nome"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return nomeg;
	}
}